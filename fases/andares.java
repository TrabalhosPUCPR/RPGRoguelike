package fases;

import java.util.List;

import adicionais.extras;
import adicionais.handler;
import entidades.inimigos;
import adicionais.combate;

public class andares extends fases{

    public andares(List<Runnable> metodos2, String nome, String desc) {
        super(nome, desc, andar_atual);

        // essa e na minha opiniao a forma mais facil de escolher uma funcao de um andar aleatoriamente 

        this.metodos.add(andares::cachorro); // 0, numero de cada sala para fazer com que a chance de cada sala aparecer seja diferente
        this.metodos.add(andares::monstro); // 1
        this.metodos.add(andares::mercador); // 2
    }

    public void iniAndares(){
        this.metodos.get(selec_index()).run();
    }

    int selec_index(){
        int valores[] = {1,1,1,1,0,0,0,0}; // isso e para deixar um andar mais provavel de aparecer, toda vez que criar uma sala diferente tenq colocar o numero dele aqui
        int rand = extras.rng_int(0, valores.length);
        return valores[rand];
    }

    public static void mercador(){

    }

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
                handler.jogador.receberXp(15);
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
        extras.println_bonito("Voce finalmente chegou no fim da fase " + fase_atual + "!", 700, 500);
        extras.delay(500);
        for(int i = 0; i < 3; i++){
            extras.print("");
            extras.println_bonito("tum", 400, 100);
        }
        int indexm = inimigos.selec_monstro(2);
        extras.delay(500);
        extras.print("");
        extras.println_bonito("Este barulho nao e um bom sinal...", 1000, 500);
        extras.delay(500);
        extras.print("");
        extras.println_bonito("Essa nao! O " + handler.bosses.get(indexm).getNome() + " apareceu!", 2000, 500);
        extras.print("");
        extras.println_bonito("Este é o chefe desta fase! Se prepare para uma luta dificil!", 1500, 500);
        combate.lutaini(2, indexm);
    }
}
