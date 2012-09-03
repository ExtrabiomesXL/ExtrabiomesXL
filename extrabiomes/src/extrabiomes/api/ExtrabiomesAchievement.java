/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

import net.minecraft.src.Achievement;
import net.minecraftforge.common.AchievementPage;

import com.google.common.base.Optional;

/**
 * Allows direct access to Extrabiome's achievments. This class' members
 * will be populated during the @Init event. If a member is absent after
 * the Init event, then it has been deactivated by user configuration.
 * <p>
 * <b>NOTE:</b> Make sure to only reference members of this class in the
 * PostInit event or later.
 * 
 * @author ScottKillen
 * 
 */
public class ExtrabiomesAchievement {
	public static Optional<Achievement>		scarecrow	= Optional
																.absent();

	public static Optional<AchievementPage>	page		= Optional
																.absent();
}
