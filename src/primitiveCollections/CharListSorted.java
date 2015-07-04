package primitiveCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/** A primitive char implementation of an {@link ArrayList}.<br>
 * This differs from {@link CharArrayList} by maintaining the natural
 * sort order of the elements in this collection.  Which is why some operations like
 * {@code add(int index, char value)} are not available as they would break the sorted order of this collection.
 * Duplicate elements are allowed.<br>
 * The {@link #get(int) get()} and {@link #add(char) add()} operations are O(1).
 * The {@link #remove(int) remove()} and {@link #removeValue(char) remove(T)} operations are approximately O(n).
 * The {@link #contains(char) contains()} method provides O(log2 n) performance.<br/> 
 * This class' purpose is to provide minor performance and memory usage improvements over an
 * {@code ArrayList<Character>} by storing the Characters as type <code>char</code>
 * without converting them to Character.
 * @see CharArrayList
 * @see CharBag
 *
 * @author TeamworkGuy2
 * @since 2014-4-17
 */
@javax.annotation.Generated("StringTemplate")
public class CharListSorted implements CharList, RandomAccess, Iterable<Character> {
	protected volatile int mod;
	protected char[] data;
	protected int size;


	/** Create a sorted group of items with a default size of 16
	 */
	public CharListSorted() {
		this(16);
	}


	/** Create an unsorted group of items with the specified size as the starting size
	 * @param capacity the initial size of the group of items
	 */
	public CharListSorted(int capacity) {
		this.data = new char[capacity];
		this.size = 0;
	}


	@Override
	public CharListSorted copy() {
		CharListSorted newList = new CharListSorted(data.length);
		newList.size = size;
		System.arraycopy(data, 0, newList.data, 0, size);
		return newList;
	}


	/** Get the integer at the specified index
	 * @param index the index between zero and {@link #size()}-1 inclusive to retrieve
	 * @return the integer found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range [0, {@link #size()}-1]
	 */
	@Override
	public char get(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		return data[index];
	}


	@Override
	public char getLast() {
		if(size < 1) {
			throw new ArrayIndexOutOfBoundsException((size - 1) + " of [0, " + size + ")");
		}
		return data[size - 1];
	}


	/** Get the index of the specified value in this sorted list if it exists
	 * @param value the value to search for in this list
	 * @return the index of the value if it ins contained in this list, else return -1
	 */
	@Override
	public int indexOf(char value) {
		int index = Arrays.binarySearch(data, 0, size, value);
		return index > -1 ? index : -1;
	}


	/** Check if the specified values is contained in this list of integers
	 * @param value the value to check for in this list
	 * @return true if the value was found in the list, false otherwise
	 */
	@Override
	public boolean contains(char value) {
		// Search for the item to remove
		int index = Arrays.binarySearch(data, 0, size, value);
		return (index > -1 && index < size);
	}


	public void getSubArray(int dataOff, char[] dstAry, int dstOff, int len) {
		if(dataOff < 0 || dataOff + len > size) {
			throw new ArrayIndexOutOfBoundsException((dataOff < 0 ? dataOff : dataOff + len) + " of [0, " + size + ")");
		}
		System.arraycopy(data, dataOff, dstAry, dstOff, len);
	}


	/** Remove the char at the specified index
	 * @param index the index between {@code [0, }{@link #size()}{@code -1]} to remove
	 * @return the char found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code -1]}
	 */
	@Override
	public char remove(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		// Shift all elements above the remove element to fill the empty index
		// Get the item to remove
		char item = data[index];

		removeRange(index, 1);

		return item;
	}


	public void removeRange(int off, int len) {
		if(off < 0 || off + len > size) {
			throw new ArrayIndexOutOfBoundsException((off < 0 ? off : off + len) + " of [0, " + size + ")");
		}
		mod++;
		// Shift all elements above the remove element to fill the empty index
		// Get the item to remove
		// Copy down the remaining upper half of the array if the item removed was not the last item in the array
		if(off + len < size) {
			System.arraycopy(data, off + len, data, off, size - (off + len));
		}
		// Decrease the size because we removed one item
		size -= len;
	}


	/** Remove the specified value from this group
	 * @param value the value to remove
	 * @return true if the value was found and removed successfully, false otherwise
	 */
	@Override
	public boolean removeValue(char value) {
		// Search for the item to remove
		int index = Arrays.binarySearch(data, 0, size, value);
		if(index > -1 && index < size) {
			mod++;
			System.arraycopy(data, index + 1, data, index, size - index - 1);
			size--;
			return true;
		}
		return false;
	}


	/** Add the specified item to this group of elements
	 * @param item the item to add to this group of elements
	 */
	@Override
	public void add(char item) {
		// If the list is to small, expand it
		if(size == data.length) {
			expandList();
		}
		mod++;
		// Add the new item
		int index = Arrays.binarySearch(data, 0, size, item);
		if(index < 0) { index = -index - 1; }
		if(index > size) { index = size; }
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = item;
		size++;
	}


