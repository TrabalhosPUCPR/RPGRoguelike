package entidades;

import java.util.ArrayList;

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

    int[] Nfases;
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

    public static ArrayList<inimigos> getInimigoLista(int tipo){
        ArrayList<inimigos> lista = new ArrayList<inimigos>();
        switch(tipo){
            case 0:
                lista = new ArrayList<inimigos>(handler.monstros);
            break;
            case 1:
                lista = new ArrayList<inimigos>(handler.bossesrand);
            break;
            case 2:
                lista = new ArrayList<inimigos>(handler.bosses);
            break;
        }
        return lista;
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
                for(int i = 0; i < handler.monstros.size();i++){
                    int[] Nfases = handler.monstros.get(i).getNfases();
                    for(int z = 0; z < Nfases.length;z++){
                        if(Nfases[z] == fases.fase_atual){
                            list_monst = extras.arrayintAdd(list_monst, i);
                            break;
                        }
                    }
                }
                break;
            case 1: // caso for monstros incomuns
                for(int i = 0; i < handler.bossesrand.size();i++){
                    int[] Nfases = handler.bossesrand.get(i).getNfases();
                    for(int z = 0; z < Nfases.length;z++){
                        if(Nfases[z] == fases.fase_atual){
                            list_monst = extras.arrayintAdd(list_monst, i);
                            break;
                        }
                    }
                }
                break;
            case 2: // caso for monstros boss
                for(int i = 0; i < handler.bosses.size();i++){
                    int[] Nfases = handler.bosses.get(i).getNfases();
                    for(int z = 0; z < Nfases.length;z++){
                        if(Nfases[z] == fases.fase_atual){
                            list_monst = extras.arrayintAdd(list_monst, i);
                            break;
                        }
                    }
                }
                break;
        }
        int index = extras.rng_int(0, list_monst.length);
        return list_monst[index];
    }

    public static void listarInimigos(int tipo){
        extras.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        extras.println("|   ID   |          Nome          |                                                Descricao                                                |   ID Arma   |   VidaMax   |   Forca   |   Defesa   |   Destreza   |   Exp   |   FasesQueAparece   |");
        extras.println("|________|________________________|_________________________________________________________________________________________________________|_____________|_____________|___________|____________|______________|_________|_____________________|");
        for(int i = 0; i < getInimigoLista(tipo).size();i++){
            extras.println("|" + extras.verTamMax_table(i, 8)
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getNome(), 24) 
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getDesc(), 105) 
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getArmaEquip(), 13) 
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getVidamax(), 13) 
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getForca(), 11) 
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getDefesa(), 12) 
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getDestreza(), 14) 
            + "|" + extras.verTamMax_table(inimigos.getInimigo(i, tipo).getExp(), 9) 
            + "|" + extras.verTamMax_table(extras.intArraytoString(inimigos.getInimigo(i, tipo).getNfases()), 21) 
            + "|");
            extras.println("|________|________________________|_________________________________________________________________________________________________________|_____________|_____________|___________|____________|______________|_________|_____________________|");
        }
    }

    public static void configInimigos(int tipo){
        extras.println("");
        listarInimigos(tipo);
        extras.println("");
        extras.println_bonito("Adicionar um novo inimigo ou editar um ja existente? (para apagar um inimigo, edite os numeros das fases em que aparece para 0)", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarInimigo(tipo);
            break;
            case "editar":
                editarInimigo(tipo);
            break;
            default:
            break;
        }
    }

    static void criarInimigo(int tipo){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome do inimigo?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a descricao do inimigo?", 500, 500);
            String desc = extras.inputS();
            extras.println("");
            armas.listArmas();
            extras.println("");
            extras.println_bonito("Qual sera o Id da arma do inimigo?", 500, 500);
            int arma_equip = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a Vida Maxima do inimigo?", 500, 500);
            int vidamax = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a forca do inimigo?", 500, 500);
            int forca = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a defesa do inimigo?", 500, 500);
            int defesa = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a destreza do inimigo?", 500, 500);
            int destreza = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a quantidade de xp que o inimigo ira dropar ao morrer?", 500, 500);
            int exp = extras.inputI();
            extras.println("");
            extras.println_bonito("Digite os numeros das fases que o inimigo ira aparecer\n(separe cada numero com virgula)", 500, 500);
            int[] Nfases = extras.stringtoIntArray(extras.inputS());
            switch(tipo){
                case 0:
                    handler.monstros.add(new monstros_f(nome, desc, arma_equip, vidamax, forca, defesa, destreza, exp, Nfases));
                break;
                case 1:
                    handler.bossesrand.add(new monstros_b(nome, desc, arma_equip, vidamax, forca, defesa, destreza, exp, Nfases));
                break;
                case 2:
                    handler.bosses.add(new monstros_b(nome, desc, arma_equip, vidamax, forca, defesa, destreza, exp, Nfases));
                break;
            }
            extras.println("");
            extras.println_bonito("Inimigo criado com sucesso", 500, 1000);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar inimigo: "+e);
        }
    }
   static void editarInimigo(int tipo){
        try{
            extras.println("");
            extras.println_bonito("Digite o ID do inimigo que gostaria de editar: ", 500, 500);
            int i = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual valor do inimigo "+getInimigo(i, tipo).getNome()+" voce gostaria de editar?", 500, 500);
            extras.println_bonito("Nome\nDesc\nArma\nVidaMax\nForca\nDestreza\nExp\nFases", 500, 500);
            switch(extras.inputS().toLowerCase()){
                case "nome":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo nome da inimigo " + getInimigo(i, tipo).getNome() + "?", 500, 500);
                    getInimigo(i, tipo).setNome(extras.inputS());
                break;
                case "desc":
                    extras.println("");
                    extras.println_bonito("Qual sera a descricao do inimigo " + getInimigo(i, tipo).getNome() + "?", 500, 500);
                    getInimigo(i, tipo).setDesc(extras.inputS());
                break;
                case "arma":
                    extras.println("");
                    armas.listArmas();
                    extras.println("");
                    extras.println_bonito("Qual sera a arma equipada do inimigo " + getInimigo(i, tipo).getNome() + "?", 500, 500);
                    getInimigo(i, tipo).setArmaEquip(extras.inputI());
                break;
                case "vidamax":
                    extras.println("");
                    extras.println_bonito("Qual sera a vida maxima do inimigo " + getInimigo(i, tipo).getNome() + "?", 500, 500);
                    getInimigo(i, tipo).setVidamax(extras.inputI());
                break;
                case "forca":
                    extras.println("");
                    extras.println_bonito("Qual sera a forca do inimigo " + getInimigo(i, tipo).getNome() + "?", 500, 500);
                    getInimigo(i, tipo).setForca(extras.inputI());
                break;
                case "destreza":
                    extras.println("");
                    extras.println_bonito("Qual sera a destreza do inimigo " + getInimigo(i, tipo).getNome() + "?", 500, 500);
                    getInimigo(i, tipo).setDestreza(extras.inputI());
                break;
                case "exp":
                    extras.println("");
                    extras.println_bonito("Qual sera a quantidade de exp que o inimigo " + getInimigo(i, tipo).getNome() + " ira dropar quando morrer?", 500, 500);
                    getInimigo(i, tipo).setExp(extras.inputI());
                break;
                case "fases":
                    extras.println("");
                    extras.println_bonito("Qual sera as fases que o inimigo " + getInimigo(i, tipo).getNome() + " ira aparecer?\nSepare cada numero com um virgula", 500, 500);
                    getInimigo(i, tipo).setNfases(extras.stringtoIntArray(extras.inputS()));
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida", 1000, 500);
                break;
            }
            extras.println("");
            extras.println_bonito(getInimigo(i, tipo).getNome() + " editado com sucesso!", 500, 700);
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar inimigo: "+e);
        }
    }

    public void setNfases(int[] n){this.Nfases = n;}
    public int[] getNfases(){return this.Nfases;}

}
