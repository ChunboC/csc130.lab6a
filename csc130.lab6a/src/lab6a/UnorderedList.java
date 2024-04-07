package lab6a;

/**
 * An Unordered list is an extension of List, it has additional methods to
 * search at the rear of the list, and replace items by index or item
 * 
 * @param <T> the type of elements in this un-orderd list.
 */
public interface UnorderedList<T> extends List<T> {
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
}
