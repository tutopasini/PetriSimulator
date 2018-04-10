package com.unisinos.petrisimulator;

import com.google.gson.Gson;

public class PetriSim {
    
    private static PetriNet net;
    
    public static void createPetriNet(String inputJson) {
        // Cria nova rede de Petri
        Gson json = new Gson();
        net = json.fromJson(inputJson, PetriNet.class);

        
    }
}
