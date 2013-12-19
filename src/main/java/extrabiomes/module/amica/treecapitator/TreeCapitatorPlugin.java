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
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Reference;

public class TreeCapitatorPlugin
{
    public static void init()
    {
        if (Loader.isModLoaded("TreeCapitator"))
        {
            LogHelper.info("Initializing TreeCapitator support...");
            NBTTagCompound tpModCfg = new NBTTagCompound();
            tpModCfg.setString("modID", Reference.MOD_ID);
            
            NBTTagList treeList = new NBTTagList();
            
            // local var to keep code concise
            int[] q = { BlockSettings.QUARTERLOG0.getID(), BlockSettings.QUARTERLOG1.getID(), BlockSettings.QUARTERLOG2.getID(), BlockSettings.QUARTERLOG3.getID(), BlockSettings.NEWQUARTERLOG.getID() };
            
            // Vanilla Oak additions
            NBTTagCompound tree = new NBTTagCompound();
            tree.setString("treeName", "vanilla_oak");
            tree.setString("logs", String.format("%d,2; %d,2; %d,2; %d,2;", q[0], q[1], q[2], q[3]));
            tree.setString("leaves", "");
            treeList.appendTag(tree);
            
            // EBXL fir
            tree = new NBTTagCompound();
            tree.setString("treeName", "fir");
            tree.setString("logs", String.format("%d,0; %d,1; %d,1; %d,1; %d,1;", BlockSettings.CUSTOMLOG.getID(), q[0], q[1], q[2], q[3]));
            tree.setString("leaves", String.format("%d,0; %d,8", BlockSettings.GREENLEAVES.getID(), BlockSettings.GREENLEAVES.getID()));
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL redwood
            tree = new NBTTagCompound();
            tree.setString("treeName", "redwood");
            tree.setString("logs", String.format("%d,0; %d,0; %d,0; %d,0;", q[0], q[1], q[2], q[3]));
            tree.setString("leaves", String.format("%d,1; %d,9", BlockSettings.GREENLEAVES.getID(), BlockSettings.GREENLEAVES.getID()));
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL acacia
            tree = new NBTTagCompound();
            tree.setString("treeName", "acacia");
            tree.setString("logs", String.format("%d,1; %d,5; %d,9", BlockSettings.CUSTOMLOG.getID(), BlockSettings.CUSTOMLOG.getID(), BlockSettings.CUSTOMLOG.getID()));
            tree.setString("leaves", String.format("%d,2; %d,10", BlockSettings.GREENLEAVES.getID(), BlockSettings.GREENLEAVES.getID()));
            treeList.appendTag(tree);
            
            // EBXL cypress
            tree = new NBTTagCompound();
            tree.setString("treeName", "cypress");
            tree.setString("logs", String.format("%d,2; %d,6; %d,10", BlockSettings.CUSTOMLOG.getID(), BlockSettings.CUSTOMLOG.getID(), BlockSettings.CUSTOMLOG.getID()));
            tree.setString("leaves", String.format("%d,3; %d,11", BlockSettings.GREENLEAVES.getID(), BlockSettings.GREENLEAVES.getID()));
            treeList.appendTag(tree);
            
            // EBXL Japanese maple
            tree = new NBTTagCompound();
            tree.setString("treeName", "japanesemaple");
            tree.setString("logs", String.format("%d,3; %d,7; %d,11", BlockSettings.CUSTOMLOG.getID(), BlockSettings.CUSTOMLOG.getID(), BlockSettings.CUSTOMLOG.getID()));
            tree.setString("leaves", String.format("%d,1; %d,9; %d,2; %d,10; %d,5; %d,13", BlockSettings.NEWLEAVES.getID(), BlockSettings.NEWLEAVES.getID(), BlockSettings.NEWLEAVES.getID(), BlockSettings.NEWLEAVES.getID(), BlockSettings.NEWLEAVES.getID(), BlockSettings.NEWLEAVES.getID()));
            tree.setInteger("maxHorLeafBreakDist", 6);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL Sakura Blossom 
            tree = new NBTTagCompound();
            tree.setString("treeName", "sakurablossom");
            tree.setString("logs", String.format("%d,0; %d,4; %d,8", BlockSettings.MINILOG.getID(), BlockSettings.MINILOG.getID(), BlockSettings.MINILOG.getID()));
            tree.setString("leaves", String.format("%d,0; %d,8", BlockSettings.MORELEAVES.getID(), BlockSettings.MORELEAVES.getID()));
            tree.setInteger("maxHorLeafBreakDist", 8);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL autumn trees
            tree = new NBTTagCompound();
            tree.setString("treeName", "autumntree");
            tree.setString("logs", String.format("%d,1; %d,5; %d,9", BlockSettings.NEWLOG.getID(), BlockSettings.NEWLOG.getID(), BlockSettings.NEWLOG.getID()));
            tree.setString("leaves", String.format("%d,0; %d,1; %d,2; %d,3; %d,8; %d,9; %d,10; %d,11", BlockSettings.AUTUMNLEAVES.getID(), BlockSettings.AUTUMNLEAVES.getID(),
                    BlockSettings.AUTUMNLEAVES.getID(), BlockSettings.AUTUMNLEAVES.getID(), BlockSettings.AUTUMNLEAVES.getID(), BlockSettings.AUTUMNLEAVES.getID(),
                    BlockSettings.AUTUMNLEAVES.getID(), BlockSettings.AUTUMNLEAVES.getID()));
            treeList.appendTag(tree);
            
            // EBXL rainbow eucalyptus
            tree = new NBTTagCompound();
            tree.setString("treeName", "rainboweucalyptus");
            tree.setString("logs", String.format("%d; %d; %d,0; %d,4; %d,8", BlockSettings.RAINBOWQUARTERLOG.getID(), BlockSettings.RAINBOWKNEELOG.getID(), BlockSettings.NEWLOG.getID(),
                    BlockSettings.NEWLOG.getID(), BlockSettings.NEWLOG.getID()));
            tree.setString("leaves", String.format("%d,3; %d,11", BlockSettings.NEWLEAVES.getID(), BlockSettings.NEWLEAVES.getID()));
            tree.setInteger("maxHorLeafBreakDist", 6);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL bald cypress
            tree = new NBTTagCompound();
            tree.setString("treeName", "baldcypress");
            tree.setString("logs", String.format("%d; %d; %d,2; %d,6; %d,10", BlockSettings.NEWQUARTERLOG.getID(), BlockSettings.KNEELOG.getID(), BlockSettings.NEWLOG.getID(),
                    BlockSettings.NEWLOG.getID(), BlockSettings.NEWLOG.getID()));
            tree.setString("leaves", String.format("%d,0; %d,8", BlockSettings.NEWLEAVES.getID(), BlockSettings.NEWLEAVES.getID()));
            tree.setInteger("maxHorLeafBreakDist", 6);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            tpModCfg.setTag("trees", treeList);
            
            FMLInterModComms.sendMessage("TreeCapitator", "ThirdPartyModConfig", tpModCfg);
            LogHelper.info("TreeCapitator IMC message sent.");
        }
    }
}
