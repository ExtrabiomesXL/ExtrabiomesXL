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
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.lib.Element;

public class BlockAutumnLeaves extends BlockLeavesBase implements IShearable {
    public enum BlockType {
        BROWN(0), ORANGE(1), PURPLE(2), YELLOW(3);

        private final int      metadata;
        private ItemStack      sapling            = new ItemStack(Block.sapling);
        private static boolean loadedCustomBlocks = false;

        
        
        static BlockType fromMetadata(int metadata) {
            metadata = unmarkedMetadata(metadata);
            for (final BlockType type : BlockType.values())
                if (type.metadata() == metadata) return type;
            return null;
        }
        

        private static void loadCustomBlocks() {
            if (Element.SAPLING_AUTUMN_BROWN.isPresent())
                BROWN.sapling = Element.SAPLING_AUTUMN_BROWN.get();
            if (Element.SAPLING_AUTUMN_ORANGE.isPresent())
                ORANGE.sapling = Element.SAPLING_AUTUMN_ORANGE.get();
            if (Element.SAPLING_AUTUMN_PURPLE.isPresent())
                PURPLE.sapling = Element.SAPLING_AUTUMN_PURPLE.get();
            if (Element.SAPLING_AUTUMN_YELLOW.isPresent())
                YELLOW.sapling = Element.SAPLING_AUTUMN_YELLOW.get();

            loadedCustomBlocks = true;
        }

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        int getSaplingID() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return sapling.itemID;
        }

