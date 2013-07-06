package extrabiomes.handlers;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

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
                		if(cmds.length == 1){
                			helpList(player);
                		} else {
                			// Give instructions about a command
                			if(cmds[1].equals("help")) {
                				player.addChatMessage("\u00A71-ExtrabiomesXl help Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl help [command]\u00A7r");
                				player.addChatMessage("If [command] is blank or an invalid command then the list of");
                				player.addChatMessage("valid commands will be displayed. If [command] is a valid");
                				player.addChatMessage("command then details about that command will be dispalyed.");
                			} else if(cmds[1].equals("spawntree")) {
                				player.addChatMessage("\u00A71-ExtrabiomesXl spawntree Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl spawntree <type> <x> <y> <z> [seed]\u00A7r");
                				player.addChatMessage("Forces a tree of the specified <type> to spawn at");
                				player.addChatMessage("<x>,<y>,<z> in the world. [command] is optional and if a");
                				player.addChatMessage("number is provided will force the tree to use the same random");
                				player.addChatMessage("numbers for tree generation for any giver seed. For example");
                				player.addChatMessage("\"/ebxl spawntree fur 0 100 0 100\" will cause the exact same");
                				player.addChatMessage("fur tree to spawn at 0,100,0 no matter how many times you run");
                				player.addChatMessage("the command.");
                			} else if(cmds[1].equals("version")){
                				player.addChatMessage("\u00A71-ExtrabiomesXl version Command-\u00A7r");
                				player.addChatMessage("\u00A7o/ebxl version\u00A7r");
                				player.addChatMessage("Displays the change log for the current");
                				player.addChatMessage("version of ExtrabiomesXL.");
                			} else {
                				helpList(player);
                			}
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
		player.addChatMessage("\u00A71-ExtrabiomesXl Commands-\u00A7r");
		player.addChatMessage("/ebxl help [command]");
		player.addChatMessage("/ebxl spawntree <type> <x> <y> <z> [seed]");
		player.addChatMessage("/ebxl version");
		
	}

}
