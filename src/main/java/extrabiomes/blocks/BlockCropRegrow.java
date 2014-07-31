package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCropRegrow extends BlockCropBasic {

	public static final int	REGROW_META	= 4;

	public enum CropType implements ICropType {
		STRAWBERRY(RENDER_TYPE_FLOWER);
		
		private ArrayList<IIcon> IIcons;
		private ItemStack		seed;
		private Item crop;
		private final int		renderType;

		private CropType(Integer renderType) {
			this.renderType = (renderType == null ? DEFAULT_RENDER_TYPE : renderType);
		}
		
		@Override
		public IIcon getStageIIcon(int stage) {
			return IIcons.get(stage);
		}
		@Override
		public void setStageIIcons(ArrayList<IIcon> IIcons) {
			this.IIcons = IIcons;
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
	
	public BlockCropRegrow(CropType type) {
		super(type);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y,
			int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		// for now, regrowers only ever produce one item
		if (meta >= MAX_GROWTH_STAGE) {
			ret.add(new ItemStack(this.getCropItem(), 1, 0));
		} else {
			ret.add(this.getSeedItem());
		}

		return ret;
	}

	/**
	 * Increase hardness for grown crops so they don't break on accident.
	 */
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		if (world.getBlockMetadata(x, y, z) >= REGROW_META)
			return 0.5f;
		return this.blockHardness;
	}

	/**
	 * Handle harvesting this crop if it is ready.
	 * 
	 * @return False if a server chose not to harvest.
	 */
	public boolean doHarvest(World world, int x, int y, int z,
			EntityPlayer player) {
		if (world.isRemote) return true;

		int growth = world.getBlockMetadata(x, y, z);
		if (growth >= MAX_GROWTH_STAGE) {
			EntityItem drop = new EntityItem(world, player.posX,
					player.posY - 1.0, player.posZ, new ItemStack(
							this.getCropItem(), 1, 0));
			// spawn the drop, then force collide it with the player
			world.spawnEntityInWorld(drop);
			drop.onCollideWithPlayer(player);

			// revert the meta on the block to our regrow stage
			doRegrow(world, x, y, z, growth);
			return true;
		}

		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		return doHarvest(world, x, y, z, player);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z,
			EntityPlayer player) {
		doHarvest(world, x, y, z, player);
	}

	/**
	 * Replace the block at half growth.
	 */
	public void doRegrow(World world, int x, int y, int z, int meta) {
		final int newMeta = meta > REGROW_META ? REGROW_META : meta;
		world.setBlock(x, y, z, this, newMeta, 3);
	}
}
