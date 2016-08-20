package ttftcuts.atg.api.events;

import com.google.common.base.Optional;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ATGBiomeRequestEvent extends Event {

	public String biomeName;
	public Optional<Biome> biome;
	
	public ATGBiomeRequestEvent(String biomeName) {
		this.biomeName = biomeName;
		this.biome = Optional.absent();
	}
}
