package net.minecraft.src;

import java.util.Random;

public class BiomeDecorator
{
    /** The world the BiomeDecorator is currently decorating */
    protected World currentWorld;

    /** The Biome Decorator's random number generator. */
    protected Random randomGenerator;

    /** The X-coordinate of the chunk currently being decorated */
    protected int chunk_X;

    /** The Z-coordinate of the chunk currently being decorated */
    protected int chunk_Z;

    /** The biome to generate trees for */
    protected BiomeGenBase biome;

    /** The clay generator. */
    protected WorldGenerator clayGen = new WorldGenClay(4);

    /** The sand generator. */
    protected WorldGenerator sandGen;
    protected WorldGenerator oasisGen;

    /** The gravel generator. */
    protected WorldGenerator gravelAsSandGen;

    /** The dirt generator. */
    protected WorldGenerator dirtGen;
    protected WorldGenerator gravelGen;
    protected WorldGenerator coalGen;
    protected WorldGenerator ironGen;

    /** Field that holds gold WorldGenMinable */
    protected WorldGenerator goldGen;

    /** Field that holds redstone WorldGenMinable */
    protected WorldGenerator redstoneGen;

    /** Field that holds diamond WorldGenMinable */
    protected WorldGenerator diamondGen;

    /** Field that holds Lapis WorldGenMinable */
    protected WorldGenerator lapisGen;

    /** Field that holds one of the plantYellow WorldGenFlowers */
    protected WorldGenerator plantYellowGen;

    /** Field that holds one of the plantRed WorldGenFlowers */
    protected WorldGenerator plantRedGen;

    /** Field that holds mushroomBrown WorldGenFlowers */
    protected WorldGenerator mushroomBrownGen;

    /** Field that holds mushroomRed WorldGenFlowers */
    protected WorldGenerator mushroomRedGen;

    /** Field that holds big mushroom generator */
    protected WorldGenerator bigMushroomGen;

    /** Field that holds WorldGenReed */
    protected WorldGenerator reedGen;

    /** Field that holds WorldGenCactus */
    protected WorldGenerator cactusGen;
    protected WorldGenerator tinyCactusGen;
    protected WorldGenerator rootGen;

    /** The water lily generation! */
    protected WorldGenerator waterlilyGen;
    protected WorldGenerator catTailGen;

    /** Amount of waterlilys per chunk. */
    protected int waterlilyPerChunk;

    /**
     * The number of trees to attempt to generate per chunk. Up to 10 in forests, none in deserts.
     */
    protected int treesPerChunk;

    /**
     * The number of yellow flower patches to generate per chunk. The game generates much less than this number, since
     * it attempts to generate them at a random altitude.
     */
    protected int flowersPerChunk;

    /** The amount of tall grass to generate per chunk. */
    protected int grassPerChunk;

    /**
     * The number of dead bushes to generate per chunk. Used in deserts and swamps.
     */
    protected int deadBushPerChunk;

    /**
     * The number of extra mushroom patches per chunk. It generates 1/4 this number in brown mushroom patches, and 1/8
     * this number in red mushroom patches. These mushrooms go beyond the default base number of mushrooms.
     */
    protected int mushroomsPerChunk;

    /**
     * The number of reeds to generate per chunk. Reeds won't generate if the randomly selected placement is unsuitable.
     */
    protected int reedsPerChunk;

    /**
     * The number of cactus plants to generate per chunk. Cacti only work on sand.
     */
    protected int cactiPerChunk;

    /**
     * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater.
     */
    protected int sandPerChunk;
    protected int oasisPerChunk;

    /**
     * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater. There
     * appear to be two seperate fields for this.
     */
    protected int sandPerChunk2;
    protected int oasisPerChunk2;

    /**
     * The number of clay patches to generate per chunk. Only generates when part of it is underwater.
     */
    protected int clayPerChunk;

