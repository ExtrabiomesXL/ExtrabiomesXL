/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import java.util.EnumMap;
import java.util.Map;

import net.minecraftforge.common.Property;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.EventBus;

import com.google.common.base.Optional;

import extrabiomes.configuration.EnhancedConfiguration;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.module.amica.Amica;
import extrabiomes.module.cautia.Cautia;
import extrabiomes.module.fabrica.Fabrica;
import extrabiomes.module.summa.Summa;

enum Module {
	SUMMA("summa", "config.summa.comment", Summa.class),
	CAUTIA("cautia", "config.cautia.comment", Cautia.class),
	FABRICA("fabrica", "config.fabrica.comment", Fabrica.class),
	AMICA("amica", "config.amica.comment", Amica.class);
	// MACHINA("machina", "Set machina to true to in enable higher level tech."),

	private static final String			MODULE_STATUS_DISABLED	= "module.status.disabled";
	private static final String			MODULE_STATUS_ENABLED	= "module.status.enabled";
	private static boolean				controlSettingsLoaded	= false;
	private static Optional<EventBus>	eventBus				= Optional
																		.of(new EventBus());

	private static void loadControlSettings(EnhancedConfiguration cfg) {
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
			property.comment = Extrabiomes.proxy
					.getStringLocalization(module.configComment);
		}
	}

	public static boolean postEvent(Event event) {
		return eventBus.isPresent() ? eventBus.get().post(event)
				: false;
	}

	static void registerModules(EnhancedConfiguration config)
			throws InstantiationException, IllegalAccessException
	{
		// Only do this once
		if (controlSettingsLoaded) return;

		loadControlSettings(config);
		controlSettingsLoaded = true;

		for (final Module module : Module.values()) {
			LogHelper
					.info(Extrabiomes.proxy
							.getStringLocalization(module.enabled ? MODULE_STATUS_ENABLED
									: MODULE_STATUS_DISABLED), module
							.toString());

			// skip disabled modules
			if (!module.enabled) continue;

			if (eventBus.isPresent())
				eventBus.get().register(
						module.pluginClass.newInstance());
		}
	}

	public static void releaseStaticResources() {
		eventBus = Optional.absent();
	}

	private boolean			enabled	= false;

	private final String	configComment;

	private final String	key;

	private final Class		pluginClass;

	private Module(String key, String configComment, Class pluginClass)
	{
		this.key = key;
		this.configComment = configComment;
		this.pluginClass = pluginClass;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
