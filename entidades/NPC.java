package entidades;

import adicionais.combate;
import adicionais.extras;
import adicionais.handler;
import adicionais.janela;
import ascii.ascii;
import fases.fases;
import itens.inventario;
import itens.itens;

public class NPC extends inimigos{

    public NPC(String nome, String desc, int arma_equip, int vidamax, int forca, int defesa, int destreza, int exp, String nomeascii){
        this.nome = nome;
        this.desc = desc;
        this.arma_equip = arma_equip;
        this.vidamax = vidamax;
        this.vida = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.exp = exp;
        this.nomeAscii = nomeascii;
    }

    public static void selec_npc(){
        switch(extras.rng_int(0, 5)){
            case 0:
                vendedor();
                break;
            case 1:
                if(!P_livre){
                    prisioneiro();
                }else{
                    selec_npc();
                }
                break;
            case 2:
                estrangeiro();
                break;
            case 3:
                mendigo();
                break;
            case 4:
                dog();
                break;
        }
    }

    static void mendigo(){
        extras.print("");
        extras.println_bonito("Voce chega na sala e percebe uma pessoa com uma roupa bem rasgada \nse aquecendo numa pequena fogueira", 1000, 1000);
        extras.print("");
        extras.println_bonito("Ele rapidamente se levante e corre em sua direcao!", 500, 500);
        extras.print("");
        ascii.printMonstroAsciipeloNome("mendigo", true);
        extras.println_bonito("'iAe JoVeN, tEn Um ToCaDiN pA nOs Da Ai IUmAo? TaMo Na DeCaDeCiA aKi NoS aIuIdA aI??'", 500, 500);
        if(inventario.dinheiro >= 1){
            double dinheiropradar = 1;
            if(inventario.dinheiro > 10){
                dinheiropradar = 10 + inventario.dinheiro*0.2;
                if(dinheiropradar > inventario.dinheiro){dinheiropradar = inventario.dinheiro;}
            }else{
                dinheiropradar = inventario.dinheiro;
            }
            extras.print("");
            extras.println_bonito("Ele parece estar meio agitado, \ntalvez ele ficaria agressivo caso voce nao dar dinheiro para ele", 500, 500);
            extras.print("");
            extras.println_bonito("Voce pode dar $" + String.format("%.02f", dinheiropradar) + " para deixar ele satisfeito", 500, 500);
            extras.print("");
            extras.println_bonito("Mentir e dizer que voce esta sem nada(nao) ou dar para ele(sim)?", 500, 500);
            if(extras.simNao()){
                inventario.gastarDinheiro(dinheiropradar);
                extras.print("");
                extras.println_bonito("hEhEhEh VaLeU aI, aTe MaIs!", 500, 500);
                extras.print("");
                extras.println_bonito("Ele pegou o dinheiro e correu para fora", 500, 500);
            }else{
                luta_mendigo();
            }
        }else{
            luta_mendigo();
        }
    }

    static void luta_mendigo(){
        extras.print("");
        extras.println_bonito("'qQe? Se NaO tEn NaDa? HaAhAhAhAhA'", 500, 500);
        extras.print("");
        extras.println_bonito("'nAo MiTa VoC zO nAo QeR dA pAr MiN!'", 500, 500);
        extras.print("");
        extras.println_bonito("'vO tIrA dE tU a FoCa!'", 500, 500);
        if(fases.fase_atual <=1){
            combate.lutaini(3, 2);
        }else{
            combate.lutaini(3, 4);
        }
    }

    private static String[] perguntas_aleatorio = {"�sdsgà�.��nsfddt�kut.��n��ahyer�\n\nafuica��.áuicaoxzzzaa��poiu.m?uqr�libuyt?", ".��nsfddt�\n\nkut.��n��ahyer!�sdsgà�.��nsfddt??", "dsgà�.��nsfddt�kut.�\n\n�n��ahyer!�sdsgà�.��ns??"
    , "�kut.��n��ahyer!�sdsgà�.\n\n��nsfddt�kut.��n��ahyer!�sd??"};
    private static boolean E_morto = false;

