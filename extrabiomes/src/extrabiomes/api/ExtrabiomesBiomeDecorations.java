/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

import net.minecraft.src.BiomeGenBase;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;


/**
 * Allows direct access to Extrabiome's BiomeDecorations. The members of
 * this class will be populated during @Init. Each biome's decorations
 * will be applied during BaseMod.generateSurface().
 * <p>
 * <b>NOTE:</b> Only reference this class in @PostInit or later.
 * 
 * @author ScottKillen
 * 
 */
public class ExtrabiomesBiomeDecorations {

	/**
	 * A map of of biome decorations to their biomes. Add decorations
	 * here to have them be applied to specific biomes. If <em>null</em>
	 * is supplied for the key instead of a specific biome, the
	 * decoration will be applied to all ExtrabiomesXL custom biomes.
	 */
	public static Optional<Multimap<BiomeGenBase, IBiomeDecoration>>	biomeDecorations	= Optional
																									.absent();

}
