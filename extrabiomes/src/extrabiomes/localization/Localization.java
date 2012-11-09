/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.localization;

public enum Localization {
	US("en_US"), GERMAN("de_DE"), FRENCH("fr_FR")/* CZECH("cs_CZ"), WELSH("cy_GB"), SPANISH("es_ES"), FRENCH("fr_FR"), ITALIAN("it_IT"),
	JAPANESE("ja_JP"), DUTCH("nl_NL"), POLISH("pl_PL"), PORTUGESE("pt_PT"), RUSSIAN("ru_RU"), SERBIAN("sr_RS"), SWEDISH("sv_SE"),
	CHINESE("zh_CN")*/;

	private final String locale;

	Localization(String locale) {
		this.locale = locale;
	}

	public String locale() {
		return locale;
	}
	
	public String filename() {
		return String.format("/extrabiomes/lang/%s.xml", locale);
	}
}
