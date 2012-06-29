
package extrabiomes.terrain;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;

public class CustomBiomeDecorator extends BiomeDecorator {
	public static class Builder {
		private final BiomeBase	biome;
		private int				waterlilyPerChunk;
		private int				treesPerChunk;
		private int				flowersPerChunk;
		private int				grassPerChunk;
		private int				deadBushPerChunk;
		private int				mushroomsPerChunk;
		private int				reedsPerChunk;
		private int				cactiPerChunk;
		private int				sandPerChunk;
		private int				sandPerChunk2;
		private int				clayPerChunk;
		private int				bigMushroomsPerChunk;

		private Builder() {
			this(null);
		}

		public Builder(BiomeBase biomebase) {
			waterlilyPerChunk = 0;
			treesPerChunk = 0;
			flowersPerChunk = 2;
			grassPerChunk = 1;
			deadBushPerChunk = 0;
			mushroomsPerChunk = 0;
			reedsPerChunk = 0;
			cactiPerChunk = 0;
			sandPerChunk = 1;
			sandPerChunk2 = 3;
			clayPerChunk = 1;
			bigMushroomsPerChunk = 0;
			biome = biomebase;
		}

		public Builder bigMushroomsPerChunk(int i) {
			bigMushroomsPerChunk = i;
			return this;
		}

		public CustomBiomeDecorator build() {
			return new CustomBiomeDecorator(this);
		}

		public Builder cactiPerChunk(int i) {
			cactiPerChunk = i;
			return this;
		}

		public Builder clayPerChunk(int i) {
			clayPerChunk = i;
			return this;
		}

		public Builder deadBushPerChunk(int i) {
			deadBushPerChunk = i;
			return this;
		}

		public Builder flowersPerChunk(int i) {
			flowersPerChunk = i;
			return this;
		}

		public Builder grassPerChunk(int i) {
			grassPerChunk = i;
			return this;
		}

		public Builder mushroomsPerChunk(int i) {
			mushroomsPerChunk = i;
			return this;
		}

		public Builder reedsPerChunk(int i) {
			reedsPerChunk = i;
			return this;
		}

		public Builder sandPerChunk(int i, int j) {
			sandPerChunk = i;
			sandPerChunk2 = j;
			return this;
		}

		public Builder treesPerChunk(int i) {
			treesPerChunk = i;
			return this;
		}

		public Builder waterlilyPerChunk(int i) {
			waterlilyPerChunk = i;
			return this;
		}
	}

	private CustomBiomeDecorator() {
		super(null);
	}

	private CustomBiomeDecorator(Builder builder) {
		super(builder.biome);
		y = builder.waterlilyPerChunk;
		z = builder.treesPerChunk;
		A = builder.flowersPerChunk;
		B = builder.grassPerChunk;
		C = builder.deadBushPerChunk;
		D = builder.mushroomsPerChunk;
		E = builder.reedsPerChunk;
		F = builder.cactiPerChunk;
		G = builder.sandPerChunk;
		H = builder.sandPerChunk2;
		I = builder.clayPerChunk;
		J = builder.bigMushroomsPerChunk;
	}
}
