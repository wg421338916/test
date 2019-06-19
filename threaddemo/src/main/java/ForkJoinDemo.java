import javax.sound.midi.Soundbank;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-19 14:59
 **/
public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool p = new ForkJoinPool();
        ForkJoinTask<Integer> submit = p.submit(new CalculateRecursiveTask(0, 1000));

        try {
            Integer integer = submit.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class CalculateRecursiveTask extends RecursiveTask<Integer> {
        private Integer start;
        private Integer end;
        private Integer max = 3;

        public CalculateRecursiveTask(Integer s, Integer e) {
            this.start = s;
            this.end = e;
        }


        @Override
        protected Integer compute() {
            if (end - start > 3) {
                int middle = (start + end) / 2;
                CalculateRecursiveTask left = new CalculateRecursiveTask(start, middle);
                CalculateRecursiveTask right = new CalculateRecursiveTask(middle + 1, end);

                left.fork();
                right.fork();

                return left.join() + right.join();
            } else {
                return IntStream.rangeClosed(start,end).sum();
            }
        }
    }
}
