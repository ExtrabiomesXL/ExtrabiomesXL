package ttftcuts.atg.api.events;

import ttftcuts.atg.api.IGenMod;

import com.google.common.base.Optional;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ATGBiomeModRequestEvent extends Event {

	public Biome biome;
	public Optional<IGenMod> mod;
	
	public ATGBiomeModRequestEvent(Biome biome) {
		this.biome = biome;
		this.mod = Optional.absent();
	}
}
