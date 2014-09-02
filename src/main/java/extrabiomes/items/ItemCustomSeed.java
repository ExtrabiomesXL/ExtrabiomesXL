package extrabiomes.items;

import java.util.List;

import extrabiomes.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class ItemCustomSeed extends Item implements IPlantable {

	public enum SeedType {
		STRAWBERRY(0);

		public final int		meta;
		public BlockFlower	cropType;
		public IIcon				IIcon;

		private SeedType(int meta) {
			this.meta = meta;
		}
	}

	private final int	NUM_SEEDS;

	public ItemCustomSeed() {
		super();

		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("extrabiomes.seed");
		setCreativeTab(Extrabiomes.tabsEBXL);

		NUM_SEEDS = SeedType.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		for (SeedType type : SeedType.values()) {
			final String IIconPath = Extrabiomes.TEXTURE_PATH + "seed_" + type.name().toLowerCase();
			type.IIcon = iconRegister.registerIcon(IIconPath);
		}
	}

	@Override
	public int getMetadata(int meta) {
		return MathHelper.clamp_int(meta, 0, NUM_SEEDS);
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return getSeedType(meta).IIcon;
	}

	public SeedType getSeedType(int meta) {
		return SeedType.values()[getMetadata(meta)];
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		final SeedType seed = getSeedType(itemStack.getItemDamage());
		return super.getUnlocalizedName() + "." + seed.name().toLowerCase();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for (SeedType type : SeedType.values()) {
			list.add(new ItemStack(item, 1, type.meta));
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float px, float py,
			float pz) {

		if (side != ForgeDirection.UP.ordinal()) {
			return false;
		} else if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
			final Block soil = world.getBlock(x, y, z);

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this)
					&& world.isAirBlock(x, y + 1, z)) {
				
				final SeedType seed = getSeedType(itemStack.getItemDamage()); 
				if( seed == null || seed.cropType == null ) {
                    LogHelper.severe("Got NULL seed type or crop when planting " + itemStack);
                } else {
                    world.setBlock(x, y + 1, z, seed.cropType);
                    --itemStack.stackSize;
                    return true;
                }
			}
		}
        return false;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return null;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}

}
