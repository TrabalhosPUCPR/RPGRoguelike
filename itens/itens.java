package itens;

import adicionais.extras;
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

    public static void setDropRateAce(){
        itensDef.setDropRateItensDef();
        itensOfen.setDropRateItensOfen();
        itensMisc.setDropRateItensMisc();
        consumiveis.setDropRateconsu();
    }

    public static int dropItem(int tipo){
        int id = 0;
        try{
            switch(tipo){
                case 0:
                    id = consumiveis.dropConsu();
                    break;
                case 1:
                    if(extras.rng_int(0, 100) > 10){
                        id = itensOfen.dropItenOfen();
                    }else{
                        id = itensOfen.dropItenOfenRaro();
                    }
                    break;
                case 2:
                    if(extras.rng_int(0, 100) > 10){
                        id = itensDef.dropItenDef();
                    }else{
                        id = itensDef.dropItenDefRaro();
                    }
                    break;
                default:
                    if(extras.rng_int(0, 100) > 10){
                        id = itensMisc.dropItenMisc();
                    }else{
                        id = itensMisc.dropItenMiscRaro();
                    }
                    break;
            }
        }catch(Exception e){
            extras.println("Falha ao dropar um item");
        }
        return id;
    }

    public int getPesoDes(){
        int des = 0;
        switch(this.peso){
            case "omega leve":
                des = 1;
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
            default:
                des = 0;
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

    public static String getItemTipoString(int tipo){
        switch(tipo){
            case 0:
                return "consumivel";
            case 1:
                return "ofensivo";
            case 2:
                return "defensivo";
            case 3:
                return "misc";
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
    public void setDesc(String n){this.desc = n;}
    public void setForca(int n){this.forca = n;}
    public void setDefesa(int n){this.defesa = n;}
    public void setDestreza(int n){this.destreza = n;}
    public void setPeso(String n){this.peso = n;}
    public void setValor(double n){this.dinheiro = n;}
    public void setRaridade(int n){this.raridade = n;}

}

