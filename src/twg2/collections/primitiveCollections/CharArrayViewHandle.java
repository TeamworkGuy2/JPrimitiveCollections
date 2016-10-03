package twg2.collections.primitiveCollections;

/** The handle for an {@link CharArrayView} which is used to modify the CharArrayView's
 * underlying array using {@link #setCharArrayView(char[], int, int)}.
 * Use {@link #getCharArrayView()} to retrieve the array view.
 * An instance of this class manages exactly one {@link CharArrayView}.
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 * This class is not thread safe, since its internal array view is not thread safe.
 * <br><br>
 *
 * @author TeamworkGuy2
 * @since 2015-1-18
 */
public class CharArrayViewHandle {
	private final CharArrayView arrayView;


	/** Create an empty array view pointing to a null array reference
	 */
	public CharArrayViewHandle() {
		this.arrayView = new CharArrayView();
	}


	/** Create an array view of an entire array
	 * @param objs the array to create a view of
	 */
	public CharArrayViewHandle(char[] objs) {
		this.arrayView = new CharArrayView(objs, 0, objs.length, false);
	}


	/** Create an array view of a sub-portion of an array
	 * @param objs the array to create a view of
	 * @param offset the offset into {@code objs} of the array view's {@code 0th} index
	 * @param length the number of values starting at {@code offset} to include in this view
	 */
	public CharArrayViewHandle(char[] objs, int offset, int length) {
		this(objs, offset, length, false);
	}


	/** Create an array view of a sub-portion of an array
	 * @param objs the array to create a view of
	 * @param offset the offset into {@code objs} of the array view's {@code 0th} index
	 * @param length the number of values starting at {@code offset} to include in this view
	 * @param allowSet true to allow this handle's {@link CharArrayView#set(int, char)} method
	 * to be called, false to throw an {@link UnsupportedOperationException} when {@code set} is called
	 */
	public CharArrayViewHandle(char[] objs, int offset, int length, boolean allowSet) {
		this.arrayView = new CharArrayView(objs, offset, length, allowSet);
	}


	/**
	 * @return this handle's array view
	 */
	public CharArrayView getCharArrayView() {
		return arrayView;
	}


	/** Point this handle's array view at another array
	 * @param objs the new array to view
	 * @param offset the offset into {@code objs} of the array view's {@code 0th} index
	 * @param length the number of values starting at {@code offset} to include in the view
	 */
	public void setCharArrayView(char[] objs, int offset, int length) {
		arrayView.mod++;
		arrayView.values = objs;
		arrayView.off = offset;
		arrayView.len = length;
	}

}
