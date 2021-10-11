package entidades;

import adicionais.extras;
import adicionais.handler;

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

    void falar() {}
    void dropar() {
        switch(extras.rng_int(0, 5)){
            case 0: // caso for dropar um item ofensivo
                break;
            case 1: // caso for dropar um item defensivo
                break;
            case 2: // caso for dropar um item misc
                break;
            case 3: // caso for dropar um item consumivel
                break;
            default: // caso for para dropar nenhum item
                break;

        }
    }

    public static void setFaseDrop(int fase){
        switch(fase){ // cada fase vai ter lista de drops diferentes 
            case 1:
                monstros_f.itensOfen_drop = new int[] {1}; // a lista de ids dos possiveis itens que um monstro fraco pode dropar na fase 1
                itensDef_drop = new int[] {1};
                itensMisc_drop = new int[] {1};
                consu_drop = new int[] {0,1,2,3};
                setArmaDrop(new int[]{1,2,4,5,7}, new int[]{3,10,11});
                armor_drop = new int[] {0,1,1,2,2,3,3};
                break;
            case 2:
                monstros_f.itensOfen_drop = new int[] {1}; // fase 2
                itensDef_drop = new int[] {1};
                itensMisc_drop = new int[] {1};
                consu_drop = new int[] {0,1,2,3,4,5,6};
                setArmaDrop(new int[]{4,5,7,8,9,12}, new int[]{3,9,9,10,11,11,13,13});
                armor_drop = new int[] {0,1,1,2,2,3,3};
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

