package twg2.collections.primitiveCollections;

/** A searchable collection of float values
 * @author TeamworkGuy2
 * @since 2015-11-29
 */
@javax.annotation.Generated("StringTemplate")
public interface FloatSearchable {

	/** Check if the specified values is contained in this searchable group of floats
	 * @param value the value to check for in this group
	 * @return true if the value was found in the group, false otherwise
	 */
	public boolean contains(float value);


	/** Find the first occurring index of the specified float in this group of elements
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value
	 * is found, or -1 if the value cannot be found
	 */
	public int indexOf(float value);


	/** Find the last occurring index of the specified float in this group of elements
	 * @param value the value to search for in this list
	 * @return an index between {@code [0, }{@link #size()}{@code -1]} if the value
	 * is found, or -1 if the value cannot be found
	 */
	public int lastIndexOf(float value);


	/** Get the current size of this searchable group of elements
	 * @return the size of this searchable group
	 */
	public int size();


	/** Check if this searchable group is empty is empty
	 * @return true if this has zero elements, false otherwise
	 */
	public boolean isEmpty();

}