    static void estrangeiro(){
        extras.print("");
        extras.println_bonito("Voce chega na sala e percebe uma pessoa \nvestida de maneira muito estranha...", 1000, 1000);
        extras.print("");
        extras.println_bonito("Ele te viu, e esta se aproximando", 600, 500);
        ascii.printMonstroAsciipeloNome("vendedor", true);
        extras.print("");
        extras.println_bonito("�kut.��nsy��sr���ahyer�!", 600, 500);
        extras.print("");
        extras.println_bonito("�sdsgà�.��nsfddt�kut.��n��ahyer�...", 600, 500);
        extras.print("");
        extras.println_bonito("Essa nao, ele nao fala a mesma lingua que voce", 600, 500);
        int chances = 2;
        int loop = 3;
        while(loop > 0){
            E_morto = false;
            extras.print("");
            extras.println_bonito(perguntas_aleatorio[extras.rng_int(0, perguntas_aleatorio.length)], 600, 500);
            extras.print("");
            extras.println_bonito("Parece que ele perguntou alguma coisa!", 600, 500);
            boolean res_certa = extras.rng_bool();
            boolean res = extras.simNao();
            if(res == res_certa){
                extras.print("");
                extras.println_bonito("Ele parece fazer uma cara de satisfeito!", 600, 500);
            }else{
                extras.print("");
                extras.println_bonito("Ele nao parece estar feliz com essa resposta.", 600, 500);
                chances--;
            }
            if(chances <= 0){
                extras.print("");
                extras.println_bonito("�sdsgà�.��nsfddt�kut.��n��ahyer!�sdsgà�.\n��nsfddt�kut.��n��ahyer!�sdsgà�.��nsfddt�kut.�\n�n��ahyer!�sdsgà�.��nsfddt�kut.��n��ahyer!", 800, 300);
                extras.print("");
                extras.println_bonito("�kut.��nsy��sr���ahyer�!sds\ngà�.��nsfddt�kut.��n��ahyer!�sdsgà�.��nsfddt�kut�kut.��nsy�\n�sr���ahyer�!sfddt�kut.��n��ahyer!", 800, 300);
                extras.print("");
                extras.println_bonito("�sdsgà�.��nsfddt�kut.��\nn��ahyer!�sdsgà�.��nsfddt�\nkut.��n��ahyer!�sdsgà�.��nsfddt�kut.��n\n��ahyer!�sdsgà�.��nsfddt�kut.��n��ahyer!", 800, 300);
                extras.print("");
                extras.println_bonito("Ele parece estar extremamente bravo, ele vai atacar!", 600, 500);
                luta_estrangeiro();
                break;
            }
            loop--;
        }
        if(!E_morto){
            extras.println_bonito("�kut.��nsy��sr���ahyer�!", 600, 500);
            extras.print("");
            extras.println_bonito("�sdsgà�.��nsfddt�kut.��n��ahyer�...", 600, 500);
            extras.print("");
            extras.println_bonito("yer!�sdsgà�.��nsfddt�kut.��n!", 600, 500);
            extras.print("");
            extras.println_bonito("Ele parece estar satisfeito, ele te deixou um item e foi embora", 600, 500);
            handler.npcs.get(5).dropar();
        }
    }

    static void luta_estrangeiro(){
        combate.lutaini(3, 5);
        E_morto = true;
    }

    public static boolean P_livre = false;

