
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import forge.ITextureProvider;

public class BlockLeafPile extends Block implements ITextureProvider {
	private static boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == Block.GRASS.id || i == Block.DIRT.id;
	}

	public BlockLeafPile(int i) {
		super(i, 64, Material.REPLACEABLE_PLANT);
		final float f = 0.5F;
		final float f1 = 0.015625F;
		a(true);
		a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		a(g);
		Block.setBurnProperties(i, 30, 60);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines
	 * whether or not to render the shared face of two adjacent blocks
	 * and also whether the player can attach torches, redstone wire,
	 * etc to this block.
	 */
	public boolean a() {
		return false;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void a(World world, int i, int j, int k, Random random) {
		checkFlowerChange(world, i, j, k);
	}

	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this));
	}

	/**
	 * If this block doesn't render as an ordinary block it will return
	 * False (examples: signs, buttons, stairs, etc)
	 */
	public boolean b() {
		return false;
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	public boolean canPlace(World world, int i, int j, int k) {
		return super.canPlace(world, i, j, k)
				&& canThisPlantGrowOnThisBlockID(world.getTypeId(i,
						j - 1, k));
	}

	private void checkFlowerChange(World world, int i, int j, int k) {
		if (!f(world, i, j, k)) {
			b(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, 0);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't
	 * know which neighbor changed (coordinates passed are their own)
	 * Args: x, y, z, neighbor blockID
	 */
	public void doPhysics(World world, int i, int j, int k, int l) {
		super.doPhysics(world, i, j, k, l);
		checkFlowerChange(world, i, j, k);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this
	 * means this box can change after the pool has been cleared to be
	 * reused)
	 */
	public AxisAlignedBB e(World world, int i, int j, int k) {
		return null;
	}

	/**
	 * Can this block stay at this position. Similar to canPlaceBlockAt
	 * except gets checked often with plants.
	 */
	public boolean f(World world, int i, int j, int k) {
		return canThisPlantGrowOnThisBlockID(world.getTypeId(i, j - 1,
				k));
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	public boolean isBlockReplaceable(World world, int i, int j, int k)
	{
		return true;
	}
}
