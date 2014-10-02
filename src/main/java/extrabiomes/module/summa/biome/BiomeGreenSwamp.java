/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeGreenSwamp extends ExtrabiomeGenBase {

  @Override
  public DecorationSettings getDecorationSettings() {
    return DecorationSettings.GREENSWAMP;
  }

  @SuppressWarnings("unchecked")
  public BiomeGreenSwamp() {
    super(BiomeSettings.GREENSWAMP, Type.SWAMP, Type.WATER);

    setColor(0x68C474);
    setBiomeName("Green Swamplands");
    temperature = BiomeGenBase.swampland.temperature - 0.1F;
    rainfall = BiomeGenBase.swampland.rainfall;
    this.setHeight(new Height(-0.05F, 0.15F));
    spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
  }

  @Override
  public void canSpawnEvent(CheckSpawn event) {
    if(event.entity instanceof EntitySlime && event.y > 50.0D && event.y < 70.0D && event.world.rand.nextFloat() < 0.5F && event.world.rand.nextFloat() < event.world.getCurrentMoonPhaseFactor() && event.world.getBlockLightValue(MathHelper.floor_double(event.x), MathHelper.floor_double(event.y), MathHelper.floor_double(event.z)) <= event.world.rand.nextInt(8)) {
      AxisAlignedBB boundingBox = event.entityLiving.boundingBox;
      
      if(event.world.checkNoEntityCollision(boundingBox) && event.world.getCollidingBoundingBoxes(event.entityLiving, boundingBox).isEmpty() && !event.world.isAnyLiquid(boundingBox)) {
        // Allow the spawning
        event.setResult(Result.ALLOW);
      }
    }
  }
}
