package threads;

/**
 * Study the example of code in the course notes for handling uncaught exceptions.
 * Write a program, that starts 5 threads running the same code, and name the threads 1, 2,3 ,4,5.
 * Within the run method of the object have the fifth thread throw an unchecked exception immediately, and have the
 * other threadd sleep for 1 sec and then terminate cleanly. In the uncaught exception handler, have the thread sleep for 10 sec
 * and then terminate clenaly. Your main method should loop and call Thread.join on each thread in order.
 * You should see four threads join quickly and a short delay before the 5th one terminates.
 * Created by suhail on 2016-12-06.
 */
public class Threads4 {
}
