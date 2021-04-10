import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueTest {

	public static void main(String[] args) {

		// creating a new queue instance
		Queue<String> namesQueue = new LinkedList<>();
		Deque<Integer> numbersDeque = new ArrayDeque<>();

		// ArrayBlockingQueue, LinkeBlockingQueue and LinkedBlockingDeque have
		// restrictions on their capacity
		BlockingQueue<String> waitingCustomers = new ArrayBlockingQueue<>(5);

		namesQueue.addAll(Arrays.asList("Alex", "Bob", "Crook", "Dash", "Egg"));
		numbersDeque.addAll(Arrays.asList(1, 2, 3, 4, 5));
		waitingCustomers.addAll(namesQueue);

		System.out.println(namesQueue + "\n\n" + numbersDeque + "\n\n" + waitingCustomers);

		// adding elements to the queue
		namesQueue.add("Francis");
		numbersDeque.addFirst(0);
		numbersDeque.addLast(6);
		// the following add attempt will throw an IllegalStateException
		// waitingCustomers.add("Francis");
		// this will prevent the exception from being thrown
		System.out.println(waitingCustomers.offer("Francis"));

		// removing can be done using remove() - exceptional or poll() - nonExceptional
		// and other operations... see
		// https://www.codejava.net/java-core/collections/java-queue-collection-tutorial-and-examples

	}

}
