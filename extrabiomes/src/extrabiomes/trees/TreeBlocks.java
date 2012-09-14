/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.trees;

import java.util.EnumMap;
import java.util.Set;
import java.util.TreeSet;

public class TreeBlocks {

	private static class Node {
		final int	woodID;
		final int	woodMeta;
		final int	leafID;
		final int	leafMeta;

		Node(int woodID, int woodMeta, int leafID, int leafMeta) {
			this.woodID = woodID;
			this.woodMeta = woodMeta;
			this.leafID = leafID;
			this.leafMeta = leafMeta;
		}
	}

	public enum Type {
		BROWN, ORANGE, PURPLE, YELLOW, FIR, REDWOOD, ACACIA
	}

	private static EnumMap<Type, Node>	nodeMap				= new EnumMap(
																	Type.class);

	public final static Set<Integer>	treesCanGrowOnIDs	= new TreeSet<Integer>();

	public static int getLeafID(Type type) {
		return nodeMap.get(type).leafID;
	}

	public static int getLeafMeta(Type type) {
		return nodeMap.get(type).leafMeta;
	}

	public static int getWoodID(Type type) {
		return nodeMap.get(type).woodID;
	}

	public static int getWoodMeta(Type type) {
		return nodeMap.get(type).woodMeta;
	}

	public static void setBlocks(Type type, int woodID, int woodMeta,
			int leafID, int leafMeta)
	{
		nodeMap.put(type, new Node(woodID, woodMeta, leafID, leafMeta));
	}
}
