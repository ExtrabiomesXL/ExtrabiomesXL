/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityScarecrow extends EntityGolem
{
    
    public EntityScarecrow(World world)
    {
        super(world);
        preventEntitySpawning = true;
        
        tasks.addTask(1, new EntityAIScareClosest(this, EntityMob.class, 50.0F));
        tasks.addTask(2, new EntityAIScareClosest(this, EntityCreature.class, 50.0F));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }
    
    @Override
    public boolean canBePushed()
    {
        return false;
    }
    
    private class EntityAIScareClosest extends EntityAIWatchClosest
    {
        
        public EntityAIScareClosest(EntityLiving entityliving, Class<? extends Entity> watchTargetClass, float maxDistance)
        {
            super(entityliving, watchTargetClass, maxDistance);
        }
        
        @Override
        public void startExecuting()
        {
            super.startExecuting();
            
            if (closestEntity instanceof EntityCreature && !(closestEntity instanceof EntityGolem))
                ((EntityCreature) closestEntity).tasks.addTask(1, new EntityAIAvoidEntity<EntityScarecrow>((EntityCreature) closestEntity, EntityScarecrow.class, 10.0F, 0.3F, 0.4F));
        }
    }
    
    @Override
    protected boolean canDespawn()
    {
        return false;
    }
    
    @Override
    protected int decreaseAirSupply(int par1)
    {
        return par1;
    }
    
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        dropItem(Items.STICK, 3);
        dropItem(Item.getItemFromBlock(Blocks.MELON_BLOCK), 1);
        dropItem(Item.getItemFromBlock(Blocks.PUMPKIN), 1);
    }
    
    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }
    
    @Override
    protected float getSoundVolume()
    {
        return 0.0F;
    }
    
    @Override
    public boolean isAIDisabled() {
    	return false;
    }
}