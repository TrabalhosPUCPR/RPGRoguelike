package itens;

import java.util.ArrayList;

import adicionais.extras;
import itens.inventario;

public class consumiveis extends itens{
    String acao;
    double valor;

    public consumiveis(String nome, String acao, double valor){
        this.nome = nome;
        this.acao = acao;
        this.valor = valor;
    }


    public void usarItem(int id){
        switch(id){
            case 0:
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
    
    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
