/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import java.util.Collection;
import java.util.Locale;

import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import extrabiomes.Extrabiomes;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCatTail;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomFlower.BlockType;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockCustomTallGrass;
import extrabiomes.blocks.BlockCustomVine;
import extrabiomes.blocks.BlockEBXLLog;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.blocks.BlockKneeLog;
import extrabiomes.blocks.BlockLeafPile;
import extrabiomes.blocks.BlockMiniLog;
import extrabiomes.blocks.BlockMoreLeaves;
import extrabiomes.blocks.BlockNewLeaves;
import extrabiomes.blocks.BlockNewQuarterLog;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.blocks.BlockQuarterLog;
import extrabiomes.blocks.BlockRedRock;
import extrabiomes.blocks.BlockWaterPlant;
import extrabiomes.blocks.GenericTerrainBlock;
import extrabiomes.events.BlockActiveEvent.RedRockActiveEvent;
import extrabiomes.handlers.BlockHandler.LogHandler.Log_A_Type;
import extrabiomes.handlers.BlockHandler.LogHandler.Log_B_Type;
import extrabiomes.helpers.BiomeHelper;
import extrabiomes.helpers.ForestryModHelper;
import extrabiomes.helpers.LogHelper;
import extrabiomes.items.ItemKneeLog;
import extrabiomes.items.ItemNewQuarterLog;
import extrabiomes.items.ItemOldQuarterLog;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.IMetaSerializable;
import extrabiomes.lib.IQuarterSerializable;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.module.summa.worldgen.CatTailGenerator;
import extrabiomes.module.summa.worldgen.EelGrassGenerator;
import extrabiomes.module.summa.worldgen.FlowerGenerator;
import extrabiomes.module.summa.worldgen.LeafPileGenerator;
import extrabiomes.module.summa.worldgen.VineGenerator;
import extrabiomes.module.summa.worldgen.WorldGenCustomVine;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.renderers.RenderKneeLog;
import extrabiomes.renderers.RenderMiniLog;
import extrabiomes.renderers.RenderNewQuarterLog;
import extrabiomes.renderers.RenderQuarterLog;
import extrabiomes.subblocks.SubBlockWaterPlant;
import extrabiomes.utility.MultiItemBlock;

public abstract class BlockHandler {

