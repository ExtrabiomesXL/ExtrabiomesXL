package net.minecraft.src;

public class mod_ExtraBiomesXL extends BaseMod
{
	
    @MLProp(name = "Cover Grass ID", info = "Cover Grass' ID", min = 0, max = 4096)
    public static int coverGrassID = 150;
    @MLProp(name = "Cracked Sand ID", info = "Cracked Sand's ID", min = 0, max = 4096)
    public static int crackedSandID = 151;
    @MLProp(name = "Dead Grass ID", info = "Dead Grass' ID", min = 0, max = 4096)
    public static int deadGrassID = 152;
    @MLProp(name = "Dead Grass Yellow ID", info = "Dead Grass Yellow's ID", min = 0, max = 4096)
    public static int deadGrassYID = 153;
    @MLProp(name = "Dead Tall Grass ID", info = "Dead Tall Grass' ID", min = 0, max = 4096)
    public static int deadTallGrassID = 154;
    @MLProp(name = "Leaves Brown ID", info = "Leaves Brown's ID", min = 0, max = 4096)
    public static int leavesBrownID = 155;
    @MLProp(name = "Leaves Orange ID", info = "Leaves Orange's ID", min = 0, max = 4096)
    public static int leavesOrangeID = 156;
    @MLProp(name = "Leaves Red ID", info = "Leaves Red's ID", min = 0, max = 4096)
    public static int leavesRedID = 157;
    @MLProp(name = "Leaves Yellow ID", info = "Leaves Yellow's ID", min = 0, max = 4096)
    public static int leavesYellowID = 158;
    @MLProp(name = "Short Grass ID", info = "Short Grass' ID", min = 0, max = 4096)
    public static int shortGrassID = 159;
    @MLProp(name = "Red Rock ID", info = "Red Rock's ID", min = 0, max = 4096)
    public static int redRockID = 160;
    @MLProp(name = "Cattail ID", info = "Cattail's ID", min = 0, max = 4096)
    public static int catTailID = 161;
    @MLProp(name = "Cattail (Item) ID", info = "Cattail (Item)'s ID", min = 0, max = 255)
    public static int catTailItemID = 2870;
    @MLProp(name = "Hydrangea ID", info = "Hydrangea's ID", min = 0, max = 4096)
    public static int hydrangeaID = 162;
    @MLProp(name = "Hydrangea (Item) ID", info = "Hydrangea (Item)'s ID", min = 0, max = 255)
    public static int hydrangeaItemID = 2871;
    @MLProp(name = "Brown Grass ID", info = "Brown Grass' ID", min = 0, max = 4096)
    public static int brownGrassID = 163;
    @MLProp(name = "Brown Grass Short ID", info = "Brown Grass Short's ID", min = 0, max = 4096)
    public static int brownGrassShortID = 164;
    @MLProp(name = "Orange Flower ID", info = "Orange Flower's ID", min = 0, max = 4096)
    public static int orangeFlowerID = 165;
    @MLProp(name = "Orange Flower (Item) ID", info = "Orange Flower (Item)'s ID", min = 0, max = 255)
    public static int orangeFlowerItemID = 2873;
    @MLProp(name = "White Flower ID", info = "White Flower's ID", min = 0, max = 4096)
    public static int whiteFlowerID = 166;
    @MLProp(name = "White Flower (Item) ID", info = "White Flower (Item)'s ID", min = 0, max = 255)
    public static int whiteFlowerItemID = 2874;
    @MLProp(name = "Purple Flower ID", info = "Purple Flower's ID", min = 0, max = 4096)
    public static int purpleFlowerID = 167;
    @MLProp(name = "Purple Flower (Item) ID", info = "Purple Flower (Item)'s ID", min = 0, max = 255)
    public static int purpleFlowerItemID = 2872;
    @MLProp(name = "Autumn Shrub ID", info = "Autumn Shrub's ID", min = 0, max = 4096)
    public static int autumnShrubID = 168;
    @MLProp(name = "Autumn Shrub (Item) ID", info = "Autumn Shrub (Item)'s ID", min = 0, max = 255)
    public static int autumnShrubItemID = 2875;
    @MLProp(name = "Toadstool ID", info = "Toadstool's ID", min = 0, max = 4096)
    public static int toadstoolID = 169;
    @MLProp(name = "Toadstool (Item) ID", info = "Toadstool (Item)'s ID", min = 0, max = 255)
    public static int toadstoolItemID = 2876;
    @MLProp(name = "Tiny Cactus ID", info = "Tiny Cactus's ID", min = 0, max = 4096)
    public static int tinyCactusID = 170;
    @MLProp(name = "Tiny Cactus (Item) ID", info = "Tiny Cactus (Item)'s ID", min = 0, max = 255)
    public static int tinyCactusItemID = 2877;
    @MLProp(name = "Wheat Grass ID", info = "Wheat Grass's ID", min = 0, max = 4096)
    public static int wheatGrassID = 171;
    @MLProp(name = "Thick Grass ID", info = "Thick Grass's ID", min = 0, max = 4096)
    public static int thickGrassID = 172;
    @MLProp(name = "Quicksand ID", info = "Quicksand's ID", min = 0, max = 4096)
    public static int quicksandID = 173;
    @MLProp(name = "Autumn Sapling ID", info = "Autumn Sapling's ID", min = 0, max = 4096)
    public static int autumnSaplingID = 174;
    @MLProp(name = "Autumn Sapling (Item) ID", info = "Autumn Sapling (Item)'s ID", min = 0, max = 255)
    public static int autumnSaplingItemID = 2878;
    @MLProp(name = "Fir Sapling ID", info = "Fir Sapling's ID", min = 0, max = 4096)
    public static int firSaplingID = 175;
    @MLProp(name = "Autumn Sapling (Item) ID", info = "Autumn Sapling (Item)'s ID", min = 0, max = 255)
    public static int firSaplingItemID = 2879;
    @MLProp(name = "Fertilizer ID", info = "Fertilizer's ID", min = 0, max = 255)
    public static int fertilizerID = 2880;
    @MLProp(name = "Root ID", info = "Root's ID", min = 0, max = 4096)
    public static int rootID = 176;
    @MLProp(name = "Root (Item) ID", info = "Root (Item)'s ID", min = 0, max = 255)
    public static int rootItemID = 2881;
    @MLProp(name = "Leaf Pile ID", info = "Leaf Pile's ID", min = 0, max = 4096)
    public static int leafPileID = 177;
    
