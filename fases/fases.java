package fases;

import java.util.ArrayList;
import java.util.List;

import adicionais.extras;
import adicionais.handler;

public class fases {

    public String nome;
    public String desc;

    public static int fase_atual = 1;

    public static int andar_atual = 0;
    public int qntd_andares;

    List<Runnable> metodos = new ArrayList<>();

    public fases(String nome, String desc, int qntd){
        this.nome = nome;
        this.desc = desc;
        this.qntd_andares = qntd;
    }

    public static void comecarFases(){
        for(int i = 0; i < handler.fase.size(); i++){ // vai comecar o loop das fases
            handler.fase.get(i).comecarFaseAtual();
        }
    }

    public void proxAndar(){
        andares andar = new andares(metodos, nome, desc);
        andar.iniAndares();
    }

    private void comecarFaseAtual(){
        //extras.print("");

        handler.resetMonstros();
        while(andar_atual < this.qntd_andares - 1){
            andar_atual++;
            proxAndar();
            extras.delay(500);
            extras.print("");
            extras.println_bonito("Voce terminou o andar " + andar_atual + " da " + this.nome, 1000, 1000);
            if(handler.jogador.getVida() != handler.jogador.getVidamax()){
                extras.print("");
                extras.println_bonito("Voce recupera " + String.format("%.02f", handler.jogador.getVidamax()*0.20) + " pontos de vida por ter completado o andar...", 500, 500); 
                handler.jogador.curar(handler.jogador.getVidamax()*0.20);// recupera 20% de vida ao chegar no final da fase
            }
            extras.print("");
            extras.println_bonito("Voce segue para o proximo andar da " + this.nome, 500, 500);
            handler.resetMonstros();
            extras.delay(500);
        }
        andares.boss();
        fimFase();
    }

    public void fimFase(){
        extras.print("");
        extras.println_bonito("Voce chegou no final da fase " + fase_atual + ", " + this.nome + " está completa! Parabéns!", 1000, 1000);
        fase_atual++;
        extras.print("");
        extras.println_bonito("Voce recuperou sua vida toda!", 500, 500); 
        handler.jogador.curar(handler.jogador.getVidamax());
        andar_atual = 0;
        extras.delay(2000);

    }    
}
