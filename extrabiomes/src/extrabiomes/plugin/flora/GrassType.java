/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

enum GrassType {
	BROWN(0, "Brown Grass"),
	SHORT_BROWN(1, "Short Brown Grass"),
	DEAD(2, "Dead Grass"),
	DEAD_TALL(3, "Tall Dead Grass"),
	DEAD_YELLOW(4, "Yellow Dead Grass");

	private final int		value;
	private final String	itemName;

	GrassType(int value, String itemName) {
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
