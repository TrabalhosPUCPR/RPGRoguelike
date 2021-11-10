package adicionais;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ascii.ascii;
import entidades.NPC;
import entidades.classes;
import entidades.monstros_b;
import entidades.monstros_f;
import entidades.player;
import fases.andares;
import fases.fases;
import itens.armaduras;
import itens.armas;
import itens.consumiveis;
import itens.inventario;
import itens.itensDef;
import itens.itensOfen;
import itens.itensMisc;

//essa classe vai criar e segurar todos os objetos do jogo, eu vou deixar tudo aqui para ficar um pouco mais facil de ver o id de cada coisa
public class handler {
    public static player jogador;
    public static ArrayList<classes> classe = new ArrayList<classes>(); // todas as classes presentes no jogo
    public static ArrayList<monstros_b> bossesrand = new ArrayList<monstros_b>(); //esses sao os monstros fortes que tem chance de aparecer aleatoriamente em qualquer andar
    public static ArrayList<monstros_b> bosses = new ArrayList<monstros_b>(); //esses sao os monstros fortes que apenas aparecem no fim da fase
    public static ArrayList<monstros_f> monstros = new ArrayList<monstros_f>(); //esses sao os monstros fracos que podem aparecer em qualquer andar 
    public static ArrayList<fases> fase = new ArrayList<fases>(); // todas as fases presentes no jogo
    public static ArrayList<armas> arma = new ArrayList<armas>(); // todas as armas presentes no jogo, de todos os tipos
    public static ArrayList<armaduras> armor = new ArrayList<armaduras>(); // todas as armaduras presentes no jogo, de todos os tipos
    public static ArrayList<consumiveis> consu = new ArrayList<consumiveis>(); // todos os itens consumiveis presentes no jogo, de todos os tipos
    public static ArrayList<itensOfen> itemOfen = new ArrayList<itensOfen>(); // todos os itens ofensivos presentes no jogo, de todos os tipos      }}
    public static ArrayList<itensDef> itemDef = new ArrayList<itensDef>(); // todos os itens defensivos presentes no jogo, de todos os tipos      }} }}  TODOS ESSES ITENS PODEM DAR EFEITOS NEGARIVOS TAMBEM
    public static ArrayList<itensMisc> itemMisc = new ArrayList<itensMisc>(); // todos os itens aleatorios presentes no jogo, de todos os tipos     }}  no codigo ta como itens mas no jogo vai aparecer como acessorio
    public static ArrayList<NPC> npcs = new ArrayList<NPC>();
    public static List<Runnable> andar = new ArrayList<>();
    public static ArrayList<ascii> asciiart = new ArrayList<ascii>();

    public static void carregarJogo() throws FileNotFoundException{
        new janela();
        extras.print("[Jogo]: Carregando...");
        iniLerTxtAscii();
        iniFases();
        iniAndares();
        iniClasses();
        extras.print("[NPC]: Criando NPCs...");
        iniNpcs();
        extras.print("[NPC]: NPCs criados com sucesso");
        iniMonstros();
        iniItens();
        extras.print("[Jogo]: Carregado");
        extras.println(" ");
        extras.print("[Jogo]: Precione enter para comecar o jogo...");
        extras.inputS();
        menuInicial();
    }

    static void menuInicial(){
        while(true){
            ascii.printMonstroAsciipeloNome("logoepico", false);
            extras.console_clear();
            ascii.printAsciipeloNome("logo");
            //ascii.printMonstroAsciipeloNome("homem", true);
            extras.println("");
            extras.println_bonito("jogar", 200, 30);
            extras.println_bonito("config", 200, 30);
            switch(extras.inputS().toLowerCase()){
                case "jogar":
                    handler.NovoJogo();
                break;
                case "config":
                    configuracao.configurar();
                break;
                default:
                break;
            }
        }
    }

