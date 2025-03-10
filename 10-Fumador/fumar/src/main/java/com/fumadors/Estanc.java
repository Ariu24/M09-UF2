package com.fumadors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    private List<Tabac> tabacList;
    private List<Llumi> llumiList;
    private List<Paper> paperList;
    private boolean obert;
    private Random random;
    
    public Estanc() {
        this.tabacList = new ArrayList<>();
        this.llumiList = new ArrayList<>();
        this.paperList = new ArrayList<>();
        this.obert = true;
        this.random = new Random();
        System.out.println("Estanc obert");
    }
    
    public synchronized void nouSubministrament() {
        int tipus = random.nextInt(3);
        switch (tipus) {
            case 0:
                addTabac();
                break;
            case 1:
                addLlumi();
                break;
            case 2:
                addPaper();
                break;
        }
        notifyAll();
    }
    
    public synchronized void addTabac() {
        Tabac tabac = new Tabac();
        tabacList.add(tabac);
        System.out.println("Afegint tabac");
    }
    
    public synchronized void addLlumi() {
        Llumi llumi = new Llumi();
        llumiList.add(llumi);
        System.out.println("Afegint Llumí");
    }
    
    public synchronized void addPaper() {
        Paper paper = new Paper();
        paperList.add(paper);
        System.out.println("Afegint Paper");
    }
    
    public synchronized Tabac venTabac() {
        while (tabacList.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        if (!obert && tabacList.isEmpty()) {
            return null;
        }
        return tabacList.remove(0);
    }
    
    public synchronized Paper venPaper() {
        while (paperList.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        if (!obert && paperList.isEmpty()) {
            return null;
        }
        return paperList.remove(0);
    }
    
    public synchronized Llumi venLlumi() {
        while (llumiList.isEmpty() && obert) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        if (!obert && llumiList.isEmpty()) {
            return null;
        }
        return llumiList.remove(0);
    }
    
    public synchronized void tancarEstanc() {
        this.obert = false;
        System.out.println("Estanc tancat");
        notifyAll();
    }
    
    public boolean isObert() {
        return obert;
    }
    
    @Override
    public void run() {
        while (obert) {
            nouSubministrament();
            try {
                sleep(500 + random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}