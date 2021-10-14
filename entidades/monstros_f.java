package entidades;

import adicionais.extras;
import itens.inventario;

public class monstros_f extends inimigos{

    public monstros_f(String nome, String desc, int arma_equip, int vidamax, int forca, int defesa, int destreza, int exp){
        this.nome = nome;
        this.desc = desc;
        this.arma_equip = arma_equip;
        this.vidamax = vidamax;
        this.vida = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.exp = exp;
    }

    void falar(){}

    @Override
    public void dropar() {
        switch(this.nome.toLowerCase()){
            case "mimic":
                dropItemQualidade();
                inventario.ganharDinheiro(extras.rng_double(100, 150));
                break;
            case "mendigo":
                dropItemGenerico();
                inventario.ganharDinheiro(extras.rng_double(0, 5));
                break;
            default:
                dropItemGenerico();
                inventario.ganharDinheiro(extras.rng_double(0, 30));
                break;
        }
    }
}


