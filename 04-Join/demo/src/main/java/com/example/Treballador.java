package com.example;

import java.util.Random;

public class Treballador extends Thread{
    public int SOU_ANUAL_BRUT;
    public int edat_inici_treballador;
    public int edat_fi_treballador;
    public int edat_actual;
    public double cobrat;
    public Random rnd;
    public Treballador(String nom, int SOU_ANUAL_BRUT, int edat_inici_treballador, int edat_fi_treballador, int edad_actual, double cobrat){
        super(nom);
        this.SOU_ANUAL_BRUT = SOU_ANUAL_BRUT;
        this.edat_inici_treballador = edat_inici_treballador;
        this.edat_fi_treballador = edat_fi_treballador;
        this.edat_actual = edad_actual;
        this.cobrat = cobrat;
        this.rnd = new Random();

    }
    public int getEdat_actual() {
        return edat_actual;
    }

    public double getCobrat() {
        return cobrat;
    }

    public Treballador(String nom){
        super(nom);
    }

    public void cobra(){
        this.cobrat = this.cobrat + (SOU_ANUAL_BRUT /12);
    }
    public void pagaImpostos(){
        this.cobrat = this.cobrat - ((SOU_ANUAL_BRUT /12) * 0.24); 
    }

    @Override
    public void run(){
        while(this.edat_actual != this.edat_fi_treballador){
            this.edat_actual++;
            if(this.edat_actual > 20){
                for(int i=0; i<12;i++){
                    this.cobra();
                    this.pagaImpostos();
                }
            }
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public String toString() {
        // Formatea cobrat para que se muestre con dos decimales
        String result = this.getName() + " -> edat: " + this.edat_actual + " / total: " + String.format("%.2f", this.cobrat);
        return result;
    }

}
