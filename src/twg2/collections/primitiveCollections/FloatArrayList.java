package twg2.collections.primitiveCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/** A primitive float implementation of an {@link ArrayList}.<br> 
 * The {@link #get(int) get()} and {@link #add(float) add()} operations are O(1).
 * The {@link #remove(int) remove()}, {@link #removeValue(float) remove()}, and
 * {@link #contains(float) contains()} operations are approximately O(n).<br>
 * This class' purpose is to provide minor performance and memory usage improvements over an
 * {@code ArrayList<Float>} by storing the Floats as type <code>float</code>
 * without converting them to Float.
 * This simplifies comparison operations such as {@link #contains(float) contains()}
 * which can use {@code ==} instead of {@code .equals()}
 * @see FloatBag
 * @see FloatListSorted
 *
 * @author TeamworkGuy2
 * @since 2013-1-20
 */
@javax.annotation.Generated("StringTemplate")
public class FloatArrayList implements FloatList, RandomAccess, Iterable<Float> {
	protected volatile int mod;
	protected float[] data;
	protected int size;


	/** Create an unsorted group of items with a default size of 16
	 */
	public FloatArrayList() {
		this(16);
	}


	/** Create an unsorted group of items with the specified size as the starting size
	 * @param capacity the initial size of the group of items
	 */
	public FloatArrayList(int capacity) {
		this.data = new float[capacity];
		this.size = 0;
	}


	public FloatArrayList(float[] values) {
		this(values, 0, values.length);
	}


	/** Create an unsorted group of items containing a copy of the specified values
	 * @param values the list of values to copy
	 * @param off the offset into {@code values} at which to start copying
	 * @param len the number of elements to copy from {@code values}
	 */
	public FloatArrayList(float[] values, int off, int len) {
		if(values == null || len < 0 || off + len > values.length) {
			throw new IllegalArgumentException("offset (" + off + ") + length (" + len + ") must specify a valid range within"
					+ " values array (length: " + (values != null ? values.length : "null array") + ")");
		}
		this.data = new float[len];
		System.arraycopy(values, off, this.data, 0, len);
		this.size = len;
	}


	/** Create a new copy of a primitive list
	 * @param list the primitive list to copy
	 */
	public FloatArrayList(FloatList list) {
		this(list.size());

		final int size = list.size();
		for(int i = 0; i < size; i++) {
			this.data[i] = list.get(i);
		}
		this.size = size;
	}


	@Override
	public FloatArrayList copy() {
		FloatArrayList newList = new FloatArrayList(data.length);
		newList.size = size;
		System.arraycopy(data, 0, newList.data, 0, size);
		return newList;
	}


	/** Get the float at the specified index
	 * @param index the index between {@code [0, }{@link #size()}{@code -1} to retrieve
	 * @return the float found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code -1]}
	 */
	@Override
	public float get(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		return data[index];
	}


	@Override
	public float getLast() {
		if(size < 1) {
			throw new ArrayIndexOutOfBoundsException((size - 1) + " of [0, " + size + ")");
		}
		return data[size - 1];
	}


	/** Check if the specified values is contained in this list of Floats
	 * @param value the value to check for in this list
	 * @return true if the value was found in the list, false otherwise
	 */
	@Override
	public boolean contains(float value) {
		return indexOf(value) > -1;
	}


