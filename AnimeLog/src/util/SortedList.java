package util;

/**
 * List for storing objects in a sorted order,
 * modeled after a LinkedList.
 * Does not allow duplicates. 
 * @author Hunter Pruitt
 *
 * @param <E> type of data to be stored in the list
 */
public class SortedList<E extends Comparable<E>> {

	/** Number of elements in the list */
	private int size;
	
	/** Reference to the first element in the list */
	private ListNode front;

	/**
	 * Constructor for SortedList, initializes size and front
	 */
	public SortedList() {
		size = 0;
		front = null;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is duplicate 
	 */
	public void add(E element) {

		//Check can add
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		} else if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		
		
		//Add as front if list is empty
		if (front == null) {
			front = new ListNode(element);
			size++;
		} else {
			
			//If need to add before front
			if (0 < front.data.compareTo(element)) {
				front = new ListNode(element, front);
				size++;
				return;
			}
			
			//Seek through nodes, seeing if need to insert before an element
			ListNode temp = front;
			
			while(temp.next != null) {

				if (0 < temp.next.data.compareTo(element)) {
					//Add element before the next element
					temp.next = new ListNode(element, temp.next);
					size++;
					return;
				}
				temp = temp.next;
			}

			//Add at end if not found yet
			temp.next = new ListNode(element);
			size++;
		} //else clause	
	}
	
	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public E remove(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		E removed;
	
		//Remove from front
		if(idx == 0) {
			removed = front.data;
			front = front.next;
		}
		//Remove from middle
		else if (idx != size - 1) {
			ListNode temp = front;
			for (int i = 1; i < idx; i++) {
				temp = temp.next;
			}
			removed = temp.next.data;
			temp.next = temp.next.next;
		}
		//Remove from end
		else {
			ListNode temp = front;
			for (int i = 1; i < idx; i++) {
				temp = temp.next;
			}
			removed = temp.next.data;
			temp.next = null;
		}
		
		size--;
		return removed;
	}	

	
	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 */
	public boolean contains(E element) {
		ListNode temp = front;
		//Return false if the list is empty
		if (front == null) {
			return false;
		}
		
		//Check if the element is the first element
		if (front.data.equals(element)) {
			return true;
		}
		//Else check to see if the list is contained in any node
		while(temp.next != null) {
			if (temp.data.equals(element)) {
				return true;
			} else {
				temp = temp.next;
			}
		}
		return false;
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		ListNode temp = front;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp.data;
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * ListNode class representing an element in the list.
	 * There will be several of these referencing one another in a chain to form the list
	 */
	public class ListNode {
		/** Information stored in the ListNode */
		public E data;
		
		/** Pointer to the following ListNode in the sequence */
		public ListNode next;
		
		/**
		 * Constructor for the list node, 
		 * used when adding to an empty list or at the end.
		 * @param data the information to add to the list
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructor for the list node, 
		 * used when adding to the front or middle of the list.
		 * @param data the information to add to the list
		 * @param next the ListNode containing the next element in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
	}

	
}