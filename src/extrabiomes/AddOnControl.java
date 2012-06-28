package extrabiomes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public enum AddOnControl {
	INSTANCE;

	private final Set<AddOn> addOns = new LinkedHashSet<AddOn>();
	private static boolean initialized = false;

	static void doDisableComponents() {
		BiomeControl.doAddonsSetDisabledCustomBiomes();
		BiomeControl.doAddOnsDisableFlora();
	}

	private void discoverAddOns() {
		Log.write("Beginning auto-discovery of API client mods.");
		addOns.addAll(AddOn.discoverAddOns());
		addOns.remove(null);
		Log.write(String.format("Completed auto-discovery. %d API client %s discovered.", addOns.size(), addOns.size() == 1 ? "mod" : "mods"));
	}

	Collection<AddOn> getAddOns() {
		return new LinkedHashSet<AddOn>(addOns);
	}

	void initialize() {
		if (initialized)
			return;
		initialized = true;
		discoverAddOns();
	}

	void notifyAPIInitialized() {
		for (AddOn addOn : addOns)
			addOn.notifyAPIInitialized();
	}
}
