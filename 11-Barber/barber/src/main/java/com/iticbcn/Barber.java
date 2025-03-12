package com.iticbcn;

import java.util.Random;

public class Barber extends Thread {

    public Barber(String nom) {
        super(nom);
    }

    @Override
    public synchronized void run() {
        while (true) {
            synchronized(Barberia.barberia.condBarber){
                Client client = Barberia.barberia.seguentClient();
                if (client != null) {
                    System.out.println(getName() + " està tallant el cabell a " + client.getName());
                    Random rand = new Random();
                    int tempsAleatori = rand.nextInt(101);
                    int tempsTotal = 900 + tempsAleatori;
                    try {
                        Thread.sleep(tempsTotal);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }else if (client == null){
                    try{
                        System.out.println(getName() + " dorm");
                        Barberia.barberia.condBarber.wait();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        }
    }
}
