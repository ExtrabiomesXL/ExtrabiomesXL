package extrabiomes;

import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import extrabiomes.api.ExtrabiomesItem;

public class AchievementScarecrow {

	public static Achievement buildScarecrow;

	public static void craftingAchievement(EntityPlayer player,
			ItemStack itemstack) {
		if (buildScarecrow != null
				&& itemstack.itemID == ExtrabiomesItem.scarecrow.shiftedIndex)
			player.addStat(buildScarecrow, 1);
	}

	public static void initialize() {
		if (ExtrabiomesItem.scarecrow != null) {
			buildScarecrow = new Achievement(3070, "buildScarecrow", -1, 2,
					new ItemStack(ExtrabiomesItem.scarecrow),
					AchievementList.buildWorkBench).registerAchievement();
			ModLoader.addAchievementDesc(buildScarecrow,
					"If I Only Had A Brain!",
					"Use a pumpkin, a melon and sticks to make a scarecrow.");
		}
	}

}
