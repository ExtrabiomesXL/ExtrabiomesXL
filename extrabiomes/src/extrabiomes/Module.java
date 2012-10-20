/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import java.util.EnumMap;
import java.util.Map;

import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.amica.Amica;
import extrabiomes.module.cautia.Cautia;
import extrabiomes.module.fabrica.Fabrica;
import extrabiomes.module.summa.Summa;

enum Module {
	SUMMA("summa", "Set summa to false to disable the mod.", Summa.class),
	CAUTIA("cautia", "Set cautia to true to add danger.", Cautia.class),
	FABRICA("fabrica", "Set fabrica to true to enable craftable items.", Fabrica.class),
	AMICA("amica", "Set amica to true to enable support for other mods.", Amica.class);
	// MACHINA("machina", "Set machina to true to in enable higher level tech."),

	private static boolean	controlSettingsLoaded	= false;
	private static boolean	initialized				= false;

	static void init() throws InstantiationException,
			IllegalAccessException
	{
		// Only do this once
		if (initialized) return;

		initialized = true;

		for (final Module module : Module.values()) {
			// skip disabled modules
			if (!module.enabled) continue;

			module.do_init();
		}
	}

	private static void loadControlSettings(ExtrabiomesConfig cfg) {
		final Map<Module, Property> properties = new EnumMap(
				Module.class);

		// Load config settings
		for (final Module module : Module.values()) {
			final Property property = cfg.get(
					ExtrabiomesConfig.CATEGORY_MODULE_CONTROL,
					module.key + ".enabled", true);
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

	static void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		// Only do this once
		if (controlSettingsLoaded) return;

		loadControlSettings(config);

		for (final Module module : Module.values()) {
			ExtrabiomesLog.info("Module %s is %s.", module.toString(),
					module.enabled ? "enabled" : "disabled, skipping");

			// skip disabled modules
			if (!module.enabled) continue;

			module.do_preInit(config);
		}
	}

	private boolean							enabled	= false;
	private final String					configComment;

	private final String					key;

	private Optional<? extends IModule>		plugin	= Optional.absent();

	private final Class<? extends IModule>	pluginClass;

	private Module(String key, String configComment,
			Class<? extends IModule> pluginClass)
	{
		this.key = key;
		this.configComment = configComment;
		this.pluginClass = pluginClass;
	}

	private void do_init() throws InstantiationException,
			IllegalAccessException
	{
		plugin.get().init();
	}

	private void do_preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		plugin = Optional.of(pluginClass.newInstance());
		plugin.get().preInit(config);
	}

	public boolean isEnabled() {
		return enabled;
	}

}