    public static void iniLerTxtAscii() throws FileNotFoundException{
        // pra criar um novo ascii art e so colocar dentro de um arquivo de texto dentro do ascii/arts/ e fazer igual como ta ai, so coloca o nome em minusculo e facil
        extras.print("[ASCII]: Criando artes ASCII...");
        handler.asciiart.add(new ascii("logo", new File("ascii/arts/logo.txt")));
        handler.asciiart.add(new ascii("logoepico", new File("ascii/arts/logoepico.txt")));
        handler.asciiart.add(new ascii("slime", new File("ascii/arts/monstros/slime.txt")));
        handler.asciiart.add(new ascii("vendedor", new File("ascii/arts/monstros/vendedor.txt")));
        handler.asciiart.add(new ascii("shrek", new File("ascii/arts/monstros/shrek.txt")));
        handler.asciiart.add(new ascii("sapo", new File("ascii/arts/monstros/sapo.txt")));
        handler.asciiart.add(new ascii("mendigo", new File("ascii/arts/monstros/mendigo.txt")));
        handler.asciiart.add(new ascii("generico", new File("ascii/arts/monstros/generico.txt")));
        handler.asciiart.add(new ascii("goblin", new File("ascii/arts/monstros/goblin.txt")));
        handler.asciiart.add(new ascii("kingslime", new File("ascii/arts/monstros/kingslime.txt")));
        handler.asciiart.add(new ascii("morcego", new File("ascii/arts/monstros/morcego.txt")));
        handler.asciiart.add(new ascii("rato", new File("ascii/arts/monstros/rato.txt")));
        handler.asciiart.add(new ascii("ratagao", new File("ascii/arts/monstros/ratagao.txt")));
        handler.asciiart.add(new ascii("esqueleto", new File("ascii/arts/monstros/esqueleto.txt")));
        handler.asciiart.add(new ascii("fantasma", new File("ascii/arts/monstros/fantasma.txt")));
        handler.asciiart.add(new ascii("zumbi", new File("ascii/arts/monstros/zumbi.txt")));
        handler.asciiart.add(new ascii("necromante", new File("ascii/arts/monstros/necromante.txt")));
        handler.asciiart.add(new ascii("objeto", new File("ascii/arts/monstros/objeto.txt")));
        handler.asciiart.add(new ascii("assombracao", new File("ascii/arts/monstros/assombracao.txt")));
        handler.asciiart.add(new ascii("shadow", new File("ascii/arts/monstros/shadow.txt")));
        handler.asciiart.add(new ascii("ghoul", new File("ascii/arts/monstros/ghoul.txt")));
        handler.asciiart.add(new ascii("senador", new File("ascii/arts/monstros/senador.txt")));
        handler.asciiart.add(new ascii("lula", new File("ascii/arts/monstros/lula.txt")));
        handler.asciiart.add(new ascii("mimic", new File("ascii/arts/monstros/mimic.txt")));
        handler.asciiart.add(new ascii("riche", new File("ascii/arts/monstros/riche.txt")));
        handler.asciiart.add(new ascii("homem", new File("ascii/arts/monstros/homem.txt")));
        handler.asciiart.add(new ascii("anubis", new File("ascii/arts/monstros/anubis.txt")));
        handler.asciiart.add(new ascii("morcegao", new File("ascii/arts/monstros/morcegao.txt")));
        handler.asciiart.add(new ascii("goblina", new File("ascii/arts/monstros/goblina.txt")));
        handler.asciiart.add(new ascii("lobo", new File("ascii/arts/monstros/lobo.txt")));
        handler.asciiart.add(new ascii("prisioneiro", new File("ascii/arts/monstros/prisioneiro.txt")));
        handler.asciiart.add(new ascii("estrangeiro", new File("ascii/arts/monstros/estrangeiro.txt"))); // por que esse jogo tem 2 desenhos do shrek? por que sim
        handler.asciiart.add(new ascii("cachorro", new File("ascii/arts/monstros/cachorro.txt")));
        handler.asciiart.add(new ascii("ddog", new File("ascii/arts/monstros/ddog.txt")));
        handler.asciiart.add(new ascii("freedom", new File("ascii/arts/monstros/freedom.txt")));
        handler.asciiart.add(new ascii("alcolico", new File("ascii/arts/monstros/alcolico.txt")));
        handler.asciiart.add(new ascii("lagarto", new File("ascii/arts/monstros/lagarto.txt")));
        handler.asciiart.add(new ascii("ladrao", new File("ascii/arts/monstros/ladrao.txt")));
        handler.asciiart.add(new ascii("jaywalker", new File("ascii/arts/monstros/jaywalker.txt")));
        handler.asciiart.add(new ascii("antena", new File("ascii/arts/monstros/antena.txt")));
        handler.asciiart.add(new ascii("ocriador", new File("ascii/arts/monstros/ocriador.txt")));
        handler.asciiart.add(new ascii("criador", new File("ascii/arts/monstros/criador.txt")));
        handler.asciiart.add(new ascii("ceifador", new File("ascii/arts/monstros/ceifador.txt")));
        extras.print("[ASCII]: Artes ASCII carregadas");
    }

    static void iniAndares(){
        // essa e na minha opiniao a forma mais facil de escolher uma funcao de um andar aleatoriamente 
        handler.andar.add(andares::NPC); // 0, numero de cada sala para fazer com que a chance de cada sala aparecer seja diferente
        handler.andar.add(andares::monstro); // 1
        // acho q vai ser so esses dois msm, pensei q seria mais
    }

    static void iniNpcs(){
        handler.npcs.add(new NPC("Vendedor ambulante","Pessoa misteriosa que comercializa itens", 0, 5, 1, 1, 1, 5, "vendedor")); // 0
        handler.npcs.add(new NPC("Prisioneiro","Outro jogador, mas aparenta estar mentalmente instavel e preso por algum motivo", handler.jogador.getArmaEquip(), handler.jogador.getVidamax()*2, handler.jogador.getForca(), (int)handler.jogador.getDefesa(), handler.jogador.getDestreza(), 5, "prisioneiro")); // 1
        handler.npcs.add(new NPC("Mendigo","Encostado na parede e coberto por um pano surrado um velho senhor, um pouco sus, lhe pede um pouco de dinheiro", 1, 30, 8, 6, 5, 16, "mendigo")); // 2
        handler.npcs.add(new NPC("D-Dog","literalmente um doguinho, com uma espada...", 8, 20, 10, 4, 7, -5, "ddog")); // 3
        handler.npcs.add(new NPC("Mendigo bebado", "Ele estava bebendo e voce o interrompeu", 6, 73, 20, 16, 10, 32, "mendigo")); // 4
        handler.npcs.add(new NPC("Estrangeiro", "Uma pessoa vestida como se tivesse vindo de outro mundo, fala uma lingua muito estranha", 8, 43, 9, 10, 13, 52, "estrangeiro")); // 5
    }

    static void iniMonstros(){
        /*monstros_f para monstros ou comuns
          monstros_b para boss ou incomuns
        */

        //  PARA CRIAR UM MONSTRO E SO COLOCAR OS VALORES DELE CERTINHO NO ARRAY DO TIPO DE MONSTRO DELE
        /*
            monstros = monstros comuns
            bossesrand = monstros incomuns
            bosses = bosses da fase
        */

        /*  AS VARIAVEIS SAO:
        nome
        descricao
        id da arma inicial
        vidamax
        forca
        defesa
        destreza
        exp que vai dropar quando morrer
        fases que aparece
        */

        extras.print("[Monstro]: Criando Monstros...");
        iniMonstrosF();
        iniMonstrosB();
        iniMonstrosBR();
        extras.print("[Monstro]: Monstros criados com sucesso");
    }

    public static void resetMonstros(){
        handler.monstros.clear();
        handler.bosses.clear();
        handler.bossesrand.clear();
        handler.npcs.clear();
        iniMonstrosF();
        iniMonstrosB();
        iniMonstrosBR();
        iniNpcs();
    }

