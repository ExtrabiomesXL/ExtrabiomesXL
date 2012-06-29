
package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockFlower;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import extrabiomes.api.TerrainGenManager;
import forge.ITextureProvider;

public class BlockCatTail extends BlockFlower implements
		ITextureProvider
{
	public BlockCatTail(int i) {
		super(i, 79, Material.PLANT);
		c(0.0F);
		a(Block.g);
		s();
		final float f = 0.375F;
		a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		TerrainGenManager.enableCattailGen = true;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines
	 * whether or not to render the shared face of two adjacent blocks
	 * and also whether the player can attach torches, redstone wire,
	 * etc to this block.
	 */
	@Override
	public boolean a() {
		return false;
	}

	@Override
	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this));
	}

	/**
	 * If this block doesn't render as an ordinary block it will return
	 * False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean b() {
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int c() {
		return 6;
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlace(World world, int i, int j, int k) {
		final int l = world.getTypeId(i, j - 1, k);

		if (l != Block.GRASS.id && l != Block.DIRT.id) return false;

		if (world.getMaterial(i - 1, j - 1, k) == Material.WATER)
			return true;

		if (world.getMaterial(i + 1, j - 1, k) == Material.WATER)
			return true;

		if (world.getMaterial(i, j - 1, k - 1) == Material.WATER)
			return true;
		else
			return world.getMaterial(i, j - 1, k + 1) == Material.WATER;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't
	 * know which neighbor changed (coordinates passed are their own)
	 * Args: x, y, z, neighbor blockID
	 */
	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
		if (!f(world, i, j, k)) {
			b(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, 0);
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this
	 * means this box can change after the pool has been cleared to be
	 * reused)
	 */
	@Override
	public AxisAlignedBB e(World world, int i, int j, int k) {
		return null;
	}

	/**
	 * Can this block stay at this position. Similar to canPlaceBlockAt
	 * except gets checked often with plants.
	 */
	@Override
	public boolean f(World world, int i, int j, int k) {
		return canPlace(world, i, j, k);
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}
}
