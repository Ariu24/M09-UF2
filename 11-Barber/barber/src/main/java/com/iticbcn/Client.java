package com.iticbcn;

public class Client extends Thread {
    public Client(int numero){
        super(Integer.toString(numero));
    }

    public String getNom(){return super.getName();}
    
    public void tallarseElCabell(){
        
        
    }
}
