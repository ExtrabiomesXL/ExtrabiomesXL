/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityScarecrow extends EntityCreature {
	public EntityScarecrow(World world) {
		super(world);
		texture = "/mods/ExtrabiomesXL/textures/models/scarecrow.png";
		preventEntitySpawning = true;
		tasks.addTask(7, new EntityAIWatchClosest(this,
				EntityPlayer.class, 50.0F));
		tasks.addTask(7, new EntityAIWatchClosest(this,
				EntityCreature.class, 50.0F));
		tasks.addTask(7, new EntityAIWatchClosest(this,
				EntityMob.class, 50.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected int decreaseAirSupply(int par1) {
		return par1;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		dropItem(Item.stick.itemID, 3);
		dropItem(Block.melon.blockID, 1);
		dropItem(Block.pumpkin.blockID, 1);
	}

	@Override
	protected String getDeathSound() {
		return null;
	}

	@Override
	protected String getHurtSound() {
		return null;
	}

	@Override
	protected String getLivingSound() {
		return null;
	}

	@Override
	public int getMaxHealth() {
		return 5;
	}

	@Override
	protected float getSoundVolume() {
		return 0.0F;
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}
}