package ex00;

public class Main {

    public static void main(String[] args) {
        Thread hen = new Thread(new HenTread());
        hen.start();

        Thread egg = new Thread(new EggTread());
        egg.start();



        for (int i = 0; i < 50; i++) {
            System.out.println("Human");

        }

    }
}

class EggTread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Egg");

        }
    }
}
class HenTread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Hen");

        }
    }
}