
package extrabiomes.handlers;

import net.minecraft.src.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import extrabiomes.Extrabiomes;
import extrabiomes.lib.Element;

public abstract class RecipeHandler {

    private static void writeLogTurnerRecipe() {
        if (!Element.LOGTURNER.isPresent()) return;

        final IRecipe recipe = new ShapedOreRecipe(Element.LOGTURNER.get(), new String[] { "ss",
                " s", "ss" }, 's', "stickWood");
        Extrabiomes.proxy.addRecipe(recipe);
    }

    public static void writeRecipes() {
        writeLogTurnerRecipe();
    }

}
