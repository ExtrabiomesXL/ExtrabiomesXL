package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.forge.NetworkMod;
import extrabiomes.Extrabiomes;

public class mod_ExtrabiomesXL extends NetworkMod {

	@Override
	public int addFuel(int id, int damage) {
		return Extrabiomes.addFuel(id, damage);
	}

	@Override
	public boolean clientSideRequired() {
		return true;
	}

	@Override
	public void generateSurface(World world, Random random, int chunkX,
			int chunkZ) {
		Extrabiomes.generateSurface(world, random, chunkX, chunkZ);
	}

	@Override
	public String getPriorities() {
		return Extrabiomes.getPriorities();
	}

	@Override
	public String getVersion() {
		return Extrabiomes.getVersion();
	}

	@Override
	public void load() {
		Extrabiomes.load();
	}

	@Override
	public void modsLoaded() {
		Extrabiomes.modsLoaded(this);
	}

	@Override
	public void takenFromCrafting(EntityPlayer player, ItemStack itemstack,
			IInventory var3) {
		Extrabiomes.takenFromCrafting(player, itemstack, var3);
	}
}
