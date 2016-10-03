package twg2.collections.primitiveCollections;

/** A int array {@link java.util.ListIterator} that supports {@link #previous} and {@link #set}.
 * {@link #add} and {@link #remove} are not supported.<br>
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 * This class is not thread safe, because the {@code int[]} passed to the constructor is not copied.
 * The only synchronization consideration is the internal {@code volatile modCached} counter.
 * <br><br>
 *
 * @author TeamworkGuy2
 * @since 2015-1-17
 */
@javax.annotation.Generated("StringTemplate")
public class IntArrayIterator implements IntIterator {
	private int mod = 0;
	private volatile int modCached = 0;
	private int[] col;
	private int off;
	private int size;
	private int index;


	public IntArrayIterator(int[] col, int offStartIndex) {
		this(col, 0, col.length, offStartIndex);
	}


	/** Create an iterator over a sub-set of an array.<br>
	 * NOTE: the array is not copied, modifications to the array will be reflected in this iterator.
	 * @param col the array of values to iterate over
	 * @param off the minimum index
	 * @param len maximum iterator index is {@code len + off} 
	 * @param offStartIndex the index to begin the iterator at
	 * false to thrown an {@link UnsupportedOperationException}
	 */
	public IntArrayIterator(int[] col, int off, int len, int offStartIndex) {
		this.col = col;
		this.off = off;
		this.size = off + len;
		this.index = off + offStartIndex - 1; // start at the previous index since next() is called get the first item
	}


	@Override
	public boolean hasNext() {
		checkMod();
		return index < size - 1;
	}


	@Override
	public int next() {
		checkMod();
		if(index >= size - 1) {
			throw new IllegalStateException("already at end of list");
		}
		index++;
		int val = (int)col[index];
		return val;
	}


	@Override
	public boolean hasPrevious() {
		return index > off;
	}


	@Override
	public int previous() {
		checkMod();
		if(index <= off) {
			throw new IllegalStateException("already at beginning of list");
		}
		index--;
		int val = (int)col[index];
		return val;
	}


	@Override
	public int nextIndex() {
		return index + 1 - off;
	}


	@Override
	public int previousIndex() {
		return index - 1 - off;
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("cannot modified immutable list iterator");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void set(int val) {
		throw new UnsupportedOperationException("cannot modified immutable list iterator");
	}


	/** Unsupported by this implementation
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void add(int e) {
		throw new UnsupportedOperationException("cannot modified immutable list iterator");
	}


	protected final void checkMod() {
		if(modCached != mod) {
			throw new java.util.ConcurrentModificationException();
		}
	}


	protected final void updateMod() {
		modCached = mod;
	}


	public void markModified() {
		mod++;
	}

}
