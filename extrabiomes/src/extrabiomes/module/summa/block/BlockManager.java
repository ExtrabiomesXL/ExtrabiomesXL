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
import net.minecraft.src.WorldGenTallGrass;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.Stuff;
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.events.BlockActiveEvent.CrackedSandActiveEvent;
import extrabiomes.events.BlockActiveEvent.FlowerActiveEvent;
import extrabiomes.events.BlockActiveEvent.LeafPileActiveEvent;
import extrabiomes.events.BlockActiveEvent.LogActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockActiveEvent;
import extrabiomes.handlers.SaplingBonemealEventHandler;
import extrabiomes.handlers.SaplingFuelHandler;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BlockSettings;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.module.summa.worldgen.CatTailGenerator;
import extrabiomes.module.summa.worldgen.FlowerGenerator;
import extrabiomes.module.summa.worldgen.LeafPileGenerator;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenBigAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenLegendOak;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;
import extrabiomes.module.summa.worldgen.WorldGenWastelandGrass;
import extrabiomes.proxy.CommonProxy;

@SuppressWarnings("deprecation")
public enum BlockManager {
    CATTAIL {
        @Override
        protected void create() {
            Stuff.cattail = Optional.of(new BlockCatTail(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.CATTAIL;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.cattail.get();

            thisBlock.setBlockName("extrabiomes.cattail");
            proxy.registerBlock(thisBlock, extrabiomes.module.summa.block.ItemCatTail.class);

            proxy.registerOre("reedTypha", thisBlock);

            proxy.registerWorldGenerator(new CatTailGenerator(thisBlock.blockID));
        }
    },
    CRACKEDSAND(true) {
        @Override
        protected void create() {
            Stuff.crackedSand = Optional.of(new BlockCrackedSand(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.CRACKEDSAND;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.crackedSand.get();

            thisBlock.setBlockName("extrabiomes.crackedsand");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("sandCracked", thisBlock);

            Extrabiomes.postInitEvent(new CrackedSandActiveEvent(thisBlock));
            addCrackedSandToWasteland(thisBlock.blockID);

            FacadeHelper.addBuildcraftFacade(thisBlock.blockID);
        }
    },
    FLOWER {
        @Override
        protected void create() {
            Stuff.flower = Optional.of(new BlockCustomFlower(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.FLOWER;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.flower.get();

            thisBlock.setBlockName("extrabiomes.flower");
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);

            Extrabiomes.postInitEvent(new FlowerActiveEvent(thisBlock));

            proxy.registerWorldGenerator(new FlowerGenerator(thisBlock.blockID));
        }
    },
    GRASS {
        @Override
        protected void create() {
            Stuff.grass = Optional.of(new BlockCustomTallGrass(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.GRASS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.grass.get();

            thisBlock.setBlockName("extrabiomes.tallgrass");
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);

            if (BiomeManager.mountainridge.isPresent()) {
                BiomeManager.addWeightedGrassGenForBiome(BiomeManager.mountainridge.get(),
                        new WorldGenTallGrass(thisBlock.blockID,
                                BlockCustomTallGrass.BlockType.BROWN.metadata()), 100);
                BiomeManager.addWeightedGrassGenForBiome(BiomeManager.mountainridge.get(),
                        new WorldGenTallGrass(thisBlock.blockID,
                                BlockCustomTallGrass.BlockType.SHORT_BROWN.metadata()), 100);
            }

            if (BiomeManager.wasteland.isPresent()) {
                BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland.get(),
                        new WorldGenWastelandGrass(thisBlock.blockID,
                                BlockCustomTallGrass.BlockType.DEAD.metadata()), 90);
                BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland.get(),
                        new WorldGenWastelandGrass(thisBlock.blockID,
                                BlockCustomTallGrass.BlockType.DEAD_YELLOW.metadata()), 90);
                BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland.get(),
                        new WorldGenWastelandGrass(thisBlock.blockID,
                                BlockCustomTallGrass.BlockType.DEAD_TALL.metadata()), 35);
            }
        }
    },
    LEAFPILE {
        @Override
        protected void create() {
            Stuff.leafPile = Optional.of(new BlockLeafPile(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.LEAFPILE;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.leafPile.get();

            thisBlock.setBlockName("extrabiomes.leafpile");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock);

            Extrabiomes.postInitEvent(new LeafPileActiveEvent(thisBlock));

            proxy.registerWorldGenerator(new LeafPileGenerator(thisBlock.blockID));
        }
    },
    REDROCK(true) {
        @Override
        protected void create() {
            Stuff.redRock = Optional.of(new BlockRedRock(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.REDROCK;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.redRock.get();

            thisBlock.setBlockName("extrabiomes.redrock");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);

            for (final BlockRedRock.BlockType type : BlockRedRock.BlockType.values())
                FacadeHelper.addBuildcraftFacade(thisBlock.blockID, type.metadata());

            Extrabiomes.postInitEvent(new RedRockActiveEvent(thisBlock));

            addRedRockToMountainRidge(thisBlock.blockID);
        }
    },
    CUSTOMLOG {
        @Override
        protected void create() {
            Stuff.log = Optional.of(new BlockCustomLog(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.CUSTOMLOG;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.log.get();

            thisBlock.setBlockName("extrabiomes.log");
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);

            proxy.registerOre("logWood", new ItemStack(thisBlock, 1, -1));

            for (final BlockCustomLog.BlockType type : BlockCustomLog.BlockType.values())
                FacadeHelper.addBuildcraftFacade(thisBlock.blockID, type.metadata());

            WorldGenAcacia.setTrunkBlock(thisBlock, BlockCustomLog.BlockType.ACACIA.metadata());
            WorldGenFirTree.setTrunkBlock(thisBlock, BlockCustomLog.BlockType.FIR.metadata());

            Extrabiomes.postInitEvent(new LogActiveEvent(thisBlock));

            Extrabiomes.proxy.registerEventHandler(thisBlock);
        }
    },
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

    private static final String LOG_MESSAGE_ADD_REDROCK     = "log.message.add.redrock";
    private static final String LOG_MESSAGE_ADD_CRACKEDSAND = "log.message.add.crackedsand";

    private static void addCrackedSandToWasteland(int crackedsandID) {
        if (!BiomeManager.wasteland.isPresent()) return;

        final BiomeGenBase wasteland = BiomeManager.wasteland.get();
        wasteland.topBlock = (byte) crackedsandID;
        wasteland.fillerBlock = (byte) crackedsandID;
        LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_ADD_CRACKEDSAND));
    }

    private static void addRedRockToMountainRidge(int redrockID) {
        if (!BiomeManager.mountainridge.isPresent()) return;

        final BiomeGenBase mountainridge = BiomeManager.mountainridge.get();
        mountainridge.topBlock = (byte) redrockID;
        mountainridge.fillerBlock = (byte) redrockID;
        LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_ADD_REDROCK));
    }

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
