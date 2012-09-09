/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

import static extrabiomes.plugin.flora.FlowerType.AUTUMN_SHRUB;
import static extrabiomes.plugin.flora.FlowerType.HYDRANGEA;
import static extrabiomes.plugin.flora.FlowerType.ORANGE;
import static extrabiomes.plugin.flora.FlowerType.PURPLE;
import static extrabiomes.plugin.flora.FlowerType.WHITE;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import extrabiomes.CommonProxy;

public class BlockCustomFlower extends Block {

	public BlockCustomFlower(int id) {
		super(id, Material.plants);
		blockIndexInTexture = 32;
		setTickRandomly(true);
		final float var4 = 0.2F;
		setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4,
				var4 * 3.0F, 0.5F + var4);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);

		final CommonProxy proxy = Flora.proxy;
		proxy.addGrassPlant(this, AUTUMN_SHRUB.metadata(), 2);
		proxy.addGrassPlant(this, HYDRANGEA.metadata(), 2);
		proxy.addGrassPlant(this, ORANGE.metadata(), 5);
		proxy.addGrassPlant(this, PURPLE.metadata(), 5);
		proxy.addGrassPlant(this, WHITE.metadata(), 5);

		setCreativeTab(CreativeTabs.tabDeco);
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		for (final FlowerType type : FlowerType.values())
			itemList.add(new ItemStack(this, 1, type.metadata()));
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
		return id == Block.grass.blockID || id == Block.dirt.blockID
				|| id == Block.tilledField.blockID;
	}

	private void checkFlowerChange(World world, int x, int y, int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	protected int damageDropped(int metadata) {
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
	public int getRenderType() {
		return 1;
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
