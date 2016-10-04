package twg2.collections.primitiveCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** An {@link List} backed by an array.<br>
 * All modify methods, such as {@link #add(int) add()} and {@link #remove(int) remove()}
 * throw {@link UnsupportedOperationException} except for {@link #set(int, int) set()}
 * which maybe be enabled or disabled when an instance of this class is created.<br>
 * {@link IntArrayViewHandle} provides a way to manage an {@code ArrayView} and replace the backing
 * array without creating a new {@code ArrayView}.
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 * This class is not thread safe, since its internal state neither immutable nor synchronized.
 * The only synchronization consideration is the internal {@code volatile mod} counter.
 * <br><br>
 *
 * @see IntArrayViewHandle
 * @author TeamworkGuy2
 * @since 2014-11-29
 */
public final class IntArrayView implements IntList, java.util.RandomAccess, Iterable<Integer> {
	int[] values;
	int off;
	int len;
	volatile int mod;
	private final boolean allowSet;


	/** Create an empty array view
	 */
	public IntArrayView() {
		this(null, 0, 0);
	}


	/** Create an empty array view
	 * @param allowSet true to allow {@link #set(int, int) set()} to be called,
	 * false to throw an {@link UnsupportedOperationException} when {@code set} is called
	 */
	public IntArrayView(boolean allowSet) {
		this(null, 0, 0, false);
	}


	/** Create an array view of an entire array
	 * @param objs the array to create a view of
	 */
	public IntArrayView(int[] objs) {
		this(objs, 0, objs.length, false);
	}


	/** Create an array view of an entire array
	 * @param objs the array to create a view of
	 * @param allowSet true to allow {@link #set(int, int) set()} to be called,
	 * false to throw an {@link UnsupportedOperationException} when {@code set} is called
	 */
	public IntArrayView(int[] objs, boolean allowSet) {
		this(objs, 0, objs.length, allowSet);
	}


	/** Create an array view of a sub-portion of an array
	 * @param objs the array to create a view of
	 * @param offset the offset into {@code objs} of the array view's {@code 0th} index
	 * @param length the number of values starting at {@code offset} to include in this view
	 */
	public IntArrayView(int[] objs, int offset, int length) {
		this(objs, offset, length, false);
	}


	/** Create an array view of a sub-portion of an array
	 * @param objs the array to create a view of
	 * @param offset the offset into {@code objs} of the array view's {@code 0th} index
	 * @param length the number of values starting at {@code offset} to include in this view
	 * @param allowSet true to allow {@link #set(int, int) set()} to be called,
	 * false to throw an {@link UnsupportedOperationException} when {@code set} is called
	 */
	public IntArrayView(int[] objs, int offset, int length, boolean allowSet) {
		this.values = objs;
		this.off = offset;
		this.len = length;
		this.allowSet = allowSet;
	}


	@Override
	public int get(int index) {
		if(index > len) {
			throw new IndexOutOfBoundsException();
		}
		int obj = values[off + index];
		return obj;
	}


	@Override
	public int getLast() {
		if(len < 1) {
			throw new IndexOutOfBoundsException();
		}
		return values[off + len - 1];
	}


	@Override
	public int size() {
		return len;
	}


	@Override
	public boolean isEmpty() {
		return len == 0;
	}


	@Override
	public boolean contains(int o) {
		return indexOf(o) > -1;
	}


	@Override
	public int indexOf(int o) {
		int modCached = mod;
		int foundIdx = -1;
		for(int i = off, size = off + len; i < size; i++) {
			if(o == values[i]) {
				foundIdx = i;
				break;
			}
		}
		if(modCached != mod) {
			throw new ConcurrentModificationException();
		}
		return foundIdx;
	}


	@Override
	public int lastIndexOf(int o) {
		int modCached = mod;
		int foundIdx = -1;
		for(int i = off + len - 1; i >= off; i--) {
			if(o == values[i]) {
				foundIdx = i;
				break;
			}
		}
		if(modCached != mod) {
			throw new ConcurrentModificationException();
		}
		return foundIdx;
	}


	@Override
	public List<Integer> toList() {
		List<Integer> values = new ArrayList<>(this.len);
		IntArrayList.addToCollection(this.values, this.off, this.len, values);
		return values;
	}


	@Override
	public void addToCollection(Collection<? super Integer> dst) {
		for(int i = this.off, size = this.off + this.len; i < size; i++) {
			dst.add(this.values[i]);
		}
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public IntArrayList copy() {
		return new IntArrayList(toArray());
	}


	@Override
	public int[] toArray() {
		return Arrays.copyOfRange(values, off, off + len);
	}


	@Override
	public int[] toArray(int[] dst, int dstOffset) {
		System.arraycopy(values, off, dst, dstOffset, len);
		return dst;
	}


	public boolean containsAll(Collection<? extends Integer> c) {
		int modCached = mod;
		for(Integer obj : c) {
			if(!contains(obj)) {
				return false;
			}
		}
		if(modCached != mod) {
			throw new ConcurrentModificationException();
		}
		return true;
	}


	@Override
	public Iterator<Integer> iterator() {
		return listIterator(0);
	}


	public ListIterator<Integer> listIterator() {
		return listIterator(0);
	}


	public ListIterator<Integer> listIterator(int idx) {
		return new IntIteratorWrapper(new IntArrayIterator(values, off, len, idx));
	}


	@Override
	public int sum() {
		return sum(this);
	}


	@Override
	public float average() {
		return average(this);
	}


	@Override
	public int max() {
		return max(this);
	}


	@Override
	public int min() {
		return min(this);
	}


	@Override
	public String toString() {
		StringBuilder strB = new StringBuilder();
		toString(strB);
		return strB.toString();
	}


	@Override
	public void toString(Appendable dst) {
		int modCached = mod;
		try {
			dst.append('[');
			if(len > 0) {
				int count = off + len - 1;
				for(int i = off; i < count; i++) {
					dst.append(Integer.toString(values[i]));
					dst.append(", ");
				}
				dst.append(Integer.toString(values[count]));
			}
			dst.append(']');
		} catch(java.io.IOException ioe) {
			throw new java.io.UncheckedIOException(ioe);
		}

		if(modCached != mod) {
			throw new ConcurrentModificationException();
		}
	}


	public int set(int index, int element) {
		if(!allowSet) {
			throw new UnsupportedOperationException("cannot modified immutable view");
		}
		if(index < 0 || index >= len) {
			throw new IndexOutOfBoundsException(index + " of view size " + len);
		}
		int oldVal = values[off + index];
		values[off + index] = element;
		mod++;
		return oldVal;
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void add(int e) {
		throw new UnsupportedOperationException("cannot modified immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	public void add(int index, int element) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addAll(int... items) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addAll(int[] items, int off, int len) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addAll(IntList coll) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public int remove(int index) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean removeValue(int o) {
		throw new UnsupportedOperationException("cannot modified immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void clear() {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void removeRange(int offset, int length) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean addAll(Collection<? extends Integer> coll) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	public static final float average(IntArrayView list) {
		int modCached = list.mod;
		float res = list.len > 0 ? (float)sum(list) / list.len : 0;
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}


	public static final int max(IntArrayView list) {
		int modCached = list.mod;
		int res = IntArrayList.max(list.values, list.off, list.len);
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}


	public static final int min(IntArrayView list) {
		int modCached = list.mod;
		int res = IntArrayList.min(list.values, list.off, list.len);
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}


	public static final int sum(IntArrayView list) {
		int modCached = list.mod;
		int res = IntArrayList.sum(list.values, list.off, list.len);
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}

}
