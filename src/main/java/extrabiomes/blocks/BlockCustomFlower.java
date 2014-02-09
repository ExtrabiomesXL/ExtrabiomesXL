/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.proxy.CommonProxy;

public class BlockCustomFlower extends Block implements IPlantable
{
	public static int	NUM_GROUPS	= 2;	// number of flower groups

    public enum BlockType
    {
    	// group 0 - original flowers
		AUTUMN_SHRUB(0, 0, "autumnshrub", 0, -1, new String[] {}),
		HYDRANGEA(0, 1, "hydrangea", 2, 12, new String[] {"One of these flowers can be","crafted into \u00A7b\u00A7oLight Blue Dye\u00A7r\u00A77,", "using a crafting table."}),
		BUTTERCUP(0, 2, "buttercup", 5, 11, new String[] {"This flower which can be", "crafted into \u00A76\u00A7oYellow Dye\u00A7r\u00A77,", "it is said to glow yellow", "when held under your chin if", "you like butter."}), // was "ORANGE", now produces yellow dye
		LAVENDER(0, 3, "lavender", 5, 5, new String[] {"Besides being valued for it’s", "vibrant colors, but also it’s", "fragrant scent. This flower", "can be crafted into \u00A75\u00A7oPurple Dye\u00A7r\u00A77."}), // was "PURPLE"
		TINY_CACTUS(0, 4, "tinycactus", 5, -1, new String[] {"Four of these non-prickly", "cacti can be crafted into", "\u00A7oCactus Paste\u00A7r\u00A77, which can be", "smelted into \u00A72\u00A7oCactus Green Dye\u00A7r\u00A77."}),
		ROOT(0, 5, "root", 0, -1, new String[] {}),
		TOADSTOOL(0, 6, "toadstools", 0, -1, new String[] {}),
		CALLA_WHITE(0, 7, "calla_white", 5, 7, new String[] {"This flower which produces", "\u00A7oLight Gray Dye\u00A7r\u00A77, was also a", "favorite of many painters", "to use in their works of art."}), // was "WHITE"
        // group 1 - added in 3.15
		ALLIUM(1, 0, "allium", 3, 13, new String[] {"This flower produces \u00A7d\u00A7oMagenta", "\u00A7d\u00A7oDye\u00A7r\u00A77. Some claim that it can", "also be used to keep vampires", "away as it is the flowering", "part of garlic, though it has", "never been proven."}),
		AMARYLLIS_PINK(1, 1, "amaryllis_pink", 3, 9, new String[] {}),
		AMARYLLIS_RED(1, 2, "amaryllis_red", 3, 1, new String[] {}),
		AMARYLLIS_WHITE(1, 3, "amaryllis_white", 3, 15, new String[] {}),
		BACHELORS_BUTTON(1, 4, "bachelorsbutton_blue", 3, 4, new String[] {}),
		BELLS_OF_IRELAND(1, 5, "bellsofireland", 3, 10, new String[] {}),
		BLUEBELL(1, 6, "bluebell", 3, 12, new String[] {"This mythical flower, was", "said to have sprang up from", "the blood of the dying prince", "Hyacinthus. \u00A7b\u00A7oLight Blue Dye", "can be made from this flower."}),
		CALLA_BLACK(1, 7, "calla_black", 3, 0, new String[] {}),
		DAISY(1, 8, "daisy", 3, 15, new String[] {}),
		DANDELION(1, 9, "dandelion", 3, -1, new String[] {}),
		EELGRASS(1, 10, "eelgrass", 3, -1, new String[] {}),
		GARDENIA(1, 11, "gardenia", 3, 7, new String[] {}),
		GERBERA_ORANGE(1, 12, "gerbera_orange", 3, 14, new String[] {}),
		GERBERA_PINK(1, 13, "gerbera_pink", 3, 9, new String[] {}),
		GERBERA_RED(1, 14, "gerbera_red", 3, 1, new String[] {}),
		GERBERA_YELLOW(1, 15, "gerbera_yellow", 3, 11, new String[] {}),
        // group 2 - added in 3.15
		ORIENTAL_PINK_LILY(2, 0, "orientalpinklily", 3, 9, new String[] {}),
		IRIS_BLUE(2, 1, "iris_blue", 3, 4, new String[] {}),
		IRIS_PURPLE(2, 2, "iris_purple", 3, 5, new String[] {"The Iris takes its name from", "the Greek word for rainbow,", "referring to the wide variety", "of flower colors found among", "the many species. This one", "will produce \u00A75\u00A7oPurple Dye\u00A7r\u00A77."}),
		LILY(2, 3, "lily", 3, 13, new String[] {}),
		MARSH_MARIGOLD(2, 4, "marshmarigold", 3, 11, new String[] {}),
		PANSY(2, 5, "pansy", 3, -1, new String[] {} /* special case, yellow + purple */),
		POPPY(2, 6, "poppy", 3, 1, new String[] {}),
		REDROVER(2, 7, "redrover", 3, 1, new String[] {}),
		SNAPDRAGON(2, 8, "snapdragon", 3, -1, new String[] {} /* future special case? */),
		TULIP(2, 9, "tulips", 3, 14, new String[] {}),
		VIOLET(2, 10, "violet", 3, 5, new String[] {}),
		YARROW(2, 11, "yarrow", 3, 11, new String[] {}),
		// group 2 cont - added in 3.15.2
		BELLADONNA(2, 12, "belladonna", 3, 4, new String[] {});
        
