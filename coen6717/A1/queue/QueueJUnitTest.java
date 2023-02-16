package coen448.coen6717.A1.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class QueueJUnitTest {
	
	private ADTQueue<Integer> Q1;
	private ADTQueue<Integer> Q2;
	private ADTQueue<Integer> Q3;

	/* Clear() : Reinitialize the queue.  The user is responsible for
	 reclaiming the storage used by the queue elements. */

	/* Enqueue() : Place an element at the rear of the queue.
 	@param it The element being enqueued. */

	/* Dequeue() : dequeue() and return element at the front of the queue.
 	@return The element at the front of the queue. */

	/* frontValue() : @return The front element. */

	/* length() : @return The number of elements in the queue. */
	

	@BeforeEach
	void setUp() throws Exception {
		// LQueue has front, rear, size
		// LQueue has methods clear(), enqueue(), dequeue(), frontValue(), length(), toString()
		Q1 = new LQueue<Integer>();

		// AQueue has default size of 10, maxSize, front, rear, listArray. One extra space is allocated at creation.
		// AQueue has methods clear(), enqueue(), dequeue(), frontValue(), length(), toString()
		Q2 = new AQueue<Integer>(15);

		// DQueue has front, rear, size
		// DQueue has methods enqueue(), dequeue(), frontValue (not completed), length(), toString(),
		Q3 = new DQueue<Integer>();
		
		
	}

	// Test 0
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 15 10 10 20 15 10 20 >
	// Test type : functional, blackbox
	// Description : All methods of the L Queue are tested, including enqueue(), dequeue(), clear(), frontValue(), length(), and toString().
	// Expected output < empty queue, 10, 2, 10 20 15 10 20, empty queue, 10 20 15 10 20 >
	@Test
	public void testGeneralLQueue() {
		int temp;
		Q1.enqueue(15);
		Q1.enqueue(10);
		Q1.clear();
		assertEquals("< >", Q1.toString());
		
		Q1.enqueue(10);
		assertEquals(10, Q1.frontValue());
		Q1.enqueue(20);
		assertEquals(2, Q1.length());
		Q1.enqueue(15);
		Q1.enqueue(10);
		Q1.enqueue(20);
		assertEquals("< 10 20 15 10 20 >", Q1.toString());
		
		while(Q1.length() > 0) {
		  temp = Q1.dequeue();
		  Q2.enqueue(temp);
		}
		assertEquals("< >", Q1.toString());
		assertEquals("< 10 20 15 10 20 >", Q2.toString());

	}


	// ECC
	// Test 1: (null,true,0)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < null >
	// Test type : functional, blackbox
	// Description : Null queue throws a null pointer exception when enqueuing/dequeueing a null value
	// Expected output < Exception Exception >
	@Test
	public void testLQueueEnqueueDequeueECC_T1() {
		Q1.clear(); //0
		Q1 = null; //null
		assertThrows(NullPointerException.class,
				()->{ Q1.enqueue(null); });
		assertThrows(NullPointerException.class,
				()->{ Q1.dequeue(); });
	}

	// Test 2: (empty,false,1)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 10 >
	// Test type : functional, blackbox
	// Description : Empty queue is capable of enqueuing/dequeuing non-null value
	// Expected output < 10 >
	@Test
	public void testLQueueEnqueueDequeueECC_T2()
	{
		Q1.clear();
		Q1.enqueue(10);
		assertEquals("< 10 >", Q1.toString());
		assertEquals(10, Q1.dequeue());
	}

	// Test 3: (At least one element,false,>1)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 1 10 >
	// Test type : functional, blackbox
	// Description : Queue with at least one element is capable of enqueuing/dequeuing a non-null value
	// Expected output < 1 10 >
	@Test
	public void testLQueueEnqueueDequeueECC_T3()
	{
		Q1.clear();
		Q1.enqueue(1);
		assertEquals("< 1 >", Q1.toString());
		Q1.enqueue(10);
		assertEquals("< 1 10 >", Q1.toString());
		assertEquals(1, Q1.dequeue());
	}

	// Test 1 Base Choice : (At least one element, false, >1)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 1 10 >
	// Test type : functional, blackbox
	// Description : Queue with one element can enqueue and dequeue regular non-null values
	// Expected output < 1 10 >

	@Test
	public void testLQueueEnqueueDequeueBCC_T1()
	{
		Q1.clear();
		Q1.enqueue(1);
		assertEquals("< 1 >", Q1.toString());
		Q1.enqueue(10);
		assertEquals("< 1 10 >", Q1.toString());
		assertEquals(1, Q1.dequeue());
	}


	// Test 2 : (null, false, >1)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 1 2 null >
	// Test type : functional, blackbox
	// Description : Null queue that had multiple elements before being declared null throws a null pointer exception when enqueuing/dequeueing a null value
	// Expected output < Exception Exception >
	@Test
	public void testLQueueEnqueueDequeueBCC_T2() {
		Q1.clear();
		Q1.enqueue(1);
		Q1.enqueue(2);
		assertEquals("< 1 2 >", Q1.toString());
		Q1 = null;
		assertThrows(NullPointerException.class,
				()->{ Q1.enqueue(null); });
		assertThrows(NullPointerException.class,
				()->{ Q1.dequeue(); });

	}


	// Test 3 : (empty, false, >1)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 1 2 1 10 >
	// Test type : functional, blackbox
	// Description : Emptied queue that had multiple elements previously is capable of enqueuing/dequeuing non-null value
	// Expected output < 1 2 1 10 >
	@Test
	public void testLQueueEnqueueDequeueBCC_T3()
	{
		Q1.enqueue(1);
		Q1.enqueue(2);
		assertEquals("< 1 2 >", Q1.toString());
		Q1.clear();
		Q1.enqueue(1);
		assertEquals("< 1 >", Q1.toString());
		Q1.enqueue(10);
		assertEquals("< 1 10 >", Q1.toString());
		assertEquals(1, Q1.dequeue());
	}

	// Test 4 : (At least one element, true, >1)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < null 1 2 >
	// Test type : functional, blackbox
	// Description : Queue can enqueue non-null values and null value, and then dequeue the null value
	// Expected output < null >
	@Test
	public void testLQueueEnqueueDequeueBCC_T4()
	{
		Q1.enqueue(null);
		assertEquals("< null >", Q1.toString());
		Q1.enqueue(1);
		Q1.enqueue(2);
		assertEquals(null, Q1.dequeue());

	}

	// Test 5 : (At least one element, false, 0)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 1 2 >
	// Test type : functional, blackbox
	// Description : Empty queue with no elements can enqueue non-null values
	// Expected output < 1 2 >
	@Test
	public void testLQueueEnqueueDequeueBCC_T5()
	{
		Q1.clear();
		Q1.enqueue(1);
		Q1.enqueue(2);
		assertEquals("< 1 2 >", Q1.toString());
		assertEquals(1, Q1.dequeue());

	}

	// Test 6 : (At least one element, false, 1)
	// Tester : Ali Turkman
	// Date : February 16th 2022
	// Test input data : < 1 2 5 >
	// Test type : functional, blackbox
	// Description : Queue with one element already can enqueue and dequeue more non-null elements
	// Expected output < 1 2 5 >
	@Test
	public void testLQueueEnqueueDequeueBCC_T6()
	{
		Q1.enqueue(1);
		assertEquals("< 1 >", Q1.toString());
		Q1.enqueue(2);
		Q1.enqueue(5);
		assertEquals("< 1 2 5 >", Q1.toString());
		assertEquals(1, Q1.dequeue());
		assertEquals(2, Q1.dequeue());
		assertEquals(5, Q1.dequeue());

	}


}
