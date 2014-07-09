package ttftcuts.atg.api.events;

import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.Event;
import ttftcuts.atg.api.IGenMod;

import com.google.common.base.Optional;

public class ATGBiomeModRequestEvent extends Event
{
    
    public BiomeGenBase      biome;
    public Optional<IGenMod> mod;
    
    public ATGBiomeModRequestEvent(BiomeGenBase biome)
    {
        this.biome = biome;
        this.mod = Optional.absent();
    }
}
