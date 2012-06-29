
package extrabiomes;

import net.minecraft.server.Block;
import net.minecraft.server.EntityCreature;
import net.minecraft.server.IAnimal;
import net.minecraft.server.Item;
import net.minecraft.server.PathfinderGoalLookAtPlayer;
import net.minecraft.server.PathfinderGoalRandomLookaround;
import net.minecraft.server.World;

public class EntityScarecrow extends EntityCreature implements IAnimal {
	public EntityScarecrow(World world) {
		super(world);
		texture = "/extrabiomes/scarecrow.png";
		bf = true;
		goalSelector.a(7, new PathfinderGoalLookAtPlayer(this,
				net.minecraft.server.EntityHuman.class, 50F));
		goalSelector.a(7, new PathfinderGoalLookAtPlayer(this,
				net.minecraft.server.EntityCreature.class, 50F));
		goalSelector.a(7, new PathfinderGoalLookAtPlayer(this,
				net.minecraft.server.EntityMonster.class, 50F));
		goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
	}

	/**
	 * Decrements the entity's air supply when underwater
	 */
	@Override
	protected int b_(int i) {
		return i;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	public boolean c_() {
		return true;
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropDeathLoot(boolean flag, int i) {
		b(Item.STICK.id, 3);
		b(Block.MELON.id, 1);
		b(Block.PUMPKIN.id, 1);
	}

	/**
	 * Returns true if this entity should push and be pushed by other
	 * entities when colliding.
	 */
	@Override
	public boolean e_() {
		return false;
	}

	@Override
	public int getMaxHealth() {
		return 5;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String i() {
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String j() {
		return null;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String k() {
		return null;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away
	 * entities
	 */
	@Override
	protected boolean n() {
		return false;
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float p() {
		return 0.0F;
	}
}
