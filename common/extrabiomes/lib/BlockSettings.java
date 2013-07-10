/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import extrabiomes.Extrabiomes;
import extrabiomes.utility.EnhancedConfiguration;

public enum BlockSettings {
    // @formatter:off
    AUTUMNLEAVES        (2200),
    CATTAIL             (2201),
    CRACKEDSAND         (255),
    FLOWER              (2202),
    GRASS               (2203),
    GREENLEAVES         (2204),
    LEAFPILE            (2205),
    REDROCK             (254),
    SAPLING             (2207),
    CUSTOMLOG           (2208),
    QUARTERLOG0         (2209),
    QUARTERLOG1         (2211),
    QUARTERLOG2         (2212),
    QUARTERLOG3         (2213),
    QUICKSAND           (2214),
    PLANKS              (2215),
    WOODSLAB            (2216),
    DOUBLEWOODSLAB      (2217),
    REDWOODSTAIRS       (2218),
    FIRSTAIRS           (2219),
    ACACIASTAIRS        (2220),
    REDROCKSLAB         (2206),
    DOUBLEREDROCKSLAB   (2222),
    REDCOBBLESTAIRS     (2223),
    REDROCKBRICKSTAIRS  (2221),
    WALL                (2210);
    // Next block IDs 253 (decending) and 2224 (ascending)

    // @formatter:on

    private int            blockID;

    private final int      defaultID;

    private static boolean clearedQuarterLogs = false;

    static boolean         clearedWoodSlabs   = false;

    private BlockSettings(int defaultID) {
        this.defaultID = defaultID;
        blockID = this.defaultID;
    }

    public int getID() {
        return blockID;
    }

    private String idKey() {
        return toString() + ".id";
    }

    private boolean isQuarterLog() {
        return this == QUARTERLOG0 || this == QUARTERLOG1 || this == QUARTERLOG2 || this == QUARTERLOG3;
    }

    public void load(EnhancedConfiguration configuration) {
        Property property;
        switch (this) {
            case CRACKEDSAND:
            case REDROCK:
                property = configuration.getTerrainBlock(Configuration.CATEGORY_BLOCK, idKey(), defaultID, String.format("%s is used in terrain generation. Its id must be less than 256.", toString()));
                break;
            default:
                property = configuration.getBlock(idKey(), defaultID);
        }

        blockID = property.getInt(0);

        if (isQuarterLog()) {
            if (blockID == 0 && !clearedQuarterLogs) {
                final BlockSettings[] settings = { QUARTERLOG0, QUARTERLOG1, QUARTERLOG2, QUARTERLOG3 };

                for (final BlockSettings setting : settings) {
                    setting.setToZero(configuration);
                }
                
                clearedQuarterLogs = true;
            }
        }

        if (this == PLANKS) {
            if (blockID == 0) {
                final BlockSettings[] settings = { WOODSLAB, DOUBLEWOODSLAB, FIRSTAIRS, REDWOODSTAIRS, ACACIASTAIRS };

                for (final BlockSettings setting : settings) {
                    setting.setToZero(configuration);
                }
            }
        }

        if (this == REDROCK) {
        	if (blockID == 0) {
                final BlockSettings[] settings = { REDROCKSLAB, WALL };

                for (final BlockSettings setting : settings) {
                    setting.setToZero(configuration);
                }
            }	
        }

        if (this == WOODSLAB || this == DOUBLEWOODSLAB) {
        	if (blockID == 0 && !clearedWoodSlabs) {
                final BlockSettings[] settings = { WOODSLAB, DOUBLEWOODSLAB };

                for (final BlockSettings setting : settings) {
                    setting.setToZero(configuration);
                }
                
                clearedWoodSlabs = true;
            }
        }

        if (this == REDROCKSLAB || this == DOUBLEREDROCKSLAB) {
            if (blockID == 0 && !clearedWoodSlabs) {
                final BlockSettings[] settings = { REDROCKSLAB, DOUBLEREDROCKSLAB };

                for (final BlockSettings setting : settings) {
                    setting.setToZero(configuration);
                }
                
                clearedWoodSlabs = true;
            }
        }
    }

    private void setToZero(EnhancedConfiguration configuration) {
        final Property property = configuration.getBlock(idKey(), 0);
        property.set(Integer.toString(0));
        blockID = 0;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
