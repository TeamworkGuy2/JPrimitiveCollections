package twg2.collections.primitiveCollections;

/** A primitive long implementation of an {@link java.util.ArrayList ArrayList}.<br>
 * The order of elements in the bag is not preserved when items are removed.<br>
 * This allows {@link #add(long) add()}, {@link #remove(int) remove()}, and {@link #get(int) get()}
 * operations to execute in O(1). Whereas {@link #contains(long) contains()} operations
 * are approximately O(n).<br>
 * This class' purpose is to provide minor performance and memory usage improvements over an
 * {@code ArrayList<Long>} by storing the Longs as type <code>long</code>
 * without converting them to Long.
 * This simplifies comparison operations such as {@link #contains(long) contains()}
 * which can use {@code ==} instead of {@code .equals()}
 * @see LongArrayList
 * @see LongListSorted
 *
 * @author TeamworkGuy2
 * @since 2014-12-24
 */
@javax.annotation.Generated("StringTemplate")
public class LongBag extends LongArrayList implements LongList, java.util.RandomAccess {


	/** Create an unsorted group of items with a default size of 16
	 */
	public LongBag() {
		super();
	}


	/** Create an unsorted group of items with the specified size as the starting size
	 * @param capacity the initial size of the group of items
	 */
	public LongBag(int capacity) {
		super(capacity);
	}


	public LongBag(long[] values) {
		super(values, 0, values.length);
	}


	/** Create an unsorted group of items containing a copy of the specified values
	 * @param values the list of values to copy
	 * @param off the offset into {@code values} at which to start copying
	 * @param len the number of elements to copy from {@code values}
	 */
	public LongBag(long[] values, int off, int len) {
		super(values, off, len);
	}


	/** Create a new copy of a primitive list
	 * @param list the primitive list to copy
	 */
	public LongBag(LongList list) {
		super(list);
	}


	/** Remove the long at the specified index
	 * @param index the index between {@code [0, }{@link #size()}{@code -1} to remove
	 * @return the long found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code -1]}
	 */
	@Override
	public long remove(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		// Shift all elements above the remove element to fill the empty index
		else {
			long item = data[index];
			// move the last element in the array into this removed index
			if(index < size - 1) {
				data[index] = data[size - 1];
			}
			// Decrease the size because we removed one item
			size--;
			return item;
		}
	}


	/** Remove the specified value from this group
	 * @param item the value to remove
	 * @return true if the value was found and removed successfully, false otherwise
	 */
	@Override
	public boolean removeValue(long item) {
		for(int i = 0; i < size; i++) {
			if(item == data[i]) {
				if(i < size - 1) {
					data[i] = data[size - 1];
				}
				size--;
				return true;
			}
		}
		return false;
	}

}
