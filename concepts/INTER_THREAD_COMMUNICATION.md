# Inter Thread Communication in Java 🔄

Inter thread communication in Java refers to the mechanism through which threads communicate and synchronize their actions to achieve a common goal. This communication typically involves passing data or signals between threads to coordinate their activities effectively.

### What is it? 🤔

Inter thread communication allows threads to cooperate and coordinate their activities by sharing information or signaling each other about the progress of their tasks.

### What is it used for? 🛠️

Inter thread communication is used when multiple threads need to work together to solve a problem or complete a task. It facilitates synchronization between threads, ensuring that they operate in a coordinated manner and avoid race conditions or inconsistencies.

### When to implement it? 📅

Inter thread communication should be implemented when:

- Multiple threads need to collaborate on a shared resource.

- Threads need to synchronize their execution to avoid conflicts.

- One thread needs to wait for a specific condition before proceeding.

### Advantages 🌟

- Enables efficient resource sharing among threads.

- Facilitates synchronization and coordination between threads.

- Helps in implementing complex concurrent algorithms.

### Disadvantages 🌧️

- Increased complexity and potential for deadlock if not implemented carefully.

- May introduce performance overhead due to synchronization mechanisms.
- Requires careful handling to avoid race conditions and thread safety issues.

### Analogy 🎭

Imagine a team of chefs working in a kitchen. Each chef represents a thread, and they need to communicate and coordinate their tasks to prepare a meal. They share ingredients (resources), pass cooking instructions (signals), and synchronize their actions to ensure that the meal is cooked perfectly without any chaos in the kitchen.

### Practical Example in Java 💻

```java
public class SharedResource {
    private String message;
    private boolean messageSet = false;

    // Method to produce a message
    public synchronized void produce(String message) {
        while (messageSet) {
            try {
                wait(); // Wait until consumer consumes the message
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.message = message;
        messageSet = true;
        System.out.println("Producer produced: " + message);
        notify(); // Notify the consumer that a message is available
    }

    // Method to consume a message
    public synchronized String consume() {
        while (!messageSet) {
            try {
                wait(); // Wait until producer produces a message
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        messageSet = false;
        System.out.println("Consumer consumed: " + message);
        notify(); // Notify the producer that the message has been consumed
        return message;
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.produce("Message " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.consume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
```

In this example, the SharedResource class represents a resource shared between a producer and a consumer thread. The producer produces messages, and the consumer consumes them. Synchronization is achieved using wait() and notify() methods to ensure that the producer and consumer communicate and coordinate their actions effectively.

## Conclusion 🎉
Inter thread communication is a crucial aspect of multi-threaded programming in Java. By enabling threads to cooperate and synchronize their activities, it helps in building concurrent applications that are efficient, reliable, and free from synchronization issues.
