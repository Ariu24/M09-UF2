package iticbcn.cat;

import java.util.Random;

public class Motor extends Thread {
    private int iPotenciaActual = 0;
    private int iPotenciaObjectiu = 0;

    public Motor(String nom) {
        super(nom);
    }

    public void setiPotenciaActual(int potencia) {
        this.iPotenciaActual = potencia;
    }

    public void setiPotenciaObjectiu(int potencia) {
        this.iPotenciaObjectiu = potencia;
    }

    @Override
    public void run() {
        try {
            String nombre = getName();
            while(true){
                while (iPotenciaActual != iPotenciaObjectiu) {
                    if (iPotenciaActual < iPotenciaObjectiu) {
                        iPotenciaActual++;
                        System.out.println(nombre + " : Incre. Objectiu : " + iPotenciaObjectiu + " Actual : " + iPotenciaActual);
                    } else if (iPotenciaActual > iPotenciaObjectiu) {
                        iPotenciaActual--;
                        System.out.println(nombre + " : Decre. Objectiu : " + iPotenciaObjectiu + " Actual : " + iPotenciaActual);
                    }
                    Thread.sleep(new Random().nextInt(2000));
                }
                break;
            }
            System.out.println(nombre + " : FerRes Objectiu : " + iPotenciaObjectiu + " Actual : " + iPotenciaActual);
        } catch (InterruptedException e) {
            System.out.println(getName() + " s'ha trencat el motor.");
        }
    }
}
