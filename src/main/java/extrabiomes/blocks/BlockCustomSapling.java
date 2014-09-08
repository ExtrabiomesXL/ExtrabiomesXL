/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.ToolTipStringFormatter;
import extrabiomes.lib.Element;
import extrabiomes.lib.GeneralSettings;
import extrabiomes.lib.SaplingSettings;
import extrabiomes.module.summa.TreeSoilRegistry;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType;
import extrabiomes.module.summa.worldgen.WorldGenBigAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenCypressTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenNewRedwood;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;

public class BlockCustomSapling extends BlockFlower
{
    
    public enum BlockType
    {
        UMBER(0, new String[] {"1x1"}), GOLDENROD(1, new String[] {"1x1"}), VERMILLION(2, new String[] {"1x1"}), CITRINE(3, new String[] {"1x1"}), FIR(4, new String[] {"1x1, 2x2"}), REDWOOD(5, new String[] {"2x2"}), ACACIA(6, new String[] {"1x1"}), CYPRESS(7, new String[] {"1x1"});
        
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
    
    Block sapling;
    static int               saplingLifespan  = 5000;
    
    private IIcon[]           textures         = { null, null, null, null, null, null, null, null };
    
    private static final int METADATA_BITMASK = 0x7;
    private static final int METADATA_MARKBIT = 0x8;
    
    private static Block       forestrySoil;
    
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
    
