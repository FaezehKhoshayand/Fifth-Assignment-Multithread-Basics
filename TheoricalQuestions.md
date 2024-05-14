
## What will be printed after interrupting the thread?

In the main method of this code we create a thread and start it (the run method starts) but before it gets finished I mean 10 seconds of sleep,the main thread reads the next line which interrupts the thread that was started thread.interrup() throws an exception that is catched in the run method and then the "Thread will be finished here!!!" is printed since the sout instructuon is located in the finally block.
so the result will be:
Thread was interrupted!
Thread will be finished here!!!

```text
public static class SleepThread extends Thread {
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted!");
            } finally {
                System.out.println("Thread will be finished here!!!");
            }
        }
    }

    public static void main(String[] args) {
        SleepThread thread = new SleepThread();
        thread.start();
        thread.interrupt();
    }
```
## In Java, what would be the outcome if the run() method of a Runnable object is invoked directly, without initiating it inside a Thread object?

In this case we won't have a new thread cause we're not creating it .we have just made a class with a method. we create an instance then call the run method and we are still in the thread main.
So th eoutcome is:
Running in: main
```text
public class DirectRunnable implements Runnable {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        DirectRunnable runnable = new DirectRunnable();
        runnable.run();
    }
}
```
## Elaborate on the sequence of events that occur when the join() method of a thread (let's call it Thread_0) is invoked within the Main() method of a Java program.

A new thread is started here.The main thread waits for the new thread to get finished and then prints "Back to: main".If we had something that throwed InterruptedException the e.printStackTrace() would be printed.
The outcome is:
Running in: Thread_0
Back to: main

```text
public class JoinThread extends Thread {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        JoinThread thread = new JoinThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Back to: " + Thread.currentThread().getName());
    }
}
```

