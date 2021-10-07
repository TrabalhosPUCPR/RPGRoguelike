package entidades;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class inimigos extends entidade{



    void falar() {}
    void dropar() {}

    public void morrer(){
        extras.print("");
        extras.println_bonito("O " + this.nome + " foi derrotado!", 300, 400);
        extras.delay(300);
    }

    public void turno_npc(){
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

    public static int selec_npc(int npctipo){ // seleciona o npc baseado na fase que deve aparecer
        int list_npc[] = {};
        switch(npctipo){
            case 0: // caso for npc normal
                switch(fases.fase_atual){
                    case 1: //aqui e o numero da fase que a pessoa esta
                        list_NPC = new int[] {0,0,0,1,2,3,3,4,4}; // lista com os ids de cada npc que e para aparecer na fase, pode colocar o mesmo id mais de uma vez pra aumenta a chance dele aparecer
                        break;
                    case 2:
                        list_NPC = new int[] {0,0,0,1,2,3,4,4};
                        break;
                    case 3:
                        list_NPC = new int[] {1,4};
                        break;
                    case 4:
                        list_NPC = new int[] {1,1,1};
                        break;
                    case 5:
                        list_NPC = new int[] {1,1,1};
                        break;
                }
            
                }
                break;
        }

        int index = extras.rng_int(0, list_NPC.length);
        return list_NPC[index];
    }

    public int getExp(){return this.exp;}
}
