package itens;

import adicionais.extras;
import adicionais.handler;
import entidades.inimigos;

public class consumiveis extends itens{
    int acaoId;
    String acao;
    double valor;


    public consumiveis(String nome, String acao, double valor, int acaoId){
        this.nome = nome;
        this.acao = acao;
        this.valor = valor;
        this.acaoId = acaoId;
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
    
    public String getAcao() {return acao;}
    public void setAcao(String acao) {this.acao = acao;}
    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}
}
