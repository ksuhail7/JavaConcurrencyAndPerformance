package javaconcurrency;

/**
 * Created by suhail on 2016-12-07.
 */
public class View {
    public static void println(String s) {
        System.out.println(s);
    }

    public void displayResults() {

    }

    public void acceptResult(Task task, int value) {
        println("Task " + task.getNumber() + " finished result = " + value);
    }
}
