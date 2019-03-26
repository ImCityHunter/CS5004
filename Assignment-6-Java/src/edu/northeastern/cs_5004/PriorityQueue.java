/**
 * PriorityQueue.java

 * This file contains the declarations for class template PriorityQueue<T>.
 *
 *  @author philip gust
 */
package edu.northeastern.cs_5004;

import java.util.Queue;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayDeque;


/**
 * This Priority Queue uses EnumMap  
 */
public class PriorityQueue<T> extends java.util.AbstractQueue<T> {
	
	
	
	/**
	 * This class implements an iterator for a queue.
	 */
	private class PriorityQueueIterator implements Iterator<T> {
		/** The current queue index */
		Priority[] priorities = Priority.values();
		int pIndex=0;
		
		/** The current queue iterator */
		Iterator<T> queueItr = queues.get(priorities[pIndex]).iterator();
		
		/**
		 * Returns the next element available or null if no more elements.
		 * 
		 * @return next element available or null if no elements avaialble 
		 */
		@Override
		public T next() {
			return (hasNext() ? queueItr.next() : null);
		}	
		
		/**
		 * Returns true if another element is available.
		 * 
		 * @return true if another element is available
		 */
		@Override
		public boolean hasNext() {
			while (pIndex < priorities.length) {
				// return true if iterator for current queue has another message
				if (queueItr.hasNext()) {
					return true;
				}
				// else move to next queue
				if (++pIndex < queues.size()) {
					queueItr = queues.get(priorities[pIndex]).iterator();
				}
			}
			return false;
		}
	}
	
	private EnumMap<Priority, Queue<T>> queues;
	
	/**
	 * The priorities for the MessagePriorityQueue
	 */
	public enum Priority{
		highest,
		high,
		low,
		lowest
	};

	/**
	 * Create a new message priority queue
	 */
	public PriorityQueue() {
		// allocate array for message queues
		
		queues = new EnumMap<Priority, Queue<T>>(Priority.class);
		
		// allocate message queue for each priority
		for (Priority p : Priority.values()) {
			Queue<T> newQ = new ArrayDeque<T>();
			queues.put(p, newQ);
		}
	}
	

	/**
	 * Get the current number of elements in the queue with the specified priority.
	 *
	 * @param the number of elements for the specified priority
	 */
	public int getSize(Priority priority) {
		return queues.get(priority).size();
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * @return true
	 * @throws NullPointerException, ClassCastException
	 */
	public boolean add(T e) {
		if(e==null) throw new NullPointerException("added value cannot be null");
		offer(e,Priority.lowest);
		return true;
	}
	
	/**
	 * Inserts the specified element into this queue
	 * if it is possible to do so immediately without violating capacity restrictions.
	 * @return true
	 * @throws NullPointerException, ClassCastException
	 */
	@Override
	public boolean offer(T e) {
		if(e == null) throw new NullPointerException("added value cannot be null");
		return offer(e, Priority.lowest);
	}
	
	/**
	 * Add a message to the queue with the given priority
	 *
	 * @param item the item to item
	 * @param priority the priority of the item
	 */
	public boolean offer(T item, Priority priority) {
		if(item == null) throw new NullPointerException("added value cannot be null");
		queues.get(priority).add(item);
		return true;
	}
	/**
	 * Retrieves and removes the head of this queue,
	 * or returns null if this queue is empty.
	 */
	@Override
	public T poll() {
		if(this.size()==0) return null;

		// dequeue message from the first non-empty queue
		for (Priority p : Priority.values()) { 
			if (queues.get(p).peek() != null) {
				return queues.get(p).poll();
			}
		}

		// return the message if found
		return null;
		
		
	}
	
	/**
	 * Retrieves and removes the head of this queue. This method differs from poll only in that it throws an exception if this queue is empty.
	 */
	public T remove() {
		if(this.size()==0) throw new NoSuchElementException();
		// dequeue message from the first non-empty queue
		for (Priority p : Priority.values()) { 
			if (queues.get(p).peek() != null) {
				return queues.get(p).remove();
			}
		}

		// return the message if found
		return null;
	}
	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * or returns null if this queue is empty.
	 */
	@Override
	public T peek() {
		T msg = null;
		// dequeue message from the first non-empty queue
		for (Priority p : Priority.values()) {
			msg =  queues.get(p).peek();
			// found message to dequeue
			if (msg != null) {
				break;
			}
		}
		
		return msg;
	}

	@Override
	public Iterator<T> iterator() {
		return new PriorityQueueIterator();
	}

	@Override
	public int size() {
		// add the size of all the queues
		int nElements = 0;
		for (Priority p : Priority.values()) {
			nElements+= getSize(p);
		}
		return nElements;
	}
}
