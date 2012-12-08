/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.ic2;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;

import java.util.Collection;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;

public class IC2Plugin {

    private static final String MOD_ID   = "IC2";
    private static final String MOD_NAME = "IndustrialCraft 2";
    private Optional<IC2API>    api      = Optional.absent();

    private void addBiomeBonus(Collection<Optional<? extends BiomeGenBase>> biomes,
            int humidityBonus, int nutrientsBonus)
    {
        for (final Optional<? extends BiomeGenBase> biome : biomes)
            addBiomeBonus(biome, humidityBonus, nutrientsBonus);
    }

    private void addBiomeBonus(Optional<? extends BiomeGenBase> biome, int humidityBonus,
            int nutrientsBonus)
    {
        api.get().addBiomeBonus(biome, humidityBonus, nutrientsBonus);
    }

    private void addBiomeBonuses() {
        addBiomeBonus(BiomeSettings.GREENSWAMP.getBiome(), 2, 2);
        addBiomeBonus(Lists.newArrayList(BiomeSettings.AUTUMNWOODS.getBiome(),
                BiomeSettings.BIRCHFOREST.getBiome(), BiomeSettings.FORESTEDHILLS.getBiome(),
                BiomeSettings.FORESTEDISLAND.getBiome(), BiomeSettings.PINEFOREST.getBiome(),
                BiomeSettings.RAINFOREST.getBiome(), BiomeSettings.REDWOODFOREST.getBiome(),
                BiomeSettings.REDWOODLUSH.getBiome(), BiomeSettings.TEMPORATERAINFOREST.getBiome(),
                BiomeSettings.WOODLANDS.getBiome()), 1, 1);
        addBiomeBonus(
                Lists.newArrayList(BiomeSettings.EXTREMEJUNGLE.getBiome(),
                        BiomeSettings.MINIJUNGLE.getBiome()), 1, 2);
        addBiomeBonus(Lists.newArrayList(BiomeSettings.MOUNTAINDESERT.getBiome(),
                BiomeSettings.MOUNTAINRIDGE.getBiome(), BiomeSettings.WASTELAND.getBiome()), 1, 0);
    }

    private void addTreeTapRecipes() {
        ItemStack target;

        try {
            target = getTreeTap();
        } catch (final NullPointerException e) {
            return;
        }

        api.get().addCraftingRecipe(target,
                new Object[] { " P ", "PPP", "P  ", Character.valueOf('P'), "plankWood" });
    }

    private void addPlantBallRecipes() {
        ItemStack target;

        try {
            target = getPlantBall();
        } catch (final NullPointerException e) {
            return;
        }

        api.get().addCraftingRecipe(target,
                new Object[] { "PPP", "P P", "PPP", Character.valueOf('P'), "treeLeaves" });

        target = target.copy();
        target.stackSize = 2;

        for (final BlockCustomSapling.BlockType type : BlockCustomSapling.BlockType.values())
            api.get().addCraftingRecipe(target,
                    new Object[] { "PPP", "P P", "PPP", Character.valueOf('P'), "treeSapling" });
    }

    private ItemStack getPlantBall() throws NullPointerException {
        final Optional<ItemStack> opt = api.get().getItem("plantBall");
        if (!opt.isPresent()) throw new NullPointerException("IC2 rwturned null for 'plantBall'.");
        return opt.get();
    }

    private ItemStack getTreeTap() throws NullPointerException {
        final Optional<ItemStack> opt = api.get().getItem("treetap");
        if (!opt.isPresent()) throw new NullPointerException("IC2 rwturned null for 'plantBall'.");
        return opt.get();
    }

    @ForgeSubscribe
    public void init(PluginEvent.Init event) {
        if (!api.isPresent()) return;

        addPlantBallRecipes();
        addTreeTapRecipes();
        addBiomeBonuses();
    }

    @ForgeSubscribe
    public void postInit(PluginEvent.Post event) {
        api = Optional.absent();
    }

    @ForgeSubscribe
    public void preInit(PluginEvent.Pre event) {
        if (!Extrabiomes.proxy.isModLoaded(MOD_ID)) return;
        LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT), MOD_NAME);
        try {
            api = Optional.of(new IC2API());
        } catch (final Exception ex) {
            ex.printStackTrace();
            LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR),
                    MOD_NAME);
            api = Optional.absent();
        }
    }

}
