package extrabiomes.api;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.src.BiomeGenBase;

/**
 * Allows direct access to Extrabiome's biomes. Will be populated during
 * BaseMod.load().
 * <p>
 * <b>NOTE:</b> Make sure to only reference this class in ModsLoaded() or later.
 * 
 * @author ScottKillen
 * 
 */
public class BiomeManager {

	public static BiomeGenBase alpine = null;
	public static BiomeGenBase autumnwoods = null;
	public static BiomeGenBase birchforest = null;
	public static BiomeGenBase extremejungle = null;
	public static BiomeGenBase forestedisland = null;
	public static BiomeGenBase forestedhills = null;
	public static BiomeGenBase glacier = null;
	public static BiomeGenBase greenhills = null;
	public static BiomeGenBase icewasteland = null;
	public static BiomeGenBase greenswamp = null;
	public static BiomeGenBase marsh = null;
	public static BiomeGenBase meadow = null;
	public static BiomeGenBase minijungle = null;
	public static BiomeGenBase mountaindesert = null;
	public static BiomeGenBase mountainridge = null;
	public static BiomeGenBase mountaintaiga = null;
	public static BiomeGenBase pineforest = null;
	public static BiomeGenBase rainforest = null;
	public static BiomeGenBase redwoodforest = null;
	public static BiomeGenBase redwoodlush = null;
	public static BiomeGenBase savanna = null;
	public static BiomeGenBase shrubland = null;
	public static BiomeGenBase snowforest = null;
	public static BiomeGenBase snowyrainforest = null;
	public static BiomeGenBase temperaterainforest = null;
	public static BiomeGenBase tundra = null;
	public static BiomeGenBase wasteland = null;
	public static BiomeGenBase woodlands = null;

	/**
	 * Add your biomes here during BaseMod.load() to have them enabled for
	 * village spawning. <b>NOTE:</b> This is set to null after the list has
	 * been processed during ModsLoaded().
	 */
	public static Collection<BiomeGenBase> villageSpawnBiomes = new ArrayList<BiomeGenBase>();

	/**
	 * This list is populated with user enabled biomes during BaseMod.load().
	 * This list is set to null in ModsLoaded() after the list has been
	 * processed and biomes added to terrain gen through ModLoader.addBiome().
	 */
	public static Collection<BiomeGenBase> enabledCustomBiomes = new ArrayList<BiomeGenBase>();

	/**
	 * This list is populated with user disabled vanilla biomes during
	 * BaseMod.load(). This list is set to null in ModsLoaded() after the list
	 * has been processed and biomes added to terrain gen through
	 * ModLoader.removeBiome().
	 */
	public static Collection<BiomeGenBase> disabledVanillaBiomes = new ArrayList<BiomeGenBase>();

}