    public static void iniMonstrosB() {
        // fase 1
        handler.bosses.add(new monstros_b("King Slime", "Rei dos Slimes! Dizem que isto e o resultado da fusao de 8 slimes!", 0, 50, 20, 10, 4, 60, new int[]{1}, "kingslime")); // 0
        handler.bosses.add(new monstros_b("Ogro", "Voce invadiu o pantano dele!", 8, 40, 15, 10, 7, 76, new int[]{1}, "shrek")); // 1

        // fase 2
        if(handler.jogador.getClasse().toLowerCase().equals("dev")){
            handler.bosses.add(new monstros_b("Shadow " + handler.jogador.getNome(), "Um lado seu obscuro...", handler.jogador.getArmaEquip(), handler.jogador.getVidamax(), handler.jogador.getForca()/2, (int)handler.jogador.getDefesa()/2, handler.jogador.getDestreza()/2, 106, new int[]{2}, "shadow")); // 2
        }else{
            handler.bosses.add(new monstros_b("Shadow " + handler.jogador.getNome(), "Um lado seu obscuro...", handler.jogador.getArmaEquip(), handler.jogador.getVidamax()*2, handler.jogador.getForca(), (int)handler.jogador.getDefesa(), handler.jogador.getDestreza(), 106, new int[]{2}, "shadow")); // 2
        }
        //fase 3
        //handler.bosses.add(new monstros_b("Aguia do deserto", "Quando ele ataca, se parece com uma bala saindo de uma arma!", 15, 90, 32, 15, 33, 159, new int[]{3}, "generico")); // 3
        //handler.bosses.add(new monstros_b("Farao", "Rei-deus do deserto! Usa magias para atacar!", 15, 100, 5, 23, 20, 156, new int[]{3}, "generico")); // 4

        //fase 4
        handler.bosses.add(new monstros_b("Senador Armstrong", "Extremamente patriota, fez infusao com nanomachines para fortalecer seus musculos a ponto de ser superhumano", 0, 200, 25, 30, 35, 156, new int[]{3}, "senador")); // 5

        //fase 5
        //handler.bosses.add(new monstros_b("Quetzalcoatl", "A Serpente com plumas! Deusa de El Dorado!", 0, 500, 82, 53, 60, 340, new int[]{5}, "generico")); // 6

        //ultima fase: ceu
        handler.bosses.add(new monstros_b("Criador", "O criador do universo, capaz de realizar o desejo de qualquer que se mostrar mais poderoso que ele!", 0, 200, 20, 30, 35, 1000, new int[]{4}, "ocriador")); // 7
        handler.bosses.add(new monstros_b("Verdadeira forma do Criador", "O criador do universo, capaz de realizar o desejo de qualquer que se mostrar mais poderoso que ele!", 0, 200, 30, 30, 35, 1000, new int[]{4}, "criador")); // 7
    }

    public static void iniMonstrosBR(){
        handler.bossesrand.add(new monstros_b("Ceifador", "Ele esta atras de voce, a sua hora chegou...", 17, 600, 15*fases.fase_atual, 10*fases.fase_atual, 17*fases.fase_atual, 240, new int[]{1, 2, 3}, "ceifador")); // 0
        handler.bossesrand.add(new monstros_b("Slime de ferro", "O rarissimo slime de ferro! Dizem que se voce derrotar ele, voce podera ficar rico!", 0, 5, 3, 99999, 75, 1000, new int[]{1, 2, 3}, "slime")); // 1
        handler.bossesrand.add(new monstros_b("Nouveau Riche", "Um homem tao rico que se veste com um terno de ouro", 0, 50, 30, 16, 15, 30, new int[]{1, 2, 3}, "riche")); // 2
        handler.bossesrand.add(new monstros_b("Lula", "Lula gigante com 9 tentáculos, o décimo parece ter sido arrancado", 0, 300, 30, 13, 24, 645, new int[]{2, 3}, "lula")); // 3
        handler.bossesrand.add(new monstros_b("Anubis", "Um deus do egito, dizem que era responsavel por guiar as almas ate o submundo", 22, 200, 25, 19, 17, 400, new int[]{3}, "anubis")); // 4
        handler.bossesrand.add(new monstros_b("Homem de preto", "Um deus do egito, dizem que era responsavel por guiar as almas ate o submundo", 21, 150, 20, 18, 20, 410, new int[]{3}, "homem")); // 5
        handler.bossesrand.add(new monstros_b("Mimic", "Um bau com itens?", 0, 20, 15, 10, 15, 14, new int[]{1, 2}, "mimic")); // 6
    }

