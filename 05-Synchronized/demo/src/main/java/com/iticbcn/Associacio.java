package com.iticbcn;

public class Associacio {
    private static final int numSocis = 1000;
    private Soci[] socis;

    public Associacio() {
        socis = new Soci[numSocis];
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci();
        }
    }

    public void iniciaCompteTempsSocis() {
        Thread[] threads = new Thread[numSocis];
        for (int i = 0; i < numSocis; i++) {
            threads[i] = new Thread(socis[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mostraBalancComptes() {
        Compte compte = Compte.getInstance();
        System.out.printf("Saldo: %.2f%n", compte.getSaldo());
    }

    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.mostraBalancComptes();
    }
}