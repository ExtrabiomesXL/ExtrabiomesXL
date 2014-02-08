package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.util.Icon;

public class BlockCropRegrow extends BlockCropBasic {

	public enum CropType implements ICropType {
		STRAWBERRY;
		
		private ArrayList<Icon> icons;
		
		@Override
		public Icon getStageIcon(int stage) {
			return icons.get(stage);
		}

		@Override
		public void setStageIcons(ArrayList<Icon> icons) {
			this.icons = icons;
		}
	}
	
	public BlockCropRegrow(int blockID, CropType type) {
		super(blockID, CropType.STRAWBERRY);
	}
}
