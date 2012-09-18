/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.quicksand;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.src.Block;
import net.minecraftforge.common.Configuration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.CommonProxy;
import extrabiomes.ExtrabiomesLog;

@Mod(modid = "EBXLQuicksand", name = "ExtrabiomesXL Quicksand Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Quicksand {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy		proxy;
	@Instance("EBXLQuicksand")
	public static Quicksand			instance;

	private static Optional<Block>	quicksand	= Optional.absent();
	private static int				quicksandID;

	@Init
	public static void init(FMLInitializationEvent event) {
		if (isEnabled()) {
			proxy.registerRenderInformation();
			proxy.registerWorldGenerator(new WorldGenerator(quicksandID));

			quicksand = Optional.of(new BlockQuicksand(quicksandID)
					.setBlockName("quicksand"));
			proxy.setBlockHarvestLevel(quicksand.get(), "shovel", 0);

			proxy.registerBlock(quicksand);

			proxy.addName(quicksand.get(), "Quicksand");
		}
	}

	public static boolean isEnabled() {
		return 0 < quicksandID;
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final Configuration cfg = new Configuration(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			quicksandID = cfg.getOrCreateBlockIdProperty(
					"quicksand.id", 157).getInt(0);

			if (!isEnabled())
				ExtrabiomesLog
						.info("quicksand.id = 0, so quicksand has been disabled.");

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}
}