    static void prisioneiro(){
        extras.print("");
        extras.println_bonito("Voce chega na sala e percebe uma pessoa, \nacorrentada sentada no cato...", 1000, 1000);
        extras.print("");
        extras.println_bonito("Ele se vira...", 500, 500);
        extras.print("");
        extras.println_bonito("Seu rosto parece um pouco familiar, mas é dificil de ver...", 500, 500);
        if(inventario.chaveNoInventario()){
            extras.print("");
            extras.println_bonito("Ele detectou algo em voce e levantou ansiosamente", 500, 500);
            extras.print("");
            extras.println_bonito("'Voce tem a chave para me libertar!'", 500, 500);
            extras.print("");
            extras.println_bonito("'Por favor! Me libere! Eu juro que irei te ajudar!'", 500, 500);
            extras.print("");
            extras.println_bonito("Voce realmente tem uma chave no seu inventario...", 500, 500);
            extras.print("");
            extras.println_bonito("Gostaria de libertar ele?", 500, 500);
            if(extras.simNao()){
                extras.print("");
                extras.println_bonito("Voce decidiu liberar ele...", 500, 500);
                extras.print("");
                extras.println_bonito("'OBRIGADO!'", 500, 500);
                extras.print("");
                extras.println_bonito("'OBRIGADO! OBRIGADO!'", 500, 500);
                extras.print("");
                extras.println_bonito("'Finalmente estou livre!'", 500, 500);
                extras.print("");
                extras.println_bonito("'Muito obrigado amigo, eu estou em divida eterna com voce!'", 500, 500);
                extras.print("");
                extras.println_bonito("'ᵉᵘ ᵃᶦⁿᵈᵃ ᵗᵉⁿʰᵒ ᵠᵘᵉ ᶠᵘᵍᶦʳ ᵈᵉˢᵗᵉ ˡᵘᵍᵃʳ'", 500, 500);
                extras.print("");
                extras.println_bonito("'Amigo, que tal eu me juntar a voce?\n Eu tenho uma boa capacidade de lutar contra monstros deste lugar,\ntalvez nos conseguimos encontrar a saida deste lugar no caminho,\nvoce nao se arrependera! '", 2000, 1000);
                if(extras.simNao()){
                    extras.print("");
                    extras.println_bonito("'Otima decisao! Agora vamos! Honra e riqueza nos espera no fim deste lugar!'", 800, 500);
                    player.P_aliado = true;
                }else{
                    extras.print("");
                    extras.println_bonito("'QUE? Voce nao quer minha ajuda?'", 500, 500);
                    extras.print("");
                    extras.println_bonito("'Voce e um tolo se voce pensa em continuar nesse lugar sozinho...'", 600, 500);
                    extras.print("");
                    extras.println_bonito("'Mas entao ta bom, nao venha correndo \natras de mim quando voce precisar de ajuda!'", 600, 500);
                }
            }else{
                extras.print("");
                extras.println_bonito("Voce decidiu que melhor nao. \nNao se sabe que tipo de pessoa ele é...", 500, 500);
                extras.print("");
                extras.println_bonito("Em silêncio voce sai da sala enquanto o \nprisoneiro apenas olha voce indo embora...", 500, 500);
            }
        }else{
            extras.print("");
            extras.println_bonito("Ele desvira com um rosto um depressivo", 500, 500);
            extras.print("");
            extras.println_bonito("Nao tem nada que voce possa fazer...", 500, 500);
            extras.print("");
            extras.println_bonito("Voce continua sua jornada", 500, 500);
        }
    }

    private static String[] vendedor_chegamais = {"Por aqui, estranho, pode vir", "Pode chegar mais estranho, sem vergonha"};
    private static String[] vendedor_cumprimentos = {"Bem vindo!", "Tenho uma seleção de coisas boas para vender, estranho.", "O que você está comprando?", "Tenho algumas coisas raras para vender, estranho."};
    private static String[] vendedor_agradecimentos = {"HEHEHEHA! Obrigado.", "Isso é tudo, estranho?"};
    private static String[] vendedor_quervender = {"O que você está vendendo?", "Aaah! Eu vou comprar isso por um preço alto.", "O que você gostaria de vender?"};
    private static String[] vendedor_dininsu = {"DINHEIRO INSUFICIENTE! Estranho."};
    private static String[] vendedor_tchau = {"Volte à qualquer hora.", "Ate mais!"};
    private static String[] vendedor_nomes = {"Drebin"};
    private static String vendedor_nome = vendedor_nomes[extras.rng_int(0, vendedor_nomes.length)];
    private static boolean vendedor_brabo = false;
    private static boolean vendedor_morto = false;
    static boolean V_Pvez = true;

