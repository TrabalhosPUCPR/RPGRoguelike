package entidades;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class inimigos extends entidade{

    void falar() {}
    void dropar() {}

    public static entidade getInimigo(int indexm, int tipo){
        switch(tipo){
            case 0:
                return handler.monstros.get(indexm);
            case 1:
                return handler.bossesrand.get(indexm);
            case 2:
                return handler.bosses.get(indexm);
            case 3:
                return handler.npcs.get(indexm);
        }
        return null;
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
                list_monst = new int[] {0,1,1,1}; 
                break;

            case 2: // caso for monstros boss
                switch(fases.fase_atual){
                    case 1: //aqui e o numero da fase que a pessoa esta
                        list_monst = new int[] {1,0,0,0,0,2,2,2,2}; 
                        break;
                    case 2:
                        list_monst = new int[] {1,1,1};
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
        }

        int index = extras.rng_int(0, list_monst.length);
        return list_monst[index];
    }

}