		private final int		group;
		private final int		metadata;
		private final int		weight;
		private final String	texture;
		private final int		color;		// what color of dye should this make?
		private final String[] toolTipText;

		BlockType(int group, int metadata, String texture, int weight, int color, String[] toolTip) {
			this.group = group;
            this.metadata = metadata;
			this.texture = texture;
			this.weight = weight;
			this.color = color;
			this.toolTipText = toolTip;
		}
        
		public int color() {
			return color;
		}

		public int group() {
			return group;
		}

		public int metadata() {
            return metadata;
        }

		private Icon	icon;
		public Icon getIcon() {
			return icon;
		}

		public Icon registerIcon(IconRegister iconRegister) {
			icon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + this.texture);
			return icon;
		}
        
        public String[] getToolTipText(){
        	return toolTipText;
        }
		
		
    }

	public final int						group;
	private final Map<Integer, BlockType>	groupMap;
	public BlockCustomFlower(int id, int group, Material material)
    {
        super(id, material);
        
        final float offset = 0.2F;
        setBlockBounds(0.5F - offset, 0.0F, 0.5F - offset, 0.5F + offset, offset * 3.0F, 0.5F + offset);
        
        this.group = group;
		this.groupMap = Maps.newHashMap();

        final CommonProxy proxy = Extrabiomes.proxy;
        for( BlockType type : BlockType.values() ) {
			if (type.group == this.group) {
				//LogHelper.info(this+": "+group+":"+type.metadata+" = "+type);
				groupMap.put(type.metadata, type);
				if (type.weight > 0) {
					proxy.addGrassPlant(this, type.metadata, type.weight);
				}
        	}
        }
		//LogHelper.fine(this.toString() + ": initialized group " + group + ", " + groupMap.size() + " flowers");
    }

	public Collection<BlockType> getGroupTypes() {
		return groupMap.values();
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
		LogHelper.fine(this.toString() + ": registerIcons");
		for (BlockType type : groupMap.values()) {
			final Icon icon = type.registerIcon(iconRegister);
			if (icon == null)
				LogHelper.warning("No icon found for " + type+" (" + type.group + "," + type.metadata + ")");
			else
				LogHelper.fine(this.toString() + ": " + type + " = " + icon);
		}
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z))
                && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
    }
    
    private boolean canThisPlantGrowOnThisBlockID(int id)
    {
		// TODO: separate rules for edge cases (like cactus)
        return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Block.sand.blockID || (BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent() && (byte) id == BiomeSettings.MOUNTAINRIDGE.getBiome().get().topBlock);
    }
    
    private void checkFlowerChange(World world, int x, int y, int z)
    {
        if (!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, 0);
        }
    }
    
    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
		BlockType type = groupMap.get(metadata);
		if( type != null ) {
			return type.getIcon();
		} else {
			return null;
		}
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
    
    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }
    
    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        final int metadata = world.getBlockMetadata(x, y, z);
        if (metadata == BlockType.TINY_CACTUS.metadata())
            return EnumPlantType.Desert;
        return EnumPlantType.Plains;
    }
    
    @Override
    public int getRenderType()
    {
        return 1;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        final int metadata = world.getBlockMetadata(x, y, z);
        
        if (metadata == BlockType.TINY_CACTUS.metadata())
            return super.getSelectedBoundingBoxFromPool(world, x, y, z);
        
        return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + maxY, z + 1);
        
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List itemList)
    {
        for (final BlockType type : groupMap.values()) {
        	// Don't show the Shrub and the Root in the creative menu
        	if(this.group != 0 || (type.metadata() != 0 & type.metadata() != 5)) {
        		itemList.add(new ItemStack(this, 1, type.metadata()));
        	}
        }
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id)
    {
        checkFlowerChange(world, x, y, z);
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        checkFlowerChange(world, x, y, z);
    }
    

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(int metaData, List listOfLines) {
    	if(groupMap.containsKey(metaData)){
    		for (final String line : groupMap.get(metaData).getToolTipText()) {
    			listOfLines.add(line);
            }
    	}
    }
}
