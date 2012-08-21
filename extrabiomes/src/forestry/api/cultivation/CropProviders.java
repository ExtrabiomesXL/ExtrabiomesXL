package forestry.api.cultivation;

import java.util.ArrayList;

public class CropProviders {

	/**
	 * Any {@link ICropProvider} providing arboreal stuff (trees).
	 * 
	 * Used by the arboretum and logger.
	 * 
	 */
	public static ArrayList<ICropProvider> arborealCrops = new ArrayList<ICropProvider>();
	/**
	 * Any {@link ICropProvider} providing cereal stuff (wheat).
	 * 
	 * Used by the farm and combine.
	 * 
	 */
	public static ArrayList<ICropProvider> cerealCrops = new ArrayList<ICropProvider>();
	/**
	 * Any {@link ICropProvider} providing infernal stuff (netherwarts).
	 * 
	 * Used by the infernal farm and harvester.
	 * 
	 */
	public static ArrayList<ICropProvider> infernalCrops = new ArrayList<ICropProvider>();
	/**
	 * Any {@link ICropProvider} providing herbaceous stuff (pumpkins, melons).
	 * 
	 * Used by the pumpkin/melon farm and harvester.
	 * 
	 */
	public static ArrayList<ICropProvider> herbaceousCrops = new ArrayList<ICropProvider>();
	/**
	 * Any {@link ICropProvider} providing succulent stuff (cacti).
	 * 
	 * Used by the cacti harvester.
	 * 
	 */
	public static ArrayList<ICropProvider> succulentCrops = new ArrayList<ICropProvider>();
	/**
	 * Any {@link ICropProvider} providing poales stuff (reeds).
	 * 
	 * Used by the sugar cane harvester.
	 * 
	 */
	public static ArrayList<ICropProvider> poaleCrops = new ArrayList<ICropProvider>();
	/**
	 * Any {link ICropProvider} providing fungal stuff (mushrooms).
	 * 
	 * Used by the mushroom farm and picker.
	 * 
	 */
	public static ArrayList<ICropProvider> fungalCrops = new ArrayList<ICropProvider>();

}
