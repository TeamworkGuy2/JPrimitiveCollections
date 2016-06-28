package twg2.collections.primitiveCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/** A primitive long implementation of an {@link ArrayList}.<br>
 * This differs from {@link LongArrayList} by maintaining the natural
 * sort order of the elements in this collection.  Which is why some operations like
 * {@code add(int index, long value)} are not available as they would break the sorted order of this collection.
 * Duplicate elements are allowed.<br>
 * The {@link #get(int) get()} and {@link #add(long) add()} operations are O(1).
 * The {@link #remove(int) remove()} and {@link #removeValue(long) remove(T)} operations are approximately O(n).
 * The {@link #contains(long) contains()} method provides O(log2 n) performance.<br/> 
 * This class' purpose is to provide minor performance and memory usage improvements over an
 * {@code ArrayList<Long>} by storing the Longs as type <code>long</code>
 * without converting them to Long.
 * @see LongArrayList
 * @see LongBag
 *
 * @author TeamworkGuy2
 * @since 2014-4-17
 */
@javax.annotation.Generated("StringTemplate")
public class LongListSorted implements LongList, RandomAccess, Iterable<Long> {
	protected volatile int mod;
	protected long[] data;
	protected int size;


	/** Create a sorted group of items with a default size of 16
	 */
	public LongListSorted() {
		this(16);
	}


	/** Create an unsorted group of items with the specified size as the starting size
	 * @param capacity the initial size of the group of items
	 */
	public LongListSorted(int capacity) {
		this.data = new long[capacity];
		this.size = 0;
	}


