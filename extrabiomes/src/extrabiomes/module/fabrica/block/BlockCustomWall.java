/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.BlockWall;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.api.Stuff;

public class BlockCustomWall extends BlockWall {

	public enum BlockType {
		RED_COBBLE(0);

		private final int	metadata;

		BlockType(int metadata) {
			this.metadata = metadata;
		}

		public int metadata() {
			return metadata;
		}
	}

	public BlockCustomWall(int id) {
		super(id, Stuff.redRock.get());
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		for (final BlockType blockType : BlockType.values())
			itemList.add(new ItemStack(this, 1, blockType.metadata()));
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
		for (final BlockType blockType : BlockType.values())
			itemList.add(new ItemStack(this, 1, blockType.metadata()));
	}

}
