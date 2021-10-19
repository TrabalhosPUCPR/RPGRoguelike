package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class itensDef extends itens{

    int defesa;
    int destreza;

    static int[] itensDef_drop;
    static int[] qual_itensDef_drop;

    public itensDef(String nome, String desc, int defesa, int destreza, double dinheiro, int raridade){
        this.nome = nome;
        this.desc = desc;
        this.defesa = defesa; 
        this.destreza = destreza;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static int dropItenDef(){ // isso daqui vai retorna o id de uma arma aleatorio baseada na fase atual e raridade de arma, da pra usar isso no ngc do mercador tb, msm coisa com outros itens e equips
        return itensDef_drop[extras.rng_int(0, itensDef_drop.length)];
    }

    public static int dropItenDefRaro(){
        return qual_itensDef_drop[extras.rng_int(0, qual_itensDef_drop.length)];
    }

    public static void setDropRateItensDef(){
        int[] itensDef_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemDef.size(); i++){
            if(handler.itemDef.get(i).getRaridade()<=fases.fase_atual+1 && handler.itemDef.get(i).getRaridade()>fases.fase_atual-1){
                itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                if(handler.itemDef.get(i).getRaridade()==fases.fase_atual){
                    itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                }
            }
        }
        itensDef.itensDef_drop = itensDef_drop;
        setDropRateItensDefRaro();
    }

    static void setDropRateItensDefRaro(){
        int[] itensDef_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.itemDef.size(); i++){
            if(handler.itemDef.get(i).getRaridade()<=fases.fase_atual+2 && handler.itemDef.get(i).getRaridade()>fases.fase_atual){
                itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                if(handler.itemDef.get(i).getRaridade()==fases.fase_atual+1){
                    itensDef_drop = extras.arrayintAdd(itensDef_drop, i);
                }
            }
        }
        itensDef.qual_itensDef_drop = itensDef_drop;
    }

    static void listarItensDef(){
        extras.println("_______________________________________________________________________________________________________________________________________");
        extras.println("|   Id   |          Nome          |                    Desc                    |   Defesa   |   Destreza   |   Valor$   |   Raridade   |");
        extras.println("|________|________________________|____________________________________________|____________|______________|____________|______________|");
        for(int i = 0; i < handler.itemDef.size();i++){
            extras.println("|"+extras.verTamMax_table(i, 8)
            +"|"+ extras.verTamMax_table(handler.itemDef.get(i).getNome(), 24)
            +"|"+ extras.verTamMax_table(handler.itemDef.get(i).getDesc(), 44)
            +"|"+ extras.verTamMax_table(handler.itemDef.get(i).getForca(), 12)
            +"|"+ extras.verTamMax_table(handler.itemDef.get(i).getDestreza(), 14)
            +"|"+ extras.verTamMax_table(handler.itemDef.get(i).getDinheiro(), 12)
            +"|"+ extras.verTamMax_table(handler.itemDef.get(i).getRaridade(), 14)
            +"|");
            extras.println("|________|________________________|____________________________________________|____________|______________|____________|______________|");
        }
    }

    public static void configItemDef(){
        extras.println("");
        listarItensDef();
        extras.println("");
        extras.println_bonito("Adicionar um novo Acessorio Defensivo ou editar um ja existente?", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarItenDef();
            break;
            case "editar":
                editarItenDef();
            break;
            default:
            break;
        }

    } 

    static void criarItenDef(){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome do acessorio defensivo?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a descricao do acessorio defensivo?", 500, 500);
            String desc = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a defesa do acessorio defensivo?", 500, 500);
            int defesa = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a destreza do acessorio defensivo?", 500, 500);
            int destreza = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera o valor do acessorio defensivo?", 500, 500);
            double valor = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera a raridade do acessorio defensivo?", 500, 500);
            int raridade = extras.inputI();
            handler.itemDef.add(new itensDef(nome, desc, defesa, destreza, valor, raridade));
            extras.println("");
            extras.println_bonito("Acessorio defensivo criado com sucesso", 500, 1000);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar acessorio defensivo: "+e);
        }
    }
    static void editarItenDef(){
        try{
            extras.println("");
            extras.println_bonito("Digite o ID do acessorio defensivo que gostaria de editar: ", 500, 500);
            int i = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual valor da arma "+handler.itemDef.get(i).getNome()+" voce gostaria de editar?", 500, 500);
            extras.println_bonito("Nome\nDesc\nDefesa\nDestreza\nValor\nRaridade", 500, 500);
            switch(extras.inputS().toLowerCase()){
                case "nome":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo nome da acessorio defensivo " + handler.itemDef.get(i).getNome() + "?", 500, 500);
                    handler.itemDef.get(i).setNome(extras.inputS());
                break;
                case "desc":
                    extras.println("");
                    extras.println_bonito("Qual sera a descricao do acessorio defensivo " + handler.itemDef.get(i).getNome() + "?", 500, 500);
                    handler.itemDef.get(i).setDesc(extras.inputS());
                break;
                case "defesa":
                    extras.println("");
                    extras.println_bonito("Qual sera a defesa do acessorio defensivo " + handler.itemDef.get(i).getNome() + "?", 500, 500);
                    handler.itemDef.get(i).setDefesa(extras.inputI());
                break;
                case "destreza":
                    extras.println("");
                    extras.println_bonito("Qual sera a destreza do acessorio defensivo " + handler.itemDef.get(i).getNome() + "?", 500, 500);
                    handler.itemDef.get(i).setDestreza(extras.inputI());
                break;
                case "valor":
                    extras.println("");
                    extras.println_bonito("Qual sera o valor $ do acessorio defensivo " + handler.itemDef.get(i).getNome() + "?", 500, 500);
                    handler.itemDef.get(i).setValor(extras.inputD());
                break;
                case "raridade":
                    extras.println("");
                    extras.println_bonito("Qual sera a raridade do acessorio defensivo " + handler.itemDef.get(i).getNome() + "?", 500, 500);
                    handler.itemDef.get(i).setRaridade(extras.inputI());
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida", 1000, 500);
                break;
            }
            extras.println("");
            extras.println_bonito(handler.itemDef.get(i).getNome() + " editado com sucesso!", 500, 700);
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar arma: "+e);
        }
    }
}
