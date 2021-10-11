package pl.edu.pw.ee;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        double[] nums = { 1.0, 2.0, 3.0, 4.0, 5.0, -5.0 };
        new InsertionSort().sort(nums);
        for (double d : nums) {
            System.out.println(String.valueOf(d));
        }
        System.out.println("Hello World!");
    }
}
