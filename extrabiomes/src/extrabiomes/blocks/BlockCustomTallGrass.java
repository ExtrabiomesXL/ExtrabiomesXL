/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.TerrainGenManager;

public class BlockCustomTallGrass extends BlockFlower implements
		IShearable
{

	public static final int	metaBrown		= 0;

	public static final int	metaShortBrown	= 1;
	public static final int	metaDead		= 2;
	public static final int	metaDeadTall	= 3;
	public static final int	metaDeadYellow	= 4;

	public BlockCustomTallGrass(int id) {
		super(id, 48, Material.vine);
		final float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3,
				0.8F, 0.5F + var3);
		setHardness(0F);
		setStepSound(soundGrassFootstep);
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this, 1, metaBrown));
		itemList.add(new ItemStack(this, 1, metaShortBrown));
		itemList.add(new ItemStack(this, 1, metaDead));
		itemList.add(new ItemStack(this, 1, metaDeadTall));
		itemList.add(new ItemStack(this, 1, metaDeadYellow));
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		final int metaGrass = world.getBlockMetadata(x, y, z);
		final int blockUnder = world.getBlockId(x, y - 1, z);

		return (metaGrass == metaBrown || metaGrass == metaShortBrown)
				&& blockUnder == TerrainGenManager.blockMountainRidge.blockID
				|| (metaGrass == metaDead || metaGrass == metaDeadTall || metaGrass == metaDeadYellow)
				&& blockUnder == BiomeManager.wasteland.get().topBlock
				|| super.canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		final int blockUnder = world.getBlockId(x, y, z);
		return blockUnder == TerrainGenManager.blockMountainRidge.blockID
				|| blockUnder == BiomeManager.wasteland.get().topBlock
				|| super.canPlaceBlockAt(world, x, y, z);
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id) {
		return id == TerrainGenManager.blockMountainRidge.blockID
				|| id == BiomeManager.wasteland.get().topBlock
				|| super.canThisPlantGrowOnThisBlockID(id);
	}

	@Override
	public int getBlockColor() {
		final double temperature = 0.5D;
		final double hunmidity = 1.0D;
		return ColorizerGrass.getGrassColor(temperature, hunmidity);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x,
			int y, int z, int metadata, int fortune)
	{
		final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int rarity = 8;
		if (metadata == BlockCustomTallGrass.metaDead
				|| metadata == BlockCustomTallGrass.metaDeadTall
				|| metadata == BlockCustomTallGrass.metaDeadYellow)
			rarity *= 2;
		if (world.rand.nextInt(rarity) != 0) return ret;

		final ItemStack item = ForgeHooks.getGrassSeed(world);

		if (item != null) ret.add(item);
		return ret;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return super.getBlockTextureFromSideAndMetadata(side, metadata)
				+ metadata;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x,
			int y, int z, int metadata)
	{
		super.harvestBlock(world, player, x, y, z, metadata);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x,
			int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world,
			int x, int y, int z, int fortune)
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
