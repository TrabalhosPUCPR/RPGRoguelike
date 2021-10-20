package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class itensOfen extends itens{

    int forca;
    int destreza;

    static int[] itensOfen_drop;
    static int[] qual_itensOfen_drop;

    public itensOfen(String nome, String desc, int forca, int destreza, double dinheiro, int raridade){
        this.nome = nome;
        this.desc = desc;
        this.forca = forca; 
        this.destreza = destreza;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static int dropItenOfen(){ 
        return itensOfen_drop[extras.rng_int(0, itensOfen_drop.length)];
    }

    public static int dropItenOfenRaro(){
        return qual_itensOfen_drop[extras.rng_int(0, qual_itensOfen_drop.length)];
    }

    public static void setDropRateItensOfen(){
        int[] itensOfen_drop = new int[]{}; 
        for(int i = 0; i < handler.itemOfen.size(); i++){
            if(handler.itemOfen.get(i).getRaridade()<=fases.fase_atual+1 && handler.itemOfen.get(i).getRaridade()>fases.fase_atual-1){
                itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                if(handler.itemOfen.get(i).getRaridade()==fases.fase_atual){
                    itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                }
            }
        }
        itensOfen.itensOfen_drop = itensOfen_drop;
        setDropRateItensOfenRaro();
    }

    static void setDropRateItensOfenRaro(){
        int[] itensOfen_drop = new int[]{}; 
        for(int i = 0; i < handler.itemOfen.size(); i++){
            if(handler.itemOfen.get(i).getRaridade()<=fases.fase_atual+2 && handler.itemOfen.get(i).getRaridade()>fases.fase_atual){
                itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                if(handler.itemOfen.get(i).getRaridade()==fases.fase_atual+1){
                    itensOfen_drop = extras.arrayintAdd(itensOfen_drop, i);
                }
            }
        }
        itensOfen.qual_itensOfen_drop = itensOfen_drop;
    }

    static void listarItensOfen(){
        extras.println("_______________________________________________________________________________________________________________________________________");
        extras.println("|   Id   |          Nome          |                    Desc                    |   Forca   |   Destreza   |   Valor$   |   Raridade   |");
        extras.println("|________|________________________|____________________________________________|___________|______________|____________|______________|");
        for(int i = 0; i < handler.itemOfen.size();i++){
            extras.println("|"+extras.verTamMax_table(i, 8)
            +"|"+ extras.verTamMax_table(handler.itemOfen.get(i).getNome(), 24)
            +"|"+ extras.verTamMax_table(handler.itemOfen.get(i).getDesc(), 44)
            +"|"+ extras.verTamMax_table(handler.itemOfen.get(i).getForca(), 11)
            +"|"+ extras.verTamMax_table(handler.itemOfen.get(i).getDestreza(), 14)
            +"|"+ extras.verTamMax_table(handler.itemOfen.get(i).getDinheiro(), 12)
            +"|"+ extras.verTamMax_table(handler.itemOfen.get(i).getRaridade(), 14)
            +"|");
            extras.println("|________|________________________|____________________________________________|___________|______________|____________|______________|");
        }
    }

    public static void configItemOfen(){
        extras.println("");
        listarItensOfen();
        extras.println("");
        extras.println_bonito("Adicionar um novo Acessorio Ofensivo ou editar um ja existente?", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarItenOfen();
            break;
            case "editar":
                editarItenOfen();
            break;
            default:
            break;
        }
    } 

    static void criarItenOfen(){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome do acessorio ofensivo?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a descricao do acessorio ofensivo?", 500, 500);
            String desc = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a forca do acessorio ofensivo?", 500, 500);
            int forca = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a destreza do acessorio ofensivo?", 500, 500);
            int destreza = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera o valor do acessorio ofensivo?", 500, 500);
            double valor = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera a raridade do acessorio ofensivo?", 500, 500);
            int raridade = extras.inputI();
            handler.itemOfen.add(new itensOfen(nome, desc, forca, destreza, valor, raridade));
            extras.println("");
            extras.println_bonito("Acessorio ofensivo criado com sucesso", 500, 1000);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar acessorio ofensivo: "+e);
        }
    }
   static void editarItenOfen(){
        try{
            extras.println("");
            extras.println_bonito("Digite o ID do acessorio ofensivo que gostaria de editar: ", 500, 500);
            int i = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual valor da arma "+handler.itemOfen.get(i).getNome()+" voce gostaria de editar?", 500, 500);
            extras.println_bonito("Nome\nDesc\nForca\nDestreza\nValor\nRaridade", 500, 500);
            switch(extras.inputS().toLowerCase()){
                case "nome":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo nome da acessorio ofensivo " + handler.itemOfen.get(i).getNome() + "?", 500, 500);
                    handler.itemOfen.get(i).setNome(extras.inputS());
                break;
                case "desc":
                    extras.println("");
                    extras.println_bonito("Qual sera a descricao do acessorio ofensivo " + handler.itemOfen.get(i).getNome() + "?", 500, 500);
                    handler.itemOfen.get(i).setDesc(extras.inputS());
                break;
                case "forca":
                    extras.println("");
                    extras.println_bonito("Qual sera a forca do acessorio ofensivo " + handler.itemOfen.get(i).getNome() + "?", 500, 500);
                    handler.itemOfen.get(i).setForca(extras.inputI());
                break;
                case "destreza":
                    extras.println("");
                    extras.println_bonito("Qual sera a destreza do acessorio ofensivo " + handler.itemOfen.get(i).getNome() + "?", 500, 500);
                    handler.itemOfen.get(i).setDestreza(extras.inputI());
                break;
                case "valor":
                    extras.println("");
                    extras.println_bonito("Qual sera o valor $ do acessorio ofensivo " + handler.itemOfen.get(i).getNome() + "?", 500, 500);
                    handler.itemOfen.get(i).setValor(extras.inputD());
                break;
                case "raridade":
                    extras.println("");
                    extras.println_bonito("Qual sera a raridade do acessorio ofensivo " + handler.itemOfen.get(i).getNome() + "?", 500, 500);
                    handler.itemOfen.get(i).setRaridade(extras.inputI());
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida", 1000, 500);
                break;
            }
            extras.println("");
            extras.println_bonito(handler.itemOfen.get(i).getNome() + " editado com sucesso!", 500, 700);
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar arma: "+e);
        }
    }
}
