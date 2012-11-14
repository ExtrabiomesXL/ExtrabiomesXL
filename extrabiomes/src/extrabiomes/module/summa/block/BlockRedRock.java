/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.module.summa.block;

import static extrabiomes.module.summa.block.BlockRedRock.BlockType.RED_COBBLE;
import static extrabiomes.module.summa.block.BlockRedRock.BlockType.RED_ROCK_BRICK;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.utility.IDRestrictionAnnotation;

@IDRestrictionAnnotation(maxIDRValue = 255)
public class BlockRedRock extends Block {

	public enum BlockType {
		RED_ROCK(0), RED_COBBLE(1), RED_ROCK_BRICK(2);

		private final int	metadata;

		BlockType(int metadata) {
			this.metadata = metadata;
		}

		public int metadata() {
			return metadata;
		}
	}

	public BlockRedRock(int id) {
		super(id, 2, Material.rock);
		setHardness(1.5F);
		setResistance(2.0F);

		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(Extrabiomes.extrabiomesTab);
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		for (final BlockType blockType : BlockType.values())
			itemList.add(new ItemStack(this, 1, blockType.metadata()));
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata == RED_ROCK_BRICK.metadata() ? RED_ROCK_BRICK
				.metadata() : RED_COBBLE.metadata();
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		final int meta = world.getBlockMetadata(x, y, z);
		return meta == RED_COBBLE.metadata() ? 2.0F : blockHardness;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return metadata == RED_ROCK_BRICK.metadata() ? 11
				: metadata == RED_COBBLE.metadata() ? 12 : super
						.getBlockTextureFromSideAndMetadata(side,
								metadata);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
		if (tab == Extrabiomes.extrabiomesTab)
			for (final BlockType blockType : BlockType.values())
				itemList.add(new ItemStack(this, 1, blockType
						.metadata()));
	}
}
