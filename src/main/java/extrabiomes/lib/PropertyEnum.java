/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import com.google.common.collect.Lists;

import extrabiomes.helpers.LogHelper;

public class PropertyEnum<T extends Enum<T> & IMetaSerializable> extends net.minecraft.block.properties.PropertyEnum<T> {
	protected final int maxSize;
	protected final T[] values;
	protected final T defaultValue;
	
	public PropertyEnum(Class<T> types, T defaultType, int maxSize) {
		super("type", types, Lists.newArrayList(types.getEnumConstants()));
		
		if (getAllowedValues().size() > maxSize)
			throw new IllegalArgumentException("Cannot have more than "+maxSize+" types in a block! Tried "+getAllowedValues().size()+'.');
		
		this.maxSize = maxSize;
		values = types.getEnumConstants();
		defaultValue = defaultType;
	}
	
	public T getDefault() {
		return defaultValue;
	}
	
	public T getForMeta(int meta) {
		if (meta < 0 || meta > maxSize) {
			LogHelper.info("Log Property received a higher meta than expected (got: %d, limit: %d)", meta, maxSize);
			return getDefault();
		} else {
			return values[meta];
		}
	}
}