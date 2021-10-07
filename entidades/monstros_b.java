package entidades;

import adicionais.extras;
import adicionais.handler;

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
    public void turno_mons(){
        double dano;
        extras.print("");
        extras.println_bonito("Cuidado! o " + this.nome + " vai atacar!", 700, 300);
        extras.print("");
        dano = atacar();
        dano = handler.jogador.levar_dano(dano, this.destreza); 
        if(dano < 0){
            extras.println_bonito("AIAIAI!", 100, 300);
        }
        extras.print("");
        extras.println_bonito("Voce levou " + dano + " de dano do " + this.nome + "!", 700, 500);
    }

    @Override
    public void morrer(){
        extras.print("");
        extras.println_bonito("Viva! O " + this.nome + " foi derrotado!", 500, 1000);
        extras.print("");
        extras.println_bonito("Voce ganhou experiencia extra por esta luta!", 400, 400);
    }
}
