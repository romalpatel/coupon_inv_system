package system;


interface listinterface<T> {

	public boolean add(T element);
	/*
	 * Precondition: List is not full
	 * Adds element to the list
	 */
	
	public boolean remove(T object);
	/*
	 * Precondition: List is not empty- Gives outofboundexception
	 * Removes the object from the List
	 */
	
	public int size();
	/*
	 * Returns the number of elements in the list
	 */
	
	public boolean isFull();
	/*
	 * Return true for a full list or vice versa
	 */
	
	public boolean contains(T o);
	/*
	 * Precondition: List is not empty
	 * Returns true is the list contains the element o
	 */
	
	
	}
