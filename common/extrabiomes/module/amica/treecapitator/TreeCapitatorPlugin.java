/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.treecapitator;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import extrabiomes.api.PluginEvent;
import extrabiomes.lib.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.ForgeSubscribe;

public class TreeCapitatorPlugin {
	
	@ForgeSubscribe
    public void init(PluginEvent.Init event) throws Exception {
		if (Loader.isModLoaded("TreeCapitator")) {
            NBTTagCompound tpModCfg = new NBTTagCompound();
            tpModCfg.setString("modID", Reference.MOD_ID);
            tpModCfg.setString("configPath", "extrabiomes/extrabiomes.cfg");
            tpModCfg.setString("blockConfigKeys", "block:customlog.id; block:quarterlog0.id; block:quarterlog1.id; block:quarterlog2.id; block:quarterlog3.id; " +
                    "block:autumnleaves.id; block:greenleaves.id");
            tpModCfg.setString("itemConfigKeys", "");
            tpModCfg.setString("axeIDList", "");
            tpModCfg.setString("shearsIDList", "");
            tpModCfg.setBoolean("useShiftedItemID", true);
            
            NBTTagList treeList = new NBTTagList();
            
            // Vanilla Oak additions
            NBTTagCompound tree = new NBTTagCompound();
            tree.setString("treeName", "vanilla_oak");
            tree.setString("logConfigKeys", "<block:quarterlog0.id>,2; <block:quarterlog1.id>,2; <block:quarterlog2.id>,2; <block:quarterlog3.id>,2;");
            tree.setString("leafConfigKeys", "<block:autumnleaves.id>");
            treeList.appendTag(tree);
            
            // Vanilla Spruce additions
            tree = new NBTTagCompound();
            tree.setString("treeName", "vanilla_spruce");
            tree.setString("logConfigKeys", "");
            tree.setString("leafConfigKeys", "<block:autumnleaves.id>");
            treeList.appendTag(tree);
            
            // EBXL fir
            tree = new NBTTagCompound();
            tree.setString("treeName", "fir");
            tree.setString("logConfigKeys", "<block:customlog.id>,0; <block:quarterlog0.id>,1; <block:quarterlog1.id>,1; <block:quarterlog2.id>,1; <block:quarterlog3.id>,1");
            tree.setString("leafConfigKeys", "<block:greenleaves.id>,0; <block:greenleaves.id>,8");
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL redwood
            tree = new NBTTagCompound();
            tree.setString("treeName", "redwood");
            tree.setString("logConfigKeys", "<block:quarterlog0.id>,0; <block:quarterlog1.id>,0; <block:quarterlog2.id>,0; <block:quarterlog3.id>,0");
            tree.setString("leafConfigKeys", "<block:greenleaves.id>,1; <block:greenleaves.id>,9");
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL acacia
            tree = new NBTTagCompound();
            tree.setString("treeName", "acacia");
            tree.setString("logConfigKeys", "<block:customlog.id>,1");
            tree.setString("leafConfigKeys", "<block:greenleaves.id>,2");
            treeList.appendTag(tree);
            
            tpModCfg.setTag("trees", treeList);
            
            FMLInterModComms.sendMessage("TreeCapitator", Reference.MOD_ID, tpModCfg);
        }
    }
}
