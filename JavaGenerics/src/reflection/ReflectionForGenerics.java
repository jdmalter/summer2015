package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * A class containing sereral static reflection operations for generics.
 * 
 * @author Jacob Malter
 *
 */
public class ReflectionForGenerics {

	/**
	 * Prints out a reflected class with {@code toString} methods.
	 * 
	 * This method starts by printing out the class and "(toString)" on the
	 * first line, and there is a single space between the end of the class and
	 * "(toString)" which follows.
	 * 
	 * The declared fields, constructors, and methods are printed out. This
	 * method prints each item on a new line.
	 * 
	 * At the end of the method, one last newline is added.
	 * 
	 * @param k
	 *            class being printed
	 */
	public static void toString(Class<?> k) {
		System.out.println(k + " (toString)");
		for (Field f : k.getDeclaredFields())
			System.out.println(f.toString());
		// Constructor is rawtype but class is parameterized. Not an issue since
		// constructor is simply printed.
		for (@SuppressWarnings("rawtypes")
		Constructor c : k.getDeclaredConstructors())
			System.out.println(c.toString());
		for (Method m : k.getDeclaredMethods())
			System.out.println(m.toString());
		System.out.println();
	}

	/**
	 * Prints out a reflected class with {@code toGenericString()} methods.
	 * 
	 * This method starts by printing out the class and "(toGenericString)" on
	 * the first line, and there is a single space between the end of the class
	 * and "(toGenericString)" which follows.
	 * 
	 * The declared fields, constructors, and methods are printed out. This
	 * method prints each item on a new line.
	 * 
	 * At the end of the method, one last newline is added.
	 * 
	 * @param k
	 *            class being printed
	 */
	public static void toGenericString(Class<?> k) {
		System.out.println(k + " (toGenericString)");
		for (Field f : k.getDeclaredFields())
			System.out.println(f.toGenericString());
		// Constructor is rawtype but class is parameterized. Not an issue since
		// constructor is simply printed.
		for (@SuppressWarnings("rawtypes")
		Constructor c : k.getDeclaredConstructors())
			System.out.println(c.toGenericString());
		for (Method m : k.getDeclaredMethods())
			System.out.println(m.toGenericString());
		System.out.println();
	}

	/**
	 * Runs {@code toString(k)} and {@code toGenericString(k)} on command line
	 * arguments.
	 * 
	 * @param args
	 *            Identifies classes run on by main method.
	 * @throws ClassNotFoundException
	 *             when class cannot be found
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> k = Class.forName("reflection.Cell");
		toString(k);
		toGenericString(k);
	}

}