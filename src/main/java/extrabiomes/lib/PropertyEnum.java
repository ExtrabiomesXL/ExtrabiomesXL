/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.state.IBlockState;

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
		if (meta < 0 || meta >= maxSize) {
			//LogHelper.info("Property Enum received a higher meta than expected (got: %d, limit: %d)", meta, maxSize);
			//When registering blocks that happens normally ^
			return getDefault();
		} else {
			return values[meta];
		}
	}

	public List<IBlockState> getTypeStates(IBlockState defaultState) {
		Collection<T> states = getAllowedValues();
		List<IBlockState> out = new ArrayList<IBlockState>(states.size());

		for (T type : states) {
			out.add(defaultState.withProperty(this, type));
		}

		return out;
	}
}