package reflection;

import java.lang.reflect.InvocationTargetException;

/**
 * A class containing general generic operations for reflection.
 * 
 * @author Jacob Malter
 *
 */
public class GeneralReflection {

	/**
	 * Takes object, finds its class, returns a new instance of the class.
	 * 
	 * This implementation should ideally be called on an object with a
	 * parameterless constructor.
	 * 
	 * @param obj
	 *            object being reflected
	 * @param <T>
	 *            object type being reflected
	 * @return reflection of object with same class
	 * @throws InstantiationException
	 *             failed instantiation from {@code newInstance()}
	 * @throws IllegalAccessException
	 *             when object constructor has private access
	 * @throws InvocationTargetException
	 *             when {@code getConstructor().newInstance()} fails to create
	 *             an object.
	 * @throws NoSuchMethodException
	 *             when object has no constructor
	 */
	// Reflection cannot return accurate types, so an unsafe cast is necessary.
	// See method comments.
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(T obj) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// Uses Constructor.instance rather than Class.newInstance to wrap any
		// exception or problems with the constructor in an
		// InvocationTargetException. New instance returns type object to
		// prevent returns of primitive arrays.
		Object newobj = obj.getClass().getConstructor().newInstance();
		return (T) newobj; // unchecked cast
	}

	/**
	 * Takes an array and returns a class token for its component type.
	 * 
	 * This implementation relies on the invoking program to follow a basic
	 * contract. Firstly, the component type at compile time will be a reifiable
	 * type (Principle of Indecent Exposure). Secondly, the reified component
	 * type returned at run time must be a subtype of the reifiable component
	 * type declared at compile time (Principle of Truth in Advertising).
	 * 
	 * @param a
	 *            array containing components
	 * @param <T>
	 *            component type
	 * @return type of components
	 */
	// See method comments.
	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getComponentType(T[] a) {
		Class<?> k = a.getClass().getComponentType();

		// k must be reified typed; guaranteed by being component in array. Cast
		// is safe.
		return (Class<? extends T>) k; // unchecked cast
	}

	/**
	 * Allocates new array with component type and size specified.
	 * 
	 * @param k
	 *            component type
	 * @param size
	 *            size of array
	 * @param <T>
	 *            class type
	 * @return new array
	 * @throws IllegalArgumentException
	 *             if class argument k is primitive type
	 */
	// Reflection cannot return accurate types, so cast is necessary. See method
	// comments.
	@SuppressWarnings("unchecked")
	public static <T> T[] newArray(Class<? extends T> k, int size) {
		if (k.isPrimitive())
			throw new IllegalArgumentException("Argument cannot be primitive"
					+ k);

		// New instance returns type object to prevent returns of primitive
		// arrays. Checks for primitive arrays have already been made, so type
		// cast is safe.
		Object a = java.lang.reflect.Array.newInstance(k, size);
		return (T[]) a; // unchecked cast
	}

	/**
	 * Takes an array and size and allocates a new array with the same component
	 * type and given size.
	 * 
	 * @param a
	 *            array with components being copied
	 * @param size
	 *            size of array
	 * @param <T>
	 *            class type
	 * @return new array
	 */
	public static <T> T[] newArray(T[] a, int size) {
		return newArray(getComponentType(a), size);
	}

}
