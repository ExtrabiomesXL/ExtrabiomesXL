/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.ToolTipStringFormatter;

public class ItemScarecrow extends Item
{
    
    public static String NAME = "extrabiomes.scarecrow";
    public static String ID = "ExtrabiomesXL." + NAME;
    
    private static boolean spawnCreature(World world, double x, double y, double z)
    {
        //{
        final Optional<Entity> entity = Optional.fromNullable(EntityList.createEntityByName(ID, world));
        
        if (entity.isPresent()) {
            entity.get().setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
            world.spawnEntityInWorld(entity.get());
        }
        
        return entity.isPresent();
        //}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "scarecrow");
    }
    
    public ItemScarecrow()
    {
        super();
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            final Block targetBlock = world.getBlock(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            double yOffsetForFence = 0.0D;
            
            if (side == 1 && targetBlock == Blocks.fence || targetBlock == Blocks.nether_brick_fence)
            {
                yOffsetForFence = 0.5D;
            }
            
            if (spawnCreature(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
            {
                --itemStack.stackSize;
            }
            
            return true;
        }
    }
    
    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List listOfLines, boolean sneaking) {
      String line = LanguageRegistry.instance().getStringLocalization(this.getUnlocalizedName() + ".description");
      
      if(!line.equals(this.getUnlocalizedName() + ".description")) {
        if(listOfLines.size() > 0 && ((String)listOfLines.get(0)).length() > 20) {
          ToolTipStringFormatter.Format(line, listOfLines, ((String)listOfLines.get(0)).length() + 5);
        } else {
          ToolTipStringFormatter.Format(line, listOfLines);
        }
      }
      /*
    	listOfLines.add("Keeps your garden free");
    	listOfLines.add("of most creatures.");
    	*/
    }
}