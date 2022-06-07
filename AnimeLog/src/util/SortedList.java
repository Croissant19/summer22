package util;

/**
 * List for storing objects alphabetically,
 * modeled after a LinkedList.
 * Allows duplicates. 
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
	
	public boolean add(E element) {
		//If list is empty
		front = new ListNode(element);
		
		// TODO Auto-generated method stub
		return false;
	}

	public E remove(int idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean contains(E element) {
		return false;
	}
	
	/**
	 * Returns an object at a specific index of the list
	 * @return element stored at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 */
	public E get(int index) {
		//
		// TODO Auto-generated method stub
		return null;
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
