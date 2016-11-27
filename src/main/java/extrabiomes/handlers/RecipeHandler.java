package extrabiomes.handlers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomFlower.BlockType;
import extrabiomes.helpers.LogHelper;
import extrabiomes.items.ItemCustomFood;
import extrabiomes.items.ItemCustomSeed;
import extrabiomes.lib.Element;
import extrabiomes.module.fabrica.block.BlockCustomWood;
import extrabiomes.proxy.CommonProxy;

public abstract class RecipeHandler
{
    
    public static void init()
    {
        writeLogTurnerRecipe();
        
        writeCrackedSandRecipes();
        writeLogRecipes();
        
        writeLogConversionRecipes();
        
        writeFlowerRecipes();
        writeLeafPileRecipes();

		writeSeedRecipes();
		writeFoodRecipes();
		
		writeWaterPantRecipes();
	}

    private static void writeWaterPantRecipes() {
    	if(!Element.WATERPLANT.isPresent()) return;
    	
    	final IRecipe recipe = new ShapelessOreRecipe(new ItemStack(Items.DYE, 1, 2), Element.WATERPLANT.get());
    	Extrabiomes.proxy.addRecipe(recipe);
    }
    
	private static void writeSeedRecipes() {
		if (!Stuff.seed.isPresent() || !Stuff.crop.isPresent()) return;

		for (ItemCustomSeed.SeedType type : ItemCustomSeed.SeedType.values()) {
			final Element seed_element;
			final Element crop_element;
			try {
				seed_element = Element.valueOf("SEED_" + type.name());
				crop_element = Element.valueOf("CROP_" + type.name());
			} catch (Exception e) {
				LogHelper.severe("Unable to find crop source for seed " + type);
				continue;
			}
			
			if (!seed_element.isPresent() || !crop_element.isPresent()) continue;

			final IRecipe recipe = new ShapelessOreRecipe(seed_element.get(),
					crop_element.get());
			Extrabiomes.proxy.addRecipe(recipe);
		}
    }
    
    private static void writeCrackedSandRecipes()
    {
        if (!Element.CRACKEDSAND.isPresent())
            return;
        
        // crackedSand + water = sand
        final IRecipe recipe = new ShapelessOreRecipe(Blocks.SAND, Element.CRACKEDSAND.get(),
                Items.WATER_BUCKET);
        Extrabiomes.proxy.addRecipe(recipe);
    }
    
    private static void writeFlowerRecipes()
    {
        final CommonProxy proxy = Extrabiomes.proxy;
        
		for (Element element : Element.values()) {
			if (!element.isPresent()) continue;
			final BlockType block;
			try {
				block = BlockCustomFlower.BlockType.valueOf(element.name());
			} catch (Exception e) {
				continue;
			}
			if (block != null) {
				final int color = block.color();
				final ItemStack dye;
				switch (color) {
					case -1:
						continue;
					case 0:
						dye = Element.DYE_BLACK.get();
						break;
					case 3:
						dye = Element.DYE_BROWN.get();
						break;
					case 4:
						dye = Element.DYE_BLUE.get();
						break;
					case 15:
						dye = Element.DYE_WHITE.get();
						break;
					default:
						dye = new ItemStack(Items.DYE, 1, color);
				}
				final IRecipe recipe = new ShapelessOreRecipe(dye, element.get());
				proxy.addRecipe(recipe);
			}
		}

		if (Element.VINE_GLORIOSA.isPresent()) {
			final ItemStack gloriosa = Element.VINE_GLORIOSA.get();
			final ItemStack dye = new ItemStack(Items.DYE, 1, 1);
			final IRecipe recipe = new ShapelessOreRecipe(dye, gloriosa);
			proxy.addRecipe(recipe);
		}

        if (Element.TOADSTOOL.isPresent())
        {
            final ItemStack toadstool = Element.TOADSTOOL.get();
            
            // emptyBowl + redMushroom + 2 toadstools = soup
            IRecipe recipe = new ShapelessOreRecipe(Items.MUSHROOM_STEW, Blocks.RED_MUSHROOM, toadstool, toadstool, Items.BOWL);
            proxy.addRecipe(recipe);
            
            // emptyBowl + brownMushroom + 2 toadstools = soup
            recipe = new ShapelessOreRecipe(Items.MUSHROOM_STEW, Blocks.RED_MUSHROOM, toadstool, toadstool, Items.BOWL);
            proxy.addRecipe(recipe);

			// toadstool = brown dye
			recipe = new ShapelessOreRecipe(Element.DYE_BROWN.get(), toadstool);
			proxy.addRecipe(recipe);
        }
    }
    
