package com.unisinos.petrisimulator;

public class Lugar extends Entidade {
    
    private int marcas;

    /**
     * Incrementa marcas
     *
     * @param i Quantidade de marcas à incrementar
     */
    public void incMarca (int i) {
        marcas += i;
    }
    
    /**
     * Decrementa marcas
     * 
     * @param i Quantidade de marcas à decrementar
     */
    public void decMarca (int i) {
        marcas -= i;
    }
    
    
    public int getMarcas() {
        return marcas;
    }

    public void setMarcas(int marcas) {
        this.marcas = marcas;
    }
    
}
