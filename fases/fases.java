package fases;

import adicionais.extras;
import adicionais.handler;
import entidades.monstros_f;

public class fases {

    public String nome;
    public String desc;

    public static int fase_atual = 1;

    public static int andar_atual = 0;
    public int qntd_andares;

    public fases(String nome, String desc, int qntd){
        this.nome = nome;
        this.desc = desc;
        this.qntd_andares = qntd;
    }

    public void loopAndares(){
        while(andar_atual < this.qntd_andares - 1){
            handler.resetMonstros();
            andar_atual++;
            andares.proxAndar();
            fimAndar();
        }
    }

    public void fimAndar(){
        extras.print("");
        extras.println_bonito("Voce terminou o andar " + andar_atual + " da " + this.nome, 1000, 1000);
        if(handler.jogador.getVida() != handler.jogador.getVidamax()){
            extras.print("");
            extras.println_bonito("Voce recupera " + String.format("%.0f", handler.jogador.getVidamax()*0.20) + " pontos de vida por ter completado o andar...", 500, 500); 
            handler.jogador.curar(handler.jogador.getVidamax()*0.20);// recupera 20% de vida ao chegar no final da fase
        }
        extras.print("");
        extras.println_bonito("Voce segue para o proximo andar da " + this.nome, 500, 500);
        handler.resetMonstros();
        loopAndares();
    }

    public static void comecarFases(){
        andar_atual = 0;
        for(int i = 0; i < handler.fase.size(); i++){ // vai comecar o loop das fases
            handler.fase.get(i).comecarFaseAtual();
        }
    }

    private void comecarFaseAtual(){
        //extras.print("");
        monstros_f.setFaseDrop(fase_atual);
        loopAndares();
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
        extras.delay(2000);

    }    
}
