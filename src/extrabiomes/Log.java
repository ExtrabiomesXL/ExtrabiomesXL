package extrabiomes;

import net.minecraft.src.ModLoader;

public final class Log {

	public static void write(String message) {
		ModLoader.getLogger().fine("Extrabiomes XL: " + message);
		System.out.println("Extrabiomes XL: " + message);
	}

}
