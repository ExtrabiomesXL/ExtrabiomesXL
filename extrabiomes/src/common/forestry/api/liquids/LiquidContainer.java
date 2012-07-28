package forestry.api.liquids;

import net.minecraft.src.ItemStack;

/**
 * Represents buckets/items that contain water.
 */
public class LiquidContainer {
	/**
	 * Contained liquid.
	 */
	public LiquidStack liquid;
	/**
	 * Item filled with liquid.
	 */
	public ItemStack filled;
	/**
	 * Item without liquid.
	 */
	public ItemStack empty;
	/**
	 * Bucket behaviour. Non-stackable, leaves {@link empty} after contents are consumed.
	 */
	public boolean isBucket;

	public LiquidContainer(LiquidStack liquid, ItemStack filled, ItemStack empty, boolean isBucket) {
		this.liquid = liquid;
		this.filled = filled;
		this.empty = empty;
		this.isBucket = isBucket;
	}
}
