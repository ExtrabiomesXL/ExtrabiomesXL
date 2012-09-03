/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import static com.google.common.base.Preconditions.checkElementIndex;

import java.io.File;
import java.util.Map;

import net.minecraft.src.Block;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigFile extends Configuration {

	private boolean	isLoaded		= false;

	private boolean	configBlocks[]	= null;

	public ConfigFile(File file) {
		super(file);
	}

	public Property getOrCreateRestrictedBlockIdProperty(String key,
			int defaultId) throws RuntimeException
	{
		checkElementIndex(defaultId, 256);
		if (configBlocks == null) {
			configBlocks = new boolean[256];

			for (int i = 0; i < configBlocks.length; ++i)
				configBlocks[i] = false;
		}

		final Map<String, Property> properties = categories
				.get(CATEGORY_BLOCK);
		if (properties.containsKey(key)) {
			final Property property = getOrCreateIntProperty(key,
					Configuration.CATEGORY_BLOCK, defaultId);
			final int value = property.getInt();
			checkElementIndex(
					value,
					configBlocks.length,
					String.format(
							"%s is used in terrain generation and must be less than 256.",
							key));
			configBlocks[value] = true;
			return property;
		} else {
			final Property property = new Property();
			properties.put(key, property);
			property.setName(key);

			if (Block.blocksList[defaultId] == null
					&& !configBlocks[defaultId])
			{
				property.value = Integer.toString(defaultId);
				configBlocks[defaultId] = true;
				return property;
			} else {
				for (int j = configBlocks.length - 1; j >= 0; --j)
					if (Block.blocksList[j] == null && !configBlocks[j])
					{
						property.value = Integer.toString(j);
						configBlocks[j] = true;
						return property;
					}

				throw new RuntimeException(String.format(
						"No more block ids available for %s.", key));
			}
		}
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	@Override
	public void load() {
		super.load();
		isLoaded = true;
	}

	@Override
	public void save() {
		super.save();
		isLoaded = false;
	}
}
