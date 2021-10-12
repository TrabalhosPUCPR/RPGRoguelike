package entidades;

import adicionais.extras;
import adicionais.handler;
import itens.inventario;

public class monstros_f extends inimigos{

    public monstros_f(String nome, String desc, int arma_equip, int vidamax, int forca, int defesa, int destreza, int exp){
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

    static int[] itensOfen_drop; // a lista de ids dos possiveis itens que um monstro fraco pode dropar
    static int[] itensDef_drop;
    static int[] itensMisc_drop;
    static int[] consu_drop;

    static int[] arma_drop;
    static int[] armor_drop;

    @Override
    public void morrer(){
        extras.print("");
        extras.println_bonito("O " + this.nome + " foi derrotado!", 300, 700);
        dropar();
    }

    void falar() {}
    void dropar() {
        int id;
        switch(extras.rng_int(0, 6)){
            case 0: // caso for dropar um item ofensivo
                id = extras.rng_int(0, itensOfen_drop.length);
                inventario.receberItem(handler.itemOfen.get(itensOfen_drop[id]), itensOfen_drop[id]);
                break;
            case 1: // caso for dropar um item defensivo
                id = extras.rng_int(0, itensDef_drop.length);
                inventario.receberItem(handler.itemDef.get(itensDef_drop[id]), itensDef_drop[id]);
                break;
            case 2: // caso for dropar um item misc
                id = extras.rng_int(0, itensMisc_drop.length);
                inventario.receberItem(handler.itemMisc.get(itensMisc_drop[id]), itensMisc_drop[id]);
                break;
            case 3: // caso for dropar um item consumivel
                id = extras.rng_int(0, consu_drop.length);
                inventario.receberItem(consu_drop[id]);
                break;
            case 4: // caso for dropar uma arma
                id = extras.rng_int(0, arma_drop.length);
                handler.jogador.receberArmaArmor(arma_drop[id], 0);
                break;
            case 5: // caso for dropar uma armadura
                id = extras.rng_int(0, armor_drop.length);
                handler.jogador.receberArmaArmor(armor_drop[id], 1);
                break;
            default: // caso for para dropar nenhum item
                break;
        }
        inventario.ganharDinheiro(extras.rng_double(0, 30));
    }

    public static void setFaseDrop(int fase){
        switch(fase){ // cada fase vai ter lista de drops diferentes 
            case 1:
                monstros_f.itensOfen_drop = new int[] {1}; // a lista de ids dos possiveis itens que um monstro fraco pode dropar na fase 1
                itensDef_drop = new int[] {1};
                itensMisc_drop = new int[] {1};
                consu_drop = new int[] {0,1,2,3};
                setArmaDrop(new int[]{1,2,4,5,7}, new int[]{3,10,11,14});
                armor_drop = new int[] {0,1,1};
                break;
            case 2:
                monstros_f.itensOfen_drop = new int[] {1}; // fase 2
                itensDef_drop = new int[] {1};
                itensMisc_drop = new int[] {1};
                consu_drop = new int[] {0,1,2,3,4,5,6};
                setArmaDrop(new int[]{4,5,7,8,9,12,15}, new int[]{3,9,9,10,11,11,13,13,14});
                armor_drop = new int[] {0,1,1};
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    static void setArmaDrop(int[] listaC, int[] listaL){
        switch(handler.jogador.gettipoArma()){
            case 0:
                arma_drop = listaC;
                break;
            case 1:
                arma_drop = listaL;
                break;
            case 2:
                for(int i = 0; i < listaC.length; i++){
                    arma_drop[i] = listaC[i];
                }
                for(int i = 0; i < listaL.length; i++){
                    arma_drop[i + listaC.length] = listaL[i];
                }
                break;
        }
    }
}

