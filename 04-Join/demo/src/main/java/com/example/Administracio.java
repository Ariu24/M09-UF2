package com.example;
public class Administracio {
    public static final int NUM_POBLACIO_ACTIVA = 50;
    public static Treballador[] poblacio_activa = new Treballador[NUM_POBLACIO_ACTIVA];
    public Administracio() {
        for (int i = 0; i < 50; i++) {
            poblacio_activa[i] = new Treballador("Ciutadà-"+i,25000,20,65,0,0);
        }
    }
    public static void main(String[] args) throws Exception {
        Administracio administracio = new Administracio();  // Crear instancia de Administracio
        // Arrancar un hilo para cada treballador
        for (int i = 0; i < NUM_POBLACIO_ACTIVA; i++) {
            poblacio_activa[i].start();
        }
        for (int i = 0; i < NUM_POBLACIO_ACTIVA; i++) {
            poblacio_activa[i].join();
        } 
        for (int i = 0; i < NUM_POBLACIO_ACTIVA; i++) {
            System.out.println(poblacio_activa[i].toString());
        } 
        
        
    }
}
