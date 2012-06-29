
package extrabiomes.api;

import net.minecraft.server.WorldGenerator;

public interface ITreeFactory {

	public enum TreeType {
		BROWN_AUTUMN, BROWN_AUTUMN_BIG, ORANGE_AUTUMN, ORANGE_AUTUMN_BIG, PURPLE_AUTUMN, PURPLE_AUTUMN_BIG, YELLOW_AUTUMN, YELLOW_AUTUMN_BIG, FIR, FIR_HUGE, REDWOOD, ACACIA
	}

	public abstract WorldGenerator makeTreeGenerator(boolean flag,
			TreeType treetype);

	public abstract void registerTreeGen(TreeType treetype,
			WorldGenerator worldgenerator, boolean flag);
}
