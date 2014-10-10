/*
 */
package two.newdawn.API;

import two.newdawn.API.noise.SimplexNoise;

/**
 * A biome selector is queried during world generation and can decide for a certain
 * biome to be present at a certain location based on the generation/chunk information.
 *
 * A selector can - but does not have to - be applicable for one or many locations, but
 * if it is applicable it must return a biome. It is recommended to use one selector
 * for several similar biomes to speed-up generation.
 *
 * Keep in mind that terrain is everywhere. If you create i.E. a selector for volcanoes,
 * this might look cool, but if you then have volcanoes all over the place, they start
 * to get boring after a while. A good selector is able to create all kind of combinations
 * of terrain for diversity.
 *
 * To limit a certain biome to a certain area, you can either query the existing chunk
 * information functions, or create a new NoiseStrech for this biome, that then limits
 * the areas in which this biome can exist. A single NoiseStrech is a "point" limitation
 * and useful for biomes that only exist at certain points. A combination of NoiseStreches
 * will create a much more realistic feeling of biome placement.
 *
 * @author Two
 */
public abstract class NewDawnBiomeSelector implements Comparable<NewDawnBiomeSelector> {

  /* The world base noise */
  protected final SimplexNoise worldNoise;
  /* The priority of this selector */
  protected final int priority;

  /**
   * Creates a new NewDawnBiomeSelector with the given parameters.
   *
   * The priority should be set by the provider.
   *
   * @param worldNoise the world base noise.
   * @param priority the priority of this selector. @See getPriority.
   */
  public NewDawnBiomeSelector(final SimplexNoise worldNoise, final int priority) {
    this.worldNoise = worldNoise;
    this.priority = priority;
  }

  /**
   * Returns whether or not this will potentially modify the terrain height.
   * Only those selectors that return true will be queried for terrain changes during generation.
   *
   * @return true if this will eventually modify the terrain height, false otherwise.
   */
  public boolean modifiesTerrain() {
    return false;
  }

  /**
   * Returns whether or not this will modify the height at the given location.
   *
   * @param blockX the world-space block x-coordinate.
   * @param blockZ the world-space block z-coordinate.
   * @param chunkInfo the chunk information that is in the process of being generated.
   * @return true if this will modify the given location, false otherwise.
   */
  public boolean modifiesLocation(final int blockX, final int blockZ, final ChunkInformation chunkInfo) {
    return false;
  }

  /**
   * Returns the modified height (if any) for the given location.
   *
   * This return value of this method will be added to the current height, so do
   * <b>not</b> include the given height information into your return value, those
   * are only provided for information.<br>
   * <br>
   * All modifications should be subtile, as otherwise several overlayed modifications
   * of different mods can result in very strange terrain. Keep in mind: terrain
   * is everywhere! A mesa plateau overlayed with a mountain just looks strange.<br>
   * <br>
   * A selector that modifies the terrain height does not necessarily have to be
   * the one selecting the biome for this location.
   *
   * @param blockX the world-space block x-coordinate.
   * @param blockZ the world-space block z-coordinate.
   * @param baseHeight the world's height at this location without any modifications.
   * @param regionHeight the world's region (wide area) height (included in baseHeight already) to determine how much local noise changed the elevation.
   * @param roughness how rough the terrain at the given location is supposed to be.
   * @param currentModification the current modified sum of heights from other selectors.
   * @param isModified whether or not another selector has already modified this location. This is true as soon as any call do modifiesLocation returned true, no matter what this function for the other selector returned.
   * @param chunkInfo the chunk information that is in the process of being generated.
   * @return the height-modification that will later be added to the base height.
   */
  public double modifyHeight(final int blockX, final int blockZ, final double baseHeight, final double regionHeight, final double roughness, final double currentModification, final boolean isModified, final ChunkInformation chunkInfo) {
    return 0.0;
  }

  /**
   * Tries to select a biome based on the given parameters and returns it.
   * This is called during generation and can decide whether or not this selector
   * defines the biome at the given location. A return value of null will cause
   * the generation to continue to query selectors, while a non-null return value
   * will define the biome at this location.
   *
   * @param blockX the world-space block x-coordinate.
   * @param blockZ the world-space block z-coordinate.
   * @param chunkInfo information about the chunk this block is located in.
   * @return a valid NewDawnBiome, if this selector is applicable, null otherwise.
   */
  public NewDawnBiome selectBiome(final int blockX, final int blockZ, final ChunkInformation chunkInfo) {
    return null;
  }

  /**
   * Returns the priority of this selector.
   * Higher priority selectors will get a chance to select a biome before lower
   * priority selectors.<br>
   * Set in constructor.
   *
   * @return the priority of this selector.
   */
  public int getPriority() {
    return priority;
  }

  @Override
  public int hashCode() {
    int hash = this.getClass().hashCode();
    hash = 97 * hash + this.getPriority();
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NewDawnBiomeSelector other = (NewDawnBiomeSelector) obj;
    return (this.compareTo(other) == 0);
  }

  @Override
  public int compareTo(final NewDawnBiomeSelector other) {
    return Integer.signum(other.getPriority() - this.getPriority());
  }
}
