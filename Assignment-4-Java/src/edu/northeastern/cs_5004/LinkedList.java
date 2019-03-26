/* LinkedList.java
 * creating LinkedList with LinkedNode. implements iterator
 * @author yu2749luca, hokang yu
 * @date 02/04/2019
 */
package edu.northeastern.cs_5004;

import java.util.Iterator;
import java.util.ListIterator;


public class LinkedList <T>implements List<T> {
	private class LinkedListIterator implements ListIterator<T>{
		private int curIndex;
		private int visitedIndex;
		private LinkedNode<T> curNode;
		private LinkedNode<T> visitedNode;
		
		/**
		 * Constructor for LinkedListIterator
		 */
		LinkedListIterator(){
			curNode = head;
			visitedNode = curNode; //default
			curIndex = -1; 
			visitedIndex = -1;
		}
		
		/**
		 * Returns true if this list iterator
		 * has more elements when traversing the list in the forward direction
		 */
		@Override
		public boolean hasNext() {
			return curIndex<size;
		}
		
		/**
		 * Returns the next element in the list and advances the cursor position
		 */
		@Override
		public T next() {
			if(!hasNext()) return null;
			else {
				curNode = curNode.next;
				curIndex++;
				visitedIndex = curIndex;
				visitedNode = curNode;
				T val = curNode.data;
				return val; //increment, then return, visited = cur
			}
		}
		
		/**
		 * Returns true if this list iterator has more elements
		 * when traversing the list in the reverse direction.
		 */
		@Override
		public boolean hasPrevious() {
			return (head != this.curNode); //head should not be read
		}
		
		/**
		 * Returns the previous element in the list and 
		 * moves the cursor position backwards
		 * 
		 * (return last called getNext)
		 */
		@Override
		public T previous() {
			T val = null;
			if(hasPrevious()) {
				val = curNode.data;
				visitedNode = curNode;
				visitedIndex = curIndex;
				
				curIndex-=1;
				curNode = curNode.prev;
				
			}
			return val;// return, then decrement, visited != cur
		}
		
		/**
		 * Returns the index of the element that would be returned by a subsequent call to next
		 */
		@Override
		public int nextIndex() {
			return curIndex+1;
		}
		
		
		/**
		 * Returns the index of the element that
		 * would be returned by a subsequent call to previous
		 */
		@Override
		public int previousIndex() {
			return curIndex;
		}
		
		/**
		 * Removes from the list the last element that was returned by next or previous
		 */
		@Override
		public void remove() {
			curIndex = visitedIndex-1;
			if(visitedNode==curNode) { //moving forward
				curNode = curNode.prev;
			}

			LinkedList.this.remove(visitedIndex);
			
			
			
			visitedIndex=-1;
			
		}
		
		/**
		 * Replaces the last element returned by next or previous with the specified element
		 */
		@Override
		public void set(T e) {
			//not tested
			if(visitedNode==null) System.out.println("ERROR!!");  
			LinkedList.this.set(visitedIndex,(T)e);
			
		}
		
		/**
		 * Inserts the specified element into the list
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void add(T e) {
			int index=0;
			if(visitedIndex==-1) index=-1;
			else if(visitedNode!=curNode) { //means moving backward
				index = visitedIndex;
				curIndex = visitedIndex;
				LinkedList.this.add(index,e);
				curNode = LinkedNode.get(head.next, index); //reset curNode pos
			}
			else { //moving forward
				index = this.nextIndex(); // add to next Index
				LinkedList.this.add(index,e);
				curIndex = index-1; //reset curIndex when moving forward
			}
			
			visitedIndex=-1;
			
		}}
	
	private static class LinkedNode <T>{
		private T data; 
		private LinkedNode<T> next;
		LinkedNode<T> prev;
		
		/**
		 * constructor
		 * @param element
		 */
		LinkedNode (T element){
			this.data = element;
			this.next = null;
			this.prev = null;
		}
		
		/**
		 * delete the current node
		 * @param node
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void deleteNode(LinkedNode node) {
			if(node!=null) {
				node.data = null;
				node.next = null;
				node.prev = null;
			}
		}
		
		/**
		 * get the previous node that is being deleted
		 * @param node
		 * @return node
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static LinkedNode deleteAfter(LinkedNode node) {
			//get the previous node first
			LinkedNode deleted = null;
			if(node!=null) {
				deleted = node.next;
				if(deleted!=null) {
					//connect node.next to node.next.next
					node.next = deleted.next;
	
					//connect deleted.next.prev to this node
					if(deleted.next!=null) {
						deleted.next.prev = node;
						}
					
					deleted.next = null;
					deleted.prev = null;
				}
				
			}
			return deleted;
		}
		/**
		 * return node of the index
		 * @param head
		 * @param index
		 * @return index
		 */
		@SuppressWarnings("rawtypes")
		public static LinkedNode get(LinkedNode head,int index) {
			LinkedNode n = head; //make a copy
			for ( ; n != null && index > 0; n = n.next, index--) {}
			return n;
		}
		
