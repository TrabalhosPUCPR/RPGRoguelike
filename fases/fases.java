package fases;

import adicionais.combate;
import adicionais.extras;
import adicionais.handler;
import adicionais.janela;
import ascii.ascii;
import entidades.player;
import itens.armaduras;
import itens.armas;
import itens.itens;

public class fases {

    public String nome;
    public String desc;

    public static int fase_atual = 1;

    public static int andar_atual = 1;
    public int qntd_andares;

    public fases(String nome, String desc, int qntd){
        this.nome = nome;
        this.desc = desc;
        this.qntd_andares = qntd;
    } 

    public void loopAndares(){
        while(andar_atual < this.qntd_andares){
            handler.resetMonstros();
            andares.proxAndar();
            fimAndar();
        }
    }

    public void fimAndar(){
        extras.print("");
        extras.println_bonito("Voce terminou o andar " + andar_atual + " da " + this.nome, 1000, 1000);
        if(handler.jogador.getVida() != handler.jogador.getVidamax()){
            extras.print("");
            extras.println_bonito("Voce recupera " + String.format("%.0f", handler.jogador.getVidamax()*0.40) + " pontos de vida por ter completado o andar...", 500, 500); 
            handler.jogador.curar(handler.jogador.getVidamax()*0.40);// recupera 20% de vida ao chegar no final da fase
        }
        extras.print("");
        extras.println_bonito("Voce segue para o proximo andar da " + this.nome, 500, 500);
        andar_atual++;
        janela.setUpPlayerGUI();
    }

    public static void comecarFases(){
        andar_atual = 1;
        janela.setUpPlayerGUI();
        for(int i = 0; i < handler.fase.size() - 1; i++){ // vai comecar o loop das fases
            handler.fase.get(i).comecarFaseAtual();
        }
        ultimateFase();
    }

    static void setDropsFase(){
        armas.setDropRateArmas();
        armaduras.setDropRateArmaduras();
        itens.setDropRateAce();
    }

    private void comecarFaseAtual(){
        extras.println("");
        extras.println_bonito("Voce chegou na "+ this.nome + "...", 800, 700);
        extras.println("");
        extras.println_bonito(this.desc, 1800, 1000);
        extras.println("");
        extras.println_bonito("Voce segue em frente...", 800, 1000);
        setDropsFase();
        loopAndares();
        handler.resetMonstros();
        andares.boss();
        fimFase();
    }

    public void fimFase(){
        extras.print("");
        extras.println_bonito("Voce chegou no final da fase " + fase_atual + ", " + this.nome + " está completa! Parabéns!", 1000, 1000);
        fase_atual++;
        andar_atual = 1;
        extras.print("");
        extras.println_bonito("Voce recuperou sua vida toda!", 500, 1500); 
        handler.jogador.curar(handler.jogador.getVidamax());
        janela.setUpPlayerGUI();
    }    

