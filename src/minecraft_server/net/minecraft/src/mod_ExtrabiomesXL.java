package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.forge.ForgeHooks;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import extrabiomes.Extrabiomes;
import extrabiomes.Log;

public class mod_ExtrabiomesXL extends NetworkMod {

	@Override
	public int addFuel(int id, int damage) {
		return Extrabiomes.addFuel(id, damage);
	}

	@Override
	public boolean clientSideRequired() {
		return Extrabiomes.clientSideRequired();
	}

	@Override
	public void generateSurface(World world, Random random, int chunkX,
			int chunkZ) {
		Extrabiomes.generateSurface(world, random, chunkX, chunkZ);
	}

	@Override
	public String getName() {
		return Extrabiomes.getName();
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
		MinecraftForge.versionDetect(getName(), 3, 2, 5);
		if (ForgeHooks.getBuildVersion() < 126)
			Log.write("IMPORTANT: Due to FML bugs, you must use Minecraft Forge build 126 or later.");
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
