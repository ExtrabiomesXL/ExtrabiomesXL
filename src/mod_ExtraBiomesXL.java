package net.minecraft.src;

import java.util.Map;

import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.MinecraftForgeClient;
import extrabiomes.EntityScarecrow;
import extrabiomes.Extrabiomes;
import extrabiomes.ModelScarecrow;
import extrabiomes.RenderScarecrow;

public class mod_ExtraBiomesXL extends BaseMod {

	@Override
	public int addFuel(int id, int damage) {
		return Extrabiomes.INSTANCE.addFuel(id, damage);
	}

	@Override
	public String getVersion() {
		return Extrabiomes.INSTANCE.getVersion();
	}

	@Override
	public void load() {
		MinecraftForge.versionDetect("Extrabiomes XL", 3, 1, 3);
		MinecraftForgeClient.preloadTexture("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void modsLoaded() {
		Extrabiomes.INSTANCE.initialize();
		super.modsLoaded();
	}

	@Override
	public void addRenderer(Map map) {
		map.put(EntityScarecrow.class, new RenderScarecrow(
				new ModelScarecrow(), 0.4F));
	}
}