        int getSaplingMetadata() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return sapling.getItemDamage();
        }

        public int metadata() {
            return metadata;
        }
    }

    private Icon[] textures = {null, null, null, null, null, null, null, null};
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesbrownfancy");
    	textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesbrownfast");
    	textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesorangefancy");
    	textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesorangefast");
    	textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesredfancy");
    	textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesredfast");
    	textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesyellowfancy");
    	textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "leavesyellowfast");
    }

    private static final int METADATA_BITMASK       = 0x3;
    private static final int METADATA_USERPLACEDBIT = 0x4;
    private static final int METADATA_DECAYBIT      = 0x8;
    private static final int METADATA_CLEARDECAYBIT = -METADATA_DECAYBIT - 1;

    private static int clearDecayOnMetadata(int metadata) {
        return metadata & METADATA_CLEARDECAYBIT;
    }

    private static boolean isDecaying(int metadata) {
        return (metadata & METADATA_DECAYBIT) != 0;
    }

    private static boolean isUserPlaced(int metadata) {
        return (metadata & METADATA_USERPLACEDBIT) != 0;
    }

    private static int setDecayOnMetadata(int metadata) {
        return metadata | METADATA_DECAYBIT;
    }

    private static int unmarkedMetadata(int metadata) {
        return metadata & METADATA_BITMASK;
    }

    int[] adjacentTreeBlocks;

    public BlockAutumnLeaves(int id, int index, Material material, boolean useFastGraphics) {
        super(id, material, useFastGraphics);
    }

    @Override
    public void beginLeavesDecay(World world, int x, int y, int z) {
        world.setBlockMetadataWithNotify(x, y, z, setDecayOnMetadata(world.getBlockMetadata(x, y, z)), 3);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int blockID, int metadata) {
        final int leafDecayRadius = 1;
        final int chuckCheckRadius = leafDecayRadius + 1;

        if (!world.checkChunksExist(x - chuckCheckRadius, y - chuckCheckRadius, z
                - chuckCheckRadius, x + chuckCheckRadius, y + chuckCheckRadius, z
                + chuckCheckRadius)) return;

        for (int x1 = -leafDecayRadius; x1 <= leafDecayRadius; ++x1)
            for (int y1 = -leafDecayRadius; y1 <= leafDecayRadius; ++y1)
                for (int z1 = -leafDecayRadius; z1 <= leafDecayRadius; ++z1) {
                    final int id = world.getBlockId(x + x1, y + y1, z + z1);

                    if (Block.blocksList[id] != null)
                        Block.blocksList[id].beginLeavesDecay(world, x + x1, y + y1, z + z1);
                }
    }

    @Override
    public int damageDropped(int metadata) {
        final Optional<BlockType> type = Optional.fromNullable(BlockType.fromMetadata(metadata));
        return type.isPresent() ? type.get().getSaplingMetadata() : 0;
    }

    private void doSaplingDrop(World world, int x, int y, int z, int metadata, int par7) {
        final int idDropped = idDropped(metadata, world.rand, par7);
        if (idDropped > 0)
            dropBlockAsItem_do(world, x, y, z, new ItemStack(idDropped(metadata, world.rand, par7),
                    1, damageDropped(metadata)));
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata,
            float chance, int par7)
    {
        if (world.isRemote) return;

        if (world.rand.nextInt(20) == 0) doSaplingDrop(world, x, y, z, metadata, par7);

        if (world.rand.nextInt(200) == 0)
            dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.appleRed, 1, 0));
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        metadata = unmarkedMetadata(metadata);
        if (metadata > 4) metadata = 4;
            return textures[unmarkedMetadata(metadata) * 2 + (!isOpaqueCube() ? 0 : 1)];
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
        if (tab == Extrabiomes.tabsEBXL) for (final BlockType blockType : BlockType.values())
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
    }

    @Override
    public int idDropped(int metadata, Random rand, int par3) {
        final Optional<BlockType> type = Optional.fromNullable(BlockType.fromMetadata(metadata));
        return type.isPresent() ? type.get().getSaplingID() : Block.sapling.blockID;
    }

    @Override
    public boolean isLeaves(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return Block.leaves.isOpaqueCube();
    }

    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
        return true;
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        beginLeavesDecay(world, x, y, z);
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z,
            int fortune)
    {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, unmarkedMetadata(world.getBlockMetadata(x, y, z))));
        return ret;
    }

    @Override
    public int quantityDropped(Random rand) {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }

    private void removeLeaves(World world, int x, final int y, final int z) {
        dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlock(x, y, z, 0);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3,
            int par4, int par5)
    {
        graphicsLevel = !Block.leaves.isOpaqueCube(); // fix leaf render
                                                      // bug
        return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (world.isRemote) return;

        final int metadata = world.getBlockMetadata(x, y, z);

        if (isUserPlaced(metadata) || !isDecaying(metadata)) return;

        final int rangeWood = 4;
        final int rangeCheckChunk = rangeWood + 1;
        final byte var9 = 32;
        final int var10 = var9 * var9;
        final int var11 = var9 / 2;

        if (adjacentTreeBlocks == null) adjacentTreeBlocks = new int[var9 * var9 * var9];

        if (world.checkChunksExist(x - rangeCheckChunk, y - rangeCheckChunk, z - rangeCheckChunk, x
                + rangeCheckChunk, y + rangeCheckChunk, z + rangeCheckChunk))
        {

            for (int var12 = -rangeWood; var12 <= rangeWood; ++var12)
                for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
                    for (int var14 = -rangeWood; var14 <= rangeWood; ++var14) {
                        final int id = world.getBlockId(x + var12, y + var13, z + var14);
                        if (Block.blocksList[id] != null
                                && Block.blocksList[id].isWood(world, x + var12, y + var13, z
                                        + var14))
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9
                                    + var14 + var11] = 0;
                        else if (Block.blocksList[id] != null
                                && Block.blocksList[id].isLeaves(world, x + var12, y + var13, z
                                        + var14))
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9
                                    + var14 + var11] = -2;
                        else
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9
                                    + var14 + var11] = -1;
                    }

            for (int var12 = 1; var12 <= 4; ++var12)
                for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
                    for (int var14 = -rangeWood; var14 <= rangeWood; ++var14)
                        for (int var15 = -rangeWood; var15 <= rangeWood; ++var15)
                            if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9
                                    + var15 + var11] == var12 - 1)
                            {
                                if (adjacentTreeBlocks[(var13 + var11 - 1) * var10
                                        + (var14 + var11) * var9 + var15 + var11] == -2)
                                    adjacentTreeBlocks[(var13 + var11 - 1) * var10
                                            + (var14 + var11) * var9 + var15 + var11] = var12;

                                if (adjacentTreeBlocks[(var13 + var11 + 1) * var10
                                        + (var14 + var11) * var9 + var15 + var11] == -2)
                                    adjacentTreeBlocks[(var13 + var11 + 1) * var10
                                            + (var14 + var11) * var9 + var15 + var11] = var12;

                                if (adjacentTreeBlocks[(var13 + var11) * var10
                                        + (var14 + var11 - 1) * var9 + var15 + var11] == -2)
                                    adjacentTreeBlocks[(var13 + var11) * var10
                                            + (var14 + var11 - 1) * var9 + var15 + var11] = var12;

                                if (adjacentTreeBlocks[(var13 + var11) * var10
                                        + (var14 + var11 + 1) * var9 + var15 + var11] == -2)
                                    adjacentTreeBlocks[(var13 + var11) * var10
                                            + (var14 + var11 + 1) * var9 + var15 + var11] = var12;

                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11)
                                        * var9 + var15 + var11 - 1] == -2)
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11)
                                            * var9 + var15 + var11 - 1] = var12;

                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11)
                                        * var9 + var15 + var11 + 1] == -2)
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11)
                                            * var9 + var15 + var11 + 1] = var12;
                            }
        }

        if (adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11] >= 0)
            world.setBlockMetadataWithNotify(x, y, z, clearDecayOnMetadata(metadata), 3);
        else
            removeLeaves(world, x, y, z);
    }
}
