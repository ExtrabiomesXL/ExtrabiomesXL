/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Map;

import net.minecraftforge.common.Property;

import com.google.common.collect.Maps;

import extrabiomes.utility.EnhancedConfiguration;

public enum DecorationSettings {
					//   lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm
	ALPINE				(null, 7,    0,    0,    null, null, null, null, null, null, null, null),
	AUTUMNWOODS			(null, 9,    null, 6,    null, 3,    null, null, null, null, null, null),
	BIRCHFOREST			(null, 7,    null, 1,    null, null, null, null, null, null, null, null),
	EXTREMEJUNGLE		(null, 50,   4,    25,   null, null, null, null, null, null, null, null),
	FORESTEDHILLS		(null, 7,    1,    5,    null, null, null, null, null, null, null, null),
					//   lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm
	FORESTEDISLAND		(null, 7,    null, 3,    null, null, null, null, null, null, null, null), 
	GLACIER				(null, null, null, null, null, null, null, null, null, null, null, null),
	GREENHILLS			(null, 1,    null, null, null, null, null, null, null, null, null, null),
	GREENSWAMP			(4,    4,    0,    null, 0,    8,    10,   null, 0,    0,    1,    null),
	ICEWASTELAND		(null, 0,    null, null, null, null, null, null, null, null, null, null),
					//   lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm
	MARSH				(null, 0,    null, 999,  null, null, 10,   null, null, null, null, null),
	MEADOW				(null, 0,    9,    12,   null, null, null, null, null, null, null, null),
	MINIJUNGLE			(12,   15,   5,    null, null, 2,    70,   null, null, null, 3,    null),
	MOUNTAINDESERT		(null, 0,    null, null, 2,    null, 50,   10,   null, null, null, null),
	MOUNTAINRIDGE		(null, 0,    null, 12,   null, null, null, null, null, null, null, null), 
					//   lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm
	MOUNTAINTAIGA		(null, 10,   null, 1,    null, null, null, null, null, null, null, null),
	PINEFOREST			(null, 10,   null, 1,    null, null, null, null, null, null, null, null), 
	RAINFOREST			(null, 7,    2,    4,    null, null, null, null, null, null, null, null),
	REDWOODFOREST		(null, 17,   null, null, null, null, null, null, null, null, null, null),
	REDWOODLUSH			(null, 17,   null, null, null, null, null, null, null, null, null, null),
					//   lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm
	SAVANNA				(null, 0,    1,    17,   null, null, null, null, null, null, null, null), 
	SHRUBLAND			(null, 0,    3,    1,    null, null, null, null, null, null, null, null),
	SNOWYFOREST			(null, 8,    1,    4,    null, null, null, null, null, null, null, null),
	SNOWYRAINFOREST		(null, 17,   null, 16,   null, 2,    null, null, null, null, null, null),
	TEMPORATERAINFOREST	(null, 17,   null, 16,   null, 2,    null, null, null, null, null, null),
					//   lily, tree, flow, gras, dead, mush, reed, cact, sand, snd2, clay, bigm
	TUNDRA				(null, null, -999, -999, null, null, null, null, 0,    0,    null, null),
	WASTELAND			(null, null, null, 1,    3,    null, null, null, null, null, null, null),
	WOODLANDS			(null, 8,    null, 3,    null, null, null, null, null, null, null, null);

	public enum Decoration{
		WATERLILY(".waterlily",0),
		TREES(".trees",0),
		FLOWERS(".flowers",2),
		GRASS(".grass",1),
		DEADBUSH(".deadBush",0),
		MUSHROOMS(".mushrooms",0),
		REEDS(".reeds",0),
		CACTI(".cacti",0),
		SAND(".sand",1),
		SAND2(".sandTwo",3),
		CLAY(".clay",1),
		BIGMUSHROOMS(".bigMushrooms",0);
		
		public final String key;	// config file key
		public final int def;		// default value
		
		private Decoration( String key, int def ) {
			this.key = key;
			this.def = def;
		}
	};
	
	private final Map<Decoration,Integer> settings = Maps.newHashMap();
    
    private DecorationSettings() {
        this(null, null, null, null, null, null, null, null, null, null, null, null);
    }

    private DecorationSettings(Integer waterlily, Integer trees, Integer flowers, 
    		Integer grass, Integer deadBush, Integer mushrooms, Integer reeds,
    		Integer cacti, Integer sand, Integer sand2, Integer clay,
    		Integer bigMushrooms) {
    	initSetting( Decoration.WATERLILY, waterlily );
		initSetting( Decoration.TREES, trees );
		initSetting( Decoration.FLOWERS, flowers );
		initSetting( Decoration.GRASS, grass );
		initSetting( Decoration.DEADBUSH, deadBush );
		initSetting( Decoration.MUSHROOMS, mushrooms );
		initSetting( Decoration.REEDS, reeds );
		initSetting( Decoration.CACTI, cacti );
		initSetting( Decoration.SAND, sand );
		initSetting( Decoration.SAND2, sand2 );
		initSetting( Decoration.CLAY, clay );
		initSetting( Decoration.BIGMUSHROOMS, bigMushrooms);
    }
    
    private void initSetting(Decoration key, Integer val) {
    	// only init if not default
    	if( val != null && !val.equals(key.def) ) {
    		settings.put(key, val);
    	}
    }

    public void load(EnhancedConfiguration configuration) {
        parseProperty( configuration, Decoration.WATERLILY );
		parseProperty( configuration, Decoration.TREES );
		parseProperty( configuration, Decoration.FLOWERS );
		parseProperty( configuration, Decoration.GRASS );
		parseProperty( configuration, Decoration.DEADBUSH );
		parseProperty( configuration, Decoration.MUSHROOMS );
		parseProperty( configuration, Decoration.REEDS );
		parseProperty( configuration, Decoration.CACTI );
		parseProperty( configuration, Decoration.SAND );
		parseProperty( configuration, Decoration.SAND2 );
		parseProperty( configuration, Decoration.CLAY );
		parseProperty( configuration, Decoration.BIGMUSHROOMS );
    }
    
    private void parseProperty(EnhancedConfiguration configuration, Decoration decoration) {
    	// only handle non-defaults
    	if( !settings.containsKey(decoration) )
    		return;
    	
    	Property property = configuration.get(EnhancedConfiguration.CATEGORY_DECORATION, toString() + decoration.key, decoration.def );
    	property.set(settings.get(decoration).toString());
    }
    
    public int getValue( Decoration decoration ) {
    	if( settings.containsKey(decoration) )
    		return settings.get(decoration).intValue();
    	else
    		return decoration.def;
    }
    
    public Map<Decoration,Integer> getSettings() {
    	return settings;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