	/** Find the first occurring index of the specified value in this list
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value is
	 * found, or -1 if the value cannot be found
	 */
	@Override
	public int indexOf(float value) {
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
	public int indexOf(float value, int fromIndex) {
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
	public int lastIndexOf(float value) {
		for(int i = size - 1; i > -1; i--) {
			if(value == data[i]) {
				return i;
			}
		}
		return -1;
	}


	public void getSubArray(int dataOff, float[] dstAry, int dstOff, int len) {
		if(dataOff < 0 || dataOff + len > size) {
			throw new ArrayIndexOutOfBoundsException((dataOff < 0 ? dataOff : dataOff + len) + " of [0, " + size + ")");
		}
		System.arraycopy(data, dataOff, dstAry, dstOff, len);
	}


	/** Remove the float at the specified index
	 * @param index the index between {@code [0, }{@link #size()}{@code -1]} to remove
	 * @return the float found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range {@code [0, }{@link #size()}{@code -1]}
	 */
	@Override
	public float remove(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		float item = data[index];

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
	 * @param item the value to remove
	 * @return true if the value was found and removed successfully, false otherwise
	 */
	@Override
	public boolean removeValue(float item) {
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


	/** Add the specified item to this group of elements
	 * @param item the item to add to this group of elements
	 */
	@Override
	public void add(float item) {
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
	public void add(int index, float value) {
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
	public final void addAll(float... items) {
		addAll(items, 0, items.length);
	}


	/** Add the specified sub array of items to this group of elements
	 * @param items the array of items
	 * @param off the offset into {@code off} at which to start adding items
	 * into this group of elements
	 * @param len the number of elements, starting at index {@code off}, to
	 * add to this group of elements from {@code items}
	 */
	@Override
	public void addAll(float[] items, int off, int len) {
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
	public void addAll(FloatList coll) {
		mod++;
		int collSize = coll.size();
		if(size + collSize > data.length) {
			expandList(size + collSize);
		} 
		coll.toArray(data, size);
		this.size += collSize;
	}


	/** Add a {@link Collection} of {@link Float} objects to this group of elements
	 * @param items the collection of items
	 * @return true if all the items are added successfully, false some items were not added (for example, if some of the values in the collection where null)
	 */
	public boolean addAll(Collection<? extends Float> items) {
		int len = items.size();
		if(size + len > data.length) {
			expandList(size + len);
		}
		mod++;
		// Add the new items, skip creating an iterator if the collection is a random access list, just use get(int)
		if(items instanceof List && items instanceof RandomAccess) {
			List<? extends Float> itemsList = (List<? extends Float>)items;
			for(int i = 0; i < len; i++) {
				data[size + i] = itemsList.get(i);
			}
		}
		else {
			int i = 0;
			for(Float item : items) {
				data[size + i] = item;
				i++;
			}
		}
		size += len;
		return true;
	}


	/** Set the float at the specified index
	 * @param index the index to store the item in
	 * Valid index range {@code [0, }{@link #size()}{@code -1]}
	 * @param value the float to store at the specified index in this list.
	 * @return the previous float at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is not within the specified range
	 */
	public float set(int index, float value) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index + " of [0, " + size + ")");
		}
		mod++;
		float oldValue = data[index];
		data[index] = value;
		return oldValue;
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
			data[i] = 0f;
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
	public float[] toArray() {
		return toArray(new float[this.size], 0);
	}


	@Override
	public float[] toArray(float[] dst, int dstOffset) {
		System.arraycopy(this.data, 0, dst, dstOffset, this.size);
		return dst;
	}


	/** Warning: This function is available for performance reasons, it is highly recommended to use {@link #get(int)} or {@link #iterator()}.<br>
	 * Note: the return value may change between calls and references to the return value should only be held in contexts where complete control over parent collection modification can be ensured.
	 * @return the underlying array used by this collection, current implementations store data start at index 0 through {@link #size()} - 1
	 */
	public float[] getRawArray() {
		return this.data;
	}


	public List<Float> toList() {
		List<Float> values = new ArrayList<>(this.size);
		addToCollection(values);
		return values;
	}


	public void addToCollection(Collection<Float> dst) {
		for(int i = 0; i < this.size; i++) {
			dst.add(this.data[i]);
		}
	}


	@Override
	public FloatIteratorWrapper iterator() {
		return new FloatIteratorWrapper(new FloatArrayListIterator(this));
	}


	public FloatArrayListIterator iteratorPrimitive() {
		return new FloatArrayListIterator(this);
	}


	private final void expandList() {
		// Expand the size by 1.5x
		int len = this.data.length;
		expandList(len + (len >>> 1));
	}


	private final void expandList(int totalSize) {
		float[] oldData = this.data;
		// Expand the size by the number of new elements + 4
		// +4 rather than +1 to prevent constantly expanding a small list
		this.data = new float[totalSize + 4];
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
					dst.append(Float.toString(data[i]));
					dst.append(", ");
				}
				dst.append(Float.toString(data[sizeTemp]));
			}
			dst.append(']');
		} catch(java.io.IOException ioe) {
			throw new java.io.UncheckedIOException(ioe);
		}
	}


	public float sum() {
		return sum(this);
	}


	public float average() {
		return average(this);
	}


	public float max() {
		return max(this);
	}


	public float min() {
		return min(this);
	}


	@SafeVarargs
	public static final FloatArrayList of(float... values) {
		FloatArrayList inst = new FloatArrayList();
		inst.addAll(values);
		return inst;
	}


	public static final FloatArrayList of(Collection<Float> values) {
		return of(values, values.size());
	}


	public static final FloatArrayList of(Collection<Float> values, int size) {
		FloatArrayList inst = newListOfDefaultValues(size);
		float[] dat = inst.data;
		int i = 0;
		for(Float value : values) {
			dat[i] = value;
			i++;
		}
		return inst;
	}


	public static final FloatArrayList newListOfDefaultValues(int length) {
		FloatArrayList inst = new FloatArrayList(length);
		inst.size = length;
		return inst;
	}


	public static final FloatArrayList newListOfDefaultValues(int length, float defaultVal) {
		FloatArrayList inst = new FloatArrayList(length);
		inst.size = length;
		Arrays.fill(inst.data, 0, length, defaultVal);
		return inst;
	}


	public static final List<Float> toList(float[] values) {
		return toList(values, 0, values.length);
	}


	public static final List<Float> toList(float[] values, int off, int len) {
		List<Float> dst = new ArrayList<>(len);
		addToCollection(values, off, len, dst);
		return dst;
	}


	public static final void addToCollection(float[] values, int off, int len, Collection<Float> dst) {
		for(int i = off, size = off + len; i < size; i++) {
			dst.add(values[i]);
		}
	}


	public static final float[] toArray(Collection<? extends Float> values) {
		return toArray(values, values.size());
	}


	public static final float[] toArray(Collection<? extends Float> values, int size) {
		float[] dst = new float[size];
		int i = 0;
		for(Float value : values) {
			dst[i] = value;
			i++;
		}
		return dst;
	}


	public static final float sum(FloatArrayList list) {
		float[] values = list.data;
		float sum = 0;
		for(int i = 0, size = list.size; i < size; i++) {
			sum += values[i];
		}
		return sum;
	}


	public static final float average(FloatArrayList list) {
		return list.size > 0 ? (float)sum(list) / list.size : 0;
	}


	public static final float max(FloatArrayList list) {
		int size = list.size;
		if(size < 1) {
			return 0f;
		}
		float[] values = list.data;
		float max = Float.MIN_VALUE;
		float val = 0;
		for(int i = 0; i < size; i++) {
			if(max < (val = values[i])) {
				max = val;
			}
		}
		return max;
	}


	public static final float min(FloatArrayList list) {
		int size = list.size;
		if(size < 1) {
			return 0f;
		}
		float[] values = list.data;
		float min = Float.MAX_VALUE;
		float val = 0;
		for(int i = 0; i < size; i++) {
			if(min > (val = values[i])) {
				min = val;
			}
		}
		return min;
	}

}
