/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import net.minecraftforge.common.Property;
import extrabiomes.core.utility.EnhancedConfiguration;

public enum BiomeSettings {
    DESERT, EXTREMEHILLS, FOREST, JUNGLE, PLAINS, SWAMPLAND, TAIGA,
    ALPINE              (32),
    AUTUMNWOODS         (33),
    BIRCHFOREST         (34),
    EXTREMEJUNGLE       (35),
    FORESTEDHILLS       (36),
    FORESTEDISLAND      (37),
    GLACIER             (38),
    GREENHILLS          (39),
    GREENSWAMP          (40),
    ICEWASTELAND        (41),
    MARSH               (42),
    MEADOW              (43),
    MINIJUNGLE          (44),
    MOUNTAINDESERT      (45),
    MOUNTAINRIDGE       (46),
    MOUNTAINTAIGA       (47),
    PINEFOREST          (48),
    RAINFOREST          (49),
    REDWOODFOREST       (50),
    REDWOODLUSH         (51),
    SAVANNA             (52),
    SHRUBLAND           (53),
    SNOWYFOREST         (54),
    SNOWYRAINFOREST     (55),
    TEMPORATERAINFOREST (56),
    TUNDRA              (57),
    WASTELAND           (58),
    WOODLANDS           (59);

    private final int     defaultID;

    private int           biomeID;
    private boolean       enabled = true;
    private boolean       allowVillages;
    private final boolean isVanilla;

    private BiomeSettings() {
        isVanilla = true;
        defaultID = 0;
    }

    private BiomeSettings(int defaultID) {
        this.defaultID = defaultID;
        biomeID = this.defaultID;
        isVanilla = false;
    }

    public boolean allowVillages() {
        return allowVillages;
    }

    public int getID() {
        if (isVanilla) throw new IllegalStateException("Vanilla biomes have no ID setting.");
        return biomeID;
    }

    public boolean isEnabled() {
        return enabled;
    }

    private String keyAllowVillages() {
        return keyVanillaPrefix() + toString() + ".allowvillages";
    }

    private String keyEnabled() {
        return keyVanillaPrefix() + toString() + ".enablegeneration";
    }

    private String keyID() {
        return toString() + ".id";
    }

    private String keyVanillaPrefix() {
        return isVanilla ? "vanilla." : "";
    }

    public void load(EnhancedConfiguration configuration) {
        Property property;

        if (!isVanilla) {
            property = configuration.getBiome(keyID(), defaultID);
            biomeID = property.getInt(0);
        }

        property = configuration.get(EnhancedConfiguration.CATEGORY_BIOME, keyEnabled(), true);
        if (biomeID == 0) property.value = Boolean.toString(false);
        enabled = property.getBoolean(false);

        property = configuration
                .get(EnhancedConfiguration.CATEGORY_BIOME, keyAllowVillages(), true);
        if (biomeID == 0) property.value = Boolean.toString(false);
        allowVillages = property.getBoolean(false);

    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
