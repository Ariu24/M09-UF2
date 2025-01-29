package com.iticbcn;

import java.util.List;
import java.util.ArrayList;

public class Esdeveniment {

    private int numeroPlacesMaxim;
    private List<Assistent> assistents = new ArrayList<>();
    private int numeroPlacesDisponibles;

    public List<Assistent> getAssistents() {
        return assistents;
    }

    public void setAssistents(List<Assistent> assistents) {
        this.assistents = assistents;
    }

    public int getNumeroPlacesDisponibles() {
        return numeroPlacesDisponibles;
    }

    public void setNumeroPlacesDisponibles(int numeroPlacesDisponibles) {
        this.numeroPlacesDisponibles = numeroPlacesDisponibles;
    }

    public int getNumeroPlacesMaxim() {
        return numeroPlacesMaxim;
    }

    public void setNumeroPlacesMaxim(int numeroPlacesMaxim) {
        this.numeroPlacesMaxim = numeroPlacesMaxim;
    }

    // Constructor
    public Esdeveniment(int numeroPlacesMaxim) {
        this.numeroPlacesMaxim = numeroPlacesMaxim;
        this.numeroPlacesDisponibles = numeroPlacesMaxim;
    }

    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {
        while (numeroPlacesDisponibles == 0) {
            System.out.println("Assistent-" + assistent.getNomAssistent() + " no ha pogut fer una reserva. Places disponibles: " + numeroPlacesDisponibles);
            this.wait();
        }
        if (!assistents.contains(assistent)) {
            assistents.add(assistent);
            numeroPlacesDisponibles--;
            System.out.println("Assistent-" + assistent.getNomAssistent() + " ha pogut fer una reserva. Places disponibles: " + numeroPlacesDisponibles);
            this.notifyAll();
        } 
    }

    public synchronized void cancelaReserva(Assistent assistent) throws InterruptedException {
        while (numeroPlacesDisponibles == numeroPlacesMaxim) {
            this.wait();
        }
        if (assistents.contains(assistent)) {
            assistents.remove(assistent);
            numeroPlacesDisponibles++;
            System.out.println("Assistents-" + assistent.getNomAssistent() + " ha cancel·lat una reserva. Places disponibles: " + numeroPlacesDisponibles);
            this.notifyAll();
        } else {
            System.out.println("Assistent-" + assistent.getNomAssistent() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + numeroPlacesDisponibles);
        }
    }
}
