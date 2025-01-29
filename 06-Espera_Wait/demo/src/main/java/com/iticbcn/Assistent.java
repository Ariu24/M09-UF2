package com.iticbcn;

import java.util.Random;

public class Assistent extends Thread {
    private Esdeveniment esdeveniment;
    private String nomAssistent;
    public String getNomAssistent() {
        return nomAssistent;
    }

    public void setNomAssistent(String nomAssistent) {
        this.nomAssistent = nomAssistent;
    }

    public Assistent(Esdeveniment esdeveniment, String nomAssistent) {
        this.esdeveniment = esdeveniment;
        this.nomAssistent = nomAssistent;
    }

    @Override
    public void run(){
        Random rnd = new Random();
        while(true){
            if(rnd.nextInt(1000) % 2 == 0){
                try {
                    this.esdeveniment.cancelaReserva(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    this.esdeveniment.ferReserva(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
