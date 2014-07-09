package extrabiomes.lib;

import java.util.Locale;

import net.minecraftforge.common.config.Property;
import extrabiomes.utility.EnhancedConfiguration;

public enum SaplingSettings
{
    UMBER(15.0f), GOLDENROD(15.0f), VERMILLION(15.0f), CITRINE(15.0f), FIR(6.0f), REDWOOD(0.5f), ACACIA(26.4f), CYPRESS(22.0f), BALD_CYPRESS(0.5f), JAPANESE_MAPLE(20.9f), JAPANESE_MAPLE_SHRUB(90.0f), RAINBOW_EUCALYPTUS(0.5f), SAKURA_BLOSSOM(19.99f);
    
    private double       chance;
    private final double defaultChance;
    
    private SaplingSettings(double defaultChance)
    {
        this.defaultChance = defaultChance;
        chance = this.defaultChance;
    }
    
    public double chance()
    {
        return chance;
    }
    
    private String chanceKey()
    {
        return toString() + ".chance";
    }
    
    public void load(EnhancedConfiguration configuration)
    {
        Property property;
        
        property = configuration.get("saplingreplanting", chanceKey(), defaultChance);
        
        chance = property.getDouble(defaultChance);
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
}
