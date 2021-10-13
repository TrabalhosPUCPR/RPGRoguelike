package entidades;

public class NPC extends inimigos{

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


  public static void agir(int id){
    switch(id){ 
        case 0:
            break;
        case 1:
            break;
        case 2:
            break;
    }
  }

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
