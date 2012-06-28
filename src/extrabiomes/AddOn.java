package extrabiomes;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.src.ModLoader;
import extrabiomes.api.ExtrabiomesAPIInitializer;
import extrabiomes.api.UsesExtrabiomesAPI;

final public class AddOn {

	private static boolean discoveryComplete = false;

	static Collection<AddOn> discoverAddOns() {
		Set<AddOn> set = new HashSet<AddOn>();

		if (!discoveryComplete)
			searchModsForAddOns(set);

		return set;
	}

	private static <E> void mergeCollectionIntoAnother(
			Collection<? extends E> collection, Set<E> another) {
		another.addAll(collection);
		another.remove(null);
	}

	private static void searchModsForAddOns(Set<AddOn> addOns) {
		final List mods = ModLoader.getLoadedMods();
		for (Object o : mods)
			if (o instanceof UsesExtrabiomesAPI) {
				final AddOn addOn = new AddOn((UsesExtrabiomesAPI) o);
				Log.write("Discovered API client mod:  " + addOn.name + ".");
				addOns.add(addOn);
			}
	}

	final private String name;
	final private UsesExtrabiomesAPI usesAPI;
	final private ExtrabiomesAPIInitializer initializer;
	final static private String API_VERSION = "1.0";

	private AddOn(UsesExtrabiomesAPI usesAPI) {
		super();
		final String name = usesAPI.getDisplayNameforExtrabiomesLogs();
		this.name = (name == null) ? "<null>" : new String(name);
		this.usesAPI = usesAPI;
		Log.write("Getting initialization from API client mod: " + name + ".");
		initializer = usesAPI.getAPIInitializer(API_VERSION);
		Log.write("Initialization from " + name + " complete.");
	}

	ExtrabiomesAPIInitializer getInitializer() {
		return initializer;
	}

	String getName() {
		return name;
	}

	void notifyAPIInitialized() {
		usesAPI.onExtrabiomesAPIInitialized(ExtrabiomesAPIImpl.INSTANCE);
	}
}
