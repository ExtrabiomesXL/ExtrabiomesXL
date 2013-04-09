/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenLegendOak extends WorldGenerator {

    enum Acuteness {
        LOOSE, TIGHTER, TIGHT
    }

    enum BendDirection {
        LEFT, STRAIGHT, RIGHT
    }

    private enum TreeBlock {
        LEAVES(new ItemStack(Block.leaves)), TRUNK_NE(new ItemStack(Block.wood)), TRUNK_NW(
                new ItemStack(Block.wood)), TRUNK_SE(new ItemStack(Block.wood)), TRUNK_SW(
                new ItemStack(Block.wood)), BRANCH(new ItemStack(Block.wood));

        private ItemStack      stack;

        private static boolean loadedCustomBlocks = false;

        private static void loadCustomBlocks() {
            if (Element.LOG_HUGE_OAK_NE.isPresent())
                TRUNK_NE.stack = Element.LOG_HUGE_OAK_NE.get();
            if (Element.LOG_HUGE_OAK_NW.isPresent())
                TRUNK_NW.stack = Element.LOG_HUGE_OAK_NW.get();
            if (Element.LOG_HUGE_OAK_SE.isPresent())
                TRUNK_SE.stack = Element.LOG_HUGE_OAK_SE.get();
            if (Element.LOG_HUGE_OAK_SW.isPresent())
                TRUNK_SW.stack = Element.LOG_HUGE_OAK_SW.get();

            loadedCustomBlocks = true;
        }

        TreeBlock(ItemStack stack) {
            this.stack = stack;
        }

        public int getID() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return stack.itemID;
        }

        public int getMetadata() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return stack.getItemDamage();
        }

    }

    public WorldGenLegendOak(boolean doNotify) {
        super(doNotify);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        if (!TreeSoilRegistry.isValidSoil(world.getBlockId(x, y - 1, z))) return false;

        final int height = random.nextInt(4) + 3;
        final int size = 15 + random.nextInt(25);

        growTree(world, random, x, y, z, height, 0, size);

        return true;
    }

    private void growLeafNode(World world, int x, int y, int z) {
        for (int xOffset = -3; xOffset <= 3; xOffset++)
            for (int zOffset = -3; zOffset <= 3; zOffset++) {
                if ((Math.abs(xOffset) != 3 || Math.abs(zOffset) != 3)
                        && (Math.abs(xOffset) != 3 || Math.abs(zOffset) != 2)
                        && (Math.abs(xOffset) != 2 || Math.abs(zOffset) != 3)
                        && (xOffset != 0 || zOffset != 0))
                    if (world.getBlockId(x + xOffset, y, z + zOffset) == 0)
                        setBlockAndMetadata(world, x + xOffset, y, z + zOffset,
                                TreeBlock.LEAVES.getID(), TreeBlock.LEAVES.getMetadata());
                if (Math.abs(xOffset) >= 3 || Math.abs(zOffset) >= 3 || Math.abs(xOffset) == 2
                        && Math.abs(zOffset) == 2) continue;
                if (world.getBlockId(x + xOffset, y - 1, z + zOffset) == 0)
                    setBlockAndMetadata(world, x + xOffset, y - 1, z + zOffset,
                            TreeBlock.LEAVES.getID(), TreeBlock.LEAVES.getMetadata());
                if (world.getBlockId(x + xOffset, y + 1, z + zOffset) != 0) continue;
                setBlockAndMetadata(world, x + xOffset, y + 1, z + zOffset,
                        TreeBlock.LEAVES.getID(), TreeBlock.LEAVES.getMetadata());
            }
    }

    protected void growLeaves(World world, Random random, int x, int y, int z, int height,
            int leaflessHeight, int leafWidth)
    {
        for (final BendDirection xDirection : BendDirection.values())
            for (final BendDirection zDirection : BendDirection.values()) {
                if (xDirection == BendDirection.STRAIGHT && zDirection == BendDirection.STRAIGHT)
                    continue;
                primary(world, random, leafWidth, xDirection, zDirection, x, y + height, z);
                inside(world, random, leafWidth, xDirection, zDirection, x, y + height, z);
                insideSmall(world, random, leafWidth, xDirection, zDirection, x, y + height, z);
            }
    }

    protected void growTree(World world, Random random, int x, int y, int z, int height,
            int leaflessHeight, int leafWidth)
    {
        world.setBlock(x, y - 1, z, Block.dirt.blockID);
        world.setBlock(x - 1, y - 1, z, Block.dirt.blockID);
        world.setBlock(x, y - 1, z - 1, Block.dirt.blockID);
        world.setBlock(x - 1, y - 1, z - 1, Block.dirt.blockID);

        growTrunk(world, random, x, y, z, height);

        growLeaves(world, random, x, y, z, height, leaflessHeight, leafWidth);

    }

    protected void growTrunk(World world, Random random, int x, int y, int z, int height) {

        for (int yOffset = 0; yOffset < height + 1; yOffset++) {
            setBlockAndMetadata(world, x, y + yOffset, z, TreeBlock.TRUNK_SE.getID(),
                    TreeBlock.TRUNK_SE.getMetadata());
            setBlockAndMetadata(world, x - 1, y + yOffset, z, TreeBlock.TRUNK_SW.getID(),
                    TreeBlock.TRUNK_SW.getMetadata());
            setBlockAndMetadata(world, x, y + yOffset, z - 1, TreeBlock.TRUNK_NE.getID(),
                    TreeBlock.TRUNK_NE.getMetadata());
            setBlockAndMetadata(world, x - 1, y + yOffset, z - 1, TreeBlock.TRUNK_NW.getID(),
                    TreeBlock.TRUNK_NW.getMetadata());
        }

    }

    private void inside(World world, Random random, int size, BendDirection xDirection,
            BendDirection zDirection, int x, int y, int z)
    {
        int length = 0;
        while (length < 2 * size / 3) {
            setBlockAndMetadata(world, x, y, z, TreeBlock.BRANCH.getID(),
                    TreeBlock.BRANCH.getMetadata());
            if (random.nextInt(3) == 0 || length == 2 * size / 3 - 1) growLeafNode(world, x, y, z);
            switch (xDirection) {
                case STRAIGHT:
                    x += random.nextInt(3) - 1;
                    break;
                case RIGHT:
                    x += random.nextInt(2);
                    break;
                case LEFT:
                    x -= random.nextInt(2);
            }
            switch (zDirection) {
                case STRAIGHT:
                    z += random.nextInt(3) - 1;
                    break;
                case RIGHT:
                    z += random.nextInt(2);
                    break;
                case LEFT:
                    z -= random.nextInt(2);
            }
            if (random.nextInt(6) == 0 && length > size / 3)
                secondary(world, random, size / 3 - length / 3, xDirection, zDirection, x, y, z);
            y++;
            length++;
        }
    }

    private void insideSmall(World world, Random random, int size, BendDirection xDirection,
            BendDirection zDirection, int x, int y, int z)
    {
        int length = 0;
        while (length < size / 3) {
            setBlockAndMetadata(world, x, y, z, TreeBlock.BRANCH.getID(),
                    TreeBlock.BRANCH.getMetadata());
            if (random.nextInt(3) == 0 || length == size / 3 - 1) growLeafNode(world, x, y, z);
            switch (xDirection) {
                case STRAIGHT:
                    x += random.nextInt(3) - 1;
                    break;
                case RIGHT:
                    x += random.nextInt(2);
                    break;
                case LEFT:
                    x -= random.nextInt(2);
            }
            switch (zDirection) {
                case STRAIGHT:
                    z += random.nextInt(3) - 1;
                    break;
                case RIGHT:
                    z += random.nextInt(2);
                    break;
                case LEFT:
                    z -= random.nextInt(2);
            }
            if (random.nextInt(6) == 0 && length > size / 6)
                secondary(world, random, size / 6 - length / 6, xDirection, zDirection, x, y, z);
            y++;
            length++;
        }
    }

    private void primary(World world, Random random, int size, BendDirection xDirection,
            BendDirection zDirection, int x, int y, int z)
    {
        Acuteness acuteness = Acuteness.LOOSE;
        int length = 0;
        if (xDirection == BendDirection.RIGHT) x += 2;
        if (xDirection == BendDirection.LEFT) x -= 2;
        if (zDirection == BendDirection.RIGHT) z += 2;
        if (zDirection == BendDirection.LEFT) z -= 2;
        while (length < size) {
            switch (acuteness) {
                case LOOSE:
                    if (random.nextInt(4) == 0) y++;
                    break;
                case TIGHTER:
                    if (random.nextInt(2) == 0) y++;
                    break;
                case TIGHT:
                    y++;
            }
            setBlockAndMetadata(world, x, y, z, TreeBlock.BRANCH.getID(),
                    TreeBlock.BRANCH.getMetadata());
            if (random.nextInt(3) == 0 || length == size - 1) growLeafNode(world, x, y, z);
            switch (xDirection) {
                case STRAIGHT:
                    x += random.nextInt(3) - 1;
                    break;
                case RIGHT:
                    x += random.nextInt(2);
                    break;
                case LEFT:
                    x -= random.nextInt(2);
            }
            switch (zDirection) {
                case STRAIGHT:
                    z += random.nextInt(3) - 1;
                    break;
                case RIGHT:
                    z += random.nextInt(2);
                    break;
                case LEFT:
                    z -= random.nextInt(2);
            }
            if (length == size / 3) acuteness = Acuteness.TIGHTER;
            if (length == 2 * size / 3) acuteness = Acuteness.TIGHT;
            if (random.nextInt(4) == 0)
                secondary(world, random, size / 2 - length / 2, xDirection, zDirection, x, y, z);
            length++;
        }
    }

    private void secondary(World world, Random random, int size, BendDirection xDirection,
            BendDirection zDirection, int x, int y, int z)
    {
        int length = 0;
        for (int branch = 0; branch < 2; branch++) {
            int x1 = x;
            int y1 = y;
            int z1 = z;
            while (length < size) {
                if (random.nextInt(2) == 0) y1++;
                setBlockAndMetadata(world, x1, y1, z1, TreeBlock.BRANCH.getID(),
                        TreeBlock.BRANCH.getMetadata());
                if (random.nextInt(4) == 0 || length == size - 1) growLeafNode(world, x1, y1, z1);
                if (zDirection == BendDirection.STRAIGHT) {
                    if (xDirection == BendDirection.RIGHT)
                        x1 += random.nextInt(2);
                    else
                        x1 -= random.nextInt(2);
                    if (branch == 0)
                        z1 += random.nextInt(2);
                    else
                        z1 -= random.nextInt(2);
                }
                if (xDirection == BendDirection.STRAIGHT) {
                    if (zDirection == BendDirection.RIGHT)
                        z1 += random.nextInt(2);
                    else
                        z1 -= random.nextInt(2);
                    if (branch == 0)
                        x1 += random.nextInt(2);
                    else
                        x1 -= random.nextInt(2);
                }
                if (xDirection == BendDirection.RIGHT) {
                    if (zDirection == BendDirection.RIGHT) if (branch == 0)
                        z1 += random.nextInt(2);
                    else
                        x1 += random.nextInt(2);
                    if (zDirection == BendDirection.LEFT) if (branch == 0)
                        z1 -= random.nextInt(2);
                    else
                        x1 += random.nextInt(2);
                }
                if (xDirection == BendDirection.LEFT) {
                    if (zDirection == BendDirection.RIGHT) if (branch == 0)
                        z1 += random.nextInt(2);
                    else
                        x1 -= random.nextInt(2);
                    if (zDirection == BendDirection.LEFT) if (branch == 0)
                        z1 -= random.nextInt(2);
                    else
                        x1 -= random.nextInt(2);
                }
                length++;
            }
        }
    }
}