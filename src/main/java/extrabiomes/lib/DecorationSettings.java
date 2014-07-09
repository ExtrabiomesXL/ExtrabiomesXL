/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;
import java.util.Map;

import net.minecraftforge.common.config.Property;

import com.google.common.collect.Maps;

import extrabiomes.utility.EnhancedConfiguration;

public enum DecorationSettings
{
	// @formatter:off
    //   				 lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm, nFlw
    ALPINE				(null, 7,    null, 0,    null, null, null, null, null, null, null, null, 4   ), // 5
    AUTUMNWOODS			(null, 6,    null, 6,    null, 3,    null, null, null, null, null, null, 5   ), // 2
    BIRCHFOREST			(null, 7,    null, 1,    null, null, null, null, null, null, null, null, 6   ), // 10
    EXTREMEJUNGLE		(null, 50,   null, 25,   null, null, null, null, null, null, null, null, 7   ), // 1
    FORESTEDHILLS		(null, 7,    null, 5,    null, null, null, null, null, null, null, null, 5   ), // 12
    //   				 lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm, nFlw
    FORESTEDISLAND		(null, 7,    1,    3,    null, null, null, null, null, null, null, null, 6   ), // 8
    GLACIER				(null, null, null, null, null, null, null, null, null, null, null, null, null),
    GREENHILLS			(null, 1,    null, null, null, null, null, null, null, null, null, null, 5   ), // 11
    GREENSWAMP			(4,    4,    null, null, 0,    8,    10,   null, 0,    0,    1,    null, 6   ), // 8
    ICEWASTELAND		(null, 0,    null, null, null, null, null, null, null, null, null, null, null),
    //   				 lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm, nFlw
    MARSH				(null, 0,    null, 32,   null, null, 10,   null, null, null, null, null, null),
    MEADOW				(null, 0,    2,    12,   null, null, null, null, null, null, null, null, 8   ), // 12
    MINIJUNGLE			(12,   15,   null, null, null, 2,    70,   null, null, null, 3,    null, 7   ), // 1
    MOUNTAINDESERT		(null, 0,    null, null, 2,    null, 50,   10,   null, null, null, null, null),
    MOUNTAINRIDGE		(null, 0,    null, 12,   null, null, null, null, null, null, null, null, null),
    //   				 lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm, nFlw
    MOUNTAINTAIGA		(null, 10,   null, 1,    null, null, null, null, null, null, null, null, 4   ), // 1
    PINEFOREST			(null, 10,   null, 1,    null, null, null, null, null, null, null, null, 4   ), // 6
    RAINFOREST			(null, 22,   null, 4,    null, null, null, null, null, null, null, null, 6   ), // 1
    REDWOODFOREST		(null, 7,    null, null, null, null, null, null, null, null, null, null, null),
    REDWOODLUSH			(null, 8,    1,    null, null, null, null, null, null, null, null, null, 5   ), // 3
    //   				 lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm, nFlw
    SAVANNA				(null, 0,    null, 17,   null, null, null, null, null, null, null, null, 5   ), // 18
    SHRUBLAND			(null, 1,    1,    1,    null, null, null, null, null, null, null, null, 4   ), // 13
    SNOWYFOREST			(null, 8,    null, 4,    null, null, null, null, null, null, null, null, null),
    SNOWYRAINFOREST		(null, 17,   null, 16,   null, 2,    null, null, null, null, null, null, null),
    TEMPORATERAINFOREST	(null, 17,   null, 16,   null, 2,    null, null, null, null, null, null, null),
    //   				 lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm, nFlw
    TUNDRA				(null, null, null, 0,    null, null, null, null, 0,    0,    null, null, null),
    WASTELAND			(null, null, null, 1,    3,    null, null, null, null, null, null, null, null),
    WOODLANDS			(null, 8,    null, 3,    null, null, null, null, null, null, null, null, 6   ), // 11
	// @formatter:on
	DEFAULT();
    
    public enum Decoration
    {
        WATERLILY(".waterlily", 0),
        TREES(".trees", 0),
        FLOWERS(".flowers", 0),	// was 2, by default we don't want vanilla flowers any more
        GRASS(".grass", 1),
        DEADBUSH(".deadBush", 0),
        MUSHROOMS(".mushrooms", 0),
        REEDS(".reeds", 0),
        CACTI(".cacti", 0),
        SAND(".sand", 1),
        SAND2(".sandTwo", 3),
        CLAY(".clay", 1),
		BIGMUSHROOMS(".bigMushrooms", 0),
		NEW_FLOWERS(".newFlowers", 0);
        
        public final String key; // config file key
        public final int    def; // default value
                                 
        private Decoration(String key, int def)
        {
            this.key = key;
            this.def = def;
        }
    };
    
    private final Map<Decoration, Integer> settings = Maps.newHashMap();
    
    private DecorationSettings()
    {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null);
    }
    
	private DecorationSettings(Integer waterlily, Integer trees,
			Integer flowers, Integer grass, Integer deadBush,
			Integer mushrooms, Integer reeds, Integer cacti, Integer sand,
			Integer sand2, Integer clay, Integer bigMushrooms,
			Integer newFlowers)
    {
        initSetting(Decoration.WATERLILY, waterlily);
        initSetting(Decoration.TREES, trees);
        initSetting(Decoration.FLOWERS, flowers);
        initSetting(Decoration.GRASS, grass);
        initSetting(Decoration.DEADBUSH, deadBush);
        initSetting(Decoration.MUSHROOMS, mushrooms);
        initSetting(Decoration.REEDS, reeds);
        initSetting(Decoration.CACTI, cacti);
        initSetting(Decoration.SAND, sand);
        initSetting(Decoration.SAND2, sand2);
        initSetting(Decoration.CLAY, clay);
        initSetting(Decoration.BIGMUSHROOMS, bigMushrooms);
		initSetting(Decoration.NEW_FLOWERS, newFlowers);
    }
    
    private void initSetting(Decoration key, Integer val)
    {
        // only init if not default
        if (val != null && !val.equals(key.def))
        {
            settings.put(key, val);
        }
    }
    
    public void load(EnhancedConfiguration configuration)
    {
        parseProperty(configuration, Decoration.WATERLILY);
        parseProperty(configuration, Decoration.TREES);
        parseProperty(configuration, Decoration.FLOWERS);
        parseProperty(configuration, Decoration.GRASS);
        parseProperty(configuration, Decoration.DEADBUSH);
        parseProperty(configuration, Decoration.MUSHROOMS);
        parseProperty(configuration, Decoration.REEDS);
        parseProperty(configuration, Decoration.CACTI);
        parseProperty(configuration, Decoration.SAND);
        parseProperty(configuration, Decoration.SAND2);
        parseProperty(configuration, Decoration.CLAY);
        parseProperty(configuration, Decoration.BIGMUSHROOMS);
		parseProperty(configuration, Decoration.NEW_FLOWERS);
    }
    
    private void parseProperty(EnhancedConfiguration configuration, Decoration decoration)
    {
        // only handle non-defaults
        if (!settings.containsKey(decoration))
            return;
        
        Property property = configuration.get(EnhancedConfiguration.CATEGORY_DECORATION, toString() + decoration.key, settings.get(decoration));
        settings.put(decoration, property.getInt());
    }
    
	public int getSetting(Decoration decoration)
    {
		if (settings.containsKey(decoration))
        {
            return settings.get(decoration).intValue();
        }
        else
        {
            return decoration.def;
        }
    }
    
    public Map<Decoration, Integer> getSettings()
    {
        return settings;
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
