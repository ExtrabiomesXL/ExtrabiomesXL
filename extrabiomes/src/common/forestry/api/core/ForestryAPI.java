package forestry.api.core;

import java.util.ArrayList;

import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.NetworkMod;

/**
 * Simple API class to add your own resources and recipes to Forestry If you need something added/changed/fixed, drop me a PM on the Minecraft forums and I'll
 * see what can be done. You are free to include this API (forestry.api.*) in your own mod and redistribute and/or modify it at will. Modifying will probably
 * cause conflicts when Forestry is installed, so best to drop me a line if you need additions or bugfixes.
 * 
 * @author SirSengir
 */
public class ForestryAPI {

	public static NetworkMod instance;
	
	// / WRENCHES
	/**
	 * Register an item as wrench. Items with the passed itemid can be used to rotate {@BlockEngine}s.
	 * 
	 * @param item
	 *            ItemStack representing the wrench. Meta sensitive.
	 * @return
	 */
	public static boolean registerWrench(ItemStack item) {
		ItemStack put = new ItemStack(item.itemID, 0, item.getItemDamage());

		for (ItemStack wrench : wrenchs)
			if (wrench.isItemEqual(item))
				return false;

		wrenchs.add(put);
		return true;
	}

	public static ArrayList<ItemStack> loggerWindfall = new ArrayList<ItemStack>();

	private static ArrayList<ItemStack> wrenchs = new ArrayList<ItemStack>();

	public static boolean isWrench(ItemStack item) {
		for (ItemStack wrench : wrenchs)
			if (wrench.isItemEqual(item))
				return true;
		return false;
	}
}
