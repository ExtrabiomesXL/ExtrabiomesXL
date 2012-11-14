/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Facing;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;

public class ItemScarecrow extends Item {

	public static String NAME = "extrabiomes.scarecrow";
	
	private static boolean spawnCreature(World world, double x,
			double y, double z)
	{
		{
			final Optional<Entity> entity = Optional
					.fromNullable(EntityList.createEntityByName(
							NAME, world));

			if (entity.isPresent()) {
				entity.get().setLocationAndAngles(x, y, z,
						world.rand.nextFloat() * 360.0F, 0.0F);
				world.spawnEntityInWorld(entity.get());
			}

			return entity.isPresent();
		}
	}

	public ItemScarecrow(int id) {
		super(id);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(Extrabiomes.extrabiomesTab);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float xOffset,
			float yOffset, float zOffset)
	{
		if (world.isRemote)
			return true;
		else {
			final int targetBlockId = world.getBlockId(x, y, z);
			x += Facing.offsetsXForSide[side];
			y += Facing.offsetsYForSide[side];
			z += Facing.offsetsZForSide[side];
			double yOffsetForFence = 0.0D;

			if (side == 1 && targetBlockId == Block.fence.blockID
					|| targetBlockId == Block.netherFence.blockID)
				yOffsetForFence = 0.5D;

			if (spawnCreature(world, x + 0.5D, y + yOffsetForFence,
					z + 0.5D) && !player.capabilities.isCreativeMode)
				--itemStack.stackSize;

			return true;
		}
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
}