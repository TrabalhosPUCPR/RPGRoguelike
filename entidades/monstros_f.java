package entidades;

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
}


