package extrabiomes.handlers;

import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.gen.feature.WorldGenerator;

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
                			} else {
                				helpList(player);
                			}
                		}
                	} else if(cmds[0].equals("saplingDespawn")) {
                		if(cmds.length == 1){
                			player.addChatMessage("Sapling despawn time is currently: " + Integer.toString(BlockCustomSapling.getSaplingLifespan()));
                		} else if(cmds.length == 2){
                			int newTime = Integer.parseInt(cmds[1]);
                			
                			if(newTime >= 0 && newTime <= 10000){
                				BlockCustomSapling.setSaplingLifespan(newTime);
                				player.addChatMessage("Sapling despawn time set to: " + cmds[1]);
                			} else {
                				player.addChatMessage("Sapling despawn time must be between 0 and 10000.");
                			}
                		} else {
                			player.addChatMessage("Incorrect format. /ebxl saplingDespawn [newtime]");
                		}
                	} else if(cmds[0].equals("spawntree")) {
                		if(cmds.length == 5) {
                			boolean valid = true;
                			int x = 0;
                			int y = 0;
                			int z = 0;
                			
                			// Get the 
                			try {
                				x = Integer.parseInt(cmds[2]);
                				y = Integer.parseInt(cmds[3]);
                				z = Integer.parseInt(cmds[4]);

                				if(cmds[1].equals("acacia")) {
                    				new WorldGenAcacia(true).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else if(cmds[1].equals("acacia")) {
                    				new WorldGenAcacia(true).generate(player.worldObj, player.worldObj.rand, x, y, z);
                    			} else {
                    				player.addChatMessage("That tree is not supported.");
                    			}
                			} catch(Exception e){
                				valid = false;
                				player.addChatMessage("X, Y and Z must be valid numbers.");
                			}
                		} else if(cmds.length == 6){
                			player.addChatMessage("Not implemented just yet.");
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
	
	private void helpList(EntityPlayer player){
		// List the available commands
		player.addChatMessage("\u00A72-ExtrabiomesXl Commands-\u00A7r");
		player.addChatMessage("/ebxl help [command]");
		player.addChatMessage("/ebxl lastseed <treetype>");
		player.addChatMessage("/ebxl spawntree <treetype> <x> <y> <z> [seed]");
		player.addChatMessage("/ebxl version");
	}
}
