/*
 */
package two.newdawn.API;

import java.util.HashMap;

/**
 * This class collects chunk information during generation and is used to define the biomes later.
 *
 * Mods can read all these values to decide which biome is selected at a given world position.<br>
 * <br>
 * Mods are allowed to modify these values, but only if there is a good reason to.
 * Keep in mind that in this way of generation the terrain, these information define
 * the biome, and not the other way around.
 *
 * @author Two
 */
public class ChunkInformation {

  public enum Temperature {

    cold, medium, hot
  };

  public enum Humidity {

    sparse, medium, woodland, wet
  };

  /* The maximum world height. You must not exceed it, Minecraft forbids it! */
  public static final int WORLD_HEIGHT = 256;
  /* A scale factor to ease in calculation */
  public static final double BLOCK_SCALE = ((double) WORLD_HEIGHT) / 128.0;
  /* The length of each side of a chunk */
  public static final int CHUNK_SIZE_X = 16;
  public static final int CHUNK_SIZE_Z = 16;
  /* The 2D area of a chunk, which is equal to the map sizes */
  public static final int CHUNK_SIZE_XZ = CHUNK_SIZE_X * CHUNK_SIZE_Z;
  /* Bit-mask values for conversion between world and chunk space */
  protected static final int LENGTH_MASK = CHUNK_SIZE_X - 1;

  /**
   * Converts from block-/world-space into chunk-space.
   * All internal maps of this class convert using this function.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return the chunk-space coordinate for the given world-space block coordinate
   */
  public static int blockToChunk(final int blockX, final int blockZ) {
    return ((blockX & LENGTH_MASK) + (blockZ & LENGTH_MASK) * CHUNK_SIZE_X); // multiplication is surprisingly faster than bit-shift
  }
  /**
   * Public data values
   */
  /* The chunk coordinate in chunk-space */
  public final int chunkX, chunkZ;
  /* Basic information and thresholds */
  public final WorldBaseValues baseValues;
  /* The height-map of this chunk, where height is the first air block */
  public final int[] height;
  /* The region-height-map of this chunk */
  public final int[] regionHeight;
  /* Whether or not a given position is part of a mountain */
  public final boolean[] isMountain;
  /* The temperature-map of this chunk */
  public final float[] temperature;
  /* The humidity-map of this chunk */
  public final float[] humidity;
  /* Statistical height information of this chunk. Height is the first air block above terrain */
  protected int lowestHeight;
  protected int averageHeight;
  protected int highestHeight;
  /* Statistical temperature information of this chunk */
  protected float lowestTemperature;
  protected float averageTemperature;
  protected float highestTemperature;
  /* Statistical humidity information of this chunk */
  protected float lowestHumidity;
  protected float averageHumidity;
  protected float highestHumidity;
  /* Additional information. Intended for mods that need to store any kind of extra data for whatever reason. */
  public final HashMap<String, Object> additionalInformation = new HashMap<String, Object>();

  /**
   * Called internally by the terrain generation function. Mods receive the result.
   */
  public ChunkInformation(final int chunkX, final int chunkZ, final WorldBaseValues basicInfo,
          final int[] height, final int[] regionHeight, final boolean[] isMountain, final float[] temperature, final float[] humidity) {
    this.chunkX = chunkX;
    this.chunkZ = chunkZ;
    this.height = height;
    this.regionHeight = regionHeight;
    this.isMountain = isMountain;
    this.baseValues = basicInfo;
    this.temperature = temperature;
    this.humidity = humidity;
  }

  public void updateMinMaxInformation() {
    int lowHeight = Integer.MAX_VALUE;
    int highHight = Integer.MIN_VALUE;
    int avgHeight = 0;
    for (int h : this.height) {
      avgHeight += h;
      lowHeight = Math.min(h, lowHeight);
      highHight = Math.max(h, highHight);
    }
    lowestHeight = lowHeight;
    averageHeight = Math.round(((float) avgHeight) / ((float) height.length));
    highestHeight = highHight;

    float lowTemperature = this.temperature[0];
    float avgTemperature = 0.0f;
    float highTemperature = this.temperature[0];
    for (float t : this.temperature) {
      avgTemperature += t;
      lowTemperature = Math.min(t, lowTemperature);
      highTemperature = Math.max(t, highTemperature);
    }
    lowestTemperature = lowTemperature;
    averageTemperature = avgTemperature / ((float) temperature.length);
    highestTemperature = highTemperature;

    float lowHumidity = this.humidity[0];
    float avgHumidity = 0.0f;
    float highHumidity = this.humidity[0];
    for (float h : this.humidity) {
      avgHumidity += h;
      lowHumidity = Math.min(h, lowHumidity);
      highHumidity = Math.max(h, highHumidity);
    }
    lowestHumidity = lowHumidity;
    averageHumidity = avgHumidity / ((float) humidity.length);
    highestHumidity = highHumidity;
  }

