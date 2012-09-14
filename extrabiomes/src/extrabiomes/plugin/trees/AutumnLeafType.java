/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.trees;

enum AutumnLeafType {
	BROWN(0, "Brown Autumn Leaves"),
	ORANGE(1, "Orange Autumn Leaves"),
	PURPLE(2, "Purple Autumn Leaves"),
	YELLOW(3, "Yellow Autumn Leaves");

	private final int		value;
	private final String	itemName;

	AutumnLeafType(int value, String itemName) {
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
