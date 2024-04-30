import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - creates a linear row of data that holds values in 
 * 	a row of connected nodes
 * 	each node has a pointer to the node that comes before and after it, making
 * 	it easy to insert and alter data within the list
 * 	the list's size can grow at the coder's will
 *
 *	@author	Rishi Salvi
 *	@since	29 April 2024
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		this.head = oldList.head; 
		this.tail = oldList.tail; 
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = null; 
		tail = null; 
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		ListNode<E> curr = new ListNode(obj); 
		if (head == null)
			head = curr;
		else if (tail == null){
			tail = curr; 
			head.setNext(tail); 
		}
		else{
			tail.setNext(curr); 
			tail = curr; 
		}
		return true; 
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj){
		ListNode<E> curr = new ListNode(obj); 
		if (index < 0 || index > size())
			throw new NoSuchElementException(); 
		ListNode<E> tmp = head;
		if (tmp == null)
			head = curr; 
		else if (index == 0){
			curr.setNext(tmp); 
			head = curr; 
		}
		else{
			for (int i = 0; i < index - 1; i++)
				tmp = tmp.getNext();
			ListNode<E> next = tmp.getNext(); 
			tmp.setNext(curr); 
			if (next != null)
				curr.setNext(next); 
			else
				tail = curr; 
		}
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		ListNode<E> tmp = head;
		if (tmp == null)
			return 0; 
		int counter = 1; 
		while (tmp.getNext() != null){
			tmp = tmp.getNext();
			counter++; 
		}
		return counter;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		if (index < 0 || index > size())
			throw new NoSuchElementException(); 
		ListNode<E> tmp = head;
		if (tmp == null)
			return null; 
		int counter = 0; 
		while (counter < index){
			tmp = tmp.getNext();
			counter++; 
		}
		return tmp; 
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		if (index < 0 || index > size())
			throw new NoSuchElementException(); 
		ListNode<E> tmp = head;
		if (tmp == null){
			head = new ListNode(obj); 
			return null; 
		}
		int counter = 0; 
		while (counter < index){
			tmp = tmp.getNext();
			counter++; 
		}
		E object = tmp.getValue(); 
		tmp.setValue(obj);
		return object; 
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		if (index < 0 || index > size())
			throw new NoSuchElementException(); 
		ListNode<E> tmp = head;
		if (tmp == null)
			return null; 
		if (index == 0){
			head = tmp.getNext(); 
			return tmp.getValue(); 
		}
		int counter = 0; 
		while (counter < index - 1){
			tmp = tmp.getNext();
			counter++; 
		}
		ListNode<E> curr = tmp.getNext();
		tmp.setNext(curr.getNext());
		return curr.getValue(); 
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		if (size() == 0)
			return true; 
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		ListNode<E> tmp = head; 
		if (tmp == null)
			return false; 
		if (indexOf(object) > -1)
			return true;
		return false; 
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		for (int i = 0; i < size(); i++){
			if (get(i).getValue().compareTo(element) == 0)
				return i; 
		}
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}
