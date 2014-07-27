package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.network.packet.Packet51MapChunk;
//import net.minecraft.server.management.PlayerInstance;
//import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.handlers.GenesisBiomeOverrideHandler;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.GenesisChunkProvider;

public class BlockMachine extends Block {

	static boolean	doingGenesis	= false;

	public BlockMachine() {
		super(Material.iron);
		// setCreativeTab(CreativeTabs.blocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		System.out.println("Registering IIcon for " + getClass().getName());
		blockIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH
				+ "genesis");
	}

	@Override
	public IIcon getIcon(int side, int metadata) {
		return blockIcon;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!doingGenesis && !world.isRemote && canDoGenesis(world, x, y, z, 3, player)) {
			doingGenesis = true;
			doGenesis(world, x, y, z, 3, player);
			doingGenesis = false;
		}
		return false;
	}
	
	public boolean canDoGenesis(World world, int x, int y, int z, int range, EntityPlayer sender){
		// Only run in the overworld
		if(sender.dimension != 0) return false;
		
		Integer chunkX = x >> 4;
		Integer chunkZ = z >> 4;
		
		for (int x1 = chunkX.intValue() - range; x1 <= chunkX.intValue() + range; x1++) {
			for (int z1 = chunkZ.intValue() - range; z1 <= chunkZ.intValue() + range; z1++) {
				
				ChunkCoordinates chunkcoordinates = world.getSpawnPoint();
	            int k = x1 * 16 + 8 - chunkcoordinates.posX;
	            int l = z1 * 16 + 8 - chunkcoordinates.posZ;
	            short short1 = 128;

	            if (k < -short1 || k > short1 || l < -short1 || l > short1){
	            } else {
                    /*
	            	ChatMessageComponent message = new ChatMessageComponent();
	            	message.addText("[Project Lazareth] - You are to close to spawn!");
	            	message.setBold(true);
	            	message.setColor(EnumChatFormatting.AQUA);
	            	
	            	
					sender.sendChatToPlayer(message);
					*/
	            	//LogHelper.info("Spawn Chunk At: (X: %d, Z: %d)", x1, z1);
	            	return false;
	            }
				
				//final Chunk chunk = world.getChunkFromBlockCoords(x, z);
				//chunks.put(Pair.of(x1, z1), chunk); // save map of chunks
				//providerServer.unloadChunksIfNotNearSpawn(x1, z1);
			}
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public void doGenesis(World world, int x, int y, int z, int range, EntityPlayer sender) {
		// pick a new biome
		LogHelper.info("Starting genesis at " + x + "," + z);

		final BiomeSettings[] biomes = BiomeSettings.values();
		// final BiomeSettings[] biomes = { BiomeSettings.AUTUMNWOODS,
		// BiomeSettings.DESERT, BiomeSettings.GREENSWAMP };

		final BiomeGenBase oldBiome = world.getBiomeGenForCoords(x, z);
		BiomeGenBase newBiome = null;
		// newBiome = BiomeSettings.AUTUMNWOODS.getBiome().get();

		do {
			Optional<? extends BiomeGenBase> opt = biomes[world.rand.nextInt(biomes.length)].getBiome();
			if (opt.isPresent()) newBiome = opt.get();
		} while (oldBiome == newBiome || newBiome == null);

		LogHelper.info("Old biome was " + oldBiome);
		LogHelper.info("New biome will be " + newBiome);

		GenesisBiomeOverrideHandler.enable(newBiome.biomeID);

		Integer dimension = null;
		Integer chunkX = sender.getPlayerCoordinates().posX >> 4;
		Integer chunkZ = sender.getPlayerCoordinates().posZ >> 4;
		dimension = Integer.valueOf(sender.dimension);
		if (( chunkX == null ) || ( chunkZ == null )) {
			chunkX = Integer.valueOf((int) sender.posX >> 4);
			chunkZ = Integer.valueOf((int) sender.posZ >> 4);
		}
		WorldServer worldObj = DimensionManager.getWorld(0);
		if (worldObj == null) {
			LogHelper.warning("Target dimension 0 is not loaded for genesis?!");
		}
		ChunkProviderServer providerServer = (ChunkProviderServer) worldObj.getChunkProvider();
		GenesisChunkProvider providerGenesis = new GenesisChunkProvider(world, newBiome);

		// clear players from the danger zone and force unload chunks in
		// question
		List<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();
		Map<EntityPlayerMP, Vec3> playerOrigPositions = new HashMap<EntityPlayerMP, Vec3>();
		players.addAll(worldObj.playerEntities);

		final int safePad = 2;
		final double safeX = ( ( chunkX.intValue() - range - safePad ) << 4 ) - 8.0;
		final double safeZ = ( ( chunkZ.intValue() - range - safePad ) << 4 ) - 8.0;
		LogHelper.info("Safe position = " + safeX + "," + safeZ);

		Map<Pair<Integer, Integer>, Chunk> chunks = new HashMap<Pair<Integer, Integer>, Chunk>();
		for (int x1 = chunkX.intValue() - range; x1 <= chunkX.intValue() + range; x1++) {
			for (int z1 = chunkZ.intValue() - range; z1 <= chunkZ.intValue() + range; z1++) {
				for (EntityPlayerMP player : players) {
					if (worldObj.getPlayerManager().isPlayerWatchingChunk(player, x1, z1) && !playerOrigPositions.containsKey(player)) {
						final Vec3 origPosition = Vec3.createVectorHelper(player.posX, player.posY,player.posZ);
						playerOrigPositions.put(player, origPosition);
						LogHelper.info("Moving player " + player);
						player.setLocationAndAngles(safeX, player.posY, safeZ, 0.0F, 0.0F);
						worldObj.updateEntityWithOptionalForce(player, true);
					}
				}
				final Chunk chunk = world.getChunkFromBlockCoords(x, z);
				chunks.put(Pair.of(x1, z1), chunk); // save map of chunks
				providerServer.unloadChunksIfNotNearSpawn(x1, z1);
			}
		}

		// verify that chunks unloaded
		int lastloaded = 0;
		while (providerServer.getLoadedChunkCount() != lastloaded) {
			lastloaded = providerServer.getLoadedChunkCount();
			providerServer.unloadQueuedChunks();

			for (final Pair<Integer, Integer> coord : chunks.keySet()) {
				final Chunk chunk = chunks.get(coord);
				if (chunk != null) {
					if (chunk.isChunkLoaded) {
						LogHelper.warning("Failed to unload chunk @ " + coord);
						/*
						 * } else { LogHelper.info("Chunk unloaded @ " + coord);
						 */
					}
				}
			}
		}
		chunks.clear();
		LogHelper.info(providerServer.makeString());

		IChunkLoader chunkloader = providerServer.currentChunkLoader;
		providerServer.currentChunkLoader = null;
		for (int x1 = chunkX.intValue() - range; x1 <= chunkX.intValue() + range; x1++) {
			for (int z1 = chunkZ.intValue() - range; z1 <= chunkZ.intValue() + range; z1++) {
				// GenesisBiomeOverrideHandler.myQueue.add(new
				// GenesisBiomeOverrideHandler.coord(x1, z1));

				final Chunk chunk = providerServer.loadChunk(x1, z1);
				
				byte[] chunkBiomes = chunk.getBiomeArray();
				for (int k = 0; k < chunkBiomes.length; ++k) {
					chunkBiomes[k] = (byte) newBiome.biomeID;
				}
				chunk.setBiomeArray(chunkBiomes);
				
				//providerServer.populate(providerGenesis, x1, z1);
				
				
				 
				//chunk.sendUpdates = true;
				//chunk.setStorageArrays(par1ArrayOfExtendedBlockStorage);
				chunk.setChunkModified();
			}
		}

		providerServer.currentChunkLoader = chunkloader;

		// notifyAdmins(sender,
		// String.format("%s regenerated chunks (%d, %d)+-%d in Dimension %d",
		// new Object[] { sender.getCommandSenderName(), chunkX, chunkZ,
		// Integer.valueOf(range), Integer.valueOf(caller.dimension) }), new
		// Object[0]);
		for (int x1 = chunkX.intValue() - range; x1 <= chunkX.intValue() + range; x1++) {
			for (int z1 = chunkZ.intValue() - range; z1 <= chunkZ.intValue() + range; z1++) {
                /*
				PlayerInstance chunkwatcher = worldObj.getPlayerManager().getOrCreateChunkWatcher(x1, z1, false);
				if (chunkwatcher != null) {
					chunkwatcher.sendToAllPlayersWatchingChunk(new Packet51MapChunk(worldObj.getChunkFromChunkCoords(x1, z1), true, -1));
				}
				*/
			}
		}

		GenesisBiomeOverrideHandler.disable();

		// TODO: restore players to their original saved positions
		for (EntityPlayerMP player : players) {
			LogHelper.info(player.toString());
		}

		/*
		 * LogHelper.info("GENESIS @ " + x + "," + y + "," + z + " by " +
		 * player.getDisplayName());
		 * 
		 * world.setBlockToAir(x, y, z); WorldServer worldServer = (WorldServer)
		 * world;
		 * 
		 * 
		 * 
		 * LogHelper.info(oldBiome.biomeName + " >> " + newBiome.biomeName);
		 * 
		 * // update chunk's biome values Chunk chunk =
		 * world.getChunkFromBlockCoords(x, z); byte[] chunkBiomes =
		 * chunk.getBiomeArray(); for (int k = 0; k < chunkBiomes.length; ++k) {
		 * chunkBiomes[k] = (byte) ( newBiome.biomeID & 0xFF ); }
		 * chunk.setBiomeArray(chunkBiomes); chunk.needsSaving(true);
		 * 
		 * LogHelper.info("-1");
		 * 
		 * int chunkX = chunk.xPosition; int chunkZ = chunk.zPosition;
		 * 
		 * // TODO: move all players in the blast radius out of the way
		 * List<EntityPlayerMP> players = Lists.newArrayList();
		 * players.addAll(worldServer.playerEntities); for (EntityPlayerMP p :
		 * players) { if(
		 * worldServer.getPlayerManager().isPlayerWatchingChunk(p, chunkX,
		 * chunkZ)) { LogHelper.info(" - pushing " + p);
		 * p.setLocationAndAngles((chunkX - 5) << 4, p.posY, (chunkZ - 5) << 4,
		 * 0F, 0F); worldServer.updateEntityWithOptionalForce(p, false); } }
		 * 
		 * LogHelper.info("0");
		 * 
		 * // unload affected chunk GenesisChunkProvider provider = new
		 * GenesisChunkProvider(world, newBiome);
		 * provider.unloadChunksIfNotNearSpawn(chunkX, chunkZ);
		 * 
		 * int loadCount = 0; while (provider.getLoadedChunkCount() !=
		 * loadCount) { loadCount = provider.getLoadedChunkCount();
		 * provider.unloadQueuedChunks(); if (chunk != null) { if(
		 * chunk.isChunkLoaded ) { LogHelper.warning("Failed to unload chunk!");
		 * loadCount = 0; } else { LogHelper.info("Chunk unloaded."); } chunk =
		 * null; // better safe than sorry } }
		 * 
		 * LogHelper.info("1");
		 * 
		 * // regenerate the chunk IChunkLoader loader =
		 * provider.getCurrentChunkLoader();
		 * provider.setCurrentChunkLoader(null); provider.loadChunk(chunkX,
		 * chunkZ); provider.populate(provider, chunkX, chunkZ);
		 * provider.setCurrentChunkLoader(loader);
		 * 
		 * LogHelper.info("2");
		 * 
		 * PlayerInstance watcher = worldServer.getPlayerManager()
		 * .getOrCreateChunkWatcher(chunkX, chunkZ, false); if (watcher != null)
		 * { chunk = world.getChunkFromChunkCoords(chunkZ, chunkZ);
		 * Packet51MapChunk packet = new Packet51MapChunk(chunk, true, -1);
		 * watcher.sendToAllPlayersWatchingChunk(packet); } else {
		 * LogHelper.warning("Unable to find chunk watcher!"); }
		 * 
		 * LogHelper.info("3");
		 */
	}

}
