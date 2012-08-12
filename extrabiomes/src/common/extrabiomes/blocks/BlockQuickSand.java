
package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockQuickSand extends Block implements ITextureProvider {

	public BlockQuickSand(final int id) {
		super(id, 1, Material.sand);
		setHardness(4.0F);
		setStepSound(Block.soundSandFootstep);
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(
			final World world, final int x, final int y, final int z)
	{
		return null;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(final World world,
			final int x, final int y, final int z, final Entity entity)
	{
		entity.setInWeb();
	}

}