    static void vendedor(){
        if(V_Pvez){
            extras.print("");
            extras.println_bonito("Voce chega na sala e percebe um homem com um casaco de couro grande, \ne um chapeu de cowboy, em volta dele tem alguns itens espalhados em volta...", 1000, 1000);
            vendedor_brabo = false;
            vendedor_morto = false;
            handler.npcs.get(0).setNome(vendedor_nome);
            extras.print("");
            extras.println_bonito("Parece suspeito", 500, 800);
            extras.print("");
            extras.println_bonito("Ele te viu", 400, 500);
            extras.print("");
            extras.println_bonito("'"+vendedor_chegamais[extras.rng_int(0, vendedor_chegamais.length)]+ "'", 1000, 800);
            extras.print("");
            extras.println_bonito("Voce se aproxima do homem suspeito...", 800, 500);
            extras.print("");
            ascii.printMonstroAsciipeloNome("vendedor", true);
            extras.println_bonito("'HIEHIEHIEIHEA! Voce parece ter um interesse pelos \nitens que eu tenho em exposicao!'", 1400, 500);
            extras.print("");
            extras.println_bonito("'HIEAEHIAEHAI!' ", 400, 1000);
            extras.print("");
            extras.println_bonito("'PERFEITO! Meu nome e " + vendedor_nome + ", e eu sou um mercador mundialmente \nconhecido que tras e pega bens de todos os tipos para \ntodas as pessoas que passam por mim!'", 3000, 1000);
            extras.print("");
            extras.println_bonito("'Mas, se voce quiser, podera levar com voce o item que quiser!' ", 1400, 800);
            extras.print("");
            extras.println_bonito("'POR UM PRECO!' ", 400, 300);
            extras.print("");
            extras.println_bonito("'HIEAEHIAEHAI!' ", 400, 500);
            extras.print("");
            extras.println_bonito("'Entao fique a vontade para olhar em volta e comprar o que desejar!' ", 1200, 1000);
            V_Pvez = false;
        }else if(vendedor_brabo == false || vendedor_morto == false){
            extras.print("");
            extras.println_bonito("Voce chega na sala, voce encontra o mercador de itens de antes", 500, 500);
            extras.print("");
            extras.println_bonito("'"+vendedor_chegamais[extras.rng_int(0, vendedor_chegamais.length)]+ "'", 500, 500);
        }
        if(vendedor_morto){
            extras.print("");
            extras.println_bonito("Voce chega na sala, voce nao encontra nada, \napenas uma barraca com alguns baus vazio...", 500, 500);
            extras.print("");
            extras.println_bonito("Talvez era para alguem estar aqui? \nDe qualquer maneira, voce continua sua jornada...", 500, 1500);
        }else if(vendedor_brabo){
            extras.print("");
            extras.println_bonito("Voce chega na sala, voce encontra o mercador de itens de antes", 500, 500);
            extras.print("");
            extras.println_bonito("Essa nao...", 600, 600);
            extras.print("");
            extras.println_bonito("Ele ainda deve lembrar do o que aconteceu...", 600, 600);
            extras.print("");
            extras.println_bonito("'HIEAEHIAEHAI!'\n'HIEAEHIAEHAI!'\n'HIEAEHIAEHAI!'", 500, 500);
            extras.print("");
            extras.println_bonito("'Voce nao devia ter feito aquilo!'", 500, 500);
            extras.print("");
            lutavendedor();
        }else{
            extras.print("");
            extras.println_bonito("'"+vendedor_cumprimentos[extras.rng_int(0, vendedor_cumprimentos.length)]+ "'", 500, 500);
            boolean loja = true;
            int[] itensId = new int[4];
            int[] itensTipo = new int[4];
            for(int i = 0; i < itensId.length; i++){
                itensTipo[i] = extras.rng_int(0, 4);
                itensId[i] = itens.dropItem(itensTipo[i]);
            }
            while(loja){
                extras.print("");
                extras.println_bonito("Voce tem " + String.format("%.02f",inventario.dinheiro) + " de dinheiro", 500, 500);
                extras.print("");
                extras.println_bonito("Comprar ", 200, 20);
                extras.println_bonito("Vender ", 200, 20);
                extras.println_bonito("Sair ", 200, 20);
                switch(extras.inputS().toLowerCase()){
                    case "comprar":
                        comprasItem(itensId, itensTipo);
                        break;
                    case "vender":
                        vendasItem();
                        break;
                    case "sair":
                        extras.print("");
                        extras.println_bonito("'"+vendedor_tchau[extras.rng_int(0, vendedor_tchau.length)]+ "'", 500, 500);
                        loja = false;
                        janela.clearJmonsAscii(true);
                        break;
                    default:
                        extras.print("");
                        extras.println_bonito("Digite uma opcao valida...", 500, 500);
                        break;
                }
            }
        }
    }

