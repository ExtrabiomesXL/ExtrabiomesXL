/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugins;

import java.lang.reflect.Field;
import java.util.Collection;

import net.minecraft.src.ModLoader;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.IPlugin;
import extrabiomes.plugins.pamsmods.PamGen;

public enum PluginHarvestcraft implements IPlugin {
	INSTANCE;

	@Override
	public String getName() {
		return "Pam's HarvestCraft";
	}

	@Override
	public void inject() {
		try {
			Class aClass = Class.forName("mod_Pamfood");

			Field field = aClass.getField("treeRarity");
			PamGen.TREES.setRarity(field.getInt(null));

			field = aClass.getField("bushRarity");
			PamGen.BUSHES.setRarity(field.getInt(null));

			field = aClass.getField("treewarmRarity");
			PamGen.WARM_TREES.setRarity(field.getInt(null));

			field = aClass.getField("bushwarmRarity");
			PamGen.WARM_BUSHES.setRarity(field.getInt(null));

			aClass = Class.forName("WorldGenPamTrees");
			final Class partypes[] = { Integer.TYPE };
			PamGen.TREES
					.setConstructor(aClass.getConstructor(partypes));

			aClass = Class.forName("WorldGenPamBushes");
			PamGen.BUSHES.setConstructor(aClass
					.getConstructor(partypes));

			aClass = Class.forName("WorldGenPamTreesWarm");
			PamGen.WARM_TREES.setConstructor(aClass
					.getConstructor(partypes));

			aClass = Class.forName("WorldGenPamBushesWarm");
			PamGen.WARM_BUSHES.setConstructor(aClass
					.getConstructor(partypes));
		} catch (final Exception e) {
			ModLoader.getLogger().fine(
					"Could not find HarvestCraft fields.");
			return;
		}
		setBiomeDecorations();
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_Pamfood");
	}

	private void setBiomeDecorations() {
		Collection<IBiomeDecoration> biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.autumnwoods);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.birchforest);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.extremejungle);
		biomeDecorations.add(PamGen.WARM_TREES);
		biomeDecorations.add(PamGen.WARM_BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.forestedhills);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.forestedisland);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.greenhills);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.greenswamp);
		biomeDecorations.add(PamGen.WARM_TREES);
		biomeDecorations.add(PamGen.WARM_BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.meadow);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.minijungle);
		biomeDecorations.add(PamGen.WARM_TREES);
		biomeDecorations.add(PamGen.WARM_BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.mountaindesert);
		biomeDecorations.add(PamGen.WARM_TREES);
		biomeDecorations.add(PamGen.WARM_BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.mountainridge);
		biomeDecorations.add(PamGen.WARM_TREES);
		biomeDecorations.add(PamGen.WARM_BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.pineforest);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.rainforest);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.redwoodforest);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.redwoodlush);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.savanna);
		biomeDecorations.add(PamGen.WARM_TREES);
		biomeDecorations.add(PamGen.WARM_BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.shrubland);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.temperaterainforest);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);

		biomeDecorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.woodlands);
		biomeDecorations.add(PamGen.TREES);
		biomeDecorations.add(PamGen.BUSHES);
	}

}
