package com.filosofs;


public class Taula {
    private Filosof[] filosofs;
    private Forquilla[] forquilles;
    public Taula(){}
    public Taula(int numFilosofes) {
        filosofs = new Filosof[numFilosofes];
        forquilles = new Forquilla[numFilosofes];
        for (int i = 0; i < numFilosofes; i++) {
            forquilles[i] = new Forquilla(i);
        }
        for (int i = 0; i < numFilosofes; i++) {
            filosofs[i] = new Filosof("fil"+i);
            filosofs[i].setForquillaEsquerra(forquilles[i]);
            //per calcular el filosof que té al costat ( 5    /    5  =   0  ) llavors el filosof 0 es qui te la forquilla
            filosofs[i].setForquillaDreta(forquilles[(i + 1) % numFilosofes]);
        }   
    }
    public void showTaula(){
        for (int i = 0; i < filosofs.length; i++) {
            System.out.println("Comensal: " + filosofs[i].getName() + " esq: " + (filosofs[i].getForquillaEsquerra()).getForquilla() + " dret: " + filosofs[i].getForquillaDreta().getForquilla());
        }
    }
    public void cridarATaula(){
        for (int i = 0; i < filosofs.length; i++) {
            filosofs[i].start();
        }
    } 
    public static void main(String[] args) {

        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
        
    }
}
