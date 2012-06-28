package net.minecraft.src;

import java.util.*;

public class EntityScarecrow extends EntityCreature
{
	public EntityScarecrow(World world)
	{
		super(world);
		texture = "/ExtraBiomesXL/scarecrow.png";
		preventEntitySpawning = true;
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityCreature.class, 50.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityMob.class, 50.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	public int getMaxHealth()
	{
		return 5;
	}

	protected String getLivingSound()
	{
		return null;
	}

	protected String getHurtSound()
	{
		return null;
	}

	protected String getDeathSound()
	{
		return null;
	}

	protected float getSoundVolume()
	{
		return 0.0F;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		dropItem(Item.stick.shiftedIndex, 3);
		dropItem(Block.melon.blockID, 1);
		dropItem(Block.pumpkin.blockID, 1);
	}

	protected boolean canDespawn()
	{
		return false;
	}
	
	protected int decreaseAirSupply(int par1)
    {
        return par1;
    }
}