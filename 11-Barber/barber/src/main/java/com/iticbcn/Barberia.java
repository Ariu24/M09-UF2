package com.iticbcn;

import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> salaEspera = new LinkedList<Client>();
    private int numCadires;
    public Object condBarber = new Object();
    private static Barberia instance;

    public Barberia(int numCadires) {
        this.numCadires = numCadires;
    }

    public static Barberia getInstance() {
        return instance;
    }

    public Client seguentClient() {
        if (salaEspera.isEmpty()) {
            return null;
        } else {
            return salaEspera.poll();
        }
    }

    public void entrarClient(Client client) {
        synchronized(condBarber) {
            if (salaEspera.size() < numCadires) {
                salaEspera.add(client);
                System.out.println(client.getNom() + " en espera");
                condBarber.notify();
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            Client client = new Client(i);
            entrarClient(client);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 11; i <= 20; i++) {
            Client client = new Client(i);
            entrarClient(client);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        instance = new Barberia(3);
        Barber barber = new Barber("Pepe");
        barber.start();
        instance.start();
    }
}