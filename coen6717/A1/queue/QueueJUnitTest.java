package coen448.coen6717.A1.queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueJUnitTest {
	private ADTQueue<Integer> Q1, Q2, Q3;

	/* clear() : Reinitialize the queue.  The user is responsible for
	 reclaiming the storage used by the queue elements. */

	/* enqueue() : Place an element at the rear of the queue.
 	@param it The element being enqueued. */

	/* dequeue() : dequeue() and return element at the front of the queue.
 	@return The element at the front of the queue. */

	/* frontValue() : @return The front element. */

	/* length() : @return The number of elements in the queue. */


	@BeforeEach
	void setUp() {
		// AQueue has default size of 10, maxSize, front, rear, listArray. One extra space is allocated at creation.
		// AQueue has methods clear(), enqueue(), dequeue(), frontValue(), length(), toString()
		Q1 = new AQueue<>(15);

		// LQueue has front, rear, size
		// LQueue has methods clear(), enqueue(), dequeue(), frontValue(), length(), toString()
		Q2 = new LQueue<>();

		// DQueue has front, rear, size
		// DQueue has methods clear(), enqueue(), dequeue(), frontValue (not completed), length(), toString(),
		Q3 = new DQueue<>();
	}



	/** AQueue Implementation
	 Variables:
	 - maxSize domain: (0, n)
	 - front domain: (0, n)
	 - rear domain: (0, n)
	 - listArray domain: Array

	 Methods:
	 - AQueue - calls init w/ defaultSize (10)
	 - AQueue(size) - calls init
	 -> Sets maxSize = size + 1, rear = 0, front = 1?
	 - init - front = rear = null, size = 0
	 -> Check if properly initialized
	 - enqueue - remove element from front
	 -> Check if queue is full (rear + 2) % maxSize != front?
	 -> Circular increment (rear + 1) % maxSize
	 -> Check if we should increment first or add to rear first
	 - dequeue - remove element from front
	 -> Circular increment (front + 1) % maxSize
	 **/
	/*
	Test 0
		Tester : Nicholas Kawwas
		Date : February 18th 2022
		Test input data : < 15 10 10 20 15 10 20 >
		Test type : functional, blackbox
		Description : All methods of the L Queue are tested, including enqueue(), dequeue(), clear(), frontValue(), length(), and toString().
		Expected output < empty queue, 10 20 15 10 20, empty queue, 10 20 15 10 20 >
	*/
	@Test
	public void testGeneralAQueue() {
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

//		while(Q1.length() > 0) {
//			temp = Q1.dequeue();
//			Q1.enqueue(temp);
//		}
//		assertEquals("< >", Q1.toString());
//		assertEquals("< 10 20 15 10 20 >", Q1.toString());

	}


	/** AQueue ECC **/
	/*
	 Test 1: (Not defined, -, -, -, -)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < null >
		 Test type : functional, blackbox
		 Description : Null queue throws a null pointer exception when enqueuing/dequeueing a null value
		 Expected output < Exception Exception >
	 */
	@Test
	public void testAQueueEnqueueDequeueECC_T1() {
		Q1 = null;
		assertThrows(NullPointerException.class, ()-> Q1.enqueue(null));
		assertThrows(NullPointerException.class, ()-> Q1.dequeue());
	}

	/*
	 Test 2: (Empty, < -1, Null, False, False)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < -3 >
		 Test type : functional, blackbox
		 Description : Empty queue with negative size can't enqueue or dequeue
		 Expected output < NegativeArraySizeException >
	 */
	@Test
	public void testAQueueEnqueueDequeueECC_T2() {
		// Initializes -1 size array
		assertThrows(NegativeArraySizeException.class,
				() -> new AQueue(-3));
	}

	/*
	 Test 3: (Empty, -1, Null, False, False)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < null >
		 Test type : functional, blackbox
		 Description : Queue with size 0 can't enqueue or dequeue
		 Expected output < Arithmetic Error >
	*/
	@Test
	public void testAQueueEnqueueDequeueECC_T3() {
		Q1 = new AQueue<>(-1);

		// Division by 0 error (maxSize = 0)
		assertThrows(ArithmeticException.class, () -> Q1.enqueue(null));
		assertThrows(ArithmeticException.class, () -> Q1.dequeue());
	}

	/*
	 Test 4: (Not empty, 0, Null, False, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < null >
		 Test type : functional, blackbox
		 Description : Queue with size 1 can enqueue but can't dequeue
		 Expected output < Assertion Error >
	*/
	@Test
	public void testAQueueEnqueueDequeueECC_T4() {
		Q1 = new AQueue<>(0);
		Q1.enqueue(null);
		assertThrows(AssertionError.class, () -> Q1.dequeue());
	}

	/*
	 Test 5: (Full, 0, Null, True, False)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 1 2 3>
		 Test type : functional, blackbox
		 Description : Full queue with size 2 can dequeue but can't enqueue
		 Expected output < Assertion Error 1 >
	*/
	@Test
	public void testAQueueEnqueueDequeueECC_T5() {
		Q1 = new AQueue<>(2);
		Q1.enqueue(1);
		Q1.enqueue(2);
		assertThrows(AssertionError.class, () -> Q1.enqueue(3));
		assertEquals(1, Q1.dequeue());
	}

	/** AQueue BCC **/
	/*
	 Test 1 : (Not empty, >=1, Non-null value, True, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 1, 10 >
		 Test type : functional, blackbox
		 Description : Queue with one element can enqueue and dequeue regular non-null values
		 Expected output < 1 10 >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T1() {
		Q1 = new AQueue<>(3);

		Q1.enqueue(1);
		assertEquals("< 1 >", Q1.toString());
		Q1.enqueue(10);
		assertEquals("< 1 10 >", Q1.toString());
		assertEquals(1, Q1.dequeue());
	}


	/*
	 Test 2 : (Empty, >=1, Non-null value, True, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 1, 2 >
		 Test type : functional, blackbox
		 Description : Empty queue that can enqueue and dequeue valid, non-null values
		 Expected output: < 1 2 >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T2() {
		Q1 = new AQueue<>(3);

		Q1.enqueue(1);
		assertEquals("< 1 >", Q1.toString());
		Q1.enqueue(2);
		assertEquals("< 1 2 >", Q1.toString());
		assertEquals(1, Q1.dequeue());
	}

	/*
	 Test 3 : (Full, >=1, Non-null value, True, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 1 2 3 >
		 Test type : functional, blackbox
		 Description : Full queue that can enqueue/dequeue non-null value
		 Expected output < 1 >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T3() {
		Q1 = new AQueue<>(2);
		Q1.enqueue(1);
		Q1.enqueue(2);
		assertThrows(AssertionError.class,
				()-> Q1.enqueue(3));
		assertEquals(1, Q1.dequeue());
	}

	/*
	 Test 4 : (Not empty, 0, Non-null value, True, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 5 4 >
		 Test type : functional, blackbox
		 Description : Not empty queue with size 0 that can enqueue and dequeue non-null values
		 Expected output < 5 >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T4() {
		Q1 = new AQueue<>(0);

		// Can enqueue by nothing is added
		Q1.enqueue(5);

		// Queue is Empty
		assertThrows(AssertionError.class,
				()-> Q1.dequeue());
	}

	/*
	 Test 5 : (Not empty, -1, Non-null value, True, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < -1 2 >
		 Test type : functional, blackbox
		 Description : Not empty queue with size 0 can enqueue and dequeue non-null values
		 Expected output < Arithemtic Exception >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T5() {
		Q1 = new AQueue<>(-1);

		// Division by zero
		assertThrows(ArithmeticException.class,
				() -> Q1.enqueue(2));
		assertThrows(ArithmeticException.class,
				()-> Q1.dequeue());
	}

	/*
	 Test 6 : (Not empty, <-1, Non-null value, True, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < -2 >
		 Test type : functional, blackbox
		 Description : Queue initialized with negative size
		 Expected output < Negative Array Size Exception >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T6() {
		// Initializes -1 size array
		assertThrows(NegativeArraySizeException.class,
				() -> new AQueue(-2));
	}

	/*
	 Test 7 : (Not empty, >=1, Null, True, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 3 null null >
		 Test type : functional, blackbox
		 Description : Queue with one element can enqueue and dequeue null elements
		 Expected output < null >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T7() {
		Q1 = new AQueue<>(3);
		Q1.enqueue(null);

		Q1.enqueue(null);
		assertEquals(null, Q1.dequeue());
	}

	/*
	 Test 8 : (Not empty, >=1, Non-null value, False, True)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 3 1 2 3 4 5 >
		 Test type : functional, blackbox
		 Description : Queue with one element that dequeues and enqueues until full
		 Expected output < 1 Assertion Error >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T8() {
		Q1 = new AQueue<>(3);
		Q1.enqueue(1);

		assertEquals(1, Q1.dequeue());
		Q1.enqueue(2);
		Q1.enqueue(3);
		Q1.enqueue(4);

		// Queue is full
		assertThrows(AssertionError.class, () -> Q1.enqueue(5));
	}

	/*
	 Test 9 : (Not empty, >=1, Non-null value, True, False)
		 Tester : Nicholas Kawwas
		 Date : February 18th 2022
		 Test input data : < 3 2 3 >
		 Test type : functional, blackbox
		 Description : Queue with one element that enqueues another non-null value then dequeues until it is empty
		 Expected output < 2 Assertion Error >
	 */
	@Test
	public void testAQueueEnqueueDequeueBCC_T9() {
		Q1 = new AQueue<>(3);
		Q1.enqueue(2);

		Q1.enqueue(3);
		assertEquals(2, Q1.dequeue());
		assertEquals(3, Q1.dequeue());

		// Queue is empty
		assertThrows(AssertionError.class, () -> Q1.dequeue());
	}


	/** LQueue Implementation
	 Variables:
	 - front domain: Linked list node
	 - rear domain: Linked list node
	 - size domain: (0, n)

	 Methods:
	 - LQueue - calls init
	 - LQueue(size) - calls init, ignoring size
	 - init - front = rear = null, size = 0
	 -> Check if properly initialized
	 - dequeue - remove element from front
	 -> Check if setting next pointer is properly done on front
	 -> Check if front next == null, then rear = front?
	 - enqueue - add element to rear
	 -> Check if setting next pointer is properly done on rear
	 **/

	/*
	Test 0
		Tester : Ali Turkman
		Date : February 16th 2022
		Test input data : < 15 10 10 20 15 10 20 >
		Test type : functional, blackbox
		Description : All methods of the L Queue are tested, including enqueue(), dequeue(), clear(), frontValue(), length(), and toString().
		Expected output < empty queue, 10 20 15 10 20, empty queue, 10 20 15 10 20 >
	*/
	@Test
	public void testGeneralLQueue() {
		int temp;
		Q2 = new AQueue<>(15);
		Q2.enqueue(15);
		Q2.enqueue(10);
		Q2.clear();
		assertEquals("< >", Q2.toString());

		Q2.enqueue(10);
		assertEquals(10, Q2.frontValue());
		Q2.enqueue(20);
		assertEquals(2, Q2.length());
		Q2.enqueue(15);
		Q2.enqueue(10);
		Q2.enqueue(20);
		assertEquals("< 10 20 15 10 20 >", Q2.toString());

//		while(Q2.length() > 0) {
//			temp = Q2.dequeue();
//			Q2.enqueue(temp);
//		}
//		assertEquals("< >", Q2.toString());
//		assertEquals("< 10 20 15 10 20 >", Q2.toString());
	}


	/** AQueue ECC **/
	/*
	 Test 1: (null,true,0)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < null >
		 Test type : functional, blackbox
		 Description : Null queue throws a null pointer exception when enqueuing/dequeueing a null value
		 Expected output < Exception Exception >
	 */
	@Test
	public void testLQueueEnqueueDequeueECC_T1() {
		Q2.clear(); //0
		Q2 = null; //null
		assertThrows(NullPointerException.class,
				()-> Q2.enqueue(null));
		assertThrows(NullPointerException.class,
				()-> Q2.dequeue());
	}

	/*
	 Test 2: (empty,false,1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 10 >
		 Test type : functional, blackbox
		 Description : Empty queue is capable of enqueuing/dequeuing non-null value
		 Expected output < 10 >
	 */
	@Test
	public void testLQueueEnqueueDequeueECC_T2() {
		Q2.clear();
		Q2.enqueue(10);
		assertEquals("< 10 >", Q2.toString());
		assertEquals(10, Q2.dequeue());
	}

	/*
	 Test 3: (At least one element,false,>1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 10 >
		 Test type : functional, blackbox
		 Description : Queue with at least one element is capable of enqueuing/dequeuing a non-null value
		 Expected output < 1 10 >
	*/
	@Test
	public void testLQueueEnqueueDequeueECC_T3() {
		Q2.clear();
		Q2.enqueue(1);
		assertEquals("< 1 >", Q2.toString());
		Q2.enqueue(10);
		assertEquals("< 1 10 >", Q2.toString());
		assertEquals(1, Q2.dequeue());
	}

	/** LQueue BCC **/
	/*
	 Test 1 : (At least one element, false, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 10 >
		 Test type : functional, blackbox
		 Description : Queue with one element can enqueue and dequeue regular non-null values
		 Expected output < 1 10 >
	 */
	@Test
	public void testLQueueEnqueueDequeueBCC_T1() {
		Q2.clear();
		Q2.enqueue(1);
		assertEquals("< 1 >", Q2.toString());
		Q2.enqueue(10);
		assertEquals("< 1 10 >", Q2.toString());
		assertEquals(1, Q2.dequeue());
	}


	/*
	 Test 2 : (null, false, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 null >
		 Test type : functional, blackbox
		 Description : Null queue that had multiple elements before being declared null throws a null pointer exception when enqueuing/dequeueing a null value
		 Expected output < Exception Exception >
	 */
	@Test
	public void testLQueueEnqueueDequeueBCC_T2() {
		Q2.clear();
		Q2.enqueue(1);
		Q2.enqueue(2);
		assertEquals("< 1 2 >", Q2.toString());
		Q2 = null;
		assertThrows(NullPointerException.class,
				()-> Q2.enqueue(null));
		assertThrows(NullPointerException.class,
				()-> Q2.dequeue());
	}


	/*
	 Test 3 : (empty, false, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 1 10 >
		 Test type : functional, blackbox
		 Description : Emptied queue that had multiple elements previously is capable of enqueuing/dequeuing non-null value
		 Expected output < 1 2 1 10 >
	 */
	@Test
	public void testLQueueEnqueueDequeueBCC_T3() {
		Q2.enqueue(1);
		Q2.enqueue(2);
		assertEquals("< 1 2 >", Q2.toString());
		Q2.clear();
		Q2.enqueue(1);
		assertEquals("< 1 >", Q2.toString());
		Q2.enqueue(10);
		assertEquals("< 1 10 >", Q2.toString());
		assertEquals(1, Q2.dequeue());
	}

	/*
	 Test 4 : (At least one element, true, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < null 1 2 >
		 Test type : functional, blackbox
		 Description : Queue can enqueue non-null values and null value, and then dequeue the null value
		 Expected output < null >
	 */
	@Test
	public void testLQueueEnqueueDequeueBCC_T4() {
		Q2.enqueue(null);
		assertEquals("< null >", Q2.toString());
		Q2.enqueue(1);
		Q2.enqueue(2);
		assertNull(Q2.dequeue());

	}

	/*
	 Test 5 : (At least one element, false, 0)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 >
		 Test type : functional, blackbox
		 Description : Empty queue with no elements can enqueue non-null values
		 Expected output < 1 2 >
	 */
	@Test
	public void testLQueueEnqueueDequeueBCC_T5() {
		Q2.clear();
		Q2.enqueue(1);
		Q2.enqueue(2);
		assertEquals("< 1 2 >", Q2.toString());
		assertEquals(1, Q2.dequeue());

	}

	/*
	 Test 6 : (At least one element, false, 1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 5 >
		 Test type : functional, blackbox
		 Description : Queue with one element already can enqueue and dequeue more non-null elements
		 Expected output < 1 2 5 >
	 */
	@Test
	public void testLQueueEnqueueDequeueBCC_T6() {
		Q2.enqueue(1);
		assertEquals("< 1 >", Q2.toString());
		Q2.enqueue(2);
		Q2.enqueue(5);
		assertEquals("< 1 2 5 >", Q2.toString());
		assertEquals(1, Q2.dequeue());
		assertEquals(2, Q2.dequeue());
		assertEquals(5, Q2.dequeue());

	}



	/** DQueue Implementation
	 Variables:
	 - front domain: Doubly linked list node
	 - rear domain: Doubly linked list node
	 - size domain: (0, n)

	 Methods:
	 - DQueue - calls init
	 - init - front = (null, null), rear = (front, null), front(null, rear)
	 -> Check if properly initialized
	 -> Note size not set
	 - enqueue - add element to rear
	 -> Check if setting prev and next pointer is properly done on rear
	 - dequeue - remove element from front
	 -> Check if setting next pointer is properly done on front with null
	 **/

	/*
	 Test 0
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 15 10 10 20 15 10 20 >
		 Test type : functional, blackbox
		 Description : All methods of the D Queue are tested, including enqueue(), dequeue(), clear(), frontValue(), length(), and toString().
		 Note : Contrary to LQueue, within DQueue the size is only decremented if an element is dequeued. If a DQueue is cleared then it maintains previous size.
		 Expected output < empty queue, 10, 2, 10 20 15 10 20, empty queue, 10 20 15 10 20 >
	*/
	@Test
	public void testGeneralDQueue() {
		int temp;
		Q3.enqueue(15);
		Q3.enqueue(10);
		Q3.clear();
		assertEquals("< >", Q3.toString());

		Q3.enqueue(10);
		assertNull(Q3.frontValue());
		Q3.enqueue(20);
		assertEquals(4, Q3.length());
		Q3.enqueue(15);
		Q3.enqueue(10);
		Q3.enqueue(20);
		assertEquals("< 10 20 15 10 20 >", Q3.toString());

		while(Q3.length() > 2) {
			temp = Q3.dequeue();
			Q2.enqueue(temp);
		}
		assertEquals("< >", Q3.toString());
		assertEquals("< 10 20 15 10 20 >", Q2.toString());

	}


	/** DQueue ECC **/
	/*
	 Test 1: (null,true,0)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < null >
		 Test type : functional, blackbox
		 Description : Null queue throws a null pointer exception when enqueuing/dequeueing a null value
		 Expected output < Exception Exception >
	 */
	@Test
	public void testDQueueEnqueueDequeueECC_T1() {
		Q3.clear(); //0
		Q3 = null; //null
		assertThrows(NullPointerException.class,
				()-> Q3.enqueue(null));
		assertThrows(NullPointerException.class,
				()-> Q3.dequeue());
	}

	/*
	 Test 2: (empty,false,1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 10 >
		 Test type : functional, blackbox
		 Description : Empty queue is capable of enqueuing/dequeuing non-null value
		 Expected output < 10 >
	 */
	@Test
	public void testDQueueEnqueueDequeueECC_T2() {
		Q3.clear();
		Q3.enqueue(10);
		assertEquals("< 10 >", Q3.toString());
		assertEquals(10, Q3.dequeue());
	}

	/*
	 Test 3: (At least one element,false,>1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 10 >
		 Test type : functional, blackbox
		 Description : Queue with at least one element is capable of enqueuing/dequeuing a non-null value
		 Expected output < 1 10 >
	 */
	@Test
	public void testDQueueEnqueueDequeueECC_T3() {
		Q3.clear();
		Q3.enqueue(1);
		assertEquals("< 1 >", Q3.toString());
		Q3.enqueue(10);
		assertEquals("< 1 10 >", Q3.toString());
		assertEquals(1, Q3.dequeue());
	}

	/** DQueue BCC **/
	/*
	 Test 1 : (At least one element, false, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 10 >
		 Test type : functional, blackbox
		 Description : Queue with one element can enqueue and dequeue regular non-null values
		 Expected output < 1 10 >
	 */
	@Test
	public void testDQueueEnqueueDequeueBCC_T1() {
		Q3.clear();
		Q3.enqueue(1);
		assertEquals("< 1 >", Q3.toString());
		Q3.enqueue(10);
		assertEquals("< 1 10 >", Q3.toString());
		assertEquals(1, Q3.dequeue());
	}

	/*
	 Test 2 : (null, false, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 null >
		 Test type : functional, blackbox
		 Description : Null queue that had multiple elements before being declared null throws a null pointer exception when enqueuing/dequeueing a null value
		 Expected output < Exception Exception >
	 */
	@Test
	public void testDQueueEnqueueDequeueBCC_T2() {
		Q3.clear();
		Q3.enqueue(1);
		Q3.enqueue(2);
		assertEquals("< 1 2 >", Q3.toString());
		Q3 = null;
		assertThrows(NullPointerException.class,
				()-> Q3.enqueue(null));
		assertThrows(NullPointerException.class,
				()-> Q3.dequeue());
	}

	/*
	 Test 3 : (empty, false, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 1 10 >
		 Test type : functional, blackbox
		 Description : Emptied queue that had multiple elements previously is capable of enqueuing/dequeuing non-null value
		 Expected output < 1 2 1 10 >
	 */
	@Test
	public void testDQueueEnqueueDequeueBCC_T3() {
		Q3.enqueue(1);
		Q3.enqueue(2);
		assertEquals("< 1 2 >", Q3.toString());
		Q3.clear();
		Q3.enqueue(1);
		assertEquals("< 1 >", Q3.toString());
		Q3.enqueue(10);
		assertEquals("< 1 10 >", Q3.toString());
		assertEquals(1, Q3.dequeue());
	}

	/*
	 Test 4 : (At least one element, true, >1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < null 1 2 >
		 Test type : functional, blackbox
		 Description : Queue can enqueue non-null values and null value, and then dequeue the null value
		 Expected output < null >
	 */
	@Test
	public void testDQueueEnqueueDequeueBCC_T4() {
		Q3.enqueue(null);
		assertEquals("< null >", Q3.toString());
		Q3.enqueue(1);
		Q3.enqueue(2);
		assertNull(Q3.dequeue());

	}

	/*
	 Test 5 : (At least one element, false, 0)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 >
		 Test type : functional, blackbox
		 Description : Empty queue with no elements can enqueue non-null values
		 Expected output < 1 2 >
	 */
	@Test
	public void testDQueueEnqueueDequeueBCC_T5() {
		Q3.clear();
		Q3.enqueue(1);
		Q3.enqueue(2);
		assertEquals("< 1 2 >", Q3.toString());
		assertEquals(1, Q3.dequeue());

	}

	/*
	 Test 6 : (At least one element, false, 1)
		 Tester : Ali Turkman
		 Date : February 16th 2022
		 Test input data : < 1 2 5 >
		 Test type : functional, blackbox
		 Description : Queue with one element already can enqueue and dequeue more non-null elements
		 Expected output < 1 2 5 >
	 */
	@Test
	public void testDQueueEnqueueDequeueBCC_T6() {
		Q3.enqueue(1);
		assertEquals("< 1 >", Q3.toString());
		Q3.enqueue(2);
		Q3.enqueue(5);
		assertEquals("< 1 2 5 >", Q3.toString());
		assertEquals(1, Q3.dequeue());
		assertEquals(2, Q3.dequeue());
		assertEquals(5, Q3.dequeue());

	}
}
