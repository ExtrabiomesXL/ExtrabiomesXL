/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.localization;

import extrabiomes.Extrabiomes;

public class LocalizationHandler {

	public static void loadLanguages() {
		for (final Localization localeFile : Localization.values())
			Extrabiomes.proxy.loadLocalization(
					localeFile.filename(), localeFile.locale());
	}

}