/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.plugin.scarecrow;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.utility.EnhancedConfiguration;

@Mod(modid = "EBXLScarecrow", name = "ExtrabiomesXL Scarecrow Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Scarecrow {

	private static final String				SCARECROW_NAME	= "scarecrow";
	@SidedProxy(clientSide = "extrabiomes.plugin.scarecrow.ScarecrowClientProxy", serverSide = "extrabiomes.plugin.scarecrow.ScarecrowProxy")
	public static ScarecrowProxy			proxy;
	@Instance("EBXLScarecrow")
	public static Scarecrow					instance;

	private static int						scarecrowID		= 0;
	private static Optional<? extends Item>	scarecrow		= Optional
																	.absent();

	private static int						scarecrowEntityID;

	public static boolean isEnabled() {
		return 0 < scarecrowID;
	}

	@Init
	public void init(FMLInitializationEvent event) {
		if (isEnabled()) {
			proxy.registerRenderInformation();

			scarecrow = Optional.of(new ItemScarecrow(scarecrowID)
					.setItemName(SCARECROW_NAME).setIconIndex(96));

			final IRecipe recipe = new ShapedOreRecipe(scarecrow.get(),
					new String[] { " p ", "sms", " s " }, 'p',
					Block.pumpkin, 'm', Block.melon, 's', Item.stick);
			proxy.addRecipe(recipe);
			ExtrabiomesLog.info("Added recipe for scarecrow.");

			proxy.addName(scarecrow.get(), "Scarecrow");

			scarecrowEntityID = proxy.findGlobalUniqueEntityId();
			proxy.registerEntityID(EntityScarecrow.class,
					SCARECROW_NAME, scarecrowEntityID);
			proxy.registerEntity(EntityScarecrow.class, SCARECROW_NAME,
					this, scarecrowEntityID, 300, 2, true);
		}
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final Configuration cfg = new EnhancedConfiguration(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			scarecrowID = cfg.getOrCreateIntProperty("scarecrow.id",
					Configuration.CATEGORY_ITEM, 12870).getInt(0);

			if (!isEnabled())
				ExtrabiomesLog
						.info("scarecrow.id = 0, so scarecrow has been disabled.");

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"EBXL Scarecrow had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}
}
