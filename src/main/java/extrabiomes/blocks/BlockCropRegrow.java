package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockCropRegrow extends BlockCropBasic {

	public static final int	REGROW_META	= 4;

	public enum CropType implements ICropType {
		STRAWBERRY(RENDER_TYPE_FLOWER);
		
		private ArrayList<Icon> icons;		
		private ItemStack		seed;
		private Item crop;
		private final int		renderType;

		private CropType(Integer renderType) {
			this.renderType = (renderType == null ? DEFAULT_RENDER_TYPE : renderType);
		}
		
		@Override
		public Icon getStageIcon(int stage) {
			return icons.get(stage);
		}
		@Override
		public void setStageIcons(ArrayList<Icon> icons) {
			this.icons = icons;
		}
		
	    @Override
		public ItemStack getSeedItem() {
	    	return seed;
	    }
	    @Override
		public Item getCropItem() {
	    	return crop;
	    }

		@Override
		public void setSeedItem(ItemStack seed) {
			this.seed = seed;
		}
		@Override
		public void setCropItem(Item crop) {
			this.crop = crop;
		}

		@Override
		public int getRenderType() {
			return renderType;
		}
	}
	
	public BlockCropRegrow(int blockID, CropType type) {
		super(blockID, type);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y,
			int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		// for now, regrowers only ever produce one item
		if (meta >= MAX_GROWTH_STAGE) {
			ret.add(new ItemStack(this.getCropItem(), 1, 0));
		}

		return ret;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		if (!world.isRemote) {
			doRegrow(world, x, y, z, meta);
		}
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}

	/**
	 * Replace the block at half growth.
	 */
	public void doRegrow(World world, int x, int y, int z, int meta) {
		final int newMeta = meta > REGROW_META ? REGROW_META : meta;
		world.setBlock(x, y, z, blockID, newMeta, 1 | 2 | 4);
	}
}
