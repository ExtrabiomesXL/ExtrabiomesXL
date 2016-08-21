/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class BiomeGreenSwamp extends ExtraBiome {

  @Override
  public DecorationSettings getDecorationSettings() {
    return DecorationSettings.GREENSWAMP;
  }

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Green Swamplands");
		props.setWaterColor(0x68C474);
		props.setBaseHeight(-0.05F);
		props.setHeightVariation(0.15F);
		props.setTemperature(Biomes.SWAMPLAND.getTemperature() - 0.1F);
		props.setRainfall(Biomes.SWAMPLAND.getRainfall());
		return props;
	}

  @SuppressWarnings("unchecked")
  public BiomeGreenSwamp() {
    super(getBiomeProperties(), BiomeSettings.GREENSWAMP, Type.SWAMP, Type.WATER);

    spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
  }

  @Override
  public void canSpawnEvent(CheckSpawn event) {
    if(event.getEntity() instanceof EntitySlime && event.getY() > 50.0D && event.getY() < 70.0D && event.getWorld().rand.nextFloat() < 0.5F && event.getWorld().rand.nextFloat() < event.getWorld().getCurrentMoonPhaseFactor() && event.getWorld().getBlockLightValue(MathHelper.floor_double(event.getX()), MathHelper.floor_double(event.getY()), MathHelper.floor_double(event.getZ())) <= event.getWorld().rand.nextInt(8)) {
      AxisAlignedBB boundingBox = event.getEntityLiving().getEntityBoundingBox();
      
      if(event.getWorld().checkNoEntityCollision(boundingBox) && event.getWorld().getCollisionBoxes(event.getEntityLiving(), boundingBox).isEmpty() && !event.getWorld().containsAnyLiquid(boundingBox)) {
        // Allow the spawning
        event.setResult(Result.ALLOW);
      }
    }
  }
}
