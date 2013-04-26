/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
	

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "scarecrow");
	}
	

	public ItemScarecrow(int id) {
		super(id);
		//setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(Extrabiomes.tabsEBXL);
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