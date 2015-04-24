/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import net.minecraft.block.Block;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Reference;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.module.cautia.worldgen.QuicksandGenerator;
import extrabiomes.proxy.CommonProxy;

public enum BlockManager
{
    QUICKSAND
    {
        @Override
        protected void create()
        {
            Stuff.quickSand = Optional.of(new BlockQuicksand());
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.quickSand.get();
            
            thisBlock.setBlockName("extrabiomes.quicksand");
            proxy.setBlockHarvestLevel(thisBlock, "shovel", 0);
            proxy.registerBlock(thisBlock, "quicksand");
            
            //FacadeHelper.addBuildcraftFacade(thisBlock);
            
            proxy.registerWorldGenerator(new QuicksandGenerator(thisBlock));
        }

		@Override
		protected BlockSettings getSettings() {
			return BlockSettings.QUICKSAND;
		}
    };
    
    private static void createBlocks() throws Exception
    {
        for (final BlockManager block : BlockManager.values())
            {
        		if( !block.getSettings().getEnabled() ) {
        			LogHelper.fine("Skipping registration of disabled block "+block);
        			continue;
        		}
                try
                {
                    block.create();
                }
                catch (final Exception e)
                {
                    throw e;
                }
                block.blockCreated = true;
            }
    }
    
    public static void init() throws InstantiationException, IllegalAccessException
    {
        for (final BlockManager block : values())
            if (block.blockCreated)
                block.prepare();
    }
    
    public static void preInit() throws Exception
    {
        createBlocks();
    }
    
    private boolean blockCreated = false;
    
    protected abstract void create();
    
    protected abstract BlockSettings getSettings();
    
    protected abstract void prepare();
}
