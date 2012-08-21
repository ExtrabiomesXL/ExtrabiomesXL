package forestry.api.core;

import java.util.ArrayList;

public enum EnumTemperature {
	NONE("None", 0), ICY("Icy", 8), COLD("Cold", 6), NORMAL("Normal", 1), WARM("Warm", 4), HOT("Hot", 2), HELLISH("Hellish", 10);

	/**
	 * Populated by Forestry with vanilla biomes. Add additional icy/snow biomes here. (ex. snow plains)
	 */
	public static ArrayList<Integer> icyBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional cold biomes here. (ex. taiga)
	 */
	public static ArrayList<Integer> coldBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional normal biomes here. (ex. forest, plains)
	 */
	public static ArrayList<Integer> normalBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional warm biomes here. (ex. jungle)
	 */
	public static ArrayList<Integer> warmBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional hot biomes here. (ex. desert)
	 */
	public static ArrayList<Integer> hotBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional hellish biomes here. (ex. nether)
	 */
	public static ArrayList<Integer> hellishBiomeIds = new ArrayList<Integer>();

	public final String name;
	public final int iconIndex;

	private EnumTemperature(String name, int iconIndex) {
		this.name = name;
		this.iconIndex = iconIndex;
	}

	public String getName() {
		return this.name;
	}

	public int getIconIndex() {
		return this.iconIndex;
	}

	public static ArrayList<Integer> getBiomeIds(EnumTemperature temperature) {

		switch (temperature) {
		case ICY:
			return icyBiomeIds;
		case COLD:
			return coldBiomeIds;
		case WARM:
			return warmBiomeIds;
		case HOT:
			return hotBiomeIds;
		case HELLISH:
			return hellishBiomeIds;
		case NORMAL:
		default:
			return normalBiomeIds;
		}

	}
}
