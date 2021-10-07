package itens;

import adicionais.extras;
import adicionais.handler;

public class inventario {
 private int itenOfensivo;
 private int itenDefensivo;
 private int itenMisc;
 private int itenConsumivel[];
 private Double dinheiro;


 public void listarItens(){
    
    for(int i = 0; i < this.itenConsumivel.length; i++){
        extras.println("_________");
        extras.println("Nome: " + handler.consu.get(this.itenConsumivel[i]).getNome());
        extras.println("Acao: " + handler.consu.get(this.itenConsumivel[i]).getAcao() + " por " + handler.consu.get(this.itenConsumivel[i]).getValor());
        extras.println("_________");
    }

 }

 
 //setters
    public void setItenOfensivo(int itenOfensivo) {this.itenOfensivo = itenOfensivo;}
    public void setItenDefensivo(int itenDefensivo) {this.itenDefensivo = itenDefensivo;}
    public void setItenMisc(int itenMisc) {this.itenMisc = itenMisc;}
    public void addItenConsumivel(int itenConsumivel) {this.itenConsumivel[this.itenConsumivel.length] = itenConsumivel;}
    public void setDinheiro(Double dinheiro) {this.dinheiro = dinheiro;}

    //getters
    public int getItenOfensivo() {return itenOfensivo;}
    public int getItenDefensivo() {return itenDefensivo;}
    public int getItenMisc() {return itenMisc;}
    public int[] getItenConsumivel() {return itenConsumivel;}
    public Double getDinheiro() {return dinheiro;}
}