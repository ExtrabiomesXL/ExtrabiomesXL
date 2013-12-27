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
   * @param priority the priority of this selector.
   */
  public NewDawnBiomeSelector(final SimplexNoise worldNoise, final int priority) {
    this.worldNoise = worldNoise;
    this.priority = priority;
  }

  /**
   * Returns whether or not this selector will return a valid biome for the given location.
   * If this returns true, then selectBiome <b>must</b> return a valid biome.
   * 
   * @param blockX the world-space block x-coordinate.
   * @param blockZ the world-space block z-coordinate.
   * @param chunkInfo information about the chunk this block is located in.
   * @return true if this selector will return a valid biome for the given location, false otherwise.
   */
  public abstract boolean isApplicable(final int blockX, final int blockZ, final ChunkInformation chunkInfo);

  /**
   * Selects a biome based on the given parameters and returns it.
   * This is called if isApplicable returned true and <b>must</b> return a valid biome.
   * 
   * @param blockX the world-space block x-coordinate.
   * @param blockZ the world-space block z-coordinate.
   * @param chunkInfo information about the chunk this block is located in.
   * @return a valid NewDawnBiome.
   */
  public abstract NewDawnBiome selectBiome(final int blockX, final int blockZ, final ChunkInformation chunkInfo);

  /**
   * Returns the priority of this selector.
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
    return Integer.signum(this.getPriority() - other.getPriority());
  }
}
