package com.unisinos.petrisimulator;

import java.util.List;

public class Entidade {

    String label;
    private List<Arco> entradas;
    private List<Arco> saidas;

    public Entidade(List<Arco> entradas, List<Arco> saidas, String label) {
        this.entradas = entradas;
        this.saidas = saidas;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Arco> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Arco> entradas) {
        this.entradas = entradas;
    }

    public List<Arco> getSaidas() {
        return saidas;
    }

    public void setSaidas(List<Arco> saidas) {
        this.saidas = saidas;
    }
    
}
