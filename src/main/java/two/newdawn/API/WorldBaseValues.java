/*
 */
package two.newdawn.API;

/**
 * World base values to be used for extended biome selection.
 *
 * Whenever possible the information in ChunkInformation should be used, but if
 * required the location parameters can be compared to the values below.
 *
 * Temperature and humidity usually range between -1 and 1, but based on the terrain
 * they can exceed these boundaries.
 *
 * Each world can have its own and very different base values.
 *
 * @author Two
 */
public class WorldBaseValues {
  /* Global temperature thresholds */

  public final float temperatureFreezing;
  public final float temperatureHot;
  /* Global humidity thresholds */
  public final float humiditySparse;
  public final float humidityWet;
  /* A humidity level at which woodland is suggested */
  public final float minHumidityWoodland;
  /* The height of the first block above ocean water.<br>
   * This means that groundLevel - 1 is either a water block (ocean) or a non-water block (shore). */
  public final int groundLevel;

  /**
   * Created by the NewDawn mod during player login.
   */
  public WorldBaseValues(final float temperatureFreezing, final float temperatureHot,
          final float humiditySparse, final float humidityWet,
          final float minHumidityWoodland,
          final int groundLevel) {
    this.temperatureFreezing = temperatureFreezing;
    this.temperatureHot = temperatureHot;
    this.humiditySparse = humiditySparse;
    this.humidityWet = humidityWet;
    this.minHumidityWoodland = minHumidityWoodland;
    this.groundLevel = groundLevel;
  }
}
