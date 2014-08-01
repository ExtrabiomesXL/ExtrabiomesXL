package extrabiomes.items;

import java.util.List;

import net.minecraft.block.BlockColored;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;

public class ItemCustomDye extends Item {

	public enum Color
	{
		BLACK("black", 0, 0x1E1B1B, 0),
		BLUE("blue", 1, 0x253192, 4),
		BROWN("brown", 2, 0x51301A, 3),
		WHITE("white", 3, 0xF0F0F0, 15);
		
		public final String	name;
		public final int	meta;
		public final int	hex;
		public final int	mcDamage;

		private Color(String name, int meta, int hex, int mcDamage)
		{
			this.name = name;
			this.meta = meta;
			this.hex = hex;
			this.mcDamage = mcDamage;
		}
	}
	
	private static Element[] elements = {Element.DYE_BLACK, Element.DYE_BLUE, Element.DYE_BROWN, Element.DYE_WHITE};
	
    @SideOnly(Side.CLIENT)
    private IIcon[] dyeIIcons;
	
	public ItemCustomDye() {
		super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);

		if (Color.values().length != elements.length) {
			LogHelper.severe("Dye color vs elements count mismatch!");
		}
	}

	public void init() {
		for( int idx = 0; idx < elements.length; ++idx ) {
			final Color color = Color.values()[idx];
			final Element element = elements[idx];
			
			LogHelper.info(color + " = " + element);

			element.set(new ItemStack(this, 1, color.meta));
			OreDictionary.registerOre("dye"+StringUtils.capitalize(color.name), element.get());
		}
		/*
		 * // make sure wool recipes are good
		 * OreDictionary.initVanillaEntries();
		 */
	}

	/**
	 * Returns true if the item can be used on the given entity, e.g. shears on
	 * sheep.
	 */
	@Override
	public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target) {
		if (target instanceof EntitySheep) {
			final EntitySheep sheep = (EntitySheep) target;
			final int damage = itemStack.getItemDamage();
			final Color color = Color.values()[damage];
			final int i = BlockColored.func_150031_c(color.mcDamage);

			LogHelper.info("Dying sheep " + damage + "/" + color + " = " + i);

			if (!sheep.getSheared() && sheep.getFleeceColor() != i) {
				sheep.setFleeceColor(i);
				--itemStack.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Gets an IIcon index based on an item's damage value
	 */
	public IIcon getIconFromDamage(int meta) {
		int j = MathHelper.clamp_int(meta, 0, Color.values().length);
		return this.dyeIIcons[j];
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, Color.values().length);
		return super.getUnlocalizedName() + "." + Color.values()[i].name;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * returns a list of items with the same ID, but different meta (eg: vanilla dye returns 16 items)
	 */
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int j = 0; j < elements.length; ++j) {
			list.add(elements[j].get());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		final Color[] colors = Color.values();
		this.dyeIIcons = new IIcon[colors.length];

		for (int i = 0; i < colors.length; ++i) {
			final String IIconPath = Extrabiomes.TEXTURE_PATH + "dye_" + colors[i].name;
			// LogHelper.info("Registering " + IIconPath);
			this.dyeIIcons[i] = iconRegister.registerIcon(IIconPath);
		}
	}
}
