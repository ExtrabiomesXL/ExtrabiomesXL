/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;

import net.minecraftforge.common.config.Property;
import extrabiomes.utility.EnhancedConfiguration;

public enum ModuleControlSettings
{
    SUMMA, CAUTIA, FABRICA, AMICA;
    
    private static final String CATEGORY_MODULE_CONTROL = "module_control";
    
    private boolean             enabled;
    
    private String commentLangKey()
    {
        if (toString() == "amica")
        {
            return "Set amica to true to enable support for other mods.";
        }
        else if (toString() == "cautia")
        {
            return "Set cautia to true to add danger.";
        }
        else if (toString() == "fabrica")
        {
            return "Set fabrica to true to enable craftable items.";
        }
        else if (toString() == "summa")
        {
            return "Set summa to false to disable the mod.";
        }
        else
        {
            return "Oops Unknown Module";
        }
        
        //return "config." + toString() + ".comment";
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    private String keyEnabled()
    {
        return toString() + ".enabled";
    }
    
    public void load(EnhancedConfiguration configuration)
    {
        //final Property property = configuration.get(CATEGORY_MODULE_CONTROL, keyEnabled(), true, Extrabiomes.proxy.getStringLocalization(commentLangKey()));
        final Property property = configuration.get(CATEGORY_MODULE_CONTROL, keyEnabled(), true, commentLangKey());
        enabled = property.getBoolean(false);
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
