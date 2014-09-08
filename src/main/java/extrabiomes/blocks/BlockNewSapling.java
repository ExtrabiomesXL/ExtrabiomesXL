/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.ToolTipStringFormatter;
import extrabiomes.lib.GeneralSettings;
import extrabiomes.lib.SaplingSettings;
import extrabiomes.module.summa.TreeSoilRegistry;
import extrabiomes.module.summa.worldgen.WorldGenBaldCypressTree;
import extrabiomes.module.summa.worldgen.WorldGenJapaneseMapleShrub;
import extrabiomes.module.summa.worldgen.WorldGenJapaneseMapleTree;
import extrabiomes.module.summa.worldgen.WorldGenRainbowEucalyptusTree;
import extrabiomes.module.summa.worldgen.WorldGenSakuraBlossomTree;

public class BlockNewSapling extends BlockFlower
{
    
    public enum BlockType
    {
        BALD_CYPRESS(0, new String[] {"2x2"}), JAPANESE_MAPLE(1,  new String[] {"1x1"}), JAPANESE_MAPLE_SHRUB(2,  new String[] {"1x1"}), RAINBOW_EUCALYPTUS(3,  new String[] {"2x2"}), SAKURA_BLOSSOM(4,  new String[] {"1x1"});
        
        private final int metadata;
        private final String[] toolTipData;
        
        BlockType(int metadata, String[] data)
        {
            this.metadata = metadata;
            this.toolTipData = data;
        }
        
        public int metadata()
        {
            return metadata;
        }
        
        public String[] toolTipData(){
        	return toolTipData;
        }
    }
    
    static int               saplingLifespan  = 5000;
    
    private IIcon[]           textures         = { null, null, null, null, null, null, null, null };
    
    private static final int METADATA_BITMASK = 0x7;
    private static final int METADATA_MARKBIT = 0x8;
    
    private static Block     forestrySoil;
    
    private static boolean isEnoughLightToGrow(World world, int x, int y, int z)
    {
        return world.getBlockLightValue(x, y, z) >= 9;
    }
    
    private static boolean isMarkedMetadata(int metadata)
    {
        return (metadata & METADATA_MARKBIT) != 0;
    }
    
    private static int markedMetadata(int metadata)
    {
        return metadata | METADATA_MARKBIT;
    }
    
    public static void setForestrySoil(Block soil)
    {
        forestrySoil = soil;
    }
    
    private static int unmarkedMetadata(int metadata)
    {
        return metadata & METADATA_BITMASK;
    }
    
    public BlockNewSapling()
    {
        super(0);
        final float var3 = 0.4F;
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister IIconRegistry)
    {
        textures[0] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingbaldcypress");
        textures[1] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingjapanesemaple");
        textures[2] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingjapanesemapleshrub");
        textures[3] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingrainboweucalyptus");
        textures[4] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingsakura");
    }
    
    private void attemptGrowTree(World world, int x, int y, int z, Random rand)
    {
        if (isEnoughLightToGrow(world, x, y + 1, z) && rand.nextInt(7) == 0)
        {
            final int metadata = world.getBlockMetadata(x, y, z);
            
            if (!isMarkedMetadata(metadata))
            {
                world.setBlockMetadataWithNotify(x, y, z, markedMetadata(metadata), 3);
            }
            else
            {
                growTree(world, x, y, z, rand);
            }
        }
    }
    
    protected boolean canThisPlantGrowOnThisBlock(Block block)
    {
        return TreeSoilRegistry.isValidSoil(block);
    }
    
    @Override
    public int damageDropped(int metadata)
    {
        return unmarkedMetadata(metadata);
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        metadata = unmarkedMetadata(metadata);
        
        // unmarkedMetadata has the potential to return a value between 0 and 7, since only 0 to 6 are valid we need to check validity.
        if (textures[metadata] == null)
            metadata = 0;
        
        return textures[metadata];
    }
    
