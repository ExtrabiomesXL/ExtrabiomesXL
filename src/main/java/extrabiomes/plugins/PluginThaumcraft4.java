package extrabiomes.plugins;

import extrabiomes.api.Stuff;
import extrabiomes.lib.Element;
import extrabiomes.module.fabrica.scarecrow.ItemScarecrow;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class PluginThaumcraft4 {
	public static void preInit() {
		
	}
	
	public static void Init() {
		
	}
	
	public static void PostInit() {
		// Add our logs
		ThaumcraftApi.registerObjectTag(Element.AUTUMN_SHRUB.get().itemID, Element.AUTUMN_SHRUB.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.CATTAIL.get().itemID, Element.CATTAIL.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_BROWN_SHORT.get().itemID, Element.GRASS_BROWN_SHORT.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_BROWN.get().itemID, Element.GRASS_BROWN.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_DEAD.get().itemID, Element.GRASS_DEAD.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_DEAD_TALL.get().itemID, Element.GRASS_DEAD_TALL.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.AIR, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_DEAD_YELLOW.get().itemID, Element.GRASS_DEAD_YELLOW.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(Element.LEAFPILE.get().itemID, Element.LEAFPILE.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(Element.ROOT.get().itemID, Element.ROOT.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		
		ThaumcraftApi.registerObjectTag(Element.FLOWER_ORANGE.get().itemID, Element.FLOWER_ORANGE.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Element.FLOWER_PURPLE.get().itemID, Element.FLOWER_PURPLE.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Element.FLOWER_WHITE.get().itemID, Element.FLOWER_WHITE.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Element.HYDRANGEA.get().itemID, Element.HYDRANGEA.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		
		
		ThaumcraftApi.registerObjectTag(Element.TINY_CACTUS.get().itemID, Element.TINY_CACTUS.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.ENTROPY, 1));
		
		ThaumcraftApi.registerObjectTag(Element.TOADSTOOL.get().itemID, Element.TOADSTOOL.get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1).add(Aspect.LIFE, 1));
		
		ThaumcraftApi.registerObjectTag(Element.LOGTURNER.get().itemID, Element.LOGTURNER.get().getItemDamage(), (new AspectList()).add(Aspect.TOOL, 2));
		ThaumcraftApi.registerObjectTag(Element.RED_COBBLE.get().itemID, Element.RED_COBBLE.get().getItemDamage(), (new AspectList()).add(Aspect.STONE, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Element.RED_ROCK.get().itemID, Element.RED_ROCK.get().getItemDamage(), (new AspectList()).add(Aspect.STONE, 2).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Element.RED_ROCK_BRICK.get().itemID, Element.RED_ROCK_BRICK.get().getItemDamage(), (new AspectList()).add(Aspect.STONE, 2).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Stuff.slabRedRock.get().blockID, -1, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Stuff.slabRedRockDouble.get().blockID, -1, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Stuff.stairsRedCobble.get().blockID, -1, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Stuff.stairsRedRockBrick.get().blockID, -1, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Stuff.wall.get().blockID, -1, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.FIRE, 1));
		
		ThaumcraftApi.registerObjectTag(Stuff.paste.get().itemID, -1, (new AspectList()).add(Aspect.SENSES, 1));
		ThaumcraftApi.registerObjectTag(Stuff.scarecrow.get().itemID, -1, (new AspectList()).add(Aspect.CROP, 4).add(Aspect.HUNGER, 2));
		ThaumcraftApi.registerObjectTag(Stuff.quickSand.get().blockID, -1, (new AspectList()).add(Aspect.WATER, 2).add(Aspect.EARTH, 3).add(Aspect.TRAP, 1));
		ThaumcraftApi.registerObjectTag(Element.CRACKEDSAND.get().itemID, Element.CRACKEDSAND.get().getItemDamage(), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 1));
		
		
		ThaumcraftApi.registerEntityTag(ItemScarecrow.NAME, (new AspectList()).add(Aspect.HARVEST, 5));
		
		//ThaumcraftApi.registerObjectTag(Stuff..get().itemID, Stuff..get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 4));
		
		//ThaumcraftApi.registerObjectTag(Element..get().itemID, Element..get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 4));
		//ThaumcraftApi.registerObjectTag(Element..get().itemID, Element..get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 4));
		
	}
}
