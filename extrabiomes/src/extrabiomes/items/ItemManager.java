/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.items;

import static extrabiomes.utility.ConfigSetting.IntegerSettingType.INTEGER;
import net.minecraftforge.common.Configuration;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.utility.ConfigSetting;

public class ItemManager {

	public static void addNames() {
		if (ExtrabiomesItem.scarecrow.isPresent())
			Extrabiomes.proxy.addName(ExtrabiomesItem.scarecrow.get(),
					"Scarecrow");
	}

	@ConfigSetting(integerType = INTEGER, key = "scarecrow.id", category = Configuration.CATEGORY_ITEM, comment = "Set to 0 to disable.")
	private final int	scarecrow	= 12870;

	public void RegisterItems() {
		if (scarecrow != 0)
			ExtrabiomesItem.scarecrow = Optional.of(new ItemScarecrow(
					scarecrow).setItemName("scarecrow")
					.setIconIndex(96));
	}

}
