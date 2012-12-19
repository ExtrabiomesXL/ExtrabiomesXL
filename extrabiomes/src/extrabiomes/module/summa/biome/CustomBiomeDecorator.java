/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

class CustomBiomeDecorator extends BiomeDecorator {

	static class Builder {

		// required parms
		private final BiomeGenBase	biome;

		// optional parms - initialized to defaults
		private int					waterlilyPerChunk		= 0;
		private int					treesPerChunk			= 0;
		private int					flowersPerChunk			= 2;
		private int					grassPerChunk			= 1;
		private int					deadBushPerChunk		= 0;
		private int					mushroomsPerChunk		= 0;
		private int					reedsPerChunk			= 0;
		private int					cactiPerChunk			= 0;
		private int					sandPerChunk			= 1;
		private int					sandPerChunk2			= 3;
		private int					clayPerChunk			= 1;
		private int					bigMushroomsPerChunk	= 0;

		Builder(BiomeGenBase biome) {
			this.biome = biome;
		}

		Builder bigMushroomsPerChunk(int val) {
			bigMushroomsPerChunk = val;
			return this;
		}

		CustomBiomeDecorator build() {
			return new CustomBiomeDecorator(this);
		}

		Builder cactiPerChunk(int val) {
			cactiPerChunk = val;
			return this;
		}

		Builder clayPerChunk(int val) {
			clayPerChunk = val;
			return this;
		}

		Builder deadBushPerChunk(int val) {
			deadBushPerChunk = val;
			return this;
		}

		Builder flowersPerChunk(int val) {
			flowersPerChunk = val;
			return this;
		}

		Builder grassPerChunk(int val) {
			grassPerChunk = val;
			return this;
		}

		Builder mushroomsPerChunk(int val) {
			mushroomsPerChunk = val;
			return this;
		}

		Builder reedsPerChunk(int val) {
			reedsPerChunk = val;
			return this;
		}

		Builder sandPerChunk(int val, int val2) {
			sandPerChunk = val;
			sandPerChunk2 = val2;
			return this;
		}

		Builder treesPerChunk(int val) {
			treesPerChunk = val;
			return this;
		}

		Builder waterlilyPerChunk(int val) {
			waterlilyPerChunk = val;
			return this;
		}
	}

	private CustomBiomeDecorator() {
		super(null);
	}

	private CustomBiomeDecorator(Builder builder) {
		super(builder.biome);

		waterlilyPerChunk = builder.waterlilyPerChunk;
		treesPerChunk = builder.treesPerChunk;
		flowersPerChunk = builder.flowersPerChunk;
		grassPerChunk = builder.grassPerChunk;
		deadBushPerChunk = builder.deadBushPerChunk;
		mushroomsPerChunk = builder.mushroomsPerChunk;
		reedsPerChunk = builder.reedsPerChunk;
		cactiPerChunk = builder.cactiPerChunk;
		sandPerChunk = builder.sandPerChunk;
		sandPerChunk2 = builder.sandPerChunk2;
		clayPerChunk = builder.clayPerChunk;
		bigMushroomsPerChunk = builder.bigMushroomsPerChunk;
	}

}
