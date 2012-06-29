
package extrabiomes.config;

import net.minecraft.server.Achievement;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import extrabiomes.AchievementScarecrow;
import forge.AchievementPage;

public class AchievementManager {
	public static AchievementPage	achievePage	= new AchievementPage(
														"Extrabiomes XL",
														new Achievement[0]);

	public static void craftingAchievement(EntityHuman entityhuman,
			ItemStack itemstack)
	{
		AchievementScarecrow
				.craftingAchievement(entityhuman, itemstack);
	}

	public static void initialize() {
		AchievementScarecrow.initialize();
	}

	public static void itemPickup(EntityHuman entityhuman,
			ItemStack itemstack)
	{}

	public AchievementManager() {}
}
