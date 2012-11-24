/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.ic2;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.PluginEvent;
import extrabiomes.api.Stuff;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.module.summa.block.BlockAutumnLeaves;
import extrabiomes.module.summa.block.BlockCustomSapling;

public class IC2Plugin {

	private static final String	MOD_NAME	= "IndustrialCraft 2";
	private Optional<IC2API>	api			= Optional.absent();

	private void addBiomeBonuses() {
		api.get().addBiomeBonus(BiomeManager.greenswamp, 2, 2);
		api.get().addBiomeBonus(BiomeManager.autumnwoods, 1, 1);
		api.get().addBiomeBonus(BiomeManager.birchforest, 1, 1);
		api.get().addBiomeBonus(BiomeManager.forestedhills, 1, 1);
		api.get().addBiomeBonus(BiomeManager.forestedisland, 1, 1);
		api.get().addBiomeBonus(BiomeManager.pineforest, 1, 1);
		api.get().addBiomeBonus(BiomeManager.rainforest, 1, 1);
		api.get().addBiomeBonus(BiomeManager.redwoodforest, 1, 1);
		api.get().addBiomeBonus(BiomeManager.redwoodlush, 1, 1);
		api.get().addBiomeBonus(BiomeManager.temperaterainforest, 1, 1);
		api.get().addBiomeBonus(BiomeManager.woodlands, 1, 1);
		api.get().addBiomeBonus(BiomeManager.extremejungle, 1, 2);
		api.get().addBiomeBonus(BiomeManager.minijungle, 1, 2);
		api.get().addBiomeBonus(BiomeManager.mountaindesert, 1, 0);
		api.get().addBiomeBonus(BiomeManager.mountainridge, 1, 0);
		api.get().addBiomeBonus(BiomeManager.wasteland, 1, 0);
	}

	private void addPlantBallRecipes() {
		ItemStack target;

		try {
			target = getPlantBall();
		} catch (final NullPointerException e) {
			return;
		}

		if (Stuff.leavesAutumn.isPresent())
			for (final BlockAutumnLeaves.BlockType type : BlockAutumnLeaves.BlockType
					.values())
				api.get().addCraftingRecipe(
						target,
						new Object[] {
								"PPP",
								"P P",
								"PPP",
								Character.valueOf('P'),
								new ItemStack(Stuff.leavesAutumn.get(),
										1, type.metadata()) });

		target = target.copy();
		target.stackSize = 2;

		if (Stuff.sapling.isPresent())
			for (final BlockCustomSapling.BlockType type : BlockCustomSapling.BlockType
					.values())
				api.get().addCraftingRecipe(
						target,
						new Object[] {
								"PPP",
								"P P",
								"PPP",
								Character.valueOf('P'),
								new ItemStack(Stuff.sapling.get(), 1,
										type.metadata()) });
	}

	private ItemStack getPlantBall() throws NullPointerException {
		final Optional<ItemStack> opt = api.get().getItem("plantBall");
		if (!opt.isPresent())
			throw new NullPointerException(
					"IC2 rwturned null for 'plantBall'.");
		return opt.get();
	}

	@ForgeSubscribe
	public void init(PluginEvent.Init event) {
		if (!api.isPresent()) return;

		addPlantBallRecipes();
		addBiomeBonuses();
	}

	@ForgeSubscribe
	public void postInit(PluginEvent.Post event) {
		api = Optional.absent();
	}

	@ForgeSubscribe
	public void preInit(PluginEvent.Pre event) {
		if (!Extrabiomes.proxy.isModLoaded("IC2")) return;
		LogHelper.fine(Extrabiomes.proxy
				.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT),
				MOD_NAME);
		try {
			api = Optional.of(new IC2API());
		} catch (final Exception ex) {
			ex.printStackTrace();
			LogHelper.fine(Extrabiomes.proxy
					.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR),
					MOD_NAME);
			api = Optional.absent();
		}
	}

}
