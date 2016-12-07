package javaconcurrency;

/**
 * Created by suhail on 2016-12-07.
 */
public class Task {
    public static final int LIMIT = 10;

    private static final int TIME_MIN = 250;
    private static final int TIME_SCALE = 100;

    private int number;
    private int complexity;
    private final View view;

    public Task(int number, int complexity, View view) {
        this.number = number;
        this.complexity = complexity;
        this.view = view;
    }

    public void execute(int param) {
        int result = complexity * (param % LIMIT);
        try {
            Thread.sleep(TIME_MIN + complexity * TIME_SCALE);
        } catch(InterruptedException e) {}
        view.acceptResult(this, result);
    }

    public int getNumber() {
        return number;
    }
}
