package forestry.api.core;

import java.util.ArrayList;

public enum EnumTemperature
{
    NONE("None", 0),
    ICY("Icy", 8),
    COLD("Cold", 6),
    NORMAL("Normal", 1),
    WARM("Warm", 4),
    HOT("Hot", 2),
    HELLISH("Hellish", 10);

    public static ArrayList icyBiomeIds = new ArrayList();
    public static ArrayList coldBiomeIds = new ArrayList();
    public static ArrayList normalBiomeIds = new ArrayList();
    public static ArrayList warmBiomeIds = new ArrayList();
    public static ArrayList hotBiomeIds = new ArrayList();
    public static ArrayList hellishBiomeIds = new ArrayList();
    public final String name;
    public final int iconIndex;

    private EnumTemperature(String s1, int j)
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

    public static ArrayList<Integer> getBiomeIds(EnumTemperature temperature) {

		switch(temperature) {
		case ICY:
			return icyBiomeIds;
		case COLD:
			return coldBiomeIds;
		case WARM:
			return warmBiomeIds;
		case HOT:
			return hotBiomeIds;
		case HELLISH:
			return hellishBiomeIds;
		case NORMAL:
		default:
			return normalBiomeIds;
		}

	}
}
