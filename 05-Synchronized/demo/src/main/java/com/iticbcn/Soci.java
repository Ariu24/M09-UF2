package com.iticbcn;

import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio = 10f;
    private int esperaMax = 100;
    private Random random;
    private int maxAnys = 10;

    public Soci() {
        compte = Compte.getInstance();
        random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < maxAnys; i++) {
            for (int x = 0; x < 12; x++) {
                if (x % 2 == 0) {
                    synchronized (compte) {
                        float current = compte.getSaldo();
                        current += aportacio;
                        compte.setSaldo(current);
                    }
                } else {
                    synchronized (compte) {
                        float current = compte.getSaldo();
                        current -= aportacio;
                        compte.setSaldo(current);
                    }
                }
                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}