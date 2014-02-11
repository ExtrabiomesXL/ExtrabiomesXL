/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import extrabiomes.utility.EnhancedConfiguration;

public enum BlockSettings
{
    // @formatter:off
    REDROCK             	(254),
    CRACKEDSAND         	(255),
    QUICKSAND           	(2300),

    FLOWER              	(2310),
    CATTAIL             	(2311),
    GRASS               	(2312),
    LEAFPILE            	(2313),
    FLOWER2					(2314),
    FLOWER3					(2315),
    GLORIOSA				(2316),
    SPANISH_MOSS			(0),
    
    SAPLING             	(2320),
    NEWSAPLING          	(2321),
    STRAWBERRY				(2322),
    
    AUTUMNLEAVES        	(2330),
    GREENLEAVES         	(2331),
    NEWLEAVES		    	(2332),
    MORELEAVES				(2333),

    QUARTERLOG0         	(0),
    QUARTERLOG1         	(0),
    QUARTERLOG2         	(0),
    QUARTERLOG3         	(0),
    
    CUSTOMLOG           	(2340),
    NEWLOG					(2341),

    KNEELOG		        	(2345),
    RAINBOWKNEELOG      	(2346),
    RAINBOWQUARTERLOG   	(2347),
    
    NEWQUARTERLOG       	(2350),
    FIRQUARTERLOG			(2351),
    REDWOODQUARTERLOG		(2352),
    OAKQUARTERLOG			(2353),

    MINILOG					(2357),
    
    PLANKS              	(2360),
   
    WOODSLAB            	(2363),
    DOUBLEWOODSLAB      	(2364),
    NEWWOODSLAB            	(2365),
    NEWDOUBLEWOODSLAB      	(2366),
    REDROCKSLAB         	(2367),

    REDCOBBLESTAIRS     	(2370),
    REDROCKBRICKSTAIRS  	(2371),
    REDWOODSTAIRS       	(2372),
    FIRSTAIRS           	(2373),
    ACACIASTAIRS        	(2374),
    DOUBLEREDROCKSLAB   	(2375),
    CYPRESSSTAIRS			(2376),
    JAPANESEMAPLESTAIRS 	(2377),
    RAINBOWEUCALYPTUSSTAIRS	(2378),
    AUTUMNSTAIRS			(2379),
    BALDCYPRESSSTAIRS		(2380),
    SAKURABLOSSOMSTAIRS		(2381),
    
	WALL					(2400),
	MACHINE					(2401); // Next block IDs 253 (decending) and 2224 (ascending)
    // @formatter:on
    
    private int            blockID;
    
    private final int      defaultID;
    
    private static boolean clearedQuarterLogs = false;
    
    static boolean         clearedWoodSlabs   = false;
    
    private BlockSettings(int defaultID)
    {
        this.defaultID = defaultID;
        blockID = this.defaultID;
    }
    
    public int getID()
    {
        return blockID;
    }
    
    private String idKey()
    {
        return toString() + ".id";
    }
    
    private boolean isQuarterLog()
    {
        return this == QUARTERLOG0 || this == QUARTERLOG1 || this == QUARTERLOG2 || this == QUARTERLOG3;
    }
    
    public void load(EnhancedConfiguration configuration)
    {
        Property property;
        switch (this)
        {
            case CRACKEDSAND:
            case REDROCK:
                property = configuration.getTerrainBlock(Configuration.CATEGORY_BLOCK, idKey(), blockID, String.format("%s is used in terrain generation. Its id must be less than 256.", toString()));
                break;
            default:
                property = configuration.getBlock(idKey(), blockID);
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
    }
    
    private void setToZero(EnhancedConfiguration configuration)
    {
        final Property property = configuration.getBlock(idKey(), 0);
        property.set(Integer.toString(0));
        blockID = 0;
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
