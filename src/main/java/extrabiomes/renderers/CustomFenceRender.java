package extrabiomes.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class CustomFenceRender implements ISimpleBlockRenderingHandler {

  @Override
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    final Tessellator tessellator = Tessellator.instance;

    for (int k = 0; k < 4; ++k) {
      float f2 = 0.125F;

      if (k == 0) {
        renderer.setRenderBounds((double) (0.5F - f2), 0.0D, 0.0D, (double) (0.5F + f2), 1.0D, (double) (f2 * 2.0F));
      }

      if (k == 1) {
        renderer.setRenderBounds((double) (0.5F - f2), 0.0D, (double) (1.0F - f2 * 2.0F), (double) (0.5F + f2), 1.0D, 1.0D);
      }

      f2 = 0.0625F;

      if (k == 2) {
        renderer.setRenderBounds((double) (0.5F - f2), (double) (1.0F - f2 * 3.0F), (double) (-f2 * 2.0F), (double) (0.5F + f2), (double) (1.0F - f2),
            (double) (1.0F + f2 * 2.0F));
      }

      if (k == 3) {
        renderer.setRenderBounds((double) (0.5F - f2), (double) (0.5F - f2 * 3.0F), (double) (-f2 * 2.0F), (double) (0.5F + f2), (double) (0.5F - f2),
            (double) (1.0F + f2 * 2.0F));
      }

      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, -1.0F, 0.0F);
      renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 1.0F, 0.0F);
      renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, metadata));
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, -1.0F);
      renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, metadata));
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, 1.0F);
      renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, metadata));
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, metadata));
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(1.0F, 0.0F, 0.0F);
      renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, metadata));
      tessellator.draw();
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  }

  @Override
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    boolean flag = false;
    float f = 0.375F;
    float f1 = 0.625F;
    renderer.setRenderBounds((double) f, 0.0D, (double) f, (double) f1, 1.0D, (double) f1);
    renderer.renderStandardBlock(block, x, y, z);
    flag = true;
    boolean flag1 = false;
    boolean flag2 = false;

    if (((BlockFence) block).canConnectFenceTo(world, x - 1, y, z) || ((BlockFence) block).canConnectFenceTo(world, x + 1, y, z)) {
      flag1 = true;
    }

    if (((BlockFence) block).canConnectFenceTo(world, x, y, z - 1) || ((BlockFence) block).canConnectFenceTo(world, x, y, z + 1)) {
      flag2 = true;
    }

    boolean flag3 = ((BlockFence) block).canConnectFenceTo(world, x - 1, y, z);
    boolean flag4 = ((BlockFence) block).canConnectFenceTo(world, x + 1, y, z);
    boolean flag5 = ((BlockFence) block).canConnectFenceTo(world, x, y, z - 1);
    boolean flag6 = ((BlockFence) block).canConnectFenceTo(world, x, y, z + 1);

    if (!flag1 && !flag2) {
      flag1 = true;
    }

    f = 0.4375F;
    f1 = 0.5625F;
    float f2 = 0.75F;
    float f3 = 0.9375F;
    float f4 = flag3 ? 0.0F : f;
    float f5 = flag4 ? 1.0F : f1;
    float f6 = flag5 ? 0.0F : f;
    float f7 = flag6 ? 1.0F : f1;
    renderer.field_152631_f = true;

    if (flag1) {
      renderer.setRenderBounds((double) f4, (double) f2, (double) f, (double) f5, (double) f3, (double) f1);
      renderer.renderStandardBlock(block, x, y, z);
      flag = true;
    }

    if (flag2) {
      renderer.setRenderBounds((double) f, (double) f2, (double) f6, (double) f1, (double) f3, (double) f7);
      renderer.renderStandardBlock(block, x, y, z);
      flag = true;
    }

    f2 = 0.375F;
    f3 = 0.5625F;

    if (flag1) {
      renderer.setRenderBounds((double) f4, (double) f2, (double) f, (double) f5, (double) f3, (double) f1);
      renderer.renderStandardBlock(block, x, y, z);
      flag = true;
    }

    if (flag2) {
      renderer.setRenderBounds((double) f, (double) f2, (double) f6, (double) f1, (double) f3, (double) f7);
      renderer.renderStandardBlock(block, x, y, z);
      flag = true;
    }

    renderer.field_152631_f = false;
    block.setBlockBoundsBasedOnState(world, x, y, z);
    return flag;
  }

  @Override
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }

  @Override
  public int getRenderId() {
    return 0;
  }

}
