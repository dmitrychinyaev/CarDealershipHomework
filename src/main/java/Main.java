import java.util.LinkedList;
import java.util.List;

public class Main {
    final static int produceCar = 2500;
    final static int desireToBuy1 = 1000;
    final static int desireToBuy2 = 1500;
    final static int desireToBuy3 = 2000;
    static int count = 0;

    public static void main(String[] args) {

        List<String> carDealership = new LinkedList<>();
        String[] carModel = {"Toyota", "Ford", "Kia", "Mazda"};


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                count ++;
                synchronized (carDealership){
                    String model = carModel[(int) (Math.random() * 4)];
                    carDealership.add(model);
                    carDealership.notify();
                    System.out.println("Производитель " + model + " выпустил авто");
                    try {
                        Thread.sleep(produceCar);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                synchronized (carDealership) {
                    System.out.println("Покупатель 1 зашёл в автосалон");
                    if (carDealership.isEmpty()) {
                        System.out.println("Машин нет");
                        try {
                            carDealership.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Покупатель 1 уехал на новеньком " + carDealership.remove(0));
                    try {
                        Thread.sleep(desireToBuy1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count == 10 || carDealership.isEmpty()){
                        break;
                    }
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                synchronized (carDealership) {
                    System.out.println("Покупатель 2 зашёл в автосалон");
                    if (carDealership.isEmpty()) {
                        System.out.println("Машин нет");
                        try {
                            carDealership.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Покупатель 2 уехал на новеньком " + carDealership.remove(0));
                    try {
                        Thread.sleep(desireToBuy2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count == 10 || carDealership.isEmpty()){
                        break;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (carDealership) {
                    System.out.println("Покупатель 3 зашёл в автосалон");
                    if (carDealership.isEmpty()) {
                        try {
                            System.out.println("Машин нет");
                            carDealership.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Покупатель 3 уехал на новеньком " + carDealership.remove(0));
                    try {
                        Thread.sleep(desireToBuy3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count == 10 || carDealership.isEmpty()){
                        break;
                    }
                }
            }
        }).start();

    }
}