  /**
   * Returns the height of this chunk at the given block position.
   * The height is y-coordinate of the first non-terrain block (either air or water).<br>
   * If height &gt;= basicInfo.groundLevel, a player can walk at this position and the block
   * at height - 1 is a solid (non-water) block.<br>
   * If height &lt; basicInfo.groundLevel, the position is under water.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return the height of this chunk at the given block position.
   */
  public int getHeight(final int blockX, final int blockZ) {
    return height[blockToChunk(blockX, blockZ)];
  }

  /**
   * Returns the height of the region at the given block position.
   * The region height is part of the height composition and describes the general
   * height of a given position. This is useful to decipher whether the actual height
   * is above/below a certain value because of local mountains/dales or because the
   * terrain just happens to be at that height in general.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return the height of the region at the given block position.
   */
  public int getRegionHeight(final int blockX, final int blockZ) {
    return regionHeight[blockToChunk(blockX, blockZ)];
  }

  /**
   * Returns how much elevation is added by local area height.
   * The region height is part of the height composition and describes the general
   * height of a given position. The difference between height an region height
   * is the amount of height added by local hills or similar.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return how much elevation is added by local area height.
   */
  public int getElevation(final int blockX, final int blockZ) {
    final int heightRegion = getRegionHeight(blockX, blockZ);
    if (heightRegion >= 0) {
      return getHeight(blockX, blockZ) - heightRegion;
    } else {
      return 0;
    }
  }

  /**
   * Returns whether or not the terrain is part of a huge mountain at the given block position.
   * Note: Parts of mountains can as well be under water.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the terrain is part of a huge mountain at the given block position.
   */
  public boolean isMountain(final int blockX, final int blockZ) {
    return isMountain[blockToChunk(blockX, blockZ)];
  }

  /**
   * Returns the temperature at the given block position.
   * The temperature <i>usually</i> is within the range of -1 (cold) &lt;= T &lt;= 1 (hot),
   * but some calculations cause it to exceed these boundaries.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return the temperature at the given block position.
   */
  public float getTemperature(final int blockX, final int blockZ) {
    return temperature[blockToChunk(blockX, blockZ)];
  }

  /**
   * Returns the temperature enum at the given block position.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return the temperature enum at the given block position.
   */
  public Temperature getTemperatureType(final int blockX, final int blockZ) {
    final float temp = temperature[blockToChunk(blockX, blockZ)];
    if (temp <= baseValues.temperatureFreezing) {
      return Temperature.cold;
    } else if (temp >= baseValues.temperatureHot) {
      return Temperature.hot;
    } else {
      return Temperature.medium;
    }
  }

  /**
   * Returns the humidity at the given block position.
   * The humidity <i>usually</i> is within the range of -1 (dry) &lt;= H &lt;= 1 (wet),
   * but some calculations cause it to exceed these boundaries.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return the humidity at the given block position.
   */
  public float getHumidity(final int blockX, final int blockZ) {
    return humidity[blockToChunk(blockX, blockZ)];
  }

  /**
   * Returns the humidity enum at the given block position.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return the humidity enum at the given block position.
   */
  public Humidity getHumidityType(final int blockX, final int blockZ) {
    final float humid = humidity[blockToChunk(blockX, blockZ)];
    if (humid <= baseValues.humiditySparse) {
      return Humidity.sparse;
    } else if (humid >= baseValues.humidityWet) {
      return Humidity.wet;
    } else if (humid >= baseValues.minHumidityWoodland) {
      return Humidity.woodland;
    } else {
      return Humidity.medium;
    }
  }

  /**
   * Returns whether or not the given block position has a temperature that is considered hot.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has a temperature that is considered hot.
   */
  public boolean isTemperatureHot(final int blockX, final int blockZ) {
    return (getTemperature(blockX, blockZ) >= baseValues.temperatureHot);
  }

  /**
   * Returns whether or not the given block position has a temperature that is considered hot, if the given modifier is added.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @param modifier a modifier to be applied to the temperature before checking.
   * @return whether or not the given block position has a temperature that is considered hot, if the given modifier is added.
   */
  public boolean isTemperatureHot(final int blockX, final int blockZ, final float modifier) {
    return ((getTemperature(blockX, blockZ) + modifier) >= baseValues.temperatureHot);
  }

  /**
   * Returns whether or not the given block position has a temperature that is considered freezing cold.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has a temperature that is considered freezing cold.
   */
  public boolean isTemperatureFreezing(final int blockX, final int blockZ) {
    return (getTemperature(blockX, blockZ) <= baseValues.temperatureFreezing);
  }

