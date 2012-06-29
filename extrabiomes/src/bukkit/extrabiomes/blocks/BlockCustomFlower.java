
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import forge.ITextureProvider;
import forge.MinecraftForge;

public class BlockCustomFlower extends Block implements
		ITextureProvider
{
	public static final int	metaAutumnShrub	= 0;
	public static final int	metaHydrangea	= 1;
	public static final int	metaOrange		= 2;
	public static final int	metaPurple		= 3;
	public static final int	metaTinyCactus	= 4;
	public static final int	metaRoot		= 5;
	public static final int	metaToadstool	= 6;
	public static final int	metaWhite		= 7;

	public BlockCustomFlower(int i) {
		super(i, Material.PLANT);
		textureId = 32;
		a(true);
		final float f = 0.2F;
		a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3F, 0.5F + f);
		c(0.0F);
		a(Block.g);
		MinecraftForge.addGrassPlant(i, 0, 2);
		MinecraftForge.addGrassPlant(i, 1, 2);
		MinecraftForge.addGrassPlant(i, 2, 5);
		MinecraftForge.addGrassPlant(i, 3, 5);
		MinecraftForge.addGrassPlant(i, 7, 5);
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

	/**
	 * From the specified side and block metadata retrieves the blocks
	 * texture. Args: side, metadata
	 */
	@Override
	public int a(int i, int j) {
		return super.a(i, j) + j;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void a(World world, int i, int j, int k, Random random) {
		checkFlowerChange(world, i, j, k);
	}

	@Override
	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this, 1, 0));
		arraylist.add(new ItemStack(this, 1, 1));
		arraylist.add(new ItemStack(this, 1, 2));
		arraylist.add(new ItemStack(this, 1, 3));
		arraylist.add(new ItemStack(this, 1, 4));
		arraylist.add(new ItemStack(this, 1, 5));
		arraylist.add(new ItemStack(this, 1, 6));
		arraylist.add(new ItemStack(this, 1, 7));
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
		return 1;
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlace(World world, int i, int j, int k) {
		return super.canPlace(world, i, j, k)
				&& canThisPlantGrowOnThisBlockID(world.getTypeId(i,
						j - 1, k));
	}

	protected boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == Block.GRASS.id || i == Block.DIRT.id
				|| i == Block.SOIL.id;
	}

	protected final void checkFlowerChange(World world, int i, int j,
			int k)
	{
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
	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
		super.doPhysics(world, i, j, k, l);
		checkFlowerChange(world, i, j, k);
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
		return (world.m(i, j, k) >= 8 || world.isChunkLoaded(i, j, k))
				&& canThisPlantGrowOnThisBlockID(world.getTypeId(i,
						j - 1, k));
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth
	 * and wood.
	 */
	@Override
	protected int getDropData(int i) {
		return i;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}
}
