package itens;

import adicionais.extras;
import adicionais.handler;
import entidades.inimigos;
import fases.fases;

public class consumiveis extends itens{
    int acaoId;
    String acao;
    double valor;

    static int[] consu_drop;


    public consumiveis(String nome, String acao, double valor, int acaoId, double dinheiro, int raridade){
        this.nome = nome;
        this.acao = acao;
        this.valor = valor;
        this.acaoId = acaoId;
        this.valor = valor;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public void usar(){
        switch(this.acaoId){
            case 0:
                extras.println("");
                extras.println_bonito("Voce se curou " + String.format("%.0f", handler.jogador.getVidamax()*this.valor) + " de vida!", 600, 500);
                handler.jogador.curar(handler.jogador.getVidamax()*this.valor);
                break;
            case 1:
                extras.println("");
                extras.println_bonito("Voce aumentou sua forca em " + String.format("%.02f", handler.jogador.getForca()*this.valor) + "!", 600, 500);
                handler.jogador.curar(handler.jogador.getForca()*this.valor);
                break;
            case 2:
                extras.println("");
                extras.println_bonito("Voce aumentou sua destreza em " + String.format("%.02f", handler.jogador.getDestreza()*this.valor) + "!", 600, 500);
                handler.jogador.curar(handler.jogador.getForca()*this.valor);
                break;
            case 3:
                extras.println("");
                extras.println_bonito("Voce Jogou " + this.nome + " no " + inimigos.getInimigo(inimigos.indexmonstro, inimigos.tipomonstro).getNome() + "!", 600, 500);
                inimigos.getInimigo(inimigos.indexmonstro, inimigos.tipomonstro).levar_dano(this.valor, handler.jogador.getDestreza(), "leve", true);
                break;
        }
    }

    public static int dropConsu(){ // isso daqui vai retorna o id de uma arma aleatorio baseada na fase atual e raridade de arma, da pra usar isso no ngc do mercador tb, msm coisa com outros itens e equips
        return consu_drop[extras.rng_int(0, consu_drop.length)];
    }

    public static void setDropRateconsu(){
        int[] consu_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.consu.size(); i++){
            if(handler.consu.get(i).getRaridade()<=fases.fase_atual+1 && handler.consu.get(i).getRaridade()>fases.fase_atual-3){
                consu_drop = extras.arrayintAdd(consu_drop, i);
                if(handler.consu.get(i).getRaridade()==fases.fase_atual){
                    consu_drop = extras.arrayintAdd(consu_drop, i);
                }
            }
        }
        consumiveis.consu_drop = consu_drop;
    }
    
    public String getAcao() {return acao;}
    public void setAcao(String acao) {this.acao = acao;}
    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}
}