    public static Block coverGrass = new BlockCoverGrass(coverGrassID, 0).setHardness(0F).setResistance(0F).setBlockName("shortGrass").setStepSound(Block.soundGrassFootstep);
    public static Block crackedSand = new BlockCrackedSand(crackedSandID, 0).setHardness(1.2F).setResistance(0F).setBlockName("crackedSand").setStepSound(Block.soundStoneFootstep);
    public static Block deadGrass = new BlockDeadGrass(deadGrassID, 0).setHardness(0F).setResistance(0F).setBlockName("deadGrass").setStepSound(Block.soundGrassFootstep);
    public static Block deadGrassY = new BlockDeadGrass(deadGrassYID, 0).setHardness(0F).setResistance(0F).setBlockName("deadGrassY").setStepSound(Block.soundGrassFootstep);
    public static Block deadTallGrass = new BlockDeadGrass(deadTallGrassID, 0).setHardness(0F).setResistance(0F).setBlockName("deadTallGrass").setStepSound(Block.soundGrassFootstep);
    public static Block leavesBrown = (new BlockLeavesBrown(leavesBrownID, 0)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leaves").setRequiresSelfNotify();
    public static Block leavesOrange = (new BlockLeavesOrange(leavesOrangeID, 0)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leavesOrange").setRequiresSelfNotify();
    public static Block leavesRed = (new BlockLeavesRed(leavesRedID, 0)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leavesRed").setRequiresSelfNotify();
    public static Block leavesYellow = (new BlockLeavesYellow(leavesYellowID, 0)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leavesYellow").setRequiresSelfNotify();
    public static Block shortGrass = (BlockShortGrass)(new BlockShortGrass(shortGrassID, 0)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("shortgrass");
    public static Block redRock = new BlockRedRock(redRockID, 0).setHardness(2.0F).setResistance(2.0F).setBlockName("redRock").setStepSound(Block.soundStoneFootstep);
    public static Block catTail = new BlockCatTail(catTailID, 0).setHardness(0F).setResistance(0F).setBlockName("catTail").setStepSound(Block.soundGrassFootstep).disableStats();
    public static Item catTailItem = (new ItemCatTail(catTailItemID, mod_ExtraBiomesXL.catTail)).setItemName("catTailItem");
    public static Block hydrangea = new BlockHydrangea(hydrangeaID, 0).setHardness(0F).setResistance(0F).setBlockName("hydrangea").setStepSound(Block.soundGrassFootstep);
    public static Item hydrangeaItem = (new ItemHydrangea(hydrangeaItemID, mod_ExtraBiomesXL.hydrangea)).setItemName("hydrangeaItem");
    public static Block brownGrass = new BlockBrownGrass(brownGrassID, 0).setHardness(0F).setResistance(0F).setBlockName("brownGrass").setStepSound(Block.soundGrassFootstep);
    public static Block brownGrassShort = new BlockBrownGrass(brownGrassShortID, 0).setHardness(0F).setResistance(0F).setBlockName("brownGrassShort").setStepSound(Block.soundGrassFootstep);
    public static Block orangeFlower = new BlockOrangeFlower(orangeFlowerID, 0).setHardness(0F).setResistance(0F).setBlockName("orangeFlower").setStepSound(Block.soundGrassFootstep);
    public static Item orangeFlowerItem = (new ItemOrangeFlower(orangeFlowerItemID, mod_ExtraBiomesXL.orangeFlower)).setItemName("orangeFlowerItem");
    public static Block whiteFlower = new BlockWhiteFlower(whiteFlowerID, 0).setHardness(0F).setResistance(0F).setBlockName("whiteFlower").setStepSound(Block.soundGrassFootstep);
    public static Item whiteFlowerItem = (new ItemWhiteFlower(whiteFlowerItemID, mod_ExtraBiomesXL.whiteFlower)).setItemName("whiteFlowerItem");
    public static Block purpleFlower = new BlockPurpleFlower(purpleFlowerID, 0).setHardness(0F).setResistance(0F).setBlockName("purpleFlower").setStepSound(Block.soundGrassFootstep);
    public static Item purpleFlowerItem = (new ItemPurpleFlower(purpleFlowerItemID, mod_ExtraBiomesXL.purpleFlower)).setItemName("purpleFlower");
    public static Block autumnShrub = new BlockAutumnShrub(autumnShrubID, 0).setHardness(0F).setResistance(0F).setBlockName("autumnShrub").setStepSound(Block.soundGrassFootstep);
    public static Item autumnShrubItem = (new ItemAutumnShrub(autumnShrubItemID, mod_ExtraBiomesXL.autumnShrub)).setItemName("autumnShrubItem");
    public static Block toadstool = new BlockToadstool(toadstoolID, 0).setHardness(0F).setResistance(0F).setBlockName("toadstool").setStepSound(Block.soundGrassFootstep);
    public static Item toadstoolItem = (new ItemToadstool(toadstoolItemID, mod_ExtraBiomesXL.toadstool)).setItemName("toadstoolItem");
    public static Block tinyCactus = new BlockTinyCactus(tinyCactusID, 0).setHardness(0F).setResistance(0F).setBlockName("tinyCactus").setStepSound(Block.soundClothFootstep);
    public static Item tinyCactusItem = (new ItemTinyCactus(tinyCactusItemID, mod_ExtraBiomesXL.tinyCactus)).setItemName("tinyCactusItem");
    public static Block wheatGrass = new BlockWheatGrass(wheatGrassID, 0).setHardness(0F).setResistance(0F).setBlockName("wheatGrass").setStepSound(Block.soundGrassFootstep);
    public static Block thickGrass = new BlockThickGrass(thickGrassID, 0).setHardness(0F).setResistance(0F).setBlockName("thickGrass").setStepSound(Block.soundGrassFootstep);
    public static Block quicksand = new BlockQuicksand(quicksandID, 0).setHardness(4F).setResistance(0F).setBlockName("quicksand").setStepSound(Block.soundSandFootstep);
    public static Block autumnSapling = new BlockAutumnSapling(autumnSaplingID, 0).setHardness(0F).setResistance(0F).setBlockName("autumnSapling").setStepSound(Block.soundGrassFootstep);
	public static Item autumnSaplingItem = (new ItemAutumnSapling(autumnSaplingItemID, mod_ExtraBiomesXL.autumnSapling)).setItemName("autumnSaplingItem");
	public static Block firSapling = new BlockFirSapling(firSaplingID, 0).setHardness(0F).setResistance(0F).setBlockName("firSapling").setStepSound(Block.soundGrassFootstep);
	public static Item firSaplingItem = (new ItemFirSapling(firSaplingItemID, mod_ExtraBiomesXL.firSapling)).setItemName("firSaplingItem");
	public static Item fertilizer = (new ItemFertilizer(fertilizerID)).setItemName("fertilizer");
	public static Block root = new BlockRoot(rootID, 0).setHardness(0F).setResistance(0F).setBlockName("root").setStepSound(Block.soundGrassFootstep);
	public static Item rootItem = (new ItemRoot(rootItemID, mod_ExtraBiomesXL.root)).setItemName("rootItem");
    public static Block leafPile = new BlockLeafPile(leafPileID, 0).setHardness(0F).setResistance(0F).setBlockName("leafPile").setStepSound(Block.soundGrassFootstep);

    public void load()
    {
    	
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
        
        coverGrass.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/cover.png");
        crackedSand.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/crackedsand.png");
        deadGrass.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/deadgrass.png");
        deadGrassY.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/deadgrass2.png");
        deadTallGrass.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/deadtallgrass.png");
        leavesBrown.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/leavesbrown.png");
        leavesOrange.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/leavesorange.png");
        leavesRed.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/leavesred.png");
        leavesYellow.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/leavesyellow.png");
        shortGrass.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/shortgrass.png");
        redRock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/redrock.png");
        catTail.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/cattail.png");
        catTailItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/cattailitem.png");
        hydrangea.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/hydrangea.png");
        hydrangeaItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/hydrangea.png");
        brownGrass.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/browngrass.png");
        brownGrassShort.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/browngrassshort.png");
        orangeFlower.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/orangeflower.png");
        orangeFlowerItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/orangeflower.png");
        whiteFlower.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/whiteflowers.png");
        whiteFlowerItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/whiteflowers.png");
        purpleFlower.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/purpleflower.png");
        purpleFlowerItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/purpleflower.png");
        autumnShrub.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/autumnshrub.png");
        autumnShrubItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/autumnshrub.png");
        toadstool.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/toadstool.png");
        toadstoolItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/toadstool.png");
        tinyCactus.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/tinycactus.png");
        tinyCactusItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/tinycactus.png");
        wheatGrass.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/wheatgrass.png");
        thickGrass.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/thickgrass.png");
        quicksand.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/quicksand.png");
        autumnSapling.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/autumnsapling.png");
		autumnSaplingItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/autumnsapling.png");
		fertilizer.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/fertilizer.png");
		firSapling.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/firsapling.png");
		firSaplingItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/firsapling.png");
		root.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/root.png");
		rootItem.iconIndex = ModLoader.addOverride("/gui/items.png", "/extrabiomes/root.png");
        leafPile.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/extrabiomes/leafpile.png");
		
        ModLoader.addName(coverGrass, "Cover Grass");
        ModLoader.addName(crackedSand, "Cracked Sand");
        ModLoader.addName(deadGrass, "Dead Grass");
        ModLoader.addName(deadGrassY, "Dead Grass");
        ModLoader.addName(deadTallGrass, "Dead Grass");
        ModLoader.addName(leavesBrown, "Leaves Brown");
        ModLoader.addName(leavesOrange, "Leaves Orange");
        ModLoader.addName(leavesRed, "Leaves Red");
        ModLoader.addName(leavesYellow, "Leaves Yellow");
        ModLoader.addName(shortGrass, "Short Grass");
        ModLoader.addName(redRock, "Red Rock");
        ModLoader.addName(catTail, "Cattail");
        ModLoader.addName(catTailItem, "Cattail");
        ModLoader.addName(hydrangea, "Hydrangea");
        ModLoader.addName(hydrangeaItem, "Hydrangea");
        ModLoader.addName(brownGrass, "Brown Grass");
        ModLoader.addName(brownGrassShort, "Brown Grass");
        ModLoader.addName(orangeFlower, "Orange Flowers");
        ModLoader.addName(orangeFlowerItem, "Orange Flowers");
        ModLoader.addName(whiteFlower, "White Flowers");
        ModLoader.addName(whiteFlowerItem, "White Flowers");
        ModLoader.addName(purpleFlower, "Purple Flower");
        ModLoader.addName(purpleFlowerItem, "Purple Flower");
        ModLoader.addName(autumnShrub, "Autumn Shrub");
        ModLoader.addName(autumnShrubItem, "Autumn Shrub");
        ModLoader.addName(toadstool, "Toadstool");
        ModLoader.addName(toadstoolItem, "Toadstool");
        ModLoader.addName(tinyCactus, "Tiny Cactus");
        ModLoader.addName(tinyCactusItem, "Tiny Cactus");
        ModLoader.addName(wheatGrass, "Wheatgrass");
        ModLoader.addName(thickGrass, "Thick Grass");
        ModLoader.addName(quicksand, "Quicksand");
        ModLoader.addName(autumnSapling, "Autumn Sapling");
		ModLoader.addName(autumnSaplingItem, "Autumn Sapling");
		ModLoader.addName(fertilizer, "Fertilizer");
		ModLoader.addName(firSapling, "Fir Sapling");
		ModLoader.addName(firSaplingItem, "Fir Sapling");
		ModLoader.addName(root, "Root");
        ModLoader.addName(rootItem, "Root");
        ModLoader.addName(leafPile, "Leaf Pile");
        
        ModLoader.addShapelessRecipe(new ItemStack(firSaplingItem, 1), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Block.sapling, 1, 1)
        });
        ModLoader.addShapelessRecipe(new ItemStack(fertilizer, 1), new Object[] {new ItemStack(Block.dirt), new ItemStack(Item.egg), new ItemStack(Item.seeds)
        });
    }
    
    public String getVersion()
    {
    	return "1.2.3";
    }
}