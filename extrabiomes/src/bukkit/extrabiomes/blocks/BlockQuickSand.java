
package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.Entity;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import forge.ITextureProvider;

public class BlockQuickSand extends Block implements ITextureProvider {
	public BlockQuickSand(int i) {
		super(i, 1, Material.SAND);
		c(4F);
		a(Block.l);
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
	 * Triggered whenever an entity collides with this block (enters
	 * into the block). Args: world, x, y, z, entity
	 */
	public void a(World world, int i, int j, int k, Entity entity) {
		entity.u();
	}

	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this));
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this
	 * means this box can change after the pool has been cleared to be
	 * reused)
	 */
	public AxisAlignedBB e(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}
}
