package twg2.collections.primitiveCollections;

import java.util.List;

/** An interface for class that wrap int arrays.  This interface provides
 * methods for getting values from the Integer array.
 * @author TeamworkGuy2
 * @since 2015-5-24
 */
@javax.annotation.Generated("StringTemplate")
public interface IntListReadOnly extends java.util.RandomAccess, IntSearchable {

	/** Create a copy of this int list
	 * @return a copy of this int list
	 */
	public IntListReadOnly copy();


	/** Get the int at the specified index
	 * @param index the index within the range {@code [0, }{@link #size()}{@code -1]} inclusive to retrieve
	 * @return the int found at the specified index
	 */
	public int get(int index);


	/**
	 * @return the last value in this list
	 * @throws ArrayIndexOutOfBoundsException if this list is empty
	 */
	public int getLast();


	/** Check if the specified values is contained in this list of ints
	 * @param value the value to check for in this list
	 * @return true if the value was found in the list, false otherwise
	 */
	@Override
	public boolean contains(int value);


	/** Find the first occurring index of the specified int in this list
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value
	 * is found, or -1 if the value cannot be found
	 */
	@Override
	public int indexOf(int value);


	/** Find the last occurring index of the specified int in this list
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value
	 * is found, or -1 if the value cannot be found
	 */
	@Override
	public int lastIndexOf(int value);


	/** Get the current size of this collection
	 * @return the size of this collection
	 */
	@Override
	public int size();


	/** Check if this collection is empty
	 * @return true if this has zero elements, false otherwise
	 */
	@Override
	public boolean isEmpty();


	/**
	 * @return the mathematical average of this list of ints
	 */
	public float average();


	/**
	 * @return the maximum int (closest to positive infinity) value in this list
	 */
	public int max();


	/**
	 * @return the minimum int (closest to negative infinity) value in this list
	 */
	public int min();


	/**
	 * @return the mathematical sum of this list of ints
	 */
	public int sum();


	/** Copy this list's elements into a new {@code int[]} array
	 * @return an array of length {@link #size()} containing a copy of this list's elements
	 */
	public int[] toArray();


	/** Copy this list's elements into a new {@code int[]} array
	 * @param dst the array to copy the elements into, throws an exception if the array is too small
	 * @param dstOffset a 0-based {@code dst} offset at which to start copying elements into
	 * @return the {@code dst} input array
	 */
	public int[] toArray(int[] dst, int dstOffset);


	/** Create a {@link Integer} collection contain a copy of this collection's values
	 */
	public List<Integer> toList();


	@Override
	public String toString();


	/** Equivalent to invoking toString() and appending the resulting string in the {@link Appendable} {@code dst} parameter.<br>
	 * NOTE: implementations SHOULD optimized this method to reduce StringBuilder garbage objects
	 */
	public void toString(Appendable dst);

}
