
package extrabiomes;

import net.minecraft.server.Block;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityTypes;
import net.minecraft.server.Facing;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class ItemScarecrow extends Item {
	public static boolean spawnCreature(World world, double d,
			double d1, double d2)
	{
		final Entity entity = EntityTypes.createEntityByName(
				"scarecrow", world);

		if (entity != null) {
			entity.setPositionRotation(d, d1, d2,
					world.random.nextFloat() * 360F, 0.0F);
			world.addEntity(entity);
		}

		return entity != null;
	}

	public ItemScarecrow(int i) {
		super(i);
	}

	/**
	 * Callback for item usage. If the item does something special on
	 * right clicking, he will have one of those. Return True if
	 * something happen and false if it don't. This is for ITEMS, not
	 * BLOCKS !
	 */
	@Override
	public boolean interactWith(ItemStack itemstack,
			EntityHuman entityhuman, World world, int i, int j, int k,
			int l)
	{
		if (world.isStatic) return true;

		final int i1 = world.getTypeId(i, j, k);
		i += Facing.b[l];
		j += Facing.c[l];
		k += Facing.d[l];
		double d = 0.0D;

		if (l == 1 && i1 == Block.FENCE.id
				|| i1 == Block.NETHER_FENCE.id) d = 0.5D;

		if (spawnCreature(world, i + 0.5D, j + d, k + 0.5D)
				&& !entityhuman.abilities.canInstantlyBuild)
			itemstack.count--;

		return true;
	}
}
