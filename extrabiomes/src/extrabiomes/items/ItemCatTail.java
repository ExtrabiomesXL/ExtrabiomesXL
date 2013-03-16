/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import extrabiomes.Extrabiomes;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;

public class ItemCatTail extends ItemBlock {

    public ItemCatTail(int id) {
        super(id);
    }
    
    @Override
    public void func_94581_a(IconRegister iconRegister)
    {
             iconIndex = iconRegister.func_94245_a(Extrabiomes.TexturePath + "cattail");
    }


}
