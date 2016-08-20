package ttftcuts.atg.api.events;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ATGBiomeRocksEvent extends Event {

	public Biome biome;
	public int rockChance;
	public int bigRockChance;
	public int rocksPerChunk;
	
	public ATGBiomeRocksEvent( Biome biome, int rockChance, int bigRockChance, int rocksPerChunk ) {
		this.biome = biome;
		this.rockChance = rockChance;
		this.bigRockChance = bigRockChance;
		this.rocksPerChunk = rocksPerChunk;
	}
}
