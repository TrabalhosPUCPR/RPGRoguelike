package itens;

import adicionais.handler;

public class itens {

    String nome;
    String desc;
    String peso;
    String tipo;

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
                des = -2;
                break;
            case "super pesado":
                des = -5;
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

    public int getForca(){return forca;}
    public double getDefesa(){return defesa;}
    public int getDestreza(){return destreza;}

}

