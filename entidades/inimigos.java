package entidades;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class inimigos extends entidade{

    public static int indexmonstro;
    public static int tipomonstro;

    public static inimigos getInimigo(int indexm, int tipo){
        indexmonstro = indexm;
        tipomonstro = tipo;
        switch(tipo){
            case 0:
                return (monstros_f)handler.monstros.get(indexm);
            case 1:
                return (monstros_b)handler.bossesrand.get(indexm);
            case 2:
                return (monstros_b)handler.bosses.get(indexm);
            case 3:
                return (NPC)handler.npcs.get(indexm);
        }
        return null;
    }

    public void Iturno(){
        double dano;
        extras.print("");
        extras.println_bonito("Cuidado! o " + this.nome + " vai atacar!", 700, 300);
        extras.print("");
        dano = atacar();
        dano = handler.jogador.levar_dano(dano, this.destreza, false); 
        if(dano > 0){
            player.dor();
            extras.print("");
        }
        extras.println_bonito("Voce levou " + String.format("%.00f", dano) + " de dano do " + this.nome + "!", 700, 500);
    }

    public static int selec_monstro(int monstipo){ // seleciona o monstro baseado na fase que deve aparecer
        int list_monst[] = {};
        switch(monstipo){
            case 0: // caso for monstros normais
                switch(fases.fase_atual){
                    case 1: //aqui e o numero da fase que a pessoa esta
                        list_monst = new int[] {0,0,0,0,0,1,1,1,1,1,2,2,2,3,4,4,4,6,6,5,6,6,6,6,6}; // lista com os ids de cada monstro que e para aparecer na fase, pode colocar o mesmo id mais de uma vez pra aumenta a chance dele aparecer
                        break;
                    case 2:
                        list_monst = new int[] {0,0,0,0,0,1,1,1,1,1,2,2,2,3,4,4,4,6,6,5,6,6,6,6,6};
                        break;
                    case 3:
                        list_monst = new int[] {1,1,1};
                        break;
                    case 4:
                        list_monst = new int[] {1,1,1};
                        break;
                    case 5:
                        list_monst = new int[] {1,1,1};
                        break;
                }
                break;
            case 1: // caso for monstros incomuns
                list_monst = new int[] {0,1,1,1,2,2,2}; 
                break;

            case 2: // caso for monstros boss
                switch(fases.fase_atual){
                    case 1: //aqui e o numero da fase que a pessoa esta
                        list_monst = new int[] {1,0}; 
                        break;
                    case 2:
                        list_monst = new int[] {2};
                        break;
                    case 3:
                        list_monst = new int[] {3,3,4};
                        break;
                    case 4:
                        list_monst = new int[] {5};
                        break;
                    case 5:
                        list_monst = new int[] {6};
                        break;
                    default:
                        if(handler.jogador.getNpcsMortos() > 10){
                            list_monst = new int[] {8};
                        }else{
                            list_monst = new int[] {7};
                        }
                        break;
                }
                break;
        }
        int index = extras.rng_int(0, list_monst.length);
        return list_monst[index];
    }

}
