
package extrabiomes;

import net.minecraft.server.ModLoader;

public final class Log {
	public static void write(String s) {
		ModLoader.getLogger().finer(s);
		System.out.println(new StringBuilder()
				.append("Extrabiomes XL: ").append(s).toString());
	}

	public Log() {}
}
