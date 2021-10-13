package fases;

import java.util.List;

import adicionais.extras;
import adicionais.handler;
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
        int valores[] = {1,1,1,1,0}; // isso e para deixar um andar mais provavel de aparecer, toda vez que criar uma sala diferente tenq colocar o numero dele aqui
        int rand = extras.rng_int(0, valores.length);
        return valores[rand];
    }

    public static void mercador(){}

    public static void cachorro(){
        extras.print("");
        extras.println_bonito("Voce encontra um cachorro, o que deseja fazer?", 1300, 500);

        String res = extras.inputS();

        switch(res.toLowerCase()){
            case "carinho":
                extras.print("");
                extras.println_bonito("Voce deu carinho no cachorro", 500, 500);
                extras.print("");
                extras.println_bonito("Ele esta feliz", 500, 500);
                extras.print("");
                extras.println_bonito("Ele te entrega um item", 500, 500);
                inimigos.getInimigo(3, 3).dropar();
                break;
            case "chutar":
                extras.print("");
                extras.println_bonito("Seu desgracado!", 500, 500);
                extras.print("");
                extras.println_bonito("O cachorro ficou bravo, ele pegou uma espada no canto da sala!", 500, 500);
                combate.lutaini(3, 3);
                
                break;
            case "sair":
                extras.print("");
                extras.println_bonito("Voce ignora o cachorro e sai", 500, 500);
                extras.print("");
                extras.println_bonito("Ele esta triste", 500, 500);
                handler.jogador.receberXp(2);
                break;
            default:
                extras.print("");
                extras.println_bonito("O cachorro nao entendeu o que quis dizer, ele vai embora", 500, 500);
                handler.jogador.receberXp(2);
        }
    }

    public static void monstro(){
        extras.print("");
        extras.println_bonito("Voce chega na porta da proxima sala, e sente perigo dentro dela, mas sem opcoes, voce entra nela para enfrentar o que tiver", 1500, 500);
        combate.lutaini(0);
    }

    public static void boss(){
        extras.print("");
        extras.println_bonito("Voce finalmente chegou no fim da " + handler.fase.get(fase_atual-1).getNome() + "!", 700, 500);
        extras.delay(500);
        extras.print("");
        for(int i = 0; i < 3; i++){
            extras.println_bonito("tum", 200, 100);
        }
        int indexm = inimigos.selec_monstro(2);
        extras.delay(500);
        extras.print("");
        extras.println_bonito("Este barulho nao e um bom sinal...", 700, 900);
        extras.print("");
        extras.println_bonito("Essa nao! O " + handler.bosses.get(indexm).getNome() + " apareceu!", 1000, 700);
        extras.print("");
        extras.println_bonito("Este é o chefe da "+handler.fase.get(fase_atual-1).getNome()+"! Se prepare para uma luta dificil!", 1000, 700);
        combate.lutaini(2, indexm);
    }
}
