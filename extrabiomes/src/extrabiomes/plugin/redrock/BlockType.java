/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.redrock;

enum BlockType {
	RED_ROCK(0, "Red Rock"),
	RED_COBBLE(1, "Red Cobblestone"),
	RED_ROCK_BRICK(2, "Red Rock Brick");

	private final int		value;
	private final String	itemName;

	BlockType(int value, String itemName) {
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
