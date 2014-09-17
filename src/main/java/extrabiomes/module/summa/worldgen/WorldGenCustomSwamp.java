/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomSwamp extends WorldGenNewTreeBase {

  public WorldGenCustomSwamp() {
    super(false);
  }

  public WorldGenCustomSwamp(boolean notify) {
    super(notify);
  }

  @Override
  public boolean generate(World world, Random rand, int x, int y, int z) {

    while (world.getBlock(x, y - 1, z).getMaterial() == Material.water)
      y--;

    final int height = rand.nextInt(4) + 10;

    if (y < 1 || y + height + 1 > 256) {
      return false;
    }

    for (int y1 = y; y1 <= y + 1 + height; y1++) {

      if (y1 < 0 && y1 >= 256) {
        return false;
      }

      byte clearanceNeededAroundTrunk = 1;

      if (y1 == y) {
        clearanceNeededAroundTrunk = 0;
      }

      if (y1 >= y + 1 + height - 2) {
        clearanceNeededAroundTrunk = 3;
      }

      for (int x1 = x - clearanceNeededAroundTrunk; x1 <= x + clearanceNeededAroundTrunk; x1++) {
        for (int x2 = z - clearanceNeededAroundTrunk; x2 <= z + clearanceNeededAroundTrunk; x2++) {
          final Block block = world.getBlock(x1, y1, x2);

          if (block == null || block.isLeaves(world, x1, y1, x2)) {
            continue;
          }

          if (block.equals(Blocks.water) || block.equals(Blocks.flowing_water)) {
            if (y1 > y) {
              return false;
            }
          } else {
            return false;
          }
        }
      }
    }

    final Block block = world.getBlock(x, y - 1, z);

    if (!block.equals(Blocks.grass) && !block.equals(Blocks.dirt) || y >= 256 - height - 1) {
      return false;
    }

    world.setBlock(x, y - 1, z, Blocks.dirt);

    for (int y1 = y - 3 + height; y1 <= y + height; y1++) {
      final int posTrunk = y1 - (y + height);
      final int canopyRadius = 2 - posTrunk / 2;

      for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++) {
        final int xOnRadius = x1 - x;

        for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++) {
          final int zOnRadius = z1 - z;

          final Block block2 = world.getBlock(x1, y1, z1);

          if ((Math.abs(xOnRadius) != canopyRadius || Math.abs(zOnRadius) != canopyRadius || rand.nextInt(2) != 0 && posTrunk != 0) && (block2 == null || block2.canBeReplacedByLeaves(world, x1, y1, z1))) {
            world.setBlock(x1, y1, z1, Blocks.leaves);
          }
        }
      }
    }

    for (int y1 = 0; y1 < height; y1++) {
      final Block block2 = world.getBlock(x, y + y1, z);

      if (block2 == null || block2.isAir(world, x, y + y1, z) || block2.isLeaves(world, x, y + y1, z) || block2.equals(Blocks.flowing_water) || block2.equals(Blocks.water)) {
        world.setBlock(x, y + y1, z, Blocks.log);
      }
    }

    for (int y1 = y - 3 + height; y1 <= y + height; y1++) {
      final int posTrunk = y1 - (y + height);
      final int canopyRadius = 2 - posTrunk / 2;

      for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++) {
        for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++) {
          final Block block2 = world.getBlock(x1, y1, z1);
          if (block2 == null || !block2.isLeaves(world, x1, y1, z1)) {
            continue;
          }

          if (rand.nextInt(4) == 0 && world.isAirBlock(x1 - 1, y1, z1)) {
            generateVines(world, x1 - 1, y1, z1, 8);
          }

          if (rand.nextInt(4) == 0 && world.isAirBlock(x1 + 1, y1, z1)) {
            generateVines(world, x1 + 1, y1, z1, 2);
          }

          if (rand.nextInt(4) == 0 && world.isAirBlock(x1, y1, z1 - 1)) {
            generateVines(world, x1, y1, z1 - 1, 1);
          }

          if (rand.nextInt(4) == 0 && world.isAirBlock(x1, y1, z1 + 1)) {
            generateVines(world, x1, y1, z1 + 1, 4);
          }
        }
      }
    }

    return true;
  }

  private void generateVines(World world, int x, int y, int z, int metadata) {
    world.setBlock(x, y, z, Blocks.vine, metadata, 3);

    for (int i = 4; world.isAirBlock(x, --y, z) && i > 0; i--) {
      world.setBlock(x, y, z, Blocks.vine, metadata, 3);
    }
  }
}
