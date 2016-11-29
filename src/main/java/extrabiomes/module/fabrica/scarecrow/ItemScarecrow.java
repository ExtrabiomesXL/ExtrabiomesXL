/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import java.util.List;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.helpers.ToolTipStringFormatter;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.lib.Reference;
import extrabiomes.utility.ModelUtil;
import net.minecraft.block.BlockFence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemScarecrow extends Item implements ITextureRegisterer
{
    
    public static String NAME = "extrabiomes.scarecrow";
    public static String ID = Reference.MOD_ID + '.' + NAME;
    
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
    public void registerTexture()
    {
    	ModelUtil.registerTexture(this, "scarecrow");
    }
    
    public ItemScarecrow()
    {
        super();
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
        {
            return EnumActionResult.PASS;
        }
        else
        {
            int x = pos.getX() + facing.getFrontOffsetX(),
            		y = pos.getY() + facing.getFrontOffsetY(),
            		z = pos.getZ() + facing.getFrontOffsetZ();
            double yOffsetForFence = 0.0D;
            
            if (facing == EnumFacing.UP && world.getBlockState(pos).getBlock() instanceof BlockFence)
            {
                yOffsetForFence = 0.5D;
            }
            
            if (spawnCreature(world, x + 0.5D, y + yOffsetForFence, z + 0.5D) && !player.capabilities.isCreativeMode)
            {
                --stack.stackSize;
            }
            
            return EnumActionResult.SUCCESS;
        }
    }
    
    @Override
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List<String> listOfLines, boolean sneaking) {
      String line = Extrabiomes.proxy.translate(this.getUnlocalizedName() + ".description");
      
      if(!line.equals(this.getUnlocalizedName() + ".description")) {
        if(listOfLines.size() > 0 && listOfLines.get(0).length() > 20) {
          ToolTipStringFormatter.Format(line, listOfLines, listOfLines.get(0).length() + 5);
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