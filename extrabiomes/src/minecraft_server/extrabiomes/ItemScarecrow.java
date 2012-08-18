
package extrabiomes;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Facing;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemScarecrow extends Item {

	public static boolean spawnCreature(World world, double x,
			double y, double z)
	{
		{
			final Entity entity = EntityList.createEntityByName(
					"scarecrow", world);

			if (entity != null) {
				entity.setLocationAndAngles(x, y, z,
						world.rand.nextFloat() * 360.0F, 0.0F);
				world.spawnEntityInWorld(entity);
			}

			return entity != null;
		}
	}

	public ItemScarecrow(int id) {
		super(id);
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemStack,
			EntityPlayer player, World world, int x, int y, int z,
			int side)
	{
		if (world.isRemote)
			return true;
		else {
			final int blockId = world.getBlockId(x, y, z);
			x += Facing.offsetsXForSide[side];
			y += Facing.offsetsYForSide[side];
			z += Facing.offsetsZForSide[side];
			double yOffset = 0.0D;

			if (side == 1 && blockId == Block.fence.blockID
					|| blockId == Block.netherFence.blockID)
				yOffset = 0.5D;

			if (spawnCreature(world, x + 0.5D, y + yOffset, z + 0.5D)
					&& !player.capabilities.isCreativeMode)
				--itemStack.stackSize;

			return true;
		}
	}
}