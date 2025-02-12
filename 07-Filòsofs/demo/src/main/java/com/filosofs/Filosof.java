package com.filosofs;

import java.util.Random;

public class Filosof extends Thread {
    private int gana = 0;
    private String nom;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;

    public int getGana() {
        return gana;
    }

    public void setGana(int gana) {
        this.gana = gana;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public Filosof(String nom) {
        this.nom = nom;
    }

    public void menjar() {
        while (true) {
            if (!this.forquillaEsquerra.isEnUs()) {
                this.forquillaEsquerra.setEnUs(true);
                System.out.println("Filòsof: " + this.getNom() + " agafa la forquilla esquerra "
                        + this.getForquillaEsquerra().getForquilla());
                if (!this.forquillaDreta.isEnUs()) {
                    this.forquillaDreta.setEnUs(true);
                    System.out.println("Filòsof: " + this.getNom() + " agafa la forquilla dreta "
                            + this.getForquillaDreta().getForquilla());
                    this.gana = 0;
                    System.out.println("Filòsof: " + this.getNom() + " menja");
                    Random rand = new Random();
                    int tiempo = rand.nextInt(1000) + 1000;
                    try {
                        Thread.sleep(tiempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.forquillaDreta.setEnUs(false);
                    return;
                } else {
                    this.gana++;
                    System.out.println("Filòsof: " + this.getNom() + " gana=" + this.gana);
                    System.out.println("Filòsof: " + this.getNom() + " deixa l'esquerra ("
                            + this.getForquillaEsquerra().getForquilla() + ") i espera (dreta ocupada)");
                    this.forquillaEsquerra.setEnUs(false);
                    Random rand = new Random();
                    int tiempo = rand.nextInt(501) + 500;
                    try {
                        Thread.sleep(tiempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                this.gana++;
                System.out.println("Filòsof: " + this.getNom() + " gana=" + this.gana);
                Random rand = new Random();
                int tiempo = rand.nextInt(501) + 500;
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void pensar() {
        Random rand = new Random();
        int tiempo = rand.nextInt(1000) + 1000;
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            this.menjar();
            this.pensar();
        }

    }
}
