
package extrabiomes.config;

import net.minecraft.src.Achievement;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.AchievementPage;
import extrabiomes.AchievementScarecrow;

public class AchievementManager {

	public static AchievementPage	achievePage	= new AchievementPage(
														"Extrabiomes XL",
														new Achievement[0]);

	public static void craftingAchievement(EntityPlayer player,
			ItemStack itemstack)
	{
		AchievementScarecrow.craftingAchievement(player, itemstack);
	}

	public static void initialize() {
		AchievementScarecrow.initialize();
	}

	public static void itemPickup(EntityPlayer player,
			ItemStack itemstack)
	{
		// for other achievements as needed
	}
}
