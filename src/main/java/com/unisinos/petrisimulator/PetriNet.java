package com.unisinos.petrisimulator;

import java.util.List;

public class PetriNet {
    
    private List<Lugar> lugares;
    private List<Transicao> transicoes;
    private List<Arco> arcos;

    public PetriNet(List<Lugar> lugares, List<Transicao> transicoes, List<Arco> arcos) {
        this.lugares = lugares;
        this.transicoes = transicoes;
        this.arcos = arcos;
    }
    
    /**
     * Cria arco a partir dos labels
     * 
     * @param entrada Label da Entidade de entrada do arco
     * @param saida Label da Entidade de saída do arco
     * @param peso Peso do arco
     */
    public void createArch (String entrada, String saida, int peso){
        boolean foundEntrada = true, foundSaida = false;
        Arco a = new Arco(peso);
        arcos.add(a);
        for (Lugar l : lugares) {
            if (entrada.equals(l.getLabel())){
                a.setEntrada(l);
                foundEntrada = true;
                break;
            }
            if (saida.equals(l.getLabel())){
                a.setSaida(l);
                foundSaida = true;
                break;
            }
        }
        for (Transicao t : transicoes) {
            if (entrada.equals(t.getLabel()) && !foundEntrada){
                a.setEntrada(t);
                break;            }
            if (saida.equals(t.getLabel()) && !foundSaida){
                a.setSaida(t);
                break;
            }
        }
    }


    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public List<Arco> getArcos() {
        return arcos;
    }

    public void setArcos(List<Arco> arcos) {
        this.arcos = arcos;
    }
    
    
}
