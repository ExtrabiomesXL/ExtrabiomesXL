/*
 */
package two.newdawn.API;

import java.util.Set;
import two.newdawn.API.noise.SimplexNoise;

/**
 * This interface is required for the one NewDawnBiomeProvider each other mod
 * needs to provide to add to the NewDawn biome generation.
 *
 * The getBiomeSelectors function is called once for each world/dimension that uses
 * the NewDawn terrain generation and should create new instances of NewDawnBiomeSelectors
 * using the given (and always different) world noise.
 *
 * Selectors are queried from lowest to highest priority till the first one that
 * is applicable for the given block position. You should distribute priorities
 * to your selectors based on which one needs to be queried first.
 *
 * Example: if you have a forest selector and a magical forest selector (which the
 * later being much more rare than the first), and you want to ensure that the
 * magical forest is used in the place of forest given a certain noise rarirty selection,
 * then the forest selector must have a priority &gt; the magical forest.
 *
 * Vanilla biome emulation uses values &gt; 100.
 *
 * @author Two
 */
public interface NewDawnBiomeProvider {

  public static final int PRIORITY_HIGHEST = 100;
  public static final int PRIORITY_LOWEST = -100;
  public static final int PRIORITY_MEDIUM = (PRIORITY_HIGHEST + PRIORITY_LOWEST) / 2;

  /**
   * Create and return a list of NewDawnBiomeSelectors for this provider using the given world noise.
   *
   * @param worldNoise the world's noise.
   * @return a list of NewDawnBiomeSelectors for this provider using the given world noise.
   */
  public Set<NewDawnBiomeSelector> getBiomeSelectors(final SimplexNoise worldNoise);
}