	@Override
	@SafeVarargs
	public final void addAll(char... items) {
		addAll(items, 0, items.length);
	}


	@Override
	public void addAll(char[] items, int off, int len) {
		mod++;
		for(int i = off, size = off + len; i < size; i++) {
			add(items[i]);
		}
	}


	@Override
	public void addAll(CharList coll) {
		mod++;
		int collSize = coll.size();
		// trick: copy the other (unsorted) collection's items into this collection's sorted data array above the existing item indices
		// this works because add() only expands this data array if it is too small (which we handle before hand) and never copies (and overwrites) data up by more than 1 index when inserting items
		if(size + collSize > data.length) {
			expandList(size + collSize);
		}
		int off = this.size;
		char[] itemsAry = this.data;

		coll.toArray(itemsAry, off);

		for(int i = off, size = off + collSize; i < size; i++) {
			add(itemsAry[i]);
		}
	}


	/** Add a {@link Collection} of {@link Character} objects to this group of elements
	 * @param items the collection of items
	 * @return true if all the items are added successfully, false some items were not added (for example, if some of the values in the collection where null)
	 */
	public boolean addAll(Collection<? extends Character> items) {
		boolean res = true;
		for(Character item : items) {
			if(item != null) {
				add(item);
			}
			res &= (item != null);
		}
		return res;
	}


	/** Clear the group of elements
	 */
	@Override
	public void clear() {
		clearFast();
	}


	public void clearFast() {
		mod++;
		size = 0;
	}


	public void clearFull() {
		mod++;
		// Clear list to null
		for(int i = 0; i < size; i++) {
			data[i] = (char)0;
		}
		// Set the size to zero
		size = 0;
	}


	/** Get the current size of this group of elements
	 * @return the size of this group of elements
	 */
	@Override
	public int size() {
		return size;
	}


	/** Is this group of elements empty
	 * @return true if this group of elements is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}


	@Override
	public char[] toArray() {
		return toArray(new char[this.size], 0);
	}


	@Override
	public char[] toArray(char[] dst, int dstOffset) {
		System.arraycopy(this.data, 0, dst, dstOffset, this.size);
		return dst;
	}


	@Override
	public CharIteratorWrapper iterator() {
		return new CharIteratorWrapper(new CharListSortedIterator(this));
	}


	public CharListSortedIterator iteratorPrimitive() {
		return new CharListSortedIterator(this);
	}


	private final void expandList() {
		// Expand the size by 1.5x
		int len = this.data.length;
		expandList(len + (len >>> 1));
	}


	private final void expandList(int totalSize) {
		char[] oldData = this.data;
		// Expand the size by the number of new elements + 4
		// +4 rather than +1 to prevent constantly expanding a small list
		this.data = new char[totalSize + 4];
		System.arraycopy(oldData, 0, this.data, 0, size);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(64);
		toString(builder);
		return builder.toString();
	}


	@Override
	public void toString(Appendable dst) {
		try {
			dst.append('[');
			if(size > 0) {
				int sizeTemp = size - 1;
				for(int i = 0; i < sizeTemp; i++) {
					dst.append(Character.toString(data[i]));
					dst.append(", ");
				}
				dst.append(Character.toString(data[sizeTemp]));
			}
			dst.append(']');
		} catch(java.io.IOException ioe) {
			throw new java.io.UncheckedIOException(ioe);
		}
	}


	public char sum() {
		return sum(this);
	}


	public float average() {
		return average(this);
	}


	public char max() {
		return max(this);
	}


	public char min() {
		return min(this);
	}


	@SafeVarargs
	public static final CharListSorted of(char... values) {
		CharListSorted inst = new CharListSorted();
		inst.addAll(values);
		return inst;
	}


	public static final CharListSorted of(Collection<? extends Character> values) {
		return of(values, values.size());
	}


	public static final CharListSorted of(Collection<? extends Character> values, int size) {
		CharListSorted inst = new CharListSorted(size);
		for(Character value : values) {
			inst.add(value);
		}
		return inst;
	}


	public static final char sum(CharListSorted list) {
		char[] values = list.data;
		char sum = 0;
		for(int i = 0, size = list.size; i < size; i++) {
			sum += values[i];
		}
		return sum;
	}


	public static final float average(CharListSorted list) {
		return (float)sum(list) / list.size;
	}


	public static final char max(CharListSorted list) {
		if(list.size > 0) {
			return list.data[list.size() - 1];
		}
		return (char)0;
	}


	public static final char min(CharListSorted list) {
		if(list.size > 0) {
			return list.data[0];
		}
		return (char)0;
	}

}
