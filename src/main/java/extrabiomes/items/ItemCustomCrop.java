package extrabiomes.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class ItemCustomCrop extends ItemFood {

	public static final int		DEFAULT_HUNGER		= 2;	// 1.0 meat
	public static final float	DEFAULT_SATURATION	= 0.6f;

	public enum CropType {
		STRAWBERRY(0, null, null);

		public final int	meta;
		public final int	hunger;
		public final float	saturation;
		public Icon			icon;
		
		public enum Edible {
			ALWAYS, YES, NO;
		};
		public Edible	edible	= Edible.YES;

		private CropType(int meta, Integer hunger, Float saturation) {
			this.meta = meta;
			this.hunger = (hunger == null ? DEFAULT_HUNGER : hunger);
			this.saturation = (saturation == null ? DEFAULT_SATURATION : saturation);
		}
		
		private CropType(int meta, Integer hunger, Float saturation, Edible edible) {
			this(meta, hunger, saturation);
			this.edible = edible;
		}
	}

	private final int	NUM_CROPS;

	public ItemCustomCrop(int itemID) {
		super(itemID, DEFAULT_HUNGER, DEFAULT_SATURATION, false);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("extrabiomes.crop");

		NUM_CROPS = CropType.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for (CropType type : CropType.values()) {
			final String iconPath = Extrabiomes.TEXTURE_PATH + type.name().toLowerCase();
			type.icon = iconRegister.registerIcon(iconPath);
		}
	}

	@Override
	public int getMetadata(int meta) {
		return MathHelper.clamp_int(meta, 0, NUM_CROPS);
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		return getCropType(meta).icon;
	}

	public CropType getCropType(int meta) {
		return CropType.values()[getMetadata(meta)];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		final CropType crop = getCropType(itemStack.getItemDamage());
		return super.getUnlocalizedName() + "." + crop.name().toLowerCase();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for (CropType type : CropType.values()) {
			list.add(new ItemStack(itemID, 1, type.meta));
		}
	}

	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
		final CropType crop = getCropType(itemStack.getItemDamage());

		player.getFoodStats().addStats(crop.hunger, crop.saturation);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(itemStack, world, player);

		--itemStack.stackSize;
		return itemStack;
    }
	
    @Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		final CropType crop = getCropType(itemStack.getItemDamage());
		
		// TODO: check for planting root crops

		if (player.canEat(crop.edible == CropType.Edible.ALWAYS))
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }

        return itemStack;
    }

}
