package adicionais;

import java.util.ArrayList;

import entidades.NPC;
import entidades.classes;
import entidades.monstros_b;
import entidades.monstros_f;
import entidades.player;
import fases.fases;
import itens.armaduras;
import itens.armas;
import itens.consumiveis;

//essa classe vai criar e segurar todos os objetos do jogo, eu vou deixar tudo aqui para ficar um pouco mais facil de ver o id de cada coisa
public class handler {
    public static player jogador;
    public static ArrayList<classes> classe = new ArrayList<classes>(); // todas as classes presentes no jogo
    public static ArrayList<monstros_b> bossesrand = new ArrayList<monstros_b>(); //esses sao os monstros fortes que tem chance de aparecer aleatoriamente em qualquer andar
    public static ArrayList<monstros_b> bosses = new ArrayList<monstros_b>(); //esses sao os monstros fortes que apenas aparecem no fim da fase
    public static ArrayList<monstros_f> monstros = new ArrayList<monstros_f>(); //esses sao os monstros fracos que podem aparecer em qualquer andar 
    public static ArrayList<fases> fase = new ArrayList<fases>(); // todas as fases presentes no jogo
    public static ArrayList<armas> arma = new ArrayList<armas>(); // todas as armas presentes no jogo, de todos os tipos
    public static ArrayList<armaduras> armor = new ArrayList<armaduras>(); // todas as armas presentes no jogo, de todos os tipos
    public static ArrayList<consumiveis> consu = new ArrayList<consumiveis>(); // todas as armas presentes no jogo, de todos os tipos
    public static ArrayList<NPC> npcs = new ArrayList<NPC>();

    public static void iniciarJogo(){
        extras.print("[Jogo]: Carregando...");
        iniFases();
        iniMonstros();
        iniClasses();
        iniItens();
        extras.println("[Jogo]: Carregado");
        extras.println(" ");
        extras.print("[Jogo]: Precione enter para comecar o jogo...");
        extras.inputS();
        NovoJogo();
    }

    static void iniNpcs(){
        extras.println("[NPC]: Criando NPCs...");
        handler.npcs.add(new NPC("Vendedor ambulante","Pessoa misteriosa que comercializa itens", 0, 5, 1, 1, 1, 5)); // 0
        handler.npcs.add(new NPC("Prisioneiro/Player","Outro jogador, mas aparenta estar mentalmente instavel, pode acabar lhe dando uma dica ou item", 0, 5, 1, 1, 1, 5)); // 1
        handler.npcs.add(new NPC("Mendigo","Encostado na parede e coberto por um pano surrado um velho senhor, um pouco sus, lhe pede um pouco de dinheiro", 1, 30, 8, 6, 5, 16)); // 2
        handler.npcs.add(new NPC("D-Dog","literalmente um doguinho, com uma espada...", 8, 20, 10, 4, 7, 10)); // 3
        extras.println("[NPC]: NPCs criados com sucesso");
    }

    static void iniMonstros(){
        /*monstros_f para monstros ou comuns
          monstros_b para boss ou incomuns
        */
        extras.print("[Monstro]: Criando Monstros...");
        iniMonstrosF();
        iniMonstrosB();
        extras.print("[Monstro]: Monstros criados com sucesso");
    }

    public static void resetMonstros(){
        handler.monstros.clear();
        iniMonstrosF();
        iniMonstrosB();
    }

    public static void iniMonstrosB() {
        handler.bosses.add(new monstros_b("King Slime", "Rei dos Slimes! Dizem que isto e o resultado da fusao de 8 slimes!", 0, 100, 20, 10, 4, 60)); // 0
        handler.bossesrand.add(new monstros_b("Ceifador", "Ele esta atras de voce, a sua hora chegou...", 4, 600, 25, 20, 22, 240)); // 1
        handler.bosses.add(new monstros_b("Ogro", "Voce invadiu o pantano dele!", 8, 70, 15, 10, 2, 76)); // 2
        handler.bossesrand.add(new monstros_b("Slime de ferro", "O rarissimo slime de ferro! Dizem que se voce derrotar ele, voce podera ficar rico!", 0, 5, 3, 99999, 100, 500)); // 3
    }

