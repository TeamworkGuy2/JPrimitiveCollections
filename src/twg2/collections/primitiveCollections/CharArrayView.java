package twg2.collections.primitiveCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** An {@link List} backed by an array.<br>
 * All modify methods, such as {@link #add(char) add()} and {@link #remove(int) remove()}
 * throw {@link UnsupportedOperationException} except for {@link #set(int, char) set()}
 * which maybe be enabled or disabled when an instance of this class is created.<br>
 * {@link CharArrayViewHandle} provides a way to manage an {@code ArrayView} and replace the backing
 * array without creating a new {@code ArrayView}.
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 * This class is not thread safe, since its internal state neither immutable nor synchronized.
 * The only synchronization consideration is the internal {@code volatile mod} counter.
 * <br><br>
 *
 * @see CharArrayViewHandle
 * @author TeamworkGuy2
 * @since 2014-11-29
 */
public final class CharArrayView implements CharList, java.util.RandomAccess, Iterable<Character> {
	char[] values;
	int off;
	int len;
	volatile int mod;
	private final boolean allowSet;


	/** Create an empty array view
	 */
	public CharArrayView() {
		this(null, 0, 0);
	}


	/** Create an empty array view
	 * @param allowSet true to allow {@link #set(int, char) set()} to be called,
	 * false to throw an {@link UnsupportedOperationException} when {@code set} is called
	 */
	public CharArrayView(boolean allowSet) {
		this(null, 0, 0, false);
	}


	/** Create an array view of an entire array
	 * @param objs the array to create a view of
	 */
	public CharArrayView(char[] objs) {
		this(objs, 0, objs.length, false);
	}


	/** Create an array view of an entire array
	 * @param objs the array to create a view of
	 * @param allowSet true to allow {@link #set(int, char) set()} to be called,
	 * false to throw an {@link UnsupportedOperationException} when {@code set} is called
	 */
	public CharArrayView(char[] objs, boolean allowSet) {
		this(objs, 0, objs.length, allowSet);
	}


	/** Create an array view of a sub-portion of an array
	 * @param objs the array to create a view of
	 * @param offset the offset into {@code objs} of the array view's {@code 0th} index
	 * @param length the number of values starting at {@code offset} to include in this view
	 */
	public CharArrayView(char[] objs, int offset, int length) {
		this(objs, offset, length, false);
	}


	/** Create an array view of a sub-portion of an array
	 * @param objs the array to create a view of
	 * @param offset the offset into {@code objs} of the array view's {@code 0th} index
	 * @param length the number of values starting at {@code offset} to include in this view
	 * @param allowSet true to allow {@link #set(int, char) set()} to be called,
	 * false to throw an {@link UnsupportedOperationException} when {@code set} is called
	 */
	public CharArrayView(char[] objs, int offset, int length, boolean allowSet) {
		this.values = objs;
		this.off = offset;
		this.len = length;
		this.allowSet = allowSet;
	}


	@Override
	public char get(int index) {
		if(index > len) {
			throw new IndexOutOfBoundsException();
		}
		char obj = values[off + index];
		return obj;
	}


	@Override
	public char getLast() {
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
	public boolean contains(char o) {
		return indexOf(o) > -1;
	}


	@Override
	public int indexOf(char o) {
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
	public int lastIndexOf(char o) {
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
	public List<Character> toList() {
		List<Character> values = new ArrayList<>(this.len);
		CharArrayList.addToCollection(this.values, this.off, this.len, values);
		return values;
	}


	@Override
	public void addToCollection(Collection<? super Character> dst) {
		for(int i = this.off, size = this.off + this.len; i < size; i++) {
			dst.add(this.values[i]);
		}
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public CharArrayList copy() {
		return new CharArrayList(toArray());
	}


	@Override
	public char[] toArray() {
		return Arrays.copyOfRange(values, off, off + len);
	}


	@Override
	public char[] toArray(char[] dst, int dstOffset) {
		System.arraycopy(values, off, dst, dstOffset, len);
		return dst;
	}


	public boolean containsAll(Collection<? extends Character> c) {
		int modCached = mod;
		for(Character obj : c) {
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
	public Iterator<Character> iterator() {
		return listIterator(0);
	}


	public ListIterator<Character> listIterator() {
		return listIterator(0);
	}


	public ListIterator<Character> listIterator(int idx) {
		return new CharIteratorWrapper(new CharArrayIterator(values, off, len, idx));
	}


	@Override
	public char sum() {
		return sum(this);
	}


	@Override
	public float average() {
		return average(this);
	}


	@Override
	public char max() {
		return max(this);
	}


	@Override
	public char min() {
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
					dst.append(Character.toString(values[i]));
					dst.append(", ");
				}
				dst.append(Character.toString(values[count]));
			}
			dst.append(']');
		} catch(java.io.IOException ioe) {
			throw new java.io.UncheckedIOException(ioe);
		}

		if(modCached != mod) {
			throw new ConcurrentModificationException();
		}
	}


	public char set(int index, char element) {
		if(!allowSet) {
			throw new UnsupportedOperationException("cannot modified immutable view");
		}
		if(index < 0 || index >= len) {
			throw new IndexOutOfBoundsException(index + " of view size " + len);
		}
		char oldVal = values[off + index];
		values[off + index] = element;
		mod++;
		return oldVal;
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void add(char e) {
		throw new UnsupportedOperationException("cannot modified immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	public void add(int index, char element) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addAll(char... items) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addAll(char[] items, int off, int len) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addAll(CharList coll) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public char remove(int index) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean removeValue(char o) {
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
	public boolean addValues(Iterable<? extends Character> coll) {
		throw new UnsupportedOperationException("cannot modify immutable view");
	}


	public static final float average(CharArrayView list) {
		int modCached = list.mod;
		float res = list.len > 0 ? (float)sum(list) / list.len : 0;
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}


	public static final char max(CharArrayView list) {
		int modCached = list.mod;
		char res = CharArrayList.max(list.values, list.off, list.len);
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}


	public static final char min(CharArrayView list) {
		int modCached = list.mod;
		char res = CharArrayList.min(list.values, list.off, list.len);
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}


	public static final char sum(CharArrayView list) {
		int modCached = list.mod;
		char res = CharArrayList.sum(list.values, list.off, list.len);
		if(modCached != list.mod) {
			throw new ConcurrentModificationException();
		}
		return res;
	}

}
