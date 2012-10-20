/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica;

import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.IModule;
import extrabiomes.api.Stuff;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.fabrica.block.BlockManager;
import extrabiomes.module.fabrica.recipe.CookBook;
import extrabiomes.module.fabrica.recipe.WoodCookBook;
import extrabiomes.module.fabrica.scarecrow.EntityScarecrow;
import extrabiomes.module.fabrica.scarecrow.ItemScarecrow;

public class Fabrica implements IModule {

	private static int	scarecrowID	= 0;

	@Override
	public void init() throws InstantiationException,
			IllegalAccessException
	{
		BlockManager.init();
		CookBook.init();
		WoodCookBook.init();

		if (scarecrowID > 0) {
			final String NAME = "Scarecrow";
			Stuff.scarecrow = Optional
					.of(new ItemScarecrow(scarecrowID)
							.setItemName(NAME).setIconIndex(96));

			Extrabiomes.proxy.addName(Stuff.scarecrow.get(),
					"Scarecrow");

			final int scarecrowEntityID = Extrabiomes.proxy
					.findGlobalUniqueEntityId();
			Extrabiomes.proxy.registerEntityID(EntityScarecrow.class,
					NAME, scarecrowEntityID);
			Extrabiomes.proxy.registerEntity(EntityScarecrow.class,
					NAME, Extrabiomes.instance, scarecrowEntityID, 300,
					2, true);

			final IRecipe recipe = new ShapedOreRecipe(
					Stuff.scarecrow.get(), new String[] { " p ", "sms",
							" s " }, 'p', Block.pumpkin, 'm',
					Block.melon, 's', Item.stick);
			Extrabiomes.proxy.addRecipe(recipe);
		}
	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		BlockManager.preInit(config);
		scarecrowID = config.getItem("scarecrow.id",
				Extrabiomes.getNextDefaultItemID()).getInt(0);

		if (scarecrowID > 0)
			Extrabiomes.proxy.registerScarecrowRendering();

	}

}
