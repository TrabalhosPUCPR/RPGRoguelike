package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class itensDef extends itens{

    int defesa;
    int destreza;

    static int[] itensDef_drop;
    static int[] qual_itensDef_drop;

    public itensDef(String nome, String desc, int defesa, int destreza, double dinheiro, int raridade){
        this.nome = nome;
        this.desc = desc;
        this.defesa = defesa; 
        this.destreza = destreza;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static int dropItenDef(){ // isso daqui vai retorna o id de uma arma aleatorio baseada na fase atual e raridade de arma, da pra usar isso no ngc do mercador tb, msm coisa com outros itens e equips
        return itensDef_drop[extras.rng_int(0, itensDef_drop.length)];
    }

    public static int dropItenDefRaro(){
        return qual_itensDef_drop[extras.rng_int(0, qual_itensDef_drop.length)];
    }

    public static void setDropRateItensDef(){
        int[] itensDef_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemDef.size(); i++){
            if(handler.itemDef.get(i).getRaridade()<=fases.fase_atual+1 && handler.itemDef.get(i).getRaridade()>fases.fase_atual-1){
                itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                if(handler.itemDef.get(i).getRaridade()==fases.fase_atual){
                    itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                }
            }
        }
        itensDef.itensDef_drop = itensDef_drop;
        setDropRateItensDefRaro();
    }

    static void setDropRateItensDefRaro(){
        int[] itensDef_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemDef.size(); i++){
            if(handler.itemDef.get(i).getRaridade()<=fases.fase_atual+2 && handler.itemDef.get(i).getRaridade()>fases.fase_atual){
                itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                if(handler.itemDef.get(i).getRaridade()==fases.fase_atual+1){
                    itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                }
            }
        }
        itensDef.qual_itensDef_drop = itensDef_drop;
    }
}
