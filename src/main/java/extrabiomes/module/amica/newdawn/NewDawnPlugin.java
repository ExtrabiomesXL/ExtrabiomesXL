package extrabiomes.module.amica.newdawn;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.ForgeSubscribe;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeList;
import two.newdawn.API.NewDawnBiomeProvider;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;

public class NewDawnPlugin implements NewDawnBiomeProvider
{
    private static boolean          enabled            = true;

    @ForgeSubscribe
    public void postInit(PluginEvent.Post event) throws Exception
    {
        if (!isEnabled())
            return;
        LogHelper.info("Registering NewDawn biome provider...");
        NewDawnBiomeList.registerProvider(this);
    }
    
    private boolean isEnabled()
    {
        return enabled && Extrabiomes.proxy.isModLoaded("newdawn");
    }
    
    public static NewDawnBiome getBiomeIfEnabled(BiomeSettings biome) {
    	if( biome != null && biome.isEnabled() ) {
    		final BiomeGenBase gen;
    		try {
    			gen = biome.getBiome().get();
    		} catch( Exception e ) {
    			return null;
    		}
    		return NewDawnBiome.copyVanilla(gen);
    	}
    	return null;
    }
    
    public static int fuzzValue(int value, Random rng) {
    	final double fuzz = 0.2 * value * rng.nextDouble();
    	final double result = 0.9 * value + fuzz;
    	return (int)result;
    }
    public static NoiseStretch getFuzzyStretch(int size, SimplexNoise noise) {
    	final Random rng = noise.getRandom();
    	final int sizeX = fuzzValue(size, rng);
    	final int sizeZ = fuzzValue(size, rng);
    	final NoiseStretch stretch =  noise.generateNoiseStretcher(sizeX, sizeZ, rng.nextDouble(), rng.nextDouble());
    	return stretch;
    }
    
    /*
    @SuppressWarnings({ "rawtypes" })
    @ForgeSubscribe
    public void preInit(PluginEvent.Pre event)
    {
        if (!isEnabled())
            return;
        //LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT), "NewDawn");
        LogHelper.fine("Initializing %s plugin.", "NewDawn");
        
        try
        {
        	
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
            //LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR), "NewDawn");
            LogHelper.fine("Could not communicate with %s. Disabling plugin.", "NewDawn");
            enabled = false;
        }
    }
    */

	@Override
	public Set<NewDawnBiomeSelector> getBiomeSelectors(SimplexNoise worldNoise) {
		final HashSet<NewDawnBiomeSelector> selectors = new HashSet<NewDawnBiomeSelector>();
		
		selectors.add(new EBXLAridSelector(worldNoise,NewDawnBiomeProvider.PRIORITY_MEDIUM - 1));
		selectors.add(new EBXLDampSelector(worldNoise,NewDawnBiomeProvider.PRIORITY_MEDIUM));
		selectors.add(new EBXLNormalSelector(worldNoise,NewDawnBiomeProvider.PRIORITY_MEDIUM + 1));
		
		return selectors;
	}
}
