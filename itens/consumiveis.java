package itens;

import adicionais.extras;

public class consumiveis extends itens{

    int acao;
    double valor;

    public consumiveis(String nome, String acao, double valor){
        this.nome = nome;
        this.acao = acao;
        this.valor = valor;
    }

    public void usarItem(int id){
        switch(id){
            case 0:
                handler.jogador.curar(this.valor*handler.jogador.getVidaMax());
                extras.println("fraca pocao de vida");
                break;
            case 1:
                extras.println("fraca pocao de forca");
                break;
            case 2:
                extras.println("fraca pocao de destreza");
                break;
        }
    }
    
}
