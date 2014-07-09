package extrabiomes.module.amica.newdawn;

import java.util.Locale;

import net.minecraftforge.common.config.Property;
import extrabiomes.utility.EnhancedConfiguration;

public enum NewDawnSettings {
	MINI_JUNGLE(StretchSize.EXTRA_SMALL),
	GLACIER(StretchSize.EXTRA_SMALL),
	
	FOREST(StretchSize.SMALL),
	ALPINE(StretchSize.SMALL),
	
	AUTUMN(StretchSize.MEDIUM),
	SNOWY(StretchSize.MEDIUM),
	EXTREME_JUNGLE(StretchSize.MEDIUM),
	REDWOOD(StretchSize.MEDIUM),
	MOUNTAIN(StretchSize.MEDIUM),
	WASTELAND(StretchSize.MEDIUM),
	SAVANNA(StretchSize.MEDIUM),
	
	BIRCH(StretchSize.LARGE),
	GREEN(StretchSize.LARGE),
	RAINFOREST(StretchSize.LARGE),
	TUNDRA(StretchSize.LARGE),
	
	MEADOW(StretchSize.EXTRA_LARGE);
	
	public enum StretchSize {
		EXTRA_SMALL(128),
		SMALL(256),
		MEDIUM(384),
		LARGE(512),
		EXTRA_LARGE(768);
		
		private final int size;
		
		private StretchSize(int size) {
			this.size = size;
		}
	}
	
	public void load(EnhancedConfiguration configuration) {
		final Property property = configuration.get(EnhancedConfiguration.CATEGORY_NEWDAWN, toString(), size);
	    size = property.getInt();
	}
	
	private int size;
	
	private NewDawnSettings(StretchSize defaultSize) {
		this.size = defaultSize.size;
	}
	
	public int getStretchSize() {
		return this.size;
	}
	
	@Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
}
