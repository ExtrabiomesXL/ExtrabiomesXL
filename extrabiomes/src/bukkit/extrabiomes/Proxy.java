
package extrabiomes;

import java.io.File;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;
import forge.MinecraftForge;
import forge.NetworkMod;

public class Proxy {
	public static void addBiome(BiomeBase biomebase) {
		if (biomebase != null) ModLoader.addBiome(biomebase);
	}

	public static void addName(Object obj, String s) {
		if (obj != null) ModLoader.addName(obj, s);
	}

	public static void addRecipe(ItemStack itemstack, Object aobj[]) {
		ModLoader.addRecipe(itemstack, aobj);
	}

	public static void addShapelessRecipe(ItemStack itemstack,
			Object aobj[])
	{
		ModLoader.addShapelessRecipe(itemstack, aobj);
	}

	public static File getExtrabiomesRoot() {
		return new File("./");
	}

	public static int getUniqueEntityId() {
		return ModLoader.getUniqueEntityId();
	}

	public static void registerBlock(Block block) {
		if (block != null) ModLoader.registerBlock(block);
	}

	public static void registerBlock(Block block, Class class1) {
		if (block != null) ModLoader.registerBlock(block, class1);
	}

	public static void registerEntity(Class class1,
			NetworkMod networkmod, int i)
	{
		MinecraftForge.registerEntity(class1, networkmod, i, 300, 2,
				true);
	}

	public static void registerEntityID(Class class1, String s, int i) {
		ModLoader.registerEntityID(class1, s, i);
	}

	public static void removeBiome(BiomeBase biomebase) {
		if (biomebase != null) ModLoader.removeBiome(biomebase);
	}

	public Proxy() {}
}
