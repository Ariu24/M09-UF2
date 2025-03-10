package com.fumadors;

import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int numFumades;
    private static final int MAX_FUMADES = 3;
    private Random random;
    
    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.tabac = null;
        this.llumi = null;
        this.paper = null;
        this.numFumades = 0;
        this.random = new Random();
    }
    
    public void fuma() {
        if (tabac != null && llumi != null && paper != null) {
            try {
                System.out.println("Fumador " + id + " fumant");
                sleep(500 + random.nextInt(500));
                tabac = null;
                llumi = null;
                paper = null;
                numFumades++;
                System.out.println("Fumador " + id + " ha fumat " + numFumades + " vegades");
                
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
    public void compraTabac() {
        System.out.println("Fumador " + id + " comprant Tabac");
        tabac = estanc.venTabac();
    }
    
    public void compraPaper() {
        System.out.println("Fumador " + id + " comprant Paper");
        paper = estanc.venPaper();
    }
    
    public void compraLlumi() {
        System.out.println("Fumador " + id + " comprant Llumí");
        llumi = estanc.venLlumi();
    }
    
    public boolean haTerminado() {
        return numFumades >= MAX_FUMADES;
    }
    
    @Override
    public void run() {
        while (!haTerminado() && estanc.isObert()) {
            if (tabac == null) {
                compraTabac();
            }
            if (paper == null && tabac != null) {
                compraPaper();
            }
            if (llumi == null && tabac != null && paper != null) {
                compraLlumi();
            }
            if (tabac != null && paper != null && llumi != null) {
                fuma();
            }
        }
    }
}