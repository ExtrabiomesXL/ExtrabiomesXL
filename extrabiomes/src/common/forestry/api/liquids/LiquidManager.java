package forestry.api.liquids;

import java.util.ArrayList;

import net.minecraft.src.ItemStack;

public class LiquidManager {

	public static ArrayList<LiquidContainer> liquidContainers = new ArrayList<LiquidContainer>();

	/**
	 * Registers a {@link LiquidContainer} (bucket or bucket replacement) for use as a generic liquid container in Forestry's machines.
	 * 
	 * @param container
	 * @return
	 */
	public static boolean registerLiquidContainer(LiquidContainer container) {
		liquidContainers.add(container);
		return true;
	}

	public static boolean isEmptyContainer(ItemStack empty) {
		for (LiquidContainer cont : liquidContainers)
			if (cont.empty.isItemEqual(empty))
				return true;

		return false;
	}

	public static LiquidContainer getEmptyContainer(ItemStack empty, LiquidStack liquid) {
		for (LiquidContainer cont : liquidContainers)
			if (cont.liquid.isLiquidEqual(liquid) && cont.empty.isItemEqual(empty))
				return cont;

		return null;
	}

	public static LiquidContainer getLiquidContainer(ItemStack container) {
		for (LiquidContainer cont : liquidContainers)
			if (cont.filled.isItemEqual(container))
				return cont;
		return null;
	}
}
