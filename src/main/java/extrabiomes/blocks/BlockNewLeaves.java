/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.lib.Element;
import extrabiomes.lib.GeneralSettings;

public class BlockNewLeaves extends BlockLeavesBase implements IShearable
{
    
    public enum BlockType
    {
        BALD_CYPRESS(0), JAPANESE_MAPLE(1), JAPANESE_MAPLE_SHRUB(2), RAINBOW_EUCALYPTUS(3);
        
        private final int      metadata;
        private ItemStack      sapling            = new ItemStack(Blocks.sapling);
        private static boolean loadedCustomBlocks = false;
        
        static BlockType fromMetadata(int metadata)
        {
            metadata = unmarkedMetadata(metadata);
            for (final BlockType type : BlockType.values())
            {
                if (type.metadata() == metadata)
                    return type;
            }
            
            return null;
        }
        
        private static void loadCustomBlocks()
        {
            if (Element.SAPLING_BALD_CYPRESS.isPresent())
                BALD_CYPRESS.sapling = Element.SAPLING_BALD_CYPRESS.get();
            if (Element.SAPLING_JAPANESE_MAPLE.isPresent())
                JAPANESE_MAPLE.sapling = Element.SAPLING_JAPANESE_MAPLE.get();
            if (Element.SAPLING_JAPANESE_MAPLE_SHRUB.isPresent())
                JAPANESE_MAPLE_SHRUB.sapling = Element.SAPLING_JAPANESE_MAPLE_SHRUB.get();
            if (Element.SAPLING_RAINBOW_EUCALYPTUS.isPresent())
                RAINBOW_EUCALYPTUS.sapling = Element.SAPLING_RAINBOW_EUCALYPTUS.get();
            
            loadedCustomBlocks = true;
        }
        
        BlockType(int metadata)
        {
            this.metadata = metadata;
        }
        
        Item getSaplingItem()
        {
            if (!loadedCustomBlocks)
            {
                loadCustomBlocks();
            }
            
            return sapling.getItem();
        }
        
        Block getSaplingBlock() {
        	return Block.getBlockFromItem(sapling.getItem());
        }
        
        int getSaplingMetadata()
        {
            if (!loadedCustomBlocks)
            {
                loadCustomBlocks();
            }
            
            return sapling.getItemDamage();
        }
        
        public int metadata()
        {
            return metadata;
        }
    }
    
    private static final int METADATA_BITMASK       = 0x3;
    private static final int METADATA_USERPLACEDBIT = 0x4;
    private static final int METADATA_DECAYBIT      = 0x8;
    private static final int METADATA_CLEARDECAYBIT = -METADATA_DECAYBIT - 1;
    
    private static int calcSmoothedBiomeFoliageColor(IBlockAccess iBlockAccess, int x, int z)
    {
        int red = 0;
        int green = 0;
        int blue = 0;
        
        for (int z1 = -1; z1 <= 1; ++z1)
        {
            for (int x1 = -1; x1 <= 1; ++x1)
            {
                final int foliageColor = iBlockAccess.getBiomeGenForCoords(x + x1, z + z1).getBiomeFoliageColor(x + x1, 96, z + z1);
                red += (foliageColor & 16711680) >> 16;
                green += (foliageColor & 65280) >> 8;
                blue += foliageColor & 255;
            }
        }
        
        return (red / 9 & 255) << 16 | (green / 9 & 255) << 8 | blue / 9 & 255;
    }
    
    static private int clearDecayOnMetadata(int metadata)
    {
        return metadata & METADATA_CLEARDECAYBIT;
    }
    
    private static boolean isDecaying(int metadata)
    {
        return (metadata & METADATA_DECAYBIT) != 0;
    }
    
    private static boolean isUserPlaced(int metadata)
    {
        return (metadata & METADATA_USERPLACEDBIT) != 0;
    }
    
    private static int setDecayOnMetadata(int metadata)
    {
        return metadata | METADATA_DECAYBIT;
    }
    
    private static int unmarkedMetadata(int metadata)
    {
        return metadata & METADATA_BITMASK;
    }
    
    int[]          adjacentTreeBlocks;
    
    private IIcon[] textures = { null, null, null, null, null, null, null, null, null, null, null, null };
    
