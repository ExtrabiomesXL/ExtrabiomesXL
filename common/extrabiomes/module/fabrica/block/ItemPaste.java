package extrabiomes.module.fabrica.block;

import extrabiomes.Extrabiomes;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemPaste extends Item{

	public ItemPaste(int par1) {
		super(par1);
		
	}
	
	@Override
    public void func_94581_a(IconRegister iconRegister)
    {
    	iconIndex = iconRegister.func_94245_a(Extrabiomes.TEXTURE_PATH + "cactuspaste");
    }
}
