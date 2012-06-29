package forestry.api.core;

import java.util.ArrayList;

public enum EnumHumidity
{
    ARID("Arid", 2),
    NORMAL("Normal", 1),
    DAMP("Damp", 4);

    public static ArrayList aridBiomeIds = new ArrayList();
    public static ArrayList dampBiomeIds = new ArrayList();
    public static ArrayList normalBiomeIds = new ArrayList();
    public final String name;
    public final int iconIndex;

    private EnumHumidity(String s1, int j)
    {
        name = s1;
        iconIndex = j;
    }

    public String getName()
    {
        return name;
    }

    public int getIconIndex()
    {
        return iconIndex;
    }

    public static ArrayList getBiomeIds(EnumHumidity humidity)
    {
		switch(humidity) {
			case ARID:
				return aridBiomeIds;
			case DAMP:
				return dampBiomeIds;
			case NORMAL:
			default:
				return normalBiomeIds;
			}
		}
}
