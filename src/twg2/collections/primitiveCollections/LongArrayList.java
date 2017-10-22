package twg2.collections.primitiveCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/** A primitive long implementation of an {@link ArrayList}.<br> 
 * The {@link #get(int) get()} and {@link #add(long) add()} operations are O(1).
 * The {@link #remove(int) remove()}, {@link #removeValue(long) remove()}, and
 * {@link #contains(long) contains()} operations are approximately O(n).<br>
 * This class' purpose is to provide minor performance and memory usage improvements over an
 * {@code ArrayList<Long>} by storing the Longs as type <code>long</code>
 * without converting them to Long.
 * This simplifies comparison operations such as {@link #contains(long) contains()}
 * which can use {@code ==} instead of {@code .equals()}
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 * This class is not thread safe, since its internal state is not synchronized.
 * The only synchronization consideration is the internal {@code volatile mod} counter.
 * <br><br>
 *
 * @see LongBag
 * @see LongListSorted
 *
 * @author TeamworkGuy2
 * @since 2013-1-20
 */
@javax.annotation.Generated("StringTemplate")
public class LongArrayList implements LongList, RandomAccess, Iterable<Long> {
	protected volatile int mod;
	protected long[] data;
	protected int size;


	/** Create a list of items with a default size of 16
	 */
	public LongArrayList() {
		this(16);
	}


	/** Create a list of items with the specified size as the starting size
	 * @param capacity the initial size capacity of the list of items
	 */
	public LongArrayList(int capacity) {
		this.data = new long[capacity];
		this.size = 0;
	}


	/** Create a list of items containing a copy of the specified values
	 * @param values the list of values to copy
	 */
	public LongArrayList(long[] values) {
		this(values, 0, values.length);
	}


	/** Create a list of items containing a copy of the specified values
	 * @param values the list of values to copy
	 * @param off the offset into {@code values} at which to start copying
	 * @param len the number of elements to copy from {@code values}
	 */
	public LongArrayList(long[] values, int off, int len) {
		if(values == null || len < 0 || off + len > values.length) {
			throw new IllegalArgumentException("offset (" + off + ") + length (" + len + ") must specify a valid range within"
					+ " values array (length: " + (values != null ? values.length : "null array") + ")");
		}
		this.data = new long[len];
		System.arraycopy(values, off, this.data, 0, len);
		this.size = len;
	}


	/** Create a new copy of a primitive list
	 * @param list the primitive list to copy
	 */
	public LongArrayList(LongList list) {
		this(list.size());

		final int size = list.size();
		for(int i = 0; i < size; i++) {
			this.data[i] = list.get(i);
		}
		this.size = size;
	}


	@Override
	public LongArrayList copy() {
		LongArrayList newList = new LongArrayList(data.length);
		newList.size = size;
		System.arraycopy(data, 0, newList.data, 0, size);
		return newList;
	}


	/** Get the long at the specified index
	 * @param index the index between {@code [0, }{@link #size()}{@code -1} inclusive to retrieve
	 * @return the long found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code -1]}
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


	/** Check if the specified values is contained in this list of longs
	 * @param value the value to check for in this list
	 * @return true if the value was found in the list, false otherwise
	 */
	@Override
	public boolean contains(long value) {
		return indexOf(value) > -1;
	}


	/** Find the first occurring index of the specified value in this list
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value is
	 * found, or -1 if the value cannot be found
	 */
	@Override
	public int indexOf(long value) {
		for(int i = 0; i < size; i++) {
			if(value == data[i]) {
				return i;
			}
		}
		return -1;
	}


	/** Find the first occurring index of the specified value in this list,
	 * starting at the specified offset
	 * @param value the value to search for in this list
	 * @param fromIndex an index within the range {@code [0, }{@link #size()}{@code -1]}
	 * at which to start searching for the {@code value}, inclusive
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value is
	 * found, or -1 if the value cannot be found
	 */
	public int indexOf(long value, int fromIndex) {
		for(int i = fromIndex; i < size; i++) {
			if(value == data[i]) {
				return i;
			}
		}
		return -1;
	}


