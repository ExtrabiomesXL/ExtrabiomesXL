package net.minecraft.src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BiomeGenBase
{
    /** An array of all the biomes, indexed by biome id. */
    public static final BiomeGenBase[] biomeList = new BiomeGenBase[256];
    public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setMinMaxHeight(-1.0F, 0.4F);
    public static final BiomeGenBase plains = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
    public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
    public static final BiomeGenBase extremeHills = (new BiomeGenHills(3)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.2F, 1.3F).setTemperatureRainfall(0.2F, 0.3F);
    public static final BiomeGenBase forest = (new BiomeGenForest(4)).setColor(353825).setBiomeName("Forest").func_4124_a(5159473).setTemperatureRainfall(0.7F, 0.8F);
    public static final BiomeGenBase taiga = (new BiomeGenTaiga(5)).setColor(747097).setBiomeName("Taiga").func_4124_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
    public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").func_4124_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
    public static final BiomeGenBase river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setMinMaxHeight(-0.5F, 0.0F);
    public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);

    /** Is the biome used for sky world. */
    public static final BiomeGenBase sky = (new BiomeGenEnd(9)).setColor(8421631).setBiomeName("Sky").setDisableRain();
    public static final BiomeGenBase frozenOcean = (new BiomeGenOcean(10)).setColor(9474208).setBiomeName("FrozenOcean").setEnableSnow().setMinMaxHeight(-1.0F, 0.5F).setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase frozenRiver = (new BiomeGenRiver(11)).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow().setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase icePlains = (new BiomeGenSnow(12)).setColor(16777215).setBiomeName("Ice Plains").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13)).setColor(10526880).setBiomeName("Ice Mountains").setEnableSnow().setMinMaxHeight(0.2F, 1.2F).setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase mushroomIsland = (new BiomeGenMushroomIsland(14)).setColor(16711935).setBiomeName("MushroomIsland").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.2F, 1.0F);
    public static final BiomeGenBase mushroomIslandShore = (new BiomeGenMushroomIsland(15)).setColor(10486015).setBiomeName("MushroomIslandShore").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-1.0F, 0.1F);

    /** Beach biome. */
    public static final BiomeGenBase beach = (new BiomeGenBeach(16)).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);

    /** Desert Hills biome. */
    public static final BiomeGenBase desertHills = (new BiomeGenDesert(17)).setColor(13786898).setBiomeName("DesertHills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.2F, 0.7F);

    /** Forest Hills biome. */
    public static final BiomeGenBase forestHills = (new BiomeGenForest(18)).setColor(2250012).setBiomeName("ForestHills").func_4124_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.6F);

    /** Taiga Hills biome. */
    public static final BiomeGenBase taigaHills = (new BiomeGenTaiga(19)).setColor(1456435).setBiomeName("TaigaHills").setEnableSnow().func_4124_a(5159473).setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F);

    /** Extreme Hills Edge biome. */
    public static final BiomeGenBase extremeHillsEdge = (new BiomeGenHills(20)).setColor(7501978).setBiomeName("Extreme Hills Edge").setMinMaxHeight(0.2F, 0.8F).setTemperatureRainfall(0.2F, 0.3F);

    /** Jungle biome identifier */
    public static final BiomeGenBase jungle = (new BiomeGenJungle(21)).setColor(5470985).setBiomeName("Jungle").func_4124_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F);
    public static final BiomeGenBase jungleHills = (new BiomeGenJungle(22)).setColor(2900485).setBiomeName("JungleHills").func_4124_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.2F);
    public static final BiomeGenBase jungleExtreme = (new BiomeGenJungle(23)).setColor(0x2c4205).setBiomeName("Extreme Jungle").func_4124_a(0x537b09).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(2.1F, 2.3F);
    public static final BiomeGenBase alps = (new BiomeGenAlps(24)).setColor(0x8DACC4).setBiomeName("Alps").setTemperatureRainfall(0.0F, 0.1F).setMinMaxHeight(1.3F, 2.1F);
    public static final BiomeGenBase autumnWoods = (new BiomeGenAutumnWoods(25)).setColor(0xF29C11).setBiomeName("Autumn Woods").setTemperatureRainfall(2.0F, 0.1F).setMinMaxHeight(0.2F, 0.8F);
    public static final BiomeGenBase birchForest = (new BiomeGenBirchForest(26)).setColor(0x62BF6C).setBiomeName("Birch Forest").setTemperatureRainfall(0.4F, 0.7F).setMinMaxHeight(0.0F, 0.4F);
    public static final BiomeGenBase mountainRidge = (new BiomeGenMountainRidge(27)).setColor(0xC4722F).setBiomeName("Mountain Ridge").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(1.7F, 1.7F);
    public static final BiomeGenBase desertMountain = (new BiomeGenDesert(28)).setColor(0xFA9418).setBiomeName("Mountainous Desert").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.4F, 1.4F).setDisableRain();
    public static final BiomeGenBase forestedHills = (new BiomeGenForestedHills(29)).setColor(0x2BF042).setBiomeName("Forested Hills").setTemperatureRainfall(0.8F, 1.2F).setMinMaxHeight(0.2F, 1.8F);
    public static final BiomeGenBase forestedIsland = (new BiomeGenForestedIsland(30)).setColor(0x62BF6C).setBiomeName("Forested Island").setTemperatureRainfall(0.4F, 0.7F).setMinMaxHeight(-0.8F, 0.8F);
    public static final BiomeGenBase glacier = (new BiomeGenGlacier(31)).setColor(0x77A696).setBiomeName("Glacier").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(1.4F, 2.1F);
    public static final BiomeGenBase greenHills = (new BiomeGenGreenHills(32)).setColor(0x68C474).setBiomeName("Green Hills").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.6F, 1.2F);
    public static final BiomeGenBase greenSwamp = (new BiomeGenGreenSwamp(33)).setColor(0x68C474).setBiomeName("Green Swamplands").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(-0.2F, 0.1F);
    public static final BiomeGenBase marsh = (new BiomeGenMarsh(34)).setColor(255).setBiomeName("Marsh").setTemperatureRainfall(0.8F, 0.9F).setMinMaxHeight(-0.4F, 0.0F);
    public static final BiomeGenBase meadow = (new BiomeGenMeadow(35)).setColor(0x29D640).setBiomeName("Meadow").setTemperatureRainfall(2.3F, 3.0F).setMinMaxHeight(0.0F, 0.0F);
    public static final BiomeGenBase miniJungle = (new BiomeGenMiniJungle(36)).setColor(0x41D923).setBiomeName("Mini Jungle").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.6F);
    public static final BiomeGenBase miniJungleMountain = (new BiomeGenMiniJungle(37)).setColor(0x41D923).setBiomeName("Mini Jungle Mountains").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.7F, 2.2F);
    public static final BiomeGenBase rainForest = (new BiomeGenRainforest(38)).setColor(0x0BD626).setBiomeName("Rainforest").setTemperatureRainfall(1.1F, 1.4F).setMinMaxHeight(0.4F, 1.3F);
    public static final BiomeGenBase savanna = (new BiomeGenSavanna(39)).setColor(0xBFA243).setBiomeName("Savanna").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.1F);
    public static final BiomeGenBase shrubland = (new BiomeGenShrubland(40)).setColor(0x51B57D).setBiomeName("Shrubland").setTemperatureRainfall(0.4F, 0.6F).setMinMaxHeight(0.1F, 0.3F);
    public static final BiomeGenBase snowForest = (new BiomeGenSnowForest(41)).setColor(0x5BA68D).setBiomeName("Snow Forest").setTemperatureRainfall(0.0F, 0.2F).setMinMaxHeight(0.1F, 0.5F);
    public static final BiomeGenBase taigaMountain = (new BiomeGenTaiga(42)).setColor(0xB6659).setBiomeName("Mountain Taiga").setTemperatureRainfall(0.0F, 0.2F).setMinMaxHeight(0.3F, 1.2F);
    public static final BiomeGenBase temperateRainforest = (new BiomeGenTemperateRainforest(43)).setColor(0x338235).setBiomeName("Temperate Rainforest").setTemperatureRainfall(0.6F, 0.9F).setMinMaxHeight(0.4F, 1.5F);
    public static final BiomeGenBase temperateRainforestSnow = (new BiomeGenTemperateRainforest(44)).setColor(0x338277).setBiomeName("Snowy Temperate Rainforest").setTemperatureRainfall(0.0F, 0.1F).setMinMaxHeight(0.4F, 1.5F);
    public static final BiomeGenBase wasteland = (new BiomeGenWasteland(45)).setColor(0x9E7C41).setBiomeName("Wasteland").setMinMaxHeight(0.0F, 0.0F).setTemperatureRainfall(2.0F, 0.0F).setDisableRain();
    public static final BiomeGenBase woodlands = (new BiomeGenWoodlands(46)).setColor(0x85B53E).setBiomeName("Woodlands").setMinMaxHeight(0.2F, 0.4F).setTemperatureRainfall(2.0F, 0.2F);
    public static final BiomeGenBase redwoodForest = (new BiomeGenRedwoodForest(47)).setColor(0x0BD626).setBiomeName("Redwood Forest").setTemperatureRainfall(1.1F, 1.4F).setMinMaxHeight(0.9F, 1.5F);
    public static final BiomeGenBase redwoodLush = (new BiomeGenRedwoodLush(48)).setColor(0x4AC758).setBiomeName("Lush Redwoods").setTemperatureRainfall(1.1F, 1.4F).setMinMaxHeight(0.9F, 1.5F);
    public static final BiomeGenBase iceWasteland = (new BiomeGenIceWasteland(49)).setColor(0x7DA0B5).setBiomeName("Ice Wasteland").setTemperatureRainfall(0.0F, 0.1F).setMinMaxHeight(0.3F, 0.4F);
    public static final BiomeGenBase pineForest = (new BiomeGenTaiga(50)).setColor(0x469C7E).setBiomeName("Pine Forest").setTemperatureRainfall(0.4F, 0.6F).setMinMaxHeight(0.1F, 0.3F);
    public static final BiomeGenBase tundra = (new BiomeGenTundra(51)).setColor(0x305A85).setBiomeName("Tundra").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.0F, 0.2F);
    public String biomeName;
    public int color;

    /** The block expected to be on the top of this biome */
    public byte topBlock;

    /** The block to fill spots in when not on the top */
    public byte fillerBlock;
    public int field_6502_q;

    /** The minimum height of this biome. Default 0.1. */
    public float minHeight;

    /** The maximum height of this biome. Default 0.3. */
    public float maxHeight;

    /** The temperature of this biome. */
    public float temperature;

    /** The rainfall in this biome. */
    public float rainfall;

    /** Color tint applied to water depending on biome */
    public int waterColorMultiplier;
    public BiomeDecorator biomeDecorator;

    /**
     * Holds the classes of IMobs (hostile mobs) that can be spawned in the biome.
     */
    protected List spawnableMonsterList;

    /**
     * Holds the classes of any creature that can be spawned in the biome as friendly creature.
     */
    protected List spawnableCreatureList;

    /**
     * Holds the classes of any aquatic creature that can be spawned in the water of the biome.
     */
    protected List spawnableWaterCreatureList;

    /** Set to true if snow is enabled for this biome. */
    private boolean enableSnow;

    /**
     * Is true (default) if the biome support rain (desert and nether can't have rain)
     */
    private boolean enableRain;

    /** The id number to this biome, and its index in the biomeList array. */
    public final int biomeID;
    protected WorldGenTrees worldGenTrees;
    protected WorldGenBigTree worldGenBigTree;
    protected WorldGenForest worldGenForest;
    protected WorldGenSwamp worldGenSwamp;
    protected WorldGenTreesBrown worldGenTreesBrown;
    protected WorldGenTreesOrange worldGenTreesOrange;
    protected WorldGenTreesRed worldGenTreesRed;
    protected WorldGenTreesYellow worldGenTreesYellow;
    protected WorldGenFirTree worldGenFirTree;
    protected WorldGenSwamp2 worldGenSwamp2;
    protected WorldGenAlps worldGenAlps;
    protected WorldGenSavanna worldGenSavanna;
    protected WorldGenRedwood worldGenRedwood;
    protected WorldGenFirTree2 worldGenFirTree2;

    protected BiomeGenBase(int par1)
    {
        this.topBlock = (byte)Block.grass.blockID;
        this.fillerBlock = (byte)Block.dirt.blockID;
        this.field_6502_q = 5169201;
        this.minHeight = 0.1F;
        this.maxHeight = 0.3F;
        this.temperature = 0.5F;
        this.rainfall = 0.5F;
        this.waterColorMultiplier = 16777215;
        this.spawnableMonsterList = new ArrayList();
        this.spawnableCreatureList = new ArrayList();
        this.spawnableWaterCreatureList = new ArrayList();
        this.enableRain = true;
        this.worldGenTrees = new WorldGenTrees(false);
        this.worldGenBigTree = new WorldGenBigTree(false);
        this.worldGenForest = new WorldGenForest(false);
        this.worldGenSwamp = new WorldGenSwamp();
        this.worldGenTreesBrown = new WorldGenTreesBrown(false);
        this.worldGenTreesOrange = new WorldGenTreesOrange(false);
        this.worldGenTreesRed = new WorldGenTreesRed(false);
        this.worldGenTreesYellow = new WorldGenTreesYellow(false);
        this.worldGenFirTree = new WorldGenFirTree(false);
        this.worldGenSwamp2 = new WorldGenSwamp2();
        this.worldGenAlps = new WorldGenAlps(false);
        this.worldGenSavanna = new WorldGenSavanna(false);
        this.worldGenRedwood = new WorldGenRedwood(false);
        this.worldGenFirTree2 = new WorldGenFirTree2(false);
        this.biomeID = par1;
        biomeList[par1] = this;
        this.biomeDecorator = this.createBiomeDecorator();
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4));
        this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
    }

    /**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     */
    protected BiomeDecorator createBiomeDecorator()
    {
        return new BiomeDecorator(this);
    }

    /**
     * Sets the temperature and rainfall of this biome.
     */
    private BiomeGenBase setTemperatureRainfall(float par1, float par2)
    {
        if (par1 > 0.1F && par1 < 0.2F)
        {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else
        {
            this.temperature = par1;
            this.rainfall = par2;
            return this;
        }
    }

    /**
     * Sets the minimum and maximum height of this biome. Seems to go from -2.0 to 2.0.
     */
    private BiomeGenBase setMinMaxHeight(float par1, float par2)
    {
        this.minHeight = par1;
        this.maxHeight = par2;
        return this;
    }

    /**
     * Disable the rain for the biome.
     */
    private BiomeGenBase setDisableRain()
    {
        this.enableRain = false;
        return this;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGenBigTree : this.worldGenTrees);
    }

    public WorldGenerator func_48410_b(Random par1Random)
    {
        return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    /**
     * sets enableSnow to true during biome initialization. returns BiomeGenBase.
     */
    protected BiomeGenBase setEnableSnow()
    {
        this.enableSnow = true;
        return this;
    }

    protected BiomeGenBase setBiomeName(String par1Str)
    {
        this.biomeName = par1Str;
        return this;
    }

    protected BiomeGenBase func_4124_a(int par1)
    {
        this.field_6502_q = par1;
        return this;
    }

    protected BiomeGenBase setColor(int par1)
    {
        this.color = par1;
        return this;
    }

    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
        par1 /= 3.0F;

        if (par1 < -1.0F)
        {
            par1 = -1.0F;
        }

        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
    }

    /**
     * Returns the correspondent list of the EnumCreatureType informed.
     */
    public List getSpawnableList(EnumCreatureType par1EnumCreatureType)
    {
        return par1EnumCreatureType == EnumCreatureType.monster ? this.spawnableMonsterList : (par1EnumCreatureType == EnumCreatureType.creature ? this.spawnableCreatureList : (par1EnumCreatureType == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : null));
    }

    /**
     * Returns true if the biome have snowfall instead a normal rain.
     */
    public boolean getEnableSnow()
    {
        return this.enableSnow;
    }

    /**
     * Return true if the biome supports lightning bolt spawn, either by have the bolts enabled and have rain enabled.
     */
    public boolean canSpawnLightningBolt()
    {
        return this.enableSnow ? false : this.enableRain;
    }

    /**
     * Checks to see if the rainfall level of the biome is extremely high
     */
    public boolean isHighHumidity()
    {
        return this.rainfall > 0.85F;
    }

    /**
     * returns the chance a creature has to spawn.
     */
    public float getSpawningChance()
    {
        return 0.1F;
    }

    /**
     * Gets an integer representation of this biome's rainfall
     */
    public final int getIntRainfall()
    {
        return (int)(this.rainfall * 65536.0F);
    }

    /**
     * Gets an integer representation of this biome's temperature
     */
    public final int getIntTemperature()
    {
        return (int)(this.temperature * 65536.0F);
    }

    /**
     * Gets a floating point representation of this biome's rainfall
     */
    public final float getFloatRainfall()
    {
        return this.rainfall;
    }

    /**
     * Gets a floating point representation of this biome's temperature
     */
    public final float getFloatTemperature()
    {
        return this.temperature;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        this.biomeDecorator.decorate(par1World, par2Random, par3, par4);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        double var1 = (double)MathHelper.clamp_float(this.getFloatTemperature(), 0.0F, 1.0F);
        double var3 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
        return ColorizerGrass.getGrassColor(var1, var3);
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        double var1 = (double)MathHelper.clamp_float(this.getFloatTemperature(), 0.0F, 1.0F);
        double var3 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
        return ColorizerFoliage.getFoliageColor(var1, var3);
    }
}
