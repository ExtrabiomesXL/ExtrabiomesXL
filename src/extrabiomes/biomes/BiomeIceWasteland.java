package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeIceWasteland extends ExtrabiomeGenBase {

	public BiomeIceWasteland(int id) {
		super(id);
		spawnableCreatureList.clear();
		topBlock = (byte) Block.blockSnow.blockID;
		fillerBlock = (byte) Block.blockSnow.blockID;
		setEnableSnow();
		setColor(0x7DA0B5);
		setBiomeName("Ice Wasteland");
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 0.3F;
		maxHeight = 0.4F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0).build();
	}

}
