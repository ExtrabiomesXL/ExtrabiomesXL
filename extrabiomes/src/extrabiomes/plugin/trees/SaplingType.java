/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.trees;

enum SaplingType {
	BROWN(0, "Brown Autumn Sapling"),
	ORANGE(1, "Orange Autumn Sapling"),
	PURPLE(2, "Purple Autumn Sapling"),
	YELLOW(3, "Yellow Autumn Sapling"),
	FIR(4, "Fir Sapling"),
	REDWOOD(5, "Redwood Sapling"),
	ACACIA(6, "Acacia Sapling");

	private final int		value;
	private final String	itemName;

	SaplingType(int value, String itemName) {
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
