package extrabiomes;

import net.minecraft.src.ModLoader;

public final class Log {

	public static void write(String message) {
		ModLoader.getLogger().finer(message);
		System.out.println("Extrabiomes XL: " + message);
	}

}
