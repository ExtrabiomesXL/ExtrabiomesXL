/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

enum FlowerType {
	AUTUMN_SHRUB(0, "Autumn Shrub"),
	HYDRANGEA(1, "Hydrangea"),
	ORANGE(2, "Orange Flower"),
	PURPLE(3, "Purple Flower"),
	TINY_CACTUS(4, "Tiny Cactus"),
	ROOT(5, "Root"),
	TOADSTOOL(6, "Toad Stool"),
	WHITE(7, "White Flower");

	private final int		value;
	private final String	itemName;

	FlowerType(int value, String itemName) {
		this.value = value;
		this.itemName = itemName;
	}

	public String itemName() {
		return itemName;
	}

	public int metadata() {
		return value;
	}

}