    public void markOrGrowMarked(World world, int x, int y, int z, Random rand)
    {
        int marked = world.getBlockMetadata(x, y, z);
        
        if ((marked & 8) == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, marked | 8, 4);
        }
        else
        {
            this.growTree(world, x, y, z, rand);
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List itemList)
    {
        for (final BlockType blockType : BlockType.values())
        {
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
        }
    }
    
    public void growTree(World world, int x, int y, int z, Random rand)
    {
        final int metadata = unmarkedMetadata(world.getBlockMetadata(x, y, z));
        WorldGenerator tree = null;
        int x1 = 0;
        int z1 = 0;
        boolean isHuge = false;
        
        final boolean isForestryFarmed = world.getBlock(x, y - 1, z) == forestrySoil;
        
        if (metadata == BlockType.BALD_CYPRESS.metadata() || metadata == BlockType.RAINBOW_EUCALYPTUS.metadata())
        {
            for (x1 = 0; x1 >= -1; --x1)
            {
                for (z1 = 0; z1 >= -1; --z1)
                    if (isSameSapling(world, x + x1, y, z + z1, metadata) && isSameSapling(world, x + x1 + 1, y, z + z1, metadata) && isSameSapling(world, x + x1, y, z + z1 + 1, metadata) && isSameSapling(world, x + x1 + 1, y, z + z1 + 1, metadata))
                    {
                        if (metadata == BlockType.BALD_CYPRESS.metadata())
                        {
                            tree = new WorldGenBaldCypressTree(true);
                        }
                        else
                        {
                            tree = new WorldGenRainbowEucalyptusTree(true);
                        }
                        isHuge = true;
                        break;
                    }
                if (tree != null)
                    break;
            }
        }
        else if (metadata == BlockType.JAPANESE_MAPLE.metadata())
        {
            tree = new WorldGenJapaneseMapleTree(true);
        }
        else if (metadata == BlockType.SAKURA_BLOSSOM.metadata())
        {
            tree = new WorldGenSakuraBlossomTree(true);
        }
        else
        {
            tree = new WorldGenJapaneseMapleShrub(true);
        }
        
        if (tree != null)
        {
            if (isHuge)
            {
                world.setBlock(x + x1, y, z + z1, Blocks.air);
                world.setBlock(x + x1 + 1, y, z + z1, Blocks.air);
                world.setBlock(x + x1, y, z + z1 + 1, Blocks.air);
                world.setBlock(x + x1 + 1, y, z + z1 + 1, Blocks.air);
            }
            else
            {
                world.setBlock(x, y, z, Blocks.air);
            }
            
            if (!tree.generate(world, rand, x + x1, y, z + z1))
            {
                if (isHuge)
                {
                    world.setBlock(x + x1, y, z + z1, this, metadata, 3);
                    world.setBlock(x + x1 + 1, y, z + z1, this, metadata, 3);
                    world.setBlock(x + x1, y, z + z1 + 1, this, metadata, 3);
                    world.setBlock(x + x1 + 1, y, z + z1 + 1, this, metadata, 3);
                }
                else
                    world.setBlock(x, y, z, this, metadata, 3);
            }
            else if (isForestryFarmed)
                if (isHuge)
                {
                    world.setBlock(x + x1, y - 1, z + z1, Blocks.sand);
                    world.setBlock(x + x1 + 1, y - 1, z + z1, Blocks.sand);
                    world.setBlock(x + x1, y - 1, z + z1 + 1, Blocks.sand);
                    world.setBlock(x + x1 + 1, y - 1, z + z1 + 1, Blocks.sand);
                }
                else
                {
                    world.setBlock(x, y - 1, z, Blocks.sand);
                }
        }//*/
    }
    