    public BlockCustomSapling(int index)
    {
        super(0);
        final float var3 = 0.4F;
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        
        this.sapling = this; // TODO: ???
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister IIconRegistry)
    {
        textures[0] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingbrownautumn");
        textures[1] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingorangeautumn");
        textures[2] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingredautumn");
        textures[3] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingyellowautumn");
        textures[4] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingfir");
        textures[5] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingredwood");
        textures[6] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingacacia");
        textures[7] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingcypress");
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
    
    @Override
    protected boolean canPlaceBlockOn(Block block)
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
        //if (metadata < 0 || metadata > 7) metadata = 0;
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
    public void getSubBlocks(Item id, CreativeTabs tab, List itemList)
    {
        for (final BlockType blockType : BlockType.values())
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
    }
    
    public void growTree(World world, int x, int y, int z, Random rand)
    {
        final int metadata = unmarkedMetadata(world.getBlockMetadata(x, y, z));
        WorldGenerator tree = null;
        int x1 = 0;
        int z1 = 0;
        boolean isHuge = false;
        int offset = 0;
        
        final boolean isForestryFarmed = world.getBlock(x, y - 1, z) == forestrySoil;
        
        if (metadata == BlockType.UMBER.metadata())
        {
            if (rand.nextInt(3) != 0)
            {
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN);
                
                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
            else
            {
                tree = new WorldGenAutumnTree(true, AutumnTreeType.BROWN);
                
                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
        }
        else if (metadata == BlockType.GOLDENROD.metadata())
        {
            if (rand.nextInt(3) != 0)
            {
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE);
                
                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
            else
            {
                tree = new WorldGenAutumnTree(true, AutumnTreeType.ORANGE);
                
                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
        }
        else if (metadata == BlockType.VERMILLION.metadata())
        {
            if (rand.nextInt(3) != 0)
            {
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE);
                
                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
            else
            {
                tree = new WorldGenAutumnTree(true, AutumnTreeType.PURPLE);
                
                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
        }
        else if (metadata == BlockType.CITRINE.metadata())
        {
            if (rand.nextInt(3) != 0)
            {
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW);
                
                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
            else
            {
                tree = new WorldGenAutumnTree(true, AutumnTreeType.YELLOW);
                
                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
            }
        }
        else if (metadata == BlockType.ACACIA.metadata())
        {
            tree = new WorldGenAcacia(true);
        }
        else if (metadata == BlockType.CYPRESS.metadata())
        {
            tree = new WorldGenCypressTree(true);
        }
        else
        {
            // Check for 2x2 firs and redwoods
            for (x1 = 0; x1 >= -1; --x1)
            {
                for (z1 = 0; z1 >= -1; --z1)
                    if (isSameSapling(world, x + x1, y, z + z1, metadata) && isSameSapling(world, x + x1 + 1, y, z + z1, metadata) && isSameSapling(world, x + x1, y, z + z1 + 1, metadata) && isSameSapling(world, x + x1 + 1, y, z + z1 + 1, metadata))
                    {
                        if (metadata == BlockType.FIR.metadata())
                        {
                            tree = new WorldGenFirTreeHuge(true);
                            offset = 1;
                        }
                        else
                        {
                            tree = GeneralSettings.useLegacyRedwoods ? new WorldGenRedwood(true) : new WorldGenNewRedwood(true);
                            offset = 0;
                        }
                        isHuge = true;
                        break;
                    }
                if (tree != null)
                    break;
            }
            if (tree == null && metadata == BlockType.FIR.metadata())
            {
                // Single fir sapling generates 1x1 tree
                z1 = 0;
                x1 = 0;
                tree = new WorldGenFirTree(true);
            }
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
                world.setBlock(x, y, z, Blocks.air);
            
            if (!tree.generate(world, rand, x + x1 + offset, y, z + z1 + offset))
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
                    world.setBlock(x, y - 1, z, Blocks.sand);
        }
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
        if (event.entityItem.getEntityItem().getItem().equals(Item.getItemFromBlock(sapling)))
        {
            int metadata = unmarkedMetadata(event.entityItem.getEntityItem().getItemDamage());
            int posX = (int) Math.floor(event.entityItem.lastTickPosX);
            int posY = (int) Math.floor(event.entityItem.lastTickPosY);
            int posZ = (int) Math.floor(event.entityItem.lastTickPosZ);
            double chance = event.entityItem.worldObj.rand.nextDouble() * 100;
            
            //event.entityItem
            if (canPlaceBlockOn(event.entityItem.worldObj.getBlock(posX, posY - 1, posZ)))
            {
                double ratio = ((!GeneralSettings.bigTreeSaplingDropModifier) ? 1.0D : 4.0D);
                
                // Determine if the sapling should despawn
                if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.ACACIA.metadata() && chance <= SaplingSettings.ACACIA.chance())
                {
                    event.entityItem.worldObj.setBlock(posX, posY, posZ, sapling, metadata, 2);
                }
                else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.UMBER.metadata() && chance <= SaplingSettings.UMBER.chance())
                {
                    event.entityItem.worldObj.setBlock(posX, posY, posZ, sapling, metadata, 2);
                }
                else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.CYPRESS.metadata() && chance <= SaplingSettings.CYPRESS.chance())
                {
                    event.entityItem.worldObj.setBlock(posX, posY, posZ, sapling, metadata, 2);
                }
                else if (metadata == BlockType.FIR.metadata() && chance <= SaplingSettings.FIR.chance() * ratio)
                {
                    plant2x2Sapling(posX, posY, posZ, event.entityItem.worldObj, event.entityItem.getEntityItem());
                }
                else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.GOLDENROD.metadata() && chance <= SaplingSettings.GOLDENROD.chance())
                {
                    event.entityItem.worldObj.setBlock(posX, posY, posZ, sapling, metadata, 2);
                }
                else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.VERMILLION.metadata() && chance <= SaplingSettings.VERMILLION.chance())
                {
                    event.entityItem.worldObj.setBlock(posX, posY, posZ, sapling, metadata, 2);
                }
                else if (metadata == BlockType.REDWOOD.metadata() && chance <= SaplingSettings.REDWOOD.chance() * ratio)
                {
                    plant2x2Sapling(posX, posY, posZ, event.entityItem.worldObj, event.entityItem.getEntityItem());
                }
                else if (event.entityItem.worldObj.isAirBlock(posX, posY, posZ) && metadata == BlockType.CITRINE.metadata() && chance <= SaplingSettings.CITRINE.chance())
                {
                    event.entityItem.worldObj.setBlock(posX, posY, posZ, sapling, metadata, 2);
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
            if (world.isAirBlock(x, y, z) && canPlaceBlockOn(world.getBlock(x, y - 1, z)) && canPlaceBlockOn(world.getBlock(x + 1, y - 1, z)) && canPlaceBlockOn(world.getBlock(x + 1, y - 1, z + 1)) && canPlaceBlockOn(world.getBlock(x, y - 1, z + 1)))
            {
                world.setBlock(x, y, z, this.sapling, metadata, 2);
                world.setBlock(x + 1, y, z, this.sapling, metadata, 2);
                world.setBlock(x + 1, y, z + 1, this.sapling, metadata, 2);
                world.setBlock(x, y, z + 1, this.sapling, metadata, 2);
                return;
            }
        }
        
        // check station 2
        if ((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x, y, z + 1) || isSameSaplingBlock(x, y, z + 1, world, sapling)) && (world.isAirBlock(x - 1, y, z + 1) || isSameSaplingBlock(x - 1, y, z + 1, world, sapling)) && (world.isAirBlock(x - 1, y, z) || isSameSaplingBlock(x - 1, y, z, world, sapling)))
        {
            if (world.isAirBlock(x, y, z) && canPlaceBlockOn(world.getBlock(x, y - 1, z)) && canPlaceBlockOn(world.getBlock(x, y - 1, z + 1)) && canPlaceBlockOn(world.getBlock(x - 1, y - 1, z + 1)) && canPlaceBlockOn(world.getBlock(x - 1, y - 1, z)))
            {
                world.setBlock(x, y, z, this.sapling, metadata, 2);
                world.setBlock(x, y, z + 1, this.sapling, metadata, 2);
                world.setBlock(x - 1, y, z + 1, this.sapling, metadata, 2);
                world.setBlock(x - 1, y, z, this.sapling, metadata, 2);
                return;
            }
        }
        
        // Check station 3
        if ((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x - 1, y, z) || isSameSaplingBlock(x - 1, y, z, world, sapling)) && (world.isAirBlock(x - 1, y, z - 1) || isSameSaplingBlock(x - 1, y, z - 1, world, sapling)) && (world.isAirBlock(x, y, z - 1) || isSameSaplingBlock(x, y, z - 1, world, sapling)))
        {
            if (world.isAirBlock(x, y, z) && canPlaceBlockOn(world.getBlock(x, y - 1, z)) && canPlaceBlockOn(world.getBlock(x - 1, y - 1, z)) && canPlaceBlockOn(world.getBlock(x - 1, y - 1, z - 1)) && canPlaceBlockOn(world.getBlock(x, y - 1, z - 1)))
            {
                world.setBlock(x, y, z, this.sapling, metadata, 2);
                world.setBlock(x - 1, y, z, this.sapling, metadata, 2);
                world.setBlock(x - 1, y, z - 1, this.sapling, metadata, 2);
                world.setBlock(x, y, z - 1, this.sapling, metadata, 2);
                return;
            }
        }
        
