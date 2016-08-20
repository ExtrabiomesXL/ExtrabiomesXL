package ttftcuts.atg.api.events;

import ttftcuts.atg.api.ATGBiomes.BiomeType;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ATGBiomeEvent extends Event {

	public static enum ResponseType { NONE, ADDED, REPLACED, BAD_GROUP, FAILED };
	
	public BiomeType type;
	public ResponseType response;
	public String group;
	public Biome biome;
	public Biome replaced;
	public double weight;
	
	public ATGBiomeEvent(BiomeType type, String group, Biome biome, Biome replaced, double weight) {
		this.type = type;
		this.group = group;
		this.biome = biome;
		this.replaced = replaced;
		this.weight = weight;
		this.response = ResponseType.NONE;
	}
}
