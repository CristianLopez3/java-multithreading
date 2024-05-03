## 🧵 Java Thread Join Method Notes

| **Aspect**       | **Description**                                                                                                 |
|------------------|-----------------------------------------------------------------------------------------------------------------|
| **Function**     | The `join()` method in Java is used to wait for the completion of a thread.                                      |
| **Syntax**       | `public final void join() throws InterruptedException`                                                         |
| **Parameters**   | None                                                                                                             |
| **Returns**      | Void                                                                                                             |
| **Behavior**     | The calling thread waits until the thread on which `join()` is called has completed its execution.              |
| **Overloads**    | There are overloaded versions of `join()` that allow specifying a timeout or waiting for a specific duration.  |
| **Exceptions**   | Throws `InterruptedException` if the thread is interrupted while waiting.                                       |
| **Usage**        | Typically used to coordinate the execution order of threads or to ensure certain tasks are completed before proceeding. |
| **Advantages**   | - Facilitates synchronization between threads. <br> - Allows for orderly execution of concurrent tasks.          |
| **Disadvantages**| - Can lead to blocking if not used carefully. <br> - May cause performance issues if misused.                   |

**Example**
```java
public static void main(String[] args) {
    Thread thread = new Thread(() -> {
        // Perform some task                                                                                        
    });
    thread.start();
    try {
        thread.join(); // Wait for thread to complete                                                              
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

```
