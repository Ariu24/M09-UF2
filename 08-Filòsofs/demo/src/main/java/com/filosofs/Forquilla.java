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

    private boolean enUs = false;  
    public boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public void deixar() {
        enUs = false;
    }
    public boolean isEnUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }

    public Forquilla(int forquilla) {
        this.forquilla = forquilla;
    }  
}
