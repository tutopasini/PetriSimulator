package com.unisinos.petrisimulator;

public class PetriNet {
    
    private Lugar origem;

    public PetriNet(Lugar origem) {
        this.origem = origem;
    }

    public Lugar getOrigem() {
        return origem;
    }

    public void setOrigem(Lugar origem) {
        this.origem = origem;
    }    
    
}
