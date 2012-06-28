package extrabiomes;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class Proxy {

	public static void addBiome(BiomeGenBase biome) {
		if (biome != null)
			ModLoader.addBiome(biome);
	}

	public static void addName(Object object, String string) {
		if (object != null)
			ModLoader.addName(object, string);
	}

	public static void addRecipe(ItemStack itemStack, Object[] objects) {
		ModLoader.addRecipe(itemStack, objects);
	}

	public static void addShapelessRecipe(ItemStack itemStack, Object[] objects) {
		ModLoader.addShapelessRecipe(itemStack, objects);
	}

	public static File getExtrabiomesRoot() {
		return Minecraft.getMinecraftDir();
	}

	public static int getUniqueEntityId() {
		return ModLoader.getUniqueEntityId();
	}

	public static void registerBlock(Block block) {
		if (block != null)
			ModLoader.registerBlock(block);
	}

	public static void registerBlock(Block block, Class type) {
		ModLoader.registerBlock(block, type);
	}

	public static void registerEntityID(Class class1, String name, int entityID) {
		ModLoader.registerEntityID(class1, name, entityID);
	}

	public static void removeBiome(BiomeGenBase biome) {
		if (biome != null)
			ModLoader.removeBiome(biome);
	}
}
