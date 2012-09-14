/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.trees;

import static extrabiomes.trees.TreeBlocks.Type.ACACIA;
import static extrabiomes.trees.TreeBlocks.Type.BROWN;
import static extrabiomes.trees.TreeBlocks.Type.FIR;
import static extrabiomes.trees.TreeBlocks.Type.ORANGE;
import static extrabiomes.trees.TreeBlocks.Type.PURPLE;
import static extrabiomes.trees.TreeBlocks.Type.REDWOOD;
import static extrabiomes.trees.TreeBlocks.Type.YELLOW;

import java.util.EnumMap;
import java.util.Set;
import java.util.TreeSet;

import net.minecraft.src.Block;

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

	public static void init() {
		setBlocks(BROWN, Block.wood.blockID, 0, Block.leaves.blockID, 0);
		setBlocks(ORANGE, Block.wood.blockID, 0, Block.leaves.blockID,
				0);
		setBlocks(PURPLE, Block.wood.blockID, 0, Block.leaves.blockID,
				0);
		setBlocks(YELLOW, Block.wood.blockID, 0, Block.leaves.blockID,
				0);
		setBlocks(FIR, Block.wood.blockID, 1, Block.leaves.blockID, 1);
		setBlocks(REDWOOD, Block.wood.blockID, 1, Block.leaves.blockID,
				0);
		setBlocks(ACACIA, Block.wood.blockID, 0, Block.leaves.blockID,
				0);

		treesCanGrowOnIDs.add(Integer.valueOf(Block.grass.blockID));
		treesCanGrowOnIDs.add(Integer.valueOf(Block.dirt.blockID));
		treesCanGrowOnIDs.add(Integer
				.valueOf(Block.tilledField.blockID));
	}

	public static void setBlocks(Type type, int woodID, int woodMeta,
			int leafID, int leafMeta)
	{
		nodeMap.put(type, new Node(woodID, woodMeta, leafID, leafMeta));
	}
}
