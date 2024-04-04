package lab6a;

public class ArrayList<T> implements UnorderedList<T> {
	/**
	 * The list - generic array of items
	 */
	protected T[] data;
	/**
	 * The number of items in the list
	 */
	protected int numItems;
	/**
	 * default maximum capacity
	 */
	private static final int CAPACITY = 100;

	/**
	 * default constructor - creates a list that can store 100 items;<br>
	 * the size of the list is initialized to 0
	 */
	public ArrayList() {
		this(CAPACITY); // using the parameterized constructor - note CAPACITY must be static
	}

	/**
	 * parameterized constructor - allows the user to specify the size of the
	 * list.<br>
	 * The list created can store at most <i>size</i> items; the size of the list is
	 * initialized to 0.
	 * 
	 * @param size indicates the maximum size of the list as specified by the user
	 */
	public ArrayList(int size) {
		if (size <= 0)
			throw new RuntimeException("Invalid List Size");

		data = (T[]) new Object[size];
		size = 0;
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param item the element to be appended to the list
	 */
	public void add(T item) {
		if (numItems == data.length)
			expandCapacity();
		data[numItems] = item;
		numItems++;
		;
	}

	/**
	 * Inserts the specified element to a specified index of this list.
	 * 
	 * @param index the index to insert into the list.
	 * @param item  the item to be inserted into the list.
	 */
	@Override
	public void add(int index, T item) {
		if (index < 0 || index > numItems)
			throw new RuntimeException("Index out bounds Exception.");
		if (numItems == data.length)
			if (!expandCapacity())
				throw new RuntimeException("List full exception.");
		// shift data to the right
		for (int i = numItems; i > index; i--)
			data[i] = data[i - 1];
		data[index] = item;
		numItems++;
	}

	/**
	 * A private utility method that expands the array by doubling its size
	 * 
	 * @return <i>true</i> if the item can be expanded, <i>false</i> otherwise
	 */
	protected boolean expandCapacity() {
		if (numItems * 2 <= Integer.MAX_VALUE) {
			T[] tempData = (T[]) new Object[data.length * 2]; // create a new array
			for (int i = 0; i < numItems; i++)
				tempData[i] = data[i]; // copy the contents of the old to the new

			data = tempData; // instance variable points to the new array
			System.gc();
			return true;
		}
		return false;
	}

	/**
	 * Removes the first occurrence of the specified element from this list. Shifts
	 * any subsequent elements to the left. Returns the element that was removed
	 * from the list.
	 * 
	 * @param item the element to be removed
	 * @return the element previously at the specified position
	 */
	public T remove(T item) {
		int index = indexOf(item);
		if (index == -1)
			return null;
		return remove(index);
	}

	@Override
	public boolean contains(T item) {
		return indexOf(item) >= 0;
	}

	public T remove(int index) {
		if (index < 0 || index >= numItems)
			return null;
		T oldItem = data[index];
		// shift items to the left
		for (int i = index; i < numItems - 1; i++)
			data[i] = data[i + 1];
		data[numItems - 1] = null;
		numItems--;
		return oldItem;
	}

	/**
	 * Returns the index of a specified item
	 * 
	 * @param item the element that's being searched for
	 * @return the index of the item put in
	 */
	@Override
	public int indexOf(T item) {
		for (int i = 0; i < numItems; i++)
			if (data[i].equals(item))
				return i;
		return -1;
	}

	/**
	 * empty method - determines whether or not the list is empty
	 * 
	 * @return <i>true</i> if the list is empty; <i>false</i> otherwise
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * toString method - returns the state of the object as a String
	 * 
	 * @return a String containing the items in the list
	 */
	public String toString() {
		String str = new String("[");
		for (int i = 0; i < numItems; i++)
			str += data[i] + (i < numItems - 1 ? ", " : "");
		return str + "]";
	}

	/**
	 * Returns an element in the array list based on the specified index.
	 * 
	 * @param index the specified index.
	 * @return the element at the specified index.
	 */
	@Override
	public T get(int index) {
		if (index < 0 || index >= numItems)
			throw new RuntimeException("Index out bounds Exception");
		return data[index];
	}

	/**
	 * Returns the size of the array list.
	 * 
	 * @return the number of elements in the array list.
	 */
	@Override
	public int getSize() {
		return numItems;
	}

	/**
	 * Clears out the entire array list.
	 */
	@Override
	public void clear() {
		data = (T[]) new Object[data.length];
		System.gc();
	}
	/**
	 * Returns the index of the last appearance of an element.
	 * @param item the item to look for.
	 * @return the index of the last appearance of the item.
	 */
	@Override
	public int lastIndexOf(T item) {
		int idx = -1;
		for(int i = 0; i < numItems; i++)
			if(data[i].equals(item))
				idx = i;
		return idx;
	}
	/**
	 * Returns the item that got replaced.
	 * @param index index of the element to replace.
	 * @param item item to be stored at the specified index.
	 * @return the item that got replaced.
	 */
	@Override
	public T set(int index, T item) {
		T replaced;
		if (index < 0 || index >= numItems)
			throw new RuntimeException("Index out bounds Exception");
		replaced = data[index];
		data[index] = item;
		return replaced;
	}
	/**
	 * Returns the item that got replaced.
	 * @param oldItem the element to be replaced.
	 * @param newItem the item to be replace with the oldItem.
	 * @return the item that got replaced.
	 */
	@Override
	public T set(T oldItem, T newItem) {
		int idx = indexOf(oldItem);
		if (idx < 0 || idx >= numItems)
			throw new RuntimeException("Index out bounds Exception");
		data[idx] = newItem;
		return oldItem;
	}
}
