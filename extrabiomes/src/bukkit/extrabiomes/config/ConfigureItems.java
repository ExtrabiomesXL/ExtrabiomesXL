
package extrabiomes.config;

import extrabiomes.ItemScarecrow;
import extrabiomes.Proxy;
import extrabiomes.api.ExtrabiomesItem;

public class ConfigureItems {
	public static void addNames() {
		if (ExtrabiomesItem.scarecrow != null)
			Proxy.addName(ExtrabiomesItem.scarecrow, "Scarecrow");
	}

	public static void initialize() {
		final int i = Config.getOrCreateIntProperty("scarecrow.id",
				"item", 2870);

		if (i != 0)
			ExtrabiomesItem.scarecrow = new ItemScarecrow(i).a(
					"scarecrow").d(96);
	}

	public ConfigureItems() {}
}
