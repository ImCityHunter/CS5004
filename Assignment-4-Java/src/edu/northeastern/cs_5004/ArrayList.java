/* ArrayList.java
 * creating ArrayList with Array. implement iterator
 * @author yu2749luca, hokang yu
 * @date 02/04/2019
 */
package edu.northeastern.cs_5004;

import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList <T> implements List<T>{
	
	private class ArrayListIterator implements ListIterator<T> {
		private int curIndex=0;
		private int visitedIndex=curIndex;
		/**
		 * Returns true if this list iterator has more elements 
		 * when traversing the list in the forward direction.
		 * @return true/false if there is next element
		 */
		@Override
		public boolean hasNext() {
			return this.nextIndex()<size;
		}
		
		/**
		 * Returns the next element in the list and advances the cursor position.
		 * @return next element
		 */
		@Override
		public T next() {
			T val= null;
			if(hasNext()) {
				visitedIndex=curIndex;
				val =  items[curIndex++];
			}
			return val;
		}
		
		/**
		 * Returns true if this list iterator has more elements 
		 * when traversing the list in the reverse direction.
		 * @return true/false
		 */
		@Override
		public boolean hasPrevious() {
			return this.previousIndex()>=0;
		}
		
		/**
		 * Returns the previous element in the list 
		 * and moves the cursor position backwards.
		 * @return last called next element
		 */
		@Override
		public T previous() {
			T val = null;
			if(hasPrevious()) {
				val = items[--curIndex];
				visitedIndex=curIndex;
			}
			return val;
		}
		
		/**
		 * Returns the index of the element 
		 * that would be returned by a subsequent call to next
		 * @return nextIndex
		 */
		@Override
		public int nextIndex() {
			return curIndex; //return, then increment
		}
		
		/**
		 * Returns the index of the element 
		 * that would be returned by a subsequent call to previous
		 */
		@Override
		public int previousIndex() {
			return curIndex-1; //decrement, then return
		}
		
		/**
		 * Removes from the list the last element 
		 * that was returned by next or previous 
		 */
		@Override
		public void remove() {
			if(visitedIndex>=0) curIndex=visitedIndex;
			ArrayList.this.remove(visitedIndex);
			visitedIndex = -1; //ensure remove function cannot be called twice
		}
		
		/**
		 * Replace the current element at this position with the input
		 * @param object E
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void set(Object e) {
			//if visitedIndex = -1 or out of bound, no value will change
			ArrayList.this.set(visitedIndex,(T) e);
		}
		
		/**
		 * Add an element at this new position, and move elements back
		 * @param Object E
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void add(Object e) {
			int index=0;
			if(visitedIndex == -1) index = -1;
			else if(visitedIndex < curIndex) index = curIndex;
			else {
				index = visitedIndex;
				curIndex++;
			}
			ArrayList.this.add(index,(T) e);
			visitedIndex=-1;
		}
	}

	private int size; //size of array
	private  T [] items; //list of items
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public ArrayList (){
		items = (T[]) new Object [5]; //set 10 as default capacity
		size = 0;
	}
	
	/**
	 * calculate and resize array
	 */
	private void ensureCapacity() {
		if(size==items.length) {
			@SuppressWarnings("unchecked")
			T [] newArrayList = (T[]) new Object[size*2];
			//Professor's in class code. replace for loop
			System.arraycopy(items, 0, newArrayList, 0, size);
			items = newArrayList;
		}
	}
	
	/**
	 * Return the current Capacity
	 */
	int getCapacity() {
		return items.length;
	}
	
	/**
	 * Returns an iterator over elements of type T.
	 * @return iterator
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	/**
	 * Appends the specified element to the end of this list.
	 * Returns true if the element was added.
	 * @param element
	 * @return true/false
	 */
	@Override
	public boolean add(T element) {
		return add(this.size,element);
	}
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * Returns true if the element was added.
	 * @param index
	 * @param element
	 * @return false/false
	 */
	@Override
	public boolean add(int index, T element) {
		if(index > this.size || index<0) return false;
		ensureCapacity();
		for (int i = this.size;i>index;i--) {
			items[i] = items[i-1];
		}
		items[index] = element;
		this.size+=1;
		return true;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		for (int i = 0;i<size;i++) {
			items[i] = null;
		}
		this.size = 0;
		
	}
	
	/**
	 * Returns the element at the specified position
	 * in this list or null if index out of bounds.
	 * @param index
	 * @return index element
	 */
	@Override
	public T get(int index) {
		if(index>=size || index<0) return (T) null;
		else return items [index];
		
	}
	
	/**
	 * Returns the first index of the first occurrence of the 
	 * specified element in this list,
	 * or -1 if this list does not contain the element.
	 * @param element
	 * @return index of element
	 */
	@Override
	public int indexOf(T t) {
		
		for (int i =0; i< size ;i++) {
			if(items[i].equals(t)) return i;
		}
		//return -1 if not found
		return -1;
	}
	
	/**
	 * Returns true if this list contains no elements.
	 * @return true/false
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * Returns the index of the last occurrence of the specified element in this list,
	 * or -1 if this list does not contain the element.
	 * @param element
	 * @return last index of element
	 */
	@Override
	public int lastIndexOf(T element) {
		for(int i=size-1;i >= 0;i--) {
			if(items[i].equals(element)) return i;
		}
		//return -1 if not found
		return -1;
	}
	
	/**
	 * Returns a list iterator over the elements in this list.
	 * @return ListIterator
	 */
	@Override
	public ListIterator <T> listIterator() {
		return new ArrayListIterator();
	}
	
	/**
	 * Removes the element at the specified position in this list.
	 * Returns the element removed or null if index is out of bounds.
	 * @param index
	 * @return previous val or null
	 */
	@Override
	public T remove(int index) {
		if(index>=size ||index<0) return (T) null;
		T val = items[index];
		size--;
		System.arraycopy(items, index+1, items, index, size-index);
		return val;
	}
	
	/**
	 * Replaces the element at the specified position in this list with the specified element.
	 * Returns the previous value or null if index is out of bounds.
	 * @param index
	 * @param element
	 * @return previous val or null
	 */
	@Override
	public T set(int index, T element) {
		T val = null;
		if(index >= size || index<0) return val;
		val = items[index];
		items[index] = element;
		return val;
	}
	
	/**
	 * @return the size of the list.
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Print the ArrayList
	 */
	public void print() {
		System.out.print("{");
		for (int i= 0;i<size;i++) {
			System.out.print(" "+items[i]+" ");
		}
		System.out.println("}");
	}

}