		/**
		 * add a node after given node
		 * @param node
		 * @param newNode
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void addAfter(LinkedNode node, LinkedNode newNode) {
			if(node!=null) {
				newNode.next = node.next;
				node.next = newNode;
				newNode.prev = node;
				
				if(newNode.next!=null) {
					newNode.next.prev = newNode;
					}
			}
			else { // first time added
				newNode.next = null;
				newNode.prev = null;
			}
		}
		
		/**
		 * delete all nodes given by the node
		 * @param head
		 */
		@SuppressWarnings("rawtypes")
		public static void deleteAll(LinkedNode head) {
			LinkedNode n;
			while((n=deleteAfter(head))!=null) {
				deleteNode(n);
			}
			deleteNode(head);
		}
	}

	
	
	
	private int size; //size of linked list
	public LinkedNode<T> head; //head node
	/**
	 * constructor
	 */
	LinkedList() {
		head = new LinkedNode<T>(null);
		size = 0;
	}
	
	/**
	 * @return LinkedListiTerator
	 */
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	/**
	 * Appends the specified element to the end of this list.
	 * Returns true if the element was added.
	 * @param element
	 * @return true if element can be added
	 */
	@Override
	public boolean add(T element) {
		return add(size,element);
	}
	
	/**
	 * @param index
	 * @param element
	 * @return true if element can be added at index
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean add(int index, T element) {
		if(index>size ||index<0) return false;
		LinkedNode <T>newNode = new LinkedNode(element);
		LinkedNode <T>copy = head;
		LinkedNode <T>prevNode = LinkedNode.get(copy, index);
		
		if(prevNode == null) return false;
		else {
		LinkedNode.addAfter(prevNode, newNode);
		this.size+=1;
		return true;
		}
		
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		LinkedNode.deleteAll(head);
		head.next = null;
		size=0;
	}
	
	/**
	 * @param index
	 * @return index of element T
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if(index>=size || index<0) return null;
		return (T) LinkedNode.get(head.next, index).data;
	}
	
	/**
	 * @param T
	 * @return index of T
	 */
	@Override
	public int indexOf(T t) {
		//head node is not counted
		LinkedNode<T> copy = head.next;
		for(int i =0;i<size&&copy!=null;i++,copy=copy.next) {
			if(copy.data ==t) return i;
		}
		return -1;
	}
	
	/**
	 * @return true/false if empty
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * @param element
	 * @return last index of the element or -1 if not found
	 */
	@Override
	public int lastIndexOf(T element) {
		int index=0;
		
		//head node is not count
		LinkedNode<T> copy = head.next;
		for (int i =0;i<size &&copy!=null;i++,copy=copy.next) {
			if(copy.data == element) index = i;
		}
		if(this.get(index)!=element) return -1;
		return index;
	}
	
	/**
	 * @return LinkedListerIterator
	 */
	@Override
	public ListIterator<T> listIterator() {
		return new LinkedListIterator();
	}
	
	/**
	 * Removes the element at the specified position in this list.
	 * @param index
	 * @return previous element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T remove(int index) {
		if(index>=size) return null;
		
		//"head" will get index-1, instead of index
		LinkedNode<T> node = LinkedNode.get(head, index); 
		T val = this.get(index);
		if(node!=null) {
			node = LinkedNode.deleteAfter(node);
			if(node!=null) {
				LinkedNode.deleteNode(node);
			}
		}
		size-=1;
		return (T)val;
	}
	
	/**
	 * Replaces the element at the specified
	 * position in this list with the specified element
	 * @param index
	 * @param element
	 * @return previous element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T set(int index, T element) {
		if(index>=size ||element ==null) return null;
		LinkedNode<T> node = LinkedNode.get(head.next, index);
		T copy = (T) node.data;
		node.data = element;
		return (T)copy;
	}
	
	/**
	 * @return size of ArrayList
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Print LinkedList
	 */
	public void print() {
		LinkedNode<T> copy = head.next;
		System.out.print("{");
		while(copy!=null) {
			System.out.print(" "+copy.data+" ");
			copy = copy.next;
		}
		System.out.println("}");
	}



}
