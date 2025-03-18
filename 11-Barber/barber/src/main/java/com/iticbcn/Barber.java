package com.iticbcn;

import java.util.Random;

public class Barber extends Thread {
    private String nom;

    public Barber(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        while (true) {
            Client client;
            synchronized(Barberia.getInstance().condBarber) {
                client = Barberia.getInstance().seguentClient();
                if (client == null) {
                    try {
                        System.out.println("Ningú en espera");
                        System.out.println("Barber " + nom + " dormint");
                        Barberia.getInstance().condBarber.wait();
                        client = Barberia.getInstance().seguentClient();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            if (client != null) {
                System.out.println("Li toca al client " + client.getNom());
                System.out.println("Tallant cabell a " + client.getNom());
                client.tallarseElCabell();
                Random rand = new Random();
                int tempsAleatori = rand.nextInt(100);
                int tempsTotal = 900 + tempsAleatori;
                try {
                    Thread.sleep(tempsTotal);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}