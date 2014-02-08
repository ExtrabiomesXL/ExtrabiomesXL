package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class BlockCropRegrow extends BlockCropBasic {

	public enum CropType implements ICropType {
		STRAWBERRY;
		
		private ArrayList<Icon> icons;		
		private Item seed;
		private Item crop;
		
		public Icon getStageIcon(int stage) {
			return icons.get(stage);
		}
		public void setStageIcons(ArrayList<Icon> icons) {
			this.icons = icons;
		}
		
	    public Item getSeedItem() {
	    	return seed;
	    }
	    public Item getCropItem() {
	    	return crop;
	    }
	}
	
	public BlockCropRegrow(int blockID, CropType type) {
		super(blockID, type);
	}
}
