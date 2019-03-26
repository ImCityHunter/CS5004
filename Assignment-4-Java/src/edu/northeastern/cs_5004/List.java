/* List.java
 * creating interface
 * @author yu2749luca, hokang yu
 * @date 02/04/2019
 */

package edu.northeastern.cs_5004;

import java.util.ListIterator;

public interface List<T> extends Iterable<T> {
	

	/**
	 * Appends the specified element to the end of this list. Returns true if the element was added.
	 * @param element to be added
	 * @return true if successfully added
	 */
	boolean add (T element);
	
	/**
	 * Inserts the specified element at the specified position in this list. 
	 * Returns true if the element was added.
	 * @param index
	 * @param element
	 * @return true if element is added
	 */
	boolean add (int index, T element);
	
	/**
	 * Removes all of the elements from this list.
	 */
	void clear ();
	
	/**
	 * Returns the element at the specified position in this list or null if index out of bounds.
	 * @param index
	 * @return element at index or null
	 */
	T  get (int index);
	
	/**
	 * Returns the first index of the first occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * @param t
	 * @return the index of element or -1
	 */
	int indexOf(T t);
	
	/**
	 * Returns true if this list contains no elements.
	 * @return true/false if list is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * @param element
	 * @return last index or -1
	 */
	int lastIndexOf(T element);
	
	/**
	 * Returns a list iterator over the elements in this list.
	 * @return listIterator
	 */
	ListIterator<T> listIterator();
	
	/**
	 * Removes the element at the specified position in this list.
	 * Returns the element removed or null if index is out of bounds.
	 * @param index
	 * @return removed value or null
	 */
	T remove(int index);
	
	/**
	 * Replaces the element at the specified position in this list with the specified element.
	 * Returns the previous value or null if index is out of bounds.
	 * @param index
	 * @param element
	 * @return replaced value or null
	 */
	T set(int index, T element);
	
	/**
	 * Returns the size of the list.
	 * @return size
	 */
	int size();
	
	
}
