/**
 * Illustrates an advantage from item 1: consider static factory methods instead of constructors.
 */
package creatinganddestroyingobjects.serviceproviderframeworksketch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Offers service registration and access. "service access API"
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class Services {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private Services() {
	}

	/** Map service names to services */
	private static final Map<String, Provider> PROVIDERS = new ConcurrentHashMap<String, Provider>();
	/** Default provider name used if none was provided */
	public static final String DEFAULT_PROVIDER_NAME = "<def>";

	// Provider registration API

	/**
	 * Adds a new provider to the system with a default name.
	 * 
	 * @param p
	 *            New provider of service added to system.
	 */
	public static void registerDefaultProvider(Provider p) {
		registerProvider(DEFAULT_PROVIDER_NAME, p);
	}

	/**
	 * Adds a new provider to the system with a parameter name.
	 * 
	 * @param name
	 *            Name of provider parameter.
	 * @param p
	 *            New provider of service added to system.
	 */
	public static void registerProvider(String name, Provider p) {
		PROVIDERS.put(name, p);
	}

	// Service access API

	/**
	 * Returns a unique instance of a service by a default provider.
	 * 
	 * @return default service
	 */
	public static Service newInstance() {
		return newInstance(DEFAULT_PROVIDER_NAME);
	}

	/**
	 * Returns a unique instance of a service provided by a named provider.
	 * 
	 * @param name
	 *            Name of provider which offers service.
	 * @return non-default service
	 */
	public static Service newInstance(String name) {
		Provider p = PROVIDERS.get(name);
		if (p == null) {
			throw new IllegalArgumentException(
					"No provider registered with name: " + name);
		}
		return p.newService();
	}

}
