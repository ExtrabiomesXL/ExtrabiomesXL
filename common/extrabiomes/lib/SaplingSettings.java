package extrabiomes.lib;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import extrabiomes.utility.EnhancedConfiguration;

public enum SaplingSettings {
	BROWN(15.0f), ORANGE(15.0f), PURPLE(15.0f), YELLOW(15.0f), FIR(1.0f), REDWOOD(1.5f), ACACIA(13.2f), CYPRESS(11.0f), BALD_CYPRESS(1.0f), JAPANESE_MAPLE(10.47f), JAPANESE_MAPLE_SHRUB(46.51f), RAINBOW_EUCALYPTUS(1.37f);
	
    private double 			chance;
    private final double	defaultChance;

    private SaplingSettings(double defaultChance) {
        this.defaultChance = defaultChance;
        chance = this.defaultChance; 
    }
    
    public double chance() {
        return chance;
    }

    private String chanceKey() {
        return toString() + ".chance";
    }
    
    public void load(EnhancedConfiguration configuration) {
        Property property;
        
        property = configuration.get("saplingreplanting", chanceKey(), defaultChance);
        
        chance = property.getDouble(defaultChance);
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
