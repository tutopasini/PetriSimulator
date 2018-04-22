package com.unisinos.petrisimulator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author augustopasini
 */
public class PetriNet {

    private List<Lugar> lugares;
    private List<Transicao> transicoes;
    private List<Arco> arcos;
    private int ciclo;
    private int relogio;

    public PetriNet(List<Lugar> lugares, List<Transicao> transicoes, List<Arco> arcos) {
        this.lugares = lugares;
        this.transicoes = transicoes;
        this.arcos = arcos;
        ciclo = 0;
        relogio = 0;
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
            lugares.add(json.fromJson(lista.get(i), Lugar.class));
        }
        // Monta as transições a partir do Json
        lista = obj.getAsJsonArray("transicoes");
        for (int i = 0; i < lista.size(); i++) {
            transicoes.add(json.fromJson(lista.get(i), Transicao.class));
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
    public void createArch(String entrada, String saida, int peso) {
        boolean foundEntrada = false, foundSaida = false;
        Arco a = new Arco(peso);
        arcos.add(a);
        for (Lugar l : lugares) {
            if (entrada.equals(l.getLabel())) {
                a.setEntrada(l);
                l.addSaida(a);
                foundEntrada = true;
                break;
            }
            if (saida.equals(l.getLabel())) {
                a.setSaida(l);
                l.addEntrada(a);
                foundSaida = true;
                break;
            }
        }
        for (Transicao t : transicoes) {
            if (entrada.equals(t.getLabel()) && !foundEntrada) {
                a.setEntrada(t);
                t.addSaida(a);
                break;
            }
            if (saida.equals(t.getLabel()) && !foundSaida) {
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
    private void habDesTransicoes() {
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
                transicao.setHabil(true);
            }
        }
    }

    private void habDesTransicao(Transicao t) {
        for (Arco entrada : t.getEntradas()) {
            Lugar lugEntrada = (Lugar) entrada.getEntrada();
            // Se peso do arco de entrada for maior que a quantidade de marcas do lugar de entrada
            if (entrada.getPeso() > lugEntrada.getMarcas() || lugEntrada.isDone()) {
                // Desabilita transição
                t.setHabil(false);
                break;
            }
        }
    }

    /**
     * Executa um ciclo da rede
     */
    public void step() {
        if (this.isFinished()) {
            return;
        }
        ciclo++;
        // Embaralha as transições para que pegue as transições de forma aleatório
        Collections.shuffle(transicoes);
        for (Transicao transicao : transicoes) {
            // Verifica se transição pode ser disparada neste ciclo
            habDesTransicao(transicao);
            // Se transição está habilitada
            if (transicao.isHabil()) {
                // Percorre arcos de entrada
                transicao.getEntradas().forEach((entrada) -> {
                    Lugar lugEntrada = (Lugar) entrada.getEntrada();
                    // Decrementa cada lugar de entrada com o peso do arco
                    lugEntrada.decMarca(entrada.getPeso());
                    // Adiciona no relógio o tempo do lugar
                    relogio += lugEntrada.getTempo();
                    // Indica que já disparou uma transição para este lugar neste ciclo
                    lugEntrada.setDone(true);
                });
                transicao.getSaidas().forEach((saida) -> {
                    // Incrementa cada lugar de saída com o peso do arco
                    ((Lugar) saida.getSaida()).incMarca(saida.getPeso());
                });
            }
        }
        // Habilita/desabilita todas as transições
        this.habDesTransicoes();
        // Redefine todos os lugares como ainda não finalizados no ciclo
        lugares.forEach((lugar) -> {
            lugar.setDone(false);
        });
    }
    
    public boolean isFinished() {
        return transicoes.stream().noneMatch((t) -> (t.isHabil()));
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
    
    public int getRelogio() {
        return relogio;
    }

    public void setRelogio(int relogio) {
        this.relogio = relogio;
    }
}
