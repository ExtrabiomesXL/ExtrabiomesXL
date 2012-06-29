
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.BlockFlower;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import extrabiomes.api.TerrainGenManager;
import forge.ForgeHooks;
import forge.IShearable;
import forge.ITextureProvider;

public class BlockCustomTallGrass extends BlockFlower implements
		IShearable, ITextureProvider
{
	public static final int	metaBrown		= 0;
	public static final int	metaShortBrown	= 1;
	public static final int	metaDead		= 2;
	public static final int	metaDeadTall	= 3;
	public static final int	metaDeadYellow	= 4;

	public BlockCustomTallGrass(int i) {
		super(i, 48, Material.REPLACEABLE_PLANT);
		final float f = 0.4F;
		a(0.09999999F, 0.0F, 0.09999999F, 0.9F, 0.8F, 0.9F);
		c(0.0F);
		a(g);
	}

	/**
	 * From the specified side and block metadata retrieves the blocks
	 * texture. Args: side, metadata
	 */
	public int a(int i, int j) {
		return super.a(i, j) + j;
	}

	/**
	 * Called when the player destroys a block with an item that can
	 * harvest it. (i, j, k) are the coordinates of the block and l is
	 * the block's subtype/damage.
	 */
	public void a(World world, EntityHuman entityhuman, int i, int j,
			int k, int l)
	{
		super.a(world, entityhuman, i, j, k, l);
	}

	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this, 1, 0));
		arraylist.add(new ItemStack(this, 1, 1));
		arraylist.add(new ItemStack(this, 1, 2));
		arraylist.add(new ItemStack(this, 1, 3));
		arraylist.add(new ItemStack(this, 1, 4));
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	public boolean canPlace(World world, int i, int j, int k) {
		final int l = world.getTypeId(i, j, k);
		return l == TerrainGenManager.blockMountainRidge.id
				|| l == TerrainGenManager.blockWasteland.id
				|| super.canPlace(world, i, j, k);
	}

	/**
	 * Gets passed in the blockID of the block below and supposed to
	 * return true if its allowed to grow on the type of blockID passed
	 * in. Args: blockID
	 */
	protected boolean d(int i) {
		return i == TerrainGenManager.blockMountainRidge.id
				|| i == TerrainGenManager.blockWasteland.id
				|| super.d(i);
	}

	/**
	 * Can this block stay at this position. Similar to canPlaceBlockAt
	 * except gets checked often with plants.
	 */
	public boolean f(World world, int i, int j, int k) {
		final int l = world.getData(i, j, k);
		final int i1 = world.getTypeId(i, j - 1, k);
		return (l == 0 || l == 1)
				&& i1 == TerrainGenManager.blockMountainRidge.id
				|| (l == 2 || l == 3 || l == 4)
				&& i1 == TerrainGenManager.blockWasteland.id
				|| super.f(world, i, j, k);
	}

	public ArrayList getBlockDropped(World world, int i, int j, int k,
			int l, int i1)
	{
		final ArrayList arraylist = new ArrayList();
		int j1 = 8;

		if (l == 2 || l == 3 || l == 4) j1 *= 2;

		if (world.random.nextInt(j1) != 0) return arraylist;

		final ItemStack itemstack = ForgeHooks.getGrassSeed(world);

		if (itemstack != null) arraylist.add(itemstack);

		return arraylist;
	}

	/**
	 * Returns the usual quantity dropped by the block plus a bonus of 1
	 * to 'i' (inclusive).
	 */
	public int getDropCount(int i, Random random) {
		return 1 + random.nextInt(i * 2 + 1);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int getDropType(int i, Random random, int j) {
		return 0;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	public boolean isBlockReplaceable(World world, int i, int j, int k)
	{
		return true;
	}

	public boolean isShearable(ItemStack itemstack, World world, int i,
			int j, int k)
	{
		return true;
	}

	public ArrayList onSheared(ItemStack itemstack, World world, int i,
			int j, int k, int l)
	{
		final ArrayList arraylist = new ArrayList();
		arraylist.add(new ItemStack(this, 1, world.getData(i, j, k)));
		return arraylist;
	}
}
