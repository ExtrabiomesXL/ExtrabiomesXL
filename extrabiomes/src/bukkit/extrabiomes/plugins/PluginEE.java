
package extrabiomes.plugins;

import java.lang.reflect.Method;

import net.minecraft.server.ModLoader;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.IPlugin;

public enum PluginEE implements IPlugin {
	INSTANCE;

	public static Method	setEMC;
	public static Method	getEMC;

	public static int getEMC(int i) {
		return getEMC(i, 0);
	}

	public static int getEMC(int i, int j) {
		final ItemStack itemstack = new ItemStack(i, 1, j);
		return getEMC(itemstack);
	}

	public static int getEMC(ItemStack itemstack) {
		final Object aobj[] = new Object[1];
		aobj[0] = itemstack;

		try {
			final Object obj = getEMC.invoke(null, aobj);
			return ((Integer) obj).intValue();
		} catch (final Exception exception) {
			ModLoader.getLogger().fine("Could not get EMC.");
		}

		return 0;
	}

	public static void setEMC(int i, int j) {
		setEMC(i, 0, j);
	}

	public static void setEMC(int i, int j, int k) {
		final Object aobj[] = new Object[3];
		aobj[0] = new Integer(i);
		aobj[1] = new Integer(j);
		aobj[2] = new Integer(k);

		try {
			setEMC.invoke(null, aobj);
		} catch (final Exception exception) {
			ModLoader.getLogger().fine("Could not set EMC.");
		}
	}

	public static void setEMC(ItemStack itemstack, int i) {
		setEMC(itemstack.id, itemstack.getData(), i);
	}

	private static void setEMCValues() {
		final int i = getEMC(Block.LEAVES.id);
		final int j = getEMC(Block.SUGAR_CANE_BLOCK.id);
		final int k = getEMC(Block.SAND.id);
		final int l = getEMC(Block.RED_ROSE.id);
		final int i1 = getEMC(Block.LONG_GRASS.id);
		final int j1 = getEMC(Block.SAND.id);
		final int k1 = 61;
		final int l1 = getEMC(Block.SAPLING.id);

		if (ExtrabiomesBlock.autumnLeaves != null && i > 0) {
			setEMC(ExtrabiomesBlock.autumnLeaves.id, 0, i);
			setEMC(ExtrabiomesBlock.autumnLeaves.id, 1, i);
			setEMC(ExtrabiomesBlock.autumnLeaves.id, 2, i);
			setEMC(ExtrabiomesBlock.autumnLeaves.id, 3, i);
		}

		if (ExtrabiomesBlock.greenLeaves != null && i > 0) {
			setEMC(ExtrabiomesBlock.greenLeaves.id, 0, i);
			setEMC(ExtrabiomesBlock.greenLeaves.id, 1, i);
			setEMC(ExtrabiomesBlock.greenLeaves.id, 2, i);
		}

		if (ExtrabiomesBlock.catTail != null && j > 0)
			setEMC(ExtrabiomesBlock.catTail.id, j);

		if (ExtrabiomesBlock.crackedSand != null && k > 0)
			setEMC(ExtrabiomesBlock.crackedSand.id, k);

		if (ExtrabiomesBlock.flower != null && l > 0) {
			setEMC(ExtrabiomesBlock.flower.id, 0, i);
			setEMC(ExtrabiomesBlock.flower.id, 1, i);
			setEMC(ExtrabiomesBlock.flower.id, 2, i);
			setEMC(ExtrabiomesBlock.flower.id, 3, i);
			setEMC(ExtrabiomesBlock.flower.id, 5, i);
			setEMC(ExtrabiomesBlock.flower.id, 4, i);
			setEMC(ExtrabiomesBlock.flower.id, 6, i);
			setEMC(ExtrabiomesBlock.flower.id, 7, i);
		}

		if (ExtrabiomesBlock.grass != null && i1 > 0) {
			setEMC(ExtrabiomesBlock.grass.id, 0, i1);
			setEMC(ExtrabiomesBlock.grass.id, 2, i1);
			setEMC(ExtrabiomesBlock.grass.id, 3, i1);
			setEMC(ExtrabiomesBlock.grass.id, 4, i1);
			setEMC(ExtrabiomesBlock.grass.id, 1, i1);
		}

		if (ExtrabiomesBlock.quickSand != null && j1 > 0)
			setEMC(ExtrabiomesBlock.quickSand.id, j1);

		if (ExtrabiomesBlock.redRock != null && k1 > 0)
			setEMC(ExtrabiomesBlock.redRock.id, k1);

		if (ExtrabiomesBlock.sapling != null && l1 > 0) {
			setEMC(ExtrabiomesBlock.sapling.id, 0, i1);
			setEMC(ExtrabiomesBlock.sapling.id, 1, i1);
			setEMC(ExtrabiomesBlock.sapling.id, 2, i1);
			setEMC(ExtrabiomesBlock.sapling.id, 3, i1);
			setEMC(ExtrabiomesBlock.sapling.id, 4, i1);
			setEMC(ExtrabiomesBlock.sapling.id, 5, i1);
			setEMC(ExtrabiomesBlock.sapling.id, 6, i1);
		}
	}

	@Override
	public String getName() {
		return "EquivalentExchange";
	}

	@Override
	public void inject() {
		try {
			final Class class1 = Class.forName("EEProxy");
			final Class aclass[] = { Integer.TYPE, Integer.TYPE,
					Integer.TYPE };
			setEMC = class1.getMethod("setEMC", aclass);
			final Class aclass1[] = { net.minecraft.src.ItemStack.class };
			getEMC = class1.getMethod("getEMC", aclass1);
		} catch (final Exception exception) {
			ModLoader.getLogger().fine("Could not find EE proxy.");
			return;
		}

		setEMCValues();
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_EE");
	}
}
