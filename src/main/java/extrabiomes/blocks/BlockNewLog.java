/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BlockSettings;

public class BlockNewLog extends BlockLog
{
    public enum BlockType
    {
        RAINBOW_EUCALYPTUS(0), AUTUMN(1), BALD_CYPRESS(2), REDWOOD(3);
        
        private final int metadata;
        
        BlockType(int metadata)
        {
            this.metadata = metadata;
        }
        
        public int metadata()
        {
            return metadata;
        }
    }
    
    private BlockSettings settings;
    private IIcon[] textures = { null, null, null, null, null, null, null, null };
    
    public BlockNewLog(BlockSettings settings)
    {
        super();
        this.settings = settings;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "lograinboweucalyptusside");
        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "lograinboweucalyptustop");
        
        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logautumnside");
        textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logautumntop");
        
        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logbaldcypressside");
        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logbaldcypresstop");
        
        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodside");
        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodtop");
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        final int orientation = metadata & 12;
        int type = metadata & 3;
        if (type > 3)
            type = 0;
        if (orientation == 0 && (side == 1 || side == 0) || orientation == 4 && (side == 5 || side == 4) || orientation == 8 && (side == 2 || side == 3))
        {
            //return texturesMap.get(index + 16 + type);
            return textures[(type * 2) + 1];
        }
        
        return textures[type * 2];
        //return texturesMap.get(index + type);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List list)
    {
        for (final BlockType type : BlockType.values())
        {
            list.add(new ItemStack(item, 1, type.metadata()));
            //LogHelper.info("Block Name: %s", type.toString());
        }
    }
        
    @SubscribeEvent
    public void onUseLogTurnerEvent(UseLogTurnerEvent event)
    {
        final Block block = event.world.getBlock(event.x, event.y, event.z);
        
        if (block == this)
        {
            final Block wood = Blocks.log;
            event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, wood.stepSound.soundName, (wood.stepSound.getVolume() + 1.0F) / 2.0F, wood.stepSound.getPitch() * 1.55F);
            
            if (!event.world.isRemote)
            {
                final int metadata = event.world.getBlockMetadata(event.x, event.y, event.z);
                int orientation = metadata & 12;
                final int type = metadata & 3;
                
                orientation = orientation == 0 ? 4 : orientation == 4 ? 8 : 0;
                event.world.setBlock(event.x, event.y, event.z, block, type | orientation, 3);
            }
            event.setHandled();
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}
