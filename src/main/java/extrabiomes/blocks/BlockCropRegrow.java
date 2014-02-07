package extrabiomes.blocks;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import extrabiomes.Extrabiomes;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockCropRegrow extends BlockCropBasic {

	public enum CropType implements ICropType {
		STRAWBERRY;
		
		private ArrayList<Icon> icons;
		
		@Override
		public Icon getStageIcon(int stage) {
			// TODO Auto-generated method stub
			return null;
		}
	
		public void registerAllIcons(IconRegister iconRegister) {
			icons = Lists.newArrayListWithExpectedSize(MAX_GROWTH_STAGE+1);
			final String name = this.name().toLowerCase();
			Icon lastIcon = null;
			for( int k = 0; k < MAX_GROWTH_STAGE; ++k ) {
				final String texture = "plant_" + name + k;
				Icon icon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + texture);
				if( icon != null ) {
					lastIcon = icon;
				} else {
					icon = lastIcon;
				}
				icons.set(k, icon);
			}
		}
		
	}
	
	public BlockCropRegrow(int blockID, CropType type) {
		super(blockID, CropType.STRAWBERRY);
	}
}