    public static void iniMonstrosF(){     //NOME          // DESC             //ID ARMA, VIDAMAX, FORCA, DEFESA, DESTREZA, EXP, NFASES, ASCII
        handler.monstros.add(new monstros_f("Slime azul", "Parece uma gelatina.", 0, 13, 6, 2, 3, 6, new int[]{1}, "slime")); // 0
        handler.monstros.add(new monstros_f("Goblin guerreiro", "Goblin equipado com uma espada e escudo!", 2, 16, 7, 4, 5, 10, new int[]{1}, "goblin")); // 1
        handler.monstros.add(new monstros_f("Goblin arqueiro", "Goblin equipado com arco e flecha", 3, 16, 6, 3, 8, 10, new int[]{1}, "goblina")); // 2
        handler.monstros.add(new monstros_f("Lobo", "Um lobo que esta furioso", 0, 17, 13, 6, 8, 20, new int[]{1}, "lobo")); // 3
        handler.monstros.add(new monstros_f("Rato", "Ele parece estar atras de algo, e voce esta no caminho.", 0, 13, 5, 4, 15, 8, new int[]{1}, "rato")); // 4
        handler.monstros.add(new monstros_f("Morcego", "Um morcego que esta faminto", 0, 13, 5, 4, 17, 14, new int[]{1}, "morcego")); // 5
        handler.monstros.add(new monstros_f("Sapo", "Um sapo agressivo", 0, 15, 7, 2, 4, 8, new int[]{1}, "sapo")); // 6
        handler.monstros.add(new monstros_f("Ratagão", "Um rato que nao aceita ser chamado de rato de esgoto", 0, 23, 15, 4, 5, 25, new int[]{1}, "ratagao")); // 4
        handler.monstros.add(new monstros_f("Morcegão", "Exatamente como o nome sugere, um morcego......só que gigante", 0, 20, 7, 5, 15, 25, new int[]{1}, "morcegao")); // 5

        handler.monstros.add(new monstros_f("Slime vermelho", "Parece uma gelatina.", 0, 25, 13, 9, 5, 18, new int[]{2}, "slime")); // 9
        handler.monstros.add(new monstros_f("Esqueleto guerreiro", "Esqueleto equipado com uma espada e escudo!", 8, 35, 16, 11, 4, 20, new int[]{2}, "esqueleto")); // 10
        handler.monstros.add(new monstros_f("Esqueleto arqueiro", "Esqueleto equipado com arco e flecha", 13, 30, 11, 6, 13, 20, new int[]{2}, "esqueleto")); // 11
        handler.monstros.add(new monstros_f("Necromante", "Consegue se comunicar com os mortos, por meio de magia", 7, 23, 8, 10, 9, 17, new int[]{2}, "necromante")); // 12
        handler.monstros.add(new monstros_f("Fantasma", "BOO! Ele veio para te assustar", 0, 28, 25, 10, 15, 22, new int[]{2}, "fantasma")); // 13
        handler.monstros.add(new monstros_f("Ghoul", "Humanoide que vai roubar teu dinheiro, e seu sangue, mas ninguem sabe exatamente o que ele é", 0, 25, 14, 10, 15, 25, new int[]{2}, "ghoul")); // 14
        handler.monstros.add(new monstros_f("Zumbi", "Infectado por um virus rarissimo, ele vai atras de cerebro humano", 0, 40, 25, 5, 15, 25, new int[]{2}, "zumbi")); // 15
        handler.monstros.add(new monstros_f("Assombração", "Uma assombração horripilante", 0, 36, 30, 8, 8, 31, new int[]{2}, "assombracao")); // 16
        handler.monstros.add(new monstros_f("Objeto inanimado", "Um objeto que se move, mas nao tem vida", 0, 50, 15, 14, 6, 24, new int[]{2}, "objeto")); // 17

        handler.monstros.add(new monstros_f("Mini mecha Danilo", "", 12, 65, 20, 20, 20, 54, new int[]{0}, "generico")); // 18
        handler.monstros.add(new monstros_f("Cheeseburger Freedom Man", "Um homem extremamente patriota que se candidatou a presidencia em suporte a legalizacao da maconha", 21, 54, 24, 17, 20, 41, new int[]{3}, "freedom")); // 19
        handler.monstros.add(new monstros_f("Pessoa Alcolica", "Uma pessoa que cheira de bebida alcolica", 24, 45, 16, 21, 25, 37, new int[]{3}, "alcolico")); // 20
        handler.monstros.add(new monstros_f("Furtador de Carteiras", "Um ladrao que rouba a carteira no bolso de pessoas distraidas", 7, 38, 15, 15, 34, 36, new int[]{3}, "ladrao")); // 21
        handler.monstros.add(new monstros_f("Homem lagarto", "Um lagarto que saiu do seu disfarce de humano", 18, 49, 26, 15, 22, 46, new int[]{3}, "lagarto")); // 22
        handler.monstros.add(new monstros_f("Antena 6g ambulante", "Uma antena que envia raios fatais 6g", 0, 70, 20, 15, 15, 37, new int[]{3}, "antena")); // 23
        handler.monstros.add(new monstros_f("Serial Jaywalker", "Um homem que ja causou milhoes de acidentes ", 7, 38, 19, 23, 25, 40, new int[]{3}, "jaywalker")); // 24
    }

    static void iniClasses(){
        //  PARA CRIAR UMA NOVA CLASSE TENQ COLOCAR OS VALORES AQUI, E NO LEVELUP() NO PLAYER.JAVA DEFINIR OS PONTOS QUE GANHA QUANDO UPA DE NIVEL

        /*  AS VARIAVEIS SAO:
        nome
        descricao
        id da arma inicial
        id da armadura inicial
        vidamax
        forca
        defesa
        destreza
        tipo de arma que consegue usar: 0 = curto; 1 = longo; 2 = todos;
        */
        extras.print("[Player]: Criando Classes...");
        handler.classe.add(new classes("Arqueiro", "Rapido e com alta destreza, pode usar armas de longo alcance, mas possui baixa defesa", 3, 1, 40, 5, 4, 8, 1)); // 0
        handler.classe.add(new classes("Guerreiro","Forte e defensivo, pode causar alto dano em curto alcance mas possui pessima destreza", 2, 1, 40, 6, 3, 7, 0)); // 1
        handler.classe.add(new classes("Paladino","Balanceado de todas as formas, usa arma de curto alcance e experiente em qualquer situacao", 2, 2, 40, 7, 4, 4, 0)); // 2
        handler.classe.add(new classes("Despojado","Roubado de todos os seus pertences, comeca fraco mas ganha pontos em status extremamente rapido e pode usar qualquer arma", 23, 0, 40, 5, 3, 5, 2)); // 3
        handler.classe.add(new classes("Dev","dev mode", 1, 2, 999, 999, 999, 999, 2)); // 3

        handler.jogador = new player("", "", 0, 0, 30, 5, 5, 5, 0); //vai criar os valores do jogador na classe handler para que possa acessa-la em outras classes
        extras.print("[Player]: Classes criado com sucesso");
    }

    static void iniItens(){
        // PARA CRIAR QUALQUER ITEM NOVO E SO COLOCA ADICIONA ELE NO ARRAYLIST DO ITEM

        extras.print("[Armas]: Criando Armas...");
        iniArmas();
        extras.print("[Armas]: Armas criadas com sucesso");
        extras.print("[Armaduras]: Criando Armaduras...");
        iniArmor();
        extras.print("[Armaduras]: Armaduras criadas com sucesso");
        extras.print("[Acessorios]: Criando Acessorios...");
        iniAce();
        extras.print("[Acessorios]: Acessorios criado com sucesso");
        extras.print("[Consumiveis]: Criando Consumiveis...");
        iniConsu();
        extras.print("[Consumiveis]: Consumiveis criados com sucesso");
    }

