package extrabiomes.module.amica.newdawn;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.common.base.Optional;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.ForgeSubscribe;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeProvider;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.NewDawnRegistry;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.module.amica.atg.ATGPluginImpl;

public class NewDawnPlugin
{
	
	private static final String            MOD_ID = "newdawn";
    private static Optional<NewDawnPluginImpl> api    = Optional.absent();
    
    @ForgeSubscribe
    public void preInit(PluginEvent.Pre event)
    {
        if (!Extrabiomes.proxy.isModLoaded(MOD_ID))
        {
            return;
        }
        
        LogHelper.fine("Initializing %s plugin.", MOD_ID);
        try
        {
            api = Optional.of(new NewDawnPluginImpl());
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
            LogHelper.fine("Could not communicate with %s. Disabling plugin.", MOD_ID);
            api = Optional.absent();
        }
    }
    
    @ForgeSubscribe
    public void init(PluginEvent.Init event)
    {
        if (!api.isPresent())
            return;
        
        api.get().init();
    }
    
    @ForgeSubscribe
    public void postInit(PluginEvent.Post event)
    {
        api = Optional.absent();
    }
}
