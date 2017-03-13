package extrabiomes.handlers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType;
import extrabiomes.module.summa.worldgen.WorldGenBaldCypressTree;
import extrabiomes.module.summa.worldgen.WorldGenBigAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenCypressTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenJapaneseMapleShrub;
import extrabiomes.module.summa.worldgen.WorldGenJapaneseMapleTree;
import extrabiomes.module.summa.worldgen.WorldGenLegendOak;
import extrabiomes.module.summa.worldgen.WorldGenNewRedwood;
import extrabiomes.module.summa.worldgen.WorldGenRainbowEucalyptusTree;
import extrabiomes.module.summa.worldgen.WorldGenSakuraBlossomTree;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.oredict.OreDictionary;

public class EBXLCommandHandler implements ICommand
{

    @Override
    public String getCommandName()
    {
        return "ebxl";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "/ebxl help";
    }
    
    private void sendChatMessage(EntityPlayer player, String message) {
    	player.addChatMessage(new TextComponentString(message));
    }

    @Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;

            if (args.length == 0)
            {
                sendChatMessage(player, "use \"/ebxl help\" for the list of valid commands.");
            }
            else
            {
                if (args[0].equals("help"))
                {
                    if (args.length == 1)
                    {
                        helpList(player);
                    }
                    else
                    {
                        // Give instructions about a command
                        if (args[1].equals("help"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl help Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl help [command]\u00A7r");
                            sendChatMessage(player, "If [command] is blank or an invalid command then the list of");
                            sendChatMessage(player, "valid commands will be displayed. If [command] is a valid");
                            sendChatMessage(player, "command then details about that command will be dispalyed.");
                        }
                        else if (args[1].equals("spawntree"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl spawntree Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl spawntree <treetype> <x> <y> <z> [seed]\u00A7r");
                            sendChatMessage(player, "Forces a tree of the specified <type> to spawn at");
                            sendChatMessage(player, "<x>,<y>,<z> in the world. [command] is optional and if a");
                            sendChatMessage(player, "number is provided will force the tree to use the same random");
                            sendChatMessage(player, "numbers for tree generation for any giver seed. For example");
                            sendChatMessage(player, "\"/ebxl spawntree fur 0 100 0 100\" will cause the exact same");
                            sendChatMessage(player, "fur tree to spawn at 0,100,0 no matter how many times you run");
                            sendChatMessage(player, "the command.");
                        }
                        else if (args[1].equals("version"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl version Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl version\u00A7r");
                            sendChatMessage(player, "Displays the change log for the current");
                            sendChatMessage(player, "version of ExtrabiomesXL.");
                        }
                        else if (args[1].equals("lastseed"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl lastseed Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl lastseed <treetype>\u00A7r");
                            sendChatMessage(player, "Displays the last random number that was used to generate");
                            sendChatMessage(player, "the specified tree type for use with the spawntree command.");
                        }
                        else if (args[1].equals("saplingdespawntime"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl saplingdespawntime Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl saplingdespawntime [ticks]\u00A7r");
                            sendChatMessage(player, "Display/set the number of ticks that a sapling will exist");
                            sendChatMessage(player, "on the ground before it despawns.");
                        }
                        else if (args[1].equals("killtree"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl killtree Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl killtree <x> <y> <z>\u00A7r");
                            sendChatMessage(player, "Kills the tree at the specified coords.");
                        }
                        else
                        {
                            helpList(player);
                        }
                    }
                }
                else if (args[0].equals("kill"))
                {
                    killTree(player, 0, 4, 0);
                }
                else if (args[0].equals("spawn"))
                {
                    (new WorldGenSakuraBlossomTree(true)).generate(player.worldObj, player.worldObj.rand, 0, 4, 0);
                }
                else if (args[0].equals("killtree"))
                {
                    if (args.length == 4)
                    {
                        try
                        {
                            int x = Integer.parseInt(args[1]);
                            int y = Integer.parseInt(args[2]);
                            int z = Integer.parseInt(args[3]);

                            killTree(player, x, y, z);
                        }
                        catch (Exception e)
                        {
                            sendChatMessage(player, "X, Y and Z must be valid numbers.");
                        }

                    }
                    else if (args.length == 2 && args[1].equals("here"))
                    {
                        killTree(player, (int) player.posX, (int) player.posY - 1, (int) player.posZ);
                    }
                    else
                    {
                        sendChatMessage(player, "Incorrect format. /ebxl killtree <x> <y> <z>");
                    }
                }
                else if (args[0].equals("saplingdespawntime"))
                {
                    if (args.length == 1)
                    {
                        sendChatMessage(player, "Sapling despawn time is currently: " + Integer.toString(BlockCustomSapling.getSaplingLifespan()) + " ticks");
                    }
                    else if (args.length == 2)
                    {
                        int newTime = Integer.parseInt(args[1]);

                        if (newTime >= 0 && newTime <= 10000)
                        {
                            BlockCustomSapling.setSaplingLifespan(newTime);
                            BlockNewSapling.setSaplingLifespan(newTime);
                            sendChatMessage(player, "Sapling despawn time set to: " + args[1] + " ticks");
                        }
                        else
                        {
                            sendChatMessage(player, "Sapling despawn time must be between 0 and 10000.");
                        }
                    }
                    else
                    {
                        sendChatMessage(player, "Incorrect format. /ebxl saplingDespawn [newtime]");
                    }
                }
                else if (args[0].equals("lastseed"))
                {
                    if (args.length == 1)
                    {
                        treeNames(player);
                    }
                    else
                    {
                        if (args[1].equals("acacia"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAcacia.getLastSeed()));
                        }
                        else if (args[1].equals("cypress"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenCypressTree.getLastSeed()));
                        }
                        else if (args[1].equals("baldcypress"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBaldCypressTree.getLastSeed()));
                        }
                        else if (args[1].equals("rainbow"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenRainbowEucalyptusTree.getLastSeed()));
                        }
                        else if (args[1].equals("japanesemaple"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenJapaneseMapleTree.getLastSeed()));
                        }
                        else if (args[1].equals("japanesemapleshrub"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenJapaneseMapleShrub.getLastSeed()));
                        }
                        else if (args[1].equals("fir"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenFirTree.getLastSeed()));
                        }
                        else if (args[1].equals("redwood"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenNewRedwood.getLastSeed()));
                        }
                        else if (args[1].equals("largefir"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenFirTreeHuge.getLastSeed()));
                        }
                        else if (args[1].equals("brown"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("orange"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("red"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("yellow"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("largebrown"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("largeorange"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("largered"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("largeyellow"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (args[1].equals("baldcypress"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBaldCypressTree.getLastSeed()));
                        }
                        else if (args[1].equals("sakura"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenSakuraBlossomTree.getLastSeed()));
                        }
                        else if (args[1].equals("legend"))
                        {
                            sendChatMessage(player, "The Legend Oak does not currently support seeding.");
                        }
                        else
                        {
                            treeNames(player);
                        }
                    }
                }
                else if (args[0].equals("spawntree"))
                {
                    if (args.length == 5)
                    {
                        try
                        {
                            int x = Integer.parseInt(args[2]);
                            int y = Integer.parseInt(args[3]);
                            int z = Integer.parseInt(args[4]);

                            if (args[1].equals("acacia"))
                            {
                                (new WorldGenAcacia(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("cypress"))
                            {
                                (new WorldGenCypressTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("baldcypress"))
                            {
                                (new WorldGenBaldCypressTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("rainbow"))
                            {
                                (new WorldGenRainbowEucalyptusTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("japanesemaple"))
                            {
                                (new WorldGenJapaneseMapleTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("japanesemapleshrub"))
                            {
                                (new WorldGenJapaneseMapleShrub(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("fir"))
                            {
                                (new WorldGenFirTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("redwood"))
                            {
                                (new WorldGenNewRedwood(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("largeFir"))
                            {
                                (new WorldGenFirTreeHuge(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("brown"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("orange"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("red"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("yellow"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("largebrown"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("largeorange"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("largered"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("largeyellow"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("sakura"))
                            {
                                (new WorldGenSakuraBlossomTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (args[1].equals("legend"))
                            {
                                (new WorldGenLegendOak(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else
                            {
                                treeNames(player);
                            }
                        }
                        catch (Exception e)
                        {
                            sendChatMessage(player, "X, Y and Z must be valid numbers.");
                        }
                    }
                    else if (args.length == 6)
                    {
                        try
                        {
                            int x = Integer.parseInt(args[2]);
                            int y = Integer.parseInt(args[3]);
                            int z = Integer.parseInt(args[4]);
                            long seed = Long.parseLong(args[5]);

                            if (args[1].equals("acacia"))
                            {
                                (new WorldGenAcacia(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("cypress"))
                            {
                                (new WorldGenCypressTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("baldcypress"))
                            {
                                (new WorldGenBaldCypressTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("rainbow"))
                            {
                                (new WorldGenRainbowEucalyptusTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("japanesemaple"))
                            {
                                (new WorldGenJapaneseMapleTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("japanesemapleshrub"))
                            {
                                (new WorldGenJapaneseMapleShrub(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("fir"))
                            {
                                (new WorldGenFirTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("redwood"))
                            {
                                (new WorldGenNewRedwood(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("largeFir"))
                            {
                                (new WorldGenFirTreeHuge(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("brown"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("orange"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("red"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("yellow"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("largeBrown"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("largeorange"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("largered"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("largeyellow"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("sakura"))
                            {
                                (new WorldGenSakuraBlossomTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (args[1].equals("legend"))
                            {
                                (new WorldGenLegendOak(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                                sendChatMessage(player, "The Legend Oak does not currently support seeding.");
                            }
                            else
                            {
                                treeNames(player);
                            }
                        }
                        catch (Exception e)
                        {
                            sendChatMessage(player, "X, Y, Z and seed must be valid numbers.");
                        }
                    }
                    else
                    {
                        sendChatMessage(player, "Incorrect format. /ebxl spawntree <treetype> <x> <y> <z> [seed]");
                    }
                }
                else
                {
                    sendChatMessage(player, "\"/ebxl " + args[0] + "\" is not a valid command.");
                    sendChatMessage(player, "Use \"/ebxl help\" for the list of valid commands.");
                }
            }
        }

    }

    private void treeNames(EntityPlayer player)
    {
        sendChatMessage(player, "Only the following tree names are supported:");
        sendChatMessage(player, "acacia, cypress, fir, redwood, largeFir,");
        sendChatMessage(player, "brown, orange, red, yellow, largebrown,");
        sendChatMessage(player, "largeorange, largered, largeyellow, baldcypress,");
        sendChatMessage(player, "rainbow, japanesemaple, japanesemaple, japanesemapleshrub,");
        sendChatMessage(player, "sakura, legend");
    }

    private boolean killTree(EntityPlayer player, int x, int y, int z)
    {
        Queue<BlockPos> killList = new LinkedList<BlockPos>();

        killList.add(new BlockPos(x, y, z));
        BlockPos currentBlock;

        while (killList.size() > 0)
        {
            currentBlock = killList.remove();
            IBlockState state = player.worldObj.getBlockState(currentBlock);
            Block block = state.getBlock();
            int damage = block.getMetaFromState(state);
            
            String blockType;
            boolean logOrLeaf = false;
            for (int ID : OreDictionary.getOreIDs(new ItemStack(block, 1, damage))) {
            	 blockType = OreDictionary.getOreName(ID);
            	 
            	 if ("logWood".equals(blockType) || "treeLeaves".equals(blockType)) {
            		 logOrLeaf = true;
            		 break;
            	 }
            }

            if (logOrLeaf)
            {
            	// shorten the coords
                int x1 = currentBlock.getX();
                int y1 = currentBlock.getY();
                int z1 = currentBlock.getZ();
                
                // Add extra blocks to the list
                killList.add(new BlockPos(x1, y1 + 1, z1));
                killList.add(new BlockPos(x1, y1 - 1, z1));
                killList.add(new BlockPos(x1 + 1, y1, z1));
                killList.add(new BlockPos(x1 - 1, y1, z1));
                killList.add(new BlockPos(x1, y1, z1 + 1));
                killList.add(new BlockPos(x1, y1, z1 - 1));

                // Remove the block
                player.worldObj.setBlockToAir(currentBlock);
            }
        }

        return true;
    }

    private void helpList(EntityPlayer player)
    {
        // List the available commands
        sendChatMessage(player, "\u00A72-ExtrabiomesXl Commands-\u00A7r");
        sendChatMessage(player, "/ebxl help [command]");
        sendChatMessage(player, "/ebxl lastseed <treetype>");
        sendChatMessage(player, "/ebxl killtree <x> <y> <z>");
        sendChatMessage(player, "/ebxl saplingdespawntime [ticks]");
        sendChatMessage(player, "/ebxl spawntree <treetype> <x> <y> <z> [seed]");
        sendChatMessage(player, "/ebxl version");
    }

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getCommandAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}
}
