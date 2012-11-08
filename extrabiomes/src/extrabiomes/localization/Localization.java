/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.localization;

public enum Localization {
	/*CZ("cs"), GB("cy"), DE("de"), */US("en")/*, ES("es"), FR("fr"), IT("it"),
	JP("ja"), NL("nl"), PL("pl"), PT("pt"), RU("ru"), RS("sr"), SE("sv"),
	CN("zh")*/;

	private final String prefix;

	Localization(String prefix) {
		this.prefix = prefix;
	}

	public String locale() {
		return String.format("%s_%s", prefix, toString());
	}
	
	public String filename() {
		return String.format("/extrabiomes/lang/%s.xml", locale());
	}
}