    public boolean isSameSapling(World world, int x, int y, int z, int metadata)
    {
        return world.getBlock(x, y, z) == this && unmarkedMetadata(world.getBlockMetadata(x, y, z)) == metadata;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, x, y, z, rand);
            attemptGrowTree(world, x, y, z, rand);
        }
    }
    
    public static int getSaplingLifespan()
    {
        return saplingLifespan;
    }
    
    public static void setSaplingLifespan(int life)
    {
        saplingLifespan = (life > 0) ? life : 0;
    }
    
    @SubscribeEvent
    public void itemExpiring(ItemExpireEvent event)
    {
        if (event.entityItem.getEntityItem().getItem() == ItemBlock.getItemFromBlock(this))
        {
            int count = event.entityItem.getEntityItem().stackSize;
            for (int i = 0; i < count; i++)
            {
                
                int metadata = unmarkedMetadata(event.entityItem.getEntityItem().getItemDamage());
                int posX = (int) Math.floor(event.entityItem.lastTickPosX);
                int posY = (int) Math.floor(event.entityItem.lastTickPosY);
                int posZ = (int) Math.floor(event.entityItem.lastTickPosZ);
                double chance = event.entityItem.worldObj.rand.nextDouble() * 100;
                //event.entityItem
                if (canThisPlantGrowOnThisBlock(event.entityItem.worldObj.getBlock(posX, posY - 1, posZ)))
                {
                    // Determine if the sapling should despawn
                    double ratio = ((!GeneralSettings.bigTreeSaplingDropModifier) ? 1.0D : 4.0D);
                    
                    if (metadata == BlockType.BALD_CYPRESS.metadata() && chance <= SaplingSettings.BALD_CYPRESS.chance() * ratio)
                    {
                        plant2x2Sapling(posX, posY, posZ, event.entityItem.worldObj, event.entityItem.getEntityItem());
                    }
                    else if (metadata == BlockType.RAINBOW_EUCALYPTUS.metadata() && chance <= SaplingSettings.RAINBOW_EUCALYPTUS.chance() * ratio)
                    {
                        plant2x2Sapling(posX, posY, posZ, event.entityItem.worldObj, event.entityItem.getEntityItem());
                    }
                    else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.JAPANESE_MAPLE.metadata() && chance <= SaplingSettings.JAPANESE_MAPLE.chance())
                    {
                        event.entityItem.worldObj.setBlock(posX, posY, posZ, this, metadata, 2);
                    }
                    else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.JAPANESE_MAPLE_SHRUB.metadata() && chance <= SaplingSettings.JAPANESE_MAPLE_SHRUB.chance())
                    {
                        event.entityItem.worldObj.setBlock(posX, posY, posZ, this, metadata, 2);
                    }
                    else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.SAKURA_BLOSSOM.metadata && chance <= SaplingSettings.SAKURA_BLOSSOM.chance())
                    {
                        event.entityItem.worldObj.setBlock(posX, posY, posZ, this, metadata, 2);
                    }
                }
            }
        }
    }
    
    private void plant2x2Sapling(int x, int y, int z, World world, ItemStack sapling)
    {
        int metadata = sapling.getItemDamage();
        
        // check station one blocks for validity
        if ((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x + 1, y, z) || isSameSaplingBlock(x + 1, y, z, world, sapling)) && (world.isAirBlock(x + 1, y, z + 1) || isSameSaplingBlock(x + 1, y, z + 1, world, sapling)) && (world.isAirBlock(x, y, z + 1) || isSameSaplingBlock(x, y, z + 1, world, sapling)))
        {
            if (world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z)) && canThisPlantGrowOnThisBlock(world.getBlock(x + 1, y - 1, z)) && canThisPlantGrowOnThisBlock(world.getBlock(x + 1, y - 1, z + 1)) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z + 1)))
            {
                world.setBlock(x, y, z, this, metadata, 2);
                world.setBlock(x + 1, y, z, this, metadata, 2);
                world.setBlock(x + 1, y, z + 1, this, metadata, 2);
                world.setBlock(x, y, z + 1, this, metadata, 2);
                return;
            }
        }
        
        // check station 2
        if ((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x, y, z + 1) || isSameSaplingBlock(x, y, z + 1, world, sapling)) && (world.isAirBlock(x - 1, y, z + 1) || isSameSaplingBlock(x - 1, y, z + 1, world, sapling)) && (world.isAirBlock(x - 1, y, z) || isSameSaplingBlock(x - 1, y, z, world, sapling)))
        {
            if (world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z)) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z + 1)) && canThisPlantGrowOnThisBlock(world.getBlock(x - 1, y - 1, z + 1)) && canThisPlantGrowOnThisBlock(world.getBlock(x - 1, y - 1, z)))
            {
                world.setBlock(x, y, z, this, metadata, 2);
                world.setBlock(x, y, z + 1, this, metadata, 2);
                world.setBlock(x - 1, y, z + 1, this, metadata, 2);
                world.setBlock(x - 1, y, z, this, metadata, 2);
                return;
            }
        }
        
        // Check station 3
        if ((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x - 1, y, z) || isSameSaplingBlock(x - 1, y, z, world, sapling)) && (world.isAirBlock(x - 1, y, z - 1) || isSameSaplingBlock(x - 1, y, z - 1, world, sapling)) && (world.isAirBlock(x, y, z - 1) || isSameSaplingBlock(x, y, z - 1, world, sapling)))
        {
            if (world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z)) && canThisPlantGrowOnThisBlock(world.getBlock(x - 1, y - 1, z)) && canThisPlantGrowOnThisBlock(world.getBlock(x - 1, y - 1, z - 1)) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z - 1)))
            {
                world.setBlock(x, y, z, this, metadata, 2);
                world.setBlock(x - 1, y, z, this, metadata, 2);
                world.setBlock(x - 1, y, z - 1, this, metadata, 2);
                world.setBlock(x, y, z - 1, this, metadata, 2);
                return;
            }
        }
        
        // Check station 4
        if ((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x, y, z - 1) || isSameSaplingBlock(x, y, z - 1, world, sapling)) && (world.isAirBlock(x + 1, y, z - 1) || isSameSaplingBlock(x + 1, y, z - 1, world, sapling)) && (world.isAirBlock(x + 1, y, z) || isSameSaplingBlock(x + 1, y, z, world, sapling)))
        {
            if (world.isAirBlock(x, y, z) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z)) && canThisPlantGrowOnThisBlock(world.getBlock(x, y - 1, z - 1)) && canThisPlantGrowOnThisBlock(world.getBlock(x + 1, y - 1, z - 1)) && canThisPlantGrowOnThisBlock(world.getBlock(x + 1, y - 1, z)))
            {
                world.setBlock(x, y, z, this, metadata, 2);
                world.setBlock(x, y, z - 1, this, metadata, 2);
                world.setBlock(x + 1, y, z - 1, this, metadata, 2);
                world.setBlock(x + 1, y, z, this, metadata, 2);
                return;
            }
        }
    }
    
    private boolean isSameSaplingBlock(int x, int y, int z, World world, ItemStack sapling)
    {
        Block block = world.getBlock(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);
        return block != null && block != Blocks.air && sapling.getItem() == ItemBlock.getItemFromBlock(this) && sapling.getItemDamage() == metadata;
    }
    
    @SubscribeEvent
    public void itemEntering(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityItem && !event.world.isRemote)
        {
            if (((EntityItem) event.entity).getEntityItem().getItem() == ItemBlock.getItemFromBlock(this))
            {
                //LogHelper.info("A sapling entered the world.");
                ((EntityItem) event.entity).lifespan = saplingLifespan;
            }
        }
    }
    
    public static void addInformation(int metaData, List listOfLines) {
    	BlockType test = BlockType.values()[metaData];
    	
    	if(test != null) {
    		String[] lines = test.toolTipData();
    		if(lines.length < 1) return;
    		
    		if(lines.length > 1) {
    			for(int line = 1; line < lines.length; line++) {
    				listOfLines.add(lines[line]);
    			}
    			
    			if(lines[0] != "") {
    				listOfLines.add("");
    			}
    		}
    		
    		if(lines[0] != "") {
    		  ToolTipStringFormatter.Format(LanguageRegistry.instance().getStringLocalization("extrabiomes.planting_guide") , listOfLines);
        	listOfLines.add(lines[0]);
    		}
    	}
    }
}
