/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.achievements;

import static extrabiomes.utility.ConfigSetting.IntegerSettingType.INTEGER;
import net.minecraft.src.Achievement;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.AchievementPage;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.ExtrabiomesAchievement;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.utility.ConfigSetting;

public class AchievementManager {

	public static void craftingAchievement(EntityPlayer player,
			ItemStack itemstack)
	{
		if (ExtrabiomesAchievement.scarecrow.isPresent()
				&& itemstack.itemID == ExtrabiomesItem.scarecrow.get().shiftedIndex)
			player.addStat(ExtrabiomesAchievement.scarecrow.get(), 1);
	}

	@ConfigSetting(integerType = INTEGER, key = "scarecrow.achievement.id")
	private final int	scarecrow	= 3070;

	public void registerAchievements() {
		if (ExtrabiomesItem.scarecrow.isPresent()) {
			ExtrabiomesAchievement.scarecrow = Optional
					.of(new Achievement(scarecrow,
							"extrabiomesBuildScarecrow", 0, 0,
							ExtrabiomesItem.scarecrow.get(), null)
							.registerAchievement());
			Extrabiomes.proxy
					.addAchievementDesc(
							ExtrabiomesAchievement.scarecrow.get(),
							"If I Only Had A Brain!",
							"Use a pumpkin, a melon and sticks to make a scarecrow.");

			ExtrabiomesAchievement.page = Optional
					.of(new AchievementPage(
							"ExtrabiomesXL",
							new Achievement[] { ExtrabiomesAchievement.scarecrow
									.get() }));
		}
	}
}
