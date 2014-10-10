/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import cpw.mods.fml.common.eventhandler.EventPriority;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.lib.Element;
import extrabiomes.lib.ItemSettings;
import extrabiomes.lib.Reference;
import extrabiomes.module.fabrica.block.BlockManager;
import extrabiomes.module.fabrica.block.ItemPaste;
import extrabiomes.module.fabrica.scarecrow.EntityScarecrow;
import extrabiomes.module.fabrica.scarecrow.ItemScarecrow;

public class Fabrica
{
    
    private int scarecrowID = 0;
    private int pasteID     = 0;
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void init(ModuleInitEvent event) throws InstantiationException, IllegalAccessException
    {
        BlockManager.init();
        
        if (ItemSettings.SCARECROW.getEnabled())
        {
            //final int scarecrowEntityID = Extrabiomes.proxy.findGlobalUniqueEntityId();
            //Extrabiomes.proxy.registerEntityID(EntityScarecrow.class, ItemScarecrow.NAME, scarecrowEntityID);
            //Extrabiomes.proxy.registerEntity(EntityScarecrow.class, ItemScarecrow.NAME, Extrabiomes.instance, scarecrowEntityID, 300, 2, true);
            Extrabiomes.proxy.registerEntity(EntityScarecrow.class, "scarecrow", Extrabiomes.instance, 0, 300, 2, true);
            ItemScarecrow.ID = (String) EntityList.classToStringMapping.get(EntityScarecrow.class);
            
            final IRecipe recipe = new ShapedOreRecipe(Stuff.scarecrow.get(), new String[] { " p ", "sms", " s " }, 'p', Blocks.pumpkin, 'm', Blocks.melon_block, 's', Items.stick);
            Extrabiomes.proxy.addRecipe(recipe);
        }
        
        if (ItemSettings.PASTE.getEnabled())
        {
            if (Element.TINY_CACTUS.isPresent())
            {
                IRecipe recipe = new ShapelessOreRecipe(Stuff.paste.get(), Blocks.cactus);
                Extrabiomes.proxy.addRecipe(recipe);
                
                recipe = new ShapelessOreRecipe(Stuff.paste.get(), Element.TINY_CACTUS.get(), Element.TINY_CACTUS.get(), Element.TINY_CACTUS.get(), Element.TINY_CACTUS.get());
                Extrabiomes.proxy.addRecipe(recipe);
                
                Extrabiomes.proxy.addSmelting(Stuff.paste.get(), 0, new ItemStack(Items.dye, 1, 2), 0.2F);
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void preInit(ModulePreInitEvent event) throws Exception
    {
        BlockManager.preInit();
        
        if (ItemSettings.SCARECROW.getEnabled())
        {
            Extrabiomes.proxy.registerScarecrowRendering();
            Stuff.scarecrow = Optional.of(new ItemScarecrow().setUnlocalizedName(ItemScarecrow.NAME));
            GameRegistry.registerItem(Stuff.scarecrow.get(), ItemScarecrow.NAME, Reference.MOD_ID);
        }
        
        if (ItemSettings.PASTE.getEnabled())
        {
            Stuff.paste = Optional.of(new ItemPaste().setUnlocalizedName("extrabiomes.paste").setCreativeTab(Extrabiomes.tabsEBXL));
            GameRegistry.registerItem(Stuff.paste.get(), "extrabiomes.paste", Reference.MOD_ID);
        }
    }
    
}
