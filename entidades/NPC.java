package entidades;

import adicionais.combate;
import adicionais.extras;
import adicionais.handler;
import itens.inventario;

public class NPC extends inimigos{

    static boolean Pvez = true;

    public NPC(String nome, String desc, int arma_equip, int vidamax, int forca, int defesa, int destreza, int exp){
        this.nome = nome;
        this.desc = desc;
        this.arma_equip = arma_equip;
        this.vidamax = vidamax;
        this.vida = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.exp = exp;
    }

    public static void selec_npc(){
        switch(extras.rng_int(0, 4)){
            case 0:
                vendedor();

                break;
            case 1:
                prisioneiro();
                extras.print("vc passou por prisioneiro");
                break;
            case 2:
                morto();
                extras.print("vc passou por uma pessoa quase morrendo");
                break;
            case 3:
                mendigo();
                extras.print("vc passou por mendigo");
                break;
            case 4:
                dog();
                
                break;
        }
    }

    static String[] vendedor_chegamais = {"Por aqui, estranho, pode vir", "Pode chegar mais estranho, sem vergonha"};
    static String[] vendedor_cumprimentos = {"Bem vindo!", "Tenho uma seleção de coisas boas para vender, estranho.", "O que você está comprando?", "Tenho algumas coisas raras para vender, estranho."};
    static String[] vendedor_agradecimentos = {"HEHEHEHA! Obrigado.", "Isso é tudo, estranho?"};
    static String[] vendedor_quervender = {"O que você está vendendo?", "Aaah! Eu vou comprar isso por um preço alto.", "O que você gostaria de vender?"};
    static String[] vendedor_dininsu = {"DINHEIRO INSUFICIENTE! Estranho."};
    static String[] vendedor_tchau = {"Volte à qualquer hora.", "Ate mais!"};
    static String[] vendedor_nomes = {"Drebin"};
    static String vendedor_nome = vendedor_nomes[extras.rng_int(0, vendedor_nomes.length)];

    static void vendedor(){
        extras.print("");
        extras.println_bonito("Voce chega na sala e percebe um homem com um casaco de couro grande, e um chapeu de cowboy, em volta dele tem alguns itens espalhados em volta...", 1000, 1000);
        if(Pvez){
            extras.print("");
            extras.println_bonito("Parece suspeito", 500, 800);
            extras.print("");
            extras.println_bonito("Ele te viu", 400, 500);
            extras.print("");
            extras.println_bonito("'"+vendedor_chegamais[extras.rng_int(0, vendedor_chegamais.length)]+ "'", 1000, 800);
            extras.print("");
            extras.println_bonito("Voce se aproxima do homem suspeito...", 800, 500);
            extras.print("");
            extras.println_bonito("'HIEHIEHIEIHEA! Voce parece ter um interesse pelos itens que eu tenho em exposicao!'", 1400, 500);
            extras.print("");
            extras.println_bonito("'HIEAEHIAEHAI!' ", 400, 1000);
            extras.print("");
            extras.println_bonito("'PERFEITO! Meu nome e " + vendedor_nome + ", e eu sou um mercador mundialmente conhecido que tras e pega bens de todos os tipos para todas as pessoas que passam por mim!'", 3000, 1000);
            extras.print("");
            extras.println_bonito("'Claro que se eu te contar onde eu pego os meus produtos, eu terei que te matar!'", 1400, 500);
            extras.print("");
            extras.println_bonito("'HIEAEHIAEHAI!' ", 400, 1000);
            extras.print("");
            extras.println_bonito("'Mas, se voce quiser, podera levar com voce o item que quiser!' ", 1400, 800);
            extras.print("");
            extras.println_bonito("'POR UM PRECO!' ", 400, 300);
            extras.print("");
            extras.println_bonito("'HIEAEHIAEHAI!' ", 400, 500);
            extras.print("");
            extras.println_bonito("'Entao fique a vontade para olhar em volta e comprar o que desejar!' ", 1200, 1000);
            extras.print("");
            extras.println_bonito("'ᴱ ˢᵉ ᵛᵒᶜᵉ ᵗᵉⁿᵗᵃʳ ᵐᵉ ʳᵒᵘᵇᵃʳ, ᵛᵒᶜᵉ ⁿᵃᵒ ˢᵃᶦʳᵃ ᵛᶦᵛᵒ' ", 1000, 500);
            extras.print("");
            extras.println_bonito("'ᴴᴵᴱᴬᴱᴴᴵᴬᴱᴴᴬᴵ'", 400, 1000);
            Pvez = false;
        }else{
            extras.print("");
            extras.println_bonito("'"+vendedor_chegamais[extras.rng_int(0, vendedor_chegamais.length)]+ "'", 500, 500);
        }
        extras.print("");
        extras.println_bonito("'"+vendedor_cumprimentos[extras.rng_int(0, vendedor_cumprimentos.length)]+ "'", 500, 500);
        boolean loja = true;
        boolean selec = true;
        while(loja){
            extras.print("");
            extras.println_bonito("Voce tem " + String.format("%.02f",inventario.dinheiro) + " de dinheiro", 500, 500);
            extras.print("");
            extras.println_bonito("Comprar ", 200, 20);
            extras.println_bonito("Vender ", 200, 20);
            extras.println_bonito("Sair ", 200, 20);
            switch(extras.inputS().toLowerCase()){
                case "comprar":
                    selec = true;
                    break;
                case "vender":
                    selec = true;
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
                    break;
                case "sair":
                    extras.print("");
                    extras.println_bonito("'"+vendedor_tchau[extras.rng_int(0, vendedor_tchau.length)]+ "'", 500, 500);
                    loja = false;
                    break;
                default:
                    extras.print("");
                    extras.println_bonito("Digite uma opcao valida...", 500, 500);
                    break;
            }
        }
    }

    static void prisioneiro(){}

    static void morto(){}

    static void mendigo(){}

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

    static void estrangeiro(){}

    void falar() {

        /* possiveis falas

        "Over here, stranger." - "Por aqui, estranho."

        "Welcome!" - "Bem vindo!"

        "What are you buying?" - "O que você está comprando?"

        "What are you selling?" - "O que você está vendendo?"

        "HEHEHEA! Thank you." - "HEHEHEHA! Obrigado."

        "Aaah! I'll buy it at a high price." - "Aaah! Eu vou comprar isso por um preço alto."

        "Got some rare things on sale, stranger." - "Tenho algumas coisas raras para vender, estranho."

        "Is that all, stranger?" - "Isso é tudo, estranho?"

        "NOT ENOUGH CASH! Stranger." - "DINHEIRO INSUFICIENTE! Estranho."

        "Got a selection of good things on sale, stranger." - "Tenho uma seleção de coisas boas para vender, estranho."

        "Come back any time." - "Volte à qualquer hora."

        "Ooooooooooaaaaaaaaaaaaaaaaarrrgggh..." - "(Quando você mata ele)"

            }
            
            else if npc = 1{"�kut.��nsy��sr���ahyer�", "vcs não falam o mesmo idioma"}

            else if npc = 2{"pessoa de poucas palavras"}

            else if npc = 3{"De um trocado pro seu mendigo ó dungeon abundante", "por que faço esses trocadilhos sem graça? boa pergunta"}
            
            else if npc = 4{"au","deveras poético"}
        */

    }
}
