package extrabiomes.blocks;

import java.util.List;
import java.util.Locale;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.ToolTipStringFormatter;
import extrabiomes.lib.BlockSettings;
import extrabiomes.subblocks.SubBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWaterPlant extends Block {
	
	private BlockSettings settings;
	protected SubBlock[] subBlocks;
	protected String blockName;
	
	//private int renderType;
	
	public BlockWaterPlant(BlockSettings settings, String name) {
		super(Material.water);

        this.setTickRandomly(true);
        this.disableStats();
        
        this.settings = settings;
		
		// Store the block name
		blockName = name;
		
		// Create the list of subblocks
		subBlocks = new SubBlock[16];
	}
	
	public BlockWaterPlant registerSubBlock(SubBlock childBlock, int metaData) throws Exception {
		// Make sure that the subblock isn't already used
		if(subBlocks[metaData] != null) throw new Exception("Subblock with metaData: " + metaData + ", already exists for " + blockName + ".");
		
		// Tell the child block about itself
		childBlock.setBlock(this, metaData);
		
		// Store the Subblock
		subBlocks[metaData] = childBlock;
		
		return this;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
		for(int i = 0; i < subBlocks.length; i++) {
			if(subBlocks[i] != null) {
				subBlocks[i].registerIcons(iconRegister);
			}
		}
    }
    
    @Override
    public IIcon getIcon(int side, int metaData) {
    	if(subBlocks[metaData] != null) {
    		return subBlocks[metaData].getIcon(side, metaData);
    	}
    	
        return null;
    }
    
    public String getLocalizedName(int metadata) {
    	if( metadata < 0 || metadata >= subBlocks.length )
    		metadata = subBlocks.length-1;
    	if(subBlocks[metadata] == null) {
    		return "extrabiomes.invaliditem";
    	} else {
    		return this.getUnlocalizedName() + "." + subBlocks[metadata].getUnlocalizedName();
    	}
    }
    
    @Override
    public int getRenderType() {
        return 6;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List itemList){
        for(int i = 0; i < subBlocks.length; i++) {
        	if(subBlocks[i] != null) {
        		itemList.add(new ItemStack(this, 1, i));
        	}
        }
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
    	final int metaData = world.getBlockMetadata(x, y, z);
    	
    	if(subBlocks[metaData] == null) {
    		return false;
    	} else {
    		return subBlocks[metaData].canPlaceBlockAt(world, x, y, z);
    	}
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
    	final int metaData = world.getBlockMetadata(x, y, z);
    	
    	if(subBlocks[metaData] == null) {
    		return false;
    	} else {
    		return subBlocks[metaData].canBlockStay(world, x, y, z);
    	}
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
    	final int metaData = world.getBlockMetadata(x, y, z);
    	
    	if(subBlocks[metaData] != null) {
    		subBlocks[metaData].onNeighborBlockChange(world, x, y, z, neighbor, this);
    	}
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int silkTouch) {
    	world.setBlock(x, y, z, Blocks.water);
    }
    
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
    	return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int unused, ItemStack item) {
    	if(item.getItem() == settings.getItem()) {
    		if(subBlocks[item.getItemDamage()] == null) {
        		return false;
        	} else {
        		return subBlocks[item.getItemDamage()].canPlaceBlockAt(world, x, y, z);
        	}
    	} else {
    		return this.canPlaceBlockOnSide(world, x, y, z, unused);
    	}
    }
    
    public boolean isBlockReplaceable(World world, int x, int y, int z) {
        return false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

	public void addInformation(int metaData, List listOfLines) {
		if(metaData < subBlocks.length && subBlocks[metaData] != null) {
			String line = I18n.format(this.getUnlocalizedName() + "." + subBlocks[metaData].getUnlocalizedName() + ".description");
    		
    		if(!line.equals(this.getUnlocalizedName() + "." + subBlocks[metaData].getUnlocalizedName() + ".description")) {
    			if(listOfLines.size() > 0 && ((String)listOfLines.get(0)).length() > 20) {
    				ToolTipStringFormatter.Format(line, listOfLines, ((String)listOfLines.get(0)).length() + 5);
    			} else {
    				ToolTipStringFormatter.Format(line, listOfLines);
    			}
    		}
    	}
	}
}
