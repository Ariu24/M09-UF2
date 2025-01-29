package com.iticbcn;

import java.util.ArrayList;
import java.util.List;

public class Organitzador {
    public static void main(String[] args){
        Esdeveniment esdeveniment = new Esdeveniment(5);
        List<Assistent> assistents = new ArrayList<Assistent>();
        for(int i =0 ; i<10; i++){
            Assistent assistent = new Assistent(esdeveniment, "Assistent" + i);
            assistents.add(assistent);
        }
        for(Assistent assistent : assistents){
            assistent.start();
        }
    }
}
