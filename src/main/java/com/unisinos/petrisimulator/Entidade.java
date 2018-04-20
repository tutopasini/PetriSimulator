package com.unisinos.petrisimulator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author augustopasini
 */
public class Entidade {

    String label;
    private List<Arco> saidas;
    private List<Arco> entradas; 
    /**
     * Adiciona arco de saída da transição
     * 
     * @param arco Arco de saída da transição
     */
    public void addSaida (Arco arco) {
        if (saidas == null) {
            saidas = new ArrayList<>();
        }
        saidas.add(arco);
    }    

    /**
     * Adiciona arco de entrada da transição
     * 
     * @param arco Arco de entrada da transição
     */
    public void addEntrada (Arco arco) {
        if (entradas == null) {
            entradas = new ArrayList<>();
        }
        entradas.add(arco);
    }

    public List<Arco> getSaidas() {
        return saidas;
    }

    public void setSaidas(List<Arco> saidas) {
        this.saidas = saidas;
    }

    public List<Arco> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Arco> entradas) {
        this.entradas = entradas;
    }    

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
}
