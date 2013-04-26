/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.BiomeManager;
import extrabiomes.proxy.CommonProxy;

@SuppressWarnings("deprecation")
public class BlockCustomFlower extends Block implements IPlantable {

    public enum BlockType {
        AUTUMN_SHRUB(0), HYDRANGEA(1), ORANGE(2), PURPLE(3), TINY_CACTUS(4), ROOT(5), TOADSTOOL(6), WHITE(
                7);

        private final int metadata;

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        public int metadata() {
            return metadata;
        }
    }
    
    private Icon autumnShrub;
    private Icon hydrangea;
    private Icon orange;
    private Icon purple;
    private Icon tinyCactus;
    private Icon root;
    private Icon toadstool;
    private Icon white;

    public BlockCustomFlower(int id, int index, Material material) {
        super(id, material);
        //blockIndexInTexture = index;
        final float var4 = 0.2F;
        setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.addGrassPlant(this, BlockType.AUTUMN_SHRUB.metadata(), 2);
        proxy.addGrassPlant(this, BlockType.HYDRANGEA.metadata(), 2);
        proxy.addGrassPlant(this, BlockType.ORANGE.metadata(), 5);
        proxy.addGrassPlant(this, BlockType.PURPLE.metadata(), 5);
        proxy.addGrassPlant(this, BlockType.WHITE.metadata(), 5);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		autumnShrub = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "autumnshrub");
		hydrangea = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "hydrangea");
		orange = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "orangeflowers");
		purple = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "purpleflowers");
		tinyCactus  = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "tinycactus");
		root = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "root");
		toadstool = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "toadstools");
		white = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "whiteflowers");
	}

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z))
                && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return super.canPlaceBlockAt(world, x, y, z)
                && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
    }

    private boolean canThisPlantGrowOnThisBlockID(int id) {
        return id == Block.grass.blockID || id == Block.dirt.blockID
                || id == Block.tilledField.blockID || id == Block.sand.blockID
                || (byte) id == BiomeManager.mountainridge.get().topBlock;
    }

    private void checkFlowerChange(World world, int x, int y, int z) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, 0);
        }
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        if (metadata > 7) metadata = 7;
        //return super.getBlockTextureFromSideAndMetadata(side, metadata) + metadata;
        switch(metadata){
        case 0: return autumnShrub;
        case 1: return hydrangea;
        case 2: return orange;
        case 3: return purple;
        case 4: return tinyCactus;
        case 5: return root;
        case 6: return toadstool;
        case 7: return white;
        default: return autumnShrub;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z) {
        return blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z) {
        final int metadata = world.getBlockMetadata(x, y, z);
        if (metadata == BlockType.TINY_CACTUS.metadata()) return EnumPlantType.Desert;
        return EnumPlantType.Plains;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        final int metadata = world.getBlockMetadata(x, y, z);

        if (metadata == BlockType.TINY_CACTUS.metadata())
            return super.getSelectedBoundingBoxFromPool(world, x, y, z);

        return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + maxY, z + 1);

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
        if (tab == Extrabiomes.tabsEBXL) for (final BlockType type : BlockType.values())
            itemList.add(new ItemStack(this, 1, type.metadata()));
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
        checkFlowerChange(world, x, y, z);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        checkFlowerChange(world, x, y, z);
    }
}
