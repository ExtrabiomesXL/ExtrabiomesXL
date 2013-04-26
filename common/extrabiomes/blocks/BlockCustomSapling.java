/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.module.summa.TreeSoilRegistry;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType;
import extrabiomes.module.summa.worldgen.WorldGenBigAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;

public class BlockCustomSapling extends BlockFlower {

    public enum BlockType {
        BROWN(0), ORANGE(1), PURPLE(2), YELLOW(3), FIR(4), REDWOOD(5), ACACIA(6);

        private final int metadata;

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        public int metadata() {
            return metadata;
        }
    }
    
    private Icon[] textures  = {null, null, null, null, null, null, null, null};

    private static final int METADATA_BITMASK = 0x7;
    private static final int METADATA_MARKBIT = 0x8;

    private static int       forestrySoilID   = 0;

    private static boolean isEnoughLightToGrow(World world, int x, int y, int z) {
        return world.getBlockLightValue(x, y, z) >= 9;
    }

    private static boolean isMarkedMetadata(int metadata) {
        return (metadata & METADATA_MARKBIT) != 0;
    }

    private static int markedMetadata(int metadata) {
        return metadata | METADATA_MARKBIT;
    }

    public static void setForestrySoilID(int soilID) {
        forestrySoilID = soilID;
    }

    private static int unmarkedMetadata(int metadata) {
        return metadata & METADATA_BITMASK;
    }

    public BlockCustomSapling(int id, int index) {
        super(id);
        final float var3 = 0.4F;
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegistry)
    {
	    textures[0] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingbrown");
	    textures[1] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingorange");
	    textures[2] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingred");
	    textures[3] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingyellow");
	    textures[4] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingfir");
	    textures[5] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingredwood");
	    textures[6] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "saplingacacia");
    }

    private void attemptGrowTree(World world, int x, int y, int z, Random rand) {
        if (isEnoughLightToGrow(world, x, y + 1, z) && rand.nextInt(7) == 0) {
            final int metadata = world.getBlockMetadata(x, y, z);

            if (!isMarkedMetadata(metadata))
                world.setBlockMetadataWithNotify(x, y, z, markedMetadata(metadata), 3);
            else
                growTree(world, x, y, z, rand);
        }
    }

    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int id) {
        return TreeSoilRegistry.isValidSoil(id);
    }

    @Override
    public int damageDropped(int metadata) {
        return unmarkedMetadata(metadata);
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        metadata = unmarkedMetadata(metadata);
        //if (metadata > 6) metadata = 0;
        return textures[metadata];
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
        if (tab == Extrabiomes.tabsEBXL) for (final BlockType blockType : BlockType.values())
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
    }

    public void growTree(World world, int x, int y, int z, Random rand) {
        final int metadata = unmarkedMetadata(world.getBlockMetadata(x, y, z));
        WorldGenerator tree = null;
        int x1 = 0;
        int z1 = 0;
        boolean isHuge = false;

        final boolean isForestryFarmed = world.getBlockId(x, y - 1, z) == forestrySoilID;

        if (metadata == BlockType.BROWN.metadata()) {
            if (rand.nextInt(20) == 0)
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN);
            else
                tree = new WorldGenAutumnTree(true, AutumnTreeType.BROWN);
        }

        else if (metadata == BlockType.ORANGE.metadata()) {
            if (rand.nextInt(20) == 0)
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE);
            else
                tree = new WorldGenAutumnTree(true, AutumnTreeType.ORANGE);
        }

        else if (metadata == BlockType.PURPLE.metadata()) {
            if (rand.nextInt(20) == 0)
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE);
            else
                tree = new WorldGenAutumnTree(true, AutumnTreeType.PURPLE);
        }

        else if (metadata == BlockType.YELLOW.metadata()) {
            if (rand.nextInt(20) == 0)
                tree = new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW);
            else
                tree = new WorldGenAutumnTree(true, AutumnTreeType.YELLOW);
        } else if (metadata == BlockType.ACACIA.metadata())
            tree = new WorldGenAcacia(true);
        else {
            // Check for 2x2 firs and redwoods
            for (x1 = 0; x1 >= -1; --x1) {
                for (z1 = 0; z1 >= -1; --z1)
                    if (isSameSapling(world, x + x1, y, z + z1, metadata)
                            && isSameSapling(world, x + x1 + 1, y, z + z1, metadata)
                            && isSameSapling(world, x + x1, y, z + z1 + 1, metadata)
                            && isSameSapling(world, x + x1 + 1, y, z + z1 + 1, metadata))
                    {
                        if (metadata == BlockType.FIR.metadata())
                            tree = new WorldGenFirTreeHuge(true);
                        else
                            tree = new WorldGenRedwood(true);
                        isHuge = true;
                        break;
                    }
                if (tree != null) break;
            }
            if (tree == null && metadata == BlockType.FIR.metadata()) {
                // Single fir sapling generates 1x1 tree
                z1 = 0;
                x1 = 0;
                tree = new WorldGenFirTree(true);
            }
        }

        if (tree != null) {
            if (isHuge) {
                world.setBlock(x + x1, y, z + z1, 0);
                world.setBlock(x + x1 + 1, y, z + z1, 0);
                world.setBlock(x + x1, y, z + z1 + 1, 0);
                world.setBlock(x + x1 + 1, y, z + z1 + 1, 0);
            } else
                world.setBlock(x, y, z, 0);

            final int offset = isHuge ? 1 : 0;

            if (!tree.generate(world, rand, x + x1 + offset, y, z + z1 + offset)) {
                if (isHuge) {
                    world.setBlock(x + x1, y, z + z1, blockID, metadata, 3);
                    world.setBlock(x + x1 + 1, y, z + z1, blockID, metadata, 3);
                    world.setBlock(x + x1, y, z + z1 + 1, blockID, metadata, 3);
                    world.setBlock(x + x1 + 1, y, z + z1 + 1, blockID, metadata, 3);
                } else
                    world.setBlock(x, y, z, blockID, metadata, 3);
            } else if (isForestryFarmed) if (isHuge) {
                world.setBlock(x + x1, y - 1, z + z1, Block.sand.blockID);
                world.setBlock(x + x1 + 1, y - 1, z + z1, Block.sand.blockID);
                world.setBlock(x + x1, y - 1, z + z1 + 1, Block.sand.blockID);
                world.setBlock(x + x1 + 1, y - 1, z + z1 + 1, Block.sand.blockID);
            } else
                world.setBlock(x, y - 1, z, Block.sand.blockID);
        }
    }

    public boolean isSameSapling(World world, int x, int y, int z, int metadata) {
        return world.getBlockId(x, y, z) == blockID
                && unmarkedMetadata(world.getBlockMetadata(x, y, z)) == metadata;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, rand);
            attemptGrowTree(world, x, y, z, rand);
        }
    }
}
