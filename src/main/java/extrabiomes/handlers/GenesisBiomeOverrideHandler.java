package extrabiomes.handlers;

import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import extrabiomes.helpers.LogHelper;

public class GenesisBiomeOverrideHandler extends GenLayer{
	
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

	static int replaceBiome = -1;
	
	
	
	@ForgeSubscribe
	public void hookGenesisBiomeHandler(InitBiomeGens event){
		//this.parent = event.originalBiomeGens[0];
		
		event.newBiomeGens = event.originalBiomeGens;
		
		//
		event.newBiomeGens[0] = new GenesisBiomeOverrideHandler(0, event.originalBiomeGens[0]);
		
		LogHelper.info("We had the log command");
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] aint = IntCache.getIntCache(width * depth);
		
		
		if(replaceBiome != -1){
			for(int i = 0; i < (width * depth); i++) {
				//LogHelper.info("Genesis X: %d, Z: %d, width: %d, depth: %d", x, z, width, depth);
				aint[i] = replaceBiome;
			}
		} else {
			aint = this.parent.getInts(x, z, width, depth);
		}
		
		return aint;
	}
	
	public static void enable(int newBiome){
		if(newBiome > -1 && newBiome < 128) {
			replaceBiome = newBiome;
		}
	}
	
	public static void disable() {
		replaceBiome = -1;
	}
	
	
	//void StartOverride

}
