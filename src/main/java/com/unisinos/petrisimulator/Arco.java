
package com.unisinos.petrisimulator;

public class Arco {
    
    private Entidade entrada;
    private Entidade saida;
    private int peso;

    public Arco(Entidade entrada, Entidade saida, int peso) {
        this.entrada = entrada;
        this.saida = saida;
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Entidade getEntrada() {
        return entrada;
    }

    public void setEntrada(Entidade entrada) {
        this.entrada = entrada;
    }

    public Entidade getSaida() {
        return saida;
    }

    public void setSaida(Entidade saida) {
        this.saida = saida;
    }
    
}