    static void iniArmas(){
        // PARA CRIAR UMA NOVA ARMA E SO COLOCAR AQUI NESSE ARRAYLIST TB, A RARIDADE DEFINE QUAIS FASES ELE PODE APARECER

        /*  AS VARIAVEIS SAO
            nome
            ataque
            tipo
            peso
            valor em dinheiro
            raridade
        */
        handler.arma.add(new armas("nada", 0, "curto", "omega leve", 0, 0)); // 0
        handler.arma.add(new armas("Faca de passar manteiga", 4, "curto", "super leve", 15, 1)); // 1
        handler.arma.add(new armas("Espada de madeira", 7, "curto", "leve", 26, 1)); // 2
        handler.arma.add(new armas("Arco de madeira", 6, "longo", "leve", 26, 1)); // 3
        handler.arma.add(new armas("Marrete", 8, "curto", "pesada", 32, 1)); // 4
        handler.arma.add(new armas("Grande espada de madeira", 12, "curto", "super pesado", 40, 2)); // 5
        handler.arma.add(new armas("Soco ingles", 7, "curto", "omega leve", 40, 2)); // 6
        handler.arma.add(new armas("Faca de ferro", 7, "curto", "super leve", 37, 1)); // 7
        handler.arma.add(new armas("Espada de ferro", 10, "curto", "leve", 39, 2)); // 8
        handler.arma.add(new armas("Espada longa de ferro", 12, "longo", "pesado", 40, 2)); // 9
        handler.arma.add(new armas("Estilingue", 5, "longo", "super leve", 20, 1)); // 10
        handler.arma.add(new armas("Arco longo de madeira", 12, "longo", "super pesado", 41, 2)); // 11
        handler.arma.add(new armas("Martelo", 13, "curto", "pesado", 45, 2)); // 12
        handler.arma.add(new armas("Arco de Ferro", 9, "longo", "leve", 36, 2)); // 13
        handler.arma.add(new armas("Zarabatana", 6, "longo", "super leve", 42, 1)); // 14
        handler.arma.add(new armas("Garras de ferro", 12, "curto", "omega leve", 63, 3)); // 15
        handler.arma.add(new armas("Boomerang", 9, "longo", "omega leve", 46, 2)); // 16
        handler.arma.add(new armas("Lanca", 16, "curto", "leve", 69, 3)); // 17
        handler.arma.add(new armas("Clava", 18, "curto", "pesado", 65, 3)); // 18
        handler.arma.add(new armas("Besta", 17, "longo", "pesado", 70, 3)); // 19
        handler.arma.add(new armas("Foice", 19, "curto", "pesado", 86, 3)); // 20
        handler.arma.add(new armas("Pistola", 20, "longo", "leve", 150, 3)); // 21
        handler.arma.add(new armas("Martelo enorme", 25, "curto", "super pesado", 180, 3)); // 22
        handler.arma.add(new armas("Bastao", 3, "curto", "super leve", 20, 1)); // 23
        handler.arma.add(new armas("Faca de passar manteiga dupla", 4, "curto", "omega", 30, 2)); // 24
        handler.arma.add(new armas("Grande Espada de Ferro", 17, "curto", "super pesado", 57, 2)); // 25
        handler.arma.add(new armas("Grande Espada de Topázio", 25, "curto", "super pesado", 96, 3)); // 26
        handler.arma.add(new armas("Revolver", 26, "longo", "pesado", 103, 3)); // 27
        handler.arma.add(new armas("Espada dupla de ferro", 10, "curto", "omega leve", 110, 3)); // 28
        handler.arma.add(new armas("Espingarda Antiga", 28, "longo", "super pesado", 145, 3)); // 29
        handler.arma.add(new armas("Grande Porrete de Madeira", 36, "curto", "super pesado", 200, 4)); // 30
        handler.arma.add(new armas("Arco de Luminite", 29, "longo", "super leve", 210, 4)); // 31
        handler.arma.add(new armas("Grande Espada Solar", 40, "curto", "super pesado", 294, 4)); // 32
        handler.arma.add(new armas("Faca de Nebula", 30, "curto", "super leve", 237, 4)); // 33
        handler.arma.add(new armas("Poeira estrelar", 18, "curto", "omega leve", 267, 4)); // 34
        handler.arma.add(new armas("Metralhadora de Vortice", 37, "longo", "super pesado", 285, 4)); // 35
    }

    static void iniArmor(){
        // PARA CRIAR UMA NOVA ARMADURA E SO COLOCAR AQUI NESSE ARRAYLIST TB, A RARIDADE DEFINE QUAIS FASES ELE PODE APARECER

        /*  AS VARIAVEIS SAO
            nome
            defesa
            bonus de evasao
            peso
            valor em dinheiro
            raridade
        */
        handler.armor.add(new armaduras("nada", 0, 1, "super leve", 0, 0)); // 0
        handler.armor.add(new armaduras("Armadura de Couro", 4, 1.1, "super leve", 30, 1)); // 1
        handler.armor.add(new armaduras("Armadura de Ferro", 6, 0.7, "pesado", 36, 1)); // 2
        handler.armor.add(new armaduras("Armadura de Pano", 0, 1.5, "omega leve", 63, 3)); // 3
        handler.armor.add(new armaduras("Armadura de Pedra", 5, 0.5, "super pesado", 32, 1)); // 4
        handler.armor.add(new armaduras("Armadura de Madeira", 2, 1.0, "leve", 24, 1)); // 5
        handler.armor.add(new armaduras("Armadura do Cavaleiro", 8, 1.0, "leve", 47, 2)); // 6
        handler.armor.add(new armaduras("Armadura do Juggernaut", 18, 0, "super pesado", 80, 3)); // 7
        handler.armor.add(new armaduras("Armadura de Gladiador", 7, 1.0, "leve", 43, 2)); // 8
        handler.armor.add(new armaduras("Armadura Acolchoada", 4, 3.0, "super leve", 56, 2)); // 9
        handler.armor.add(new armaduras("Armadura de placa", 2, 3.0, "super leve", 56, 2)); // 10
        handler.armor.add(new armaduras("Gibão de peles", 5, 1.5, "super leve", 48, 2)); // 11
        handler.armor.add(new armaduras("Armadura de couro reforçado", 6, 1.1, "super leve", 40, 2)); // 12
        handler.armor.add(new armaduras("Cota de Malha", 5, 0.9, "leve", 37, 1)); // 13
        handler.armor.add(new armaduras("Armadura de Bronze", 3, 0.8, "pesado", 32, 1)); // 14
        handler.armor.add(new armaduras("Armadura de Farrapos", 11, 1.0, "leve", 63, 3)); // 15
        handler.armor.add(new armaduras("Manto de Assassino", 8, 2.0, "super leve", 87, 3)); // 16
        handler.armor.add(new armaduras("Armadura espanhola", 13, 0.8, "pesado", 91, 3)); // 17
        handler.armor.add(new armaduras("Armadura de Vortice", 18, 1.0, "super leve", 130, 4)); // 18
        handler.armor.add(new armaduras("Armadura Solar", 23, 0.5, "super pesado", 142, 4)); // 19
        handler.armor.add(new armaduras("Armadura Estrelar", 14, 1.6, "super leve", 122, 4)); // 20
        handler.armor.add(new armaduras("Armadura de Nebula", 20, 1.0, "pesado", 133, 4)); // 21

    }

