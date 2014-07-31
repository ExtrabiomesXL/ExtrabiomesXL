package extrabiomes.handlers;

import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import extrabiomes.helpers.LogHelper;

public class GenesisBiomeOverrideHandler extends GenLayer{
	public static GenesisBiomeOverrideHandler	INSTANCE	= new GenesisBiomeOverrideHandler();
	
	public static class coord {
		public int x = 0;
		public int z = 0;
		
		public coord (int xx, int zz) {
			x = xx;
			z = zz;
		}
	}
	
	public static Queue<coord> myQueue = new LinkedList<coord>();
	
	public GenesisBiomeOverrideHandler() {
		super(0);
	}
	
	public GenesisBiomeOverrideHandler(long par1, GenLayer par) {
		super(par1);
		
		this.parent = par;
	}

	private int	replaceBiome	= -1;
	
	@SubscribeEvent
	public void hookGenesisBiomeHandler(InitBiomeGens event){
		//this.parent = event.originalBiomeGens[0];
		
		event.newBiomeGens = event.originalBiomeGens;
		event.newBiomeGens[0] = new GenesisBiomeOverrideHandler(0, event.originalBiomeGens[0]);
		
		LogHelper.info("We had the log command");
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		final int[] ints;
		
		
		
		if(INSTANCE.replaceBiome != -1){
			LogHelper.info("Overriding @ " + x + "," + z + " x " + width + "," + depth + " = " +  BiomeGenBase.getBiomeGenArray()[INSTANCE.replaceBiome].biomeName);
			ints = IntCache.getIntCache(width * depth);
			for(int i = 0; i < (width * depth); i++) {
				//LogHelper.info("Genesis X: %d, Z: %d, width: %d, depth: %d", x, z, width, depth);
				ints[i] = INSTANCE.replaceBiome;
			}
		} else {
			//LogHelper.info("Not overriding.");
			ints = this.parent.getInts(x, z, width, depth);
		}
		
		return ints;
	}
	
	public static void enable(int newBiome){
		if(newBiome > -1 && newBiome < 128) {
			LogHelper.info("Genesis override handler enabled, target = " + newBiome);
			INSTANCE.replaceBiome = newBiome;
		}
	}
	
	public static void disable() {
		LogHelper.info("Genesis override handler disabled");
		INSTANCE.replaceBiome = -1;
	}

}
