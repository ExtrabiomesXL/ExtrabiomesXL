/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

import static extrabiomes.plugin.flora.GrassType.DEAD;
import static extrabiomes.plugin.flora.GrassType.DEAD_TALL;
import static extrabiomes.plugin.flora.GrassType.DEAD_YELLOW;

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
import extrabiomes.api.BiomeManager;

public class BlockCustomTallGrass extends BlockFlower implements
		IShearable
{

	public BlockCustomTallGrass(int id) {
		super(id, 48, Material.vine);
		final float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3,
				0.8F, 0.5F + var3);
		setHardness(0F);
		setStepSound(soundGrassFootstep);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabDeco);
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id) {
		return (byte) id == BiomeManager.mountainridge.get().topBlock
				|| (byte) id == BiomeManager.wasteland.get().topBlock
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
	public ArrayList<ItemStack> getBlockDropped(World world, int x,
			int y, int z, int metadata, int fortune)
	{
		final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int rarity = 8;
		if (metadata == DEAD.metadata()
				|| metadata == DEAD_TALL.metadata()
				|| metadata == DEAD_YELLOW.metadata()) rarity *= 2;
		if (world.rand.nextInt(rarity) != 0) return ret;

		final Optional<ItemStack> item = Flora.proxy
				.getGrassSeed(world);

		if (item.isPresent()) ret.add(item.get());
		return ret;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return super.getBlockTextureFromSideAndMetadata(side, metadata)
				+ metadata;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
		if (tab == CreativeTabs.tabDeco)
			for (final GrassType type : GrassType.values())
				itemList.add(new ItemStack(this, 1, type.metadata()));
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