  private static void createAutumnLeaves() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.AUTUMNLEAVES.getEnabled())
      return;

    final BlockAutumnLeaves block = new BlockAutumnLeaves(3, Material.LEAVES, false);
    block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(block, extrabiomes.items.ItemCustomLeaves.class, "leaves_1");
    proxy.registerOreInAllSubblocks("treeLeaves", block);
    Blocks.FIRE.setFireInfo(block, 30, 60);

    Element.LEAVES_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.UMBER.metadata()));
    Element.LEAVES_AUTUMN_ORANGE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.GOLDENROD.metadata()));
    Element.LEAVES_AUTUMN_PURPLE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.VERMILLION.metadata()));
    Element.LEAVES_AUTUMN_YELLOW.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.CITRINE.metadata()));

    final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
    ForestryModHelper.registerLeaves(stack);
    ForestryModHelper.addToForesterBackpack(stack);
  }

  public static void createBlocks() throws Exception {
    createAutumnLeaves();
    createCattail();
    createCrackedSand();
    createFlowers();
    createGrass();
    createGreenLeaves();
    createNewLeaves();
    createMoreLeaves();
    createLeafPile();
    createRedRock();
    createSapling();
    createNewSapling();
    LogHandler.createLogs();
    createVines();
    createWaterPlants();

  }

  private static void createWaterPlants() throws Exception {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.WATERPLANT.getEnabled())
      return;

    final BlockWaterPlant waterPlantBlock = new BlockWaterPlant(BlockSettings.WATERPLANT, "waterPlant");

    waterPlantBlock.setBlockName("extrabiomes.waterplant").setHardness(0.01F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    // Add the subblocks
    waterPlantBlock.registerSubBlock(new SubBlockWaterPlant("eelgrass").addPlaceableBlock(Blocks.GRASS).addPlaceableBlock(Blocks.SAND).addPlaceableBlock(Blocks.GRAVEL).addPlaceableBlock(Blocks.CLAY), 0);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(waterPlantBlock, extrabiomes.items.ItemBlockWaterPlant.class, "waterplant1");

    Element.WATERPLANT.set(new ItemStack(waterPlantBlock, 1, 0));

    proxy.registerWorldGenerator(new EelGrassGenerator(waterPlantBlock, 0));
  }

  private static void createCattail() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.CATTAIL.getEnabled())
      return;

    final BlockCatTail block = new BlockCatTail(79, Material.plants);
    block.setBlockName("extrabiomes.cattail").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(block, extrabiomes.items.ItemCatTail.class, "plants4");
    proxy.registerOre("reedTypha", block);

    Element.CATTAIL.set(new ItemStack(block));

    proxy.registerWorldGenerator(new CatTailGenerator(block));
  }

  private static void createCrackedSand() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.CRACKEDSAND.getEnabled())
      return;

    final GenericTerrainBlock block = new GenericTerrainBlock(0, Material.rock);
    block.setBlockName("extrabiomes.crackedsand").setHardness(1.2F).setStepSound(Block.soundTypeStone).setCreativeTab(Extrabiomes.tabsEBXL);

    block.texturePath = "crackedsand";

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.setBlockHarvestLevel(block, "pickaxe", 0);
    proxy.registerBlock(block, "terrain_blocks2");

    proxy.registerOre("sandCracked", block);

    final ItemStack stack = new ItemStack(block);
    Element.CRACKEDSAND.set(stack);

    BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.WASTELAND, block, block);

    ForestryModHelper.addToDiggerBackpack(stack);
    //FacadeHelper.addBuildcraftFacade(block);
  }

  private static void createFlowers() {
    if (!ModuleControlSettings.SUMMA.isEnabled())
      return;

    final boolean enableds[] = { BlockSettings.FLOWER.getEnabled(), BlockSettings.FLOWER2.getEnabled(), BlockSettings.FLOWER3.getEnabled() };

    final CommonProxy proxy = Extrabiomes.proxy;
    final FlowerGenerator generator = FlowerGenerator.getInstance();

    for (int group = 0; group < enableds.length; ++group) {
      if (!enableds[group])
        continue;

      final BlockCustomFlower block = new BlockCustomFlower(group, Material.plants);
      block.setBlockName("extrabiomes.flower").setTickRandomly(true).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);
      proxy.registerBlock(block, extrabiomes.items.ItemFlower.class, "flower" + (group + 1));

      Collection<BlockType> types = block.getGroupTypes();
      for (BlockType type : types) {
        final Element element;
        try {
          element = Element.valueOf(type.name());
        } catch (Exception e) {
          LogHelper.warning("No element found for flower " + type);
          continue;
        }
        type.setBlock(block);
        ItemStack item = new ItemStack(block, 1, type.metadata());
        element.set(item);
        ForestryModHelper.registerBasicFlower(item);
      }

      generator.registerBlock(block, types);
      ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
    }

    proxy.registerWorldGenerator(generator);
  }

  private static void createVines() {
    if (!ModuleControlSettings.SUMMA.isEnabled())
      return;

    final CommonProxy proxy = Extrabiomes.proxy;

    // BlockCustomVine.BlockType[] vines = BlockCustomVine.BlockType.values();
    BlockCustomVine.BlockType[] vines = { BlockCustomVine.BlockType.GLORIOSA };

    for (BlockCustomVine.BlockType blockType : vines) {
      final BlockSettings settings;
      try {
        settings = BlockSettings.valueOf(blockType.name());
      } catch (Exception e) {
        LogHelper.severe("Unable to find settings for " + blockType);
        continue;
      }

      if (!settings.getEnabled())
        continue;

      /*
       * final String shortName = blockType.name()
       * .substring(blockType.name().indexOf('_')).toLowerCase();
       */

      final BlockCustomVine block = new BlockCustomVine(blockType);
      block.setBlockName("extrabiomes.vine." + blockType.name().toLowerCase()).setCreativeTab(Extrabiomes.tabsEBXL);
      proxy.registerBlock(block, ItemBlock.class, "vines");

      final Element element;
      try {
        element = Element.valueOf("VINE_" + blockType.name());
      } catch (Exception e) {
        LogHelper.warning("No element found for vine " + blockType);
        continue;
      }
      final ItemStack item = new ItemStack(block, 1);
      element.set(item);

      ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));

      final VineGenerator generator;
      // gloriosa gets a biome list override
      if (blockType == BlockCustomVine.BlockType.GLORIOSA) {
        final BiomeSettings[] biomeList = { BiomeSettings.EXTREMEJUNGLE, BiomeSettings.MINIJUNGLE, BiomeSettings.RAINFOREST };
        generator = new VineGenerator(block, biomeList);
      } else {
        generator = new VineGenerator(block);
      }
      proxy.registerWorldGenerator(generator);
    }
  }

  private static void createGrass() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.GRASS.getEnabled())
      return;

    final BlockCustomTallGrass block = new BlockCustomTallGrass(48, Material.vine);
    block.setBlockName("extrabiomes.tallgrass").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(block, extrabiomes.items.ItemGrass.class, "grass");
    Blocks.FIRE.setFireInfo(block, 60, 100);

    Element.GRASS_BROWN.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.BROWN.metadata()));
    Element.GRASS_DEAD.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD.metadata()));
    Element.GRASS_BROWN_SHORT.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.SHORT_BROWN.metadata()));
    Element.GRASS_DEAD_TALL.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD_TALL.metadata()));
    Element.GRASS_DEAD_YELLOW.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD_YELLOW.metadata()));

    ItemStack grassStack = Element.GRASS_BROWN.get();
    BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 100);
    grassStack = Element.GRASS_BROWN_SHORT.get();
    BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 100);

    grassStack = Element.GRASS_DEAD.get();
    BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 90);
    grassStack = Element.GRASS_DEAD_YELLOW.get();
    BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 90);
    grassStack = Element.GRASS_DEAD_TALL.get();
    BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 35);
  }

  private static void createNewLeaves() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.NEWLEAVES.getEnabled())
      return;

    final BlockNewLeaves block = new BlockNewLeaves(Material.LEAVES, false);
    block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(block, extrabiomes.items.ItemCustomNewLeaves.class, "leaves_2");
    proxy.registerOreInAllSubblocks("treeLeaves", block);
    Blocks.FIRE.setFireInfo(block, 30, 60);

    Element.LEAVES_BALD_CYPRESS.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.BALD_CYPRESS.metadata()));
    Element.LEAVES_JAPANESE_MAPLE.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.JAPANESE_MAPLE.metadata()));
    Element.LEAVES_JAPANESE_MAPLE_SHRUB.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.JAPANESE_MAPLE_SHRUB.metadata()));
    Element.LEAVES_RAINBOW_EUCALYPTUS.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.RAINBOW_EUCALYPTUS.metadata()));

    final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
    ForestryModHelper.registerLeaves(stack);
    ForestryModHelper.addToForesterBackpack(stack);
  }

  private static void createMoreLeaves() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.MORELEAVES.getEnabled())
      return;

    final BlockMoreLeaves block = new BlockMoreLeaves(Material.LEAVES, false);
    block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(block, extrabiomes.items.ItemCustomMoreLeaves.class, "leaves_3");
    proxy.registerOreInAllSubblocks("treeLeaves", block);
    Blocks.FIRE.setFireInfo(block, 30, 60);

    Element.LEAVES_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockMoreLeaves.BlockType.SAKURA_BLOSSOM.metadata()));

    final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
    ForestryModHelper.registerLeaves(stack);
    ForestryModHelper.addToForesterBackpack(stack);
  }

  private static void createGreenLeaves() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.GREENLEAVES.getEnabled())
      return;

    final BlockGreenLeaves block = new BlockGreenLeaves(Material.LEAVES, false);
    block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(block, extrabiomes.items.ItemCustomGreenLeaves.class, "leaves_4");
    proxy.registerOreInAllSubblocks("treeLeaves", block);
    Blocks.FIRE.setFireInfo(block, 30, 60);

    Element.LEAVES_ACACIA.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.ACACIA.metadata()));
    Element.LEAVES_FIR.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.FIR.metadata()));
    Element.LEAVES_REDWOOD.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.REDWOOD.metadata()));
    Element.LEAVES_CYPRESS.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.CYPRESS.metadata()));

    final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
    ForestryModHelper.registerLeaves(stack);
    ForestryModHelper.addToForesterBackpack(stack);
  }

  private static void createLeafPile() {
    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.LEAFPILE.getEnabled())
      return;

    final BlockLeafPile block = new BlockLeafPile(64, Material.vine);
    block.setBlockName("extrabiomes.leafpile").setHardness(0.0F).setTickRandomly(true).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

    final CommonProxy proxy = Extrabiomes.proxy;
    proxy.registerBlock(block, "leaf_pile");
    Blocks.FIRE.setFireInfo(block, 30, 60);

    Element.LEAFPILE.set(new ItemStack(block));

    proxy.registerWorldGenerator(new LeafPileGenerator(block));
  }

  public static final class LogHandler {
		/** 4 log types, name open to change **/
		public static enum Log_A_Type implements IMetaSerializable {
			FIR(0), ACACIA(1), CYPRESS(2), JAPANESE_MAPLE(3);

			private final String name;
			private final int metadata;

			Log_A_Type(int metadata) {
				this.name = name().toLowerCase(Locale.ENGLISH);
				this.metadata = metadata;
			}

			@Override
			public String getName() {
				return name;
			}
			
			@Override
			public int getMetadata() {
				return metadata;
			}
			
			/* FIXME: Add these textures back
			textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logfirside");
	        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logfirtop");
	        
	        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logacaciaside");
	        textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logacaciatop");
	        
	        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logcypressside");
	        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logcypresstop");
	        
	        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logjapanesemapleside");
	        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logjapanesemapletop");
			 */
		}
		
		/** 4 new log types, name open to change **/
		public static enum Log_B_Type implements IMetaSerializable {
			RAINBOW_EUCALYPTUS(0), AUTUMN(1), BALD_CYPRESS(2), REDWOOD(3);

			private final String name;
			private final int metadata;

			Log_B_Type(int metadata) {
				this.name = name().toLowerCase(Locale.ENGLISH);
				this.metadata = metadata;
			}
			
			@Override
			public String getName() {
				return name;
			}

			@Override
			public int getMetadata() {
				return metadata;
			}
			
			/* FIXME: Add these textures back
			textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "lograinboweucalyptusside");
        	textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "lograinboweucalyptustop");
        
        	textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logautumnside");
        	textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logautumntop");
        
	        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logbaldcypressside");
	        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logbaldcypresstop");
	        
	        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodside");
	        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodtop");*/
		}
		
		public enum QuarterLogs_A_Type implements IQuarterSerializable
	    {
	        REDWOOD(0, BlockSettings.CUSTOMLOG.getItem(), Log_B_Type.REDWOOD.getMetadata()),
	        FIR(1, BlockSettings.NEWLOG.getItem(), Log_A_Type.FIR.getMetadata()),
	        OAK(2, ItemBlock.getItemFromBlock(Blocks.LOG), 0);
	        
			private final Item item;
	    	private final String name;
	        private final int metadata, damage;
	        
	        QuarterLogs_A_Type(int metadata, Item item, int damage)
	        {
	        	this.name = name().toLowerCase(Locale.ENGLISH);
	            this.metadata = metadata;
	            
	            this.item = item;
	            this.damage = damage;
	        }
	        
	        public String getName()
	        {
	        	return name;
	        }
	        
	        @Override
	        public int getMetadata()
	        {
	            return metadata;
	        }
	        
	        @Override
	        public Item getItem() {
	        	return item;
	        }
	        
	        @Override
	        public int getMeta() {
	        	return damage;
	        }
	        
	        /* FIXME: Add these textures back
	        textureArray[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodsideleft");
	        textureArray[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodsideright");
	        textureArray[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodtopleft");
	        textureArray[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodtopright");
	        textureArray[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodbottomleft");
	        textureArray[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodbottomright");
	        textureArray[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodsideleft");
	        textureArray[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodsideright");
	        
	        textureArray[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logfirsideleft");
	        textureArray[9] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logfirsideright");
	        textureArray[10] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firtopleft");
	        textureArray[11] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firtopright");
	        textureArray[12] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firbottomleft");
	        textureArray[13] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firbottomright");
	        textureArray[14] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firsideleft");
	        textureArray[15] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firsideright");
	        
	        textureArray[16] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logoaksideleft");
	        textureArray[17] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logoaksideright");
	        textureArray[18] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaktopleft");
	        textureArray[19] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaktopright");
	        textureArray[20] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oakbottomleft");
	        textureArray[21] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oakbottomright");
	        textureArray[22] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaksideleft");
	        textureArray[23] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaksideright");
	         */
	    }
	  
		private static void createLogs() {
			if (ModuleControlSettings.SUMMA.isEnabled()) {
				createWood();
				createMiniLogs();
				createQuarterLogs();
				createNewQuarterLogs();
				createKneeLogs();
			}
		}

	  private static void createMiniLogs() {
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.MINILOG.getEnabled())
	      return;
	
	    final BlockMiniLog block = new BlockMiniLog(BlockSettings.MINILOG);
	    extrabiomes.lib.Blocks.BLOCK_LOG_SAKURA_GROVE.set(block);
	    block.setBlockName("extrabiomes.log").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
	
	    final CommonProxy proxy = Extrabiomes.proxy;
	    proxy.setBlockHarvestLevel(block, "axe", 0);
	    proxy.registerBlock(block, extrabiomes.items.ItemCustomMiniLog.class, "mini_log_1");
	    proxy.registerOreInAllSubblocks("logWood", block);
	    proxy.registerEventHandler(block);
	    Blocks.FIRE.setFireInfo(block, 5, 5);
	
	    Element.LOG_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockMiniLog.BlockType.SAKURA_BLOSSOM.metadata()));
	
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
	
	    BlockMiniLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderMiniLog()));
	  }
	
	  private static void createKneeLogs() {
	    final BlockKneeLog block = new BlockKneeLog(BlockSettings.KNEELOG, "baldcypress");
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.KNEELOG.getEnabled())
	      return;
	
	    block.setBlockName("extrabiomes.cypresskneelog");
	    ((BlockKneeLog) block).setDroppedItemStack(Element.LOG_BALD_CYPRESS.get());
	
	    final CommonProxy proxy = Extrabiomes.proxy;
	    proxy.setBlockHarvestLevel(block, "axe", 0);
	    // proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class);
	    proxy.registerBlock(block, ItemKneeLog.class, "log_elbow_baldcypress");
	    proxy.registerOreInAllSubblocks("logWood", block);
	    proxy.registerEventHandler(block);
	    Blocks.FIRE.setFireInfo(block, 5, 5);
	
	    final BlockKneeLog block2 = new BlockKneeLog(BlockSettings.RAINBOWKNEELOG, "rainboweucalyptus");
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.RAINBOWKNEELOG.getEnabled())
	      return;
	
	    block2.setBlockName("extrabiomes.rainbowkneelog");
	    ((BlockKneeLog) block2).setDroppedItemStack(Element.LOG_RAINBOW_EUCALYPTUS.get());
	
	    proxy.setBlockHarvestLevel(block2, "axe", 0);
	    proxy.registerBlock(block2, ItemKneeLog.class, "log_elbow_rainbow_eucalyptus");
	    proxy.registerOreInAllSubblocks("logWood", block2);
	    proxy.registerEventHandler(block2);
	    Blocks.FIRE.setFireInfo(block2, 5, 5);
	
	    Element.LOG_KNEE_BALD_CYPRESS.set(new ItemStack(block, 1, Short.MAX_VALUE));
	    Element.LOG_KNEE_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, Short.MAX_VALUE));
	
	    BlockKneeLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderKneeLog()));
	
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block2, 1, Short.MAX_VALUE));
	
	    FacadeHelper.addBuildcraftFacade(block);
	    FacadeHelper.addBuildcraftFacade(block2);
	
	  }
	
	  private static void createNewQuarterLogs() {
	    final CommonProxy proxy = Extrabiomes.proxy;
	    BlockNewQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderNewQuarterLog()));
	
	    final BlockNewQuarterLog block = new BlockNewQuarterLog(BlockSettings.NEWQUARTERLOG, "baldcypress");
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.NEWQUARTERLOG.getEnabled())
	      return;
	
	    block.setBlockName("extrabiomes.baldcypressquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F)
	        .setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
	    ((BlockNewQuarterLog) block).setDroppedItemStack(Element.LOG_BALD_CYPRESS.get());
	
	    proxy.setBlockHarvestLevel(block, "axe", 0);
	    proxy.registerBlock(block, ItemNewQuarterLog.class, "cornerlog_baldcypress");
	    proxy.registerOreInAllSubblocks("logWood", block);
	    proxy.registerEventHandler(block);
	    Blocks.FIRE.setFireInfo(block, 5, 5);
	
	    final BlockNewQuarterLog block2 = new BlockNewQuarterLog(BlockSettings.RAINBOWQUARTERLOG, "rainboweucalyptus");
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.RAINBOWQUARTERLOG.getEnabled())
	      return;
	
	    block2.setBlockName("extrabiomes.rainboweucalyptusquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
	    ((BlockNewQuarterLog) block2).setDroppedItemStack(Element.LOG_RAINBOW_EUCALYPTUS.get());
	
	    proxy.setBlockHarvestLevel(block2, "axe", 0);
	    proxy.registerBlock(block2, ItemNewQuarterLog.class, "cornerlog_rainboweucalyptus");
	    proxy.registerOreInAllSubblocks("logWood", block2);
	    proxy.registerEventHandler(block2);
	    Blocks.FIRE.setFireInfo(block2, 5, 5);
	
	    final BlockNewQuarterLog block3 = new BlockNewQuarterLog(BlockSettings.OAKQUARTERLOG, "oak");
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.OAKQUARTERLOG.getEnabled())
	      return;
	
	    block3.setBlockName("extrabiomes.oakquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
	    ((BlockNewQuarterLog) block3).setDroppedItemStack(new ItemStack(Item.getItemFromBlock(Blocks.log), 1, 0));
	
	    proxy.setBlockHarvestLevel(block3, "axe", 0);
	    proxy.registerBlock(block3, ItemNewQuarterLog.class, "cornerlog_oak");
	    proxy.registerOreInAllSubblocks("logWood", block3);
	    proxy.registerEventHandler(block3);
	    Blocks.FIRE.setFireInfo(block3, 5, 5);
	
	    final BlockNewQuarterLog block4 = new BlockNewQuarterLog(BlockSettings.FIRQUARTERLOG, "fir");
	    if (!ModuleControlSettings.SUMMA.isEnabled() && !BlockSettings.FIRQUARTERLOG.getEnabled())
	      return;
	
	    block4.setBlockName("extrabiomes.firquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
	    ((BlockNewQuarterLog) block4).setDroppedItemStack(Element.LOG_FIR.get());
	
	    proxy.setBlockHarvestLevel(block4, "axe", 0);
	    proxy.registerBlock(block4, ItemNewQuarterLog.class, "cornerlog_fir");
	    proxy.registerOreInAllSubblocks("logWood", block4);
	    proxy.registerEventHandler(block4);
	    Blocks.FIRE.setFireInfo(block4, 5, 5);
	
	    final BlockNewQuarterLog block5 = new BlockNewQuarterLog(BlockSettings.REDWOODQUARTERLOG, "redwood");
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.REDWOODQUARTERLOG.getEnabled())
	      return;
	
	    block5.setBlockName("extrabiomes.redwoodquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
	    ((BlockNewQuarterLog) block5).setDroppedItemStack(Element.LOG_REDWOOD.get());
	    // block5.setRenderId(renderId);
	
	    proxy.setBlockHarvestLevel(block5, "axe", 0);
	    proxy.registerBlock(block5, ItemNewQuarterLog.class, "cornerlog_redwood");
	    proxy.registerOreInAllSubblocks("logWood", block5);
	    proxy.registerEventHandler(block5);
	    Blocks.FIRE.setFireInfo(block5, 5, 5);
	
	    Element.LOG_QUARTER_BALD_CYPRESS.set(new ItemStack(block, 1, Short.MAX_VALUE));
	    Element.LOG_QUARTER_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, Short.MAX_VALUE));
	    Element.LOG_QUARTER_OAK.set(new ItemStack(block3, 1, Short.MAX_VALUE));
	    Element.LOG_QUARTER_FIR.set(new ItemStack(block4, 1, Short.MAX_VALUE));
	    Element.LOG_QUARTER_REDWOOD.set(new ItemStack(block5, 1, Short.MAX_VALUE));
	
	    // BlockNewQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new
	    // RenderNewQuarterLog()));
	
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block2, 1, Short.MAX_VALUE));
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block3, 1, Short.MAX_VALUE));
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block4, 1, Short.MAX_VALUE));
	    ForestryModHelper.addToForesterBackpack(new ItemStack(block5, 1, Short.MAX_VALUE));
	    //FacadeHelper.addBuildcraftFacade(block, i);
	    //FacadeHelper.addBuildcraftFacade(block2, i);
	    //FacadeHelper.addBuildcraftFacade(block3, i);
	    //FacadeHelper.addBuildcraftFacade(block4, i);
	    //FacadeHelper.addBuildcraftFacade(block5, i);
	
	  }
	
	  private static void createQuarterLogs() {
	    final boolean blockIDNW = BlockSettings.QUARTERLOG0.getEnabled();
	    final boolean blockIDNE = BlockSettings.QUARTERLOG1.getEnabled();
	    final boolean blockIDSW = BlockSettings.QUARTERLOG2.getEnabled();
	    final boolean blockIDSE = BlockSettings.QUARTERLOG3.getEnabled();
	    if (!blockIDNE || !blockIDNW || !blockIDSE || !blockIDSW)
	      return;
	
	    final BlockQuarterLog blockNW = new BlockQuarterLog(BlockSettings.QUARTERLOG0, 144, BlockQuarterLog.BarkOn.NW);
	    final BlockQuarterLog blockNE = new BlockQuarterLog(BlockSettings.QUARTERLOG1, 144, BlockQuarterLog.BarkOn.NE);
	    final BlockQuarterLog blockSW = new BlockQuarterLog(BlockSettings.QUARTERLOG2, 144, BlockQuarterLog.BarkOn.SW);
	    final BlockQuarterLog blockSE = new BlockQuarterLog(BlockSettings.QUARTERLOG3, 144, BlockQuarterLog.BarkOn.SE);
	
	    for (final BlockQuarterLog block : new BlockQuarterLog[] { blockNW, blockNE, blockSW, blockSE }) {
	      block.setUnlocalizedName("extrabiomes.log.quarter");
	
	      alterLog(block, "log_old_quarter");
	    }
	
	    Element.LOG_HUGE_FIR_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.FIR.getMetadata()));
	    Element.LOG_HUGE_FIR_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.FIR.getMetadata()));
	    Element.LOG_HUGE_FIR_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.FIR.getMetadata()));
	    Element.LOG_HUGE_FIR_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.FIR.getMetadata()));
	    Element.LOG_HUGE_OAK_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.OAK.getMetadata()));
	    Element.LOG_HUGE_OAK_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.OAK.getMetadata()));
	    Element.LOG_HUGE_OAK_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.OAK.getMetadata()));
	    Element.LOG_HUGE_OAK_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.OAK.getMetadata()));
	    Element.LOG_HUGE_REDWOOD_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.REDWOOD.getMetadata()));
	    Element.LOG_HUGE_REDWOOD_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.REDWOOD.getMetadata()));
	    Element.LOG_HUGE_REDWOOD_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.REDWOOD.getMetadata()));
	    Element.LOG_HUGE_REDWOOD_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.REDWOOD.getMetadata()));
	
	    // Create the recipies to update logs
	
	    BlockQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderQuarterLog()));
	
	    for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType.values()) {
	      FacadeHelper.addBuildcraftFacade(blockSE, type.getMetadata());
	    }
	  }
	
	  private static void createRedRock() {
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.REDROCK.getEnabled())
	      return;
	
	    final BlockRedRock block = new BlockRedRock();
	    block.setBlockName("extrabiomes.redrock").setHardness(1.5F).setResistance(2.0F).setCreativeTab(Extrabiomes.tabsEBXL);
	
	    final CommonProxy proxy = Extrabiomes.proxy;
	    proxy.setBlockHarvestLevel(block, "pickaxe", 0);
	    proxy.registerBlock(block, extrabiomes.items.ItemRedRock.class, "terrain_blocks1");
	
	    Element.RED_ROCK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK.metadata()));
	    Element.RED_COBBLE.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_COBBLE.metadata()));
	    Element.RED_ROCK_BRICK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK_BRICK.metadata()));
	
	    Extrabiomes.postInitEvent(new RedRockActiveEvent(block));
	    BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.MOUNTAINRIDGE, block, block);
	
	    ForestryModHelper.addToDiggerBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
	    for (final BlockRedRock.BlockType type : BlockRedRock.BlockType.values()) {
	      //FacadeHelper.addBuildcraftFacade(block, type.metadata());
	    }
	  }
	
	  private static void createSapling() {
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.SAPLING.getEnabled())
	      return;
	
	    final BlockCustomSapling block = new BlockCustomSapling(16);
	    block.setUnlocalizedName("extrabiomes.sapling").setHardness(0.0F).setSoundType(SoundType.GROUND).setCreativeTab(Extrabiomes.tabsEBXL);
	
	    final CommonProxy proxy = Extrabiomes.proxy;
	    proxy.registerBlock(block, extrabiomes.items.ItemSapling.class, "saplings_1");
	    proxy.registerOreInAllSubblocks("treeSapling", block);
	
	    Element.SAPLING_ACACIA.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.ACACIA.metadata()));
	    Element.SAPLING_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.UMBER.metadata()));
	    Element.SAPLING_AUTUMN_ORANGE.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.GOLDENROD.metadata()));
	    Element.SAPLING_AUTUMN_PURPLE.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.VERMILLION.metadata()));
	    Element.SAPLING_AUTUMN_YELLOW.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.CITRINE.metadata()));
	    Element.SAPLING_FIR.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.FIR.metadata()));
	    Element.SAPLING_REDWOOD.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.REDWOOD.metadata()));
	    Element.SAPLING_CYPRESS.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.CYPRESS.metadata()));
	
	    final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
	
	    // Temp fix so that NEI shows the fermenter recipies when you try to view
	    // uses of saplings.
	    // ForestryModHelper.registerSapling(stack);
	    ForestryModHelper.registerSapling(Element.SAPLING_ACACIA.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_BROWN.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_ORANGE.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_PURPLE.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_YELLOW.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_FIR.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_REDWOOD.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_CYPRESS.get());
	    ForestryModHelper.addToForesterBackpack(stack);
	
	    // all but redwood
	    final Element[] forestrySaplings = { Element.SAPLING_ACACIA, Element.SAPLING_AUTUMN_BROWN, Element.SAPLING_AUTUMN_ORANGE, Element.SAPLING_AUTUMN_PURPLE,
	        Element.SAPLING_AUTUMN_YELLOW, Element.SAPLING_FIR, Element.SAPLING_CYPRESS };
	    for (final Element sapling : forestrySaplings) {
	      ForestryModHelper.registerGermling(sapling.get());
	    }
	
	    proxy.registerEventHandler(new SaplingBonemealEventHandler(block));
	    proxy.registerFuelHandler(new SaplingFuelHandler(block));
	  }
	
	  private static void createNewSapling() {
	    if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.NEWSAPLING.getEnabled())
	      return;
	
	    final BlockNewSapling block = new BlockNewSapling();
	    block.setBlockName("extrabiomes.sapling").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);
	
	    final CommonProxy proxy = Extrabiomes.proxy;
	    proxy.registerBlock(block, extrabiomes.items.ItemNewSapling.class, "saplings_2");
	    proxy.registerOreInAllSubblocks("treeSapling", block);
	
	    Element.SAPLING_BALD_CYPRESS.set(new ItemStack(block, 1, BlockNewSapling.BlockType.BALD_CYPRESS.metadata()));
	    Element.SAPLING_JAPANESE_MAPLE.set(new ItemStack(block, 1, BlockNewSapling.BlockType.JAPANESE_MAPLE.metadata()));
	    Element.SAPLING_JAPANESE_MAPLE_SHRUB.set(new ItemStack(block, 1, BlockNewSapling.BlockType.JAPANESE_MAPLE_SHRUB.metadata()));
	    Element.SAPLING_RAINBOW_EUCALYPTUS.set(new ItemStack(block, 1, BlockNewSapling.BlockType.RAINBOW_EUCALYPTUS.metadata()));
	    Element.SAPLING_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockNewSapling.BlockType.SAKURA_BLOSSOM.metadata()));
	
	    final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
	
	    // Temp fix so that NEI shows the fermenter recipies when you try to view
	    // uses of saplings.
	    // ForestryModHelper.registerSapling(stack);
	    ForestryModHelper.registerSapling(Element.SAPLING_BALD_CYPRESS.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_JAPANESE_MAPLE.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_JAPANESE_MAPLE_SHRUB.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_RAINBOW_EUCALYPTUS.get());
	    ForestryModHelper.registerSapling(Element.SAPLING_SAKURA_BLOSSOM.get());
	    ForestryModHelper.addToForesterBackpack(stack);
	
	    // all but redwood
	    final Element[] forestrySaplings = { Element.SAPLING_JAPANESE_MAPLE, Element.SAPLING_JAPANESE_MAPLE_SHRUB, Element.SAPLING_SAKURA_BLOSSOM };
	    for (final Element sapling : forestrySaplings) {
	      ForestryModHelper.registerGermling(sapling.get());
	    }
	
	    proxy.registerEventHandler(new SaplingBonemealNewEventHandler(block));
	    proxy.registerFuelHandler(new SaplingFuelHandler(block));
	  }
	
		private static void createWood() {
			if (BlockSettings.CUSTOMLOG.getEnabled()) {

				final BlockEBXLLog<Log_A_Type> log = BlockEBXLLog.create(Log_A_Type.class, Log_A_Type.FIR);
				log.setUnlocalizedName("extrabiomes.log");

				alterLog(log, "log1");

				Element.LOG_ACACIA.set(new ItemStack(log, 1, Log_A_Type.ACACIA.getMetadata()));
				Element.LOG_FIR.set(new ItemStack(log, 1, Log_A_Type.FIR.getMetadata()));
				Element.LOG_CYPRESS.set(new ItemStack(log, 1, Log_A_Type.CYPRESS.getMetadata()));
				Element.LOG_JAPANESE_MAPLE.set(new ItemStack(log, 1, Log_A_Type.JAPANESE_MAPLE.getMetadata()));

				ForestryModHelper.addToForesterBackpack(new ItemStack(log, 1, Short.MAX_VALUE));
				for (final Log_A_Type type : Log_A_Type.values()) {
					//FacadeHelper.addBuildcraftFacade(block, type.getMetadata());
					LogHelper.fine("Successfully built log for %s", type.getName());
				}
			}

			if (BlockSettings.NEWLOG.getEnabled()) {
				final BlockEBXLLog<Log_B_Type> log = BlockEBXLLog.create(Log_B_Type.class, Log_B_Type.AUTUMN);
				log.setUnlocalizedName("extrabiomes.newlog");

				alterLog(log, "log2");

				Element.LOG_RAINBOW_EUCALYPTUS.set(new ItemStack(log, 1, Log_B_Type.RAINBOW_EUCALYPTUS.getMetadata()));
				Element.LOG_AUTUMN.set(new ItemStack(log, 1, Log_B_Type.AUTUMN.getMetadata()));
				Element.LOG_BALD_CYPRESS.set(new ItemStack(log, 1, Log_B_Type.BALD_CYPRESS.getMetadata()));
				Element.LOG_REDWOOD.set(new ItemStack(log, 1, Log_B_Type.REDWOOD.getMetadata()));

				ForestryModHelper.addToForesterBackpack(new ItemStack(log, 1, Short.MAX_VALUE));
				for (final Log_B_Type type : Log_B_Type.values()) {
					//FacadeHelper.addBuildcraftFacade(log, type.getMetadata());
					LogHelper.fine("Successfully built log for %s", type.getName());
				}
			}
		}
		
		private static void alterLog(Block log, String name) {
			final CommonProxy proxy = Extrabiomes.proxy;
			
			proxy.setBlockHarvestLevel(log, "axe", 0);
			proxy.registerBlock(log, MultiItemBlock.class, name);
			proxy.registerOreInAllSubblocks("logWood", log);
			proxy.registerEventHandler(log);
			
			Blocks.FIRE.setFireInfo(log, 5, 5);
		}
  }
}
