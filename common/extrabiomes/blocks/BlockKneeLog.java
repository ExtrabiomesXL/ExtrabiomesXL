package extrabiomes.blocks;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
import extrabiomes.blocks.BlockCustomLog.BlockType;
import extrabiomes.blocks.BlockQuarterLog.BarkOn;
import extrabiomes.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;


public class BlockKneeLog extends BlockLog {
	
	public enum BlockType {
        CYPRESS(0);

        private final int metadata;

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        public int metadata() {
            return metadata;
        }
    }
	
	private Icon[] textures  = {null, null, null, null, null, null, null, null, null};
    private static int renderId = 32;
    private String treeType = "knee";
	
	public BlockKneeLog(int id, String treeType) {
        super(id);
        
        this.treeType = treeType;
    }
	
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){

		textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee1");
    	textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee2");
    	textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee3");
    	textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee4");

    	textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "side1");
    	textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "side2");
    	textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "top");
    	textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "top");
    	
    	textures[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "todo");
    	
    }
	
	@Override
    public Icon getIcon(int side, int metadata) {
        final int orientation = metadata;
        
        switch(side){
        	case 0:
        		return textures[bottopIcon(orientation)];
	        case 1:
	        	return textures[topIcon(orientation)];
	        case 2:
	        	return textures[southIcon(orientation)];
	        case 3:
	        	return textures[northIcon(orientation)];
	        case 4:
	        	return textures[eastIcon(orientation)];
	        case 5:
	        	return textures[westIcon(orientation)];
        }
        
        return textures[4];
        
    }
	
	private int topIcon(int orientation) {
		switch(orientation){
	    	case 0:
	    		return 0;
	    	case 1:
	    		return 1;
	    	case 2:
	    		return 2;
	    	case 3:
	    		return 3;
	    	case 4:
	    		return 4;
	    	case 5:
	    		return 5;
	    	case 6:
	    		return 7;
	    	case 7:
	    		return 6;
	    	case 8:
	    		return 4;
	    	case 9:
	    		return 5;
	    	case 10:
	    		return 6;
	    	case 11:
	    		return 7;
	    	default:
	    		return 8;
	    }
	}

	private int bottopIcon(int orientation) {
		switch(orientation){
	    	case 0:
	    		return 0;
	    	case 1:
	    		return 1;
	    	case 2:
	    		return 2;
	    	case 3:
	    		return 3;
	    	case 4:
	    		return 6;
	    	case 5:
	    		return 7;
	    	case 6:
	    		return 4;
	    	case 7:
	    		return 5;
	    	case 8:
	    		return 6;
	    	case 9:
	    		return 7;
	    	case 10:
	    		return 5;
	    	case 11:
	    		return 4;
	    		
	    	default:
	    		return 8;
	    }
	}
	
	private int eastIcon(int orientation) {
		switch(orientation){
			case 0:
				return 5;
			case 1:
				return 7;
			case 2:
				return 6;
			case 3:
				return 4;
			case 4:
				return 7;
			case 5:
				return 4;
			case 6:
				return 5;
			case 7:
				return 6;
	    	case 8:
	    		return 0;
	    	case 9:
	    		return 1;
	    	case 10:
	    		return 2;
	    	case 11:
	    		return 3;
	    	default:
	    		return 8;
	    }
	}
	
	private int westIcon(int orientation) {
		switch(orientation){
			case 0:
				return 6;
			case 1:
				return 4;
			case 2:
				return 5;
			case 3:
				return 7;
			case 4:
				return 4;
			case 5:
				return 6;
			case 6:
				return 7;
			case 7:
				return 5;
	    	case 8:
	    		return 1;
	    	case 9:
	    		return 0;
	    	case 10:
	    		return 3;
	    	case 11:
	    		return 2;
	    	default:
	    		return 8;
	    }
	}
	
	private int southIcon(int orientation) {
		switch(orientation){
			case 0:
				return 4;
			case 1:
				return 5;
			case 2:
				return 7;
			case 3:
				return 6;
	    	case 4:
	    		return 0;
	    	case 5:
	    		return 1;
	    	case 6:
	    		return 2;
	    	case 7:
	    		return 3;
			case 8:
				return 4;
			case 9:
				return 7;
			case 10:
				return 6;
			case 11:
				return 5;
	    	default:
	    		return 8;
	    }
	}
	
	private int northIcon(int orientation) {
		switch(orientation){
			case 0:
				return 7;
			case 1:
				return 6;
			case 2:
				return 4;
			case 3:
				return 5;
	    	case 4:
	    		return 1;
	    	case 5:
	    		return 0;
	    	case 6:
	    		return 3;
	    	case 7:
	    		return 2;
			case 8:
				return 6;
			case 9:
				return 4;
			case 10:
				return 5;
			case 11:
				return 7;
	    	default:
	    		return 8;
	    }
	}

    public static void setRenderId(int renderId) {
        BlockKneeLog.renderId = renderId;
    }

    @Override
    public int getRenderType() {
        return renderId;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs par2CreativeTabs, List list) {
        for (final BlockType type : BlockType.values()) {
            list.add(new ItemStack(blockID, 1, type.metadata()));
        }
    }

    @Override
    public int idDropped(int metadata, Random rand, int unused) {
        return blockID;
    }
    
    @ForgeSubscribe
    public void onUseLogTurnerEvent(UseLogTurnerEvent event) {
        int id = event.world.getBlockId(event.x, event.y, event.z);

        if (id == blockID) {
            final Block wood = Block.wood;
            event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, wood.stepSound.getStepSound(), (wood.stepSound.getVolume() + 1.0F) / 2.0F, wood.stepSound.getPitch() * 1.55F);

            if (!event.world.isRemote) {

                int metadata = event.world.getBlockMetadata(event.x, event.y, event.z);
                LogHelper.info("Orientation: %d", BlockPistonBase.determineOrientation(event.world, event.x, event.y, event.z, event.entityLiving));
                
                // Increment the orentation
                if(BlockPistonBase.determineOrientation(event.world, event.x, event.y, event.z, event.entityLiving) % 2 == 0) {
                	LogHelper.info("Go Plus.");
                	metadata = (++metadata > 11) ? 0: metadata;
                } else {
                	LogHelper.info("Go Minus.");
                	metadata = (--metadata < 0) ? 11: metadata;
                }

                event.world.setBlock(event.x, event.y, event.z, id, metadata, 3);

                //unturned = false;
            }
            event.setHandled();
            
            // Patch for skipping over block ids that are out of order
            event.setCanceled(true);
        }
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        
                
        world.setBlock(x, y, z, blockID, 0, 3);
	}
}
