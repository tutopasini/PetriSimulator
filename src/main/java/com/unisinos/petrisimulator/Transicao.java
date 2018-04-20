package com.unisinos.petrisimulator;


/**
 *
 * @author augustopasini
 */
public class Transicao extends Entidade {

    private boolean habil = true;
    private int cicloAtual;


    public void incCicloAtual(){
        cicloAtual++;
    }
    
    public boolean isHabil() {
        return habil;
    }

    public void setHabil(boolean habil) {
        this.habil = habil;
    }

    public int getCicloAtual() {
        return cicloAtual;
    }

    public void setCicloAtual(int cicloAtual) {
        this.cicloAtual = cicloAtual;
    }
}
