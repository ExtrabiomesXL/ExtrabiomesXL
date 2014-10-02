package extrabiomes.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;

public class ItemCustomFood extends ItemFood {

	public static final int		DEFAULT_HUNGER		= 2;	// 1.0 meat
	public static final float	DEFAULT_SATURATION	= 0.6f;

	public enum FoodType {
		// @formatter:off
		//						 idx   hung  sat   text
		CHOCOLATE			 	(0,    3,    0.5f, "chocolate" ),
		CHOCOLATE_STRAWBERRY	(1,    7,    1.0f, "ch_strawberry" );
		// @formatter:on
		
		public final int	meta;
		public final int	hunger;
		public final float	saturation;
		public IIcon		IIcon;
		public final String texture;
		
		public boolean 		alwaysEdible = false;
		
		private FoodType(int meta, Integer hunger, Float saturation, String texture ) {
			this.meta = meta;
			this.hunger = (hunger == null ? DEFAULT_HUNGER : hunger);
			this.saturation = (saturation == null ? DEFAULT_SATURATION : saturation);
			this.texture = texture;
		}
		
		private FoodType(int meta, Integer hunger, Float saturation, String texture, boolean alwaysEdible) {
			this(meta, hunger, saturation, texture);
			this.alwaysEdible = alwaysEdible;
		}
	}

	private final int	NUM_FOODS;

	public ItemCustomFood() {
		super(DEFAULT_HUNGER, DEFAULT_SATURATION, false);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("extrabiomes.food");
		setCreativeTab(Extrabiomes.tabsEBXL);

		NUM_FOODS = FoodType.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		for (FoodType type : FoodType.values()) {
			final String IIconPath = Extrabiomes.TEXTURE_PATH + type.texture;
			type.IIcon = iconRegister.registerIcon(IIconPath);
		}
	}

	@Override
	public int getMetadata(int meta) {
		return MathHelper.clamp_int(meta, 0, NUM_FOODS);
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return getFoodType(meta).IIcon;
	}

	public FoodType getFoodType(int meta) {
		return FoodType.values()[getMetadata(meta)];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		final FoodType type = getFoodType(itemStack.getItemDamage());
		return super.getUnlocalizedName() + "." + type.name().toLowerCase();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for (FoodType type : FoodType.values()) {
			list.add(new ItemStack(item, 1, type.meta));
		}
	}

	@Override
	public int func_150905_g(ItemStack itemStack)
	{
		final FoodType type = getFoodType(itemStack.getItemDamage());
		return type.hunger;
	}

	@Override
	public float func_150906_h(ItemStack itemStack)
	{
		final FoodType type = getFoodType(itemStack.getItemDamage());
		return type.saturation;
	}
	
    @Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		final FoodType type = getFoodType(itemStack.getItemDamage());
		
		if (player.canEat(type.alwaysEdible)) {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }

        return itemStack;
    }

}
