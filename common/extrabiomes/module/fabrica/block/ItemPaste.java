package extrabiomes.module.fabrica.block;

import extrabiomes.Extrabiomes;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemPaste extends Item{

	public ItemPaste(int par1) {
		super(par1);
		
	}
	
	@Override
    public void updateIcons(IconRegister iconRegister)
    {
    	iconIndex = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "cactuspaste");
    }
}
