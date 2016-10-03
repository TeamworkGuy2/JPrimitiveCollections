package twg2.collections.primitiveCollections;

import java.util.List;

/** An interface for class that wrap long arrays.  This interface provides
 * methods for getting values from the Long array.
 * @author TeamworkGuy2
 * @since 2015-5-24
 */
@javax.annotation.Generated("StringTemplate")
public interface LongListReadOnly extends java.util.RandomAccess, LongSearchable {

	/** Create a copy of this long list
	 * @return a copy of this long list
	 */
	public LongListReadOnly copy();


	/** Get the long at the specified index
	 * @param index the index within the range {@code [0, }{@link #size()}{@code -1]} inclusive to retrieve
	 * @return the long found at the specified index
	 */
	public long get(int index);


	/**
	 * @return the last value in this list
	 * @throws ArrayIndexOutOfBoundsException if this list is empty
	 */
	public long getLast();


	/** Check if the specified values is contained in this list of longs
	 * @param value the value to check for in this list
	 * @return true if the value was found in the list, false otherwise
	 */
	@Override
	public boolean contains(long value);


	/** Find the first occurring index of the specified long in this list
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value
	 * is found, or -1 if the value cannot be found
	 */
	@Override
	public int indexOf(long value);


	/** Find the last occurring index of the specified long in this list
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value
	 * is found, or -1 if the value cannot be found
	 */
	@Override
	public int lastIndexOf(long value);


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
	 * @return the mathematical average of this list of longs
	 */
	public double average();


	/**
	 * @return the maximum long (closest to positive infinity) value in this list
	 */
	public long max();


	/**
	 * @return the minimum long (closest to negative infinity) value in this list
	 */
	public long min();


	/**
	 * @return the mathematical sum of this list of longs
	 */
	public long sum();


	/** Copy this list's elements into a new {@code long[]} array
	 * @return an array of length {@link #size()} containing a copy of this list's elements
	 */
	public long[] toArray();


	/** Copy this list's elements into a new {@code long[]} array
	 * @param dst the array to copy the elements into, throws an exception if the array is too small
	 * @param dstOffset a 0-based {@code dst} offset at which to start copying elements into
	 * @return the {@code dst} input array
	 */
	public long[] toArray(long[] dst, int dstOffset);


	/** Create a {@link Long} collection contain a copy of this collection's values
	 */
	public List<Long> toList();


	@Override
	public String toString();


	/** Equivalent to invoking toString() and appending the resulting string in the {@link Appendable} {@code dst} parameter.<br>
	 * NOTE: implementations SHOULD optimized this method to reduce StringBuilder garbage objects
	 */
	public void toString(Appendable dst);

}