    /** Amount of big mushrooms per chunk */
    protected int bigMushroomsPerChunk;
    protected int shortGrassPerChunk;
    protected int coverPerChunk;
    protected int deadGrassPerChunk;
    protected int deadGrassYPerChunk;
    protected int deadTallGrassPerChunk;
    protected int catTailPerChunk;
    protected int hydrangeaPerChunk;
    protected int brownGrassPerChunk;
    protected int brownGrassShortPerChunk;
    protected int orangeFlowerPerChunk;
    protected int whiteFlowerPerChunk;
    protected int purpleFlowerPerChunk;
    protected int autumnShrubPerChunk;
    protected int toadstoolsPerChunk;
    protected int tinyCactiPerChunk;
    protected int wheatGrassPerChunk;
    protected int thickGrassPerChunk;
    protected int rootPerChunk;
    protected int leafPilePerChunk;

    /** True if decorator should generate surface lava & water */
    public boolean generateLakes;

    public BiomeDecorator(BiomeGenBase par1BiomeGenBase)
    {
        this.sandGen = new WorldGenSand(7, Block.sand.blockID);
        this.oasisGen = new WorldGenOasis(7, Block.grass.blockID);
        this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
        this.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
        this.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
        this.coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
        this.ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
        this.goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
        this.redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
        this.diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
        this.lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
        this.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
        this.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
        this.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
        this.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
        this.bigMushroomGen = new WorldGenBigMushroom();
        this.reedGen = new WorldGenReed();
        this.cactusGen = new WorldGenCactus();
        this.tinyCactusGen = new WorldGenTinyCactus();
        this.waterlilyGen = new WorldGenWaterlily();
        this.catTailGen = new WorldGenCatTail();
        this.waterlilyPerChunk = 0;
        this.treesPerChunk = 0;
        this.flowersPerChunk = 2;
        this.grassPerChunk = 1;
        this.deadBushPerChunk = 0;
        this.mushroomsPerChunk = 0;
        this.reedsPerChunk = 0;
        this.cactiPerChunk = 0;
        this.oasisPerChunk = 0;
        this.sandPerChunk = 1;
        this.sandPerChunk2 = 3;
        this.oasisPerChunk2 = 0;
        this.clayPerChunk = 1;
        this.bigMushroomsPerChunk = 0;
        this.shortGrassPerChunk = 1;
        this.coverPerChunk = 2;
        this.deadGrassPerChunk = 0;
        this.deadGrassYPerChunk = 0;
        this.deadTallGrassPerChunk = 0;
        this.catTailPerChunk = 0;
        this.hydrangeaPerChunk = 0;
        this.brownGrassPerChunk = 0;
        this.brownGrassShortPerChunk = 0;
        this.orangeFlowerPerChunk = 0;
        this.whiteFlowerPerChunk = 0;
        this.purpleFlowerPerChunk = 0;
        this.autumnShrubPerChunk = 0;
        this.toadstoolsPerChunk = 0;
        this.tinyCactiPerChunk = 0;
        this.rootPerChunk = 0;
        this.leafPilePerChunk = 0;
        this.wheatGrassPerChunk = 2;
        this.thickGrassPerChunk = 2;
        this.generateLakes = true;
        this.biome = par1BiomeGenBase;
    }

    /**
     * Decorates the world. Calls code that was formerly (pre-1.8) in ChunkProviderGenerate.populate
     */
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = par1World;
            this.randomGenerator = par2Random;
            this.chunk_X = par3;
            this.chunk_Z = par4;
            this.decorate();
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