    static void lutavendedor(){
        extras.println_bonito("'HIEAEHIAEHAI! Seguranca! Acaba com ele!'", 500, 500);
        extras.print("");
        extras.println_bonito("'Meu amigo aqui ira te dar uma licao...'", 500, 500);
        extras.print("");
        extras.println_bonito("'HIEAEHIAEHAI! Agora voce tera que pagar!'", 500, 500);
        combate.lutaini(1, 6);
        ascii.printMonstroAsciipeloNome("vendedor", true);
        extras.print("");
        extras.println_bonito("'QUE!?!?!?!?'", 500, 500);
        extras.print("");
        extras.println_bonito("'ᵃʰ ⁿᵃᵒ'", 500, 500);
        extras.print("");
        extras.println_bonito("'Agora chega! Eu mesmo o farei pagar!'", 500, 500);
        combate.lutaini(2, 0);
        vendedor_morto = true;
    }

    static void comprasItem(int[] itensId, int[] itensTipo){
        boolean selec = true;
        while(selec){
            extras.print("");
            extras.print("________________________________________________________________________________________________________________________________________________________________________");
            extras.print("|      |            Nome            |                                                Descricao                                                |     Tipo     |    $    |");
            extras.print("|______|____________________________|_________________________________________________________________________________________________________|______________|_________|");
            for(int i = 0; i < itensId.length;i++){
                janela.printJanela("   ");
                extras.println(
                 "|" + extras.verTamMax_table(i+1, 6) 
                +"|" + extras.verTamMax_table(itens.getItem(itensId[i], itensTipo[i]).getNome(), 28)
                +"|" + extras.verTamMax_table(itens.getItem(itensId[i], itensTipo[i]).getDesc(), 105)
                +"|" + extras.verTamMax_table(itens.getItemTipoString(itensTipo[i]), 14)
                +"|" + extras.verTamMax_table(String.format("%.02f",itens.getItem(itensId[i], itensTipo[i]).getDinheiro()*1.2), 9)
                +"|");
                extras.print("|______|____________________________|_________________________________________________________________________________________________________|______________|_________|");
            }
            extras.print("");
            extras.println_bonito("Digite o numero do item que deseja comprar ou apenas aperte enter para voltar", 500, 500);
            try{
                String res = extras.inputS();
                if(res.isEmpty()){
                    selec = false;
                }else if(res.startsWith("roubar")){
                    int id = Character.getNumericValue(res.charAt(7));
                    id--;
                    extras.print("");
                    extras.println_bonito("Voce tem certeza que gostaria de roubar " + itens.getItem(itensId[id], itensTipo[id]).getNome()+ "?" , 600, 600);
                    if(extras.simNao()){
                        inventario.receberItem(itensTipo[id], itensId[id]);
                        if(handler.jogador.dodge(inimigos.getInimigo(0, 3).getDestreza())){
                            extras.print("");
                            extras.println_bonito("Voce conseguiu roubar o " + itens.getItem(itensId[id], itensTipo[id]).getNome()+ " e conseguiu escapar do vendedor!" , 600, 600);
                            extras.print("");
                            extras.println_bonito("Ele nao deve estar feliz, tomara que nao passe por ele de novo..." , 600, 600);
                        }else{
                            extras.print("");
                            extras.println_bonito("Voce conseguiu roubar o " + itens.getItem(itensId[id], itensTipo[id]).getNome()+ "!" , 600, 600);
                            extras.print("");
                            extras.println_bonito("Essa não, o vendedor trancou a saida..." , 600, 600);
                            extras.print("");
                            extras.println_bonito("'HIEAEHIAEHAI!' ", 400, 1000);
                            extras.print("");
                            extras.println_bonito("'O QUE VOCE ACHA QUE ESTA FAZENDO !?!?!?!?!?' ", 400, 1000);
                            extras.print("");
                            extras.println_bonito("'Nao pense que voce vai sair dessa vivo!' ", 400, 1000);
                            extras.print("");
                            extras.println_bonito("'Se prepare para morrer!' ", 400, 1000);
                        }
                    }
                }else{
                    int id = (Integer.parseInt(res)-1);
                    extras.print("");
                    extras.println_bonito("Voce tem certeza que gostaria de comprar " + itens.getItem(itensId[id], itensTipo[id]).getNome()+ "?" , 600, 600);
                    if(extras.simNao()){
                        if(inventario.dinheiro > itens.getItem(itensId[id], itensTipo[id]).getDinheiro()*1.2){
                            inventario.gastarDinheiro(itens.getItem(itensId[id], itensTipo[id]).getDinheiro()*1.2);
                            inventario.receberItem(itensTipo[id], itensId[id]);
                            itensId = extras.removeIndex(itensId, id);
                            itensTipo = extras.removeIndex(itensTipo, id);
                        }else{
                            extras.print("");
                            extras.println_bonito(vendedor_dininsu[extras.rng_int(0, vendedor_dininsu.length)], 500, 1500);
                        }
                    }
                }
            }catch(Exception e){
                extras.print("");
                extras.println_bonito("Numero invalido, digite novamente", 500, 800);
            }
        }
    }

