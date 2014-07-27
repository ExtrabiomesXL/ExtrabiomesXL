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

@SuppressWarnings("unused")
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

    WALL;
    
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
    
    private String idKey()
    {
        return toString() + ".id";
    }
    
    private boolean isQuarterLog()
    {
        return this == QUARTERLOG0 || this == QUARTERLOG1 || this == QUARTERLOG2 || this == QUARTERLOG3;
    }
    
    public void load(EnhancedConfiguration configuration, boolean update)
    {
    	// TODO: actually rewrite config loading
    	/*
        Property property;
        switch (this)
        {
            case CRACKEDSAND:
            case REDROCK:
            	if(update) {
            		property = configuration.get(Configuration.CATEGORY_BLOCK, idKey(), blockID);
            	} else {
            		property = configuration.getTerrainBlock(Configuration.CATEGORY_BLOCK, idKey(), blockID, String.format("%s is used in terrain generation. Its id must be less than 256.", toString()));
            	}
                break;
            default:
            	if(update) {
            		property = configuration.get(Configuration.CATEGORY_BLOCK, idKey(), blockID);
            	} else {
            		property = configuration.getBlock(idKey(), blockID);
            	}
        }
        
        blockID = property.getInt(0);
        
        if (isQuarterLog())
        {
            if (blockID == 0 && !clearedQuarterLogs)
            {
                final BlockSettings[] settings = { QUARTERLOG0, QUARTERLOG1, QUARTERLOG2, QUARTERLOG3 };
                
                for (final BlockSettings setting : settings)
                {
                    setting.setToZero(configuration);
                }
                
                clearedQuarterLogs = true;
            }
        }
        
        if (this == PLANKS)
        {
            if (blockID == 0)
            {
                final BlockSettings[] settings = { WOODSLAB, DOUBLEWOODSLAB, FIRSTAIRS, REDWOODSTAIRS, ACACIASTAIRS };
                
                for (final BlockSettings setting : settings)
                {
                    setting.setToZero(configuration);
                }
            }
        }
        
        if (this == REDROCK)
        {
            if (blockID == 0)
            {
                final BlockSettings[] settings = { REDROCKSLAB, WALL };
                
                for (final BlockSettings setting : settings)
                {
                    setting.setToZero(configuration);
                }
            }
        }
        
        if (this == WOODSLAB || this == DOUBLEWOODSLAB)
        {
            if (blockID == 0 && !clearedWoodSlabs)
            {
                final BlockSettings[] settings = { WOODSLAB, DOUBLEWOODSLAB };
                
                for (final BlockSettings setting : settings)
                {
                    setting.setToZero(configuration);
                }
                
                clearedWoodSlabs = true;
            }
        }
        
        if (this == REDROCKSLAB || this == DOUBLEREDROCKSLAB)
        {
            if (blockID == 0 && !clearedWoodSlabs)
            {
                final BlockSettings[] settings = { REDROCKSLAB, DOUBLEREDROCKSLAB };
                
                for (final BlockSettings setting : settings)
                {
                    setting.setToZero(configuration);
                }
                
                clearedWoodSlabs = true;
            }
        }
        */
    }
    
    /*
    private void setToZero(EnhancedConfiguration configuration)
    {	
        final Property property = configuration.getBlock(idKey(), 0);
        property.set(Integer.toString(0));
        blockID = 0;
    }
    */
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
