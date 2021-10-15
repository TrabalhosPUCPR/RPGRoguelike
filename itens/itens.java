package itens;

import adicionais.handler;

public class itens {

    String nome;
    String desc;
    String peso;
    String tipo;
    double dinheiro;
    int raridade;

    int forca;
    int defesa;
    int destreza;

    public int getPesoDes(){
        int des = 0;
        switch(this.peso){
            case "omega leve":
                des = 5;
                break;
            case "super leve":
                des = 2;
                break;
            case "leve":
                des = 0;
                break;
            case "pesado":
                des = -5;
                break;
            case "super pesado":
                des = -9;
                break;
        }
        return des;
    }

    public static itens getItem(int indexi, int tipo){
        switch(tipo){
            case 0:
                return (consumiveis)handler.consu.get(indexi);
            case 1:
                return (itensOfen)handler.itemOfen.get(indexi);
            case 2:
                return (itensDef)handler.itemDef.get(indexi);
            case 3:
                return (itensMisc)handler.itemMisc.get(indexi);
        }
        return null;
    }
    
    public String getNome(){return this.nome;}
    public String getDesc(){return this.desc;}
    public String getTipo(){return this.tipo;}
    public String getPeso(){return this.peso;}
    public double getDinheiro(){return this.dinheiro;}
    public int getForca(){return this.forca;}
    public double getDefesa(){return this.defesa;}
    public int getDestreza(){return this.destreza;}
    public int getRaridade(){return this.raridade;}

    public void setNome(String n){this.nome = n;}
    public void setPeso(String n){this.peso = n;}
    public void setValor(double n){this.dinheiro = n;}

}

