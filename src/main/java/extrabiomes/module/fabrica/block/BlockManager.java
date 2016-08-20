/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import extrabiomes.helpers.LogHelper;
import extrabiomes.items.ItemCustomDoor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

import com.google.common.base.Optional;

import net.minecraftforge.fml.common.registry.GameRegistry;
import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.blocks.BlockCustomFence;
import extrabiomes.blocks.BlockCustomFenceGate;
import extrabiomes.events.BlockActiveEvent.AcaciaStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.AutumnStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.BaldCypressStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.CypressStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.FirStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.JapaneseMapleStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.NewWoodSlabActiveEvent;
import extrabiomes.events.BlockActiveEvent.PlankActiveEvent;
import extrabiomes.events.BlockActiveEvent.RainbowEucalyptusStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedCobbleStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockBrickStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockSlabActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedwoodStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.SakuraBlossomStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.WallActiveEvent;
import extrabiomes.events.BlockActiveEvent.WoodSlabActiveEvent;
import extrabiomes.events.BlockActiveEvent.WoodDoorActiveEvent;
import extrabiomes.events.BlockActiveEvent.FenceActiveEvent;
import extrabiomes.events.BlockActiveEvent.FenceGateActiveEvent;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.GeneralSettings;
import extrabiomes.lib.Reference;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.module.fabrica.block.BlockCustomWood;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.renderers.CustomDoorRender;
import extrabiomes.renderers.CustomFenceRender;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public enum BlockManager
{
    PLANKS(Stuff.planks, true)
    {
        @Override
        protected void create()
        {
            Stuff.planks = Optional.of(new BlockCustomWood());
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.PLANKS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.planks.get();
            
            thisBlock.setBlockName("extrabiomes.planks");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class, "planks");
            for (final BlockCustomWood.BlockType type : BlockCustomWood.BlockType.values())
            {
                //FacadeHelper.addBuildcraftFacade(thisBlock, type.metadata());
            }
            
            proxy.registerOreInAllSubblocks("plankWood", thisBlock);
            
            Extrabiomes.postInitEvent(new PlankActiveEvent(thisBlock));
        }
    },
    NEWWOODSLAB(Stuff.newslabWood, true)
    {
        @Override
        protected void create()
        {
            Stuff.newslabWood = Optional.of(new BlockNewWoodSlab(getSettings(), false));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.NEWWOODSLAB;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.newslabWood.get();
            
            thisBlock.setBlockName("extrabiomes.woodslab");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            
            proxy.registerFuelHandler(new FuelHandlerWoodSlabs(thisBlock));
        }
    },
    NEWDOUBLEWOODSLAB(Stuff.newslabWoodDouble, true)
    {
        @Override
        protected void create()
        {
            Stuff.newslabWoodDouble = Optional.of(new BlockNewWoodSlab(getSettings(), true));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.NEWDOUBLEWOODSLAB;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.newslabWoodDouble.get();
            
            thisBlock.setBlockName("extrabiomes.woodslab");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            ItemNewWoodSlab.setSlabs((BlockSlab) Stuff.newslabWood.get(), (BlockSlab) Stuff.newslabWoodDouble.get());
            proxy.registerBlock(Stuff.newslabWood.get(), extrabiomes.module.fabrica.block.ItemNewWoodSlab.class, "woodslab2");
            proxy.registerBlock(thisBlock, extrabiomes.module.fabrica.block.ItemNewWoodSlab.class, "double_woodslab2");
            
            proxy.registerOreInAllSubblocks("slabWood", Stuff.newslabWood.get());
            
            // We can not create the slab reciepe till after we have created both the single and double slabs
            Extrabiomes.postInitEvent(new NewWoodSlabActiveEvent(Stuff.newslabWood.get()));
        }
    },
    WOODSLAB(Stuff.slabWood, true)
    {
        @Override
        protected void create()
        {
            Stuff.slabWood = Optional.of(new BlockCustomWoodSlab(getSettings(), false));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.WOODSLAB;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabWood.get();
            
            thisBlock.setBlockName("extrabiomes.woodslab");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            
            proxy.registerFuelHandler(new FuelHandlerWoodSlabs(thisBlock));
        }
    },
    DOUBLEWOODSLAB(Stuff.slabWoodDouble, true)
    {
        @Override
        protected void create()
        {
            Stuff.slabWoodDouble = Optional.of(new BlockCustomWoodSlab(getSettings(), true));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.DOUBLEWOODSLAB;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabWoodDouble.get();
            
            thisBlock.setBlockName("extrabiomes.woodslab");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            ItemWoodSlab.setSlabs((BlockSlab) Stuff.slabWood.get(), (BlockSlab) Stuff.slabWoodDouble.get());
            proxy.registerBlock(Stuff.slabWood.get(), extrabiomes.module.fabrica.block.ItemWoodSlab.class, "woodslab");
            proxy.registerBlock(thisBlock, extrabiomes.module.fabrica.block.ItemWoodSlab.class, "double_woodslab");
            
            LogHelper.info("Name: %s", Block.blockRegistry.getNameForObject(thisBlock));
            
            proxy.registerOreInAllSubblocks("slabWood", Stuff.slabWood.get());
            
            // We can not create the slab reciepe till after we have created both the single and double slabs
            Extrabiomes.postInitEvent(new WoodSlabActiveEvent(Stuff.slabWood.get()));
        }
    },
    REDWOODSTAIRS(Stuff.stairsRedwood, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsRedwood = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.REDWOOD.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.REDWOODSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRedwood.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.redwood");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.redwood");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new RedwoodStairsActiveEvent(thisBlock));
        }
    },
    FIRSTAIRS(Stuff.stairsFir, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsFir = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.FIR.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.FIRSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsFir.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.fir");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.fir");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new FirStairsActiveEvent(thisBlock));
        }
    },
    ACACIASTAIRS(Stuff.stairsAcacia, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsAcacia = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.ACACIA.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.ACACIASTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsAcacia.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.acacia");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.acacia");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new AcaciaStairsActiveEvent(thisBlock));
        }
    },
    RAINBOWEUCALYPTUSSTAIRS(Stuff.stairsRainbowEucalyptus, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsRainbowEucalyptus = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.RAINBOW_EUCALYPTUS.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.RAINBOWEUCALYPTUSSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRainbowEucalyptus.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.rainboweucalyptus");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.rainboweucalyptus");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new RainbowEucalyptusStairsActiveEvent(thisBlock));
        }
    },
    CYPRESSSTAIRS(Stuff.stairsCypress, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsCypress = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.CYPRESS.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.CYPRESSSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsCypress.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.cypress");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.cypress");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new CypressStairsActiveEvent(thisBlock));
        }
    },
    BALDCYPRESSSTAIRS(Stuff.stairsBaldCypress, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsBaldCypress = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.BALD_CYPRESS.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.BALDCYPRESSSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsBaldCypress.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.baldcypress");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.baldcypress");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new BaldCypressStairsActiveEvent(thisBlock));
        }
    },
    JAPANESEMAPLESTAIRS(Stuff.stairsJapaneseMaple, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsJapaneseMaple = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.JAPANESE_MAPLE.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.JAPANESEMAPLESTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsJapaneseMaple.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.japanesemaple");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.japanesemaple");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new JapaneseMapleStairsActiveEvent(thisBlock));
        }
    },
    AUTUMNSTAIRS(Stuff.stairsAutumn, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsAutumn = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.AUTUMN.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.AUTUMNSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsAutumn.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.autumn");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.autumn");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new AutumnStairsActiveEvent(thisBlock));
        }
    },
    SAKURABLOSSOMSTAIRS(Stuff.stairsSakuraBlossom, true)
    {
        @Override
        protected void create()
        {
            Stuff.stairsSakuraBlossom = Optional.of(new BlockWoodStairs(Stuff.planks.get(), BlockCustomWood.BlockType.SAKURA_BLOSSOM.metadata()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsSakuraBlossom.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.sakurablossom");
            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "stairs.sakurablossom");
            
            proxy.registerOre("stairWood", thisBlock);
            Extrabiomes.postInitEvent(new SakuraBlossomStairsActiveEvent(thisBlock));
        }
    },
    REDROCKSLAB(Stuff.slabRedRock, false)
    {
        @Override
        protected void create()
        {
            Stuff.slabRedRock = Optional.of(new BlockRedRockSlab(false));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.REDROCKSLAB;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabRedRock.get();
            
            thisBlock.setBlockName("extrabiomes.redrockslab");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            
        }
    },
    DOUBLEREDROCKSLAB(Stuff.slabRedRockDouble, false)
    {
        @Override
        protected void create()
        {
            Stuff.slabRedRockDouble = Optional.of(new BlockRedRockSlab(true));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.DOUBLEREDROCKSLAB;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.slabRedRockDouble.get();
            
            thisBlock.setBlockName("extrabiomes.redrockslab");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            ItemRedRockSlab.setSlabs((BlockSlab) Stuff.slabRedRock.get(), (BlockSlab) Stuff.slabRedRockDouble.get());
            proxy.registerBlock(Stuff.slabRedRock.get(), extrabiomes.module.fabrica.block.ItemRedRockSlab.class, "slabRedRock");
            proxy.registerBlock(thisBlock, extrabiomes.module.fabrica.block.ItemRedRockSlab.class, "double_slabRedRock");
            
            Extrabiomes.postInitEvent(new RedRockSlabActiveEvent(Stuff.slabRedRock.get()));
        }
    },
    REDCOBBLESTAIRS(Stuff.stairsRedCobble, false)
    {
        @Override
        protected void create()
        {
            Stuff.stairsRedCobble = Optional.of(new BlockCustomStairs(Block.getBlockFromItem(Element.RED_COBBLE.get().getItem()), Element.RED_COBBLE.get().getItemDamage()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.REDCOBBLESTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRedCobble.get();
            
            thisBlock.setBlockName("extrabiomes.stairs.redcobble");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock, "stairsRedCobble");
            
            Extrabiomes.postInitEvent(new RedCobbleStairsActiveEvent(thisBlock));
        }
    },
    REDROCKBRICKSTAIRS(Stuff.stairsRedRockBrick, false)
    {
        @Override
        protected void create()
        {
            Stuff.stairsRedRockBrick = Optional.of(new BlockCustomStairs(Block.getBlockFromItem(Element.RED_ROCK_BRICK.get().getItem()), Element.RED_ROCK_BRICK .get().getItemDamage()));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.REDROCKBRICKSTAIRS;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.stairsRedRockBrick.get();

            thisBlock.setBlockName("extrabiomes.stairs.redrockbrick");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock, "redrockbrick");
            
            Extrabiomes.postInitEvent(new RedRockBrickStairsActiveEvent(thisBlock));
        }
    },
    WALL(Stuff.wall, false)
    {
        @Override
        protected void create()
        {
            Stuff.wall = Optional.of(new BlockCustomWall());
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.WALL;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.wall.get();

            thisBlock.setBlockName("extrabiomes.wall");
            proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.utility.MultiItemBlock.class, "wall");
            
            Extrabiomes.postInitEvent(new WallActiveEvent(thisBlock));
        }
    },
    FENCE(Stuff.fence, false)
    {
        @Override
        protected void create()
        {
            Stuff.fence = Optional.of(new BlockCustomFence());
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.WALL;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.fence.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomFence.class, "fence");
            ((BlockCustomFence)thisBlock).setRenderId(Extrabiomes.proxy.registerBlockHandler(new CustomFenceRender()));
            Extrabiomes.postInitEvent(new FenceActiveEvent(thisBlock));
        }
    },
    ACACIADOOR(Stuff.doorAcacia, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorAcacia = Optional.of(new BlockCustomWoodDoor("acacia"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.ACACIADOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorAcacia.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_acacia");
            //proxy.registerItem(Stuff.doorAcaciaItem.get(), "item_door_acacia");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorAcacia.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.ACACIA.metadata())));
        }
    },
    AUTUMNDOOR(Stuff.doorAutumn, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorAutumn = Optional.of(new BlockCustomWoodDoor("autumn"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.AUTUMNDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorAutumn.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_autumn");
            //proxy.registerItem(Stuff.doorAutumnItem.get(), "item_door_autumn");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorAutumn.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.AUTUMN.metadata())));
        }
    },
    BALDCYPRESSDOOR(Stuff.doorBaldcypress, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorBaldcypress = Optional.of(new BlockCustomWoodDoor("baldcypress"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.BALDCYPRESSDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorBaldcypress.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_baldcypress");
            //proxy.registerItem(Stuff.doorBaldcypressItem.get(), "item_door_baldcypress");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorBaldcypress.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.BALD_CYPRESS.metadata())));
        }
    },
    CYPRESSDOOR(Stuff.doorCypress, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorCypress = Optional.of(new BlockCustomWoodDoor("cypress"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.CYPRESSDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorCypress.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_cypress");
            //proxy.registerItem(Stuff.doorCypressItem.get(), "item_door_cypress");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorCypress.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.CYPRESS.metadata())));
        }
    },
    FIRDOOR(Stuff.doorFir, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorFir = Optional.of(new BlockCustomWoodDoor("fir"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.FIRDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorFir.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_fir");
            //proxy.registerItem(Stuff.doorFirItem.get(), "item_door_fir");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorFir.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.FIR.metadata())));
        }
    },
    JAPANESEMAPLEDOOR(Stuff.doorJapaneseMaple, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorJapaneseMaple = Optional.of(new BlockCustomWoodDoor("japanesemaple"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.JAPANESEMAPLEDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorJapaneseMaple.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_japanesemaple");
            //proxy.registerItem(Stuff.doorJapaneseMapleItem.get(), "item_door_japanesemaple");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorJapaneseMaple.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.JAPANESE_MAPLE.metadata())));
        }
    },
    RAINBOWEUCALYPTUSDOOR(Stuff.doorRainbowEucalyptus, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorRainbowEucalyptus = Optional.of(new BlockCustomWoodDoor("rainboweucalyptus"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.RAINBOWEUCALYPTUSDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorRainbowEucalyptus.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_rainboweucalyptus");
            //proxy.registerItem(Stuff.doorRainbowEucalyptusItem.get(), "item_door_rainboweucalyptus");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorRainbowEucalyptus.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.RAINBOW_EUCALYPTUS.metadata())));
        }
    },
    REDWOODDOOR(Stuff.doorRedwood, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorRedwood = Optional.of(new BlockCustomWoodDoor("redwood"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.REDWOODDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorRedwood.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_redwood");
            //proxy.registerItem(Stuff.doorRedwoodItem.get(), "item_door_redwood");
            //GameRegistry.registerItem(Stuff.paste.get(), "extrabiomes.paste", Reference.MOD_ID);
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorRedwood.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.REDWOOD.metadata())));
        }
    },
    SAKURADOOR(Stuff.doorSakura, true)
    {
        @Override
        protected void create()
        {
            Stuff.doorSakura = Optional.of(new BlockCustomWoodDoor("sakura"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMDOOR;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.doorSakura.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, extrabiomes.items.ItemCustomDoor.class, "door_sakura");
            //proxy.registerItem(Stuff.doorSakuraItem.get(), "item_door_sakura");
            
            // Add the recipe for the door
            //ItemStack stack =  new ItemStack(Stuff.planks.get(), 3, BlockCustomWood.BlockType.REDWOOD.metadata());
            Extrabiomes.postInitEvent(new WoodDoorActiveEvent(new ItemStack(Stuff.doorSakura.get(), GeneralSettings.useMC18Doors ? 3 : 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.SAKURA_BLOSSOM.metadata())));
        }
    },
    ACACIAGATE(Stuff.gateAcacia, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateAcacia = Optional.of(new BlockCustomFenceGate("acacia"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateAcacia.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_acacia");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateAcacia.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.ACACIA.metadata())));
        }
    },
    AUTUMNGATE(Stuff.gateAutumn, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateAutumn = Optional.of(new BlockCustomFenceGate("autumn"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateAutumn.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_autumn");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateAutumn.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.AUTUMN.metadata())));
        }
    },
    BALDCYPRESSGATE(Stuff.gateSakura, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateBaldcypress = Optional.of(new BlockCustomFenceGate("baldcypress"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateBaldcypress.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_baldcypress");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateBaldcypress.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.BALD_CYPRESS.metadata())));
        }
    },
    CYPRESSGATE(Stuff.gateCypress, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateCypress = Optional.of(new BlockCustomFenceGate("cypress"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateCypress.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_cypress");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateCypress.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.CYPRESS.metadata())));
        }
    },
    FIRGATE(Stuff.gateFir, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateFir = Optional.of(new BlockCustomFenceGate("fir"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateFir.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_fir");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateFir.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.FIR.metadata())));
        }
    },
    JAPANESEMAPLEGATE(Stuff.gateJapaneseMaple, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateJapaneseMaple = Optional.of(new BlockCustomFenceGate("japanesemaple"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateJapaneseMaple.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_japanesemaple");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateJapaneseMaple.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.JAPANESE_MAPLE.metadata())));
        }
    },
    RAINBOWEUCALYPTUSGATE(Stuff.gateRainbowEucalyptus, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateRainbowEucalyptus = Optional.of(new BlockCustomFenceGate("rainboweucalyptus"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateRainbowEucalyptus.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_rainboweucalyptus");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateRainbowEucalyptus.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.RAINBOW_EUCALYPTUS.metadata())));
        }
    },
    REDWOODGATE(Stuff.gateRedwood, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateRedwood = Optional.of(new BlockCustomFenceGate("redwood"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateRedwood.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_redwood");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateRedwood.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.REDWOOD.metadata())));
        }
    },
    SAKURAGATE(Stuff.gateSakura, true)
    {
        @Override
        protected void create()
        {
            Stuff.gateSakura = Optional.of(new BlockCustomFenceGate("sakura"));
        }
        
        @Override
        protected BlockSettings getSettings()
        {
            return BlockSettings.SAKURABLOSSOMGATE;
        }
        
        @Override
        protected void prepare()
        {
            final CommonProxy proxy = Extrabiomes.proxy;
            final Block thisBlock = Stuff.gateSakura.get();

            proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
            proxy.registerBlock(thisBlock, "fencegate_sakura");
            
            // Add the recipe for the gate
            Extrabiomes.postInitEvent(new FenceGateActiveEvent(new ItemStack(Stuff.gateSakura.get(), 1), new ItemStack(Stuff.planks.get(), 1, BlockCustomWood.BlockType.SAKURA_BLOSSOM.metadata())));
        }
    };
    
    private static void createBlocks() throws Exception
    {
        for (final BlockManager block : BlockManager.values())
            if (block.getSettings().getEnabled())
            {
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
        {
            if (block.blockCreated) {
                block.prepare();
                if( block._flammable && block._stuff.isPresent() ) {
                    try {
                        block._block = (Block)block._stuff.get();
                        Blocks.fire.setFireInfo(block._block, 5, 20);
                    } catch(ArrayIndexOutOfBoundsException e) {
                        LogHelper.severe("Unable to set "+block+" flammable", e);
                        block._flammable = false;
                    }
                }
            }
        }
    }
    
    public static void preInit() throws Exception
    {
        createBlocks();
        BlockCustomWoodDoor.setRenderId(Extrabiomes.proxy.registerBlockHandler(new CustomDoorRender()));
    }
    
    private boolean blockCreated = false;

    private Block _block = null;
    private boolean _flammable = false;
    private Optional _stuff = null;

    private BlockManager(Optional stuff, boolean flammable) {
        _stuff = stuff;
        _flammable = flammable;
    }

    protected abstract BlockSettings getSettings();

    protected abstract void create();
    protected abstract void prepare();
}
