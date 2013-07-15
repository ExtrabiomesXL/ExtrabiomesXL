/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.events.BlockActiveEvent.AcaciaStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.FirStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.PlankActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedCobbleStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockBrickStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockSlabActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedwoodStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.WallActiveEvent;
import extrabiomes.events.BlockActiveEvent.WoodSlabActiveEvent;
import extrabiomes.events.BlockActiveEvent.CypressStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.JapaneseMapleStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RainbowEucalyptusStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.AutumnStairsActiveEvent;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.module.fabrica.block.FuelHandlerWoodSlabs;

public enum BlockManager {
    PLANKS {
        @Override
        protected void create() {
            Stuff.planks = Optional.of(new BlockCustomWood(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.PLANKS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.planks.get();

            thisBlock.setUnlocalizedName("extrabiomes.planks");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);
            for (final BlockCustomWood.BlockType type : BlockCustomWood.BlockType.values()) {
                FacadeHelper.addBuildcraftFacade(thisBlock.blockID, type.metadata());
            }

            proxy.registerOreInAllSubblocks("plankWood", thisBlock);
            
            Extrabiomes.postInitEvent(new PlankActiveEvent(thisBlock));
        }
    },
    WOODSLAB {
        @Override
        protected void create() {
            Stuff.slabWood = Optional.of(new BlockCustomWoodSlab(getSettings().getID(), false));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.WOODSLAB;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabWood.get();

            thisBlock.setUnlocalizedName("extrabiomes.woodslab");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);

            proxy.registerFuelHandler(new FuelHandlerWoodSlabs(thisBlock.blockID));
            Extrabiomes.postInitEvent(new WoodSlabActiveEvent(thisBlock));
        }
    },
    DOUBLEWOODSLAB {
        @Override
        protected void create() {
            Stuff.slabWoodDouble = Optional.of(new BlockCustomWoodSlab(getSettings().getID(), true));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.DOUBLEWOODSLAB;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabWoodDouble.get();

            thisBlock.setUnlocalizedName("extrabiomes.woodslab");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            ItemWoodSlab.setSlabs((BlockHalfSlab) Stuff.slabWood.get(), (BlockHalfSlab) Stuff.slabWoodDouble.get());
            proxy.registerBlock(Stuff.slabWood.get(), extrabiomes.module.fabrica.block.ItemWoodSlab.class);
            proxy.registerBlock(thisBlock, extrabiomes.module.fabrica.block.ItemWoodSlab.class);

            proxy.registerOreInAllSubblocks("slabWood", Stuff.slabWood.get());

            new ItemStack(Stuff.slabWood.get(), 1, BlockCustomWoodSlab.BlockType.FIR.metadata());
            new ItemStack(Stuff.slabWood.get(), 1, BlockCustomWoodSlab.BlockType.REDWOOD.metadata());
            new ItemStack(Stuff.slabWood.get(), 1, BlockCustomWoodSlab.BlockType.ACACIA.metadata());
        }
    },
    REDWOODSTAIRS {
        @Override
        protected void create() {
            Stuff.stairsRedwood = Optional.of(new BlockWoodStairs(getSettings().getID(), Stuff.planks.get(), BlockCustomWood.BlockType.REDWOOD.metadata()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.REDWOODSTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRedwood.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.redwood");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new RedwoodStairsActiveEvent(thisBlock));
        }
    },
    FIRSTAIRS {
        @Override
        protected void create() {
            Stuff.stairsFir = Optional.of(new BlockWoodStairs(getSettings().getID(), Stuff.planks.get(), BlockCustomWood.BlockType.FIR.metadata()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.FIRSTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsFir.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.fir");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new FirStairsActiveEvent(thisBlock));
        }
    },
    ACACIASTAIRS {
        @Override
        protected void create() {
            Stuff.stairsAcacia = Optional.of(new BlockWoodStairs(getSettings().getID(), Stuff.planks.get(), BlockCustomWood.BlockType.ACACIA.metadata()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.ACACIASTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsAcacia.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.acacia");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new AcaciaStairsActiveEvent(thisBlock));
        }
    },
    RAINBOWEUCALYPTUSSTAIRS {
        @Override
        protected void create() {
            Stuff.stairsRainbowEucalyptus = Optional.of(new BlockWoodStairs(getSettings().getID(), Stuff.planks.get(), BlockCustomWood.BlockType.RAINBOW_EUCALYPTUS.metadata()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.RAINBOWEUCALYPTUSSTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRainbowEucalyptus.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.rainboweucalyptus");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new RainbowEucalyptusStairsActiveEvent(thisBlock));
        }
    },
    CYPRESSSTAIRS {
        @Override
        protected void create() {
            Stuff.stairsCypress = Optional.of(new BlockWoodStairs(getSettings().getID(), Stuff.planks.get(), BlockCustomWood.BlockType.CYPRESS.metadata()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.CYPRESSSTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsCypress.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.cypress");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new CypressStairsActiveEvent(thisBlock));
        }
    },
    JAPANESEMAPLESTAIRS {
        @Override
        protected void create() {
            Stuff.stairsJapaneseMaple = Optional.of(new BlockWoodStairs(getSettings().getID(), Stuff.planks.get(), BlockCustomWood.BlockType.JAPANESE_MAPLE.metadata()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.JAPANESEMAPLESTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsJapaneseMaple.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.japanesemaple");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new JapaneseMapleStairsActiveEvent(thisBlock));
        }
    },
    AUTUMNSTAIRS {
        @Override
        protected void create() {
            Stuff.stairsAutumn = Optional.of(new BlockWoodStairs(getSettings().getID(), Stuff.planks.get(), BlockCustomWood.BlockType.AUTUMN.metadata()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.AUTUMNSTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsAutumn.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.autumn");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock);

            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new AutumnStairsActiveEvent(thisBlock));
        }
    },
    REDROCKSLAB {
        @Override
        protected void create() {
            Stuff.slabRedRock = Optional.of(new BlockRedRockSlab(getSettings().getID(), false));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.REDROCKSLAB;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabRedRock.get();

            thisBlock.setUnlocalizedName("extrabiomes.redrockslab");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);

            Extrabiomes.postInitEvent(new RedRockSlabActiveEvent(thisBlock));
        }
    },
    DOUBLEREDROCKSLAB {
        @Override
        protected void create() {
            Stuff.slabRedRockDouble = Optional.of(new BlockRedRockSlab(getSettings().getID(), true));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.DOUBLEREDROCKSLAB;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabRedRockDouble.get();

            thisBlock.setUnlocalizedName("extrabiomes.redrockslab");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            ItemRedRockSlab.setSlabs((BlockHalfSlab) Stuff.slabRedRock.get(), (BlockHalfSlab) Stuff.slabRedRockDouble.get());
            proxy.registerBlock(Stuff.slabRedRock.get(), extrabiomes.module.fabrica.block.ItemRedRockSlab.class);
            proxy.registerBlock(thisBlock, extrabiomes.module.fabrica.block.ItemRedRockSlab.class);
        }
    },
    REDCOBBLESTAIRS {
        @Override
        protected void create() {
            Stuff.stairsRedCobble = Optional.of(new BlockCustomStairs(getSettings().getID(),
                    Block.blocksList[Element.RED_COBBLE.get().itemID], Element.RED_COBBLE.get()
                            .getItemDamage()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.REDCOBBLESTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRedCobble.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.redcobble");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock);

            Extrabiomes.postInitEvent(new RedCobbleStairsActiveEvent(thisBlock));
        }
    },
    REDROCKBRICKSTAIRS {
        @Override
        protected void create() {
            Stuff.stairsRedRockBrick = Optional.of(new BlockCustomStairs(getSettings().getID(),
                    Block.blocksList[Element.RED_ROCK_BRICK.get().itemID], Element.RED_ROCK_BRICK
                            .get().getItemDamage()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.REDROCKBRICKSTAIRS;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRedRockBrick.get();

            thisBlock.setUnlocalizedName("extrabiomes.stairs.redrockbrick");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock);

            Extrabiomes.postInitEvent(new RedRockBrickStairsActiveEvent(thisBlock));
        }
    },
    WALL {
        @Override
        protected void create() {
            Stuff.wall = Optional.of(new BlockCustomWall(getSettings().getID()));
        }

        @Override
        protected BlockSettings getSettings() {
            return BlockSettings.WALL;
        }

        @Override
        protected void prepare() {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.wall.get();

            thisBlock.setUnlocalizedName("extrabiomes.wall");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class);

            Extrabiomes.postInitEvent(new WallActiveEvent(thisBlock));
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
        for (final BlockManager block : values()) {
            if (block.blockCreated) block.prepare();
        }
    }

    public static void preInit() throws Exception {
        createBlocks();
    }

    private boolean blockCreated = false;

    protected abstract void create();

    protected abstract BlockSettings getSettings();

    protected abstract void prepare();
}
