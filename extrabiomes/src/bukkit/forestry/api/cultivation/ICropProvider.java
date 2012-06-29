package forestry.api.cultivation;

import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public interface ICropProvider
{
    public abstract boolean isGermling(ItemStack itemstack);

    public abstract boolean isCrop(World world, int i, int j, int k);

    public abstract ItemStack[] getWindfall();

    public abstract boolean doPlant(ItemStack itemstack, World world, int i, int j, int k);

    public abstract ICropEntity getCrop(World world, int i, int j, int k);
}
