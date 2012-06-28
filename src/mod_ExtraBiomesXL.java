package net.minecraft.src;

import net.minecraft.src.forge.*;
import java.util.*;
import net.minecraft.client.Minecraft;
import java.io.*;

public class mod_ExtraBiomesXL extends BaseMod
{
	static Configuration configuration = new Configuration(new File(Minecraft.getMinecraftDir(), "/ExtraBiomesXL.cfg"));
	
	static int coverGrassID = configurationProperties();
    static int crackedSandID;
    static int deadGrassID;
    static int deadGrassYID;
    static int deadTallGrassID;
    static int leavesBrownID;
    static int leavesOrangeID;
    static int leavesRedID;
    static int leavesYellowID;
    static int shortGrassID;
    static int redRockID;
    static int catTailID;
    static int hydrangeaID;
    static int brownGrassID;
    static int brownGrassShortID;
    static int orangeFlowerID;
    static int whiteFlowerID;
    static int purpleFlowerID;
    static int autumnShrubID;
    static int toadstoolID;
    static int tinyCactusID;
    static int wheatGrassID;
    static int thickGrassID;
    static int quicksandID;
    static int autumnSaplingID;
    static int firSaplingID;
    static int rootID;
    static int leafPileID;
    static int redwoodSaplingID;
    
    static int catTailItemID;
    static int fertilizerID;
    
