
package vazkii.um;

import net.minecraft.src.BaseMod;
import net.minecraftforge.common.ForgeHooks;

/**
 * This class is dedicated to add compatibility with other mods.
 * 
 * @author Vazkii
 */
public final class ModCompatibility {

	public final class Forge extends UpdateManagerMod {

		public Forge() {
			super(new BaseMod() {
				@Override
				public String getVersion() {
					return String.format("%d.%d.%d.%d",
							ForgeHooks.majorVersion,
							ForgeHooks.minorVersion,
							ForgeHooks.revisionVersion,
							ForgeHooks.buildVersion);
				}

				@Override
				public void load() {}
			});
		}

		@Override
		public String[] addNotes() {
			return new String[] {
					"The version being checked for is the reccommended version rather",
					"than the latest version." };
		}

		@Override
		public String getModName() {
			return "Minecraft Forge";
		}

		@Override
		public ModType getModType() {
			return ModType.API;
		}

		@Override
		public String getModURL() {
			return "http://minecraftforge.net/forum/index.php/topic,5.0.html";
		}

		@Override
		public String getUpdateURL() {
			return "http://dl.dropbox.com/u/28221422/MinecraftForge/Recommended.txt";
		}
	}

	static boolean	inited	= false;

	public ModCompatibility() {
		if (!inited) {
			new Forge();
			inited = true;
		}
	}
}
