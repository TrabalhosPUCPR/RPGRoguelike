package fases;

import java.util.List;

import adicionais.extras;
import adicionais.handler;
import ascii.ascii;
import entidades.NPC;
import entidades.inimigos;
import adicionais.combate;

public class andares extends fases{

    public static andares andar;

    public andares(List<Runnable> metodos2, String nome, String desc) {
        super(nome, desc, andar_atual);
    }

    public static void proxAndar(){
        handler.andar.get(selec_index()).run();
    }

    static int selec_index(){
        int valores[] = {1,1,1,0}; // isso e para deixar um andar mais provavel de aparecer, toda vez que criar uma sala diferente tenq colocar o numero dele aqui
        return valores[extras.rng_int(0, valores.length)];
    }

    public static void NPC(){
        NPC.selec_npc();
    }

    public static void monstro(){
        extras.print("");
        extras.println_bonito("Voce chega na porta da proxima sala, e sente perigo dentro dela,\nmas sem opcoes, voce entra nela para enfrentar o que tiver", 1500, 500);
        combate.lutaini(0);
    }

    public static void boss(){
        extras.print("");
        extras.println_bonito("Voce finalmente chegou no fim da " + handler.fase.get(fase_atual-1).getNome() + "!", 700, 1000);
        extras.print("");
        for(int i = 0; i < 3; i++){
            extras.println_bonito("tum", 200, 100);
        }
        int indexm = inimigos.selec_monstro(2);
        extras.delay(500);
        extras.print("");
        extras.println_bonito("Este barulho nao e um bom sinal...", 700, 900);
        extras.print("");
        ascii.printMonstroAsciipeloNome(handler.bosses.get(indexm).getAscii(), true);
        extras.println_bonito("Essa nao! O " + handler.bosses.get(indexm).getNome() + " apareceu!", 1000, 700);
        extras.print("");
        extras.println_bonito("Este é o chefe da "+handler.fase.get(fase_atual-1).getNome()+"! Se prepare para uma luta dificil!", 1000, 700);
        combate.lutaini(2, indexm);
    }
}
