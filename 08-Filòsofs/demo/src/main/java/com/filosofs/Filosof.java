package com.filosofs;

import java.util.Random;

public class Filosof extends Thread {
    private int gana = 0;
    private String nom;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int nComensal;

    public int getnComensal() {
        return nComensal;
    }

    public void setnComensal(int nComensal) {
        this.nComensal = nComensal;
    }

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

    public void menjar() throws InterruptedException, Exception {
        while (true) {
            if (agafarForquilles()) {
                this.gana = 0;
                System.out.println("Filòsof: " + this.getNom() + " menja");
                try {
                    Thread.sleep(new Random().nextInt(1000) + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Filòsof: " + this.getNom() + " ha acabat de menjar");
                this.pensar();
                deixarForquilles();
                notifyAll();
            } else {
                this.gana++;
                System.out.println("Filòsof: " + this.getNom() + " gana=" + this.gana);
                try {
                    Thread.sleep(new Random().nextInt(501) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                wait();
            }
        }
    }

    private boolean agafarForquilles() throws InterruptedException, Exception {
        if (agafaForquillaEsquerra()) {
            if (agafaForquillaDreta()) {
                return true;
            } else {
                System.out.println("Filòsof: " + this.getNom() + " deixa l'esquerra (" + this.forquillaEsquerra.getForquilla() + ") i espera (dreta ocupada)");
                this.forquillaEsquerra.deixar();
            }
        }
        return false;
    }

    private boolean agafaForquillaEsquerra() throws Exception {
        if (this.forquillaEsquerra.agafar(this.nComensal)) {
            System.out.println("Filòsof: " + this.getNom() + " agafa la forquilla esquerra " + this.forquillaEsquerra.getForquilla());
            return true;
        }
        return false;
    }

    private boolean agafaForquillaDreta() throws Exception {
        if (this.forquillaDreta.agafar(this.nComensal)) {
            System.out.println("Filòsof: " + this.getNom() + " agafa la forquilla dreta " + this.forquillaDreta.getForquilla());
            return true;
        }
        return false;
    }

    private void deixarForquilles() {
        this.forquillaDreta.deixar();
        this.forquillaEsquerra.deixar();
        notifyAll();
    }

    public void pensar() {
        System.out.println("Filòsof: " + this.getNom() + " pensant");
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
            try {
                this.menjar();
                this.pensar();
            } catch (Exception e) {
            }
        }
    }

}