package entidades;

import adicionais.extras;
import adicionais.handler;
import fases.fases;
import itens.inventario;

public class inimigos extends entidade{

    public static int indexmonstro;
    public static int tipomonstro;

    static int[] itensOfen_drop; // a lista de ids dos possiveis itens que um monstro fraco pode dropar
    static int[] itensDef_drop;
    static int[] itensMisc_drop;
    static int[] consu_drop;

    static int[] arma_drop;
    static int[] armor_drop;

    static int[] qual_itensOfen_drop; // a lista de ids dos possiveis itens de qualidade que pode dropar
    static int[] qual_itensDef_drop;
    static int[] qual_itensMisc_drop;

    static int[] qual_arma_drop;
    static int[] qual_armor_drop;

    public static inimigos getInimigo(int indexm, int tipo){
        indexmonstro = indexm;
        tipomonstro = tipo;
        switch(tipo){
            case 0:
                return (monstros_f)handler.monstros.get(indexm);
            case 1:
                return (monstros_b)handler.bossesrand.get(indexm);
            case 2:
                return (monstros_b)handler.bosses.get(indexm);
            case 3:
                return (NPC)handler.npcs.get(indexm);
        }
        return null;
    }

    public void Iturno(){
        double dano;
        extras.print("");
        extras.println_bonito("Cuidado! o " + this.nome + " vai atacar!", 700, 300);
        dano = atacar();
        dano = handler.jogador.levar_dano(dano, this.destreza, handler.arma.get(this.arma_equip).getPeso(), false); 
        if(dano > 0){
            player.dor();
        }
        extras.print("");
        extras.println_bonito("Voce levou " + String.format("%.00f", dano) + " de dano do " + this.nome + "!", 700, 500);
    }

    @Override
    public double levar_dano(double dano, int des_atacante, String pesoAtac, boolean ig_def){
        double danolevou = calc_dano(dano, des_atacante, ig_def);
        msg_dano(danolevou);
        this.vida -= danolevou;
        if(pesoAtac == "omega leve" || handler.armor.get(handler.jogador.getArmorEquip()).getPeso() == "omega leve"){
            extras.println("");
            extras.println_bonito("O ataque acertou duas vezes!", 400, 600);
            dano = (calc_dano(dano, des_atacante, ig_def))/2;
            msg_dano(dano);
            this.vida -= dano;
            return danolevou + dano;
        }
        return danolevou;
    }

    @Override
    public void morrer(){
        extras.print("");
        extras.println_bonito("O " + this.nome + " foi derrotado!", 300, 700);
        dropar();
    }

    public void dropar() {
        dropItemGenerico();
        inventario.ganharDinheiro(extras.rng_double(0, 30));
    }

    public static void dropItemGenerico(){
        int id;
        switch(extras.rng_int(0, 7)){
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
            case 6:
            case 7:
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
        }
    }

    public static void dropItemQualidade(){
        int id;
        switch(extras.rng_int(0, 5)){
            case 0: // caso for dropar um item ofensivo
                id = extras.rng_int(0, qual_itensOfen_drop.length);
                inventario.receberItem(handler.itemOfen.get(qual_itensOfen_drop[id]), id);
                break;
            case 1: // caso for dropar um item defensivo
                id = extras.rng_int(0, qual_itensDef_drop.length);
                inventario.receberItem(handler.itemDef.get(qual_itensDef_drop[id]), id);
                break;
            case 2: // caso for dropar um item misc
                id = extras.rng_int(0, qual_itensMisc_drop.length);
                inventario.receberItem(handler.itemMisc.get(qual_itensMisc_drop[id]), id);
                break;
            case 3: // caso for dropar uma arma
                id = extras.rng_int(0, qual_arma_drop.length);
                handler.jogador.receberArmaArmor(qual_arma_drop[id], 0);
                break;
            case 4: // caso for dropar uma armadura
                id = extras.rng_int(0, qual_armor_drop.length);
                handler.jogador.receberArmaArmor(qual_armor_drop[id], 1);
                break;
            default: // caso for para dropar nenhum item
                break;
        }
    }

