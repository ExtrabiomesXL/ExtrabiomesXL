package extrabiomes;

import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

public enum FloraControl {

	INSTANCE;

	private Map<Extrabiome, Set<Flora>> enabledFloraByBiome = new EnumMap<Extrabiome, Set<Flora>>(
			Extrabiome.class);

	Set<Flora> getenabledFloraForBiome(final Extrabiome biome) {
		if (biome == null)
			return null;

		Set<Flora> enabledFlora = enabledFloraByBiome.get(biome);
		if (enabledFlora == null)
			enabledFlora = EnumSet.allOf(Flora.class);

		return enabledFlora;
	}

	void disableFloraForBiome(final Extrabiome biome,
			final Collection<Flora> disabledFlora) {
		if (biome == null || disabledFlora == null)
			return;

		Set<Flora> enabledFlora = enabledFloraByBiome.get(biome);
		if (enabledFlora == null)
			enabledFlora = EnumSet.allOf(Flora.class);

		enabledFlora.removeAll(disabledFlora);
		enabledFloraByBiome.put(biome, enabledFlora);
	}

	boolean isEnabled(Extrabiome biome, Flora flora) {
		if (biome != null && flora != null) {
			Set<Flora> enabledFlora = enabledFloraByBiome.get(biome);
			if (enabledFlora == null || enabledFlora.contains(flora))
				return true;
		}
		return false;
	}
}
