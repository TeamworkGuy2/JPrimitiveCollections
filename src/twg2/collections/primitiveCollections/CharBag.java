package twg2.collections.primitiveCollections;

/** A primitive char implementation of an {@link java.util.ArrayList ArrayList}.<br>
 * The order of elements in the bag is not preserved when items are removed.<br>
 * This allows {@link #add(char) add()}, {@link #remove(int) remove()}, and {@link #get(int) get()}
 * operations to execute in O(1). Whereas {@link #contains(char) contains()} operations
 * are approximately O(n).<br>
 * This class' purpose is to provide minor performance and memory usage improvements over an
 * {@code ArrayList<Character>} by storing the Characters as type <code>char</code>
 * without converting them to Character.
 * This simplifies comparison operations such as {@link #contains(char) contains()}
 * which can use {@code ==} instead of {@code .equals()}
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 * This class is not thread safe, see {@link CharArrayList}.
 * <br><br>
 *
 * @see CharArrayList
 * @see CharListSorted
 *
 * @author TeamworkGuy2
 * @since 2014-12-24
 */
@javax.annotation.Generated("StringTemplate")
public class CharBag extends CharArrayList implements CharList, java.util.RandomAccess {

	/** Create an unsorted group of items with a default size of 16
	 */
	public CharBag() {
		super();
	}


	/** Create an unsorted group of items with the specified size as the starting size
	 * @param capacity the initial size of the group of items
	 */
	public CharBag(int capacity) {
		super(capacity);
	}


	public CharBag(char[] values) {
		super(values, 0, values.length);
	}


	/** Create an unsorted group of items containing a copy of the specified values
	 * @param values the list of values to copy
	 * @param off the offset into {@code values} at which to start copying
	 * @param len the number of elements to copy from {@code values}
	 */
	public CharBag(char[] values, int off, int len) {
		super(values, off, len);
	}


	/** Create a new copy of a primitive list
	 * @param list the primitive list to copy
	 */
	public CharBag(CharList list) {
		super(list);
	}


	/** Remove the char at the specified index
	 * @param index the index between {@code [0, }{@link #size()}{@code -1} to remove
	 * @return the char found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code -1]}
	 */
	@Override
	public char remove(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		// Shift all elements above the remove element to fill the empty index
		else {
			char item = data[index];
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
	public boolean removeValue(char item) {
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
