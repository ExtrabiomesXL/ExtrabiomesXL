package extrabiomes.handlers;

import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

import extrabiomes.api.Stuff;
import extrabiomes.blocks.BlockCropBasic;
import extrabiomes.blocks.BlockCropRegrow;
import extrabiomes.helpers.LogHelper;
import extrabiomes.items.ItemCustomCrop;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ItemSettings;

public class CropHandler {
    public static void createCrops() {
		createCropItems();
		createSeedItems();
    	createBasicCrops();
    	createRegrowCrops();
    }
    
	private static void createCropItems() {
		final ItemSettings settings = ItemSettings.CROP;
		ItemCustomCrop item = new ItemCustomCrop(settings.getID());
		Stuff.crop = Optional.of(item);

		for (ItemCustomCrop.CropType type : ItemCustomCrop.CropType.values()) {
			final Element element;
			try {
				element = Element.valueOf("CROP_" + type.name());
			} catch( Exception e ) {
				LogHelper.warning("Missing element for crop " + type);
    			continue;
			}

			element.set(new ItemStack(item.itemID, 1, type.meta));
		}
	}

	private static void createSeedItems() {
		final ItemSettings settings = ItemSettings.SEED;
	}

    private static void createBasicCrops() {
    	for( BlockCropBasic.CropType type : BlockCropBasic.CropType.values() ) {
    	}
    }
    
    private static void createRegrowCrops() {
    	for( BlockCropRegrow.CropType type : BlockCropRegrow.CropType.values() ) {
    		final BlockSettings plant_settings;
    		final Element plant_element;
    		try {
    			final String name = type.name();
    			
    			plant_settings = BlockSettings.valueOf(name);
				plant_element = Element.valueOf("PLANT_" + name);
    		} catch( Exception e ) {
				LogHelper.warning("Missing settings or elements for plant " + type);
    			continue;
    		}
    		
    		final BlockCropRegrow block = new BlockCropRegrow(plant_settings.getID(), type);
			plant_element.set(new ItemStack(block));

			// TODO: attach seeds and crops to blocks
    	}
    }

}
