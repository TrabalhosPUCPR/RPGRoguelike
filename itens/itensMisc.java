package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class itensMisc extends itens{

    double buff_evasion;

    static int[] itensMisc_drop;
    static int[] qual_itensMisc_drop;

    public itensMisc(String nome, String desc, int forca, int defesa, int destreza, double buff_evasion, double dinheiro, int raridade){
        this.nome = nome;
        this.desc = desc;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.buff_evasion = buff_evasion;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static int dropItenMisc(){ // isso daqui vai retorna o id de uma arma aleatorio baseada na fase atual e raridade de arma, da pra usar isso no ngc do mercador tb, msm coisa com outros itens e equips
        return itensMisc_drop[extras.rng_int(0, itensMisc_drop.length)];
    }

    public static int dropItenMiscRaro(){
        return qual_itensMisc_drop[extras.rng_int(0, qual_itensMisc_drop.length)];
    }

    public static void setDropRateItensMisc(){
        int[] itensMisc_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemMisc.size(); i++){
            if(handler.itemMisc.get(i).getRaridade()<=fases.fase_atual+1 && handler.itemMisc.get(i).getRaridade()>fases.fase_atual-1){
                itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                if(handler.itemMisc.get(i).getRaridade()==fases.fase_atual){
                    itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                }
            }
        }
        itensMisc.itensMisc_drop = itensMisc_drop;
        setDropRateItensMiscRaro();
    }

    static void setDropRateItensMiscRaro(){
        int[] itensMisc_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemMisc.size(); i++){
            if(handler.itemMisc.get(i).getRaridade()<=fases.fase_atual+2 && handler.itemMisc.get(i).getRaridade()>fases.fase_atual){
                itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                if(handler.itemMisc.get(i).getRaridade()==fases.fase_atual+1){
                    itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                }
            }
        }
        itensMisc.qual_itensMisc_drop = itensMisc_drop;
    }

    public double getBuffEva(){return this.buff_evasion;}

}
