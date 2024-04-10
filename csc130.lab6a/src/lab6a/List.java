package lab6a;

/**
 * A List represents a zero-based ordered sequence of elements. Elements can be
 * inserted at the end or at a stated index. The user can access elements by
 * position in the list, and search for elements in the list.
 *
 * @param <T> the type of elements in this list
 */
public interface List<T> {
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param item the element to be appended to the list
	 */
	void add(T item);

	/**
	 * Removes the first occurrence of the specified element from this list. Shifts
	 * any subsequent elements to the left. Returns the element that was removed
	 * from the list.
	 * 
	 * @param item the element to be removed
	 * @return the element previously at the specified position
	 */
	T remove(T item);

	/**
	 * Returns <i>true</i> if this list contains the specified element and
	 * <i>false</i> otherwise.
	 * 
	 * @param item element to search for
	 * @return <i>true</i> if this list contains the specified element or
	 *         <i>false</i> otherwise
	 */
	boolean contains(T item);

	/**
	 * Inserts the specified element at the specified position in this list. If
	 * necessary, shifts the element currently at that position and any subsequent
	 * elements to the right.
	 * 
	 * @param item  the element to be inserted to the list
	 * @param index index at which the specified element is to be inserted
	 */
	void add(int index, T item);

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or <i>-1</i> if the element is not in the list.
	 * 
	 * @param item element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or <i>-1</i> if the element is not in the list
	 */
	int indexOf(T item);

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or <i>-1</i> if the element is not in the list.
	 * 
	 * @param item element to search for
	 * @return the index of the last occurrence of the specified element in this
	 *         list, or <i>-1</i> if the element is not in the list
	 */
	int lastIndexOf(T item);

	/**
	 * Removes the element at the specified location from this list and returns the
	 * value presently stored.
	 * 
	 * @param index index of the element to remove
	 * @return the element previously at the specified position
	 */
	T remove(int index);

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index index of the element to return
	 * @return the element previously at the specified position
	 */
	T get(int index);

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element and returns the value presently stored.
	 * 
	 * @param index index of the element to replace
	 * @param item  element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	T set(int index, T item);

	/**
	 * Replaces an element in this list with a new value and returns the value
	 * presently stored.
	 * 
	 * @param oldItem element to be replaced
	 * @param newItem new element to be stored
	 * @return the element previously at the specified position
	 */
	T set(T oldItem, T newItem);

	/**
	 * Returns <i>true</i> if there are no elements in this list and <i>false</i>
	 * otherwise.
	 * 
	 * @return <i>true</i> if there are no elements in this list and <i>false</i>
	 *         otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list.
	 */
	int getSize();

	/**
	 * Removes all of the elements from this list
	 */
	void clear();
}