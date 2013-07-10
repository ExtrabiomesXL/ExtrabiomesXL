package extrabiomes.handlers;

import java.util.LinkedList;
import java.util.Queue;

import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.lib.Vector3;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenBigAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.oredict.OreDictionary;

public class EBXLCommandHandler extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "ebxl";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		// TODO Auto-generated method stub
		return "/ebxl help";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] cmds) {
		// TODO Auto-generated method stub
		if(icommandsender instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)icommandsender;
                
                if(cmds.length == 0) {
                	player.addChatMessage("use \"/ebxl help\" for the list of valid commands.");
                } else {
                	if(cmds[0].equals("help")) {
                		if(cmds.length == 1) {
                			helpList(player);
                		} else {
                			// Give instructions about a command
                			if(cmds[1].equals("help")) {
                				player.addChatMessage("\u00A72-ExtrabiomesXl help Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl help [command]\u00A7r");
                				player.addChatMessage("If [command] is blank or an invalid command then the list of");
                				player.addChatMessage("valid commands will be displayed. If [command] is a valid");
                				player.addChatMessage("command then details about that command will be dispalyed.");
                			} else if(cmds[1].equals("spawntree")) {
                				player.addChatMessage("\u00A72-ExtrabiomesXl spawntree Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl spawntree <treetype> <x> <y> <z> [seed]\u00A7r");
                				player.addChatMessage("Forces a tree of the specified <type> to spawn at");
                				player.addChatMessage("<x>,<y>,<z> in the world. [command] is optional and if a");
                				player.addChatMessage("number is provided will force the tree to use the same random");
                				player.addChatMessage("numbers for tree generation for any giver seed. For example");
                				player.addChatMessage("\"/ebxl spawntree fur 0 100 0 100\" will cause the exact same");
                				player.addChatMessage("fur tree to spawn at 0,100,0 no matter how many times you run");
                				player.addChatMessage("the command.");
                			} else if(cmds[1].equals("version")){
                				player.addChatMessage("\u00A72-ExtrabiomesXl version Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl version\u00A7r");
                				player.addChatMessage("Displays the change log for the current");
                				player.addChatMessage("version of ExtrabiomesXL.");
                			} else if(cmds[1].equals("lastseed")){
                				player.addChatMessage("\u00A72-ExtrabiomesXl lastseed Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl lastseed <treetype>\u00A7r");
                				player.addChatMessage("Displays the last random number that was used to generate");
                				player.addChatMessage("the specified tree type for use with the spawntree command.");
                			} else if(cmds[1].equals("saplingdespawntime")){
                				player.addChatMessage("\u00A72-ExtrabiomesXl saplingdespawntime Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl saplingdespawntime [ticks]\u00A7r");
                				player.addChatMessage("Display/set the number of ticks that a sapling will exist");
                				player.addChatMessage("on the ground before it despawns.");
                			} else if(cmds[1].equals("killtree")){
                				player.addChatMessage("\u00A72-ExtrabiomesXl killtree Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl killtree <x> <y> <z>\u00A7r");
                				player.addChatMessage("Kills the tree at the specified coords.");
                			} else {
                				helpList(player);
                			}
                		}
                	} else if(cmds[0].equals("killtree")) {
                		if(cmds.length == 4){
                			try {
                				int x = Integer.parseInt(cmds[1]);
                				int y = Integer.parseInt(cmds[2]);
                				int z = Integer.parseInt(cmds[3]);

                				killTree(player, x , y, z);
                			} catch(Exception e){
                				player.addChatMessage("X, Y and Z must be valid numbers.");
                			}

                		} else {
                			player.addChatMessage("Incorrect format. /ebxl killtree <x> <y> <z>");
                		}
                	} else if(cmds[0].equals("saplingdespawntime")) {
                		if(cmds.length == 1){
                			player.addChatMessage("Sapling despawn time is currently: " + Integer.toString(BlockCustomSapling.getSaplingLifespan()) + " ticks");
                		} else if(cmds.length == 2){
                			int newTime = Integer.parseInt(cmds[1]);
                			
                			if(newTime >= 0 && newTime <= 10000){
                				BlockCustomSapling.setSaplingLifespan(newTime);
                				player.addChatMessage("Sapling despawn time set to: " + cmds[1] + " ticks");
                			} else {
                				player.addChatMessage("Sapling despawn time must be between 0 and 10000.");
                			}
                		} else {
                			player.addChatMessage("Incorrect format. /ebxl saplingDespawn [newtime]");
                		}
                	} else if(cmds[0].equals("lastseed")) {
                		if(cmds.length == 1){
                			player.addChatMessage("The following tree names are supported:");
            				player.addChatMessage("acacia, cypress, fir, redwood, largeFir,");
            				player.addChatMessage("brown, orange, purple, yellow, largeBrown,");
            				player.addChatMessage("largeOrange, largePurple, largeYellow");
                		} else {
                			if(cmds[1].equals("acacia")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenAcacia.getLastSeed()));
                			} else if(cmds[1].equals("cypress")) {
                				//player.addChatMessage("The last seed used was: " + Long.toString(WorldGenCypressTree.getLastSeed()));
                				player.addChatMessage("There is no worldgen for the cypress tree at this time.");
                			} else if(cmds[1].equals("fir")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenFirTree.getLastSeed()));
                			} else if(cmds[1].equals("redwood")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenRedwood.getLastSeed()));
                			} else if(cmds[1].equals("largeFir")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenFirTreeHuge.getLastSeed()));
                			} else if(cmds[1].equals("brown")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                			} else if(cmds[1].equals("orange")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                			} else if(cmds[1].equals("purple")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                			} else if(cmds[1].equals("yellow")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                			} else if(cmds[1].equals("largeBrown")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                			} else if(cmds[1].equals("largeOrange")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                			} else if(cmds[1].equals("largePurple")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                			} else if(cmds[1].equals("largeYellow")) {
                				player.addChatMessage("The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                			} else {
                				player.addChatMessage("Only the following tree names are supported:");
                				player.addChatMessage("acacia, cypress, fir, redwood, largeFir,");
                				player.addChatMessage("brown, orange, purple, yellow, largeBrown,");
                				player.addChatMessage("largeOrange, largePurple, largeYellow");
                			}
                		}
                	} else if(cmds[0].equals("spawntree")) {
                		if(cmds.length == 5) {
                			try {
                				int x = Integer.parseInt(cmds[2]);
                				int y = Integer.parseInt(cmds[3]);
                				int z = Integer.parseInt(cmds[4]);

                				if(cmds[1].equals("acacia")) {
                    				(new WorldGenAcacia(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("cypress")) {
                    				//new WorldGenAcacia(true).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    				player.addChatMessage("There is no worldgen for the cypress tree at this time.");
                    			} else if(cmds[1].equals("fir")) {
                    				(new WorldGenFirTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("redwood")) {
                    				(new WorldGenRedwood(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("largeFir")) {
                    				(new WorldGenFirTreeHuge(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("brown")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.BROWN)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("orange")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.ORANGE)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("purple")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.PURPLE)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("yellow")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.YELLOW)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("largeBrown")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("largeOrange")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("largePurple")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("largeYellow")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else {
                    				player.addChatMessage("Only the following tree names are supported:");
                    				player.addChatMessage("acacia, cypress, fir, redwood, largeFir,");
                    				player.addChatMessage("brown, orange, purple, yellow, largeBrown,");
                    				player.addChatMessage("largeOrange, largePurple, largeYellow");
                    			}
                			} catch(Exception e){
                				player.addChatMessage("X, Y and Z must be valid numbers.");
                			}
                		} else if(cmds.length == 6){
                			try {
                				int x = Integer.parseInt(cmds[2]);
                				int y = Integer.parseInt(cmds[3]);
                				int z = Integer.parseInt(cmds[4]);
                				long seed = Long.parseLong(cmds[5]);

                				if(cmds[1].equals("acacia")) {
                    				(new WorldGenAcacia(true)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("cypress")) {
                    				//new WorldGenAcacia(true).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    				player.addChatMessage("There is no worldgen for the cypress tree at this time.");
                    			} else if(cmds[1].equals("fir")) {
                    				(new WorldGenFirTree(true)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("redwood")) {
                    				(new WorldGenRedwood(true)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("largeFir")) {
                    				(new WorldGenFirTreeHuge(true)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("brown")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.BROWN)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("orange")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.ORANGE)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("purple")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.PURPLE)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("yellow")) {
                    				(new WorldGenAutumnTree(true, AutumnTreeType.YELLOW)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("largeBrown")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("largeOrange")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("largePurple")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE)).generate(player.worldObj, seed, x, y, z);
                    			} else if(cmds[1].equals("largeYellow")) {
                    				(new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW)).generate(player.worldObj, seed, x, y, z);
                    			} else {
                    				player.addChatMessage("Only the following tree names are supported:");
                    				player.addChatMessage("acacia, cypress, fir, redwood, largeFir,");
                    				player.addChatMessage("brown, orange, purple, yellow, largeBrown,");
                    				player.addChatMessage("largeOrange, largePurple, largeYellow");
                    			}
                			} catch(Exception e){
                				player.addChatMessage("X, Y, Z and seed must be valid numbers.");
                			}
                		} else {
                			player.addChatMessage("Incorrect format. /ebxl spawntree <treetype> <x> <y> <z> [seed]");
                		}
                	} else {
                		player.addChatMessage("\"/ebxl " + cmds[0] + "\" is not a valid command.");
                		player.addChatMessage("Use \"/ebxl help\" for the list of valid commands.");
                	}
                }
        }

	}
	
	private boolean killTree(EntityPlayer player, int x, int y, int z){
		Queue<Vector3> killList = new LinkedList<Vector3>();
		
		killList.add(new Vector3(x,y,z));
		int killBlock = 0;
		Vector3 currentBlock;
		
		while(killList.size() > 0){
			currentBlock = killList.remove();
			int blockId = player.worldObj.getBlockId(currentBlock.x(), currentBlock.y(), currentBlock.z());
			int damage = player.worldObj.getBlockId(currentBlock.x(), currentBlock.y(), currentBlock.z());
			String blockType = OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(blockId, 1, damage)));
			
			// shorten the coords
			int x1 = currentBlock.x();
			int y1 = currentBlock.y();
			int z1 = currentBlock.z();
			
			if(blockType.equals("logWood") || blockType.equals("treeLeaves")){
				// Add extra blocks to the list
				killList.add(new Vector3(x1, y1 + 1, z1));
				killList.add(new Vector3(x1, y1 - 1, z1));
				killList.add(new Vector3(x1 + 1, y1, z1));
				killList.add(new Vector3(x1 - 1, y1, z1));
				killList.add(new Vector3(x1, y1, z1 + 1));
				killList.add(new Vector3(x1, y1, z1 - 1));
				
				// Remove the block
				player.worldObj.setBlockToAir(x1, y1, z1);
			}
		}
		
		return true;
	}
	
	private void helpList(EntityPlayer player){
		// List the available commands
		player.addChatMessage("\u00A72-ExtrabiomesXl Commands-\u00A7r");
		player.addChatMessage("/ebxl help [command]");
		player.addChatMessage("/ebxl lastseed <treetype>");
		player.addChatMessage("/ebxl killtree <x> <y> <z>");
		player.addChatMessage("/ebxl saplingdespawntime [ticks]");
		player.addChatMessage("/ebxl spawntree <treetype> <x> <y> <z> [seed]");
		player.addChatMessage("/ebxl version");
	}
}