    /**
     * The method that does the work of actually decorating chunks
     */
    protected void decorate()
    {
        this.generateOres();
        int var1;
        int var2;
        int var3;

        for (var1 = 0; var1 < this.sandPerChunk2; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        for (var1 = 0; var1 < this.clayPerChunk; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.clayGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }

        for (var1 = 0; var1 < this.sandPerChunk; ++var1)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
        }
        
        for (int i = 0; i < oasisPerChunk2; i++)
        {
            int i1 = chunk_X + randomGenerator.nextInt(16) + 8;
            int k5 = chunk_Z + randomGenerator.nextInt(16) + 8;
            oasisGen.generate(currentWorld, randomGenerator, i1, currentWorld.getTopSolidOrLiquidBlock(i1, k5), k5);
        }

        for (int k = 0; k < oasisPerChunk; k++)
        {
            int k1 = chunk_X + randomGenerator.nextInt(16) + 8;
            int i6 = chunk_Z + randomGenerator.nextInt(16) + 8;
            oasisGen.generate(currentWorld, randomGenerator, k1, currentWorld.getTopSolidOrLiquidBlock(k1, i6), i6);
        }

        var1 = this.treesPerChunk;

        if (this.randomGenerator.nextInt(10) == 0)
        {
            ++var1;
        }

        int var4;

        for (var2 = 0; var2 < var1; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            WorldGenerator var5 = this.biome.getRandomWorldGenForTrees(this.randomGenerator);
            var5.setScale(1.0D, 1.0D, 1.0D);
            var5.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
        }

        for (var2 = 0; var2 < this.bigMushroomsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
        }

        int var7;

        for (var2 = 0; var2 < this.flowersPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);

            if (this.randomGenerator.nextInt(4) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(128);
                var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            }
        }

        for (var2 = 0; var2 < this.grassPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            WorldGenerator var6 = this.biome.func_48410_b(this.randomGenerator);
            var6.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
        }
        
        for (int k2 = 0; k2 < shortGrassPerChunk; k2++)
        {
            int j7 = 2;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.shortGrass.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < coverPerChunk; k2++)
        {
            int j7 = 2;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.coverGrass.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < wheatGrassPerChunk; k2++)
        {
            int j7 = 2;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.wheatGrass.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < thickGrassPerChunk; k2++)
        {
            int j7 = 2;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.thickGrass.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (var2 = 0; var2 < this.deadBushPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
        }
        for (int l2 = 0; l2 < deadGrassPerChunk; l2++)
        {
            int k7 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l11 = randomGenerator.nextInt(128);
            int k15 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenDeadGrass(mod_ExtraBiomesXL.deadGrass.blockID)).generate(currentWorld, randomGenerator, k7, l11, k15);
        }

