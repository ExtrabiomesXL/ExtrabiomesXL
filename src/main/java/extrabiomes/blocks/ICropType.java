package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.util.Icon;

public interface ICropType {
	public Icon getStageIcon(int stage);
	public void setStageIcons(ArrayList<Icon> icons);
	public String name();
}