    static void iniAce(){
        // PARA CRIAR ACESSORIO NOVO DE QUALQUER TIPO E SO COLOCAR AQUI NESSE ARRAYLIST TB, A RARIDADE DEFINE QUAIS FASES ELE PODE APARECER

        /*  AS VARIAVEIS DE UM ITEM DEFENSIVO SAO
            nome
            descricao
            defesa
            destreza
            valor em dinheiro
            raridade
        */
        // defensivos
        handler.itemDef.add(new itensDef("nada", "nada", 0, 0, 0.0, 0)); // 0
        handler.itemDef.add(new itensDef("Botas de couro", "Aumenta um pouco a sua destreza", 1, 3, 25, 1)); // 1
        handler.itemDef.add(new itensDef("Escudo de Madeira", "Aumenta um pouco a sua defesa", 3, 0, 40, 2)); // 2
        handler.itemDef.add(new itensDef("Chapeu de palha", "Um chapeu feito de palha, \nnao faz nada de especial", 0, 0, 15, 1)); // 3
        handler.itemDef.add(new itensDef("Oculos escuros", "Te deixa maneiro, mas nao faz muita diferenca", 0, 1, 50, 1)); // 4
        handler.itemDef.add(new itensDef("Capa vermelha", "Uma capa vermelha, aumenta sua destreza", 0, 6, 70, 2)); // 5
        handler.itemDef.add(new itensDef("Escudo quebrado", "Um escudo usado varias vezes que quebrou, aumenta defesa", 1, 0, 15, 1)); // 6
        handler.itemDef.add(new itensDef("Colete a prova de balas", "Aumenta a sua defesa, mas diminui destreza", 15, -8, 150, 3)); // 7
        handler.itemDef.add(new itensDef("Capacete de madeira", "Um capacete que cabe na cabe de qualquer um", 2, 0, 25, 1)); // 8
        handler.itemDef.add(new itensDef("Capacete de aço", "Um capacete um pouco pesado, mas defende bem", 5, -2, 40, 2)); // 9
        handler.itemDef.add(new itensDef("Colete refletivo", "Um colete usado por policiais de transito", 1, 2, 42, 2)); // 10
        handler.itemDef.add(new itensDef("Escudo de mithril", "Escudo feito com um minerio rarissimo", 7, 0, 112, 3)); // 11
        handler.itemDef.add(new itensDef("Botas de velocidade", "Botas que faz voce correr extremamente rapido", 1, 10, 124, 3)); // 12
        

        /*  AS VARIAVEIS DE UM ITEM OFERNSIVO SAO
            nome
            descricao
            forca
            destreza
            valor em dinheiro
            raridade
        */
        //ofensivos
        handler.itemOfen.add(new itensOfen("nada", "nada", 0, 0, 0, 0)); // 0
        handler.itemOfen.add(new itensOfen("Anel de forca", "Aumenta um pouco a sua forca", 2, 0, 30, 1)); // 1
        handler.itemOfen.add(new itensOfen("Luvas de couro", "Aumenta a sua destreza", 1, 5, 23.5, 1)); // 2
        handler.itemOfen.add(new itensOfen("Colar de ouro", "Te faz se sentir um pouco mais forte", 4, 0, 75, 1)); // 3
        handler.itemOfen.add(new itensOfen("Bandana", "Aumenta forca e destreza", 2, 4, 40, 2)); // 4
        handler.itemOfen.add(new itensOfen("Anel de ouro", "Um anel bem elegante", 3, 0, 60, 2)); // 5
        handler.itemOfen.add(new itensOfen("Capuz escuro", "Um capuz que consegue esconder seu rosto", 1, 5, 72, 2)); // 6
        handler.itemOfen.add(new itensOfen("Anel de bronze", "Um anel feito de bronze, mas tambem parece ser antigo", 1, 0, 15, 1)); // 7
        handler.itemOfen.add(new itensOfen("Luvas cirurgicas", "Luvas de um cirurgico feito para ter precisao", 0, 3, 21, 1)); // 8
        handler.itemOfen.add(new itensOfen("Luvas de ferro", "Uma luva de ferro feita para ajudar a carregar armas", 5, 2, 80, 3)); // 9
        handler.itemOfen.add(new itensOfen("Talismã", "Um talisma que consegue oferecer poder", 4, 3, 100, 3)); // 10
        handler.itemOfen.add(new itensOfen("Anel de mithril", "Anel feito com um minerio rarissimo", 6, 4, 200, 3)); // 11
        handler.itemOfen.add(new itensOfen("Capa de espinhos", "Uma capa cheia de espinhos que pode ajudar em dar dano no inimigo", 5, -3, 110, 3)); // 12


        /*  AS VARIAVEIS DE UM ITEM MISC SAO
            nome
            descricao
            forca
            defesa
            destreza
            bonus de evasao
            valor em dinheiro
            raridade
        */
        //Misc
        handler.itemMisc.add(new itensMisc("nada", "nada", 0, 0, 0, 1, 0.0, 0)); // 0
        handler.itemMisc.add(new itensMisc("Peso de 5kg", "Aumenta sua forca, mas diminui destreza", 3, 0, -3, 0.7, 28, 1)); // 1
        handler.itemMisc.add(new itensMisc("Tijolo da supreme", "So serve como peso, mas vale bastante", 0, 0, -6, 0.8, 99.99, 1)); // 2
        handler.itemMisc.add(new itensMisc("Amuleto da sorte", "Aumenta sua chance de desviar", 0, 0, 0, 1.5, 24.0, 1)); // 3
        handler.itemMisc.add(new itensMisc("Carta colecionavel", "Uma carta colecionavel, aumenta sua forca", 3, 0, 0, 1, 25.0, 1)); // 4
        handler.itemMisc.add(new itensMisc("Chave", "Uma chave esquisita, nao se sabe onde pode ser usado", 0, 0, 0, 1, 25.0, 2)); // 5
        handler.itemMisc.add(new itensMisc("Colar com dente de tubarao", "Um colar com um dente de tubarao", 3, 0, 1, 1, 28.0, 2)); // 6
        handler.itemMisc.add(new itensMisc("Tatuagem falsa", "Uma tatuagem falsa que pode \nser colocado no braco, pode dar sensacao de forca", 5, 0, 0, 1, 37.0, 2)); // 7
        handler.itemMisc.add(new itensMisc("Monoculo", "Faz voce enchergar melhor", 0, -2, 5, 1.5, 45.0, 2)); // 8
        handler.itemMisc.add(new itensMisc("Livro antigo", "Te da conhecimento", 3, 3, 3, 1.2, 104.0, 3)); // 9
        handler.itemMisc.add(new itensMisc("Pé de coelho", "Dizem que quem segura um desse tem muita sorte", 0, 0, 0, 3.0, 89.0, 3)); // 10
        handler.itemMisc.add(new itensMisc("Peso de 40kg", "Voce precisa de forca para estar sempre carregando isso", 9, 0, -6, 1.0, 130.0, 3)); // 11
        handler.itemMisc.add(new itensMisc("Peso de 80kg", "Voce precisa de MUITA forca para estar sempre carregando isso", 15, 0, -10, 0.0, 140.0, 3)); // 12

    }

