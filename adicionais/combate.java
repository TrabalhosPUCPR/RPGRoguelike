package adicionais;

import entidades.entidade;
import entidades.inimigos;

public class combate extends entidade{

    public combate(){}

    public static void lutaini(){
        double rand = extras.rng_double(1);
        handler.jogador.luta_prep();

        if(rand > 0.05){ // todo andar que for do tipo monstro tem 5% chance de ser um monstro incomun
            luta_monstrinho();
        }else{
            luta_boss_rand();
        }
    }

    public static void luta_monstrinho(){
        int indexm = inimigos.selec_monstro(0); // tipo do monstro, pra monstro comum e 0
        extras.print("");
        extras.println_bonito("Cuidado!", 600, 500);
        extras.print("");
        extras.println_bonito("Um " + handler.monstros.get(indexm).getNome() + " se aproxima!", 700, 500);

        while(handler.jogador.getVida() > 0 && handler.monstros.get(indexm).getVida() > 0){

            if(handler.jogador.getDestreza() >= handler.monstros.get(indexm).getDestreza()){ // caso a destreza do jogador for maior que a do monstro, ele ataca primeiro
                handler.jogador.turno(indexm, 0);
                if (handler.monstros.get(indexm).getVida() > 0){
                    if(handler.jogador.getDestreza()*0.33 > handler.monstros.get(indexm).getDestreza()){ //caso 33% da destreza do jogador for maior que da destreza total do monstro, o jogador ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Sua destreza e muito alta!", 500, 800);
                        handler.jogador.turno(indexm, 0);
                    }
                    if (handler.monstros.get(indexm).getVida() > 0){
                        handler.monstros.get(indexm).turno_mons();
                    }
                }

            }else{ // caso a destreza do jogador for apenas menor que a do monstro, o monstro ataca primeiro
                handler.monstros.get(indexm).turno_mons();
                if (handler.jogador.getVida() > 0){
                    if(handler.jogador.getDestreza() < handler.monstros.get(indexm).getDestreza()*0.33){ // caso a destreza do jogador for menor que 33% da destreza total do monstro, o monstro ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Essa nao! O " + handler.monstros.get(indexm).getNome() + " tem muita destreza!", 1500, 800);
                        handler.monstros.get(indexm).turno_mons();
                    }
                    if (handler.jogador.getVida() > 0){
                        handler.jogador.turno(indexm, 0);
                    }
                }
            } 
        }
        if(handler.jogador.getVida() <= 0){
            handler.jogador.morrer(indexm, 0);
        }
        handler.monstros.get(indexm).morrer();
        handler.jogador.fimLuta(handler.monstros.get(indexm).getExp());
    }

    public static void luta_boss_rand(){
        int indexm = inimigos.selec_monstro(1);
        extras.print("");
        extras.println_bonito("Cuidado!", 600, 500);
        extras.print("");
        extras.println_bonito("O " + handler.bossesrand.get(indexm).getNome() + " se aproxima!", 700, 500);

        while(handler.jogador.getVida() > 0 && handler.bossesrand.get(indexm).getVida() > 0){

            if(handler.jogador.getDestreza() >= handler.bossesrand.get(indexm).getDestreza()){ // caso a destreza do jogador for maior que a do monstro, ele ataca primeiro~
                handler.jogador.turno(indexm, 1);
                if (handler.bossesrand.get(indexm).getVida() > 0){
                    if(handler.jogador.getDestreza()*0.33 > handler.bossesrand.get(indexm).getDestreza()){ //caso 33% da destreza do jogador for maior que da destreza total do monstro, o jogador ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Sua destreza e muito alta!", 600, 800);
                        handler.jogador.turno(indexm, 1);
                    }
                    if (handler.monstros.get(indexm).getVida() > 0){
                        handler.bossesrand.get(indexm).turno_mons();
                    }
                }

            }else{ // caso a destreza do jogador for apenas menor que a do monstro, o monstro ataca primeiro
                handler.bossesrand.get(indexm).turno_mons();
                if (handler.jogador.getVida() > 0){
                    if(handler.jogador.getDestreza() < handler.bossesrand.get(indexm).getDestreza()*0.33){ // caso a destreza do jogador for menor que 33% da destreza total do monstro, o monstro ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Essa nao! O " + handler.bossesrand.get(indexm).getNome() + " tem muita destreza!", 1500, 800);
                        handler.bossesrand.get(indexm).turno_mons();
                    }
                    if (handler.jogador.getVida() > 0){
                        handler.jogador.turno(indexm, 1);
                    }
                }
            } 
        }
        if(handler.jogador.getVida() <= 0){
            handler.jogador.morrer(indexm, 1);
        }
        handler.bossesrand.get(indexm).morrer();
        handler.jogador.fimLuta(handler.bossesrand.get(indexm).getExp());
    }

    public static void luta_boss(int indexm){
        handler.jogador.luta_prep();
        extras.print("");
        extras.println_bonito("Cuidado!", 600, 500);
        extras.print("");
        extras.println_bonito("O " + handler.bosses.get(indexm).getNome() + " se aproxima!", 700, 500);

        while(handler.jogador.getVida() > 0 && handler.bosses.get(indexm).getVida() > 0){

            if(handler.jogador.getDestreza() >= handler.bosses.get(indexm).getDestreza()){ // caso a destreza do jogador for maior que a do monstro, ele ataca primeiro~
                handler.jogador.turno(indexm, 2);
                if (handler.bosses.get(indexm).getVida() > 0){
                    if(handler.jogador.getDestreza()*0.33 > handler.bosses.get(indexm).getDestreza()){ //caso 33% da destreza do jogador for maior que da destreza total do monstro, o jogador ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Sua destreza e muito alta!", 600, 800);
                        handler.jogador.turno(indexm, 2);
                    }
                    if (handler.bosses.get(indexm).getVida() > 0){
                        handler.bosses.get(indexm).turno_mons();
                    }
                }

            }else{ // caso a destreza do jogador for apenas menor que a do monstro, o monstro ataca primeiro
                handler.bosses.get(indexm).turno_mons();
                if (handler.jogador.getVida() > 0){
                    if(handler.jogador.getDestreza() < handler.bosses.get(indexm).getDestreza()*0.33){ // caso a destreza do jogador for menor que 33% da destreza total do monstro, o monstro ataca primeiro duas vezes
                        extras.print("");
                        extras.println_bonito("Essa nao! O " + handler.bosses.get(indexm).getNome() + " tem muita destreza!", 1500, 800);
                        extras.print("");
                        handler.bosses.get(indexm).turno_mons();
                    }
                    if (handler.jogador.getVida() > 0){
                        handler.jogador.turno(indexm, 2);
                    }
                }
            } 
        }
        if(handler.jogador.getVida() <= 0){
            handler.jogador.morrer(indexm, 2);
        }
        handler.bosses.get(indexm).morrer();
        handler.jogador.fimLuta(handler.bosses.get(indexm).getExp());
    }
}
