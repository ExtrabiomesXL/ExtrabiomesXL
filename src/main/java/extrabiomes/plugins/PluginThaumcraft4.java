package extrabiomes.plugins;

import net.minecraft.item.ItemStack;
import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.lib.Element;
import extrabiomes.module.fabrica.scarecrow.ItemScarecrow;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class PluginThaumcraft4 {
	public static void preInit() {
		
	}
	
	public static boolean isEnabled() {
	  return Extrabiomes.proxy.isModLoaded("Thaumcraft");
	}
	
	public static void init() {
	
	}
	
	public static void postInit() {
		// Add our logs
		ThaumcraftApi.registerObjectTag(Element.AUTUMN_SHRUB.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.CATTAIL.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_BROWN_SHORT.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_BROWN.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_DEAD.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_DEAD_TALL.get(), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.AIR, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(Element.GRASS_DEAD_YELLOW.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(Element.LEAFPILE.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(Element.ROOT.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.AIR, 1));
		
		ThaumcraftApi.registerObjectTag(Element.BUTTERCUP.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Element.HYDRANGEA.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Element.CALLA_WHITE.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(Element.HYDRANGEA.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.LIFE, 1));
		
		
		ThaumcraftApi.registerObjectTag(Element.TINY_CACTUS.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.SENSES, 1).add(Aspect.ENTROPY, 1));
		
		ThaumcraftApi.registerObjectTag(Element.TOADSTOOL.get(), (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1).add(Aspect.LIFE, 1));
		
		ThaumcraftApi.registerObjectTag(Element.LOGTURNER.get(), (new AspectList()).add(Aspect.TOOL, 2));
		ThaumcraftApi.registerObjectTag(Element.RED_COBBLE.get(), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Element.RED_ROCK.get(), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(Element.RED_ROCK_BRICK.get(), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.slabRedRock.get()), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.slabRedRockDouble.get()), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.stairsRedCobble.get()), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.stairsRedRockBrick.get()), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.wall.get()), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1));
		
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.paste.get()), (new AspectList()).add(Aspect.SENSES, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.scarecrow.get()), (new AspectList()).add(Aspect.CROP, 4).add(Aspect.HUNGER, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(Stuff.quickSand.get()), (new AspectList()).add(Aspect.WATER, 2).add(Aspect.EARTH, 3).add(Aspect.TRAP, 1));
		ThaumcraftApi.registerObjectTag(Element.CRACKEDSAND.get(), (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 1));
		
		
		ThaumcraftApi.registerEntityTag("ExtrabiomesXL." + ItemScarecrow.NAME, (new AspectList()).add(Aspect.HARVEST, 5));
		
		//ThaumcraftApi.registerObjectTag(Stuff..get().itemID, Stuff..get().getItemDamage(), (new AspectList()).add(Aspect.PLANT, 4));
		
		//ThaumcraftApi.registerObjectTag(Element..get(), (new AspectList()).add(Aspect.PLANT, 4));
		//ThaumcraftApi.registerObjectTag(Element..get(), (new AspectList()).add(Aspect.PLANT, 4));
		
	}
}
