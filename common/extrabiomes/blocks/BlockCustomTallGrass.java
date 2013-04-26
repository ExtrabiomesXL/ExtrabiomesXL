/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    
    private Icon[] textures = {null, null, null, null, null, null, null, null};

    public BlockCustomTallGrass(int id, int index, Material material) {
        super(id, material);
        final float var3 = 0.4F;
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegistry)
    {
    	textures[0] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "browngrasstall");
    	textures[1] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "browngrassshort");
    	textures[2] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "deadgrassshort");
    	textures[3] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "deadgrasstall");
    	textures[4] = iconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "deadgrassyellow");
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
    public Icon getIcon(int side, int metadata) {
        if (metadata > 4) metadata = 4;
        return textures[metadata];
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
