package extrabiomes.lib;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import extrabiomes.utility.EnhancedConfiguration;

public enum SaplingSettings {
	BROWN(5.0f), ORANGE(5.0f), PURPLE(5.0f), YELLOW(5.0f), FIR(5.0f), REDWOOD(5.0f), ACACIA(5.0f), CYPRESS(5.0f);
	
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
