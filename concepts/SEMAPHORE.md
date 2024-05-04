# Semaphore 🛑
A semaphore is like a traffic light 🚦 that controls access to a shared resource, like a single-lane bridge. It allows a fixed number of threads to access the resource simultaneously. Here's the breakdown:

- **What it does:** Limits the number of threads that can access a resource concurrently.

- **When to use:** When you want to control access to a shared resource, like a database connection pool or a critical section of code.

- **Advantages:** Helps prevent resource contention and manage concurrency.

- **Disadvantages:** Can lead to deadlocks if not used carefully.

- **Why use it:** Ensures orderly access to shared resources, preventing race conditions.

- **Practical use:** Managing connections in a connection pool, limiting concurrent access to a critical section of code.

# Condition :bell:
A condition is like a bell 🔔 that threads can ring to signal each other about changes in the shared state. It's often used in conjunction with locks. Here's the scoop:

- **What it does:** Allows threads to wait until a certain condition is met before proceeding.

- **When to use:** When threads need to coordinate based on some condition, like a queue being empty or full.

- **Advantages:** Enables efficient thread coordination and synchronization.

- **Disadvantages:** Requires explicit synchronization using locks.

- **Why use it:** Facilitates inter-thread communication and synchronization.

- **Practical use:** Implementing producer-consumer patterns, managing thread pools.

### Comparison Table

| Feature        | Semaphore                                      | Condition                                           |
|----------------|------------------------------------------------|-----------------------------------------------------|
| Purpose        | Control access to shared resources             | Coordinate threads based on a condition            |
| Usage          | Controlling concurrent access                  | Synchronizing based on a condition                 |
| Advantages     | Prevents resource contention, manages concurrency | Enables efficient thread coordination             |
| Disadvantages  | Potential for deadlocks                        | Requires explicit synchronization with locks       |
| Practical Use  | Managing connection pools, critical sections   | Implementing producer-consumer patterns, thread pools |


# Code Example 

## semaphore
```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static final int NUM_THREADS = 5;
    private static Semaphore semaphore = new Semaphore(2); // Allow only 2 threads at a time

    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new Worker(i));
            thread.start();
        }
    }

    static class Worker implements Runnable {
        private int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(); // Acquire permit from semaphore
                System.out.println("Worker " + id + " is working");
                Thread.sleep(2000); // Simulate some work
                System.out.println("Worker " + id + " finished");
                semaphore.release(); // Release permit back to semaphore
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Explanation:

1. We import the Semaphore class from java.util.concurrent package.
2. We create a class SemaphoreExample with a constant NUM_THREADS and a Semaphore object initialized with 2 permits.
3. Inside the main method, we start 5 worker threads.
4. Each worker thread runs the Worker class, which implements the Runnable interface.
5. In the run method of Worker, each thread acquires a permit from the semaphore using semaphore.acquire().
6. It then performs some simulated work (sleeps for 2 seconds) and releases the permit back to the semaphore using semaphore.release().
Now, let's move on to an example for condition in Java:

## condition
```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static boolean flag = false;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            lock.lock();
            try {
                while (flag) {
                    condition.await(); // Wait until flag is false
                }
                System.out.println("Producing...");
                flag = true;
                condition.signal(); // Signal consumer thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(() -> {
            lock.lock();
            try {
                while (!flag) {
                    condition.await(); // Wait until flag is true
                }
                System.out.println("Consuming...");
                flag = false;
                condition.signal(); // Signal producer thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        producer.start();
        consumer.start();
    }
}
```

### Explanation:

1. We import Condition, Lock, and ReentrantLock from java.util.concurrent.locks package.
2. We create a class ConditionExample with a ReentrantLock, a Condition, and a boolean flag.
3. Inside the main method, we create two threads - one for the producer and one for the consumer.
4. The producer thread waits until the flag is false, produces some output, sets the flag to true, and signals the consumer thread.
5. The consumer thread waits until the flag is true, consumes some output, sets the flag to false, and signals the producer thread. 



These examples demonstrate how semaphores and conditions can be used for synchronization and coordination between threads in Java.
