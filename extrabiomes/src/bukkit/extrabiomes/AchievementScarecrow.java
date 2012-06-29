
package extrabiomes;

import net.minecraft.server.Achievement;
import net.minecraft.server.AchievementList;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;
import extrabiomes.api.ExtrabiomesItem;

public class AchievementScarecrow {
	public static Achievement	buildScarecrow;

	public static void craftingAchievement(EntityHuman entityhuman,
			ItemStack itemstack)
	{
		if (buildScarecrow != null
				&& itemstack.id == ExtrabiomesItem.scarecrow.id)
			entityhuman.a(buildScarecrow, 1);
	}

	public static void initialize() {
		if (ExtrabiomesItem.scarecrow != null) {
			buildScarecrow = new Achievement(3070, "buildScarecrow",
					-1, 2, new ItemStack(ExtrabiomesItem.scarecrow),
					AchievementList.h).c();
			ModLoader
					.addAchievementDesc(buildScarecrow,
							"If I Only Had A Brain!",
							"Use a pumpkin, a melon and sticks to make a scarecrow.");
		}
	}

	public AchievementScarecrow() {}
}
