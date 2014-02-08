package extrabiomes.handlers;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import extrabiomes.blocks.BlockCropBasic;
import extrabiomes.blocks.BlockCropRegrow;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ItemSettings;

public class CropHandler {
    public static void createCrops() {
    	createBasicCrops();
    	createRegrowCrops();
    }
    
    private static void createBasicCrops() {
    	for( BlockCropBasic.CropType type : BlockCropBasic.CropType.values() ) {
    	}
    }
    
    private static void createRegrowCrops() {
    	for( BlockCropRegrow.CropType type : BlockCropRegrow.CropType.values() ) {
    		final BlockSettings plant_settings;
    		final ItemSettings seed_settings;
    		final ItemSettings crop_settings;
    		
    		final Element plant_element;
    		final Element seed_element;
    		final Element crop_element;
    		try {
    			final String name = type.name();
    			
    			plant_settings = BlockSettings.valueOf(name);
    			seed_settings = ItemSettings.valueOf("SEED_"+name);
    			crop_settings = ItemSettings.valueOf("CROP_"+name);
    			
    			plant_element = Element.valueOf("PLANT_"+name);
    			seed_element = Element.valueOf("SEED_"+name);
    			crop_element = Element.valueOf("CROP_"+name);
    		} catch( Exception e ) {
				LogHelper.warning("Missing settings or elements for plant " + type);
    			continue;
    		}
    		
    		final BlockCropRegrow block = new BlockCropRegrow(plant_settings.getID(), type);
    		final Item seed = new Item(seed_settings.getID());
    		final Item crop = new Item(crop_settings.getID());
    		
    		plant_element.set(new ItemStack(block));
    		seed_element.set(new ItemStack(seed));
    		crop_element.set(new ItemStack(crop));
    	}
    }

}
