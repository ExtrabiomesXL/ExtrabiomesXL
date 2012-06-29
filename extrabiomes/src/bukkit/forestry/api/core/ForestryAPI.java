package forestry.api.core;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.server.ItemStack;

public class ForestryAPI
{
    public static ArrayList loggerWindfall = new ArrayList();
    private static ArrayList wrenchs = new ArrayList();

    public ForestryAPI()
    {
    }

    public static boolean registerWrench(ItemStack itemstack)
    {
        ItemStack itemstack1 = new ItemStack(itemstack.id, 0, itemstack.getData());

        for (Iterator iterator = wrenchs.iterator(); iterator.hasNext();)
        {
            ItemStack itemstack2 = (ItemStack)iterator.next();

            if (itemstack2.doMaterialsMatch(itemstack))
            {
                return false;
            }
        }

        wrenchs.add(itemstack1);
        return true;
    }

    public static boolean isWrench(ItemStack itemstack)
    {
        for (Iterator iterator = wrenchs.iterator(); iterator.hasNext();)
        {
            ItemStack itemstack1 = (ItemStack)iterator.next();

            if (itemstack1.doMaterialsMatch(itemstack))
            {
                return true;
            }
        }

        return false;
    }
}
