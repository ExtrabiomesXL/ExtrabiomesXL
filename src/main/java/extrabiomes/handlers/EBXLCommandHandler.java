package extrabiomes.handlers;

import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.oredict.OreDictionary;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.lib.Element;
import extrabiomes.lib.Vector3;
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
import extrabiomes.module.summa.worldgen.WorldGenRainbowEucalyptusTree;
import extrabiomes.module.summa.worldgen.WorldGenNewRedwood;
import extrabiomes.module.summa.worldgen.WorldGenSakuraBlossomTree;

public class EBXLCommandHandler extends CommandBase
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
    	player.addChatMessage(new ChatComponentText(message));
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] cmds)
    {
        if (icommandsender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) icommandsender;

            if (cmds.length == 0)
            {
                sendChatMessage(player, "use \"/ebxl help\" for the list of valid commands.");
            }
            else
            {
                if (cmds[0].equals("help"))
                {
                    if (cmds.length == 1)
                    {
                        helpList(player);
                    }
                    else
                    {
                        // Give instructions about a command
                        if (cmds[1].equals("help"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl help Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl help [command]\u00A7r");
                            sendChatMessage(player, "If [command] is blank or an invalid command then the list of");
                            sendChatMessage(player, "valid commands will be displayed. If [command] is a valid");
                            sendChatMessage(player, "command then details about that command will be dispalyed.");
                        }
                        else if (cmds[1].equals("spawntree"))
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
                        else if (cmds[1].equals("version"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl version Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl version\u00A7r");
                            sendChatMessage(player, "Displays the change log for the current");
                            sendChatMessage(player, "version of ExtrabiomesXL.");
                        }
                        else if (cmds[1].equals("lastseed"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl lastseed Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl lastseed <treetype>\u00A7r");
                            sendChatMessage(player, "Displays the last random number that was used to generate");
                            sendChatMessage(player, "the specified tree type for use with the spawntree command.");
                        }
                        else if (cmds[1].equals("saplingdespawntime"))
                        {
                            sendChatMessage(player, "\u00A72-ExtrabiomesXl saplingdespawntime Command-\u00A7r");
                            sendChatMessage(player, "\u00A7o/ebxl saplingdespawntime [ticks]\u00A7r");
                            sendChatMessage(player, "Display/set the number of ticks that a sapling will exist");
                            sendChatMessage(player, "on the ground before it despawns.");
                        }
                        else if (cmds[1].equals("killtree"))
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
                else if (cmds[0].equals("kill"))
                {
                    killTree(player, 0, 4, 0);
                }
                else if (cmds[0].equals("spawn"))
                {
                    (new WorldGenSakuraBlossomTree(true)).generate(player.worldObj, player.worldObj.rand, 0, 4, 0);
                }
                else if (cmds[0].equals("killtree"))
                {
                    if (cmds.length == 4)
                    {
                        try
                        {
                            int x = Integer.parseInt(cmds[1]);
                            int y = Integer.parseInt(cmds[2]);
                            int z = Integer.parseInt(cmds[3]);

                            killTree(player, x, y, z);
                        }
                        catch (Exception e)
                        {
                            sendChatMessage(player, "X, Y and Z must be valid numbers.");
                        }

                    }
                    else if (cmds.length == 2 && cmds[1].equals("here"))
                    {
                        killTree(player, (int) player.posX, (int) player.posY - 1, (int) player.posZ);
                    }
                    else
                    {
                        sendChatMessage(player, "Incorrect format. /ebxl killtree <x> <y> <z>");
                    }
                }
                else if (cmds[0].equals("saplingdespawntime"))
                {
                    if (cmds.length == 1)
                    {
                        sendChatMessage(player, "Sapling despawn time is currently: " + Integer.toString(BlockCustomSapling.getSaplingLifespan()) + " ticks");
                    }
                    else if (cmds.length == 2)
                    {
                        int newTime = Integer.parseInt(cmds[1]);

                        if (newTime >= 0 && newTime <= 10000)
                        {
                            BlockCustomSapling.setSaplingLifespan(newTime);
                            BlockNewSapling.setSaplingLifespan(newTime);
                            sendChatMessage(player, "Sapling despawn time set to: " + cmds[1] + " ticks");
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
                else if (cmds[0].equals("lastseed"))
                {
                    if (cmds.length == 1)
                    {
                        treeNames(player);
                    }
                    else
                    {
                        if (cmds[1].equals("acacia"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAcacia.getLastSeed()));
                        }
                        else if (cmds[1].equals("cypress"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenCypressTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("baldcypress"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBaldCypressTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("rainbow"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenRainbowEucalyptusTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("japanesemaple"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenJapaneseMapleTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("japanesemapleshrub"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenJapaneseMapleShrub.getLastSeed()));
                        }
                        else if (cmds[1].equals("fir"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenFirTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("redwood"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenNewRedwood.getLastSeed()));
                        }
                        else if (cmds[1].equals("largefir"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenFirTreeHuge.getLastSeed()));
                        }
                        else if (cmds[1].equals("brown"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("orange"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("red"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("yellow"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("largebrown"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("largeorange"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("largered"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("largeyellow"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBigAutumnTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("baldcypress"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenBaldCypressTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("sakura"))
                        {
                            sendChatMessage(player, "The last seed used was: " + Long.toString(WorldGenSakuraBlossomTree.getLastSeed()));
                        }
                        else if (cmds[1].equals("legend"))
                        {
                            sendChatMessage(player, "The Legend Oak does not currently support seeding.");
                        }
                        else
                        {
                            treeNames(player);
                        }
                    }
                }
                else if (cmds[0].equals("spawntree"))
                {
                    if (cmds.length == 5)
                    {
                        try
                        {
                            int x = Integer.parseInt(cmds[2]);
                            int y = Integer.parseInt(cmds[3]);
                            int z = Integer.parseInt(cmds[4]);

                            if (cmds[1].equals("acacia"))
                            {
                                (new WorldGenAcacia(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("cypress"))
                            {
                                (new WorldGenCypressTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("baldcypress"))
                            {
                                (new WorldGenBaldCypressTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("rainbow"))
                            {
                                (new WorldGenRainbowEucalyptusTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("japanesemaple"))
                            {
                                (new WorldGenJapaneseMapleTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("japanesemapleshrub"))
                            {
                                (new WorldGenJapaneseMapleShrub(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("fir"))
                            {
                                (new WorldGenFirTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("redwood"))
                            {
                                (new WorldGenNewRedwood(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("largeFir"))
                            {
                                (new WorldGenFirTreeHuge(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("brown"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("orange"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("red"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("yellow"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("largebrown"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("largeorange"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("largered"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("largeyellow"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("sakura"))
                            {
                                (new WorldGenSakuraBlossomTree(true)).generate(player.worldObj, player.worldObj.rand, x, y, z);
                            }
                            else if (cmds[1].equals("legend"))
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
                    else if (cmds.length == 6)
                    {
                        try
                        {
                            int x = Integer.parseInt(cmds[2]);
                            int y = Integer.parseInt(cmds[3]);
                            int z = Integer.parseInt(cmds[4]);
                            long seed = Long.parseLong(cmds[5]);

                            if (cmds[1].equals("acacia"))
                            {
                                (new WorldGenAcacia(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("cypress"))
                            {
                                (new WorldGenCypressTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("baldcypress"))
                            {
                                (new WorldGenBaldCypressTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("rainbow"))
                            {
                                (new WorldGenRainbowEucalyptusTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("japanesemaple"))
                            {
                                (new WorldGenJapaneseMapleTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("japanesemapleshrub"))
                            {
                                (new WorldGenJapaneseMapleShrub(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("fir"))
                            {
                                (new WorldGenFirTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("redwood"))
                            {
                                (new WorldGenNewRedwood(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("largeFir"))
                            {
                                (new WorldGenFirTreeHuge(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("brown"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("orange"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("red"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("yellow"))
                            {
                                WorldGenAutumnTree tree = new WorldGenAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("largeBrown"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.BROWN);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("largeorange"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.ORANGE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("largered"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.PURPLE);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("largeyellow"))
                            {
                                WorldGenBigAutumnTree tree = new WorldGenBigAutumnTree(true, AutumnTreeType.YELLOW);
                                WorldGenBigAutumnTree.setTrunkBlock(Block.getBlockFromItem(Element.LOG_AUTUMN.get().getItem()), Element.LOG_AUTUMN.get().getItemDamage());
                                tree.generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("sakura"))
                            {
                                (new WorldGenSakuraBlossomTree(true)).generate(player.worldObj, seed, x, y, z);
                            }
                            else if (cmds[1].equals("legend"))
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
                    sendChatMessage(player, "\"/ebxl " + cmds[0] + "\" is not a valid command.");
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
        Queue<Vector3> killList = new LinkedList<Vector3>();

        killList.add(new Vector3(x, y, z));
        Vector3 currentBlock;

        while (killList.size() > 0)
        {
            currentBlock = killList.remove();
            Block block = player.worldObj.getBlock(currentBlock.x(), currentBlock.y(), currentBlock.z());
            int damage = player.worldObj.getBlockMetadata(currentBlock.x(), currentBlock.y(), currentBlock.z());
            String blockType = OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(block, 1, damage)));

            // shorten the coords
            int x1 = currentBlock.x();
            int y1 = currentBlock.y();
            int z1 = currentBlock.z();

            if (blockType.equals("logWood") || blockType.equals("treeLeaves"))
            {
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
    public int compareTo(Object o) {
        return 0;
    }
}
