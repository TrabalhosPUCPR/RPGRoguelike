package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class itensOfen extends itens{

    int forca;
    int destreza;

    static int[] itensOfen_drop;
    static int[] qual_itensOfen_drop;

    public itensOfen(String nome, String desc, int forca, int destreza, double dinheiro, int raridade){
        this.nome = nome;
        this.desc = desc;
        this.forca = forca; 
        this.destreza = destreza;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static int dropItenOfen(){ // isso daqui vai retorna o id de uma arma aleatorio baseada na fase atual e raridade de arma, da pra usar isso no ngc do mercador tb, msm coisa com outros itens e equips
        return itensOfen_drop[extras.rng_int(0, itensOfen_drop.length)];
    }

    public static int dropItenOfenRaro(){
        return qual_itensOfen_drop[extras.rng_int(0, qual_itensOfen_drop.length)];
    }

    public static void setDropRateItensOfen(){
        int[] itensOfen_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemOfen.size(); i++){
            if(handler.itemOfen.get(i).getRaridade()<=fases.fase_atual+1 && handler.itemOfen.get(i).getRaridade()>fases.fase_atual-1){
                itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                if(handler.itemOfen.get(i).getRaridade()==fases.fase_atual){
                    itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                }
            }
        }
        itensOfen.itensOfen_drop = itensOfen_drop;
        setDropRateItensOfenRaro();
    }

    static void setDropRateItensOfenRaro(){
        int[] itensOfen_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemOfen.size(); i++){
            if(handler.itemOfen.get(i).getRaridade()<=fases.fase_atual+2 && handler.itemOfen.get(i).getRaridade()>fases.fase_atual){
                itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                if(handler.itemOfen.get(i).getRaridade()==fases.fase_atual+1){
                    itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                }
            }
        }
        itensOfen.qual_itensOfen_drop = itensOfen_drop;
    }
}
