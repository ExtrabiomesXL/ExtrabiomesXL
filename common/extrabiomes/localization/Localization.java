/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.localization;

public enum Localization {
    US("en_US"), GERMAN("de_DE"), FRENCH("fr_FR"), PORTUGAL("pt_PT"), BRAZIL("pt_BR"), ITALIAN(
            "it_IT"), RUSSIAN("ru_RU"), CHINESE_SC("zh_CN"),CHINESE_TC("zh_TW"), SPANISH_AR("es_AR"), SPANISH_ES("es_ES"),
            SPANISH_MX("es_MX"), SPANISH_UY("es_UY"), SPANISH_VE("es_VE")/*
                                       * CZECH("cs_CZ"), WELSH("cy_GB"),
                                       * FRENCH("fr_FR"),
                                       * JAPANESE("ja_JP"),
                                       * DUTCH("nl_NL"),
                                       * POLISH("pl_PL"),
                                       * SERBIAN("sr_RS"),
                                       * SWEDISH("sv_SE")
                                       */;

    private final String locale;

    Localization(String locale) {
        this.locale = locale;
    }

    public String filename() {
        return String.format("/extrabiomes/lang/%s.xml", locale);
    }

    public String locale() {
        return locale;
    }
}
