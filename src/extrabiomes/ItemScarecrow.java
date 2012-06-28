package extrabiomes;

import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementList;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Facing;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public class ItemScarecrow extends Item implements ITextureProvider {

	static Achievement buildScarecrow;
	
	public static boolean func_48440_a(World par0World, int par1, double par2,
			double par4, double par6) {
		{
			Entity var8 = EntityList.createEntityByName(
					"scarecrow", par0World);

			if (var8 != null) {
				var8.setLocationAndAngles(par2, par4, par6,
						par0World.rand.nextFloat() * 360.0F, 0.0F);
				par0World.spawnEntityInWorld(var8);
			}

			return var8 != null;
		}
	}

	public ItemScarecrow(int par1) {
		super(par1);
		buildScarecrow = new Achievement(3070, "buildScarecrow", -1, 2, this, AchievementList.buildWorkBench).registerAchievement();
        ModLoader.addAchievementDesc(buildScarecrow, "If I Only Had A Brain!", "Use a pumpkin, a melon and sticks to make a scarecrow.")	;
	}

	@Override
	public int func_46057_a(int par1, int par2) {
		return par2 > 0 ? super.func_46057_a(par1, par2) + 16 : super
				.func_46057_a(par1, par2);
	}

	@Override
	public boolean func_46058_c() {
		return true;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		par3EntityPlayer.addStat(buildScarecrow, 1);
		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7) {
		if (par3World.isRemote) {
			return true;
		} else {
			int var8 = par3World.getBlockId(par4, par5, par6);
			par4 += Facing.offsetsXForSide[par7];
			par5 += Facing.offsetsYForSide[par7];
			par6 += Facing.offsetsZForSide[par7];
			double var9 = 0.0D;

			if (par7 == 1 && var8 == Block.fence.blockID
					|| var8 == Block.netherFence.blockID) {
				var9 = 0.5D;
			}

			if (func_48440_a(par3World, par1ItemStack.getItemDamage(),
					par4 + 0.5D, par5 + var9, par6 + 0.5D)
					/*&& !par2EntityPlayer.capabilities.isCreativeMode*/) {
				--par1ItemStack.stackSize;
			}

			return true;
		}
	}
}