
package extrabiomes;

import java.io.File;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

public class Proxy {

	private static final String	DOWNLOAD_URL	= "https://raw.github.com/ScottKillen/ExtrabiomesXL/master/extrabiomes/url-server-download.txt";

	public static void addBiome(BiomeGenBase biome) {
		if (biome != null) ModLoader.addBiome(biome);
	}

	public static void addName(Object object, String string) {
		if (object != null) ModLoader.addName(object, string);
	}

	public static void addRecipe(ItemStack itemStack, Object[] objects)
	{
		ModLoader.addRecipe(itemStack, objects);
	}

	public static void addShapelessRecipe(ItemStack itemStack,
			Object[] objects)
	{
		ModLoader.addShapelessRecipe(itemStack, objects);
	}

	public static File getExtrabiomesRoot() {
		return new File("./");
	}

	public static String getModDownloadURL() {
		return DOWNLOAD_URL;
	}

	public static int getUniqueEntityId() {
		return ModLoader.getUniqueEntityId();
	}

	public static void registerBlock(Block block) {
		if (block != null) ModLoader.registerBlock(block);
	}

	public static void registerBlock(Block block, Class type) {
		if (block != null) ModLoader.registerBlock(block, type);
	}

	public static void registerEntity(Class class1, NetworkMod mod,
			int ID)
	{
		MinecraftForge.registerEntity(class1, mod, ID, 300, 2, true);
	}

	public static void registerEntityID(Class entityClass, String name,
			int id)
	{
		ModLoader.registerEntityID(entityClass, name, id);
	}

	public static void removeBiome(BiomeGenBase biome) {
		if (biome != null) ModLoader.removeBiome(biome);
	}

}
