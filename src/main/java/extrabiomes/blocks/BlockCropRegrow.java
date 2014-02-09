package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class BlockCropRegrow extends BlockCropBasic {

	public enum CropType implements ICropType {
		STRAWBERRY(RENDER_TYPE_FLOWER);
		
		private ArrayList<Icon> icons;		
		private Item seed;
		private Item crop;
		private final int		renderType;

		private CropType(Integer renderType) {
			this.renderType = (renderType == null ? DEFAULT_RENDER_TYPE : renderType);
		}
		
		@Override
		public Icon getStageIcon(int stage) {
			return icons.get(stage);
		}
		@Override
		public void setStageIcons(ArrayList<Icon> icons) {
			this.icons = icons;
		}
		
	    @Override
		public Item getSeedItem() {
	    	return seed;
	    }
	    @Override
		public Item getCropItem() {
	    	return crop;
	    }

		@Override
		public void setSeedItem(Item seed) {
			this.seed = seed;
		}
		@Override
		public void setCropItem(Item crop) {
			this.crop = crop;
		}

		@Override
		public int getRenderType() {
			return renderType;
		}
	}
	
	public BlockCropRegrow(int blockID, CropType type) {
		super(blockID, type);
	}
}
