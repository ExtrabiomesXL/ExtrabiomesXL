/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.terrain;

import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import extrabiomes.api.IBiomeDecoration;


import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ModLoader;

public class BiomeDecorationsMap implements
		Map<BiomeGenBase, Collection<IBiomeDecoration>> {

	public static BiomeDecorationsMap newInstance() {
		return new BiomeDecorationsMap();
	}

	private Map<BiomeGenBase, Collection<IBiomeDecoration>> map = new HashMap<BiomeGenBase, Collection<IBiomeDecoration>>();

	private BiomeDecorationsMap() {
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<Entry<BiomeGenBase, Collection<IBiomeDecoration>>> entrySet() {
		return Collections.unmodifiableSet(map.entrySet());
	}

	@Override
	public Collection<IBiomeDecoration> get(Object key) {
		if (key == null)
			return Collections.emptySet();
		Collection<IBiomeDecoration> value = map.get(key);
		if (value == null) {
			if (!(key instanceof BiomeGenBase))
				return Collections.emptySet();
			value = new ArrayList<IBiomeDecoration>();
			map.put((BiomeGenBase) key, value);
		}
		return value;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<BiomeGenBase> keySet() {
		return Collections.unmodifiableSet(map.keySet());
	}

	@Override
	public Collection<IBiomeDecoration> put(BiomeGenBase key,
			Collection<IBiomeDecoration> value) {
		ModLoader.throwException("BiomeDecorationsMap is not modifiable.",
				new UnmodifiableClassException());
		return Collections.emptySet();
	}

	@Override
	public void putAll(
			Map<? extends BiomeGenBase, ? extends Collection<IBiomeDecoration>> m) {
		ModLoader.throwException("BiomeDecorationsMap is not modifiable.",
				new UnmodifiableClassException());
	}

	@Override
	public Collection<IBiomeDecoration> remove(Object key) {
		ModLoader.throwException("BiomeDecorationsMap is not modifiable.",
				new UnmodifiableClassException());
		return Collections.emptySet();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<Collection<IBiomeDecoration>> values() {
		return Collections.unmodifiableCollection(map.values());
	}

}
