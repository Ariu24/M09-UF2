package com.filosofs;

import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private final int num;
    private final ReentrantLock bloqueig = new ReentrantLock(true);

    public Forquilla(int num) {
        this.num = num;
    }

    public int getForquilla() {
        return num;
    }

    public boolean agafar(int idFilosof) {
        boolean agafada = bloqueig.tryLock();
        return agafada;
    }

    public void deixar() {
        if (bloqueig.isHeldByCurrentThread()) {
            bloqueig.unlock();
        }
    }
}