  /**
   * Returns whether or not the given block position has a temperature that is considered freezing cold, if the given modifier is added.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @param modifier a modifier to be applied to the temperature before checking.
   * @return whether or not the given block position has a temperature that is considered freezing cold, if the given modifier is added.
   */
  public boolean isTemperatureFreezing(final int blockX, final int blockZ, final float modifier) {
    return ((getTemperature(blockX, blockZ) + modifier) <= baseValues.temperatureFreezing);
  }

  /**
   * Returns whether or not the given block position has a temperature that is considered neither freezing nor hot.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has a temperature that is considered neither freezing nor hot.
   */
  public boolean isTemperatureMedium(final int blockX, final int blockZ) {
    final float blockTemperature = getTemperature(blockX, blockZ);
    return ((blockTemperature > baseValues.temperatureFreezing) && (blockTemperature < baseValues.temperatureHot));
  }

  /**
   * Returns whether or not the given block position has a temperature that is considered neither freezing nor hot, if the given modifier is added.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @param modifier a modifier to be applied to the temperature before checking.
   * @return whether or not the given block position has a temperature that is considered neither freezing nor hot, if the given modifier is added.
   */
  public boolean isTemperatureMedium(final int blockX, final int blockZ, final float modifier) {
    final float blockTemperature = getTemperature(blockX, blockZ) + modifier;
    return ((blockTemperature > baseValues.temperatureFreezing) && (blockTemperature < baseValues.temperatureHot));
  }

  /**
   * Returns whether or not the given block position has a humidity that is considered wet.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has a humidity that is considered wet.
   */
  public boolean isHumidityWet(final int blockX, final int blockZ) {
    return (getHumidity(blockX, blockZ) >= baseValues.humidityWet);
  }

  /**
   * Returns whether or not the given block position has a humidity that is considered wet, if the given modifier is added.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @param modifier a modifier to be applied to the humidity before checking.
   * @return whether or not the given block position has a humidity that is considered wet, if the given modifier is added.
   */
  public boolean isHumidityWet(final int blockX, final int blockZ, final float modifier) {
    return ((getHumidity(blockX, blockZ) + modifier) >= baseValues.humidityWet);
  }

  /**
   * Returns whether or not the given block position has a humidity that is considered sparse.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has a humidity that is considered sparse.
   */
  public boolean isHumiditySparse(final int blockX, final int blockZ) {
    return (getHumidity(blockX, blockZ) <= baseValues.humiditySparse);
  }

  /**
   * Returns whether or not the given block position has a humidity that is considered sparse, if the given modifier is added.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @param modifier a modifier to be applied to the humidity before checking.
   * @return whether or not the given block position has a humidity that is considered sparse, if the given modifier is added.
   */
  public boolean isHumiditySparse(final int blockX, final int blockZ, final float modifier) {
    return ((getHumidity(blockX, blockZ) + modifier) <= baseValues.humiditySparse);
  }

  /**
   * Returns whether or not the given block position has a humidity that is considered neither wet nor sparse.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has a humidity that is considered neither wet nor sparse.
   */
  public boolean isHumidityMedium(final int blockX, final int blockZ) {
    final float blockHumidity = getHumidity(blockX, blockZ);
    return ((blockHumidity > baseValues.humiditySparse) && (blockHumidity < baseValues.humidityWet));
  }

  /**
   * Returns whether or not the given block position has a humidity that is considered neither wet nor sparse, if the given modifier is added.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @param modifier a modifier to be applied to the humidity before checking.
   * @return whether or not the given block position has a humidity that is considered neither wet nor sparse, if the given modifier is added.
   */
  public boolean isHumidityMedium(final int blockX, final int blockZ, final float modifier) {
    final float blockHumidity = getHumidity(blockX, blockZ) + modifier;
    return ((blockHumidity > baseValues.humiditySparse) && (blockHumidity < baseValues.humidityWet));
  }

  /**
   * Returns whether or not the given block position has parameters that suggest woodland.
   * The threshold is an arbitrary choice that causes the world to have a reasonable amount of woodland.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has parameters that suggest woodland.
   */
  public boolean isWoodland(final int blockX, final int blockZ) {
    return (getHumidity(blockX, blockZ) >= baseValues.minHumidityWoodland);
  }

  /**
   * Returns whether or not the given block position has parameters that suggest woodland, if the given modifier is added.
   * The threshold is an arbitrary choice that causes the world to have a reasonable amount of woodland.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not the given block position has parameters that suggest woodland, if the given modifier is added.
   */
  public boolean isWoodland(final int blockX, final int blockZ, final float modifier) {
    return (getHumidity(blockX, blockZ) + modifier >= baseValues.minHumidityWoodland);
  }

