package extrabiomes.lib;

import net.minecraft.block.Block;

import com.google.common.base.Optional;

public enum Blocks
{
    // @formatter:off
	BLOCK_LOG_SAKURA_GROVE;
    // @formatter:on
    
    private Optional<Block> block = Optional.absent();
    
    public Block get()
    {
        return block.get();
    }
    
    public boolean isPresent()
    {
        return block.isPresent();
    }
    
    public void set(Block block)
    {
        this.block = Optional.of(block);
    }
}
