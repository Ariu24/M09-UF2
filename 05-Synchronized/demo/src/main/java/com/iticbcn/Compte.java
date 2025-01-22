package com.iticbcn;
//
public class Compte {
    private static Compte instance;
    private float saldo;

    private Compte() {
        saldo = 0.0f;
    }

    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}