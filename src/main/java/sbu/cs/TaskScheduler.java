package sbu.cs;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskScheduler
{
    public static class Task implements Runnable
    {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */
        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */

        @Override
        public void run() {
            /*
            TODO
                Simulate utilizing CPU by sleeping the thread for the specified processingTime
             */
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                System.out.println(e.fillInStackTrace());
            }
        }
        public int getProcessingTime() {
            return processingTime;
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks) throws InterruptedException {
        ArrayList<String> finishedTasks = new ArrayList<>();
        tasks.sort(Comparator.comparing(Task::getProcessingTime));
        Collections.reverse(tasks);
        for(Task temp : tasks) {
            Thread thread = new Thread(temp);
            thread.start();
            try{
                thread.join();
                finishedTasks.add(temp.taskName);
            }
            catch(InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }

        /*
        TODO
            Create a thread for each given task, And then start them based on which task has the highest priority
            (highest priority belongs to the tasks that take more time to be completed).
            You have to wait for each task to get done and then start the next task.
            Don't forget to add each task's name to the finishedTasks after it's completely finished.
         */

        return finishedTasks;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
