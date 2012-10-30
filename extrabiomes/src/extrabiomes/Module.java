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

	private static boolean				controlSettingsLoaded	= false;
	private static Optional<EventBus>	eventBus				= Optional
																		.of(new EventBus());

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

	public static boolean postEvent(Event event) {
		return eventBus.isPresent() ? eventBus.get().post(event)
				: false;
	}

	static void registerModules(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		// Only do this once
		if (controlSettingsLoaded) return;

		loadControlSettings(config);
		controlSettingsLoaded = true;

		for (final Module module : Module.values()) {
			ExtrabiomesLog.info("Module %s is %s.", module.toString(),
					module.enabled ? "enabled" : "disabled, skipping");

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
