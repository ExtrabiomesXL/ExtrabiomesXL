package forestry.api.cultivation;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public interface ICropEntity {

	/**
	 * @return True if the crop is ready for harvest
	 */
	public boolean isHarvestable();

	/**
	 * @return Integer array of three designating the coordinates to check for a crop after this one. Useful to chop down whole trees. Return null to skip this
	 *         function.
	 */
	public int[] getNextPosition();

	/**
	 * @return Itemstacks gathered from the harvest. Also responsible for removing the harvested crop from the world.
	 */
	public ArrayList<ItemStack> doHarvest();

}
