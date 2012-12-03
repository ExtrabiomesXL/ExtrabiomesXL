/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.IShearable;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.BiomeManager;

@SuppressWarnings("deprecation")
public class BlockCustomTallGrass extends BlockFlower implements IShearable {
    public enum BlockType {
        BROWN(0), SHORT_BROWN(1), DEAD(2), DEAD_TALL(3), DEAD_YELLOW(4);

        private final int metadata;

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        public int metadata() {
            return metadata;
        }
    }

    public BlockCustomTallGrass(int id, int index, Material material) {
        super(id, index, material);
        final float var3 = 0.4F;
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }

    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int id) {

        return BiomeManager.mountainridge.isPresent()
                && (byte) id == BiomeManager.mountainridge.get().topBlock
                || BiomeManager.wasteland.isPresent()
                && (byte) id == BiomeManager.wasteland.get().topBlock
                || super.canThisPlantGrowOnThisBlockID(id);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        final double temperature = 0.5D;
        final double hunmidity = 1.0D;
        return ColorizerGrass.getGrassColor(temperature, hunmidity);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata,
            int fortune)
    {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int rarity = 8;
        if (metadata == BlockType.DEAD.metadata() || metadata == BlockType.DEAD_TALL.metadata()
                || metadata == BlockType.DEAD_YELLOW.metadata()) rarity *= 2;
        if (world.rand.nextInt(rarity) != 0) return ret;

        final Optional<ItemStack> item = Extrabiomes.proxy.getGrassSeed(world);

        if (item.isPresent()) ret.add(item.get());
        return ret;
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
        return super.getBlockTextureFromSideAndMetadata(side, metadata) + metadata;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
        if (tab == Extrabiomes.tabsEBXL) for (final BlockType type : BlockType.values())
            itemList.add(new ItemStack(this, 1, type.metadata()));
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z,
            int fortune)
    {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }

    @Override
    public int quantityDroppedWithBonus(int i, Random rand) {
        return 1 + rand.nextInt(i * 2 + 1);
    }
}
