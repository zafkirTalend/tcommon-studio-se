package routines.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class XMLGroupCache {

	private Map<Key, Element> cache;

	private static Map<String, XMLGroupCache> instanceCache = new HashMap<>();

	public synchronized static XMLGroupCache getInstance(String instanceKey) {
		XMLGroupCache instance = instanceCache.get(instanceKey);
		if (instance == null) {
			instance = new XMLGroupCache();
			instanceCache.put(instanceKey, instance);
		}
		return instance;
	}

	public synchronized static void release(String instanceKey) {
		XMLGroupCache instance = instanceCache.get(instanceKey);
		if (instance != null) {
			instanceCache.remove(instanceKey);
		}
	}

	private XMLGroupCache() {
		cache = new HashMap<>();
	}

	public class Key {
		String path;
		Map<String, String> defaultNamespaceUriTOPrefix;
		Map<String, String> declaredNamespacesMapping;
		List<String> values;

		public int hashCode() {
			int hash = path.hashCode(); 
			
			hash = 31 * hash + (defaultNamespaceUriTOPrefix == null ? 0 : defaultNamespaceUriTOPrefix.hashCode());
			hash = 31 * hash + (declaredNamespacesMapping == null ? 0 : declaredNamespacesMapping.hashCode());
			hash = 31 * hash + (values == null ? 0 : values.hashCode());
			
			return hash;
		}

		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}

			if (!(obj instanceof Key)) {
				return false;
			}

			Key key = (Key) obj;
			if (!path.equals(key.path)) {
				return false;
			}

			if (!equalMap(defaultNamespaceUriTOPrefix, key.defaultNamespaceUriTOPrefix)) {
				return false;
			}

			if (!equalMap(declaredNamespacesMapping, key.declaredNamespacesMapping)) {
				return false;
			}

			if (equalList(values, key.values)) {
				return true;
			}

			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	private boolean equalMap(Map m1, Map m2) {
		if (m1 == m2) {
			return true;
		}

		if (isEmptyOrNull(m1) && isEmptyOrNull(m2)) {
			return true;
		}

		if (m1 != null && m2 != null) {
			return m1.equals(m2);
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	private boolean isEmptyOrNull(Map m) {
		return m == null || m.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	private boolean equalList(List l1, List l2) {
		if (l1 == l2) {
			return true;
		}

		if (isEmptyOrNull(l1) && isEmptyOrNull(l2)) {
			return true;
		}

		if (l1 != null && l2 != null) {
			return l1.equals(l2);
		}

		return false;

	}

	@SuppressWarnings("rawtypes")
	private boolean isEmptyOrNull(List l) {
		return l == null || l.isEmpty();
	}

	public Key createKey(String path, Map<String, String> defaultNamespaceUriTOPrefix,
			Map<String, String> declaredNamespacesMapping, List<String> values) {
		Key key = new Key();

		key.path = path;
		key.defaultNamespaceUriTOPrefix = defaultNamespaceUriTOPrefix;
		key.declaredNamespacesMapping = declaredNamespacesMapping;
		key.values = values;

		return key;
	}

	public void put(Key key, Element value) {
		cache.put(key, value);
	}

	public Element get(Key key) {
		return cache.get(key);
	}

}
