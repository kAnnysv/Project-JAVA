package ex01;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ProducerConsumer pc = new ProducerConsumer();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }

}

class ProducerConsumer {

    public void producer() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            synchronized (this){
                System.out.println("Egg");
                wait();

            }
        }
    }

    public void consumer() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            synchronized (this){
                System.out.println("Hen");
                notify();

            }
            Thread.sleep(100);
        }
    }


}