    static void vendasItem(){
        boolean selec = true;
        while(selec){
            extras.print("");
            extras.println_bonito("'"+vendedor_quervender[extras.rng_int(0, vendedor_quervender.length)]+ "'", 500, 500);
            extras.print("");
            extras.println_bonito("Arma", 200, 20);
            extras.println_bonito("Armadura", 200, 20);
            extras.println_bonito("Acessorio", 200, 20);
            extras.println_bonito("Voltar", 200, 20);
            switch(extras.inputS().toLowerCase()){
                case "arma":
                    if(handler.jogador.venderArma()){
                        extras.print("");
                        extras.println_bonito("'"+vendedor_agradecimentos[extras.rng_int(0, vendedor_agradecimentos.length)]+ "'", 500, 500);
                        selec = false;
                    }
                break;
                case "armadura":
                    if(handler.jogador.venderArmadura()){
                        extras.print("");
                        extras.println_bonito("'"+vendedor_agradecimentos[extras.rng_int(0, vendedor_agradecimentos.length)]+ "'", 500, 500);
                        selec = false;
                    }
                break;
                case "acessorio":
                    if(inventario.venderAce()){
                        extras.print("");
                        extras.println_bonito("'"+vendedor_agradecimentos[extras.rng_int(0, vendedor_agradecimentos.length)]+ "'", 500, 500);
                        selec = false;
                    }
                break;
                case "voltar":
                    selec = false;
                break;
                default:
                    extras.print("");
                    extras.println_bonito("Digite uma opcao valida...", 500, 500);
                break;
            }
        }
    }

    static void dog(){
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
                inimigos.dropItemGenerico();
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
}