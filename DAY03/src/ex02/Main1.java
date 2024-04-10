package ex02;

public class Main1 {
//    private static int numberCount;
//   private static int threadCount;  /* для ввода через arg*/

    public static int array[] = {1,2,3,4,5,6,7,8,9,10}; /* для тестов*/
    public static int sum;  /* для тестов*/

    public static void main(String[] args) {
        int numbersCount = 10;
        int treadCount = 3;
        int result = 0;


//        if (args.length != 2
//                || !args[0].startsWith("--arraySize=")
//                || !args[1].startsWith("--threadsCount=")) {
//            System.err.println("Usage: java Program --arraySize=<size> --threadsCount=<count>");
//            System.exit(-1);
//        }
//        numberCount = Integer.parseInt(args[0].substring(12));
//        threadCount = Integer.parseInt(args[1].substring(15));
//
//        if (numbersCount <= 0 || threadCount <= 0 || numbersCount > 2_000_000) {
//            System.err.println("Invalid arguments: numbersCount should be between 1 and 2,000,000, threadCount should be greater than 0");
//            System.exit(-1);
//        }
//
//        /* для ввода через arg*/





        int numberOfElementsInOneTread = (int) Math.ceil((double) (numbersCount) / (double) (treadCount));
        int[] sums = new int[treadCount];
        for (int i = 0; i < sums.length; i++) {
            SumThread1 sumThread =new SumThread1(i * numberOfElementsInOneTread, (i + 1) * numberOfElementsInOneTread - 1);
            sumThread.start();
            try {
                sumThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sums[i] = sum;
            System.out.println("Tread " + (i + 1)  + ": " + "from " + i * numberOfElementsInOneTread + " to " + ((i + 1) * numberOfElementsInOneTread - 1) + " sum is " + sum );
            result += sums[i];

        }
        System.out.println(result);



    }
}

class SumThread1 extends Thread {
    private int start;
    private int end;

    public SumThread1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        Main1.sum = 0;
        for (int i = this.start; i <= this.end; i++) {
            if (i < Main1.array.length)
                Main1.sum += Main1.array[i];
        }
    }
}