    public BlockNewLeaves(Material material, boolean useFastGraphics)
    {
        super(material, useFastGraphics);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesbaldcypressfancy");
        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesbaldcypressfast");
        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesjapanesemaplefancy");
        textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesjapanesemaplefast");
        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesjapanesemapleshrubfancy");
        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesjapanesemapleshrubfast");
        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesrainboweucalyptusfancy");
        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesrainboweucalyptusfast");
        textures[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "better_leavesbaldcypress");
        textures[9] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "better_leavesjapanesemaple");
        textures[10] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "better_leavesjapanesemapleshrub");
        textures[11] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "better_leavesrainboweucalyptus");
    }
    
    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, setDecayOnMetadata(world.getBlockMetadata(x, y, z)), 3);
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block _block, int metadata)
    {
        final int leafDecayRadius = 1;
        
        final int chuckCheckRadius = leafDecayRadius + 1;
        if (!world.checkChunksExist(x - chuckCheckRadius, y - chuckCheckRadius, z - chuckCheckRadius, x + chuckCheckRadius, y + chuckCheckRadius, z + chuckCheckRadius))
            return;
        
        for (int x1 = -leafDecayRadius; x1 <= leafDecayRadius; ++x1)
        {
            for (int y1 = -leafDecayRadius; y1 <= leafDecayRadius; ++y1)
            {
                for (int z1 = -leafDecayRadius; z1 <= leafDecayRadius; ++z1)
                {
                    final Block block = world.getBlock(x + x1, y + y1, z + z1);
                    
                    if (block != null)
                    {
                        block.beginLeavesDecay(world, x + x1, y + y1, z + z1);
                    }
                }
            }
        }
    }
    
    @Override
    public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        final int metadata = unmarkedMetadata(iBlockAccess.getBlockMetadata(x, y, z));
        
        if (metadata == BlockType.JAPANESE_MAPLE.metadata() || metadata == BlockType.JAPANESE_MAPLE_SHRUB.metadata()) {
            return getRenderColor(metadata);
        } else {
            return calcSmoothedBiomeFoliageColor(iBlockAccess, x, z);
        }
    }
    
    @Override
    public int damageDropped(int metadata)
    {
        final Optional<BlockType> type = Optional.fromNullable(BlockType.fromMetadata(metadata));
        return type.isPresent() ? type.get().getSaplingMetadata() : 0;
    }
    
    private void doSaplingDrop(World world, int x, int y, int z, int metadata, int par7)
    {
        final Item itemDropped = getItemDropped(metadata, world.rand, par7);
        final int damageDropped = damageDropped(metadata);
        dropBlockAsItem(world, x, y, z, new ItemStack(itemDropped, 1, damageDropped));
    }
    
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int par7)
    {
        leafTypeDropper(world, world, x, y, z, metadata, par7);
    }
    
    private void leafTypeDropper(IBlockAccess iBlockAccess, World world, int x, int y, int z, int metadata, int par7)
    {
        final int damageValue = unmarkedMetadata(iBlockAccess.getBlockMetadata(x, y, z));
        if (world.isRemote)
            return;
        
        if (damageValue == BlockType.JAPANESE_MAPLE.metadata || damageValue == BlockType.JAPANESE_MAPLE_SHRUB.metadata || !GeneralSettings.bigTreeSaplingDropModifier)
        {
            if (world.rand.nextInt(20) == 0)
            {
                doSaplingDrop(world, x, y, z, metadata, par7);
            }
        }
        else
        {
            if (world.rand.nextInt(90) == 0)
            {
                doSaplingDrop(world, x, y, z, metadata, par7);
            }
        }
    }
    
    @Override
    public int getBlockColor()
    {
        return ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return textures[unmarkedMetadata(metadata) * 2 + (!isOpaqueCube() ? 0 : 1)];
    }
    
    // Return your Better Leaves IIcon
    public IIcon getIconBetterLeaves(int metadata, float randomIndex)
    {
        return textures[8 + unmarkedMetadata(metadata)];
    }
    
    public IIcon getIconFallingLeaves(int metadata)
    {
        return textures[(unmarkedMetadata(metadata) * 2) + 1];
    }
    
    public float getSpawnChanceFallingLeaves(int metadata)
    {
        return 0.01F;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return unmarkedMetadata(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public int getRenderColor(int metadata)
    {
        metadata = unmarkedMetadata(metadata);
        
        //return ColorizerFoliage.getFoliageColor(1.0F, 0.5F);
        
        switch (metadata)
        {
            case 0:
                return ColorizerFoliage.getFoliageColor(1.0F, 0.5F);
            case 1:
                return 0xffffff;
                //return ColorizerFoliage.getFoliageColor(1.0F, 0.2F);
            case 2:
                return ColorizerFoliage.getFoliageColor(1.0F, 0.5F);
            default:
                return ColorizerFoliage.getFoliageColor(1.0F, 0.0F);
                //
        }
        
        //return metadata == 0 ? ColorizerFoliage.getFoliageColorPine() : metadata == 1 ? ColorizerFoliage.getFoliageColorBasic() : ColorizerFoliage.getFoliageColor(0.9F, 0.1F);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List itemList)
    {
        for (final BlockType blockType : BlockType.values())
        {
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
        }
    }
    
    @Override
    public void harvestBlock(World world, final EntityPlayer player, final int x, final int y, final int z, final int md)
    {
        super.harvestBlock(world, player, x, y, z, md);
    }
    
    @Override
    public Item getItemDropped(int metadata, Random rand, int par3)
    {
        final Optional<BlockType> type = Optional.fromNullable(BlockType.fromMetadata(metadata));
        return type.isPresent() ? type.get().getSaplingItem() : Item.getItemFromBlock(Blocks.sapling);
    }
    
    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return Blocks.leaves.isOpaqueCube();
    }
    
    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        beginLeavesDecay(world, x, y, z);
    }
    
    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, unmarkedMetadata(world.getBlockMetadata(x, y, z))));
        return ret;
    }
    
    @Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }
    
    private void removeLeaves(World world, int x, int y, int z)
    {
        dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlockToAir(x, y, z);
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        this.field_150121_P = !Blocks.leaves.isOpaqueCube(); // fix leaf render
                                                      // bug
        return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4, par5);
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (world.isRemote)
            return;
        
        final int metadata = world.getBlockMetadata(x, y, z);
        
        if (isUserPlaced(metadata) || !isDecaying(metadata))
            return;
        
        final int rangeWood = (unmarkedMetadata(metadata) == BlockType.JAPANESE_MAPLE.metadata) ? 8 : 6;
        final int rangeCheckChunk = rangeWood + 1;
        final byte var9 = 32;
        final int var10 = var9 * var9;
        final int var11 = var9 / 2;
        final int leafRange = (unmarkedMetadata(metadata) == BlockType.JAPANESE_MAPLE.metadata) ? 10 : 4;
        
        if (adjacentTreeBlocks == null)
        {
            adjacentTreeBlocks = new int[var9 * var9 * var9];
        }
        
        if (world.checkChunksExist(x - rangeCheckChunk, y - rangeCheckChunk, z - rangeCheckChunk, x + rangeCheckChunk, y + rangeCheckChunk, z + rangeCheckChunk))
        {
            
            for (int var12 = -rangeWood; var12 <= rangeWood; ++var12)
            {
                for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
                {
                    for (int var14 = -rangeWood; var14 <= rangeWood; ++var14)
                    {
                        final Block block = world.getBlock(x + var12, y + var13, z + var14);
                        
                        if (block != null && block.canSustainLeaves(world, x + var12, y + var13, z + var14))
                        {
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                        }
                        else if (block != null && block.isLeaves(world, x + var12, y + var13, z + var14))
                        {
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                        }
                        else
                        {
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                        }
                    }
                }
            }
            
            for (int var12 = 1; var12 <= leafRange; ++var12)
            {
                for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
                {
                    for (int var14 = -rangeWood; var14 <= rangeWood; ++var14)
                    {
                        for (int var15 = -rangeWood; var15 <= rangeWood; ++var15)
                        {
                            if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1)
                            {
                                if (adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 - 1] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 - 1] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if (adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11] >= 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, clearDecayOnMetadata(metadata), 3);
        }
        else
        {
            removeLeaves(world, x, y, z);
        }
    }
    
}
