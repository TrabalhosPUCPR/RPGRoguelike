package entidades;

import adicionais.extras;
import adicionais.handler;
import fases.fases;
import itens.armaduras;
import itens.armas;
import itens.consumiveis;
import itens.inventario;
import itens.itensDef;
import itens.itensMisc;
import itens.itensOfen;

public class inimigos extends entidade{

    public static int indexmonstro;
    public static int tipomonstro;

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
        try{
            switch(extras.rng_int(0, 6)){
                case 0: // caso for dropar um item ofensivo
                    id = itensOfen.dropItenOfen();
                    inventario.receberItem(handler.itemOfen.get(id), id);
                    break;
                case 1: // caso for dropar um item defensivo
                    id = itensDef.dropItenDef();
                    inventario.receberItem(handler.itemDef.get(id), id);
                    break;
                case 2: // caso for dropar um item misc
                    id = itensMisc.dropItenMisc();
                    inventario.receberItem(handler.itemMisc.get(id), id);
                    break;
                case 3: // caso for dropar uma arma
                    handler.jogador.receberArmaArmor(armas.dropArma(), 0);
                    break;
                case 4: // caso for dropar uma armadura
                    handler.jogador.receberArmaArmor(armaduras.dropArmor(), 1);
                    break;
                default:
                    id = consumiveis.dropConsu();
                    inventario.receberItem(id);
                    break;
            }
        }catch(Exception e){
            extras.println("");
            extras.println_bonito("Erro: "+e, 600, 500);
            extras.println_bonito("Talvez seja por que nao ha nenhum item para dropar", 600, 500);
        }
    }

    public static void dropItemQualidade(){
        int id;
        try{
            switch(extras.rng_int(0, 4)){
                case 0: // caso for dropar um item ofensivo
                    id = itensOfen.dropItenOfenRaro();
                    inventario.receberItem(handler.itemOfen.get(id), id);
                    break;
                case 1: // caso for dropar um item defensivo
                    id = itensDef.dropItenDefRaro();
                    inventario.receberItem(handler.itemDef.get(id), id);
                    break;
                case 2: // caso for dropar um item misc
                    id = itensMisc.dropItenMiscRaro();
                    inventario.receberItem(handler.itemMisc.get(id), id);
                    break;
                case 3: // caso for dropar uma arma
                    handler.jogador.receberArmaArmor(armas.dropArmaRaro(), 0);
                    break;
                case 4: // caso for dropar uma armadura
                    handler.jogador.receberArmaArmor(armaduras.dropArmorRaro(), 1);
                    break;
            }
        }catch(Exception e){
            extras.println("");
            extras.println_bonito("Erro: "+e, 600, 500);
            extras.println_bonito("Talvez seja por que nao ha nenhum item para dropar", 600, 500);
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
