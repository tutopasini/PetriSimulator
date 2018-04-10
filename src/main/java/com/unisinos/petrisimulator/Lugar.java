package com.unisinos.petrisimulator;

import java.util.List;

public class Lugar extends Entidade {
    
    private int marcas;

    public Lugar(int marcas, List<Arco> entradas, List<Arco> saidas, String label) {
        super(entradas, saidas, label);
        this.marcas = marcas;
    }
    
    public int getMarcas() {
        return marcas;
    }

    public void setMarcas(int marcas) {
        this.marcas = marcas;
    }
    
}
