package com.unisinos.petrisimulator;

/**
 *
 * @author augustopasini
 */
public class Lugar extends Entidade {

    private int marcas;
    private int tempo;
    private boolean done;

    public Lugar(){
        done = false;
    }
    /**
     * Incrementa marcas
     *
     * @param i Quantidade de marcas à incrementar
     */
    public void incMarca(int i) {
        marcas += i;
    }

    /**
     * Decrementa marcas
     *
     * @param i Quantidade de marcas à decrementar
     */
    public void decMarca(int i) {
        if (marcas >= i) {
            marcas -= i;
        }
    }

    public int getMarcas() {
        return marcas;
    }

    public void setMarcas(int marcas) {
        this.marcas = marcas;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
