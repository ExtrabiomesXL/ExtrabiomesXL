/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.google.common.base.Optional;

import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.DecorationSettings;
import extrabiomes.lib.GeneralSettings;
import extrabiomes.lib.ItemSettings;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.lib.Reference;
import extrabiomes.lib.SaplingSettings;
import extrabiomes.module.amica.newdawn.NewDawnSettings;
import extrabiomes.utility.EnhancedConfiguration;

/**
 * Loads configuration data
 * 
 */
public abstract class ConfigurationHandler
{
	public static void init(File configFile)
	{
		init(configFile, false);
	}

	public static EnhancedConfiguration init(File configFile, boolean upgradeOverride)
    {
		if (upgradeOverride) {
			LogHelper.info("Overriding upgrade preference");
		}
        Optional<EnhancedConfiguration> optionalConfig = Optional.absent();
        
        try
        {
            optionalConfig = Optional.of(new EnhancedConfiguration(configFile));
            final EnhancedConfiguration configuration = optionalConfig.get();
            
			// version section
            Property configVersion;
			if (configuration.hasKey(EnhancedConfiguration.CATEGORY_VERSION, "configFileVersoin")) {
				// handle legacy typoed option
				configVersion = configuration.get(EnhancedConfiguration.CATEGORY_VERSION, "configFileVersoin", "");
			} else {
				configVersion = configuration.get(EnhancedConfiguration.CATEGORY_VERSION, "configFileVersion", "");
			}
			configVersion.comment = "To help ebxl in updating the config file in the future.";

			// for future use - are we upgrading between config versions?
			final boolean isNewVersion = !configVersion.getString().equals(Reference.CONFIG_VERSION);
			configVersion.set(Reference.CONFIG_VERSION);

			Property upgradeProp = configuration.get("version", "upgrade", upgradeOverride);
			upgradeProp.comment = "Should new (game changing) features be automatically enabled?";
			boolean autoUpgrade = upgradeProp.getBoolean(false);

			// load general config settings

            for (final BiomeSettings setting : BiomeSettings.values())
            {
                setting.load(configuration);
            }
            
            for (final DecorationSettings setting : DecorationSettings.values())
            {
                setting.load(configuration);
            }
            
            for (final BlockSettings setting : BlockSettings.values())
            {
                setting.load(configuration, upgradeOverride);
            }
            
            for (final ItemSettings setting : ItemSettings.values())
            {
                setting.load(configuration, upgradeOverride);
            }
            
            configuration.addCustomCategoryComment("saplingreplanting", "Settings to configure the chance that saplings will replant themselves up despawning on valid soil.");
            for (final SaplingSettings setting : SaplingSettings.values())
            {
                setting.load(configuration);
            }
            
            for (final ModuleControlSettings setting : ModuleControlSettings.values())
            {
                setting.load(configuration);
            }
            
            configuration.addCustomCategoryComment(EnhancedConfiguration.CATEGORY_NEWDAWN, "New Dawn biome size hints.");
            for (final NewDawnSettings setting : NewDawnSettings.values())
            {
                setting.load(configuration);
            }
            
            Property bigTreeSaplingDropRateProperty = configuration.get(Configuration.CATEGORY_GENERAL, "Relative sapling drops", GeneralSettings.bigTreeSaplingDropModifier);
            bigTreeSaplingDropRateProperty.comment = "Setting relative sapling drops to true will decrease the amount of saplings dropped by decaying fir and redwood leaf blocks to a more reasonable amount.";
            GeneralSettings.bigTreeSaplingDropModifier = bigTreeSaplingDropRateProperty.getBoolean(false);
            
            //
            Property consoleCommandsDisabled = configuration.get(Configuration.CATEGORY_GENERAL, "DisableConsoleCommands", GeneralSettings.consoleCommandsDisabled);
            consoleCommandsDisabled.comment = "Set to false to enable console commands.";
            GeneralSettings.consoleCommandsDisabled = consoleCommandsDisabled.getBoolean(true);

            Property useLegacyRedwoods = configuration.get(Configuration.CATEGORY_GENERAL, "UseLegacyRedwoods", autoUpgrade ? true : GeneralSettings.useLegacyRedwoods);
            useLegacyRedwoods.comment = "Set to true to enable old redwood tree generation.";
            GeneralSettings.useLegacyRedwoods = useLegacyRedwoods.getBoolean(false);

            Property useMC18Doors = configuration.get(Configuration.CATEGORY_GENERAL, "UseMC18Doors", GeneralSettings.useMC18Doors);
            useMC18Doors.comment = "Allow EbXL doors to stack like in MC 1.8 and be crafted in stacks of 3.";
            GeneralSettings.useMC18Doors = useMC18Doors.getBoolean(true);
            
            //GeneralSettings.consoleCommandsDisabled = consoleCommandsDisabled.getBoolean(true);
            
        }
        catch (final Exception e)
        {
            LogHelper.severe("%s had had a problem loading its configuration", Reference.MOD_NAME);
        }
        finally
        {
            if (optionalConfig.isPresent()) {
            	final EnhancedConfiguration config = optionalConfig.get();
            	config.save();
            	return config;
            }
        }
        return null;
    }
}
