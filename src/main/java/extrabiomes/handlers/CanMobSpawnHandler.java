package extrabiomes.handlers;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import extrabiomes.module.summa.biome.ExtrabiomeGenBase;

public class CanMobSpawnHandler {

  public static CanMobSpawnHandler INSTANCE = new CanMobSpawnHandler();
  
  @SubscribeEvent
  public void canSpawnEvent(CheckSpawn event) {
    BiomeGenBase biome = event.world.getBiomeGenForCoords((int)event.x, (int)event.z);
    
    if(!(biome instanceof ExtrabiomeGenBase)) {
      return;
    }
    
    ((ExtrabiomeGenBase) biome).canSpawnEvent(event);
  }
}
