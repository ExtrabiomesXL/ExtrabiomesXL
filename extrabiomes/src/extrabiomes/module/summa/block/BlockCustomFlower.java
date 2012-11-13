/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.BiomeManager;
import extrabiomes.proxy.CommonProxy;

public class BlockCustomFlower extends Block implements IPlantable {

	public enum BlockType {
		AUTUMN_SHRUB(0), HYDRANGEA(1), ORANGE(2), PURPLE(3), TINY_CACTUS(4),
		ROOT(5), TOADSTOOL(6), WHITE(7);

		private final int	metadata;

		BlockType(int metadata) {
			this.metadata = metadata;
		}

		public int metadata() {
			return metadata;
		}
	}

	public BlockCustomFlower(int id) {
		super(id, Material.plants);
		blockIndexInTexture = 32;
		setTickRandomly(true);
		final float var4 = 0.2F;
		setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4,
				var4 * 3.0F, 0.5F + var4);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);

		final CommonProxy proxy = Extrabiomes.proxy;
		proxy.addGrassPlant(this, BlockType.AUTUMN_SHRUB.metadata(), 2);
		proxy.addGrassPlant(this, BlockType.HYDRANGEA.metadata(), 2);
		proxy.addGrassPlant(this, BlockType.ORANGE.metadata(), 5);
		proxy.addGrassPlant(this, BlockType.PURPLE.metadata(), 5);
		proxy.addGrassPlant(this, BlockType.WHITE.metadata(), 5);

		setCreativeTab(Extrabiomes.extrabiomesTab);
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world
				.canBlockSeeTheSky(x, y, z))
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x,
						y - 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z)
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x,
						y - 1, z));
	}

	private boolean canThisPlantGrowOnThisBlockID(int id) {
		return id == Block.grass.blockID
				|| id == Block.dirt.blockID
				|| id == Block.tilledField.blockID
				|| id == Block.sand.blockID
				|| (byte) id == BiomeManager.mountainridge.get().topBlock;
	}

	private void checkFlowerChange(World world, int x, int y, int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return super.getBlockTextureFromSideAndMetadata(side, metadata)
				+ metadata;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,
			int x, int y, int z)
	{
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
	public EnumPlantType getPlantType(World world, int x, int y, int z)
	{
		final int metadata = world.getBlockMetadata(x, y, z);
		if (metadata == BlockType.TINY_CACTUS.metadata())
			return EnumPlantType.Desert;
		return EnumPlantType.Plains;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world,
			int x, int y, int z)
	{
		final int metadata = world.getBlockMetadata(x, y, z);

		if (metadata == BlockType.TINY_CACTUS.metadata())
			return super.getSelectedBoundingBoxFromPool(world, x, y, z);

		return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(x, y,
				z, x + 1, y + maxY, z + 1);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
		if (tab == Extrabiomes.extrabiomesTab)
			for (final BlockType type : BlockType.values())
				itemList.add(new ItemStack(this, 1, type.metadata()));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int id)
	{
		checkFlowerChange(world, x, y, z);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		checkFlowerChange(world, x, y, z);
	}
}
