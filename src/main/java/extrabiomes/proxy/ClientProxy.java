/*
 * Copyright (c) Scott Killen and MisterFiber, 2012 This mod is
 * distributed under the terms of the Minecraft Mod Public License 1.0,
 * or MMPL. Please check the contents of the license located in
 * /MMPL-1.0.txt
 */

package extrabiomes.proxy;

import java.util.HashSet;
import java.util.Set;

import extrabiomes.blocks.BlockEBXLLeaves;
import extrabiomes.handlers.BlockHandler.LeafHandler.Green_Leaf_Types;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.module.fabrica.scarecrow.EntityScarecrow;
import extrabiomes.module.fabrica.scarecrow.ModelScarecrow;
import extrabiomes.module.fabrica.scarecrow.RenderScarecrow;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String uniqueName) {
		super.registerBlock(block, itemclass, uniqueName);
		
		if (block instanceof ITextureRegisterer) {
			((ITextureRegisterer)block).registerTexture();
		}
	}
	
	@Override
	public void registerItem(Item item, String name) {
		super.registerItem(item, name);
		
		if (item instanceof ITextureRegisterer) {
			((ITextureRegisterer)item).registerTexture();
		}
	}
	
    @Override
    public void registerScarecrowRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityScarecrow.class, new IRenderFactory<EntityScarecrow>() {
			@Override
			public Render<? super EntityScarecrow> createRenderFor(RenderManager manager) {
				return new RenderScarecrow(manager, new ModelScarecrow(), 0.4F);
			}
		});
    }
    
    @Override
    public void onPostInit() {
    	super.onPostInit();
    	
    	final BlockColors colours = Minecraft.getMinecraft().getBlockColors();
    	Set<Block> blocks = new HashSet<Block>(); //TODO: Look back over these
    	
    	/*if (BlockSettings.AUTUMNLEAVES.getEnabled()) {
    		Block leaves = Block.getBlockFromItem(Element.LEAVES_AUTUMN_BROWN.get().getItem());
    		
			colours.registerBlockColorHandler(new IBlockColor() {
				public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
					return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
				}
			}, leaves);
			blocks.add(leaves);
    	}
    	
    	if (BlockSettings.NEWLEAVES.getEnabled()) {
    		@SuppressWarnings("unchecked") //Pah, we know it's fine
			final BlockEBXLLeaves<New_Leaf_Types> leaves = (BlockEBXLLeaves<New_Leaf_Types>) Block.getBlockFromItem(Element.LEAVES_BALD_CYPRESS.get().getItem()); 
    				
    		colours.registerBlockColorHandler(new IBlockColor() {
				public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
					switch (state.getValue(leaves.type)) {
					case JAPANESE_MAPLE:
						return 0xffffff;

					case JAPANESE_MAPLE_SHRUB:
						return ColorizerFoliage.getFoliageColor(1.0F, 0.5F);

					case BALD_CYPRESS:
					case RAINBOW_EUCALYPTUS:
					default:
						return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
					}
				}
			}, leaves);
    		blocks.add(leaves);
    	}
    	
    	if (BlockSettings.NEWLEAVES.getEnabled()) {
    		@SuppressWarnings("unchecked") //Pah, we know it's fine
			final BlockEBXLLeaves<More_Leaf_Types> leaves = (BlockEBXLLeaves<More_Leaf_Types>) Block.getBlockFromItem(Element.LEAVES_SAKURA_BLOSSOM.get().getItem()); 
    				
    		colours.registerBlockColorHandler(new IBlockColor() {
				public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
					switch (state.getValue(leaves.type)) {
					case SAKURA_BLOSSOM:
						return 0xffffff;

					default:
						return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
					}
				}
			}, leaves);
    		blocks.add(leaves);
    	}*/
    	
    	if (BlockSettings.GREENLEAVES.getEnabled()) {
    		@SuppressWarnings("unchecked") //Pah, we know it's fine
			final BlockEBXLLeaves<Green_Leaf_Types> leaves = (BlockEBXLLeaves<Green_Leaf_Types>) Block.getBlockFromItem(Element.LEAVES_FIR.get().getItem()); 
    				
    		colours.registerBlockColorHandler(new IBlockColor() {
				public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
					switch (state.getValue(leaves.type)) {
					case FIR:
						return ColorizerFoliage.getFoliageColorPine();

					case ACACIA:
						return ColorizerFoliage.getFoliageColorBasic();

					case CYPRESS:
						return 0xe5fff3;

					case REDWOOD:
					default:
						return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
					}
				}
			}, leaves);
    		blocks.add(leaves);
    	}
    	
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			@SuppressWarnings("deprecation")
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
				
				return colours.colorMultiplier(state, null, null, tintIndex);
			}
		}, blocks.toArray(new Block[blocks.size()]));
    }
}
