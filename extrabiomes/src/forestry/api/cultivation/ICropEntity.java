package forestry.api.cultivation;

import java.util.ArrayList;

public abstract interface ICropEntity {

	public abstract boolean isHarvestable();
	public abstract int[] getNextPosition();
	public abstract ArrayList doHarvest();
}
