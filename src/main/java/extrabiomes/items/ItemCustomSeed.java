package extrabiomes.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class ItemCustomSeed extends Item implements IPlantable {

	public enum SeedType {
		STRAWBERRY(0);

		public final int		meta;
		public BlockFlower	cropType;
		public Icon				icon;

		private SeedType(int meta) {
			this.meta = meta;
		}
	}

	private final int	NUM_SEEDS;

	public ItemCustomSeed(int itemID) {
		super(itemID);

		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("extrabiomes.seed");

		NUM_SEEDS = SeedType.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for (SeedType type : SeedType.values()) {
			final String iconPath = Extrabiomes.TEXTURE_PATH + "seed_" + type.name().toLowerCase();
			type.icon = iconRegister.registerIcon(iconPath);
		}
	}

	@Override
	public int getMetadata(int meta) {
		return MathHelper.clamp_int(meta, 0, NUM_SEEDS);
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		return getSeedType(meta).icon;
	}

	public SeedType getSeedType(int meta) {
		return SeedType.values()[getMetadata(meta)];
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		final SeedType seed = getSeedType(itemStack.getItemDamage());
		return super.getUnlocalizedName() + "." + seed.name().toLowerCase();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for (SeedType type : SeedType.values()) {
			list.add(new ItemStack(itemID, 1, type.meta));
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float px, float py,
			float pz) {

		if (side != ForgeDirection.UP.ordinal()) {
			return false;
		} else if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
			final int soilID = world.getBlockId(x, y, z);
			Block soil = Block.blocksList[soilID];

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this)
					&& world.isAirBlock(x, y + 1, z)) {
				
				final SeedType seed = getSeedType(itemStack.getItemDamage()); 
				
				world.setBlock(x, y + 1, z, seed.cropType.blockID);
				--itemStack.stackSize;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return 0;
	}

}