    private static void ultimateFase(){
        setDropsFase();
        extras.println("");
        extras.println_bonito("..........................................................", 2000, 1000);
        extras.println("");
        extras.println_bonito("Voce esta andando ja faz um tempo.", 1500, 800);
        extras.println("");
        extras.println_bonito("Voce ja chegou bem longe, o fim deve estar proximo...", 1500, 800);
        extras.println("");
        extras.println_bonito("Voce vê uma luz no fim do tunel e sente o vento batendo no seu rosto", 2000, 800);
        extras.println("");
        extras.println_bonito("Voce finalmente esta fora deste loop infinito!", 1500, 800);
        extras.println("");
        extras.println_bonito("Voce esta no " + handler.fase.get(handler.fase.size()-1).getNome(), 1300, 700);
        extras.println("");
        extras.println_bonito(handler.fase.get(handler.fase.size()-1).getDesc(), 1800, 1000);
        extras.println("");
        extras.println_bonito("Voce caminha nas nuvens, onde voce eventualmente \nencontra um castelo, e entra...", 1600, 700);
        extras.println("");
        extras.println_bonito("Um lugar enorme feito apenas de nuvens, \ne um forte raio de luz atingindo o centro", 1600, 700);
        extras.println("");
        extras.println_bonito("Um buraco se abre no chao, parece que tem algo vindo...", 1400, 700);
        extras.println("");
        extras.println_bonito("Voce sente a forca do vento aumentando consideravelmente!", 1400, 700);
        extras.println("");
        extras.println_bonito("É ele! O criador deste lugar!", 1200, 700);
        ascii.printMonstroAsciipeloNome("ocriador", true);
        extras.delay(500);
        extras.println("");
        extras.println_bonito("......", 2000, 700);
        extras.println("");
        extras.println_bonito("Um cachorro?", 2000, 700);
        extras.println("");
        extras.println_bonito("'AUAU'", 400, 400);
        extras.println("");
        extras.println_bonito("Ele parece estar querendo lutar, ele avanca em voce!", 1500, 700);
        combate.lutaini(2, handler.bosses.size()-2);
        extras.println("");
        extras.println_bonito("Ele parece estar animado!", 900, 500);
        extras.println("");
        extras.println_bonito("Ele comeca a brilhar! Ele esta se transformando!", 800, 1000);
        ascii.printMonstroAsciipeloNome("criador", true);
        extras.println("");
        extras.println_bonito("'Ola humano, voce fez bem de chegar ate aqui...'", 800, 1000);
        extras.println("");
        extras.println_bonito("'Eu sou o responsavel por esta prisao de onde voce escapou, \no que controla quem sai e quem entra...'", 1200, 800);
        extras.println("");
        extras.println_bonito("'E é minha responsabilidade criar e treinar todos \nque estão aqui para voltarem ao mundo real'", 1200, 800);
        extras.println("");
        extras.println_bonito("'Voce acaba de atingir a ultima fase para sua luta pela liberdade'", 1200, 800);
        extras.println("");
        extras.println_bonito("'Basta apenas voce provar para mim que tudo que voce adquiriu'", 1200, 800);
        extras.println("");
        extras.println_bonito("'Entao se prepare, pois desta vez, \neu usarei todo o meu poder contra voce! '", 1200, 800);
        combate.lutaini(2, handler.bosses.size()-1);
        extras.println("");
        extras.println_bonito(".........\n.......", 2000, 800);
        extras.println("");
        ascii.printMonstroAsciipeloNome("criador", true);
        extras.println_bonito("'.....'", 1200, 800);
        extras.println("");
        extras.println_bonito("'Eu estou impressionado'", 1200, 800);
        extras.println("");
        extras.println_bonito("'Voce superou minhas expectativas, passou por todos os \ndesafios eu criei e no final ate mesmo me derrotou'", 2500, 900);
        extras.println("");
        extras.println_bonito("'Eu irei concender-lhe a liberdade que tanto desejou, e um ultimo desejo...'", 2500, 900);
        extras.println("");
        extras.println_bonito("'Agora, qual sera o seu desejo?'", 2500, 1000);
        extras.println("");
        extras.println_bonito("Digite o seu desejo divino:", 1500, 1000);
        String des = extras.inputS();
        extras.println("");
        extras.println_bonito("'Voce quer o que?'", 1500, 1000);
        extras.println("");
        extras.println_bonito("'" + des + "?'", 1500, 1000);
        extras.println("");
        extras.println_bonito("'Tudo bem, eu nunca entendi as necessidades humanas, \nmas eu irei fazer seu desejo tornar-se uma realidade'", 3000, 1000);
        extras.println("");
        extras.println_bonito("'Adeus humano, foi uma honra conhecer voce...'", 1500, 3000);
        extras.delay(1000);
        extras.console_clear();
        janela.clearJmonsAscii(true);
        fimJogo();
    }

    private static void fimJogo(){
        extras.println("");
        extras.println_bonito("PARABENS!", 500, 500);
        extras.println("");
        extras.println_bonito("Voce terminou Desejo Divino!", 500, 500);
        extras.println("");
        extras.println_bonito("Um jogo criado por:", 500, 500);
        extras.println("");
        extras.println_bonito("Leonardo Matthew Knight\nMatthaus Rautenberg Roth\nVinícius Kovalhuk Borini", 2000, 500);
        extras.println("");
        extras.println_bonito("Obrigado por jogar!", 500, 2000);
        extras.println("");
        extras.println_bonito("Verifique seu progresso nesta ultima tentativa:", 600, 600);
        handler.jogador.printStatsFim();
        extras.println("");
        extras.println_bonito("Reiniciar o jogo do inicio?", 600, 600);
        if(extras.simNao()){
            handler.Prun = 0;
            handler.jogador.resetNmonstrosderrot();
            handler.jogador.resetBuff();
            player.P_aliado = false;
            extras.console_clear();
            handler.NovoJogo();
        }else{
            System.exit(1);
        }
    }

    public String getNome(){return this.nome;}
    public String getDesc(){return this.desc;}
}
