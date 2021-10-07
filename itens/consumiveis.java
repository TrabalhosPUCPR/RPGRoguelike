package itens;

import adicionais.extras;

public class consumiveis extends itens{

    String acao;
    double valor;

    public consumiveis(String nome, String acao, double valor){
        this.nome = nome;
        this.acao = acao;
        this.valor = valor;
    }

    public static void usarItem(int id){
        switch(id){
            case 0:
                extras.println("fraca pocao de vida");
            case 1:
                extras.println("fraca pocao de forca");
        }
    }
    
}
