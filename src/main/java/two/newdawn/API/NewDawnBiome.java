/*
 */
package two.newdawn.API;

import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import two.newdawn.API.noise.SimplexNoise;

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
    return new NewDawnBiome(vanillaBiome, vanillaBiome.topBlock, vanillaBiome.fillerBlock, Blocks.stone);
  }

  /* The vanilla biome set during world creation */
  public final BiomeGenBase vanillaBiome;
  /* The top block used for this biome during generation */
  public final Block topBlock;
  /* The filler block used for this biome during generation (between ground and top block) */
  public final Block fillerBlock;
  /* The ground block used for this biome during generation (below filler block) */
  public final Block groundBlock;

  /**
   * Convenience constructor that uses stone as ground block.
   *
   * @param vanillaBiome the vanilla biome set during world creation.
   * @param topBlock the top block used for this biome during generation.
   * @param fillerBlock the filler block used for this biome during generation (between ground and top block).
   */
  public NewDawnBiome(final BiomeGenBase vanillaBiome, final Block topBlock, final Block fillerBlock) {
    this(vanillaBiome, topBlock, fillerBlock, Blocks.stone);
  }

  /**
   * Creates a new NewDawnBiome using the given values during creation.
   *
   * @param vanillaBiome the vanilla biome set during world creation.
   * @param topBlock the top block used for this biome during generation.
   * @param fillerBlock the filler block used for this biome during generation (between ground and top block).
   * @param groundBlock the ground block used for this biome during generation (below filler block).
   */
  public NewDawnBiome(final BiomeGenBase vanillaBiome, final Block topBlock, final Block fillerBlock, final Block groundBlock) {
    this.vanillaBiome = vanillaBiome;
    this.topBlock = topBlock;
    this.fillerBlock = fillerBlock;
    this.groundBlock = groundBlock;
  }

  /**
   * Fills the given X/Z coordinate with ground blocks.
   *
   * Called for each X/Z coordinate in each chunk on the biome that defines the specific coordinate.
   *
   * @param worldNoise the world's noise.
   * @param chunkData the chunk data to write to.
   * @param dataPos the data position to start writing. Limit is dataPos + ChunkInformation.WORLD_HEIGHT.
   * @param height the calculated height for this coordinate.
   * @param fillerHeight the height of the blocks between solid ground and top block.
   * @param blockX the x-coordinate in world-space.
   * @param blockZ the x-coordinate in world-space.
   * @param chunkInfo the chunk information for the chunk of this coordinate.
   */
  public void fillLocation(final SimplexNoise worldNoise, final Block[] chunkData, int dataPos, final int height, final int fillerHeight, final int blockX, final int blockZ, final ChunkInformation chunkInfo) {
    final int bedrockHeight = fillBedrock(worldNoise, chunkData, dataPos, height, fillerHeight, blockX, blockZ, chunkInfo);
    fillGround(worldNoise, chunkData, dataPos, height, bedrockHeight, fillerHeight, blockX, blockZ, chunkInfo);

    if (chunkInfo.isBelowGroundLevel(blockX, blockZ)) { // this this part of the world below ocean level?
      fillWater(worldNoise, chunkData, dataPos, height, fillerHeight, blockX, blockZ, chunkInfo);
    }
  }

  /**
   * Fills the given X/Z coordinate with bedrock.
   *
   * @param worldNoise the world's noise.
   * @param chunkData the chunk data to write to.
   * @param dataPos the data position to start writing. Limit is dataPos + ChunkInformation.WORLD_HEIGHT.
   * @param height the calculated height for this coordinate.
   * @param fillerHeight the height of the blocks between solid ground and top block.
   * @param blockX the x-coordinate in world-space.
   * @param blockZ the x-coordinate in world-space.
   * @param chunkInfo the chunk information for the chunk of this coordinate.
   * @return the height of the bedrock.
   */
  protected int fillBedrock(final SimplexNoise worldNoise, final Block[] chunkData, int dataPos, final int height, final int fillerHeight, final int blockX, final int blockZ, final ChunkInformation chunkInfo) {
    final int bedrockHeight = Math.min(height, 5);
    chunkData[dataPos] = Blocks.bedrock; // in all cases: lowest level is solid bedrock
    for (int i = 1; i < bedrockHeight; ++i) {
      chunkData[dataPos + i] = worldNoise.noise(blockX, i, blockZ) <= 0.0 ? Blocks.bedrock : this.groundBlock; // bedrock mixed with some noise
    }
    return bedrockHeight;
  }

  /**
   * Fills the ground level (from bedrock to top) for the given X/Z coordinate.
   *
   * @param worldNoise the world's noise.
   * @param chunkData the chunk data to write to.
   * @param dataPos the data position to start writing. Limit is dataPos + ChunkInformation.WORLD_HEIGHT.
   * @param height the calculated height for this coordinate (the first air block).
   * @param bedrockHeight the calculated bedrock-height for this coordinate.
   * @param fillerHeight the height of the blocks between solid ground and top block.
   * @param blockX the x-coordinate in world-space.
   * @param blockZ the x-coordinate in world-space.
   * @param chunkInfo the chunk information for the chunk of this coordinate.
   */
  protected void fillGround(final SimplexNoise worldNoise, final Block[] chunkData, int dataPos, final int height, final int bedrockHeight, final int fillerHeight, final int blockX, final int blockZ, final ChunkInformation chunkInfo) {
    if (fillerHeight > bedrockHeight) {
      Arrays.fill(chunkData, dataPos + bedrockHeight, dataPos + fillerHeight, this.groundBlock); // bedrock -> almost at top
      Arrays.fill(chunkData, dataPos + fillerHeight, dataPos + height - 1, this.fillerBlock); // almost at top -> top
      if (this.fillerBlock == Blocks.sand) {
        Arrays.fill(chunkData, dataPos + fillerHeight, dataPos + (height - 1 + fillerHeight) / 2, Blocks.sandstone); // add some sandstone
      }
    } else {
      Arrays.fill(chunkData, dataPos + bedrockHeight, dataPos + height - 1, this.groundBlock); // special case: world height is almost at bedrock level
    }
    chunkData[dataPos + height - 1] = this.topBlock; // top block of this biome    
  }

  /**
   * Fills the given X/Z coordinate with water.
   *
   * This is called only, if the coordinate is below ground level. Water should
   * be filled till ground level.
   *
   * @param worldNoise the world's noise.
   * @param chunkData the chunk data to write to.
   * @param dataPos the data position to start writing. Limit is dataPos + ChunkInformation.WORLD_HEIGHT.
   * @param height the calculated height for this coordinate.
   * @param fillerHeight the height of the blocks between solid ground and top block.
   * @param blockX the x-coordinate in world-space.
   * @param blockZ the x-coordinate in world-space.
   * @param chunkInfo the chunk information for the chunk of this coordinate.
   */
  protected void fillWater(final SimplexNoise worldNoise, final Block[] chunkData, int dataPos, final int height, final int fillerHeight, final int blockX, final int blockZ, final ChunkInformation chunkInfo) {
    if (this.topBlock == Blocks.grass) {
      chunkData[dataPos + height - 1] = Blocks.dirt; // fixes underwater grass
    }
    Arrays.fill(chunkData, dataPos + height, dataPos + chunkInfo.baseValues.groundLevel, Blocks.water); // stationary water needs to be used, otherwise it will not react to player events because it is flowing "down".
  }
}
