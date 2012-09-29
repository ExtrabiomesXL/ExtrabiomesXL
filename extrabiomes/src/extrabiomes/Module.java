package extrabiomes;
/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */


import java.util.EnumMap;
import java.util.Map;

import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.cautia.Cautia;
import extrabiomes.module.fabrica.Fabrica;
import extrabiomes.module.summa.Summa;

enum Module {
	SUMMA("summa", "Set summa to false to disable the mod.", Summa.class),
	CAUTIA("cautia", "Set cautia to true to add danger.", Cautia.class),
	FABRICA("fabrica", "Set fabrica to true to enable craftable items.", Fabrica.class);
	// MACHINA("machina",
	// "Set machina to true to in enable higher level tech."),
	// AMICA("amica", "Set amica to true to enable mod support.");

	private static boolean	controlSettingsLoaded	= false;

	static void do_PreInit(ExtrabiomesConfig cfg)
			throws Exception
	{
		for (final Module module : Module.values()) {
			// skip disabled modules
			if (!module.enabled) continue;

			module.preInit(cfg);
		}
	}

	static void loadControlSettings(ExtrabiomesConfig cfg) {
		// Only do this once
		if (controlSettingsLoaded) return;

		final Map<Module, Property> properties = new EnumMap(
				Module.class);

		// Load config settings
		for (final Module module : Module.values()) {
			final Property property = cfg.getOrCreateBooleanProperty(
					module.key,
					ExtrabiomesConfig.CATEGORY_MODULE_CONTROL, true);
			module.enabled = property.getBoolean(false);

			// Save for later rewriting
			properties.put(module, property);
		}

		// Everything depends on Summa
		for (final Module module : Module.values()) {
			if (!SUMMA.isEnabled()) module.enabled = false;

			// Rewrite config file
			final Property property = properties.get(module);
			property.value = Boolean.toString(module.enabled);
			property.comment = module.configComment;
		}
	}

	private boolean							enabled	= false;
	private final String					key;
	private final String					configComment;

	private final Class<? extends IModule>	pluginClass;

	private Optional<? extends IModule>		plugin	= Optional.absent();

	private Module(String key, String configComment,
			Class<? extends IModule> pluginClass)
	{
		this.key = key;
		this.configComment = configComment;
		this.pluginClass = pluginClass;
	}

	public boolean isEnabled() {
		return enabled;
	}

	private void preInit(ExtrabiomesConfig cfg) throws Exception {
		// Only do this once per module
		if (plugin.isPresent()) return;

		plugin = Optional.of(pluginClass.newInstance());
		plugin.get().preInit(new ModulePreInitEvent(cfg));
	}

}
