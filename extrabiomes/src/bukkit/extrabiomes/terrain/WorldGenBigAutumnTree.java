
package extrabiomes.terrain;

import java.util.Random;

import org.bukkit.BlockChangeDelegate;

import net.minecraft.server.MathHelper;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import extrabiomes.api.TerrainGenManager;

public class WorldGenBigAutumnTree extends WorldGenerator {
	static final byte		otherCoordPairs[]	= { 2, 0, 0, 1, 2, 1 };
	Random					rand;
	BlockChangeDelegate					worldObj;
	int						basePos[]			= { 0, 0, 0 };
	int						heightLimit;
	int						height;
	double					heightAttenuation;
	double					branchDensity;
	double					branchSlope;
	double					scaleWidth;
	double					leafDensity;
	int						trunkSize;
	int						heightLimitLimit;
	int						leafDistanceLimit;
	int						leafNodes[][];
	private final ItemStack	leaf;
	private final ItemStack	wood;

	public WorldGenBigAutumnTree(boolean flag, ItemStack itemstack,
			ItemStack itemstack1)
	{
		super(flag);
		rand = new Random();
		heightLimit = 0;
		heightAttenuation = 0.61799999999999999D;
		branchDensity = 1.0D;
		branchSlope = 0.38100000000000001D;
		scaleWidth = 1.0D;
		leafDensity = 1.0D;
		trunkSize = 1;
		heightLimitLimit = 12;
		leafDistanceLimit = 4;
		leaf = itemstack;
		wood = itemstack1;
	}

	/**
	 * Rescales the generator settings, only used in WorldGenBigTree
	 */
	public void a(double d, double d1, double d2) {
		heightLimitLimit = (int) (d * 12D);

		if (d > 0.5D) leafDistanceLimit = 5;

		scaleWidth = d1;
		leafDensity = d2;
	}

	@Override
	public boolean a(World arg0, Random arg1, int arg2, int arg3,
			int arg4)
	{
		return generate((BlockChangeDelegate) arg0, arg1, arg2, arg3,
				arg4);
	}

