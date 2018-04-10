package com.unisinos.petrisimulator;

import java.util.List;


public class Transicao extends Entidade {

    public Transicao(List<Arco> entradas, List<Arco> saidas, String label) {
        super(entradas, saidas, label);
    }    
    
}
