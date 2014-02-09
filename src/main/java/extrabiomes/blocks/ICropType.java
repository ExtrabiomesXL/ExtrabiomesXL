package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public interface ICropType {
	public Icon getStageIcon(int stage);
	public void setStageIcons(ArrayList<Icon> icons);
	
    public Item getSeedItem();
    public Item getCropItem();
	
	public void setSeedItem(Item seed);
	public void setCropItem(Item crop);

	public String name();
}
