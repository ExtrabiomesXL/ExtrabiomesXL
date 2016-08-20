package ttftcuts.atg.api.events;

import ttftcuts.atg.api.IGenMod;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ATGBiomeModEvent extends Event {

	public static enum EventType { GENMOD, SUBBIOME }
	
	public EventType type;
	public Biome biome;
	public IGenMod mod;
	public Biome subBiome;
	public double weight;
	
	public ATGBiomeModEvent( EventType type, Biome biome, IGenMod mod, Biome subBiome, double weight ) {
		
		this.type = type;
		this.biome = biome;
		this.subBiome = subBiome;
		this.mod = mod;
		this.weight = weight;
	}
}
