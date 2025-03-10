package com.filosofs;

import java.util.Random;

public class Filosof extends Thread {
    private long iniciGana;
    private long fiGana;
    private long gana;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int nComensal;

    public int getnComensal() {
        return nComensal;
    }

    public void setnComensal(int nComensal) {
        this.nComensal = nComensal;
    }

    public long getGana() {
        return gana;
    }

    public void setGana(int gana) {
        this.gana = gana;
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
        super(nom);
        iniciGana = System.currentTimeMillis();
        fiGana = System.currentTimeMillis();
    }

    public void menjar() throws InterruptedException, Exception {
        agafarForquilles();
        fiGana = System.currentTimeMillis();
        calcularGana();
        System.out.println("Filòsof: " + this.getName() + " menja amb gana " + this.gana);
        try {
            Thread.sleep(new Random().nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filòsof: " + this.getName() + " ha acabat de menjar");
        deixarForquilles();
    }

    private void agafarForquilles() throws InterruptedException, Exception {
        boolean teEsquerra = false;
        while (true) {
            if (!teEsquerra) {
                teEsquerra = agafaForquillaEsquerra();
                if (teEsquerra) {
                    System.out.println("Filòsof: " + this.getName() + " agafa la forquilla esquerra " + this.forquillaEsquerra.getForquilla());
                }
            }
            if (teEsquerra) {
                boolean tedreta = agafaForquillaDreta();
                if (tedreta) {
                    System.out.println("Filòsof: " + this.getName() + " agafa la forquilla dreta " + this.forquillaDreta.getForquilla());
                    return;
                } else {
                    this.forquillaEsquerra.deixar();
                    teEsquerra = false;
                    System.out.println("Filòsof: " + this.getName() + " deixa l'esquerra (" + this.forquillaEsquerra.getForquilla() + ") i espera (dreta ocupada)");
                    Thread.sleep(new Random().nextInt(501) + 500);
                }
            }
        }
    }

    private boolean agafaForquillaEsquerra() throws Exception {
        return this.forquillaEsquerra.agafar(this.nComensal);
    }

    private boolean agafaForquillaDreta() throws Exception {
        return this.forquillaDreta.agafar(this.nComensal);
    }

    private void deixarForquilles() {
        this.forquillaDreta.deixar();
        this.forquillaEsquerra.deixar();
    }
    
    private void calcularGana() {
        gana = ((fiGana - iniciGana) / 1000);
    }

    public void pensar() {
        iniciGana = System.currentTimeMillis();
        System.out.println("Filòsof: " + this.getName() + " pensant");
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
                e.printStackTrace();
            }
        }
    }
}