/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

import net.minecraft.src.BiomeGenBase;

import com.google.common.base.Optional;

/**
 * Allows direct access to Extrabiome's biomes. This class' members will
 * be populated during the @Init event. If a biome is absent after the
 * Init event, then the Extrabiomes mod is not active or not installed.
 * <p>
 * <b>NOTE:</b> Make sure to only reference members of this class in the
 * PostInit event or later.
 * 
 * @author ScottKillen
 * 
 */
public class ExtrabiomesBiome {

	public static Optional<BiomeGenBase>	alpine				= Optional
																		.absent();
	public static Optional<BiomeGenBase>	autumnwoods			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	birchforest			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	extremejungle		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	forestedisland		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	forestedhills		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	glacier				= Optional
																		.absent();
	public static Optional<BiomeGenBase>	greenhills			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	icewasteland		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	greenswamp			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	marsh				= Optional
																		.absent();
	public static Optional<BiomeGenBase>	meadow				= Optional
																		.absent();
	public static Optional<BiomeGenBase>	minijungle			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	mountaindesert		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	mountainridge		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	mountaintaiga		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	pineforest			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	rainforest			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	redwoodforest		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	redwoodlush			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	savanna				= Optional
																		.absent();
	public static Optional<BiomeGenBase>	shrubland			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	snowforest			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	snowyrainforest		= Optional
																		.absent();
	public static Optional<BiomeGenBase>	temperaterainforest	= Optional
																		.absent();
	public static Optional<BiomeGenBase>	tundra				= Optional
																		.absent();
	public static Optional<BiomeGenBase>	wasteland			= Optional
																		.absent();
	public static Optional<BiomeGenBase>	woodlands			= Optional
																		.absent();

}
