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
    public static ArrayList<ascii> ascii = new ArrayList<ascii>();

    public static void carregarJogo() throws FileNotFoundException{
        extras.print("[Jogo]: Carregando...");
        iniLerTxtAscii();
        iniFases();
        iniAndares();
        iniClasses();
        iniNpcs();
        iniMonstros();
        iniItens();
        extras.print("[Jogo]: Carregado");
        extras.println(" ");
        extras.print("[Jogo]: Precione enter para comecar o jogo...");
        extras.inputS();
    }

    public static void iniLerTxtAscii() throws FileNotFoundException{
        // pra criar um novo ascii art e so colocar dentro de um arquivo de texto dentro do ascii/arts/ e fazer igual como ta ai, so coloca o nome em minusculo e facil
        extras.print("[ASCII]: Criando artes ASCII...");
        handler.ascii.add(new ascii("logo", new File("ascii/arts/logo.txt")));
        handler.ascii.add(new ascii("logoepico", new File("ascii/arts/logoepico.txt")));
        handler.ascii.add(new ascii("slime", new File("ascii/arts/monstros/slime.txt")));
        handler.ascii.add(new ascii("vendedor", new File("ascii/arts/monstros/vendedor.txt")));
        handler.ascii.add(new ascii("shrek", new File("ascii/arts/monstros/shrek.txt")));
        handler.ascii.add(new ascii("sapo", new File("ascii/arts/monstros/sapo.txt")));
        handler.ascii.add(new ascii("mendigo", new File("ascii/arts/monstros/mendigo.txt")));
        handler.ascii.add(new ascii("generico", new File("ascii/arts/monstros/generico.txt")));
        extras.print("[ASCII]: Artes ASCII carregadas");
    }

    static void iniAndares(){
        // essa e na minha opiniao a forma mais facil de escolher uma funcao de um andar aleatoriamente 
        handler.andar.add(andares::NPC); // 0, numero de cada sala para fazer com que a chance de cada sala aparecer seja diferente
        handler.andar.add(andares::monstro); // 1
        // acho q vai ser so esses dois msm, pensei q seria mais
    }

    static void iniNpcs(){
        extras.print("[NPC]: Criando NPCs...");
        handler.npcs.add(new NPC("Vendedor ambulante","Pessoa misteriosa que comercializa itens", 0, 5, 1, 1, 1, 5)); // 0
        handler.npcs.add(new NPC(handler.jogador.getNome(),"Outro jogador, mas aparenta estar mentalmente instavel, pode acabar lhe dando uma dica ou item", handler.jogador.getArmaEquip(), handler.jogador.getVidamax(), handler.jogador.getForca(), (int)handler.jogador.getDefesa(), handler.jogador.getDestreza(), 5)); // 1
        handler.npcs.add(new NPC("Mendigo","Encostado na parede e coberto por um pano surrado um velho senhor, um pouco sus, lhe pede um pouco de dinheiro", 1, 30, 8, 6, 5, 16)); // 2
        handler.npcs.add(new NPC("D-Dog","literalmente um doguinho, com uma espada...", 8, 20, 10, 4, 7, -5)); // 3
        extras.print("[NPC]: NPCs criados com sucesso");
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
        iniMonstrosF();
        iniMonstrosB();
        iniMonstrosBR();
    }

    public static void iniMonstrosB() {
        // fase 1
        handler.bosses.add(new monstros_b("King Slime", "Rei dos Slimes! Dizem que isto e o resultado da fusao de 8 slimes!", 0, 50, 20, 10, 4, 60, new int[]{1}, "slime")); // 0
        handler.bosses.add(new monstros_b("Ogro", "Voce invadiu o pantano dele!", 8, 40, 15, 10, 2, 76, new int[]{1}, "shrek")); // 1

        // fase 2
        handler.bosses.add(new monstros_b("Shadow " + handler.jogador.getNome(), "Um lado seu obscuro...", handler.jogador.getArmaEquip(), handler.jogador.getVidamax(), handler.jogador.getForca(), (int)handler.jogador.getDefesa(), handler.jogador.getDestreza(), 106, new int[]{2}, "generico")); // 2

        //fase 3
        handler.bosses.add(new monstros_b("Aguia do deserto", "Quando ele ataca, se parece com uma bala saindo de uma arma!", 15, 90, 32, 15, 33, 159, new int[]{3}, "generico")); // 3
        handler.bosses.add(new monstros_b("Farao", "Rei-deus do deserto! Usa magias para atacar!", 15, 100, 5, 23, 20, 156, new int[]{3}, "generico")); // 4

        //fase 4
        handler.bosses.add(new monstros_b("Senador Armstrong", "Extremamente patriota, fez infusao com nanomachines para fortalecer seus musculos a ponto de ser superhumano", 0, 200, 70, 30, 35, 156, new int[]{4}, "generico")); // 5

        //fase 5
        handler.bosses.add(new monstros_b("Quetzalcoatl", "A Serpente com plumas! Deusa de El Dorado!", 0, 500, 82, 53, 60, 340, new int[]{5}, "generico")); // 6

        //ultima fase: ceu
        handler.bosses.add(new monstros_b("O Criador", "O criador do universo, capaz de realizar o desejo de qualquer que se mostrar mais poderoso que ele!", 0, 200, 70, 30, 35, 1000, new int[]{6}, "generico")); // 7
        //ultima fase: inferno
        handler.bosses.add(new monstros_b("O Destruidor", "O mais poderoso do universo! Destruidor de mundos, banido do ceu e o mais malvado de todos, nunca foi visto antes por olhos vivos...", 0, 200, 70, 30, 35, 1000, new int[]{6}, "generico")); // 8
    }

    public static void iniMonstrosBR(){
        handler.bossesrand.add(new monstros_b("Ceifador", "Ele esta atras de voce, a sua hora chegou...", 17, 600, 15*fases.fase_atual, 10*fases.fase_atual, 17*fases.fase_atual, 240, new int[]{1, 2, 3, 4, 5}, "generico")); // 0
        handler.bossesrand.add(new monstros_b("Slime de ferro", "O rarissimo slime de ferro! Dizem que se voce derrotar ele, voce podera ficar rico!", 0, 5, 3, 99999, 40, 1000, new int[]{1, 2, 3, 4, 5}, "generico")); // 1
        handler.bossesrand.add(new monstros_b("Nouveau Riche", "Um homem tao rico que se veste com um terno de ouro", 0, 50, 30, 16, 15, 30, new int[]{1, 2, 3, 4, 5}, "generico")); // 2
        handler.bossesrand.add(new monstros_b("Lula", "Lula gigante com 9 tentáculos, o décimo parece ter sido arrancado", 0, 300, 30, 13, 24, 645, new int[]{1, 2, 3, 4, 5}, "generico")); // 3
        handler.bossesrand.add(new monstros_b("Anubis", "Um deus do egito, dizem que era responsavel por guiar as almas ate o submundo", 22, 200, 25, 19, 17, 400, new int[]{3}, "generico")); // 4
        handler.bossesrand.add(new monstros_b("Homem de preto", "Um deus do egito, dizem que era responsavel por guiar as almas ate o submundo", 21, 150, 20, 18, 20, 410, new int[]{4}, "generico")); // 5
    }

    public static void iniMonstrosF(){
        handler.monstros.add(new monstros_f("Slime azul", "Parece uma gelatina.", 0, 10, 6, 2, 3, 6, new int[]{1}, "slime")); // 0
        handler.monstros.add(new monstros_f("Goblin guerreiro", "Goblin equipado com uma espada e escudo!", 2, 16, 7, 4, 5, 10, new int[]{1}, "generico")); // 1
        handler.monstros.add(new monstros_f("Goblin arqueiro", "Goblin equipado com arco e flecha", 3, 16, 6, 3, 8, 10, new int[]{1}, "generico")); // 2
        handler.monstros.add(new monstros_f("Mendigo", "Ele estava dormindo e voce o acordou.", 1, 30, 8, 6, 5, 16, new int[]{1}, "mendigo")); // 3
        handler.monstros.add(new monstros_f("Rato", "Ele parece estar atras de algo, e voce esta no caminho.", 0, 13, 5, 4, 15, 8, new int[]{1}, "generico")); // 4
        handler.monstros.add(new monstros_f("Mimic", "Um bau com itens?", 0, 20, 15, 10, 15, 14, new int[]{1, 2}, "generico")); // 5
        handler.monstros.add(new monstros_f("Sapo", "Um sapo agressivo", 0, 10, 7, 2, 4, 8, new int[]{1}, "sapo")); // 6
        handler.monstros.add(new monstros_f("Slime vermelho", "Parece uma gelatina.", 0, 40, 15, 6, 5, 18, new int[]{2}, "generico")); // 7
        handler.monstros.add(new monstros_f("Esqueleto guerreiro", "Esqueleto equipado com uma espada e escudo!", 8, 55, 16, 11, 4, 20, new int[]{2}, "generico")); // 8
        handler.monstros.add(new monstros_f("Esqueleto arqueiro", "Esqueleto equipado com arco e flecha", 13, 36, 11, 6, 13, 20, new int[]{2}, "generico")); // 9
        handler.monstros.add(new monstros_f("Mendigo bebado", "Ele estava bebendo e voce o interrompeu", 6, 73, 20, 16, 10, 32, new int[]{2}, "mendigo")); // 10
        handler.monstros.add(new monstros_f("Fantasma", "BOO! Ele veio para te assustar", 0, 40, 25, 10, 15, 22, new int[]{2}, "generico")); // 11
        handler.monstros.add(new monstros_f("Zumbi", "Infectado por um virus rarissimo, ele vai atras de cerebro humano", 0, 80, 25, 5, 15, 25, new int[]{2}, "generico")); // 12
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
        handler.classe.add(new classes("Guerreiro","Forte e defensivo, pode causar alto dano em curto alcance mas possui pessima destreza", 1, 1, 40, 6, 3, 7, 0)); // 1
        handler.classe.add(new classes("Paladino","Balanceado de todas as formas, usa arma de curto alcance e experiente em qualquer situacao", 2, 1, 40, 8, 5, 3, 0)); // 2
        handler.classe.add(new classes("Despojado","Roubado de todos os seus pertences, comeca fraco mas ganha pontos em status extremamente rapido e pode usar qualquer arma", 0, 2, 40, 5, 3, 5, 2)); // 3
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
        handler.arma.add(new armas("Pistola", 20, "longo", "leve", 150, 4)); // 21
        handler.arma.add(new armas("Martelo enorme", 25, "curto", "super pesado", 180, 4)); // 21
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
        handler.armor.add(new armaduras("nada", 0, 1, " super leve", 0, 0)); // 0
        handler.armor.add(new armaduras("Armadura de Couro", 4, 1.1, "super leve", 30, 1)); // 1
        handler.armor.add(new armaduras("Armadura de Ferro", 6, 0.7, "pesado", 36, 1)); // 2
        handler.armor.add(new armaduras("Armadura de Pano", 0, 1.5, "omega leve", 63, 3)); // 3
        handler.armor.add(new armaduras("Armadura de Pedra", 5, 0.5, "super pesado", 32, 1)); // 4
        handler.armor.add(new armaduras("Armadura de Madeira", 2, 1.0, "leve", 24, 1)); // 5
        handler.armor.add(new armaduras("Armadura do Cavaleiro", 8, 1.0, "leve", 47, 2)); // 6
        handler.armor.add(new armaduras("Armadura do Juggernaut", 15, 0, "super pesado", 80, 3)); // 7

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
        handler.itemDef.add(new itensDef("Chapeu de palha", "Um chapeu feito de palha, nao faz nada de especial", 0, 0, 15, 1)); // 3
        handler.itemDef.add(new itensDef("Oculos escuros", "Te deixa maneiro, mas nao faz muita diferenca", 0, 1, 50, 1)); // 4
        handler.itemDef.add(new itensDef("Capa vermelha", "Uma capa vermelha, aumenta sua destreza", 0, 6, 70, 2)); // 5
        handler.itemDef.add(new itensDef("Escudo quebrado", "Um escudo usado varias vezes que quebrou, aumenta defesa", 1, 0, 15, 1)); // 6
        handler.itemDef.add(new itensDef("Colete a prova de balas", "Aumenta a sua defesa, mas diminui destreza", 15, -8, 150, 3)); // 7

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
        handler.itemMisc.add(new itensMisc("Amuleto da sorte", "Aumenta sua chance de desviar", 0, 0, 0, 1.5, 44.0, 2)); // 3
        handler.itemMisc.add(new itensMisc("Carta colecionavel", "Uma carta colecionavel, aumenta sua forca", 3, 0, 0, 1, 25.0, 2)); // 4
        handler.itemMisc.add(new itensMisc("Chave", "Uma chave esquisita, nao se sabe onde pode ser usado", 0, 0, 0, 1, 25.0, 2)); // 5
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
        handler.fase.add(new fases("Masmorra","Um lugar com um passado sombrio, muitas pessoas foram presas aqui para passar o resto das suas vidas para pagar pelos seus crimes...", 5)); // ordem das fases e de cima para baixo
        handler.fase.add(new fases("Mansao Assombrada","Uma casa abandonada seculos atras, dizem que todos que entram nao saem mais...", 7));
        handler.fase.add(new fases("Deserto","Um lugar aberto, onde se enxerga apenas areia ate o fim do horizonte...", 5));
        handler.fase.add(new fases("Colorado U.S","Uma cidade cheio de arranha-ceus, repleto de nanomachines e memes, parece estar tendo algum tipo de guerra...", 8));
        handler.fase.add(new fases("El dorado","Uma antiga cidade indigena, dizem que e uma cidade feita de ouro, muitos aventureiros vem em busca de riquezas...", 10));
        handler.fase.add(new fases("Ceu","O lugar onde todos que nao cometeram pecados vem quando morrem, tambem onde permanece o criador...", 0)); // ultima fase so tem o boss
        handler.fase.add(new fases("Inferno","O lugar onde todos que cometeram muitos pecados vao quando morrem, tambem permanece a pessoa mais malvada e poderosa do planeta...", 0)); // ultima fase tb, aparece esse se o jogador matou mt npc
    }

    public static void NovoJogo(){
        extras.console_clear();
        extras.println_bonito("Bem vindo ao mundo de foda nao sei o nome! ", 1000, 500);
        NovoJogador();
        janela.clearJmonsAscii(false);
        fases.comecarFases(); 
        //aqui vai ser pra dar a introducao para o jogo
    }

    static void NovoJogador(){
        inventario.resetInventario();
        extras.println_bonito("Digite o seu nome: ", 700, 100);
        handler.jogador.setNome(extras.inputS());
        setClasseJogador();
    }

    static void setClasseJogador(){
        int res2 = 0;
        while(res2 != 1){
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
