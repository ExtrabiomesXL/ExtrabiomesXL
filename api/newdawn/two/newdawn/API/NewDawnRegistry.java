/*
 */
package two.newdawn.API;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import two.newdawn.API.noise.SimplexNoise;

/**
 * This class keeps a list of all NewDawnBiomeProviders and can query those
 * to generate a new list of NewDawnBiomeSelectors.
 *
 * There is one set of providers and N sets of selectors, where N is the amount
 * of worlds currently using the NewDawn terrain generation.
 *
 * @author Two
 */
public class NewDawnRegistry {

  /**
   * Register a new provider for NewDawn world generation.
   *
   * Should be done during post-init step.
   *
   * @param provider the provider to register.
   */
  public static void registerProvider(final NewDawnBiomeProvider provider) {
    if (provider != null) {
      if (biomeProviders.contains(provider)) {
        FMLLog.warning("Tried to add NewDawn biome provider '%s' twice!", provider.getClass().getName());
      } else {
        biomeProviders.add(provider);
      }
    }
  }
  /* Internal list of providers */
  protected final static ArrayList<NewDawnBiomeProvider> biomeProviders = new ArrayList<NewDawnBiomeProvider>();

  /**
   * Initializes a new set of NewDawnSelectors for a new world distributing the given world noise.
   *
   * The world noise can be used by the selectors to limit availablilty of biomes.
   *
   * @param worldNoise the world's noise.
   * @return a set of NewDawnBiomeSelectors sorted by priority.
   */
  public static List<NewDawnBiomeSelector> getSelectors(final SimplexNoise worldNoise) {
    final ArrayList<NewDawnBiomeSelector> result = new ArrayList<NewDawnBiomeSelector>();
    for (final NewDawnBiomeProvider provider : biomeProviders) {
      result.addAll(provider.getBiomeSelectors(worldNoise));
    }
    Collections.sort(result);
    return result;
  }
}
