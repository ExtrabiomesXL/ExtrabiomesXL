/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;

import extrabiomes.utility.EnhancedConfiguration;

public enum ItemSettings
{
	// @formatter:off
    LOGTURNER			(12870),
    SCARECROW			(12871),
    PASTE				(12872),
    DYE					(12873),
    SEED				(12874),
    CROP				(12875);
    // @formatter:on
    
    private final int defaultID;
    
    private int       itemID;
    
    private ItemSettings(int defaultID)
    {
        this.defaultID = defaultID;
        itemID = this.defaultID;
    }
    
    public int getID()
    {
        return itemID;
    }
    
    public void load(EnhancedConfiguration configuration)
    {
        itemID = configuration.getItem(toString() + ".id", itemID).getInt(0);
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
