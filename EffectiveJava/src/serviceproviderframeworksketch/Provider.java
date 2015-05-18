/**
 * Illustrates an advantage from item 1: consider static factory methods instead of constructors.
 */
package serviceproviderframeworksketch;

/**
 * System registers service implementations. "provider registration API"
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public interface Provider {

	/**
	 * Creates a new service.
	 * 
	 * @return newly registered service implementation
	 */
	Service newService();

}
