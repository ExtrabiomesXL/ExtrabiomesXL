/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import extrabiomes.Extrabiomes;
import extrabiomes.core.utility.EnhancedConfiguration;

public enum BlockSettings {
    AUTUMNLEAVES        (200),
    CATTAIL             (201),
    CRACKEDSAND         (202),
    FLOWER              (203),
    GRASS               (204),
    GREENLEAVES         (205),
    LEAFPILE            (206),
    REDROCK             (207),
    SAPLING             (208),
    CUSTOMLOG           (209),
    QUARTERLOG0         (210),
    QUARTERLOG1         (211),
    QUARTERLOG2         (212),
    QUARTERLOG3         (213),
    QUICKSAND           (214),
    PLANKS              (215),
    WOODSLAB            (216),
    DOUBLEWOODSLAB      (217),
    REDWOODSTAIRS       (218),
    FIRSTAIRS           (219),
    ACACIASTAIRS        (220),
    REDROCKSLAB         (221),
    DOUBLEREDROCKSLAB   (222),
    REDCOBBLESTAIRS     (223),
    REDROCKBRICKSTAIRS  (224),
    WALL                (225);

    private int            blockID;

    private final int      defaultID;

    private static boolean clearedQuarterLogs = false;

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
        return this == QUARTERLOG0 || this == QUARTERLOG1 || this == QUARTERLOG2
                || this == QUARTERLOG3;
    }

    public void load(EnhancedConfiguration configuration) {
        Property property;
        switch (this) {
            case CRACKEDSAND:
            case REDROCK:
                property = configuration.getTerrainBlock(Configuration.CATEGORY_BLOCK, idKey(), defaultID, String.format(
                        Extrabiomes.proxy.getStringLocalization("config.block.terraingen.comment"),
                        toString()));
                break;
            default:
                property = configuration.getBlock(idKey(), defaultID);
        }

        blockID = property.getInt(0);

        if (isQuarterLog())
            if (blockID == 0 && !clearedQuarterLogs) {
                final BlockSettings[] settings = { QUARTERLOG0, QUARTERLOG1, QUARTERLOG2,
                        QUARTERLOG3 };

                for (final BlockSettings setting : settings)
                    setting.setToZero(configuration);
                clearedQuarterLogs = true;
            }
        
        if (this == PLANKS) {
            if (blockID == 0) {
                final BlockSettings[] settings = { WOODSLAB, DOUBLEWOODSLAB, FIRSTAIRS, REDWOODSTAIRS, ACACIASTAIRS };

                for (final BlockSettings setting : settings)
                    setting.setToZero(configuration);
            }
        }
        
        if (this == REDROCK) {
            if (blockID == 0) {
                final BlockSettings[] settings = { REDROCKSLAB, WALL };

                for (final BlockSettings setting : settings)
                    setting.setToZero(configuration);
            }
        }
        
        if (this == WOODSLAB || this == DOUBLEWOODSLAB) {
            if (blockID == 0 && ! clearedWoodSlabs) {
                final BlockSettings[] settings = { WOODSLAB, DOUBLEWOODSLAB };

                for (final BlockSettings setting : settings)
                    setting.setToZero(configuration);
                clearedWoodSlabs = true;
            }
        }
        
        if (this == REDROCKSLAB || this == DOUBLEREDROCKSLAB) {
            if (blockID == 0 && ! clearedWoodSlabs) {
                final BlockSettings[] settings = { REDROCKSLAB, DOUBLEREDROCKSLAB };

                for (final BlockSettings setting : settings)
                    setting.setToZero(configuration);
                clearedWoodSlabs = true;
            }
        }
    }

    static boolean clearedWoodSlabs = false;
    
    private void setToZero(EnhancedConfiguration configuration) {
        final Property property = configuration.getBlock(idKey(), 0);
        property.value = Integer.toString(0);
        blockID = 0;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
