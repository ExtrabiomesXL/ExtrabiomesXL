/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.lib.Element;

public class BlockCustomWall extends BlockWall {

    public enum BlockType {
        RED_COBBLE(0);

        private final int metadata;

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        public int metadata() {
            return metadata;
        }
    }
    
    private Icon texture;

    public BlockCustomWall(int id) {
        super(id, Block.blocksList[Element.RED_COBBLE.get().itemID]);
        //setTextureFile("/extrabiomes/extrabiomes.png");
        setCreativeTab(Extrabiomes.tabsEBXL);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	texture = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockcobble");
    }
    	
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addCreativeItems(ArrayList itemList) {
        for (final BlockType blockType : BlockType.values())
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        return texture;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
        for (final BlockType blockType : BlockType.values())
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
    }

}
