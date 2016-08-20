package ttftcuts.atg.api.events;

import java.util.List;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ATGBiomeGroupRequestEvent extends Event {

	public Biome biome;
	public List<String> groups;
	
	public ATGBiomeGroupRequestEvent(Biome biome) {
		this.biome = biome;
		this.groups = null;
	}
}
