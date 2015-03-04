/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameData;

public enum Element
{
    // @formatter:off
	ALLIUM,
    AMARYLLIS_PINK,
    AMARYLLIS_RED,
	AMARYLLIS_WHITE,
	AUTUMN_SHRUB,
	BACHELORS_BUTTON,
	BELLS_OF_IRELAND,
	BELLADONNA,
	BLUEBELL,
	BLUE_POPPY,
	BUTTERCUP, // was FLOWER_ORANGE
	CALLA_BLACK,
	CALLA_WHITE, // was FLOWER_WHITE
	CATTAIL,
	WATERPLANT,
	CRACKEDSAND,
	DAISY,
	DANDELION,
	DYE_BLACK,
	DYE_BLUE,
	DYE_BROWN,
	DYE_WHITE,
	EELGRASS,
	GARDENIA,
	GERBERA_ORANGE,
	GERBERA_PINK,
	GERBERA_RED,
	GERBERA_YELLOW,
	GRASS_BROWN,
	GRASS_BROWN_SHORT,
	GRASS_DEAD,
	GRASS_DEAD_TALL,
	GRASS_DEAD_YELLOW,
	HYDRANGEA,
	IRIS_BLUE,
	IRIS_PURPLE,
	LAVENDER, // was FLOWER_PURPLE
	LEAFPILE,
	LEAVES_ACACIA,
	LEAVES_AUTUMN_BROWN,
    LEAVES_AUTUMN_ORANGE,
    LEAVES_AUTUMN_PURPLE,
    LEAVES_AUTUMN_YELLOW,
    LEAVES_BALD_CYPRESS,
    LEAVES_CYPRESS,
    LEAVES_FIR,
    LEAVES_JAPANESE_MAPLE,
    LEAVES_JAPANESE_MAPLE_SHRUB,
    LEAVES_RAINBOW_EUCALYPTUS,
    LEAVES_REDWOOD,
    LEAVES_SAKURA_BLOSSOM,
    LILY,
    LOG_ACACIA,
    LOG_AUTUMN,
    LOG_BALD_CYPRESS,
    LOG_CYPRESS,
    LOG_FIR,
    LOG_HUGE_FIR_NE,
    LOG_HUGE_FIR_NW,
    LOG_HUGE_FIR_SE,
    LOG_HUGE_FIR_SW,
    LOG_HUGE_OAK_NE,
    LOG_HUGE_OAK_NW,
    LOG_HUGE_OAK_SE,
    LOG_HUGE_OAK_SW,
    LOG_HUGE_REDWOOD_NE,
    LOG_HUGE_REDWOOD_NW,
    LOG_HUGE_REDWOOD_SE,
    LOG_HUGE_REDWOOD_SW,
    LOG_JAPANESE_MAPLE,
    LOG_KNEE_BALD_CYPRESS,
    LOG_KNEE_RAINBOW_EUCALYPTUS,
    LOG_QUARTER_BALD_CYPRESS,
    LOG_QUARTER_FIR,
    LOG_QUARTER_OAK,
    LOG_QUARTER_RAINBOW_EUCALYPTUS,
    LOG_QUARTER_REDWOOD,
    LOG_RAINBOW_EUCALYPTUS,
    LOG_REDWOOD,
    LOG_SAKURA_BLOSSOM,
    LOGTURNER,
    MARSH_MARIGOLD,
	ORIENTAL_PINK_LILY,
    PANSY,
    POPPY,
    RED_COBBLE,
    RED_ROCK,
    RED_ROCK_BRICK,
    REDROVER,
    ROOT,
    SAPLING_ACACIA,
    SAPLING_AUTUMN_BROWN,
    SAPLING_AUTUMN_ORANGE,
    SAPLING_AUTUMN_PURPLE,
    SAPLING_AUTUMN_YELLOW,
    SAPLING_BALD_CYPRESS,
    SAPLING_CYPRESS,
    SAPLING_FIR,
    SAPLING_JAPANESE_MAPLE,
    SAPLING_JAPANESE_MAPLE_SHRUB,
    SAPLING_RAINBOW_EUCALYPTUS,
    SAPLING_REDWOOD,
    SAPLING_SAKURA_BLOSSOM,
    SNAPDRAGON,
    TINY_CACTUS,
    TOADSTOOL,
    TULIP,
    VINE_GLORIOSA,
    VINE_SPANISH_MOSS,
    VIOLET,
    YARROW,
    
    SEED_STRAWBERRY,
    PLANT_STRAWBERRY,
    CROP_STRAWBERRY;
    // @formatter:on

    private Optional<ItemStack> stack = Optional.absent();

    public ItemStack get()
    {
        return stack.get();
    }

    public boolean isPresent()
    {
        return stack.isPresent();
    }

    public String getID()
    {
        if (isPresent())
            return GameData.getItemRegistry().getNameForObject(get().getItem());
        return null;
    }

    public int getMetadata()
    {
        if (isPresent())
            return get().getItemDamage();
        return 0;
    }

    public void set(ItemStack stack)
    {
        this.stack = Optional.of(stack);
    }
}