    static void iniConsu(){
        // SO COLOCAR CONSUMIVEL NO ARRAYLIST ALI TB PRA CRIAR UM NOVO, A RARIDADE DEFINE QUAIS FASES ELE PODE APARECER

        /*  AS VARIAVEIS SAO
            nome
            descricao
            valor
            id da acao
            valor em dinheiro
            raridade

            ACOES E ID

            0 = curar
            1 = aumentar forca
            2 = aumentar destreza
            3 = aumentar defesa
            4 = causar dano verdadeiro

            todos de curar/aumentar vai fazer isso = valor*statusmax(do o que for modificado)
        */
        handler.consu.add(new consumiveis("Pedra", "Uma pedra que voce pode jogar", 5, 4, 10, 1)); // 0 // causa 5 de dano garantido no oponente
        handler.consu.add(new consumiveis("Fraca Pocao de Vida", "Cura um pouco a sua vida", 0.2, 0, 15, 1)); // 1 // cura 20% da vida da pessoa
        handler.consu.add(new consumiveis("Fraca Pocao de Forca", "Aumenta sua forca um pouco", 0.2, 1, 15, 1)); // 2 // aumenta em 20% a forca da pessoa
        handler.consu.add(new consumiveis("Fraca Pocao de Destreza","Aumenta sua destreza um pouco",0.2, 2, 15, 1));// 3 // aumenta em 20% a destreza da pessoa
        handler.consu.add(new consumiveis("Media Pocao de Vida", "Cura um bocado a sua vida", 0.5, 0, 35, 1)); // 4 // cura 50% da vida da pessoa
        handler.consu.add(new consumiveis("Media Pocao de Forca", "Aumenta sua forca um bocado", 0.5, 1, 35, 1)); // 5 // aumenta em 50% a forca da pessoa
        handler.consu.add(new consumiveis("Media Pocao de Destreza","Aumenta sua destreza um bocado",0.5, 2, 35, 1));// 6 // aumenta em 50% a destreza da pessoa
        handler.consu.add(new consumiveis("Forte Pocao de Vida", "Cura a sua vida consideravelmente", 0.75, 0, 45, 2)); // 7 // cura 75% da vida da pessoa
        handler.consu.add(new consumiveis("Forte Pocao de Forca", "Aumenta sua forca consideravelmente", 0.75, 1, 45, 2)); // 8 // aumenta em 75% a forca da pessoa
        handler.consu.add(new consumiveis("Forte Pocao de Destreza","Aumenta sua destreza consideravelmente",0.75, 2, 45, 2));// 9 // aumenta em 75% a destreza da pessoa
        handler.consu.add(new consumiveis("Maxima Pocao de Vida", "Cura completamente a sua vida", 1, 0, 65, 3)); // 10 // cura 100% da vida da pessoa
        handler.consu.add(new consumiveis("Maxima Pocao de Forca", "Dobra a sua forca", 1, 1, 65, 3)); // 11 // aumenta em 100% a forca da pessoa
        handler.consu.add(new consumiveis("Maxima Pocao de Destreza","Dobra sua destreza", 1, 2, 65, 3));// 12 // aumenta em 100% a destreza da pessoa
    }

    static void iniFases(){
        fases.fase_atual = 1;
        // infelizmente vamo ter q cortar algumas fazer do jogo :(
        handler.fase.add(new fases("Masmorra","Um lugar com um passado sombrio, muitas pessoas foram presas aqui \npara passar o resto das suas vidas para pagar pelos seus crimes...", extras.rng_int(5, 6))); // ordem das fases e de cima para baixo
        handler.fase.add(new fases("Mansao Assombrada","Uma casa abandonada seculos atras, \ndizem que todos que entram nao saem mais...", extras.rng_int(7, 11)));
        //handler.fase.add(new fases("Deserto","Um lugar aberto, onde se enxerga apenas areia ate o fim do horizonte...", 5));
        handler.fase.add(new fases("Colorado U.S","Uma cidade cheio de arranha-ceus, \nrepleto de nanomachines e memes, parece estar tendo algum tipo de guerra...", extras.rng_int(8, 11)));
        //handler.fase.add(new fases("El dorado","Uma antiga cidade indigena, dizem que e \numa cidade feita de ouro, muitos aventureiros vem em busca de riquezas...", 10));
        handler.fase.add(new fases("Ceu","O reino que permanece acima das nuvens...", 0)); // ultima fase so tem o boss
    }

