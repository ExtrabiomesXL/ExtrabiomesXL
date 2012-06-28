package extrabiomes.api;

import net.minecraft.src.Item;

/**
 * Allows direct access to Extrabiome's items. Will be populated during
 * BaseMod.load(). If item is null, then it has been deactivated by user
 * configuration.
 * <p>
 * <b>NOTE:</b> Make sure to only reference it in ModsLoaded() or later.
 * 
 * @author ScottKillen
 * 
 */
public class ExtrabiomesItem {

	public static Item scarecrow;

}