	public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k) {
		worldObj = world;
		final long l = random.nextLong();
		rand.setSeed(l);
		basePos[0] = i;
		basePos[1] = j;
		basePos[2] = k;

		if (heightLimit == 0)
			heightLimit = 5 + rand.nextInt(heightLimitLimit);

		if (!validTreeLocation())
			return false;
		else {
			generateLeafNodeList();
			generateLeaves();
			generateTrunk();
			generateLeafNodeBases();
			return true;
		}
	}

	int checkBlockLine(int ai[], int ai1[]) {
		final int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int i = 0;

		for (; byte0 < 3; byte0++) {
			ai2[byte0] = ai1[byte0] - ai[byte0];

			if (Math.abs(ai2[byte0]) > Math.abs(ai2[i])) i = byte0;
		}

		if (ai2[i] == 0) return -1;

		final byte byte1 = otherCoordPairs[i];
		final byte byte2 = otherCoordPairs[i + 3];
		byte byte3;

		if (ai2[i] > 0)
			byte3 = 1;
		else
			byte3 = -1;

		final double d = (double) ai2[byte1] / (double) ai2[i];
		final double d1 = (double) ai2[byte2] / (double) ai2[i];
		final int ai3[] = { 0, 0, 0 };
		int j = 0;
		final int k = ai2[i] + byte3;

		do {
			if (j == k) break;

			ai3[i] = ai[i] + j;
			ai3[byte1] = MathHelper.floor(ai[byte1] + j * d);
			ai3[byte2] = MathHelper.floor(ai[byte2] + j * d1);
			final int l = worldObj.getTypeId(ai3[0], ai3[1], ai3[2]);

			if (Block.byId[l] != null
					&& !Block.byId[l].isLeaves(worldObj, ai3[0],
							ai3[1], ai3[2])) break;

			j += byte3;
		} while (true);

		return j != k ? Math.abs(j) : -1;
	}

	void generateLeafNode(int i, int j, int k) {
		int l = j;

		for (final int i1 = j + leafDistanceLimit; l < i1; l++) {
			final float f = leafSize(l - j);
			genTreeLayer(i, l, k, f);
		}
	}

	void generateLeafNodeBases() {
		int i = 0;
		final int j = leafNodes.length;
		final int ai[] = { basePos[0], basePos[1], basePos[2] };

		for (; i < j; i++) {
			final int ai1[] = leafNodes[i];
			final int ai2[] = { ai1[0], ai1[1], ai1[2] };
			ai[1] = ai1[3];
			final int k = ai[1] - basePos[1];

			if (leafNodeNeedsBase(k)) placeBlockLine(ai, ai2);
		}
	}

	void generateLeafNodeList() {
		height = (int) (heightLimit * heightAttenuation);

		if (height >= heightLimit) height = heightLimit - 1;

		int i = (int) (1.3819999999999999D + Math.pow(leafDensity
				* heightLimit / 13D, 2D));

		if (i < 1) i = 1;

		final int ai[][] = new int[i * heightLimit][4];
		int j = basePos[1] + heightLimit - leafDistanceLimit;
		int k = 1;
		final int l = basePos[1] + height;
		int i1 = j - basePos[1];
		ai[0][0] = basePos[0];
		ai[0][1] = j;
		ai[0][2] = basePos[2];
		ai[0][3] = l;
		j--;

		while (i1 >= 0) {
			int j1 = 0;
			final float f = layerSize(i1);

			if (f < 0.0F) {
				j--;
				i1--;
			} else {
				final double d = 0.5D;

				for (; j1 < i; j1++) {
					final double d1 = scaleWidth * f
							* (rand.nextFloat() + 0.32800000000000001D);
					final double d2 = rand.nextFloat() * 2D * Math.PI;
					final int k1 = MathHelper.floor(d1 * Math.sin(d2)
							+ basePos[0] + d);
					final int l1 = MathHelper.floor(d1 * Math.cos(d2)
							+ basePos[2] + d);
					final int ai1[] = { k1, j, l1 };
					final int ai2[] = { k1, j + leafDistanceLimit, l1 };

					if (checkBlockLine(ai1, ai2) != -1) continue;

					final int ai3[] = { basePos[0], basePos[1],
							basePos[2] };
					final double d3 = Math.sqrt(Math.pow(
							Math.abs(basePos[0] - ai1[0]), 2D)
							+ Math.pow(Math.abs(basePos[2] - ai1[2]),
									2D));
					final double d4 = d3 * branchSlope;

					if (ai1[1] - d4 > l)
						ai3[1] = l;
					else
						ai3[1] = (int) (ai1[1] - d4);

					if (checkBlockLine(ai3, ai1) == -1) {
						ai[k][0] = k1;
						ai[k][1] = j;
						ai[k][2] = l1;
						ai[k][3] = ai3[1];
						k++;
					}
				}

				j--;
				i1--;
			}
		}

		leafNodes = new int[k][4];
		System.arraycopy(ai, 0, leafNodes, 0, k);
	}

	void generateLeaves() {
		int i = 0;

		for (final int j = leafNodes.length; i < j; i++)
			generateLeafNode(leafNodes[i][0], leafNodes[i][1],
					leafNodes[i][2]);
	}

	void generateTrunk() {
		final int i = basePos[0];
		final int j = basePos[1];
		final int k = basePos[1] + height;
		final int l = basePos[2];
		final int ai[] = { i, j, l };
		final int ai1[] = { i, k, l };
		placeBlockLine(ai, ai1);

		if (trunkSize == 2) {
			ai[0]++;
			ai1[0]++;
			placeBlockLine(ai, ai1);
			ai[2]++;
			ai1[2]++;
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			placeBlockLine(ai, ai1);
		}
	}

	void genTreeLayer(int i, int j, int k, float f) {
		final int l = (int) (f + 0.61799999999999999D);
		final byte byte0 = otherCoordPairs[1];
		final byte byte1 = otherCoordPairs[4];
		final int ai[] = { i, j, k };
		final int ai1[] = { 0, 0, 0 };
		int i1 = -l;
		final int j1 = -l;
		ai1[1] = ai[1];

		for (; i1 <= l; i1++) {
			ai1[byte0] = ai[byte0] + i1;

			for (int k1 = -l; k1 <= l;) {
				final double d = Math.sqrt(Math.pow(
						Math.abs(i1) + 0.5D, 2D)
						+ Math.pow(Math.abs(k1) + 0.5D, 2D));

				if (d > f)
					k1++;
				else {
					ai1[byte1] = ai[byte1] + k1;
					final int l1 = worldObj.getTypeId(ai1[0], ai1[1],
							ai1[2]);

					if (Block.byId[l1] != null
							&& !Block.byId[l1].isLeaves(worldObj,
									ai1[0], ai1[1], ai1[2]))
						k1++;
					else {
						setTypeAndData(worldObj, ai1[0], ai1[1],
								ai1[2], leaf.getItem().id,
								leaf.getData());
						k1++;
					}
				}
			}
		}
	}

	float layerSize(int i) {
		if (i < heightLimit * 0.29999999999999999D) return -1.618F;

		final float f = heightLimit / 2.0F;
		final float f1 = heightLimit / 2.0F - i;
		float f2;

		if (f1 == 0.0F)
			f2 = f;
		else if (Math.abs(f1) >= f)
			f2 = 0.0F;
		else
			f2 = (float) Math.sqrt(Math.pow(Math.abs(f), 2D)
					- Math.pow(Math.abs(f1), 2D));

		f2 *= 0.5F;
		return f2;
	}

	boolean leafNodeNeedsBase(int i) {
		return i >= heightLimit * 0.20000000000000001D;
	}

	float leafSize(int i) {
		return i < 0 || i >= leafDistanceLimit ? -1F : i == 0
				|| i == leafDistanceLimit - 1 ? 2.0F : 3F;
	}

	void placeBlockLine(int ai[], int ai1[]) {
		final int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int i = 0;

		for (; byte0 < 3; byte0++) {
			ai2[byte0] = ai1[byte0] - ai[byte0];

			if (Math.abs(ai2[byte0]) > Math.abs(ai2[i])) i = byte0;
		}

		if (ai2[i] != 0) {
			final byte byte1 = otherCoordPairs[i];
			final byte byte2 = otherCoordPairs[i + 3];
			byte byte3;

			if (ai2[i] > 0)
				byte3 = 1;
			else
				byte3 = -1;

			final double d = (double) ai2[byte1] / (double) ai2[i];
			final double d1 = (double) ai2[byte2] / (double) ai2[i];
			final int ai3[] = { 0, 0, 0 };
			int j = 0;

			for (final int k = ai2[i] + byte3; j != k; j += byte3) {
				ai3[i] = MathHelper.floor((ai[i] + j) + 0.5D);
				ai3[byte1] = MathHelper.floor(ai[byte1] + j * d + 0.5D);
				ai3[byte2] = MathHelper
						.floor(ai[byte2] + j * d1 + 0.5D);
				final Block block = Block.byId[worldObj.getTypeId(
						ai3[0], ai3[1], ai3[2])];

				if (block == null
						|| block.isLeaves(worldObj, ai3[0], ai3[1],
								ai3[2]))
					setTypeAndData(worldObj, ai3[0], ai3[1], ai3[2],
							wood.getItem().id, wood.getData());
			}
		}
	}

	boolean validTreeLocation() {
		final int ai[] = { basePos[0], basePos[1], basePos[2] };
		final int ai1[] = { basePos[0], basePos[1] + heightLimit - 1,
				basePos[2] };
		final int i = worldObj.getTypeId(basePos[0], basePos[1] - 1,
				basePos[2]);

		if (!TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(i))) return false;

		final int j = checkBlockLine(ai, ai1);

		if (j == -1) return true;

		if (j < 6)
			return false;
		else {
			heightLimit = j;
			return true;
		}
	}
}
