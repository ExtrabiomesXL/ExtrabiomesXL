/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import java.util.List;

import com.google.common.collect.Sets;

import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
import extrabiomes.helpers.ToolTipStringFormatter;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.utility.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class LogTurner extends ItemTool implements ITextureRegisterer
{
    public LogTurner()
    {
        super(ToolMaterial.WOOD, Sets.<Block>newHashSet());
    }
    
    @Override
    public void registerTexture() {
    	ModelUtil.registerTexture(this, "logturner");
    }

    @Override
    public boolean isDamageable()
    {
        return false;
    }
    
    @Override
    public boolean isItemTool(ItemStack par1ItemStack)
    {
        return true;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!player.canPlayerEdit(pos, facing, stack))
            return EnumActionResult.FAIL;
        
        final UseLogTurnerEvent event = new UseLogTurnerEvent(player, stack, world, pos);
        if (Extrabiomes.proxy.postEventToBus(event))
            return EnumActionResult.FAIL;
        if (event.isHandled())
            return world.isRemote ? EnumActionResult.PASS : EnumActionResult.SUCCESS;
        
        IBlockState state = world.getBlockState(pos);
        final Block block = state.getBlock();
        
        final int[] oreId = OreDictionary.getOreIDs(new ItemStack(block, 1, block.getMetaFromState(state)));
        final int logOreId = OreDictionary.getOreID("logWood");
        
        boolean containsLogOreId = false;
        for(int id : oreId) {
        	if(id == logOreId) {
        		containsLogOreId = true;
        		break;
        	}
        }
        if(!containsLogOreId) return EnumActionResult.FAIL;
        
		final SoundType wood = Blocks.LOG.getSoundType();
        world.playSound(event.getEntityPlayer(), event.getPos(), wood.getPlaceSound(), SoundCategory.BLOCKS, (wood.getVolume() + 1F) / 2F, wood.getPitch() * 1.55F);
        
        if (!world.isRemote)
        {
        	if (event.getEntityPlayer().isSneaking())
        		state = Blocks.LOG.withRotation(state, Rotation.COUNTERCLOCKWISE_90);
        	else
        		state = Blocks.LOG.withRotation(state, Rotation.CLOCKWISE_90);
            
            world.setBlockState(pos, state);
            
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
    
    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemForTooltip, EntityPlayer playerViewingToolTip, List<String> listOfLines, boolean sneaking) {
      String line = Extrabiomes.proxy.translate(this.getUnlocalizedName() + ".description");
      
      if(!line.equals(this.getUnlocalizedName() + ".description")) {
        if(listOfLines.size() > 0 && listOfLines.get(0).length() > 20) {
          ToolTipStringFormatter.Format(line, listOfLines, listOfLines.get(0).length() + 5);
        } else {
          ToolTipStringFormatter.Format(line, listOfLines);
        }
      }
    }
}