  /**
   * Returns whether or not a given block position is exactly at ground level.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not a given block position is exactly at ground level.
   */
  public boolean isGroundLevel(final int blockX, final int blockZ) {
    return (getHeight(blockX, blockZ) == baseValues.groundLevel);
  }

  /**
   * Returns whether or not a given block position is above sea level.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not a given block position is above sea level.
   */
  public boolean isAboveSeaLevel(final int blockX, final int blockZ) {
    return (getHeight(blockX, blockZ) >= baseValues.groundLevel);
  }

  /**
   * Returns whether or not a given block position is below or at sea level.
   * Any terrain at a height where this returns true is usually filled with water.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return whether or not a given block position is below or at sea level.
   */
  public boolean isBelowGroundLevel(final int blockX, final int blockZ) {
    return (getHeight(blockX, blockZ) < baseValues.groundLevel);
  }

  /**
   * Returns if the given block position is deep water.
   * A player needs to swim if in water that is considered deep.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return if the given block position is deep water.
   */
  public boolean isDeepWater(final int blockX, final int blockZ) {
    return (getHeight(blockX, blockZ) + 1 < baseValues.groundLevel);
  }

  /**
   * Returns if the given block position is deep ocean.
   * Deep ocean is much more below the shore line than other ocean.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return if the given block position is deep ocean.
   */
  public boolean isDeepOcean(final int blockX, final int blockZ) {
    return (getHeight(blockX, blockZ) + 4 < baseValues.groundLevel);
  }

  /**
   * Returns if the given block position is shallow water.
   * A player can still walk if in water that is considered shallow.<br>
   * Returns false if the given block position is not below ground level.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return if the given block position is shallow water.
   */
  public boolean isShallowWater(final int blockX, final int blockZ) {
    return (getHeight(blockX, blockZ) + 1 == baseValues.groundLevel);
  }

  /**
   * Returns if the given block position is either ground level or shallow water.
   * This is used for biomes that can exist in both cases like swampland.
   *
   * @param blockX the block x-coordinate in world space.
   * @param blockZ the block z-coordinate in world space.
   * @return if the given block position is either ground level or shallow water.
   */
  public boolean isGroundLevelOrShallowWater(final int blockX, final int blockZ) {
    final int blockHeight = getHeight(blockX, blockZ);
    return ((blockHeight == baseValues.groundLevel) || (blockHeight + 1 == baseValues.groundLevel));
  }

  /**
   * Returns the average humidity of this chunk.
   *
   * @return the average humidity of this chunk.
   */
  public float getAverageHumidity() {
    return averageHumidity;
  }

  /**
   * Returns the average temperature of this chunk.
   *
   * @return the average temperature of this chunk.
   */
  public float getAverageTemperature() {
    return averageTemperature;
  }

  /**
   * Returns the average height of this chunk.
   *
   * @return the average height of this chunk.
   */
  public int getAverageHeight() {
    return averageHeight;
  }

  /**
   * Returns the lowest height of this chunk.
   *
   * @return the lowest height of this chunk.
   */
  public int getLowestHeight() {
    return lowestHeight;
  }

  /**
   * Returns the highest height of this chunk.
   *
   * @return the highest height of this chunk.
   */
  public int getHighestHeight() {
    return highestHeight;
  }

  /**
   * Returns the lowest temperature of this chunk.
   *
   * @return the lowest temperature of this chunk.
   */
  public float getLowestTemperature() {
    return lowestTemperature;
  }

  /**
   * Returns the highest temperature of this chunk.
   *
   * @return the highest temperature of this chunk.
   */
  public float getHighestTemperature() {
    return highestTemperature;
  }

  /**
   * Returns the lowest humidity of this chunk.
   *
   * @return the lowest humidity of this chunk.
   */
  public float getLowestHumidity() {
    return lowestHumidity;
  }

  /**
   * Returns the highest humidity of this chunk.
   *
   * @return the highest humidity of this chunk.
   */
  public float getHighestHumidity() {
    return highestHumidity;
  }

  /**
   * Returns the difference between highest and lowest height in this chunk.
   *
   * @return the difference between highest and lowest height in this chunk.
   */
  public int getHeightDifference() {
    return this.highestHeight - this.lowestHeight;
  }

  /**
   * Returns the difference between highest and lowest temperature in this chunk.
   *
   * @return the difference between highest and lowest temperature in this chunk.
   */
  public float getTemperatureDifference() {
    return this.highestTemperature - this.lowestTemperature;
  }

  /**
   * Returns the difference between highest and lowest humidity in this chunk.
   *
   * @return the difference between highest and lowest humidity in this chunk.
   */
  public float getHumidityDifference() {
    return this.highestHumidity - this.lowestHumidity;
  }
}
