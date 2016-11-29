package extrabiomes.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public interface ICropType {
	/*public IIcon getStageIIcon(int stage);
	public void setStageIIcons(ArrayList<IIcon> IIcons);*/
	
	public ItemStack getSeedItem();
    public Item getCropItem();
	
	public void setSeedItem(ItemStack seed);
	public void setCropItem(Item crop);

	public int getRenderType();

	public String name();
}