        for (int l2 = 0; l2 < deadGrassYPerChunk; l2++)
        {
            int k7 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l11 = randomGenerator.nextInt(128);
            int k15 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenDeadGrass(mod_ExtraBiomesXL.deadGrassY.blockID)).generate(currentWorld, randomGenerator, k7, l11, k15);
        }

        for (int l2 = 0; l2 < brownGrassPerChunk; l2++)
        {
            int k7 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l11 = randomGenerator.nextInt(128);
            int k15 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenBrownGrass(mod_ExtraBiomesXL.brownGrass.blockID)).generate(currentWorld, randomGenerator, k7, l11, k15);
        }

        for (int l2 = 0; l2 < brownGrassShortPerChunk; l2++)
        {
            int k7 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l11 = randomGenerator.nextInt(128);
            int k15 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenBrownGrass(mod_ExtraBiomesXL.brownGrassShort.blockID)).generate(currentWorld, randomGenerator, k7, l11, k15);
        }

        for (int l2 = 0; l2 < deadTallGrassPerChunk; l2++)
        {
            int k7 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l11 = randomGenerator.nextInt(128);
            int k15 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenDeadGrass(mod_ExtraBiomesXL.deadTallGrass.blockID)).generate(currentWorld, randomGenerator, k7, l11, k15);
        }

        for (int k2 = 0; k2 < hydrangeaPerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.hydrangea.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < orangeFlowerPerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.orangeFlower.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < whiteFlowerPerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.whiteFlower.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < purpleFlowerPerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.purpleFlower.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < autumnShrubPerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.autumnShrub.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int k2 = 0; k2 < toadstoolsPerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(mod_ExtraBiomesXL.toadstool.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }
        
        for (int k2 = 0; k2 < leafPilePerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(128);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenLeafPile(mod_ExtraBiomesXL.leafPile.blockID, j7)).generate(currentWorld, randomGenerator, k11, j15, l17);
        }

        for (int l4 = 0; l4 < tinyCactiPerChunk; l4++)
        {
            int l9 = chunk_X + randomGenerator.nextInt(16) + 8;
            int i14 = randomGenerator.nextInt(128);
            int i17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            tinyCactusGen.generate(currentWorld, randomGenerator, l9, i14, i17);
        }
        
        for (int l4 = 0; l4 < rootPerChunk; l4++)
        {
            int l9 = chunk_X + randomGenerator.nextInt(16) + 8;
            int i14 = randomGenerator.nextInt(128);
            int i17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            rootGen.generate(currentWorld, randomGenerator, l9, i14, i17);
        }

        for (var2 = 0; var2 < this.waterlilyPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

            for (var7 = this.randomGenerator.nextInt(128); var7 > 0 && this.currentWorld.getBlockId(var3, var7 - 1, var4) == 0; --var7)
            {
                ;
            }

            this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
        }

        for (var2 = 0; var2 < this.mushroomsPerChunk; ++var2)
        {
            if (this.randomGenerator.nextInt(4) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var7 = this.currentWorld.getHeightValue(var3, var4);
                this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
            }

            if (this.randomGenerator.nextInt(8) == 0)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                var7 = this.randomGenerator.nextInt(128);
                this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
            }
        }

        if (this.randomGenerator.nextInt(4) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        if (this.randomGenerator.nextInt(8) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        for (var2 = 0; var2 < this.reedsPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            var7 = this.randomGenerator.nextInt(128);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
        }
        
        for (int i4 = 0; i4 < catTailPerChunk; i4++)
        {
            int i9 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j13 = chunk_Z + randomGenerator.nextInt(16) + 8;
            int k16 = randomGenerator.nextInt(128);
            catTailGen.generate(currentWorld, randomGenerator, i9, k16, j13);
        }

        for (var2 = 0; var2 < 10; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
        }

        if (this.randomGenerator.nextInt(32) == 0)
        {
            var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var3 = this.randomGenerator.nextInt(128);
            var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
        }

        for (var2 = 0; var2 < this.cactiPerChunk; ++var2)
        {
            var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            var4 = this.randomGenerator.nextInt(128);
            var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.cactusGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
        }

        if (this.generateLakes)
        {
            for (var2 = 0; var2 < 50; ++var2)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
                var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            }

            for (var2 = 0; var2 < 20; ++var2)
            {
                var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
                var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
                var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
            }
        }
    }

    /**
     * Standard ore generation helper. Generates most ores.
     */
    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
            int var7 = this.randomGenerator.nextInt(par4 - par3) + par3;
            int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
        }
    }

    /**
     * Standard ore generation helper. Generates Lapis Lazuli.
     */
    protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
    {
        for (int var5 = 0; var5 < par1; ++var5)
        {
            int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
            int var7 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + (par3 - par4);
            int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
        }
    }

    /**
     * Generates ores in the current chunk
     */
    protected void generateOres()
    {
        this.genStandardOre1(20, this.dirtGen, 0, 128);
        this.genStandardOre1(10, this.gravelGen, 0, 128);
        this.genStandardOre1(20, this.coalGen, 0, 128);
        this.genStandardOre1(20, this.ironGen, 0, 64);
        this.genStandardOre1(2, this.goldGen, 0, 32);
        this.genStandardOre1(8, this.redstoneGen, 0, 16);
        this.genStandardOre1(1, this.diamondGen, 0, 16);
        this.genStandardOre2(1, this.lapisGen, 16, 16);
    }
}
