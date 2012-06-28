package extrabiomes.api;

import net.minecraft.src.BaseMod;

/**
 * To make use of the Extrabiomes API, a mod must support ModLoader and properly
 * register itself and the mod's class (the one that subclasses {@link BaseMod})
 * and has a name beginning with "mod_") must implement
 * <code>UsesExtrabiomesAPI</code>. This will allow Extrabiomes XL to
 * automatically discover and register API client mods. This interface should be
 * implemented by the API client mod.
 * 
 * @author ScottKillen
 */
public interface UsesExtrabiomesAPI {

	/**
	 * The <code>OnAPIInitialized</code> hook is called when initialization of
	 * the API is completed and a useful proxy is available.
	 * 
	 * @param api
	 *            A proxy to the Extrabiomes API. This object remains in scope
	 *            through the life of Extrabiomes XL.
	 */
	void onExtrabiomesAPIInitialized(ExtrabiomesAPI api);

	/**
	 * <code>getAPIInitializer</code> is called when an API client mod is
	 * detected and registered.
	 * 
	 * @param version A <code>String</code> containing the version of the API implementation.
	 * 
	 * @return {@link ExtrabiomesAPIInitializer} object that the API uses to
	 *         retrieve information that affects the operation of the
	 *         Extrabiomes mod. If the API client mod does not implement
	 *         <code>ExtrabiomesAPIInitializer</code>, this should return
	 *         <code>null</code>.
	 */
	ExtrabiomesAPIInitializer getAPIInitializer(String version);

	/**
	 * Returns the name used for display in logs and on the console
	 * 
	 * @return <code>String</code> containing the name used for display in logs
	 *         and on the console. This name should be a relatively short unique
	 *         identifier that will be clear to players of any experience level.
	 */
	String getDisplayNameforExtrabiomesLogs();
}
