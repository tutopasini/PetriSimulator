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
        boolean foundEntrada = false, foundSaida = false;
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
                t.addSaida(a);
                break;            }
            if (saida.equals(t.getLabel()) && !foundSaida){
                a.setSaida(t);
                t.addEntrada(a);
                break;
            }
        }

    }

    /**
     * Habilida/desabilita transições
     * 
     */
    public void habDesTransicoes () {
        // Percorre transições
        for (Transicao transicao : transicoes) {
            // Percore entradas da transição
            for (Arco entrada : transicao.getEntradas()) {
                // Se peso do arco de entrada for maior que a quantidade de marcas do lugar de entrada
                if (entrada.getPeso() > ((Lugar) entrada.getEntrada()).getMarcas()) {
                    // Desabilita transição
                    transicao.setHabil(false);
                    break;
                }
            }
        }
    }
    
    
    public void step (){
        // Percorre transições
        transicoes.stream().filter((transicao) -> (transicao.isHabil())).map((transicao) -> {
            // Percorre arcos de entrada
            List<Arco> entradas = transicao.getEntradas();
            entradas.forEach((entrada) -> {
                // Decrementa cada lugar de entrada com o peso do arco
                ((Lugar) entrada.getEntrada()).decMarca(entrada.getPeso());
            });
            List<Arco> saidas = transicao.getSaidas();
            return saidas;
        }).forEachOrdered((saidas) -> {
            saidas.forEach((saida) -> {
                // Incrementa cada lugar de saída com o peso do arco
                ((Lugar) saida.getSaida()).incMarca(saida.getPeso());
            });
        }); 
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