        // Check station 4
        if ((world.isAirBlock(x, y, z) || isSameSaplingBlock(x, y, z, world, sapling)) && (world.isAirBlock(x, y, z - 1) || isSameSaplingBlock(x, y, z - 1, world, sapling)) && (world.isAirBlock(x + 1, y, z - 1) || isSameSaplingBlock(x + 1, y, z - 1, world, sapling)) && (world.isAirBlock(x + 1, y, z) || isSameSaplingBlock(x + 1, y, z, world, sapling)))
        {
            if (world.isAirBlock(x, y, z) && canPlaceBlockOn(world.getBlock(x, y - 1, z)) && canPlaceBlockOn(world.getBlock(x, y - 1, z - 1)) && canPlaceBlockOn(world.getBlock(x + 1, y - 1, z - 1)) && canPlaceBlockOn(world.getBlock(x + 1, y - 1, z)))
            {
                world.setBlock(x, y, z, this.sapling, metadata, 2);
                world.setBlock(x, y, z - 1, this.sapling, metadata, 2);
                world.setBlock(x + 1, y, z - 1, this.sapling, metadata, 2);
                world.setBlock(x + 1, y, z, this.sapling, metadata, 2);
                return;
            }
        }
    }
    
    private boolean isSameSaplingBlock(int x, int y, int z, World world, ItemStack sapling)
    {
        Block block = world.getBlock(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);
        return !block.isAir(world, x, y, z) && block != null && sapling.getItem().equals(Item.getItemFromBlock(block)) && sapling.getItemDamage() == metadata;
    }
    
    @SubscribeEvent
    public void itemEntering(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityItem && !event.world.isRemote)
        {
            if (((EntityItem) event.entity).getEntityItem().getItem().equals(Item.getItemFromBlock(sapling)))
            {
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
