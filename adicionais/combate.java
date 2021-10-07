package adicionais;

import entidades.entidade;
import entidades.inimigos;

public class combate extends entidade{

    public static int n_turno = 0;

    public combate(){}

    public static void lutaini(int tipo){
        n_turno = 0;
        handler.jogador.luta_prep();
        switch(tipo){
            case 0:
                if(extras.rng_double(0, 1) > 0.02){ // todo andar que for do tipo monstro tem 2% chance de ser um monstro incomum
                    luta(inimigos.selec_monstro(0), 0);
                }else{
                    luta(inimigos.selec_monstro(1), 1);
                }
                break;
            case 1:
            case 2:
            case 3:
                luta(inimigos.selec_monstro(tipo), tipo);
                break;
        }
    }

    public static void lutaini(int tipo, int indexm){
        n_turno = 0;
        handler.jogador.luta_prep();
        luta(indexm, tipo);
    }

    public static void luta(int indexm, int tipo){
        extras.print("");
        extras.println_bonito("Cuidado!", 300, 500);
        extras.print("");
        extras.println_bonito("Um " + inimigos.getInimigo(indexm, tipo).getNome() + " se aproxima!", 700, 500);

        while(handler.jogador.getVida() > 0 && inimigos.getInimigo(indexm, tipo).getVida() > 0){

            if(handler.jogador.getDestreza() >= inimigos.getInimigo(indexm, tipo).getDestreza()){ // caso a destreza do jogador for maior que a do monstro, ele ataca primeiro
                handler.jogador.P_turno(indexm, tipo);
                if (inimigos.getInimigo(indexm, tipo).getVida() > 0){
                    if(handler.jogador.getDestreza()*0.33 > inimigos.getInimigo(indexm, tipo).getDestreza()){ //caso 33% da destreza do jogador for maior que da destreza total do monstro, o jogador ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Sua destreza e muito alta!", 500, 800);
                        handler.jogador.P_turno(indexm, tipo);
                    }
                    if (inimigos.getInimigo(indexm, tipo).getVida() > 0){
                        inimigos.getInimigo(indexm, tipo).Iturno();
                    }
                }

            }else{ // caso a destreza do jogador for apenas menor que a do monstro, o monstro ataca primeiro
                inimigos.getInimigo(indexm, tipo).Iturno();
                if (handler.jogador.getVida() > 0){
                    if(handler.jogador.getDestreza() < inimigos.getInimigo(indexm, tipo).getDestreza()*0.33){ // caso a destreza do jogador for menor que 33% da destreza total do monstro, o monstro ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Essa nao! O " + inimigos.getInimigo(indexm, tipo).getNome() + " tem muita destreza!", 1500, 800);
                        inimigos.getInimigo(indexm, tipo).Iturno();
                    }
                    if (handler.jogador.getVida() > 0){
                        handler.jogador.P_turno(indexm, tipo);
                    }
                }
            }
            n_turno++; 
        }
        if(handler.jogador.getVida() <= 0){
            handler.jogador.morrer(indexm, tipo);
        }
        inimigos.getInimigo(indexm, tipo).morrer();
        handler.jogador.fimLuta(inimigos.getInimigo(indexm, tipo).getExp(), tipo);
    }

}
