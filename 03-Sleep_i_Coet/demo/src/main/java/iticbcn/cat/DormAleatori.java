package iticbcn.cat;

import java.util.Random;

public class DormAleatori extends Thread{
    long lTemsStart;
    public DormAleatori(String nom){
        super(nom);
        lTemsStart = System.currentTimeMillis();
    }
    @Override
    public void run() {
        String nombre = getName();
        try {
            for (int i = 1; i <= 10; i++) {
                Random random = new Random();
                int intervalAleatori = random.nextInt(1000);
                long tempsActual = System.currentTimeMillis(); 
                long tempsTotal = tempsActual - lTemsStart;
                Thread.sleep(intervalAleatori);
                System.out.println(nombre + " (" + i + ") a dormir " + intervalAleatori + "ms total " + tempsTotal);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " ha deixat de dormir.");
        }
    }
    public static void main(String[] args) {
        String[] noms = {"Joan", "Pep"};
        for (String nom : noms) {
            new DormAleatori(nom).start();
        }
        System.out.println("------Fi del main--------");
    }
}