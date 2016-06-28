package twg2.collections.primitiveCollections;

import java.util.Map;
import java.util.RandomAccess;
import java.util.function.BiConsumer;

/** A primitive double implementation of a {@link Map}.<br>
 * @param <T> the data type of the values stored in this map
 * @see DoubleArrayList
 * @see DoubleBag
 *
 * @author TeamworkGuy2
 * @since 2015-5-25
 */
@javax.annotation.Generated("StringTemplate")
public interface DoubleMapReadOnly<T> extends RandomAccess {

	public DoubleMapReadOnly<T> copy();


	public T get(double key);


	/** Get the key at the specified index
	 * @param index the index between zero and {@link #size()}-1 inclusive to retrieve
	 * @return the key found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range [0, {@link #size()}-1]
	 */
	public double getKey(int index);


	/** Get the integer at the specified index
	 * @param index the index between zero and {@link #size()}-1 inclusive to retrieve
	 * @return the integer found at the specified index
	 * @throws ArrayIndexOutOfBoundsException if the index is outside the range [0, {@link #size()}-1]
	 */
	public T getValue(int index);


	/** Get the index of the specified key in this sorted list if it exists
	 * @param key the key to search for in this list
	 * @return the index of the key if it is contained in this list, else return -1
	 */
	public int indexOfKey(double key);


	/** Get the last index of the specified key in this sorted list if it exists
	 * @param key the key to search for in this list
	 * @return the index of the key if it is contained in this list, else return -1
	 */
	public int lastIndexOfKey(double key);


	/** Get the index of the specified value in this map if it exists
	 * @param value the value to search for in this map
	 * @return the index of the key if it is contained in this list, else return -1
	 */
	public int indexOfValue(T value);


	/** Check if the specified values is contained in this list of integers
	 * @param key the key to check for in this list
	 * @return true if the key was found in the list, false otherwise
	 */
	public boolean contains(double key);


	/** Check if the specified values is contained in this list of integers
	 * @param value the value to check for in this list
	 * @return true if the key was found in the list, false otherwise
	 */
	public boolean containsValue(T value);


	/** Get the current size of this group of elements
	 * @return the size of this group of elements
	 */
	public int size();


	/** Is this group of elements empty
	 * @return true if this group of elements is empty, false otherwise
	 */
	public boolean isEmpty();


	public void forEach(BiConsumer<? super Double, ? super T> action);


	@Override
	public String toString();

}
