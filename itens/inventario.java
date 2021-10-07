package itens;

import java.util.*;
import adicionais.extras;
import adicionais.handler;

public class inventario {
 private int itenOfensivo;
 private int itenDefensivo;
 private int itenMisc;
 private ArrayList<consumiveis> itenConsumivel;
 private Double dinheiro;

 
 
 public int getItenOfensivo() {
        return itenOfensivo;
    }

    public void setItenOfensivo(int itenOfensivo) {
        this.itenOfensivo = itenOfensivo;
    }

    public int getItenDefensivo() {
        return itenDefensivo;
    }

    public void setItenDefensivo(int itenDefensivo) {
        this.itenDefensivo = itenDefensivo;
    }

    public int getItenMisc() {
        return itenMisc;
    }

    public void setItenMisc(int itenMisc) {
        this.itenMisc = itenMisc;
    }

    public ArrayList<consumiveis> getItenConsumivel() {
        return itenConsumivel;
    }

    public void setItenConsumivel(ArrayList<consumiveis> itenConsumivel) {
        this.itenConsumivel = itenConsumivel;
    }

    public Double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Double dinheiro) {
        this.dinheiro = dinheiro;
    }
}