/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.utility;

import java.util.Collections;
import java.util.Map;

import extrabiomes.Extrabiomes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ModelUtil {
	private static final DefaultStateMapper defaultStateMapper = new DefaultStateMapper();
	
	public static ModelResourceLocation getModelLocation(ResourceLocation loc, IBlockState state) {
		return new ModelResourceLocation(loc, getVariant(state));
	}

	public static String getVariant(IBlockState state) {
		return defaultStateMapper.getPropertyString(state.getProperties());
	}
	
	//Blocks >
	public static void registerTexture(Block block) {
		registerTextures(block, Collections.singletonList(block.getDefaultState()));
	}

	public static void registerTextures(Block block, Iterable<IBlockState> states) {
		registerTextures(block, states, null);
	}

	public static void registerTextures(Block block, Iterable<IBlockState> states, IStateMapper mapper) {
		final Item item = Item.getItemFromBlock(block);
		if (item == null) return; //No item

		final ResourceLocation loc = Item.REGISTRY.getNameForObject(item);
		if (loc == null) return; //Item isn't registered

		final boolean usingMapper = mapper != null; //If there's a custom state mapper or not
		Map<IBlockState, ModelResourceLocation> locations = usingMapper ? mapper.putStateModelLocations(block) : null;

		for (IBlockState state : states) {
			final int meta = block.getMetaFromState(state);
			
			ModelResourceLocation location = usingMapper ? locations.get(state) : getModelLocation(loc, state);
			if (location == null) throw new RuntimeException("No model for state: "+state);

			//System.out.println("Putting "+location+" for "+item+'@'+meta);
			ModelLoader.setCustomModelResourceLocation(item, meta, location);
		}
	}
	
	//Items>
	public static void registerTexture(Item item, String name) {
		registerTexture(item, 0, name);
	}

	public static void registerTexture(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta, getModelLocation(name));
	}

	public static ModelResourceLocation getModelLocation(String name) {
		return new ModelResourceLocation(Extrabiomes.TEXTURE_PATH + name, null);
	}
}