package extrabiomes.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.blocks.BlockNewSapling.BlockType;
import extrabiomes.subblocks.SubBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockWaterPlant extends Block {
	
	protected SubBlock[] subBlocks;
	protected String blockName;
	
	//private int renderType;
	
	public BlockWaterPlant(int blockID, String name) {
		super(blockID, Material.water);

        this.setTickRandomly(true);
        this.disableStats();
		
		// Store the block name
		blockName = name;
		
		// Create the list of subblocks
		subBlocks = new SubBlock[16];
	}
	
	public BlockWaterPlant registerSubBlock(SubBlock childBlock, int metaData) throws Exception {
		// Make sure that the subblock isn't already used
		if(subBlocks[metaData] != null) throw new Exception("Subblock with metaData: " + metaData + ", already exists for " + blockName + ".");
		
		// Tell the child block about itself
		childBlock.setBlockIds(blockID, metaData);
		
		// Store the Subblock
		subBlocks[metaData] = childBlock;
		
		return this;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
		for(int i = 0; i < subBlocks.length; i++) {
			if(subBlocks[i] != null) {
				subBlocks[i].registerIcons(iconRegister);
			}
		}
    }
    
    @Override
    public Icon getIcon(int side, int metaData) {
    	if(subBlocks[metaData] != null) {
    		return subBlocks[metaData].getIcon(side, metaData);
    	}
    	
        return null;
    }
    
    public String getLocalizedName(int metaData) {
    	if(subBlocks[metaData] == null) {
    		return "extrabiomes.invaliditem";
    	} else {
    		return this.getUnlocalizedName() + "." + subBlocks[metaData].getUnlocalizedName();
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
    public void getSubBlocks(int id, CreativeTabs tab, List itemList){
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
    public void onNeighborBlockChange(World world, int x, int y, int z, int idNeighbor) {
    	final int metaData = world.getBlockMetadata(x, y, z);
    	
    	if(subBlocks[metaData] != null) {
    		subBlocks[metaData].onNeighborBlockChange(world, x, y, z, idNeighbor, this);
    	}
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int silkTouch) {
    	world.setBlock(x, y, z, Block.waterStill.blockID);
    }
    
    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
    	return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int unused, ItemStack item) {
    	if(item.itemID == blockID) {
    		if(subBlocks[item.getItemDamage()] == null) {
        		return false;
        	} else {
        		return subBlocks[item.getItemDamage()].canPlaceBlockAt(world, x, y, z);
        	}
    	} else {
    		return this.canPlaceBlockOnSide(world, x, y, z, unused);
    	}
    }
    
    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z) {
        return false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }
}
