/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.Stuff;
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.events.BlockActiveEvent.LogActiveEvent;
import extrabiomes.lib.BlockSettings;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenLegendOak;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;
import extrabiomes.proxy.CommonProxy;

@SuppressWarnings("deprecation")
public enum BlockManager {
    QUARTERLOG0 {
        @Override
        protected void create() {
            Stuff.quarterLogNW = Optional.of(new BlockQuarterLog(getSettings().getID(),
                    BlockQuarterLog.BarkOn.NW));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.QUARTERLOG0;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.quarterLogNW.get();

            thisBlock.setBlockName("extrabiomes.log.quarter");
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);

            for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType.values()) {
                final ItemStack itemstack = new ItemStack(thisBlock, 1, type.metadata());
                proxy.registerOre("logWood", itemstack);
            }
            Extrabiomes.postInitEvent(new LogActiveEvent(thisBlock));

            Extrabiomes.proxy.registerEventHandler(thisBlock);
        }
    },
    QUARTERLOG1 {
        @Override
        protected void create() {
            Stuff.quarterLogNE = Optional.of(new BlockQuarterLog(getSettings().getID(),
                    BlockQuarterLog.BarkOn.NE));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.QUARTERLOG1;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.quarterLogNE.get();

            thisBlock.setBlockName("extrabiomes.log.quarter");
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);

            for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType.values()) {
                final ItemStack itemstack = new ItemStack(thisBlock, 1, type.metadata());
                proxy.registerOre("logWood", itemstack);
            }

            Extrabiomes.postInitEvent(new LogActiveEvent(thisBlock));

            Extrabiomes.proxy.registerEventHandler(thisBlock);
        }
    },
    QUARTERLOG2 {
        @Override
        protected void create() {
            Stuff.quarterLogSW = Optional.of(new BlockQuarterLog(getSettings().getID(),
                    BlockQuarterLog.BarkOn.SW));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.QUARTERLOG2;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.quarterLogSW.get();

            thisBlock.setBlockName("extrabiomes.log.quarter");
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);

            for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType.values()) {
                final ItemStack itemstack = new ItemStack(thisBlock, 1, type.metadata());
                proxy.registerOre("logWood", itemstack);
            }

            Extrabiomes.postInitEvent(new LogActiveEvent(thisBlock));

            Extrabiomes.proxy.registerEventHandler(thisBlock);
        }
    },
    QUARTERLOG3 {
        @Override
        protected void create() {
            Stuff.quarterLogSE = Optional.of(new BlockQuarterLog(getSettings().getID(),
                    BlockQuarterLog.BarkOn.SE));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.QUARTERLOG3;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.quarterLogSE.get();

            thisBlock.setBlockName("extrabiomes.log.quarter");
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            BlockQuarterLog.setDropID(thisBlock.blockID);

            for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType.values()) {
                final ItemStack itemstack = new ItemStack(thisBlock, 1, type.metadata());
                proxy.registerOre("logWood", itemstack);

                FacadeHelper.addBuildcraftFacade(thisBlock.blockID, type.metadata());
            }

            Extrabiomes.postInitEvent(new LogActiveEvent(thisBlock));

            Extrabiomes.proxy.registerEventHandler(thisBlock);
        }
    };

    private static void createBlocks() throws Exception {
        for (final BlockManager block : BlockManager.values())
            if (block.getSettings().getID() > 0) {
                try {
                    block.create();
                } catch (final Exception e) {
                    throw e;
                }
                block.blockCreated = true;
            }
    }

    public static void init() throws InstantiationException, IllegalAccessException {
        for (final BlockManager block : values())
            if (block.blockCreated) block.prepare();

        if (Stuff.quarterLogNE.isPresent() || Stuff.quarterLogNW.isPresent()
                || Stuff.quarterLogSE.isPresent() || Stuff.quarterLogSW.isPresent())
            BlockQuarterLog.setRenderId(Extrabiomes.proxy
                    .registerBlockHandler(new RenderQuarterLog()));

        if (Stuff.quarterLogNE.isPresent() && Stuff.quarterLogNW.isPresent()
                && Stuff.quarterLogSE.isPresent() && Stuff.quarterLogSW.isPresent())
        {
            WorldGenFirTreeHuge.setTrunkBlock(Stuff.quarterLogNW.get(), Stuff.quarterLogNE.get(),
                    Stuff.quarterLogSW.get(), Stuff.quarterLogSE.get(),
                    BlockQuarterLog.BlockType.FIR.metadata());
            WorldGenRedwood.setTrunkBlock(Stuff.quarterLogNW.get(), Stuff.quarterLogNE.get(),
                    Stuff.quarterLogSW.get(), Stuff.quarterLogSE.get(),
                    BlockQuarterLog.BlockType.REDWOOD.metadata());
            WorldGenLegendOak.setTrunkBlock(Stuff.quarterLogNW.get(), Stuff.quarterLogNE.get(),
                    Stuff.quarterLogSW.get(), Stuff.quarterLogSE.get(),
                    BlockQuarterLog.BlockType.OAK.metadata());
            BlockQuarterLog.setQuarterLogs(Stuff.quarterLogNW.get(), Stuff.quarterLogNE.get(),
                    Stuff.quarterLogSW.get(), Stuff.quarterLogSE.get());
        }
    }

    public static void preInit() throws Exception {
        createBlocks();

        final Collection<BiomeGenBase> biomes = new ArrayList();
        if (BiomeManager.mountainridge.isPresent()) biomes.add(BiomeManager.mountainridge.get());
        if (BiomeManager.wasteland.isPresent()) biomes.add(BiomeManager.wasteland.get());
        BiomeManagerImpl.disableDefaultGrassforBiomes(biomes);
    }

    private boolean blockCreated = false;

    BlockManager() {
        this(false);
    }

    BlockManager(boolean restrictTo256) {}

    protected abstract void create();

    protected abstract BlockSettings getSettings();

    protected abstract void prepare();
}
