/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import net.minecraftforge.common.Property;
import extrabiomes.Extrabiomes;
import extrabiomes.core.utility.EnhancedConfiguration;

public enum ModuleControlSettings {
    SUMMA, CAUTIA, FABRICA, AMICA;

    private static final String CATEGORY_MODULE_CONTROL = "module_control";

    private boolean             enabled;

    private String commentLangKey() {
        return "config." + toString() + ".comment";
    }

    public boolean isEnabled() {
        return enabled;
    }

    private String keyEnabled() {
        return toString() + ".enabled";
    }

    public void load(EnhancedConfiguration configuration) {
        final Property property = configuration.get(CATEGORY_MODULE_CONTROL, keyEnabled(), true, Extrabiomes.proxy.getStringLocalization(commentLangKey()));
        enabled = property.getBoolean(false);
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