    public static void setFaseDrop(int fase){
        monstros_b.setFaseDropQualidade(fase);
        switch(fase){ // cada fase vai ter lista de drops diferentes 
            case 1:
                itensOfen_drop = new int[] {1,2,3,4}; // a lista de ids dos possiveis itens que um monstro fraco pode dropar na fase 1
                itensDef_drop = new int[] {1,2,3,4,5,6};
                itensMisc_drop = new int[] {1,2,3,4};
                consu_drop = new int[] {0,1,2,3};
                setArmaDrop(new int[]{1,2,4,5,7}, new int[]{3,10,11,14});
                armor_drop = new int[] {0,1,2,3,4};
                break;
            case 2:
                itensOfen_drop = new int[] {1,2,3,4}; // fase 2
                itensDef_drop = new int[] {3,4,5,6};
                itensMisc_drop = new int[] {1,2,3,4};
                consu_drop = new int[] {0,1,2,3,4,5,6};
                setArmaDrop(new int[]{4,5,7,8,9,12,15}, new int[]{3,9,9,10,11,11,13,13,14,16});
                armor_drop = new int[] {0,1,2,3,4,5,5,5,6,6};
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    static void setFaseDropQualidade(int fase){
        switch(fase){ // cada fase vai ter lista de drops diferentes 
            case 1:
                qual_itensOfen_drop = new int[] {3,4}; // a lista de ids dos possiveis itens que um boss pode dropar na fase 1
                qual_itensDef_drop = new int[] {7,5,2};
                qual_itensMisc_drop = new int[] {4};
                setArmaDrop(new int[]{4,5,6,8,12}, new int[]{9,10,11,13,14,16});
                qual_armor_drop = new int[] {5,6,2};
                break;
            case 2:
                qual_itensOfen_drop = new int[] {3,4}; // a lista de ids dos possiveis itens que um boss pode dropar na fase 1
                qual_itensDef_drop = new int[] {7,5,2};
                qual_itensMisc_drop = new int[] {4};
                setArmaDrop(new int[]{8,12,15}, new int[]{13,14,16});
                qual_armor_drop = new int[] {5,6,2};
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
        arma_drop = new int[handler.arma.size()];
        for(int i = 0; i < handler.arma.size(); i++){
            arma_drop[i] = i;
        }

        /*
        switch(handler.jogador.gettipoArma()){
            case 0:
                arma_drop = listaC;
                break;
            case 1:
                arma_drop = listaL;
                break;
            case 2:
                arma_drop = new int[listaC.length+listaL.length];
                for(int i = 0; i < listaC.length; i++){
                    arma_drop[i] = listaC[i];
                }
                for(int i = 0; i < listaL.length; i++){
                    arma_drop[i + listaC.length] = listaL[i];
                }
                break;
        }
        */
    }

    static void setArmaDropQualidade(int[] listaC, int[] listaL){
        switch(handler.jogador.gettipoArma()){
            case 0:
                qual_arma_drop = listaC;
                break;
            case 1:
                qual_arma_drop = listaL;
                break;
            case 2:
                qual_arma_drop = new int[listaC.length+listaL.length];
                for(int i = 0; i < listaC.length; i++){
                    qual_arma_drop[i] = listaC[i];
                }
                for(int i = 0; i < listaL.length; i++){
                    qual_arma_drop[i + listaC.length] = listaL[i];
                }
                break;
        }
    }

    public static int selec_monstro(int monstipo){ // seleciona o monstro baseado na fase que deve aparecer
        int[] list_monst = {};
        switch(monstipo){
            case 0: // caso for monstros normais
                switch(fases.fase_atual){
                    case 0:
                    case 1: //aqui e o numero da fase que a pessoa esta
                        list_monst = new int[] {0,0,0,0,0,1,1,1,1,1,2,2,2,3,4,4,4,6,6,5,6,6,6,6,6}; // lista com os ids de cada monstro que e para aparecer na fase, pode colocar o mesmo id mais de uma vez pra aumenta a chance dele aparecer
                        break;
                    case 2:
                        list_monst = new int[] {0,0,0,0,0,1,1,1,1,1,2,2,2,3,4,4,4,6,6,5,6,6,6,6,6};
                        break;
                    case 3:
                        list_monst = new int[] {1,1,1};
                        break;
                    case 4:
                        list_monst = new int[] {1,1,1};
                        break;
                    case 5:
                        list_monst = new int[] {1,1,1};
                        break;
                }
                break;
            case 1: // caso for monstros incomuns
                list_monst = new int[] {0,1,1,1,2,2,2}; 
                break;

            case 2: // caso for monstros boss
                switch(fases.fase_atual){
                    case 0:
                    case 1: //aqui e o numero da fase que a pessoa esta
                        list_monst = new int[] {1,0}; 
                        break;
                    case 2:
                        list_monst = new int[] {2};
                        break;
                    case 3:
                        list_monst = new int[] {3,3,4};
                        break;
                    case 4:
                        list_monst = new int[] {5};
                        break;
                    case 5:
                        list_monst = new int[] {6};
                        break;
                    default:
                        if(handler.jogador.getNpcsMortos() > 10){
                            list_monst = new int[] {8};
                        }else{
                            list_monst = new int[] {7};
                        }
                        break;
                }
                break;
        }
        int index = extras.rng_int(0, list_monst.length);
        return list_monst[index];
    }

}
