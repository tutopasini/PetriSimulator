package com.unisinos.petrisimulator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;

public class PetriNet {
    
    private List<Lugar> lugares;
    private List<Transicao> transicoes;
    private List<Arco> arcos;
    private int ciclo;
    private boolean finished;

    public PetriNet(List<Lugar> lugares, List<Transicao> transicoes, List<Arco> arcos) {
        this.lugares = lugares;
        this.transicoes = transicoes;
        this.arcos = arcos;
        ciclo = 0;
        finished = false;
    }
    
    /**
     * Cria rede de Petri a partir de entrada Json
     * 
     * @param inputJson
     */
    public void createPetriNet(String inputJson) {
        // Cria nova rede de Petri
        Gson json = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(inputJson).getAsJsonObject();
        // Monta os lugares a partir do Json
        JsonArray lista = obj.getAsJsonArray("lugares");
        for (int i = 0; i < lista.size(); i++) {
            lugares.add(json.fromJson(lista.get(i),Lugar.class));
        }
        // Monta as transições a partir do Json
        lista = obj.getAsJsonArray("transicoes");
        for (int i = 0; i < lista.size(); i++) {
            transicoes.add(json.fromJson(lista.get(i),Transicao.class));
        }
        // Monta os arcos a partir do Json
        lista = obj.getAsJsonArray("arcos");
        for (int i = 0; i < lista.size(); i++) {
            JsonObject arcObj = lista.get(i).getAsJsonObject();
            String entrada = arcObj.get("entrada").getAsString();
            String saida = arcObj.get("saida").getAsString();
            int peso = arcObj.get("peso").getAsInt();
            this.createArch(entrada, saida, peso);
        }
        
        this.habDesTransicoes();
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
    private void habDesTransicoes () {
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
        if (finished)
            return;
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
        ciclo++;
        this.habDesTransicoes();
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

    public int getCiclo() {
        return ciclo;
    }
    
    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public boolean isFinished() {
        return transicoes.stream().noneMatch((t) -> (t.isHabil()));
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
