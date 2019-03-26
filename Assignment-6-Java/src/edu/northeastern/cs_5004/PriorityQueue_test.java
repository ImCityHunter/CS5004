package edu.northeastern.cs_5004;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.FixMethodOrder;  
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.MethodSorters;

import edu.northeastern.cs_5004.PriorityQueue.Priority;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

//what is the difference between poll/dequeu (remove from highest)   && enqueue/offer (add to lowest)


/**
 * This class performs unit tests for the PriorityQueue classes.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriorityQueue_test {
	
	/**
	 * Test Self-written methods
	 */
	@Test
	public void test_enumMap() {
		int size=10;
		Priority[] priorities  = PriorityQueue.Priority.values();
		PriorityQueue<Integer> intQueues = new PriorityQueue<Integer>();
		for(PriorityQueue.Priority p: PriorityQueue.Priority.values()) {
			int value = p.ordinal()*10;
			for(int i=0;i<size;i++) {
				intQueues.offer(value+i, p);
			}
			assertEquals(size,intQueues.getSize(p));
		}
		assertEquals(size*priorities.length,intQueues.size());
		
		//test iterator reading first priority
		for(int i=0;i<size*priorities.length;i++) {
			int value = intQueues.peek();
			assertEquals((Integer)value,(Integer)intQueues.poll());
		}
		
		assertEquals(0,intQueues.size());
		assertTrue(intQueues.isEmpty());
		
		//only add info in two queues
		for(int i=0;i<size;i++) {
			intQueues.offer(i,priorities[0]);
			intQueues.offer(i,priorities[3]);
		}
		
		//To ensure all elements can still polled 
		for(int i=0;i<size*2;i++) {
			assertNotNull(intQueues.poll());
		}
		assertEquals(0,intQueues.size());
		assertTrue(intQueues.isEmpty());
		
		//insert null will fail
		try {
			intQueues.add(null);
			fail();
		}
		catch(NullPointerException e) {};
		
		//when size is 0; remove should catch exception
		try {
			intQueues.remove();
			fail();
		}
		catch(NoSuchElementException e) {};		
		
		//poll should return null if size is 0
		Integer value = intQueues.poll();
		assertNull(value);
	}
	
	/**
	 * Test Self-written methods
	 */
	@Test
	public void test_enumMapItr() {
		int size=10;
		Priority[]priorities = PriorityQueue.Priority.values();
		
		PriorityQueue<Integer> intQueues = new PriorityQueue<Integer>();
		for(PriorityQueue.Priority p: PriorityQueue.Priority.values()) {
			int value = p.ordinal()*10;
			for(int i=0;i<size;i++) {
				intQueues.offer(value+i, p);
			}
			assertEquals(size,intQueues.getSize(p));
		}
		assertEquals(size*priorities.length,intQueues.size());
		
		//make iterators
		Iterator <Integer> intItr = intQueues.iterator();
		
		for(int i=0;i<size*priorities.length;i++) {
			assertEquals(intItr.next(),intQueues.poll());
		}
		
		//ensure iterator return false when there is no next
		assertFalse(intItr.hasNext());
		
		//only add info in two queues
		for(int i=0;i<size;i++) {
			intQueues.offer(i,priorities[0]);
			intQueues.offer(i,priorities[3]);
		}
		
		intItr = intQueues.iterator();
		//To iterator can read all elements when not all queues have elements 
		for(int i=0;i<size*2;i++) {
			assertNotNull(intItr.next());
		}
		//ensure iterator return false when there is no next
		assertFalse(intItr.hasNext());
		

	}

	
	/**
	 * Unit tests for Message
	 */
	@Test
	public void test_0010_Message() {
		// test creation of message with null string
		Message msg = new Message();
		assertNotNull("Message created", msg);
		assertNull("Message string null", msg.getMessage());

		// test allocation of message with non-null string
		msg = new Message("Hello");
		assertNotNull("Message created", msg);
		assertNotNull("Message string", msg.getMessage());
		assertEquals("Message string", "Hello", msg.getMessage());
	}

	
	/**
	 * Unit tests for PriorityQueue
	 */
	@Test
	public void test_0030_PriorityQueue() {
		//// test empty queue
		// create a message queue
		PriorityQueue<Message> mpq = new PriorityQueue<Message>();
		assertNotNull("Priority queue createed", mpq);
		assertEquals("Priority queue empty", 0, mpq.size());

		// dequeueue message from empty queue
		assertNull("Priority queue empty", mpq.poll());

		//// test single message on queue
		// create message queue and enqueue message
		mpq = new PriorityQueue<Message>();
		Message msg = new Message("0.0");
		mpq.offer(msg, PriorityQueue.Priority.high);
		assertEquals("Prioity queue size", 1, mpq.size());
		assertEquals("Priority queue size: high",
					 1, mpq.getSize(PriorityQueue.Priority.high));

		// dequeue a message
		assertEquals("Expected message dequeued", mpq.poll(), msg);
		assertEquals("Priority queue empty", 0, mpq.size());

		// dequeue message from empty queue
		assertNull("Priority queue empty", mpq.poll());
		assertEquals("Priority queue empty", 0, mpq.size());

		//// Enqueue 3 messages for each priority
		mpq = new PriorityQueue<Message>();
		for (PriorityQueue.Priority p : PriorityQueue.Priority.values()) {
			for (int i = 0; i < 3; i++) {
				msg = new Message(p + "." + i);
				mpq.offer(msg, p);
			}
		}

		// ensure that each queue has 3 messages
		assertEquals("Expected #messages", 
					 12, mpq.size());
		assertEquals("Expected highest messages",
					 3, mpq.getSize(PriorityQueue.Priority.highest));
		assertEquals("Expected high messages",
				     3, mpq.getSize(PriorityQueue.Priority.high));
		assertEquals("Expected low messages",
				     3, mpq.getSize(PriorityQueue.Priority.low));
		assertEquals("Expected highest lowest",
				 	 3, mpq.getSize(PriorityQueue.Priority.lowest));

		// verify that the messages dequeue in the right order
		for (PriorityQueue.Priority p : PriorityQueue.Priority.values()) {
			for (int i = 0; i < 3; i++) {
				msg = mpq.poll();
				assertNotNull("Message not null", msg);
				assertEquals("Message dequeue order", 
							 p + "." + i, msg.getMessage());
			}
		}
		assertEquals("Queue empty", 0, mpq.size());
	}
	/**
	 * Unit tests for MessageQueueIterator
	 */
	@Test
	public void test_0035_MessagePriorityQueueIterator() {
		//// Enqueue 3 messages for each priority
		PriorityQueue<Message> mpq = new PriorityQueue<Message>();
		for (PriorityQueue.Priority p : PriorityQueue.Priority.values()) {
			for (int i = 0; i < 3; i++) {
				Message msg = new Message(p + "." + i);
				mpq.offer(msg, p);
			}
		}
		
		// iterate over messages
		int priorityIndex = 0;
		int msgIndex = 0;
		PriorityQueue.Priority[] priorities = PriorityQueue.Priority.values();
		for (Message msg : mpq) {
			String msgStr = priorities[priorityIndex] + "." + msgIndex;
			assertEquals("PriorityQueue message", msgStr, msg.getMessage());
			if (++msgIndex == 3) {
				msgIndex = 0;
				priorityIndex++;
			}
		}
	}
	
	/**
	 * Run the tests in this class.
	 * 
	 * @param args the program arguments
	 */
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(PriorityQueue_test.class);
	 
	    System.out.println("[Unit Test Results]");
	    System.out.println();
	    
	    if (result.getFailureCount() > 0) {
	    	System.out.println("Test failure details:");
		    for (Failure failure : result.getFailures()) {
		       System.out.println(failure.toString());
		    }
		    System.out.println();
	    }
	    
	    int passCount = result.getRunCount()-result.getFailureCount()-result.getIgnoreCount(); 
	    System.out.println("Test summary:");
	    System.out.println("* Total tests = " + result.getRunCount());
	    System.out.println("* Passed tests: " + passCount);
	    System.out.println("* Failed tests = " + result.getFailureCount());
	    System.out.println("* Inactive tests = " + result.getIgnoreCount());
	}
}
