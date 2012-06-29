
package extrabiomes.terrain;

import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.ModLoader;

public class BiomeDecorationsMap implements Map {
	public static BiomeDecorationsMap newInstance() {
		return new BiomeDecorationsMap();
	}

	private final Map	map;

	private BiomeDecorationsMap() {
		map = new HashMap();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object obj) {
		return map.containsKey(obj);
	}

	@Override
	public boolean containsValue(Object obj) {
		return map.containsValue(obj);
	}

	@Override
	public Set entrySet() {
		return Collections.unmodifiableSet(map.entrySet());
	}

	@Override
	public Collection get(Object obj) {
		if (obj == null) return Collections.emptySet();

		Object obj1 = map.get(obj);

		if (obj1 == null) {
			if (!(obj instanceof BiomeBase))
				return Collections.emptySet();

			obj1 = new ArrayList();
			map.put(obj, obj1);
		}

		return (Collection) obj1;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set keySet() {
		return Collections.unmodifiableSet(map.keySet());
	}

	public Collection put(BiomeBase biomebase, Collection collection) {
		ModLoader.throwException(
				"BiomeDecorationsMap is not modifiable.",
				new UnmodifiableClassException());
		return Collections.emptySet();
	}

	@Override
	public Object put(Object obj, Object obj1) {
		return put(obj, obj1);
	}

	@Override
	public void putAll(Map map1) {
		ModLoader.throwException(
				"BiomeDecorationsMap is not modifiable.",
				new UnmodifiableClassException());
	}

	@Override
	public Collection remove(Object obj) {
		ModLoader.throwException(
				"BiomeDecorationsMap is not modifiable.",
				new UnmodifiableClassException());
		return Collections.emptySet();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection values() {
		return Collections.unmodifiableCollection(map.values());
	}
}
