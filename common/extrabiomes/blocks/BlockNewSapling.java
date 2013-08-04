/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.GeneralSettings;
import extrabiomes.lib.SaplingSettings;
import extrabiomes.module.summa.TreeSoilRegistry;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType;
import extrabiomes.module.summa.worldgen.WorldGenBaldCypressTree;
import extrabiomes.module.summa.worldgen.WorldGenBigAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenJapaneseMapleShrub;
import extrabiomes.module.summa.worldgen.WorldGenJapaneseMapleTree;
import extrabiomes.module.summa.worldgen.WorldGenRainbowEucalyptusTree;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;

public class BlockNewSapling extends BlockFlower {

    public enum BlockType {
        BALD_CYPRESS(0), JAPANESE_MAPLE(1), JAPANESE_MAPLE_SHRUB(2), RAINBOW_EUCALYPTUS(3);

        private final int metadata;

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        public int metadata() {
            return metadata;
        }
    }

	int saplingID = 0;
	static int saplingLifespan = 5000;
    
    private Icon[] textures  = {null, null, null, null, null, null, null, null};

    private static final int METADATA_BITMASK = 0x7;
    private static final int METADATA_MARKBIT = 0x8;

    private static int       forestrySoilID   = 0;

    private static boolean isEnoughLightToGrow(World world, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) >= 9;
    }

    private static boolean isMarkedMetadata(int metadata) {
        return (metadata & METADATA_MARKBIT) != 0;
    }

    private static int markedMetadata(int metadata) {
        return metadata | METADATA_MARKBIT;
    }

    public static void setForestrySoilID(int soilID) {
        forestrySoilID = soilID;
    }

    private static int unmarkedMetadata(int metadata) {
        return metadata & METADATA_BITMASK;
    }

    public BlockNewSapling(int id) {
        super(id);
        final float var3 = 0.4F;
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        
        saplingID = id;
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegistry) {
	    textures[0] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingbaldcypress");
	    textures[1] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingjapanesemaple");
	    textures[2] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingjapanesemapleshrub");
	    textures[3] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingrainboweucalyptus");
    }

    private void attemptGrowTree(World world, int x, int y, int z, Random rand) {
        if (isEnoughLightToGrow(world, x, y + 1, z) && rand.nextInt(7) == 0) {
            final int metadata = world.getBlockMetadata(x, y, z);

            if (!isMarkedMetadata(metadata)) {
                world.setBlockMetadataWithNotify(x, y, z, markedMetadata(metadata), 3);
            } else {
                growTree(world, x, y, z, rand);
            }
        }
    }

    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int id) {
        return TreeSoilRegistry.isValidSoil(id);
    }

    @Override
    public int damageDropped(int metadata) {
        return unmarkedMetadata(metadata);
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        metadata = unmarkedMetadata(metadata);
        
        // unmarkedMetadata has the potential to return a value between 0 and 7, since only 0 to 6 are valid we need to check validity.
        if (metadata < 0 || metadata > 3) metadata = 0;
        
        return textures[metadata];
    }

    public void markOrGrowMarked(World world, int x, int y, int z, Random rand) {
        int marked = world.getBlockMetadata(x, y, z);

        if ((marked & 8) == 0) {
        	world.setBlockMetadataWithNotify(x, y, z, marked | 8, 4);
        } else {
            this.growTree(world, x, y, z, rand);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
    	for (final BlockType blockType : BlockType.values()) {
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
    	}
    }

    public void growTree(World world, int x, int y, int z, Random rand) {
        final int metadata = unmarkedMetadata(world.getBlockMetadata(x, y, z));
        WorldGenerator tree = null;
        int x1 = 0;
        int z1 = 0;
        boolean isHuge = false;

        final boolean isForestryFarmed = world.getBlockId(x, y - 1, z) == forestrySoilID;

        
        if (metadata == BlockType.BALD_CYPRESS.metadata() || metadata == BlockType.RAINBOW_EUCALYPTUS.metadata()) {
        	for (x1 = 0; x1 >= -1; --x1) {
                for (z1 = 0; z1 >= -1; --z1)
                    if (isSameSapling(world, x + x1, y, z + z1, metadata) && isSameSapling(world, x + x1 + 1, y, z + z1, metadata) && isSameSapling(world, x + x1, y, z + z1 + 1, metadata) && isSameSapling(world, x + x1 + 1, y, z + z1 + 1, metadata)) {
                    	if(metadata == BlockType.BALD_CYPRESS.metadata()){
                    		tree = new WorldGenBaldCypressTree(true);
                    	} else {
                    		tree = new WorldGenRainbowEucalyptusTree(true);
                    	}
                        isHuge = true;
                        break;
                    }
                if (tree != null) break;
            }
        } else if(metadata == BlockType.JAPANESE_MAPLE.metadata()) {
        	tree = new WorldGenJapaneseMapleTree(true);
        } else {
        	tree = new WorldGenJapaneseMapleShrub(true);
        }
        
        if (tree != null) {
        	if (isHuge) {
                world.setBlock(x + x1, y, z + z1, 0);
                world.setBlock(x + x1 + 1, y, z + z1, 0);
                world.setBlock(x + x1, y, z + z1 + 1, 0);
                world.setBlock(x + x1 + 1, y, z + z1 + 1, 0);
            } else {
                world.setBlock(x, y, z, 0);
            }

            if (!tree.generate(world, rand, x + x1, y, z + z1)) {
                if (isHuge) {
                    world.setBlock(x + x1, y, z + z1, blockID, metadata, 3);
                    world.setBlock(x + x1 + 1, y, z + z1, blockID, metadata, 3);
                    world.setBlock(x + x1, y, z + z1 + 1, blockID, metadata, 3);
                    world.setBlock(x + x1 + 1, y, z + z1 + 1, blockID, metadata, 3);
                } else
                    world.setBlock(x, y, z, blockID, metadata, 3);
            } else if (isForestryFarmed) if (isHuge) {
                world.setBlock(x + x1, y - 1, z + z1, Block.sand.blockID);
                world.setBlock(x + x1 + 1, y - 1, z + z1, Block.sand.blockID);
                world.setBlock(x + x1, y - 1, z + z1 + 1, Block.sand.blockID);
                world.setBlock(x + x1 + 1, y - 1, z + z1 + 1, Block.sand.blockID);
            } else {
                world.setBlock(x, y - 1, z, Block.sand.blockID);
            }
        }//*/
    }

    public boolean isSameSapling(World world, int x, int y, int z, int metadata) {
        return world.getBlockId(x, y, z) == blockID && unmarkedMetadata(world.getBlockMetadata(x, y, z)) == metadata;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, rand);
            attemptGrowTree(world, x, y, z, rand);
        }
    }
    
    public static int getSaplingLifespan(){
    	return saplingLifespan;
    }
    
    public static void setSaplingLifespan(int life){
    	saplingLifespan = (life > 0) ? life : 0;
    }
    
    @ForgeSubscribe
    public void itemExpiring(ItemExpireEvent event) {
    	if(event.entityItem.getEntityItem().itemID == saplingID){
    		int count = event.entityItem.getEntityItem().stackSize;
	    	for(int i = 0; i < count; i++) {
	    		
	    		int metadata = unmarkedMetadata(event.entityItem.getEntityItem().getItemDamage());
	    		int posX = (int)Math.floor(event.entityItem.lastTickPosX);
	    		int posY = (int)Math.floor(event.entityItem.lastTickPosY);
	    		int posZ = (int)Math.floor(event.entityItem.lastTickPosZ);
	    		double chance = event.entityItem.worldObj.rand.nextDouble() * 100;
	    		boolean replant = false;
	    		    		
	    		
	    		
	    		//event.entityItem
	    		if(canThisPlantGrowOnThisBlockID(event.entityItem.worldObj.getBlockId(posX, posY - 1, posZ))) {
	    			// Determine if the sapling should despawn
	    			double ratio = ((!GeneralSettings.bigTreeSaplingDropModifier) ? 1.0D : 4.0D);
	    			
	    			if(metadata == BlockType.BALD_CYPRESS.metadata() && chance <= SaplingSettings.BALD_CYPRESS.chance() * ratio){
	    				plant2x2Sapling(posX, posY, posZ, event.entityItem.worldObj, event.entityItem.getEntityItem());
	    			} else if(metadata == BlockType.RAINBOW_EUCALYPTUS.metadata() && chance <= SaplingSettings.RAINBOW_EUCALYPTUS.chance() * ratio){
	    				plant2x2Sapling(posX, posY, posZ, event.entityItem.worldObj, event.entityItem.getEntityItem());
	    			} else if(event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.JAPANESE_MAPLE.metadata() && chance <= SaplingSettings.JAPANESE_MAPLE.chance()){
	    				event.entityItem.worldObj.setBlock(posX, posY, posZ, saplingID, metadata, 2);
	    			} else if(event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.JAPANESE_MAPLE_SHRUB.metadata() && chance <= SaplingSettings.JAPANESE_MAPLE_SHRUB.chance()){
	    				event.entityItem.worldObj.setBlock(posX, posY, posZ, saplingID, metadata, 2);
	    			}
	    		}
    		}
    	}
    }
    
    private void plant2x2Sapling(int x, int y, int z, World world, ItemStack sapling) {
    	int metadata = sapling.getItemDamage();
    	
    	// check station one blocks for validity
    	if((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x+1, y, z) || isSameSaplingBlock(x+1, y, z, world, sapling)) && (world.isAirBlock(x+1, y, z+1) || isSameSaplingBlock(x+1, y, z+1, world, sapling)) && (world.isAirBlock(x, y, z+1) || isSameSaplingBlock(x, y, z+1, world, sapling))) {
    		if(world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z))) {
    			world.setBlock(x, y, z, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x+1, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x+1, y-1, z))) {
    			world.setBlock(x+1, y, z, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x+1, y, z+1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x+1, y-1, z+1))) {
    			world.setBlock(x+1, y, z+1, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x, y, z+1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z+1))) {
    			world.setBlock(x, y, z+1, saplingID, metadata, 2);
    			return;
    		}
    	}

    	// check station 2
    	if((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x, y, z+1) || isSameSaplingBlock(x, y, z+1, world, sapling)) && (world.isAirBlock(x-1, y, z+1) || isSameSaplingBlock(x-1, y, z+1, world, sapling)) && (world.isAirBlock(x-1, y, z) || isSameSaplingBlock(x-1, y, z, world, sapling))) {
    		if(world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z))) {
    			world.setBlock(x, y, z, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x, y, z+1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z+1))) {
    			world.setBlock(x, y, z+1, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x-1, y, z+1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x-1, y-1, z+1))) {
    			world.setBlock(x-1, y, z+1, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x-1, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x-1, y-1, z))) {
    			world.setBlock(x-1, y, z, saplingID, metadata, 2);
    			return;
    		}
    	}
    	
    	// Check station 3
    	if((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x-1, y, z) || isSameSaplingBlock(x-1, y, z, world, sapling)) && (world.isAirBlock(x-1, y, z-1) || isSameSaplingBlock(x-1, y, z-1, world, sapling)) && (world.isAirBlock(x, y, z-1) || isSameSaplingBlock(x, y, z-1, world, sapling))) {
    		if(world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z))) {
    			world.setBlock(x, y, z, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x-1, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x-1, y-1, z))) {
    			world.setBlock(x-1, y, z, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x-1, y, z-1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x-1, y-1, z-1))) {
    			world.setBlock(x-1, y, z-1, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x, y, z-1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z-1))) {
    			world.setBlock(x, y, z-1, saplingID, metadata, 2);
    			return;
    		}
    	}
    	
    	// Check station 4
    	if((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x, y, z-1) || isSameSaplingBlock(x, y, z-1, world, sapling)) && (world.isAirBlock(x+1, y, z-1) || isSameSaplingBlock(x+1, y, z-1, world, sapling)) && (world.isAirBlock(x+1, y, z) || isSameSaplingBlock(x+1, y, z, world, sapling))) {
    		if(world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z))) {
    			world.setBlock(x, y, z, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x, y, z-1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z-1))) {
    			world.setBlock(x, y, z-1, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x+1, y, z-1) && canThisPlantGrowOnThisBlockID(world.getBlockId(x+1, y-1, z-1))) {
    			world.setBlock(x+1, y, z-1, saplingID, metadata, 2);
    			return;
    		}
    		if(world.isAirBlock(x+1, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x+1, y-1, z))) {
    			world.setBlock(x+1, y, z, saplingID, metadata, 2);
    			return;
    		}
    	}
    }
    
    private boolean isSameSaplingBlock(int x, int y, int z, World world, ItemStack sapling){
    	int id = world.getBlockId(x, y, z);
    	int metadata = world.getBlockMetadata(x, y, z);
        return id != 0 && Block.blocksList[id] != null && sapling.itemID == id && sapling.getItemDamage() == metadata;
    }
    
    @ForgeSubscribe
    public void itemEntering(EntityJoinWorldEvent event) {
    	if(event.entity instanceof EntityItem && !event.world.isRemote){
    		if(((EntityItem)event.entity).getEntityItem().itemID == saplingID){
    			//LogHelper.info("A sapling entered the world.");
    			((EntityItem)event.entity).lifespan = saplingLifespan;
    		}
    	}
    }
}
