/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;

import net.minecraftforge.common.config.Configuration;
import extrabiomes.utility.EnhancedConfiguration;

public enum ItemSettings
{
	// @formatter:off
    LOGTURNER,
    SCARECROW,
    PASTE,
    DYE,
    SEED,
    CROP,
    FOOD;
    // @formatter:on
    
    private boolean enabled = true;
    
    private ItemSettings()
    {
    	this.enabled = true;
    }
    private ItemSettings(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    public boolean getEnabled()
    {
        return enabled;
    }
    
    public void load(EnhancedConfiguration configuration, boolean update) {
    /*	if(update || itemID == 0) {
    		itemID = configuration.get(Configuration.CATEGORY_ITEM, toString() + ".id", itemID).getInt(0);
    	} else {
    		itemID = configuration.getItem(toString() + ".id", itemID).getInt(0);
    	}*/
      
      this.enabled = configuration.get("item", toString() + ".enabled", this.enabled).getBoolean();
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
