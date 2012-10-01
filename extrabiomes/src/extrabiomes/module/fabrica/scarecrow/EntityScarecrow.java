/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import net.minecraft.src.Block;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.World;

public class EntityScarecrow extends EntityCreature {
	public EntityScarecrow(World world) {
		super(world);
		texture = "/extrabiomes/scarecrow.png";
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
		dropItem(Item.stick.shiftedIndex, 3);
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