package twg2.collections.primitiveCollections;

/** A long array {@link java.util.ListIterator} that supports {@link #previous} and {@link #set}.
 * {@link #add} and {@link #remove} are not supported.
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 * This class is only thread safe if the {@link LongIterator} instance passed to this class' constructor is thread safe.
 * <br><br>
 *
 * @author TeamworkGuy2
 * @since 2015-1-17
 */
@javax.annotation.Generated("StringTemplate")
public class LongIteratorWrapper implements java.util.ListIterator<Long> {
	private final LongIterator iter;


	/** Create an iterator over a set of indices
	 * @param iter the primitive iterator to iterate over
	 */
	public LongIteratorWrapper(LongIterator iter) {
		this.iter = iter;
	}


	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}


	@Override
	public Long next() {
		return iter.next();
	}


	@Override
	public boolean hasPrevious() {
		return iter.hasPrevious();
	}


	@Override
	public Long previous() {
		return iter.previous();
	}


	@Override
	public int nextIndex() {
		return iter.nextIndex();
	}


	@Override
	public int previousIndex() {
		return iter.previousIndex();
	}


	@Override
	public void remove() {
		iter.remove();
	}


	@Override
	public void set(Long val) {
		iter.set(val);
	}


	@Override
	public void add(Long val) {
		iter.add(val);
	}

}
