# Thread Locking Cheat Sheet

### General Description:
Thread locking is a synchronization technique used in multi-threaded programming to control access to shared resources. It ensures that only one thread can access the resource at a time, preventing data corruption and race conditions.

### Techniques:

| Technique     | Description                                                                                                                                                                                                                                                                                       | Analogy                                                                                                                                                                                                                                                                                              |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| TryLock       | TryLock attempts to acquire a lock without blocking. If the lock is available, it's acquired; otherwise, it returns immediately without waiting. It's like trying to grab the last slice of pizza from the box. If it's there, great, you take it. If not, you move on without waiting for more to be delivered. 🍕 | Imagine you're at a party and there's only one slice of pizza left. Instead of waiting in line, you just reach for it. If it's there, you snag it. If someone else has it, you back off and wait for another opportunity. 🎉                                         |
| Reentrant Lock| Reentrant Lock allows a thread to acquire the same lock multiple times. It keeps a count of the number of times it's locked and requires an equal number of unlocks to release it. It's like having a room key that only you can use, and you can enter and exit as many times as you want without losing access. 🔑 | Think of it like having a special key that opens a room with your favorite snacks. Each time you enter, you show the key and get in. You can leave and come back as many times as you like, and the key will always let you back in until you return it. 🚪 |
| Watchdog      | Watchdog is a mechanism to prevent deadlocks by releasing locks if a timeout is reached. It's like having a pet dog who barks if it doesn't see any movement for a while. If nobody moves for too long, it starts barking, reminding everyone to keep moving. 🐶 | Picture a watchdog keeping an eye on a group of friends playing a game. If they stop moving for too long, indicating they might be stuck or frozen, the watchdog barks, prompting them to continue playing and preventing the game from grinding to a halt. 🕒 |

Remember, understanding thread locking is crucial for writing concurrent programs that are both efficient and safe! Keep practicing and experimenting to master these concepts. 🚀

### Try Lock Example

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        if (lock.tryLock()) {
            try {
                // Critical section: Access shared resource
                System.out.println("Lock acquired successfully!");
            } finally {
                lock.unlock(); // Release the lock
            }
        } else {
            // Handle if lock acquisition fails
            System.out.println("Failed to acquire lock. Resource busy!");
        }
    }
}

```

### Reentrant Lock Example
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock(); // First lock acquisition
        try {
            // Critical section: Access shared resource
            System.out.println("First lock acquired successfully!");

            // Nested lock acquisition
            lock.lock();
            try {
                // Nested critical section: Access shared resource
                System.out.println("Second lock acquired successfully!");
            } finally {
                lock.unlock(); // Release the nested lock
                System.out.println("Second lock released!");
            }
        } finally {
            lock.unlock(); // Release the first lock
            System.out.println("First lock released!");
        }
    }
}

```


### Watch Dog Example
```java 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class WatchdogExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        boolean locked = false;
        try {
            // Attempt to acquire lock with timeout
            locked = lock.tryLock(2, TimeUnit.SECONDS);
            if (locked) {
                // Critical section: Access shared resource
                System.out.println("Lock acquired successfully!");
            } else {
                // Handle if lock acquisition times out
                System.out.println("Failed to acquire lock within timeout. Resource busy!");
            }
        } catch (InterruptedException e) {
            // Handle InterruptedException
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock(); // Release the lock if acquired
            }
        }
    }
}

```
