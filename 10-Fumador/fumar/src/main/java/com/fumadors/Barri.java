package com.fumadors;

public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;
    
    public Barri() {
        this.estanc = new Estanc();
        this.fumadors = new Fumador[3];
        for (int i = 0; i < fumadors.length; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }
    
    public void iniciar() {
        for (Fumador fumador : fumadors) {
            fumador.start();
        }
        estanc.start();
        try {
            for (Fumador fumador : fumadors) {
                fumador.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        estanc.tancarEstanc();
        try {
            estanc.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.iniciar();
    }
}