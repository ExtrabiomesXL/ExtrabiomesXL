package extrabiomes.handlers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.blocks.BlockCropBasic;
import extrabiomes.blocks.BlockCropRegrow;
import extrabiomes.helpers.LogHelper;
import extrabiomes.items.ItemCustomCrop;
import extrabiomes.items.ItemCustomSeed;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ItemSettings;
import extrabiomes.lib.Reference;
import extrabiomes.module.summa.worldgen.FlowerGenerator;
import extrabiomes.proxy.CommonProxy;

public class CropHandler {
    public static void createCrops() {
		// create crops (products) first
		createCropItems();
		// then create the plant blocks that create those crops
    	createBasicCrops();
    	createRegrowCrops();
		// finally create the seeds that create the blocks
		createSeedItems();
		// register wild crops with worldgen
		registerWildCrops();
    }
    
	private static void registerWildCrops() {
		Element[] crops = { Element.PLANT_STRAWBERRY };

		final FlowerGenerator generator = FlowerGenerator.getInstance();
		for (Element crop : crops) {
			generator.registerCrop(crop);
		}
	}

	private static void createCropItems() {
		final ItemSettings settings = ItemSettings.CROP;
		ItemCustomCrop item = new ItemCustomCrop(settings.getID());
		Stuff.crop = Optional.of(item);
		GameRegistry.registerItem(item, "extrabiomes.crop", Reference.MOD_ID);

		for (ItemCustomCrop.CropType type : ItemCustomCrop.CropType.values()) {
			final Element element;
			try {
				element = Element.valueOf("CROP_" + type.name());
			} catch( Exception e ) {
				LogHelper.warning("Missing element for crop " + type);
    			continue;
			}

			final ItemStack crop_item = new ItemStack(item, 1, type.meta);
			element.set(crop_item);
			OreDictionary.registerOre(
					"crop" + StringUtils.capitalize(type.name().toLowerCase()),
					crop_item);
		}
	}

	private static void createSeedItems() {
		final ItemSettings settings = ItemSettings.SEED;
		ItemCustomSeed item = new ItemCustomSeed();
		Stuff.seed = Optional.of(item);
		GameRegistry.registerItem(item, "extrabiomes.seed", Reference.MOD_ID);

		for (ItemCustomSeed.SeedType type : ItemCustomSeed.SeedType.values()) {
			final Element seed_element;
			final Element plant_element;
			try {
				seed_element = Element.valueOf("SEED_" + type.name());
				plant_element = Element.valueOf("PLANT_" + type.name());
			} catch (Exception e) {
				LogHelper.warning("Missing element for seed " + type);
				continue;
			}

			final ItemStack seed_item = new ItemStack(item, 1, type.meta);
			seed_element.set(seed_item);
			// and associate with our target block
			if (plant_element != null && plant_element.isPresent()) {
				final BlockFlower block = (BlockFlower) Block.getBlockFromItem(plant_element.get().getItem());
				type.cropType = block;
				if(block instanceof BlockCropBasic) {
					( (BlockCropBasic) block ).setSeedItem(seed_item);
				} else {
					LogHelper.severe("Unable to set seed item for " + type);
				}
			} else {
				LogHelper.severe("Missing plant element for " + type);
			}
		}
	}

    private static void createBasicCrops() {
    	for( BlockCropBasic.CropType type : BlockCropBasic.CropType.values() ) {
    	}
    }
    
    private static void createRegrowCrops() {
		final CommonProxy proxy = Extrabiomes.proxy;

    	for( BlockCropRegrow.CropType type : BlockCropRegrow.CropType.values() ) {
			final String name = type.name();

    		final BlockSettings plant_settings;
    		final Element plant_element;
    		final Element crop_element;

    		try {
    			plant_settings = BlockSettings.valueOf(name);
    			
        		if(!plant_settings.getEnabled()) continue;
    			
				plant_element = Element.valueOf("PLANT_" + name);
				crop_element = Element.valueOf("CROP_" + name);
    		} catch( Exception e ) {
				LogHelper.warning("Missing settings or elements for plant " + type);
    			continue;
    		}
    		
    		final BlockCropRegrow block = new BlockCropRegrow(type);
			plant_element.set(new ItemStack(block));
			block.setCropItem(crop_element.get().getItem());
			block.setBlockName("extrabiomes.crop." + name.toLowerCase());
			proxy.registerEventHandler(new CropBonemealEventHandler(block));
			proxy.registerBlock(block, block.getUnlocalizedName());
    	}
    }

}