    private static void writeLeafPileRecipes()
    {
        if (!Element.LEAFPILE.isPresent())
            return;
        
        // leafPile x 9 = leafBlock
        final IRecipe recipe = new ShapedOreRecipe(Blocks.LEAVES,
                new String[] { "lll", "lll", "lll" }, 'l', Element.LEAFPILE.get());
        Extrabiomes.proxy.addRecipe(recipe);
    }
    
    private static void writeLogConversionRecipes() {
        final CommonProxy proxy = Extrabiomes.proxy;
        
    	if(Element.LOG_QUARTER_BALD_CYPRESS.isPresent() && Element.LOG_BALD_CYPRESS.isPresent()) {
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_BALD_CYPRESS.get().getItem(), 4, Element.LOG_BALD_CYPRESS.get().getItemDamage()),new String[] { "ll","ll" }, 'l', Element.LOG_QUARTER_BALD_CYPRESS.get()));
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_QUARTER_BALD_CYPRESS.get().getItem(), 4, 0), new String[] { "ll","ll" }, 'l', Element.LOG_BALD_CYPRESS.get()));
    	}
    	
    	if(Element.LOG_KNEE_BALD_CYPRESS.isPresent() && Element.LOG_BALD_CYPRESS.isPresent()){
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_BALD_CYPRESS.get().getItem(), 3, Element.LOG_BALD_CYPRESS.get().getItemDamage()),new String[] { " l","ll" }, 'l', Element.LOG_KNEE_BALD_CYPRESS.get()));
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_KNEE_BALD_CYPRESS.get().getItem(), 3, 0), new String[] { " l","ll" }, 'l', Element.LOG_BALD_CYPRESS.get()));
    	}
    	
    	if(Element.LOG_QUARTER_RAINBOW_EUCALYPTUS.isPresent() && Element.LOG_RAINBOW_EUCALYPTUS.isPresent()) {
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_RAINBOW_EUCALYPTUS.get().getItem(), 4, Element.LOG_RAINBOW_EUCALYPTUS.get().getItemDamage()),new String[] { "ll","ll" }, 'l', Element.LOG_QUARTER_RAINBOW_EUCALYPTUS.get()));
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_QUARTER_RAINBOW_EUCALYPTUS.get().getItem(), 4, 0), new String[] { "ll","ll" }, 'l', Element.LOG_RAINBOW_EUCALYPTUS.get()));
    	}
    	
    	if(Element.LOG_KNEE_RAINBOW_EUCALYPTUS.isPresent() && Element.LOG_RAINBOW_EUCALYPTUS.isPresent()) {
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_RAINBOW_EUCALYPTUS.get().getItem(), 3, Element.LOG_RAINBOW_EUCALYPTUS.get().getItemDamage()),new String[] { " l","ll" }, 'l', Element.LOG_KNEE_RAINBOW_EUCALYPTUS.get()));
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_KNEE_RAINBOW_EUCALYPTUS.get().getItem(), 3, 0), new String[] { " l","ll" }, 'l', Element.LOG_RAINBOW_EUCALYPTUS.get()));
    	}
    	
    	if(Element.LOG_QUARTER_FIR.isPresent() && Element.LOG_FIR.isPresent()) {
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_FIR.get().getItem(), 4, Element.LOG_FIR.get().getItemDamage()),new String[] { "ll","ll" }, 'l', Element.LOG_QUARTER_FIR.get()));
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_QUARTER_FIR.get().getItem(), 4, 0), new String[] { "ll","ll" }, 'l', Element.LOG_FIR.get()));
    	}
    	if(Element.LOG_QUARTER_FIR.isPresent() && Element.LOG_HUGE_FIR_SE.isPresent()) {
            proxy.addRecipe(new ShapelessOreRecipe(new ItemStack(Element.LOG_QUARTER_FIR.get().getItem(), 1, 0), Element.LOG_HUGE_FIR_SE.get()));
        }
    	
    	if(Element.LOG_REDWOOD.isPresent() && Element.LOG_REDWOOD.isPresent()) {
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_REDWOOD.get().getItem(), 4, Element.LOG_REDWOOD.get().getItemDamage()),new String[] { "ll","ll" }, 'l', Element.LOG_QUARTER_REDWOOD.get()));
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_QUARTER_REDWOOD.get().getItem(), 4, 0), new String[] { "ll","ll" }, 'l', Element.LOG_REDWOOD.get()));
    	}
    	if(Element.LOG_QUARTER_REDWOOD.isPresent() && Element.LOG_HUGE_REDWOOD_SE.isPresent()) {
            proxy.addRecipe(new ShapelessOreRecipe(new ItemStack(Element.LOG_QUARTER_REDWOOD.get().getItem(), 1, 0), Element.LOG_HUGE_REDWOOD_SE.get()));
    	}
    	
    	if(Element.LOG_HUGE_OAK_SE.isPresent() && Element.LOG_QUARTER_OAK.isPresent()) {
            proxy.addRecipe(new ShapelessOreRecipe(new ItemStack(Element.LOG_QUARTER_OAK.get().getItem(), 1, 0), Element.LOG_HUGE_OAK_SE.get()));
    	}
    	if(Element.LOG_QUARTER_OAK.isPresent()) {
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.LOG, 4, 0), new String[] { "ll","ll" }, 'l', Element.LOG_QUARTER_OAK.get()));
    		proxy.addRecipe(new ShapedOreRecipe(new ItemStack(Element.LOG_QUARTER_OAK.get().getItem(), 4, 0), new String[] { "ll","ll" }, 'l', new ItemStack(Blocks.LOG, 1, 0)));
		}
    }
    
    private static void writeLogRecipes()
    {
        if (!Stuff.planks.isPresent())
            return;
        
        final CommonProxy proxy = Extrabiomes.proxy;
        final Block block = Stuff.planks.get();
        final ItemStack charcoal = new ItemStack(Items.COAL, 1, 1);
        
        if (Element.LOG_ACACIA.isPresent())
        {
            final ItemStack logAcacia = Element.LOG_ACACIA.get();
            final ItemStack acaciaPlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.ACACIA.getMetadata());
            
            // acaciaLog  = acaciaPlanks
            final IRecipe recipe = new ShapelessOreRecipe(acaciaPlanks, logAcacia);
            proxy.addRecipe(recipe);
            
            // acaciaLog ==> charcoal
            proxy.addSmelting(logAcacia, charcoal, 0.15F);
        }
        
        if (Element.LOG_JAPANESE_MAPLE.isPresent())
        {
            final ItemStack logMaple = Element.LOG_JAPANESE_MAPLE.get();
            final ItemStack maplePlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.JAPANESE_MAPLE.getMetadata());
            
            // acaciaLog  = acaciaPlanks
            final IRecipe recipe = new ShapelessOreRecipe(maplePlanks, logMaple);
            proxy.addRecipe(recipe);
            
            // acaciaLog ==> charcoal
            proxy.addSmelting(logMaple, charcoal, 0.15F);
        }
        
        if (Element.LOG_AUTUMN.isPresent())
        {
            final ItemStack logAutumn = Element.LOG_AUTUMN.get();
            final ItemStack autumnPlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.AUTUMN.getMetadata());
            
            // acaciaLog  = acaciaPlanks
            final IRecipe recipe = new ShapelessOreRecipe(autumnPlanks, logAutumn);
            proxy.addRecipe(recipe);
            
            // acaciaLog ==> charcoal
            proxy.addSmelting(logAutumn, charcoal, 0.15F);
        }
        
        if(Element.LOG_HUGE_OAK_NW.isPresent() && Element.LOG_HUGE_OAK_NE.isPresent() && Element.LOG_HUGE_OAK_SW.isPresent() && Element.LOG_HUGE_OAK_SE.isPresent()) {
	        for (final ItemStack itemstack : new ItemStack[] { Element.LOG_HUGE_OAK_NW.get(), Element.LOG_HUGE_OAK_NE.get(), Element.LOG_HUGE_OAK_SW.get(), Element.LOG_HUGE_OAK_SE.get() }) {
	    		final IRecipe recipe = new ShapelessOreRecipe(new ItemStack(Blocks.LOG), itemstack);
	            Extrabiomes.proxy.addRecipe(recipe);
	    	}
        }
        
        if(Element.LOG_FIR.isPresent() && Element.LOG_HUGE_FIR_NW.isPresent() && Element.LOG_HUGE_FIR_NE.isPresent() && Element.LOG_HUGE_FIR_SW.isPresent() && Element.LOG_HUGE_FIR_SE.isPresent()) {
        	for (final ItemStack itemstack : new ItemStack[] { Element.LOG_HUGE_FIR_NW.get(), Element.LOG_HUGE_FIR_NE.get(), Element.LOG_HUGE_FIR_SW.get(), Element.LOG_HUGE_FIR_SE.get() }) {
        		final IRecipe recipe = new ShapelessOreRecipe(Element.LOG_FIR.get(), itemstack);
                Extrabiomes.proxy.addRecipe(recipe);
        	}
        }
        
        if(Element.LOG_REDWOOD.isPresent() && Element.LOG_HUGE_REDWOOD_NW.isPresent() && Element.LOG_HUGE_REDWOOD_NE.isPresent() && Element.LOG_HUGE_REDWOOD_SW.isPresent() && Element.LOG_HUGE_REDWOOD_SE.isPresent()) {
        	for (final ItemStack itemstack : new ItemStack[] { Element.LOG_HUGE_REDWOOD_NW.get(), Element.LOG_HUGE_REDWOOD_NE.get(), Element.LOG_HUGE_REDWOOD_SW.get(), Element.LOG_HUGE_REDWOOD_SE.get()}) {
        		final IRecipe recipe = new ShapelessOreRecipe(Element.LOG_REDWOOD.get(), itemstack);
                Extrabiomes.proxy.addRecipe(recipe);
        	}
        }
        
        for (final Element logCypress : new Element[] { Element.LOG_CYPRESS })
        {
            if (logCypress.isPresent())
            {
                final ItemStack cypressPlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.CYPRESS.getMetadata());
                
                // cypressLog  = cypressPlanks
                final IRecipe recipe = new ShapelessOreRecipe(cypressPlanks, logCypress.get());
                proxy.addRecipe(recipe);
                
                // cypressLog ==> charcoal
                proxy.addSmelting(logCypress.get(), charcoal, 0.15F);
            }
        }
        
        for (final Element logSakura : new Element[] { Element.LOG_SAKURA_BLOSSOM })
        {
            if (logSakura.isPresent())
            {
                final ItemStack sakuraPlanks = new ItemStack(block, 2, BlockCustomWood.BlockType.SAKURA_BLOSSOM.getMetadata());
                
                // cypressLog  = cypressPlanks
                final IRecipe recipe = new ShapelessOreRecipe(sakuraPlanks, logSakura.get());
                proxy.addRecipe(recipe);
                
                // cypressLog ==> charcoal
                proxy.addSmelting(logSakura.get(), charcoal, 0.15F);
            }
        }
        
        for (final Element logBaldCypress : new Element[] { Element.LOG_BALD_CYPRESS, Element.LOG_QUARTER_BALD_CYPRESS, Element.LOG_KNEE_BALD_CYPRESS })
        {
            if (logBaldCypress.isPresent())
            {
                final ItemStack cypressPlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.BALD_CYPRESS.getMetadata());
                
                // cypressLog  = cypressPlanks
                final IRecipe recipe = new ShapelessOreRecipe(cypressPlanks, logBaldCypress.get());
                proxy.addRecipe(recipe);
                
                // cypressLog ==> charcoal
                proxy.addSmelting(logBaldCypress.get(), charcoal, 0.15F);
            }
        }
        
        for (final Element logRainbow : new Element[] { Element.LOG_RAINBOW_EUCALYPTUS, Element.LOG_QUARTER_RAINBOW_EUCALYPTUS, Element.LOG_KNEE_RAINBOW_EUCALYPTUS })
        {
            if (logRainbow.isPresent())
            {
                final ItemStack rainbowPlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.RAINBOW_EUCALYPTUS.getMetadata());
                
                // rainbowLog  = rainbowPlanks
                final IRecipe recipe = new ShapelessOreRecipe(rainbowPlanks, logRainbow.get());
                proxy.addRecipe(recipe);
                
                // rainbowLog ==> charcoal
                proxy.addSmelting(logRainbow.get(), charcoal, 0.15F);
            }
        }
        
        for (final Element firLog : new Element[] { Element.LOG_FIR, Element.LOG_QUARTER_FIR })
        {
            if (firLog.isPresent())
            {
                final ItemStack firPlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.FIR.getMetadata());
                
                // firLog  = firPlanks
                final IRecipe recipe = new ShapelessOreRecipe(firPlanks, firLog.get());
                proxy.addRecipe(recipe);
                
                // firLog ==> charcoal
                proxy.addSmelting(firLog.get(), charcoal, 0.15F);
            }
        }
        
        for (final Element redwoodLog : new Element[] { Element.LOG_QUARTER_REDWOOD, Element.LOG_REDWOOD })
        {
            if (redwoodLog.isPresent())
            {
                final ItemStack redwoodPlanks = new ItemStack(block, 4, BlockCustomWood.BlockType.REDWOOD.getMetadata());
                
                // redwoodLog  = redwoodPlanks
                final IRecipe recipe = new ShapelessOreRecipe(redwoodPlanks, redwoodLog.get());
                proxy.addRecipe(recipe);
                
                // redwoodLog ==> charcoal
                proxy.addSmelting(redwoodLog.get(), charcoal, 0.15F);
            }
        }
        
        for (final Element oakLog : new Element[] { Element.LOG_QUARTER_OAK })
        {
            if (oakLog.isPresent())
            {
                final ItemStack oakPlanks = new ItemStack(Blocks.PLANKS, 4);
                
                // oakLog  = oakPlanks
                final IRecipe recipe = new ShapelessOreRecipe(oakPlanks, oakLog.get());
                proxy.addRecipe(recipe);
                
                // oakLog ==> charcoal
                proxy.addSmelting(oakLog.get(), charcoal, 0.15F);
            }
        }
    }
    
    private static void writeLogTurnerRecipe()
    {
        if (!Element.LOGTURNER.isPresent())
            return;
        
        final IRecipe recipe = new ShapedOreRecipe(Element.LOGTURNER.get(), new String[] { "ss",
                " s", "ss" }, 's', "stickWood");
        Extrabiomes.proxy.addRecipe(recipe);
    }
    
    private static void writeFoodRecipes()
    {
    	if (!Stuff.food.isPresent())
    		return;
    	final Item foodItem = Stuff.food.get();
    	final CommonProxy proxy = Extrabiomes.proxy;
    	
    	final ItemStack chocolate = new ItemStack(foodItem, 1, ItemCustomFood.FoodType.CHOCOLATE.meta);
    	final ItemStack cocoa_powder = new ItemStack(Items.DYE, 1, 3);
    	IRecipe recipe = new ShapelessOreRecipe(chocolate, cocoa_powder, Items.SUGAR, Items.MILK_BUCKET);
    	proxy.addRecipe(recipe);
    	
    	final ItemStack choco_strawberry = new ItemStack(foodItem, 1, ItemCustomFood.FoodType.CHOCOLATE_STRAWBERRY.meta);
    	recipe = new ShapelessOreRecipe(choco_strawberry, chocolate, "cropStrawberry");
    	proxy.addRecipe(recipe);
    }
    
}
