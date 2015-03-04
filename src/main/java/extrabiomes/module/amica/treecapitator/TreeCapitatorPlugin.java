/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.treecapitator;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;
import extrabiomes.lib.Reference;

public class TreecapitatorPlugin
{
    public static void init()
    {
        if (Loader.isModLoaded("Treecapitator"))
        {
            LogHelper.info("Initializing Treecapitator support...");
            NBTTagCompound tpModCfg = new NBTTagCompound();
            tpModCfg.setString("modID", Reference.MOD_ID);

            NBTTagList treeList = new NBTTagList();

            // Vanilla Oak additions
            NBTTagCompound tree = new NBTTagCompound();
            tree.setString("treeName", "vanilla_oak");
            tree.setString("logs", String.format("%s", Element.LOG_QUARTER_OAK.getID()));
            tree.setString("leaves", "");
            treeList.appendTag(tree);

            // EBXL fir
            tree = new NBTTagCompound();
            tree.setString("treeName", "fir");
            tree.setString("logs", String.format("%s,%d %% 4; %s", Element.LOG_FIR.getID(), Element.LOG_FIR.getMetadata(), Element.LOG_QUARTER_FIR.getID()));
            tree.setString("leaves", String.format("%s,%d %% 8", Element.LEAVES_FIR.getID(), Element.LEAVES_FIR.getMetadata()));
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);

            // EBXL redwood
            tree = new NBTTagCompound();
            tree.setString("treeName", "redwood");
            tree.setString("logs", String.format("%s,%d %% 4; %s", Element.LOG_REDWOOD.getID(), Element.LOG_REDWOOD.getMetadata(), Element.LOG_QUARTER_REDWOOD.getID()));
            tree.setString("leaves", String.format("%s,%d %% 8", Element.LEAVES_REDWOOD.getID(), Element.LEAVES_REDWOOD.getMetadata()));
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);

            // EBXL acacia
            tree = new NBTTagCompound();
            tree.setString("treeName", "acacia");
            tree.setString("logs", String.format("%s,%d %% 4", Element.LOG_ACACIA.getID(), Element.LOG_ACACIA.getMetadata()));
            tree.setString("leaves", String.format("%s,%d %% 8", Element.LEAVES_ACACIA.getID(), Element.LEAVES_ACACIA.getMetadata()));
            treeList.appendTag(tree);

            // EBXL cypress
            tree = new NBTTagCompound();
            tree.setString("treeName", "cypress");
            tree.setString("logs", String.format("%s,%d %% 4", Element.LOG_CYPRESS.getID(), Element.LOG_CYPRESS.getMetadata()));
            tree.setString("leaves", String.format("%s,%d %% 8", Element.LEAVES_CYPRESS.getID(), Element.LEAVES_CYPRESS.getMetadata()));
            treeList.appendTag(tree);

            // EBXL Japanese maple
            tree = new NBTTagCompound();
            tree.setString("treeName", "japanesemaple");
            tree.setString("logs", String.format("%s,%d %% 4", Element.LOG_JAPANESE_MAPLE.getID(), Element.LOG_JAPANESE_MAPLE.getMetadata()));
            tree.setString("leaves", String.format("%s,%d %% 8; %s,%d %% 8", Element.LEAVES_JAPANESE_MAPLE.getID(), Element.LEAVES_JAPANESE_MAPLE.getMetadata(),
                    Element.LEAVES_JAPANESE_MAPLE_SHRUB.getID(), Element.LEAVES_JAPANESE_MAPLE_SHRUB.getMetadata()));
            tree.setInteger("maxHorLeafBreakDist", 6);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);

            // EBXL Sakura Blossom 
            tree = new NBTTagCompound();
            tree.setString("treeName", "sakurablossom");
            tree.setString("logs", String.format("%s,%d %% 4", Element.LOG_SAKURA_BLOSSOM.getID(), Element.LOG_SAKURA_BLOSSOM.getMetadata()));
            tree.setString("leaves", String.format("%s,%d %% 8", Element.LEAVES_SAKURA_BLOSSOM.getID(), Element.LEAVES_SAKURA_BLOSSOM.getMetadata()));
            tree.setInteger("maxHorLeafBreakDist", 8);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);

            // EBXL autumn trees
            tree = new NBTTagCompound();
            tree.setString("treeName", "autumntree");
            tree.setString("logs", String.format("%s,%d %% 4", Element.LOG_AUTUMN.getID(), Element.LOG_AUTUMN.getMetadata()));
            tree.setString("leaves", String.format("%s,%d %% 8; %s,%d %% 8; %s,%d %% 8; %s,%d %% 8",
                    Element.LEAVES_AUTUMN_BROWN.getID(), Element.LEAVES_AUTUMN_BROWN.getMetadata(),
                    Element.LEAVES_AUTUMN_ORANGE.getID(), Element.LEAVES_AUTUMN_ORANGE.getMetadata(),
                    Element.LEAVES_AUTUMN_PURPLE.getID(), Element.LEAVES_AUTUMN_PURPLE.getMetadata(),
                    Element.LEAVES_AUTUMN_YELLOW.getID(), Element.LEAVES_AUTUMN_YELLOW.getMetadata()));
            treeList.appendTag(tree);

            // EBXL rainbow eucalyptus
            tree = new NBTTagCompound();
            tree.setString("treeName", "rainboweucalyptus");
            tree.setString("logs", String.format("%s; %s; %s,%d %% 4", Element.LOG_QUARTER_RAINBOW_EUCALYPTUS.getID(), Element.LOG_KNEE_RAINBOW_EUCALYPTUS.getID(),
                    Element.LOG_RAINBOW_EUCALYPTUS.getID(), Element.LOG_RAINBOW_EUCALYPTUS.getMetadata()));
            tree.setString("leaves", String.format("%s,%d %% 8", Element.LEAVES_RAINBOW_EUCALYPTUS.getID(), Element.LEAVES_RAINBOW_EUCALYPTUS.getMetadata()));
            tree.setInteger("maxHorLeafBreakDist", 6);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);

            // EBXL bald cypress
            tree = new NBTTagCompound();
            tree.setString("treeName", "baldcypress");
            tree.setString("logs", String.format("%s; %s; %s,%d %% 4", Element.LOG_QUARTER_BALD_CYPRESS.getID(), Element.LOG_KNEE_BALD_CYPRESS.getID(),
                    Element.LOG_BALD_CYPRESS.getID(), Element.LOG_BALD_CYPRESS.getMetadata()));
            tree.setString("leaves", String.format("%s,%d %% 8", Element.LEAVES_BALD_CYPRESS.getID(), Element.LEAVES_BALD_CYPRESS.getMetadata()));
            tree.setInteger("maxHorLeafBreakDist", 6);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);

            tpModCfg.setTag("trees", treeList);

            FMLInterModComms.sendMessage("Treecapitator", "ThirdPartyModConfig", tpModCfg);
            LogHelper.info("Treecapitator IMC message sent.");
        }
    }
}
