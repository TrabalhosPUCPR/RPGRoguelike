package entidades;

import adicionais.extras;
import adicionais.handler;
import fases.fases;
import random

public class NPC extends entidade{

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
      
    }
  }


Arraylist<NPC>npcs= new Arraylist<NPC>()

NPC 0 = new NPC("Vendedor ambulante","Pessoa misteriosa que comercializa itens", 0, 5, 1, 1, 1, 5);
NPC 1 = new NPC("Prisioneiro/Player","Outro jogador, mas aparenta estar mentalmente instavel, pode acabar lhe dando uma dica ou item","um equipamento semelhante, mas não igual, ao do jogador", "o resto não sei como arruma");
NPC 2 = new NPC("Cadáver","Em seu caminho se depara com um corpo largado no chão aparentemente não morreu a muito tempo","item ou nada", "o resto não sei como arruma");
NPC 3 = new NPC("Mendigo","Encostado na parede e coberto por um pano surrado um velho senhor, um pouco sus, lhe pede um pouco de dinheiro","adaga", "o resto não sei como arruma");
NPC 4 = new NPC("D-Dog","literalmente um doguinho","uma espada na boca estilo dark souls", "o resto não sei como arruma");

npc.add(0);
npc.add(1);
npc.add(2);
npc.add(3);
npc.add(4);

void falar() {

if npc=0{"possiveis falas"

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


    }
    void dropar() {}

    public void morrer(){
        extras.print("");
        extras.println_bonito("O " + this.nome + " foi derrotado!", 300, 400);
        extras.delay(300);
    }

    public void turno_npc(){
        double dano;
        extras.print("");
        extras.println_bonito("Cuidado! o " + this.nome + " vai atacar!", 700, 300);
        extras.print("");
        dano = atacar();
        dano = handler.jogador.levar_dano(dano, this.destreza); 
        if(dano < 0){
            extras.println_bonito("AIAIAI!", 100, 300);
        }
        extras.print("");
        extras.println_bonito("Voce levou " + dano + " de dano do " + this.nome + "!", 700, 500);
    }

    public static int selec_npc(int npctipo){ // seleciona o npc baseado na fase que deve aparecer
        int list_npc[] = {};
        switch(npctipo){
            case 0: // caso for npc normal
                switch(fases.fase_atual){
                    case 1: //aqui e o numero da fase que a pessoa esta
                        list_NPC = new int[] {0,0,0,1,2,3,3,4,4}; // lista com os ids de cada npc que e para aparecer na fase, pode colocar o mesmo id mais de uma vez pra aumenta a chance dele aparecer
                        break;
                    case 2:
                        list_NPC = new int[] {0,0,0,1,2,3,4,4};
                        break;
                    case 3:
                        list_NPC = new int[] {1,4};
                        break;
                    case 4:
                        list_NPC = new int[] {1,1,1};
                        break;
                    case 5:
                        list_NPC = new int[] {1,1,1};
                        break;
                }
            
                }
                break;
        }

        int index = extras.rng_int(0, list_NPC.length);
        return list_NPC[index];
    }

    public int getExp(){return this.exp;}
}
