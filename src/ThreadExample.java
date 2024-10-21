import java.util.ArrayList;
import java.util.List;
public class ThreadExample {
    private static final int NUM_THREADS = 10;
    private static final int NUM_ITERATIONS = 1000;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        // Create threads that add elements to the list
        for (int i = 0; i < NUM_THREADS / 2; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    list.add(j);
                }
            });
        }

        // Create threads that remove elements from the list
        for (int i = NUM_THREADS / 2; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    if (!list.isEmpty()) {
                        list.remove(0);
                    }
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Print the final size of the list
        System.out.println("Final size of the list: " + list.size());
    }
}
