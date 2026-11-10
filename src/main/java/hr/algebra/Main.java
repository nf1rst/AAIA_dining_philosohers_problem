package hr.algebra;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final int NUMBER_OF_PHILOSOPHERS =5;
    static void main() {
        Philosopher[] philosphers = new Philosopher[NUMBER_OF_PHILOSOPHERS];

        Object[] forks = IntStream.range(0, NUMBER_OF_PHILOSOPHERS).mapToObj(i -> new Object()).toArray();

        try (ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS)) {
            IntStream.range(0, NUMBER_OF_PHILOSOPHERS).forEach(i -> executor.submit(() -> {
                Object leftFork = forks[i];
                Object rightFork = forks[i + 1 % NUMBER_OF_PHILOSOPHERS];


                philosphers[i] = i == NUMBER_OF_PHILOSOPHERS - 1 ?
                        new Philosopher(rightFork, leftFork, (i + 1)) :
                        new Philosopher(leftFork, rightFork, (i + 1));
                philosphers[i].start();
            }));
        }
    }
}
