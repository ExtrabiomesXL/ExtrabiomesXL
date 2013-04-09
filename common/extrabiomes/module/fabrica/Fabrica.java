/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.lib.Element;
import extrabiomes.lib.ItemSettings;
import extrabiomes.module.fabrica.block.BlockManager;
import extrabiomes.module.fabrica.block.ItemPaste;
import extrabiomes.module.fabrica.scarecrow.EntityScarecrow;
import extrabiomes.module.fabrica.scarecrow.ItemScarecrow;

public class Fabrica {

    private int scarecrowID = 0;
    private int pasteID     = 0;

    @ForgeSubscribe(priority = EventPriority.LOW)
    public void init(ModuleInitEvent event) throws InstantiationException, IllegalAccessException {
        BlockManager.init();

        if (scarecrowID > 0) {
            Stuff.scarecrow = Optional.of(new ItemScarecrow(scarecrowID).setUnlocalizedName(
                    ItemScarecrow.NAME)/*.setIconIndex(96)*/);

            final int scarecrowEntityID = Extrabiomes.proxy.findGlobalUniqueEntityId();
            Extrabiomes.proxy.registerEntityID(EntityScarecrow.class, ItemScarecrow.NAME,
                    scarecrowEntityID);
            Extrabiomes.proxy.registerEntity(EntityScarecrow.class, ItemScarecrow.NAME,
                    Extrabiomes.instance, scarecrowEntityID, 300, 2, true);

            final IRecipe recipe = new ShapedOreRecipe(Stuff.scarecrow.get(), new String[] { " p ",
                    "sms", " s " }, 'p', Block.pumpkin, 'm', Block.melon, 's', Item.stick);
            Extrabiomes.proxy.addRecipe(recipe);
        }

        if (pasteID > 0) {
            Stuff.paste = Optional.of(new ItemPaste(pasteID).setUnlocalizedName("extrabiomes.paste")
                    /*.setIconIndex(111)*/.setCreativeTab(Extrabiomes.tabsEBXL));

            if (Element.TINY_CACTUS.isPresent()) {
                IRecipe recipe = new ShapelessOreRecipe(Stuff.paste.get(), Block.cactus);
                Extrabiomes.proxy.addRecipe(recipe);

                recipe = new ShapelessOreRecipe(Stuff.paste.get(), Element.TINY_CACTUS.get(),
                        Element.TINY_CACTUS.get(), Element.TINY_CACTUS.get(), Element.TINY_CACTUS.get());
                Extrabiomes.proxy.addRecipe(recipe);

                Extrabiomes.proxy.addSmelting(Stuff.paste.get().itemID, 0, new ItemStack(
                        Item.dyePowder, 1, 2), 0.2F);
            }
        }
    }

    @ForgeSubscribe(priority = EventPriority.LOW)
    public void preInit(ModulePreInitEvent event) throws Exception {
        BlockManager.preInit();
        scarecrowID = ItemSettings.SCARECROW.getID();
        pasteID = ItemSettings.PASTE.getID();

        if (scarecrowID > 0) Extrabiomes.proxy.registerScarecrowRendering();
    }

}
