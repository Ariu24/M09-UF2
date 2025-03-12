package com.iticbcn;

import java.util.LinkedList;
import java.util.Queue;

    public class Barberia extends Thread {
        private Queue<Client>llista = new LinkedList<Client>();
        private int numCadires = 3;
        public Object condBarber = new Object();
        public static Barberia barberia;

        public Barberia(int nCadires){
            this.numCadires = nCadires;
        }
        public Client seguentClient(){
            synchronized(condBarber){
                if(llista.size() == 0){
                    return null;
                }else{
                    return llista.poll();
                }
            }
        }
        public synchronized void entrarClient(Client client){
            synchronized(condBarber){
                if(llista.size() < numCadires){
                    llista.add(client);
                    condBarber.notifyAll();
                }else{
                    System.out.println("No queden cadires, client Client-" + client.getName() + " se'n va");
                }
            }
        }
        @Override
        public void run() {
            for(int i = 1;i<10;i++){
                Client client = new Client(i);
                entrarClient(client);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 10;i<20;i++){
                Client client = new Client(i);
                entrarClient(client);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public static void main(String[] args){
            barberia = new Barberia(3);
            Barber barber = new Barber("Juan");
            barber.start();
            barberia.start();
        }
    }
