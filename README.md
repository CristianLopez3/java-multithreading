# Asynchronous Programming in Java :hourglass_flowing_sand:


### Important Concepts 🧠

1. [_Atomic Operations_](concepts/ATOMIC.md)
2. [_Join method_](concepts/JOIN.md)
3. [**_Performance and latency_**](concepts/PERFORMANCE.md)
4. [_Volatile keyword_](concepts/VOLATILE.md)
5. [_Interrupt threads_](concepts/TERMINATION_THREAD.md)
6. [_Synchronized keyword_](concepts/SYNCHRONIZED.md)
7. [_Deadlocks_](concepts/DEADLOCKS.md)
8. [_Locking_](concepts/LOCKING.md)
9. [**_Semaphore_**](concepts/SEMAPHORE.md)
10. [_Inter Thread Communication_](concepts/INTER_THREAD_COMMUNICATION.md)
11. [_Blocking IO_](concepts/BLOCKING_IO.md)
12. [_Non-blocking IO_](concepts/NON_BLOCKING_IO.md)
13. [**_Virtual ThreadS_**](concepts/VIRTUAL_THREADS.md)
14. [Concurrency vs. Parallelism](#concurrency-vs-parallelism)


Asynchronous programming, or **async** for short, is a programming paradigm where operations are executed independently of the main program flow. This means that your program doesn't have to wait for these operations to complete before moving on to the next operation.

## Importance :dart:

Async is important for improving the **performance** and **responsiveness** of your applications. It allows your program to continue executing other tasks while waiting for IO operations (like network requests or reading from a database) to complete. This can greatly improve the user experience in your applications.

## Implementation :wrench:

In Java, you can implement async programming using **Threads**. A Thread is a separate path of execution in your program. Java provides a `Thread` class that you can extend to create your own threads.

Here's a simple example of creating a new thread in Java:

```java
public static void main(String[] args) {
    Thread thread = new Thread(() -> {
        // Code to be executed in new thread
    });
    thread.start();
}
```

## Advantages :+1:

- **Improved performance**: By performing IO operations asynchronously, your program can continue executing other tasks, improving overall performance.
- **Improved responsiveness**: In user-facing applications, async programming can prevent your application from freezing while waiting for long-running operations to complete.

## Disadvantages :-1:

- **Complexity**: Async programming can make your code more complex and harder to reason about. You have to consider things like synchronization and thread-safety.
- **Resource usage**: Each thread consumes resources (like memory), so creating a large number of threads can lead to high resource usage.

Remember, async programming is a powerful tool, but like all tools, it should be used judiciously. Always consider the trade-offs when deciding whether to use async in your applications.

---

## Concurrency vs. Parallelism  {#concurrency-vs-parallelism}

### Concurrency

Concurrency refers to the ability of a system to execute multiple tasks or processes simultaneously, or appear to do so. In concurrent systems, tasks can start, run, and complete in overlapping periods, making it seem like they are executing simultaneously.

**🤔 What is it?**
Concurrency allows multiple tasks to be executed in overlapping time periods.

**🛠️ Why is it used?**
Concurrency is used to improve system responsiveness, resource utilization, and overall throughput.

**⏰ When to use it?**
Concurrency is suitable for applications requiring multitasking, such as web servers, multitasking operating systems, and asynchronous programming.

**👍 Advantages**
- Improved responsiveness
- Better resource utilization
- Increased throughput

**👎 Disadvantages**
- Increased complexity
- Potential for race conditions and synchronization issues

### Parallelism

Parallelism involves the simultaneous execution of multiple tasks or processes using multiple physical or virtual processors. In parallel systems, tasks are truly executed simultaneously, leveraging the available computational resources efficiently.

**🤔 What is it?**
Parallelism enables tasks to be executed simultaneously using multiple processors or cores.

**🛠️ Why is it used?**
Parallelism is used to improve computation speed and handle computationally intensive tasks more efficiently.

**⏰ When to use it?**
Parallelism is suitable for tasks that can be divided into independent subtasks, such as matrix multiplication, image processing, and scientific computations.

**👍 Advantages**
- Faster computation speed
- Efficient utilization of multiple processors
- Scalability

**👎 Disadvantages**
- Complexity in task decomposition and synchronization
- Overhead due to communication and coordination between parallel tasks

### Differences

| Aspect          | Concurrency                   | Parallelism                  |
|-----------------|-------------------------------|------------------------------|
| Execution       | Overlapping time periods      | Simultaneous execution       |
| Resource Usage  | Shared resources               | Dedicated resources          |
| Synchronization| Often required                 | Less required                |
| Complexity      | Moderate                       | Higher                       |

### Analogy to Understand:

Think of concurrency like a multitasking chef in a kitchen. The chef can chop vegetables, stir a sauce, and boil pasta simultaneously, switching between tasks to keep everything moving smoothly. This represents concurrency, where tasks overlap in time but are managed by a single resource (the chef).

On the other hand, parallelism is akin to having multiple chefs working in parallel in the kitchen. Each chef has their own workstation and can independently prepare different dishes at the same time. This represents parallelism, where tasks are truly executed simultaneously using dedicated resources (individual chefs and workstations).


