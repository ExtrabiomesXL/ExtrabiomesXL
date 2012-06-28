package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenMiniJungle extends BiomeGenBase
{
    public BiomeGenMiniJungle(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 15;
        biomeDecorator.grassPerChunk = 9;
        biomeDecorator.flowersPerChunk = 5;
        biomeDecorator.reedsPerChunk = 70;
        biomeDecorator.clayPerChunk = 3;
        biomeDecorator.mushroomsPerChunk = 2;
        biomeDecorator.waterlilyPerChunk = 12;
        waterColorMultiplier = 0x24b01c;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if (random.nextInt(2) == 0)
        {
            return worldGenSwamp;
        }

        if (random.nextInt(1) == 0)
        {
            return worldGenBigTree;
        }
        else
        {
            return worldGenTrees;
        }
    }
	
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
		
		if (par2Random.nextInt(1) == 0)
        {
            int i = par3 + par2Random.nextInt(16) + 8;
            int j = par4 + par2Random.nextInt(16) + 8;
            WorldGenPit2 worldgenpit2 = new WorldGenPit2();
            worldgenpit2.generate(par1World, par2Random, i, par1World.getHeightValue(i, j) + 1, j);
			worldgenpit2.generate(par1World, par2Random, i, par1World.getHeightValue(i, j) + 1, j);
			worldgenpit2.generate(par1World, par2Random, i, par1World.getHeightValue(i, j) + 1, j);
        }
		
		if (par2Random.nextInt(1) == 0)
        {
            int i = par3 + par2Random.nextInt(16) + 8;
            int j = par4 + par2Random.nextInt(16) + 8;
            WorldGenPit worldgenpit = new WorldGenPit();
            worldgenpit.generate(par1World, par2Random, i, par1World.getHeightValue(i, j) + 1, j);
        }
    }
	
	public WorldGenerator func_48410_b(Random par1Random)
    {
        if (par1Random.nextInt(4) == 0)
        {
            return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
        }
        else
        {
            return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
        }
    }
}