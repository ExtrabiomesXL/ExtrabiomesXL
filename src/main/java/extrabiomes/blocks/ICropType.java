package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public interface ICropType {
	public Icon getStageIcon(int stage);
	public void setStageIcons(ArrayList<Icon> icons);
	
	public ItemStack getSeedItem();
    public Item getCropItem();
	
	public void setSeedItem(ItemStack seed);
	public void setCropItem(Item crop);

	public int getRenderType();

	public String name();
}
