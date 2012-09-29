package extrabiomes;
/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */


import extrabiomes.configuration.ExtrabiomesConfig;

public class ModulePreInitEvent {

	private final ExtrabiomesConfig	config;

	ModulePreInitEvent(ExtrabiomesConfig config) {
		this.config = config;
	}

	public ExtrabiomesConfig getConfig() {
		return config;
	}

}
