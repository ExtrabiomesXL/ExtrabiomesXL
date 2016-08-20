package extrabiomes.handlers;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import extrabiomes.module.summa.biome.ExtraBiome;

public class CanMobSpawnHandler {

  public static CanMobSpawnHandler INSTANCE = new CanMobSpawnHandler();
  
  @SubscribeEvent
  public void canSpawnEvent(CheckSpawn event) {
    Biome biome = event.getWorld().getBiomeGenForCoords(new BlockPos(event.getX(), event.getY(), event.getZ()));
    
    if(!(biome instanceof ExtraBiome)) {
      return;
    }
    
    ((ExtraBiome) biome).canSpawnEvent(event);
  }
}
