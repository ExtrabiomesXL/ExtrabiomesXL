/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.DecorationSettings;
import extrabiomes.lib.GeneralSettings;
import extrabiomes.lib.ItemSettings;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.lib.Reference;
import extrabiomes.utility.EnhancedConfiguration;

/**
 * Loads configuration data
 * 
 */
public abstract class ConfigurationHandler {

    public static void init(File configFile) {
        Optional<EnhancedConfiguration> optionalConfig = Optional.absent();

        try {
            optionalConfig = Optional.of(new EnhancedConfiguration(configFile));
            final EnhancedConfiguration configuration = optionalConfig.get();

            for (final BiomeSettings setting : BiomeSettings.values())
                setting.load(configuration);
            
            for (final DecorationSettings setting : DecorationSettings.values())
            	setting.load(configuration);

            for (final BlockSettings setting : BlockSettings.values())
                setting.load(configuration);

            for (final ItemSettings setting : ItemSettings.values())
                setting.load(configuration);

            for (final ModuleControlSettings setting : ModuleControlSettings.values())
                setting.load(configuration);
            
            Property bigTreeSaplingDropRateProperty = configuration.get(Configuration.CATEGORY_GENERAL, "Big tree sapling drop rate", 80);
            bigTreeSaplingDropRateProperty.comment = "This controls the chance of a sapling dropping when a leaf block decays. A larger number reduces the number of saplings dropped.";
            GeneralSettings.bigTreeSaplingDropModifier = bigTreeSaplingDropRateProperty.getInt(80);
            
        } catch (final Exception e) {
            LogHelper.log(Level.SEVERE, e, "%s had had a problem loading its configuration",
                    Reference.MOD_NAME);
        } finally {
            if (optionalConfig.isPresent()) optionalConfig.get().save();
        }
    }
}
