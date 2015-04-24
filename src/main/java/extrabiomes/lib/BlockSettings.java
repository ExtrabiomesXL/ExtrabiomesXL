/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import extrabiomes.helpers.LogHelper;
import extrabiomes.utility.EnhancedConfiguration;

public enum BlockSettings
{
    // @formatter:off
    REDROCK,
    CRACKEDSAND,
    QUICKSAND,

    FLOWER,
    CATTAIL,
    GRASS,
    LEAFPILE,
    FLOWER2,
    FLOWER3,
    GLORIOSA,
    WATERPLANT,
    SPANISH_MOSS	(false),
    
    SAPLING,
    NEWSAPLING,
    STRAWBERRY,
    
    AUTUMNLEAVES,
    GREENLEAVES,
    NEWLEAVES,
    MORELEAVES,

    QUARTERLOG0		(false),
    QUARTERLOG1		(false),
    QUARTERLOG2		(false),
    QUARTERLOG3		(false),
    
    CUSTOMLOG,
    NEWLOG,

    KNEELOG,
    RAINBOWKNEELOG,
    RAINBOWQUARTERLOG,
    
    NEWQUARTERLOG,
    FIRQUARTERLOG,
    REDWOODQUARTERLOG,
    OAKQUARTERLOG,

    MINILOG,
    
    PLANKS,
   
    WOODSLAB,
    DOUBLEWOODSLAB,
    NEWWOODSLAB,
    NEWDOUBLEWOODSLAB,
    REDROCKSLAB,

    REDCOBBLESTAIRS,
    REDROCKBRICKSTAIRS,
    REDWOODSTAIRS,
    FIRSTAIRS,
    ACACIASTAIRS,
    DOUBLEREDROCKSLAB,
    CYPRESSSTAIRS,
    JAPANESEMAPLESTAIRS,
    RAINBOWEUCALYPTUSSTAIRS,
    AUTUMNSTAIRS,
    BALDCYPRESSSTAIRS,
    SAKURABLOSSOMSTAIRS,

    WALL,    

    REDWOODDOOR,
    FIRDOOR,
    ACACIADOOR,
    CYPRESSDOOR,
    JAPANESEMAPLEDOOR,
    RAINBOWEUCALYPTUSDOOR,
    AUTUMNDOOR,
    BALDCYPRESSDOOR,
    SAKURABLOSSOMDOOR,
    
    SAKURABLOSSOMGATE;
    
    private boolean			enabled;
    private Item			item;
    
    private static boolean	clearedQuarterLogs = false;
    private static boolean	clearedWoodSlabs   = false;
    
    private BlockSettings()
    {
    	this.enabled = true;
    }
    private BlockSettings(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    public boolean getEnabled()
    {
        return enabled;
    }
    
    public Item getItem()
    {
    	return item;
    }
    public void setItem(Item item)
    {
    	this.item = item;
    }
    
    private String enabledKey()
    {
        return toString() + ".enabled";
    }
    
    private boolean isQuarterLog()
    {
        return this == QUARTERLOG0 || this == QUARTERLOG1 || this == QUARTERLOG2 || this == QUARTERLOG3;
    }
    
    public void load(EnhancedConfiguration configuration, boolean update)
    {
    	switch( this ) {
    		// case REDROCK:
	    	case CRACKEDSAND:
	    	case QUICKSAND:
	    		final Property property = configuration.get(configuration.CATEGORY_BLOCK, enabledKey(), true);
	    		this.enabled = property.getBoolean();
	    		break;
			default:
				break;
    	}
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