	@Override
	public LongListSorted copy() {
		LongListSorted newList = new LongListSorted(data.length);
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
	public long get(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		return data[index];
	}


	@Override
	public long getLast() {
		if(size < 1) {
			throw new ArrayIndexOutOfBoundsException((size - 1) + " of [0, " + size + ")");
		}
		return data[size - 1];
	}


	/** Check if the specified values is contained in this list of integers
	 * @param value the value to check for in this list
	 * @return true if the value was found in the list, false otherwise
	 */
	@Override
	public boolean contains(long value) {
		int index = Arrays.binarySearch(data, 0, size, value);
		return (index > -1 && index < size);
	}


	/** Get the index of the specified value in this sorted list if it exists
	 * @param value the value to search for in this list
	 * @return the index of the value if it ins contained in this list, else return -1
	 */
	@Override
	public int indexOf(long value) {
		int sz = this.size;
		int idx = Arrays.binarySearch(data, 0, sz, value);
		if(idx > -1) {
			long val = data[idx];
			while(idx - 1 > -1 && data[idx - 1] == val) { idx--; }
		}
		return idx > -1 ? idx : -1;
	}


	/** Get the last index of the specified value in this sorted list if it exists
	 * @param value the value to search for in this list
	 * @return the index of the value if it ins contained in this list, else return -1
	 */
	@Override
	public int lastIndexOf(long value) {
		int sz = this.size;
		int idx = Arrays.binarySearch(data, 0, sz, value);
		if(idx > -1) {
			long val = data[idx];
			while(idx + 1 < sz && data[idx + 1] == val) { idx++; }
		}
		return idx > -1 ? idx : -1;
	}


	public void getSubArray(int dataOff, long[] dstAry, int dstOff, int len) {
		if(dataOff < 0 || dataOff + len > size) {
			throw new ArrayIndexOutOfBoundsException((dataOff < 0 ? dataOff : dataOff + len) + " of [0, " + size + ")");
		}
		System.arraycopy(data, dataOff, dstAry, dstOff, len);
	}


	/** Remove the long at the specified index
	 * @param index the index between {@code [0, }{@link #size()}{@code -1]} to remove
	 * @return the long found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code -1]}
	 */
	@Override
	public long remove(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		long item = data[index];

		removeRange(index, 1);

		return item;
	}


	public void removeRange(int off, int len) {
		if(off < 0 || off + len > size) {
			throw new ArrayIndexOutOfBoundsException((off < 0 ? off : off + len) + " of [0, " + size + ")");
		}
		mod++;
		// Shift all elements above the remove element to fill the empty index
		// Copy down the remaining upper half of the array if the item removed was not the last item in the array
		if(off + len < size) {
			System.arraycopy(data, off + len, data, off, size - (off + len));
		}
		// Decrease the size because we removed items
		size -= len;
	}


	/** Remove the specified value from this group
	 * @param value the value to remove
	 * @return true if the value was found and removed successfully, false otherwise
	 */
	@Override
	public boolean removeValue(long value) {
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
	public void add(long item) {
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
	public final void addAll(long... items) {
		addAll(items, 0, items.length);
	}


	@Override
	public void addAll(long[] items, int off, int len) {
		mod++;
		for(int i = off, size = off + len; i < size; i++) {
			add(items[i]);
		}
	}


	@Override
	public void addAll(LongList coll) {
		mod++;
		int collSize = coll.size();
		// trick: copy the other (unsorted) collection's items into this collection's sorted data array above the existing item indices
		// this works because add() only expands this data array if it is too small (which we handle before hand) and never copies (and overwrites) data up by more than 1 index when inserting items
		if(size + collSize > data.length) {
			expandList(size + collSize);
		}
		int off = this.size;
		long[] itemsAry = this.data;

		coll.toArray(itemsAry, off);

		for(int i = off, size = off + collSize; i < size; i++) {
			add(itemsAry[i]);
		}
	}


	/** Add a {@link Collection} of {@link Long} objects to this group of elements
	 * @param items the collection of items
	 * @return true if all the items are added successfully, false some items were not added (for example, if some of the values in the collection where null)
	 */
	public boolean addAll(Collection<? extends Long> items) {
		boolean res = true;
		for(Long item : items) {
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
		for(int i = 0; i < size; i++) {
			data[i] = 0L;
		}
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
	public long[] toArray() {
		return toArray(new long[this.size], 0);
	}


	@Override
	public long[] toArray(long[] dst, int dstOffset) {
		System.arraycopy(this.data, 0, dst, dstOffset, this.size);
		return dst;
	}


	/** Warning: This function is available for performance reasons, it is highly recommended to use {@link #get(int)} or {@link #iterator()}.<br>
	 * Note: the return value may change between calls and references to the return value should only be held in contexts where complete control over parent collection modification can be ensured.
	 * @return the underlying array used by this collection, current implementations store data start at index 0 through {@link #size()} - 1
	 */
	public long[] getRawArray() {
		return this.data;
	}


	public List<Long> toList() {
		List<Long> values = new ArrayList<>(this.size);
		addToCollection(values);
		return values;
	}


	public void addToCollection(Collection<Long> dst) {
		for(int i = 0; i < this.size; i++) {
			dst.add(this.data[i]);
		}
	}


	@Override
	public LongIteratorWrapper iterator() {
		return new LongIteratorWrapper(new LongListSortedIterator(this));
	}


	public LongListSortedIterator iteratorPrimitive() {
		return new LongListSortedIterator(this);
	}


	private final void expandList() {
		// Expand the size by 1.5x
		int len = this.data.length;
		expandList(len + (len >>> 1));
	}


	private final void expandList(int totalSize) {
		long[] oldData = this.data;
		// Expand the size by the number of new elements + 4
		// +4 rather than +1 to prevent constantly expanding a small list
		this.data = new long[totalSize + 4];
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
					dst.append(Long.toString(data[i]));
					dst.append(", ");
				}
				dst.append(Long.toString(data[sizeTemp]));
			}
			dst.append(']');
		} catch(java.io.IOException ioe) {
			throw new java.io.UncheckedIOException(ioe);
		}
	}


	public long sum() {
		return sum(this);
	}


	public double average() {
		return average(this);
	}


	public long max() {
		return max(this);
	}


	public long min() {
		return min(this);
	}


	@SafeVarargs
	public static final LongListSorted of(long... values) {
		LongListSorted inst = new LongListSorted();
		inst.addAll(values);
		return inst;
	}


	public static final LongListSorted of(Collection<? extends Long> values) {
		return of(values, values.size());
	}


	public static final LongListSorted of(Collection<? extends Long> values, int size) {
		LongListSorted inst = new LongListSorted(size);
		for(Long value : values) {
			inst.add(value);
		}
		return inst;
	}


	public static final long sum(LongListSorted list) {
		long[] values = list.data;
		long sum = 0;
		for(int i = 0, size = list.size; i < size; i++) {
			sum += values[i];
		}
		return sum;
	}


	public static final float average(LongListSorted list) {
		return list.size > 0 ? (float)sum(list) / list.size : 0;
	}


	public static final long max(LongListSorted list) {
		if(list.size > 0) {
			return list.data[list.size() - 1];
		}
		return 0L;
	}


	public static final long min(LongListSorted list) {
		if(list.size > 0) {
			return list.data[0];
		}
		return 0L;
	}

}