    public static void NovoJogo(){
        extras.console_clear();
        Prun++;
        janela.clearJmonsAscii(false);
        historia();
        NovoJogador();
        fases.comecarFases(); 
        //aqui vai ser pra dar a introducao para o jogo
    }

    public static int Prun = 0;
    static void historia(){
        if(Prun == 1){
            extras.print("");
            extras.println_bonito("....", 800, 500);
            extras.print("");
            extras.println_bonito("Voce acorda, e repara que voce esta preso em um lugar esquisito", 800, 500);
            extras.print("");
            extras.println_bonito("Voce tenta sair mas repara que esta dentro de uma cela trancada", 800, 500);
            extras.print("");
            extras.println_bonito("!", 300, 400);
            extras.print("");
            extras.println_bonito("A porta da cela abriu sozinha!", 500, 400);
            extras.print("");
            extras.println_bonito("O que esta acontecendo?", 1000, 500);
            extras.print("");
            extras.println_bonito("Voce sai da cela, e se prepara para enfrentar o que encontrar!", 1000, 500);
        }else if(Prun == 2){
            extras.print("");
            extras.println_bonito("AAAAAAH", 1000, 800);
            extras.print("");
            extras.println_bonito("...", 800, 500);
            extras.print("");
            extras.println_bonito("O que aconteceu?", 800, 500);
            extras.print("");
            extras.println_bonito("Voce morreu?", 800, 500);
            extras.print("");
            extras.println_bonito("Entao como que voce voltou para o inicio?", 800, 500);
            extras.print("");
            extras.println_bonito("Voce esta em uma cela, e a porta novamente abriu sozinha", 800, 500);
            extras.print("");
            extras.println_bonito("Parece que sua unica opcao e continuar novamente...", 800, 500);
        }else if(Prun == 3){
            extras.print("");
            extras.println_bonito("AAAAAAH", 500, 1200);
            extras.print("");
            extras.println_bonito("...", 800, 500);
            extras.print("");
            extras.println_bonito("Mais uma vez?", 800, 500);
            extras.print("");
            extras.println_bonito("...", 800, 500);
            extras.print("");
            extras.println_bonito("Esta tudo acontecendo novamente", 800, 500);
            extras.print("");
            extras.println_bonito("Voce esta em um loop infinito", 800, 500);
            extras.print("");
            extras.println_bonito("Voce mais uma vez, sem opcao, continua em frente", 800, 500);
        }else if(Prun == 4){
            extras.print("");
            extras.println_bonito("...", 1000, 800);
            extras.print("");
            extras.println_bonito(".......", 800, 500);
            extras.print("");
            extras.println_bonito("Nao desista!", 800, 500);
            extras.print("");
            extras.println_bonito("Liberdade aguarda no fim deste loop!", 800, 500);
        }else if(Prun == 5){
            extras.print("");
            extras.println_bonito("...", 1000, 800);
            extras.print("");
            extras.println_bonito("Voce comeca a ouvir vozes vindo das paredes de sua cela...", 800, 500);
            extras.print("");
            extras.println_bonito("Parece ser alguem pensando em voz alta", 800, 500);
            extras.print("");
            extras.println_bonito("Parece que ele se arrepende de ter vindo para ca?", 800, 500);
            extras.print("");
            extras.println_bonito("...", 800, 500);
            extras.print("");
            extras.println_bonito("Ele veio atras de um grande deus capaz de realizar qualquer desejo...", 800, 500);
            extras.print("");
            extras.println_bonito("Seria por isso que voce esta aqui tambem?", 800, 500);
            extras.print("");
            extras.println_bonito("De qualquer maneira, sem opcao, voce deve chegar no fim desta prisao!", 800, 500);
        }else{
            extras.print("");
            extras.println_bonito("...", 1000, 800);
            extras.print("");
            extras.println_bonito(".......", 800, 500);
            extras.print("");
            extras.println_bonito("Nao desista!", 800, 500);
            extras.print("");
            extras.println_bonito("Liberdade aguarda no fim deste loop!", 800, 500);
        }
    }

    static void NovoJogador(){
        inventario.resetInventario();
        extras.print("");
        extras.println_bonito("Digite o seu nome: ", 700, 100);
        handler.jogador.setNome(extras.inputS());
        setClasseJogador();
    }

    static void setClasseJogador(){
        int res2 = 0;
        while(res2 != 1){
            extras.print("");
            extras.println_bonito("Digite qual classe voce gostaria de jogar: ", 1000, 500);
            classes.printClasses();
            String res = extras.inputS();
            Boolean classe_ok = false;
            res2 = 1;
            for(int i = 0; i < handler.classe.size();i++){
                if(handler.classe.get(i).getNome().toLowerCase().equals(res.toLowerCase())){
                    handler.jogador.setVidamax(handler.classe.get(i).getVidamax());
                    handler.jogador.setVida(handler.classe.get(i).getVida());
                    handler.jogador.setForca(handler.classe.get(i).getForca());
                    handler.jogador.setDefesa(handler.classe.get(i).getDefesa());
                    handler.jogador.setDestreza(handler.classe.get(i).getDestreza());
                    handler.jogador.setArmaEquip(handler.classe.get(i).getArmaEquip());
                    handler.jogador.setArmorEquip(handler.classe.get(i).getArmorEquip());
                    handler.jogador.setClasse(handler.classe.get(i).getNome());
                    handler.jogador.setTipoArma(handler.classe.get(i).gettipoArma());
                    classe_ok = true;
                    break;
                }
            }
            handler.jogador.setNivel(1);
            if(classe_ok == false){
                extras.println_bonito("Por favor digite uma classe válida...", 1000, 500);
                res2 = 0;
            }
        }
        janela.setUpPlayerGUI();
    }

}
