/*
 */
package two.newdawn.API;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * This class allows to generate any kind of biomes which are similar to vanilla
 * biomes, just with different blocks.
 *
 * During world creation each X/Z coordinate is filled from 0 to <i>height</i>
 * using blocks in this order:<br>
 * bedrock &lt; ground block &lt; filler block &lt; top block &lt; air<br>
 * Where the filler block is a random amount between 1 and 4.<br>
 * <br>
 * The benefit of using this intermediate biome class is that you can create any number of
 * biomes based on a vanilla biome without having to register a new biome type,
 * as long as only the blocks are changed. See the VanillaPlainsSelector for
 * a good example.
 *
 * @author Two
 */
public class NewDawnBiome {

  /**
   * Convenience function to create a NewDawnBiome as copy of a vanilla biome.
   *
   * @param vanillaBiome the vanilla biome to copy.
   * @return a NewDawnBiome which is an exact copy of a vanilla biome.
   */
  public static NewDawnBiome copyVanilla(final BiomeGenBase vanillaBiome) {
    return new NewDawnBiome(vanillaBiome, vanillaBiome.topBlock, vanillaBiome.fillerBlock, Block.stone.blockID);
  }

  /* The vanilla biome set during world creation */
  public final BiomeGenBase vanillaBiome;
  /* The top block used for this biome during generation */
  public final byte topBlockID;
  /* The filler block used for this biome during generation (between ground and top block) */
  public final byte fillerBlockID;
  /* The ground block used for this biome during generation (below filler block) */
  public final byte groundBlockID;

  /**
   * Convenience constructor that uses stone as ground block.
   *
   * @param vanillaBiome the vanilla biome set during world creation.
   * @param topBlockID the top block used for this biome during generation.
   * @param fillerBlockID the filler block used for this biome during generation (between ground and top block).
   */
  public NewDawnBiome(final BiomeGenBase vanillaBiome, final int topBlockID, final int fillerBlockID) {
    this(vanillaBiome, topBlockID, fillerBlockID, Block.stone.blockID);
  }

  /**
   * Creates a new NewDawnBiome using the given values during creation.
   *
   * @param vanillaBiome the vanilla biome set during world creation.
   * @param topBlockID the top block used for this biome during generation.
   * @param fillerBlockID the filler block used for this biome during generation (between ground and top block).
   * @param groundBlockID the ground block used for this biome during generation (below filler block).
   */
  public NewDawnBiome(final BiomeGenBase vanillaBiome, final int topBlockID, final int fillerBlockID, final int groundBlockID) {
    this.vanillaBiome = vanillaBiome;
    this.topBlockID = (byte) (topBlockID & 0xFF);
    this.fillerBlockID = (byte) (fillerBlockID & 0xFF);
    this.groundBlockID = (byte) (groundBlockID & 0xFF);
  }
}
