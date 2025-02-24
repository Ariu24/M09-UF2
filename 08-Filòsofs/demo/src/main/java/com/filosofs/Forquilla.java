package com.filosofs;

public class Forquilla {
    private int forquilla;
    private int propietari;
    private final int LLIURE = -1;

    public int getLLIURE() {
        return LLIURE;
    }

    public int getPropietari() {
        return propietari;
    }
    
    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }

    public int getForquilla() {
        return forquilla;
    }

    public void setForquilla(int forquilla) {
        this.forquilla = forquilla;
    }

    public boolean agafar(int propietari) {
        if (this.propietari == LLIURE) {
            this.propietari = propietari;
            return true;
        }
        return false;
    }

    public void deixar() {
        this.propietari = LLIURE;
    }

    public Forquilla(int forquilla) {
        this.forquilla = forquilla;
        this.propietari = LLIURE;
    }
}