	/** Find the last occurring index of the specified value in this list
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value is
	 * found, or -1 if the value cannot be found
	 */
	@Override
	public int lastIndexOf(long value) {
		for(int i = size - 1; i > -1; i--) {
			if(value == data[i]) {
				return i;
			}
		}
		return -1;
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


	@Override
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


	/** Remove the specified value from this list
	 * @param item the value to remove
	 * @return true if the value was found and removed successfully, false otherwise
	 */
	@Override
	public boolean removeValue(long item) {
		for(int i = 0; i < size; i++) {
			if(item == data[i]) {
				mod++;
				if(i < size - 1) {
					System.arraycopy(data, i + 1, data, i, size - i - 1);
				}
				size--;
				return true;
			}
		}
		return false;
	}


	/** Add the specified item to this list
	 * @param item the item to add
	 */
	@Override
	public void add(long item) {
		mod++;
		if(size >= data.length) {
			expandList();
		}
		data[size] = item;
		size++;
	}


	/** Add the specified item to this list of elements
	 * @param index the index at which to insert the value
	 * @param value the value to add to this list of elements
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code )}
	 */
	public void add(int index, long value) {
		if(index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		mod++;
		if(size >= data.length) {
			expandList();
		}
		// shift items up to make room for the new item
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = value;
		size++;
	}


	@Override
	@SafeVarargs
	public final void addAll(long... items) {
		addAll(items, 0, items.length);
	}


	/** Add the specified sub-array of items to this list
	 * @param items the array of items
	 * @param off the offset into {@code items} at which to start adding items to this list
	 * @param len the number of {@code items}, starting at index {@code off}, to add to this list
	 */
	@Override
	public void addAll(long[] items, int off, int len) {
		if(len < 0) {
			throw new IllegalArgumentException("number of elements to add must not be negative (" + len + ")");
		}
		mod++;
		if(size + len > data.length) {
			expandList(size + len);
		}

		// Add the new items
		System.arraycopy(items, off, data, size, len);

		size += len;
	}


	@Override
	public void addAll(LongList coll) {
		mod++;
		int collSize = coll.size();
		if(size + collSize > data.length) {
			expandList(size + collSize);
		} 
		coll.toArray(data, size);
		this.size += collSize;
	}


	/** Add an {@link Iterable} of {@link Long} objects to this list
	 * @param items the collection of items
	 * @return true if all the items are added successfully, false some items were not added (for example, if some of the values in the iterable where null)
	 */
	@Override
	public boolean addValues(Iterable<? extends Long> items) {
		if(!(items instanceof Collection)) {
			if(items instanceof LongList) {
				addAll((LongList)items);
				return true;
			}
			for(Long item : items) {
				add(item);
			}
			return true;
		}
		Collection<? extends Long> coll = (Collection<? extends Long>)items;
		int len = coll.size();
		if(size + len > data.length) {
			expandList(size + len);
		}
		mod++;
		// Add the new items, skip creating an iterator if the collection is a random access list, just use get(int)
		if(coll instanceof List && coll instanceof RandomAccess) {
			List<? extends Long> itemsList = (List<? extends Long>)coll;
			for(int i = 0; i < len; i++) {
				data[size + i] = itemsList.get(i);
			}
		}
		else {
			int i = 0;
			for(Long item : coll) {
				data[size + i] = item;
				i++;
			}
		}
		size += len;
		return true;
	}


	/** Set the long at the specified index
	 * @param index the index to store the item in
	 * Valid index range {@code [0, }{@link #size()}{@code -1]}
	 * @param value the long to store at the specified index in this list.
	 * @return the previous long at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is not within the specified range
	 */
	public long set(int index, long value) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		mod++;
		long oldValue = data[index];
		data[index] = value;
		return oldValue;
	}


	/** Clear this list. After calling this method {@link #size()} returns 0
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


	/**
	 * @return the current size of this list
	 */
	@Override
	public int size() {
		return size;
	}


	/**
	 * @return true if this list is empty ({@code size() == 0}), false otherwise
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


	@Override
	public List<Long> toList() {
		List<Long> values = new ArrayList<>(this.size);
		addToCollection(values);
		return values;
	}


	@Override
	public void addToCollection(Collection<? super Long> dst) {
		for(int i = 0; i < this.size; i++) {
			dst.add(this.data[i]);
		}
	}


	@Override
	public LongIteratorWrapper iterator() {
		return new LongIteratorWrapper(new LongArrayListIterator(this));
	}


	public LongArrayListIterator iteratorPrimitive() {
		return new LongArrayListIterator(this);
	}


	@Override
	public double average() {
		return average(this);
	}


	@Override
	public long max() {
		return max(this);
	}


	@Override
	public long min() {
		return min(this);
	}


	@Override
	public long sum() {
		return sum(this);
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


	@SafeVarargs
	public static final LongArrayList of(long... values) {
		LongArrayList inst = new LongArrayList();
		inst.addAll(values);
		return inst;
	}


	public static final LongArrayList of(Collection<Long> values) {
		return of(values, values.size());
	}


	public static final LongArrayList of(Iterable<? extends Long> values, int size) {
		LongArrayList inst = newListOfDefaultValues(size);
		long[] dat = inst.data;
		int i = 0;
		for(Long value : values) {
			dat[i] = value;
			i++;
		}
		return inst;
	}


	public static final LongArrayList newListOfDefaultValues(int length) {
		LongArrayList inst = new LongArrayList(length);
		inst.size = length;
		return inst;
	}


	public static final LongArrayList newListOfDefaultValues(int length, long defaultVal) {
		LongArrayList inst = new LongArrayList(length);
		inst.size = length;
		Arrays.fill(inst.data, 0, length, defaultVal);
		return inst;
	}


	public static final List<Long> toList(long[] values) {
		return toList(values, 0, values.length);
	}


	public static final List<Long> toList(long[] values, int off, int len) {
		List<Long> dst = new ArrayList<>(len);
		addToCollection(values, off, len, dst);
		return dst;
	}


	public static final void addToCollection(long[] values, int off, int len, Collection<Long> dst) {
		for(int i = off, size = off + len; i < size; i++) {
			dst.add(values[i]);
		}
	}


	public static final long[] toArray(Collection<? extends Long> values) {
		return toArray(values, values.size());
	}


	public static final long[] toArray(Iterable<? extends Long> values, int size) {
		long[] dst = new long[size];
		int i = 0;
		for(Long value : values) {
			dst[i] = value;
			i++;
		}
		return dst;
	}


	public static final double average(LongArrayList list) {
		return average(list.data, 0, list.size);
	}


	public static final double average(long[] vals, int off, int len) {
		return len > 0 ? (double)sum(vals, off, len) / len : 0;
	}


	public static final long max(LongArrayList list) {
		return max(list.data, 0, list.size);
	}


	public static final long max(long[] vals, int off, int len) {
		if(len < 1) {
			return 0L;
		}
		long max = Long.MIN_VALUE;
		long val = 0;
		for(int i = off, size = off + len; i < size; i++) {
			if(max < (val = vals[i])) {
				max = val;
			}
		}
		return max;
	}


	public static final long min(LongArrayList list) {
		return min(list.data, 0, list.size);
	}


	public static final long min(long[] vals, int off, int len) {
		if(len < 1) {
			return 0L;
		}
		long min = Long.MAX_VALUE;
		long val = 0;
		for(int i = off, size = off + len; i < size; i++) {
			if(min > (val = vals[i])) {
				min = val;
			}
		}
		return min;
	}


	public static final long sum(LongArrayList list) {
		return sum(list.data, 0, list.size);
	}


	public static final long sum(long[] vals, int off, int len) {
		long sum = 0;
		for(int i = off, size = off + len; i < size; i++) {
			sum += vals[i];
		}
		return sum;
	}

}
