package extrabiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockCropBasic extends BlockFlower {
	
	public static final int MAX_GROWTH_STAGE=7;
	
	public enum CropType implements ICropType {
		;

		@Override
		public Icon getStageIcon(int stage) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public final ICropType cropType;
	protected BlockCropBasic(int blockID, ICropType type) {
		super(blockID);
		cropType = type;
		// TODO: set creative tab
		this.setStepSound(Block.soundGrassFootstep);
		
        final float offset = 0.2F;
        setBlockBounds(0.5F - offset, 0.0F, 0.5F - offset, 0.5F + offset, offset * 3.0F, 0.5F + offset);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	//cropType.registerAllIcons(iconRegister);
    }


	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}
	
}
