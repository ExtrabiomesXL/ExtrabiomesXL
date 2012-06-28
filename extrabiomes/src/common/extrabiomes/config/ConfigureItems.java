package extrabiomes.config;

import net.minecraft.src.forge.Configuration;
import extrabiomes.ItemScarecrow;
import extrabiomes.Proxy;
import extrabiomes.api.ExtrabiomesItem;

public class ConfigureItems {

	public static void addNames() {
		if (ExtrabiomesItem.scarecrow != null)
			Proxy.addName(ExtrabiomesItem.scarecrow, "Scarecrow");
	}

	public static void initialize() {
		int scarecrowID = Config.getOrCreateIntProperty("scarecrow.id",
				Configuration.CATEGORY_ITEM, 2870);
		if (scarecrowID != 0)
			ExtrabiomesItem.scarecrow = new ItemScarecrow(scarecrowID)
					.setItemName("scarecrow").setIconIndex(96);
	}

}
