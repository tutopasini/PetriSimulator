package com.unisinos.petrisimulator;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class PetriSim {

    private PetriNet net;

    /**
     * Cria rede de Petri a partir de entrada Json
     * 
     * @param inputJson
     */
    public void createPetriNet(String inputJson) {
        // Cria nova rede de Petri
        net = new PetriNet(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Gson json = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(inputJson).getAsJsonObject();
        // Monta os lugares a partir do Json
        JsonArray lista = obj.getAsJsonArray("lugares");
        for (int i = 0; i < lista.size(); i++) {
            net.getLugares().add(json.fromJson(lista.get(i),Lugar.class));
        }
        // Monta as transições a partir do Json
        lista = obj.getAsJsonArray("transicoes");
        for (int i = 0; i < lista.size(); i++) {
            net.getTransicoes().add(json.fromJson(lista.get(i),Transicao.class));
        }
        // Monta os arcos a partir do Json
        lista = obj.getAsJsonArray("arcos");
        for (int i = 0; i < lista.size(); i++) {
            JsonObject arcObj = lista.get(i).getAsJsonObject();
            String entrada = arcObj.get("entrada").getAsString();
            String saida = arcObj.get("saida").getAsString();
            int peso = arcObj.get("peso").getAsInt();
            net.createArch(entrada, saida, peso);
        }
        
        net.habDesTransicoes();
     }
}
