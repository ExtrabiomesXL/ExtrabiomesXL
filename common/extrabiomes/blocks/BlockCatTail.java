/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockCatTail extends BlockFlower {

	private Icon texture;
	
    public BlockCatTail(int id, int index, Material material) {
        super(id, material);
        disableStats();
        final float f = 0.375F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }
    
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "cattail");
    }
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return texture;
	}

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        final int blockId = world.getBlockId(x, y - 1, z);

        if (blockId != Block.grass.blockID && blockId != Block.dirt.blockID) return false;

        y--;

        for (int offset = -1; offset < 2; offset += 2)
            if (world.getBlockMaterial(x + offset, y, z) == Material.water
                    || world.getBlockMaterial(x, y, z + offset) == Material.water) return true;

        return false;
    }

    @Override
    public int getRenderType() {
        return 6;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int idNeighbor) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, 0);
        }
    }
}
