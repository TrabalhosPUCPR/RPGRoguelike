package entidades;

import adicionais.extras;

public class monstros_b extends inimigos{

    public monstros_b(String nome, String desc, int arma_equip, int vidamax, int forca, int defesa, int destreza, int exp){
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

    @Override
    public void morrer(){
        extras.print("");
        extras.println_bonito("Viva! O " + this.nome + " foi derrotado!", 500, 1000);
        extras.print("");
        extras.println_bonito("Voce ganhou experiencia extra por esta luta!", 400, 400);
        dropar();
    }
}
