
package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.server.Block;
import net.minecraft.server.EnumCreatureType;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.ModLoader;
import net.minecraft.server.World;
import extrabiomes.api.TerrainGenManager;
import forge.ITextureProvider;

public class BlockRedRock extends Block implements ITextureProvider {
	public BlockRedRock(int i) {
		super(i, 2, Material.STONE);
		c(1.5F);
		b(2.0F);
		TerrainGenManager.blockMountainRidge = this;
	}

	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this));
	}

	public boolean canCreatureSpawn(EnumCreatureType enumcreaturetype,
			World world, int i, int j, int k)
	{
		return true;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}
}
