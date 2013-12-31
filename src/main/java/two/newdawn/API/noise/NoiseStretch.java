/*
 * Copyright (c) by Stefan Feldbinder aka Two
 */
package two.newdawn.API.noise;

/**
 * This class is a conveniece class to stretch the noise values of a SimplexNoise.
 *
 * A standard noise field has very different values for each x/z coordinate, using
 * this class these values can be smoothly interpolated.<br>
 * <br>
 * A noise stretched by N means that between noise A and noise B there will be N blocks in between.<br>
 * So a NoiseStretch initializes with 1000/1000 will interpolate between two noises over
 * an area of 1000 blocks.
 *
 * @author Two
 */
public class NoiseStretch {

  protected final double stretchX;
  protected final double stretchY;
  protected final double stretchZ;
  protected final double offsetX;
  protected final double offsetY;
  protected final double offsetZ;
  protected final SimplexNoise noise;

  /**
   * Creates a new 2D noise stretcher.
   *
   * @param noise the base noise to use.
   * @param stretchX the stretch value for x-coordinates.
   * @param stretchZ the stretch value for z-coordinates.
   * @param offsetX an offset added to the x-coordinate, which should be initialized with a random value like random.nextDouble().
   * @param offsetZ an offset added to the z-coordinate, which should be initialized with a random value like random.nextDouble().
   */
  public NoiseStretch(final SimplexNoise noise, final double stretchX, final double stretchZ, final double offsetX, final double offsetZ) {
    this(noise, stretchX, 1.0, stretchZ, offsetX, 0.0, offsetZ);
  }

  /**
   * Creates a new 3D noise stretcher.
   *
   * @param noise the base noise to use.
   * @param stretchX the stretch value for x-coordinates.
   * @param stretchY the stretch value for y-coordinates.
   * @param stretchZ the stretch value for z-coordinates.
   * @param offsetX an offset added to the x-coordinate, which should be initialized with a random value like random.nextDouble().
   * @param offsetY an offset added to the y-coordinate, which should be initialized with a random value like random.nextDouble().
   * @param offsetZ an offset added to the z-coordinate, which should be initialized with a random value like random.nextDouble().
   */
  public NoiseStretch(SimplexNoise noise, double stretchX, double stretchY, double stretchZ, double offsetX, double offsetY, double offsetZ) {
    this.noise = noise;
    this.stretchX = stretchX;
    this.stretchY = stretchY;
    this.stretchZ = stretchZ;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
    this.offsetZ = offsetZ;
  }

  /**
   * Returns the interpolated 2D noise for a given block coordinate.
   *
   * @param blockX the block x-coordinate.
   * @param blockZ the block z-coordinate.
   * @return the interpolated noise value between -1 and 1.
   */
  public double getNoise(final double blockX, final double blockZ) {
    return this.noise.noise(blockX / this.stretchX + offsetX, blockZ / this.stretchZ + offsetZ);
  }

  /**
   * Returns the interpolated 3D noise for a given block coordinate.
   *
   * @param blockX the block x-coordinate.
   * @param blockY the block y-coordinate.
   * @param blockZ the block z-coordinate.
   * @return the interpolated noise value between -1 and 1.
   */
  public double getNoise(final double blockX, final double blockY, final double blockZ) {
    return this.noise.noise(blockX / this.stretchX + offsetX, blockY / this.stretchY + offsetY, blockZ / this.stretchZ + offsetZ);
  }
}