    public static void iniMonstrosF(){
        switch(fases.fase_atual){
            case 0:
            case 1: // cada fase vai ter a propria lista de monstros, esse vai ser pra fase 1
                handler.monstros.add(new monstros_f("Slime azul", "Parece uma gelatina.", 0, 10, 6, 2, 3, 6)); // 0
                handler.monstros.add(new monstros_f("Goblin guerreiro", "Goblin equipado com uma espada e escudo!", 2, 16, 7, 4, 5, 10)); // 1
                handler.monstros.add(new monstros_f("Goblin arqueiro", "Goblin equipado com arco e flecha", 3, 16, 6, 3, 8, 10)); // 2
                handler.monstros.add(new monstros_f("Mendigo", "Ele estava dormindo e voce o acordou.", 1, 30, 8, 6, 5, 16)); // 3
                handler.monstros.add(new monstros_f("Rato", "Ele parece estar atras de algo, e voce esta no caminho.", 0, 13, 5, 4, 15, 8)); // 4
                handler.monstros.add(new monstros_f("Mimic", "Um bau com itens?", 0, 20, 15, 20, 15, 14)); // 5
                handler.monstros.add(new monstros_f("Sapo", "Um sapo agressivo", 0, 10, 7, 2, 4, 8)); // 6
                break;
            case 2: // fase 2
                handler.monstros.add(new monstros_f("Slime vermelho", "Parece uma gelatina.", 0, 40, 15, 6, 5, 18)); // 0
                handler.monstros.add(new monstros_f("Esqueleto guerreiro", "Esqueleto equipado com uma espada e escudo!", 8, 55, 16, 11, 4, 20)); // 1
                handler.monstros.add(new monstros_f("Esqueleto arqueiro", "Esqueleto equipado com arco e flecha", 13, 36, 11, 6, 13, 20)); // 2
                handler.monstros.add(new monstros_f("Mendigo bebado", "Ele estava bebendo e voce o interrompeu", 6, 73, 20, 16, 10, 32)); // 3
                handler.monstros.add(new monstros_f("Fantasma", "BOO! Ele veio para te assustar", 0, 40, 25, 10, 15, 22)); // 4
                handler.monstros.add(new monstros_f("Mimic", "Um bau com itens?", 0, 20, 15, 20, 15, 20)); // 5
                handler.monstros.add(new monstros_f("Zumbi", "Infectado por um virus rarissimo, ele vai atras de cerebro humano", 0, 300, 35, 5, 15, 25)); // 6
                break;
        }
    }

    static void iniClasses(){
        extras.print("[Player]: Criando Classes...");
        handler.classe.add(new classes("Arqueiro", "Rapido e com alta destreza, pode usar armas de longo alcance, mas possui baixa defesa", 3, 0, 35, 5, 4, 8)); // 0
        handler.classe.add(new classes("Guerreiro","Forte e defensivo, pode causar alto dano em curto alcance mas possui pessima destreza", 1, 1, 35, 6, 3, 7)); // 1
        handler.classe.add(new classes("Paladino","Balanceado de todas as formas, usa arma de curto alcance e experiente em qualquer situacao", 2, 1, 50, 8, 5, 3)); // 2
        extras.print("[Player]: Classes criado com sucesso");
    }

    static void iniItens(){
        extras.print("[Armas]: Criando Armas...");
        iniArmas();
        extras.print("[Armas]: Armas criadas com sucesso");
        extras.print("[Armaduras]: Criando Armaduras...");
        iniArmor();
        extras.print("[Armaduras]: Armaduras criadas com sucesso");

    }

    static void iniArmas(){
        handler.arma.add(new armas("nada", 2, "curto", "omega leve")); // 0
        handler.arma.add(new armas("Faca de passar manteiga", 4, "curto", "super leve")); // 1
        handler.arma.add(new armas("Espada de madeira", 7, "curto", "leve")); // 2
        handler.arma.add(new armas("Arco de madeira", 6, "longo", "leve")); // 3
        handler.arma.add(new armas("Marrete", 8, "curto", "pesada")); // 4
        handler.arma.add(new armas("Grande espada de madeira", 12, "curto", "super pesado")); // 5
        handler.arma.add(new armas("Soco ingles", 7, "curto", "omega leve")); // 6
        handler.arma.add(new armas("Faca de ferro", 7, "curto", "super leve")); // 7
        handler.arma.add(new armas("Espada de ferro", 10, "curto", "leve")); // 8
        handler.arma.add(new armas("Espada longa de ferro", 12, "longo", "pesado")); // 9
        handler.arma.add(new armas("Estilingue", 5, "longo", "super leve")); // 10
        handler.arma.add(new armas("Arco longo de madeira", 12, "longo", "super pesado")); // 11
        handler.arma.add(new armas("Martelo", 13, "curto", "pesado")); // 12
        handler.arma.add(new armas("Arco de Ferro", 9, "longo", "leve")); // 13
    }

