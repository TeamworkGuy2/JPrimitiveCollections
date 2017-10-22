package twg2.collections.primitiveCollections;

import java.util.Collection;
import java.util.Iterator;
import java.util.RandomAccess;

/** An interface for class that wrap double arrays.  This interface provides
 * methods for getting, setting and removing values from the Double array.
 * @author TeamworkGuy2
 * @since 2013-1-20
 */
@javax.annotation.Generated("StringTemplate")
public interface DoubleList extends RandomAccess, DoubleListReadOnly {

	@Override
	public DoubleList copy();


	/** Add the specified item to this collection
	 * @param item the item to add to this collection
	 */
	public void add(double item);


	/** Add an array of items to this collection
	 * @param items the array of items to add to this collection
	 */
	public void addAll(double... items);


	/** Add an array of items to this collection
	 * @param coll the collection of items to add to this collection
	 */
	public void addAll(DoubleList coll);


	/** Add an iterable of {@link Double} values to this collection
	 * @param coll the values to add to this collection
	 */
	public boolean addValues(Iterable<? extends Double> coll);


	/** Add the specified array of items to this collection
	 * @param items the array of items to add to this collection
	 * @param off the {@code items} offset at which to start adding items to this collection
	 * @param len the number of items to add from {@code items} into this collection
	 */
	public void addAll(double[] items, int off, int len);


	/** Add this collection of double values to the specified collection.
	 * Note: this collection is not modified.
	 * @param collDst the destination collection to copy this collection to
	 */
	public void addToCollection(Collection<? super Double> collDst);


	/** Remove the double at the specified index
	 * @param index the index within the range {@code [0, }{@link #size()}{@code -1]}
	 * inclusive to remove
	 * @return the double found at the specified index
	 */
	public double remove(int index);


	/** Remove the specified value from this list
	 * @param item the value to remove
	 * @return true if the value was found and removed successfully, false otherwise
	 */
	public boolean removeValue(double item);


	/** Remove a subset of values from this collection
	 * @param offset the 0-based index offset into this collection between {@code [0, }{@link #size()}{@code -1]} at which to start removing values
	 * @param length the number of elements to remove starting at {@code offset}
	 */
	public void removeRange(int offset, int length);


	/** Clear this list of elements
	 */
	public void clear();


	public Iterator<Double> iterator();

}