    public static Block coverGrass = new BlockCoverGrass(coverGrassID, 111).setHardness(0F).setResistance(0F).setBlockName("shortGrass").setStepSound(Block.soundGrassFootstep);
	public static Block crackedSand = new BlockCrackedSand(crackedSandID, 0).setHardness(1.2F).setResistance(0F).setBlockName("crackedSand").setStepSound(Block.soundStoneFootstep);
	public static Block deadGrass = new BlockDeadGrass(deadGrassID, 127).setHardness(0F).setResistance(0F).setBlockName("deadGrass").setStepSound(Block.soundGrassFootstep);
    public static Block deadGrassY = new BlockDeadGrass(deadGrassYID, 143).setHardness(0F).setResistance(0F).setBlockName("deadGrassY").setStepSound(Block.soundGrassFootstep);
    public static Block deadTallGrass = new BlockDeadGrass(deadTallGrassID, 159).setHardness(0F).setResistance(0F).setBlockName("deadTallGrass").setStepSound(Block.soundGrassFootstep);
    public static Block leavesBrown = (new BlockLeavesBrown(leavesBrownID, 3)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leaves").setRequiresSelfNotify();
    public static Block leavesOrange = (new BlockLeavesOrange(leavesOrangeID, 5)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leavesOrange").setRequiresSelfNotify();
    public static Block leavesRed = (new BlockLeavesRed(leavesRedID, 7)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leavesRed").setRequiresSelfNotify();
    public static Block leavesYellow = (new BlockLeavesYellow(leavesYellowID, 9)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leavesYellow").setRequiresSelfNotify();
    public static Block shortGrass = (BlockShortGrass)(new BlockShortGrass(shortGrassID, 14)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("shortgrass");
    public static Block redRock = new BlockRedRock(redRockID, 2).setHardness(2.0F).setResistance(2.0F).setBlockName("redRock").setStepSound(Block.soundStoneFootstep);
    public static Block catTail = new BlockCatTail(catTailID, 79).setHardness(0F).setResistance(0F).setBlockName("catTail").setStepSound(Block.soundGrassFootstep);
    public static Block hydrangea = new BlockHydrangea(hydrangeaID, 207).setHardness(0F).setResistance(0F).setBlockName("hydrangea").setStepSound(Block.soundGrassFootstep);
    public static Block brownGrass = new BlockBrownGrass(brownGrassID, 47).setHardness(0F).setResistance(0F).setBlockName("brownGrass").setStepSound(Block.soundGrassFootstep);
    public static Block brownGrassShort = new BlockBrownGrass(brownGrassShortID, 63).setHardness(0F).setResistance(0F).setBlockName("brownGrassShort").setStepSound(Block.soundGrassFootstep);
    public static Block orangeFlower = new BlockOrangeFlower(orangeFlowerID, 223).setHardness(0F).setResistance(0F).setBlockName("orangeFlower").setStepSound(Block.soundGrassFootstep);
    public static Block whiteFlower = new BlockWhiteFlower(whiteFlowerID, 94).setHardness(0F).setResistance(0F).setBlockName("whiteFlower").setStepSound(Block.soundGrassFootstep);
    public static Block purpleFlower = new BlockPurpleFlower(purpleFlowerID, 239).setHardness(0F).setResistance(0F).setBlockName("purpleFlower").setStepSound(Block.soundGrassFootstep);
    public static Block autumnShrub = new BlockAutumnShrub(autumnShrubID, 31).setHardness(0F).setResistance(0F).setBlockName("autumnShrub").setStepSound(Block.soundGrassFootstep);
    public static Block toadstool = new BlockToadstool(toadstoolID, 62).setHardness(0F).setResistance(0F).setBlockName("toadstool").setStepSound(Block.soundGrassFootstep);
    public static Block tinyCactus = new BlockTinyCactus(tinyCactusID, 46).setHardness(0F).setResistance(0F).setBlockName("tinyCactus").setStepSound(Block.soundClothFootstep);
    public static Block wheatGrass = new BlockWheatGrass(wheatGrassID, 78).setHardness(0F).setResistance(0F).setBlockName("wheatGrass").setStepSound(Block.soundGrassFootstep);
    public static Block thickGrass = new BlockThickGrass(thickGrassID, 30).setHardness(0F).setResistance(0F).setBlockName("thickGrass").setStepSound(Block.soundGrassFootstep);
    public static Block quicksand = new BlockQuicksand(quicksandID, 1).setHardness(4F).setResistance(0F).setBlockName("quicksand").setStepSound(Block.soundSandFootstep);
    public static Block autumnSapling = new BlockAutumnSapling(autumnSaplingID, 15).setHardness(0F).setResistance(0F).setBlockName("autumnSapling").setStepSound(Block.soundGrassFootstep);
	public static Block firSapling = new BlockFirSapling(firSaplingID, 191).setHardness(0F).setResistance(0F).setBlockName("firSapling").setStepSound(Block.soundGrassFootstep);
	public static Block root = new BlockRoot(rootID, 255).setHardness(0F).setResistance(0F).setBlockName("root").setStepSound(Block.soundGrassFootstep);
    public static Block leafPile = new BlockLeafPile(leafPileID, 11).setHardness(0F).setResistance(0F).setBlockName("leafPile").setStepSound(Block.soundGrassFootstep);
    public static Block redwoodSapling = new BlockRedwoodSapling(redwoodSaplingID, 110).setHardness(0F).setBlockName("redwoodSapling").setStepSound(Block.soundGrassFootstep);
    public static Item fertilizer = (new ItemFertilizer(fertilizerID)).setIconIndex(175).setItemName("fertilizer");
    public static Item catTailItem = (new ItemCatTail(catTailItemID, mod_ExtraBiomesXL.catTail)).setIconIndex(95).setItemName("catTailItem");
    
    public mod_ExtraBiomesXL()
	{
	}
    
    public void load()
	{
		MinecraftForgeClient.preloadTexture("/ExtraBiomesXL/extrabiomes.png");
		
		ModLoader.registerBlock(coverGrass);
		ModLoader.registerBlock(crackedSand);
		ModLoader.registerBlock(deadGrass);
        ModLoader.registerBlock(deadGrassY);
        ModLoader.registerBlock(deadTallGrass);
        ModLoader.registerBlock(leavesBrown);
        ModLoader.registerBlock(leavesOrange);
        ModLoader.registerBlock(leavesRed);
        ModLoader.registerBlock(leavesYellow);
        ModLoader.registerBlock(shortGrass);
        ModLoader.registerBlock(redRock);
        ModLoader.registerBlock(catTail);
        ModLoader.registerBlock(hydrangea);
        ModLoader.registerBlock(brownGrass);
        ModLoader.registerBlock(brownGrassShort);
        ModLoader.registerBlock(orangeFlower);
        ModLoader.registerBlock(whiteFlower);
        ModLoader.registerBlock(purpleFlower);
        ModLoader.registerBlock(autumnShrub);
        ModLoader.registerBlock(toadstool);
        ModLoader.registerBlock(tinyCactus);
        ModLoader.registerBlock(wheatGrass);
        ModLoader.registerBlock(thickGrass);
        ModLoader.registerBlock(quicksand);
        ModLoader.registerBlock(autumnSapling);
		ModLoader.registerBlock(firSapling);
		ModLoader.registerBlock(root);
        ModLoader.registerBlock(leafPile);
        ModLoader.registerBlock(redwoodSapling);
		
        ModLoader.addName(coverGrass, "Grass");
		ModLoader.addName(crackedSand, "Cracked Sand");
		ModLoader.addName(deadGrass, "Dead Grass");
        ModLoader.addName(deadGrassY, "Dead Grass");
        ModLoader.addName(deadTallGrass, "Dead Grass");
        ModLoader.addName(leavesBrown, "Leaves");
        ModLoader.addName(leavesOrange, "Leaves");
        ModLoader.addName(leavesRed, "Leaves");
        ModLoader.addName(leavesYellow, "Leaves");
        ModLoader.addName(shortGrass, "Short Grass");
        ModLoader.addName(redRock, "Red Rock");
        ModLoader.addName(catTail, "Cattail");
        ModLoader.addName(hydrangea, "Hydrangeas");
        ModLoader.addName(brownGrass, "Brown Grass");
        ModLoader.addName(brownGrassShort, "Brown Grass");
        ModLoader.addName(orangeFlower, "Orange Flowers");
        ModLoader.addName(whiteFlower, "White Flowers");
        ModLoader.addName(purpleFlower, "Purple Flowers");
        ModLoader.addName(autumnShrub, "Autumn Shrub");
        ModLoader.addName(toadstool, "Toadstool");
        ModLoader.addName(tinyCactus, "Tiny Cactus");
        ModLoader.addName(wheatGrass, "Wheatgrass");
        ModLoader.addName(thickGrass, "Grass");
        ModLoader.addName(quicksand, "Quicksand");
        ModLoader.addName(autumnSapling, "Autumn Sapling");
		ModLoader.addName(firSapling, "Fir Sapling");
		ModLoader.addName(root, "Root");
        ModLoader.addName(leafPile, "Leaf Pile");
        ModLoader.addName(redwoodSapling, "Redwood Sapling");
        
        ModLoader.addName(fertilizer, "Fertilizer");
        ModLoader.addName(catTailItem, "Cattail");
        
        ModLoader.addShapelessRecipe(new ItemStack(firSapling, 3), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Block.sapling, 1, 1)});
        ModLoader.addShapelessRecipe(new ItemStack(redwoodSapling, 3), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Block.sapling, 0, 0)});
        ModLoader.addShapelessRecipe(new ItemStack(fertilizer, 6), new Object[] {new ItemStack(Block.dirt), new ItemStack(Item.egg), new ItemStack(Item.seeds)});
    }
	
    public static int configurationProperties()
    {
    	configuration.load();
        coverGrassID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Cover Grass", 150).value);
        crackedSandID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Cracked Sand",  151).value);
        deadGrassID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Dead Grass", 152).value);
        deadGrassYID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Dead Grass Yellow", 153).value);
        deadTallGrassID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Dead Tall Grass", 154).value);
        leavesBrownID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Leaves Brown", 155).value);
        leavesOrangeID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Leaves Orange", 156).value);
        leavesRedID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Leaves Red", 157).value);
        leavesYellowID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Leaves Yellow", 158).value);
        shortGrassID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Short Grass", 159).value);
        redRockID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Red Rock", 160).value);
        catTailID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Cattail", 161).value);
        hydrangeaID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Hydrangea", 162).value);
        brownGrassID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Brown Grass", 163).value);
        brownGrassShortID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Brown Grass Short", 164).value);
        orangeFlowerID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Orange Flower", 165).value);
        whiteFlowerID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("White Flower", 166).value);
        purpleFlowerID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Purple Flower", 167).value);
        autumnShrubID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Autumn Shrub", 168).value);
        toadstoolID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Toadstool", 169).value);
        tinyCactusID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Tiny Cactus", 170).value);
        wheatGrassID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Wheatgrass", 171).value);
        thickGrassID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Thick Grass", 172).value);
        quicksandID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Quicksand", 173).value);
        autumnSaplingID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Autumn Sapling", 174).value);
        firSaplingID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Fir Sapling", 175).value);
        rootID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Root", 176).value);
        leafPileID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Leaf Pile", 177).value);
        redwoodSaplingID = Integer.parseInt(configuration.getOrCreateBlockIdProperty("Redwood Sapling", 178).value);
        
        fertilizerID = Integer.parseInt(configuration.getOrCreateIntProperty("Fertilizer", Configuration.CATEGORY_ITEM, 2870).value);
        catTailItemID = Integer.parseInt(configuration.getOrCreateIntProperty("Cattail", Configuration.CATEGORY_ITEM, 2871).value);
        
        configuration.save();
        return coverGrassID;
    }
	
    public String getVersion()
	{
		return "ExtraBiomesXL V1.13 for Minecraft 1.2.4";
	}
}