    static void iniArmor(){
        handler.armor.add(new armaduras("Armadura de Couro", 2, 1.1, "super leve"));
        handler.armor.add(new armaduras("Armadura de Ferro", 4, 0.7, "pesado"));
    }

    static void iniConsu(){
        extras.print("[Consumiveis]: Criando Consumiveis...");
        handler.consu.add(new consumiveis("Fraca Pocao de Vida", "cura um pouco a sua vida", 1.2)); // 0 // cura 20% da vida da pessoa
        handler.consu.add(new consumiveis("Fraca Pocao de Forca", "aumenta sua forca um pouco", 1.2)); // 1 // aumenta em 20% a forca da pessoa
        handler.consu.add(new consumiveis("Fraca Pocao de Destreza","aumenta sua destreza um pouco",1.2));// 2 // aumenta em 20% a destreza da pessoa
        handler.consu.add(new consumiveis("Media Pocao de Vida", "cura um bocado a sua vida", 1.5)); // 0 // cura 50% da vida da pessoa
        handler.consu.add(new consumiveis("Media Pocao de Forca", "aumenta sua forca um bocado", 1.5)); // 1 // aumenta em 50% a forca da pessoa
        handler.consu.add(new consumiveis("Media Pocao de Destreza","aumenta sua destreza um bocado",1.5));// 2 // aumenta em 50% a destreza da pessoa
        handler.consu.add(new consumiveis("Forte Pocao de Vida", "cura quase toda a sua vida", 1.75)); // 0 // cura 75% da vida da pessoa
        handler.consu.add(new consumiveis("Forte Pocao de Forca", "aumenta sua forca consideravelmente", 1.75)); // 1 // aumenta em 75% a forca da pessoa
        handler.consu.add(new consumiveis("Forte Pocao de Destreza","aumenta sua destreza consideravelmente",1.75));// 2 // aumenta em 75% a destreza da pessoa
        handler.consu.add(new consumiveis("Maxima Pocao de Vida", "cura completamente a sua vida", 2)); // 0 // cura 100% da vida da pessoa
        handler.consu.add(new consumiveis("Maxima Pocao de Forca", "Dobra a sua forca", 2)); // 1 // aumenta em 100% a forca da pessoa
        handler.consu.add(new consumiveis("Maxima Pocao de Destreza","Dobra sua destrexa", 2));// 2 // aumenta em 100% a destreza da pessoa

        extras.print("[Consumiveis]: Consumiveis criado com sucesso");
    }

    static void iniFases(){
        handler.fase.add(new fases("Masmorra","foda", 5)); // ordem das fases e de cima para baixo
        handler.fase.add(new fases("aaaaa","sim", 5));
    }

    public static void NovoJogo(){
        extras.console_clear();
        extras.println_bonito("Bem vindo ao mundo de foda nao sei o nome! ", 1000, 500);
        NovoJogador();
        fases.comecarFases(); 
        //aqui vai ser pra dar a introducao para o jogo
    }

    static void NovoJogador(){
        extras.println_bonito("Digite o seu nome: ", 700, 100);
        handler.jogador = new player(extras.inputS(), "", 0, 0, 30, 5, 5, 5); //vai criar os valores do jogador na classe handler para que possa acessa-la em outras classes
        setClasseJogador();
    }

    static void setClasseJogador(){
        int res2 = 0;
        int res3 = 0;
        while(res2 != 1){
            extras.println_bonito("Digite qual classe voce gostaria de jogar: ", 1000, 500);
            classes.printClasses();
            String res = extras.inputS().toLowerCase();
            res2 = 1;
            switch(res){
                case"arqueiro":
                    res3 = 0;
                    break;
                case"guerreiro":
                    res3 = 1;
                    break;
                case"paladino":
                    res3 = 2;
                    break;
                default:
                    extras.println_bonito("Por favor digite uma classe válida...", 1000, 500);
                    res2 = 0;
            }
        }
        handler.jogador.setVidamax(handler.classe.get(res3).getVidamax());
        handler.jogador.setVida(handler.classe.get(res3).getVida());
        handler.jogador.setForca(handler.classe.get(res3).getForca());
        handler.jogador.setDefesa(handler.classe.get(res3).getDefesa());
        handler.jogador.setDestreza(handler.classe.get(res3).getDestreza());
        handler.jogador.setArmaEquip(handler.classe.get(res3).getArmaEquip());
        handler.jogador.setClasse(handler.classe.get(res3).getNome());
    }

}
