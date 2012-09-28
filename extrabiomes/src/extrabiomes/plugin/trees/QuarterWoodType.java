/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.trees;

enum QuarterWoodType {
	REDWOOD(0, "Redwood"),
	FIR(1, "Fir"),
	OAK(2, "Oak");

	private final int		value;
	private final String	itemName;

	QuarterWoodType(int value, String itemName) {
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
