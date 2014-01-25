package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet51MapChunk;
import net.minecraft.server.management.PlayerInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import extrabiomes.handlers.GenesisBiomeOverrideHandler;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.GenesisChunkProvider;

public class BlockMachine extends Block {

	static boolean				doingGenesis	= false;

	public BlockMachine(int id) {
		super(id, Material.iron);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!doingGenesis && !world.isRemote) {
			doingGenesis = true;
			doGenesis(world, x, y, z, player);
			doingGenesis = false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void doGenesis(World world, int x, int y, int z, EntityPlayer sender) {
		// pick a new biome
		BiomeSettings[] biomes = BiomeSettings.values();

		BiomeGenBase oldBiome = world.getBiomeGenForCoords(x, z);
		BiomeGenBase newBiome = null;
		do {
			Optional<?extends BiomeGenBase> opt = biomes[world.rand.nextInt(biomes.length)].getBiome();
			if (opt.isPresent()) newBiome = opt.get();
		} while (oldBiome == newBiome || newBiome == null);
		

	    GenesisBiomeOverrideHandler.enable(newBiome.biomeID);
		
		int range = 3;
	    Integer dimension = null;
	    Integer chunkX = sender.getPlayerCoordinates().posX >> 4;
	    Integer chunkZ = sender.getPlayerCoordinates().posZ >> 4;
	    dimension = Integer.valueOf(sender.dimension);
	    if ((chunkX == null) || (chunkZ == null))
	    { 
	      chunkX = Integer.valueOf((int)sender.posX >> 4);
	      chunkZ = Integer.valueOf((int)sender.posZ >> 4);
	    }
	    WorldServer worldObj = DimensionManager.getWorld(dimension.intValue());
	    if (worldObj == null) {
	      //throw new CommandException("The target world is not loaded", new Object[0]);
	    }
	    ChunkProviderServer chunkprovider = (ChunkProviderServer)worldObj.getChunkProvider();
	    GenesisChunkProvider provider = new GenesisChunkProvider(world, newBiome);
	    
	    List<EntityPlayerMP> players = new ArrayList();
	    players.addAll(worldObj.playerEntities);
	    for (int x1 = chunkX.intValue() - range; x1 <= chunkX.intValue() + range; x1++) {
	      for (int z1 = chunkZ.intValue() - range; z1 <= chunkZ.intValue() + range; z1++)
	      {
	        for (EntityPlayerMP player : players) {
	          if (worldObj.getPlayerManager().isPlayerWatchingChunk(player, x1, z1))
	          {
	            player.setLocationAndAngles(chunkX.intValue() - range - 2 << 4, player.posY, chunkZ.intValue() - range - 2 << 4, 0.0F, 0.0F);
	            worldObj.updateEntityWithOptionalForce(player, false);
	          }
	        }
	        chunkprovider.unloadChunksIfNotNearSpawn(x1, z1);
	        
	      }
	    }
	    
	    Chunk chunk = world.getChunkFromBlockCoords(x, z);
	    
	    int lastloaded = 0;
	    while (chunkprovider.getLoadedChunkCount() != lastloaded)
	    {
	      lastloaded = chunkprovider.getLoadedChunkCount();
	      chunkprovider.unloadQueuedChunks();
	      
	      if (chunk != null) {
				if( chunk.isChunkLoaded ) {
					LogHelper.warning("Failed to unload chunk!");
					//loadCount = 0;
				} else {
					LogHelper.info("Chunk unloaded.");
				}
				chunk = null; // better safe than sorry
			}
	    }
	    System.out.println(chunkprovider.makeString());
	    
	    
	    IChunkLoader chunkloader = chunkprovider.currentChunkLoader;
	    chunkprovider.currentChunkLoader = null;
	    for (int x1 = chunkX.intValue() - range; x1 <= chunkX.intValue() + range; x1++) {
	      for (int z1 = chunkZ.intValue() - range; z1 <= chunkZ.intValue() + range; z1++) {
	    	//GenesisBiomeOverrideHandler.myQueue.add(new GenesisBiomeOverrideHandler.coord(x1, z1));
	    	  
	    	provider.loadChunk(x1, z1);
	    	provider.populate(provider, x1, z1);
	        
	      }
	    }
	    
	    
	    chunkprovider.currentChunkLoader = chunkloader;
	    
	    //notifyAdmins(sender, String.format("%s regenerated chunks (%d, %d)+-%d in Dimension %d", new Object[] { sender.getCommandSenderName(), chunkX, chunkZ, Integer.valueOf(range), Integer.valueOf(caller.dimension) }), new Object[0]);
	    for (int x1 = chunkX.intValue() - range; x1 <= chunkX.intValue() + range; x1++) {
	      for (int z1 = chunkZ.intValue() - range; z1 <= chunkZ.intValue() + range; z1++)
	      {
	        PlayerInstance chunkwatcher = worldObj.getPlayerManager().getOrCreateChunkWatcher(x1, z1, false);
	        if (chunkwatcher != null) {
	          chunkwatcher.sendToAllPlayersWatchingChunk(new Packet51MapChunk(worldObj.getChunkFromChunkCoords(x1, z1), true, -1));
	        }
	      }
	    }
	    
	    GenesisBiomeOverrideHandler.disable();
		
		/*
		LogHelper.info("GENESIS @ " + x + "," + y + "," + z + " by "
				+ player.getDisplayName());
		
		world.setBlockToAir(x, y, z);
		WorldServer worldServer = (WorldServer) world;

		

		LogHelper.info(oldBiome.biomeName + " >> " + newBiome.biomeName);
		
		// update chunk's biome values
		Chunk chunk = world.getChunkFromBlockCoords(x, z);
		byte[] chunkBiomes = chunk.getBiomeArray();
		for (int k = 0; k < chunkBiomes.length; ++k) {
			chunkBiomes[k] = (byte) ( newBiome.biomeID & 0xFF );
		}
		chunk.setBiomeArray(chunkBiomes);
		chunk.needsSaving(true);

		LogHelper.info("-1");

		int chunkX = chunk.xPosition;
		int chunkZ = chunk.zPosition;

		// TODO: move all players in the blast radius out of the way
		List<EntityPlayerMP> players = Lists.newArrayList();
		players.addAll(worldServer.playerEntities);
		for (EntityPlayerMP p : players) {
			if( worldServer.getPlayerManager().isPlayerWatchingChunk(p, chunkX, chunkZ)) {
				LogHelper.info(" - pushing " + p);
				p.setLocationAndAngles((chunkX - 5) << 4, p.posY,
						(chunkZ - 5) << 4, 0F, 0F);
				worldServer.updateEntityWithOptionalForce(p, false);
			}
		}

		LogHelper.info("0");

		// unload affected chunk
		GenesisChunkProvider provider = new GenesisChunkProvider(world,
				newBiome);
		provider.unloadChunksIfNotNearSpawn(chunkX, chunkZ);

		int loadCount = 0;
		while (provider.getLoadedChunkCount() != loadCount) {
			loadCount = provider.getLoadedChunkCount();
			provider.unloadQueuedChunks();
			if (chunk != null) {
				if( chunk.isChunkLoaded ) {
					LogHelper.warning("Failed to unload chunk!");
					loadCount = 0;
				} else {
					LogHelper.info("Chunk unloaded.");
				}
				chunk = null; // better safe than sorry
			}
		}

		LogHelper.info("1");

		// regenerate the chunk
		IChunkLoader loader = provider.getCurrentChunkLoader();
		provider.setCurrentChunkLoader(null);
		provider.loadChunk(chunkX, chunkZ);
		provider.populate(provider, chunkX, chunkZ);
		provider.setCurrentChunkLoader(loader);

		LogHelper.info("2");

		PlayerInstance watcher = worldServer.getPlayerManager()
				.getOrCreateChunkWatcher(chunkX, chunkZ, false);
		if (watcher != null) {
			chunk = world.getChunkFromChunkCoords(chunkZ, chunkZ);
			Packet51MapChunk packet = new Packet51MapChunk(chunk, true, -1);
			watcher.sendToAllPlayersWatchingChunk(packet);
		} else {
			LogHelper.warning("Unable to find chunk watcher!");
		}

		LogHelper.info("3");
		
		*/
	}

}
