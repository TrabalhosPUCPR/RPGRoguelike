package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class armas extends itens{

    static int[] arma_drop;
    static int[] qual_arma_drop;
    int ataque;

    public armas(String nome, int ataque, String tipo, String peso, double dinheiro, int raridade){
        this.nome = nome;
        this.ataque = ataque; 
        this.tipo = tipo;
        this.peso = peso;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static void texto_som(int id){
        String sons_c[] = {"WHAAAAM!", "KA-BLAAAM!", "PUUFFFF!", "WOOOM!", "BAAAAM!", "TZINNG!"};
        String sons_l[] = {"ZOOOM!", "VOOOMP!", "PUUFFFF!", "TUUUMM!", "BAAAANG!"};


        switch(handler.arma.get(id).getTipo()){
            case "curto":
                extras.print("");
                extras.println_bonito(sons_c[extras.rng_int(0, sons_c.length)], 300, 200);
                break;
            case "longo":
                extras.print("");
                extras.println_bonito(sons_l[extras.rng_int(0, sons_l.length)], 300, 200);
                break;
        }
    }

    public static int dropArma(){ // isso daqui vai retorna o id de uma arma aleatorio baseada na fase atual e raridade de arma, da pra usar isso no ngc do mercador tb, msm coisa com outros itens e equips
        return arma_drop[extras.rng_int(0, arma_drop.length)];
    }

    public static int dropArmaRaro(){
        return qual_arma_drop[extras.rng_int(0, qual_arma_drop.length)];
    }

    public static void setDropRateArmas(){
        int[] arma_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.arma.size(); i++){
            if(handler.arma.get(i).getRaridade()<=fases.fase_atual+1 && handler.arma.get(i).getRaridade()>fases.fase_atual-1 && filtroArmaCurtoLongo(handler.arma.get(i).getTipo())){
                arma_drop = extras.arrayintAdd(arma_drop, i);
                if(handler.arma.get(i).getRaridade()==fases.fase_atual){
                    arma_drop = extras.arrayintAdd(arma_drop, i);
                }
            }
        }
        armas.arma_drop = arma_drop;
        setDropRateArmasRaro();
    }

    static void setDropRateArmasRaro(){
        int[] arma_drop = new int[]{}; // msm coisa q ali em cima
        for(int i = 0; i < handler.arma.size(); i++){
            if(handler.arma.get(i).getRaridade()<=fases.fase_atual+2 && handler.arma.get(i).getRaridade()>fases.fase_atual && filtroArmaCurtoLongo(handler.arma.get(i).getTipo())){
                arma_drop = extras.arrayintAdd(arma_drop, i);
                if(handler.arma.get(i).getRaridade()==fases.fase_atual+1){
                    arma_drop = extras.arrayintAdd(arma_drop, i);
                }
            }
        }
        armas.qual_arma_drop = arma_drop;
    }

    public static boolean filtroArmaCurtoLongo(String i){
        switch(i){
            case "curto":
                i = "0";
                break;
            case "longo":
                i = "1";
                break;
        }
        if(Integer.parseInt(i) == handler.jogador.gettipoArma() || handler.jogador.gettipoArma() == 2){
            return true;
        }
        return false;
    }

    public void printStats(){
        extras.println("____________________________________________________________________________");
        extras.println("|       Nome       |   Ataque   |   Tipo   |      Peso      |   Raridade   |");
        extras.println("|__________________|____________|__________|________________|______________|");
        extras.println("|"+ extras.verTamMax_table(this.nome, 18) + "|" + extras.verTamMax_table(this.ataque, 12) + "|" + extras.verTamMax_table(this.tipo, 10) + "|" + extras.verTamMax_table(this.peso, 16) + "|"+extras.verTamMax_table(this.raridade, 14)+ "|");
        extras.println("|__________________|____________|__________|________________|______________|");
    }

    public static void listArmas(){
        extras.println("___________________________________________________________________________________");
        extras.println("|  id  |       Nome       |   Ataque   |   Tipo   |      Peso      |   Raridade   |");
        extras.println("|______|__________________|____________|__________|________________|______________|");
        for(int i = 0; i < handler.arma.size(); i++){
            extras.println("|"+extras.verTamMax_table(i, 6)+"|"+ extras.verTamMax_table(handler.arma.get(i).getNome(), 18) + "|" + extras.verTamMax_table(handler.arma.get(i).getAtaque(), 12) + "|" + extras.verTamMax_table(handler.arma.get(i).getTipo(), 10) + "|" + extras.verTamMax_table(handler.arma.get(i).getPeso(), 16) + "|"+extras.verTamMax_table(handler.arma.get(i).getRaridade(), 14)+ "|");
            extras.println("|______|__________________|____________|__________|________________|______________|");
        }
    }

    public static void configArmas(){
        extras.println("");
        listArmas();
        extras.println("");
        extras.println_bonito("Adicionar uma nova arma ou editar uma ja existente? (para apagar uma arma, edite a raridade para 0)", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarArma();
            break;
            case "editar":
                editarArma();
            break;
            default:
            break;
        }
    }

    private static void criarArma(){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome da arma?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera o ataque da arma?", 500, 500);
            int ataque = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera o tipo da arma?", 500, 500);
            extras.println_bonito("Curto\nLongo", 500, 500);
            String tipoArma = extras.inputS().toLowerCase();
            extras.println("");
            extras.println_bonito("Qual sera o peso da arma?", 500, 500);
            extras.println_bonito("Omega leve\nSuper leve\nLeve\nPesado\nSuper pesado", 500, 500);
            String peso = extras.inputS().toLowerCase();
            extras.println("");
            extras.println_bonito("Qual sera o valor da arma?", 500, 500);
            double valor = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera a raridade da arma?", 500, 500);
            int raridade = extras.inputI();
            handler.arma.add(new armas(nome, ataque, tipoArma, peso, valor, raridade));
            extras.println("");
            extras.println_bonito("Arma criado com sucesso", 500, 1000);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar arma: "+e);
        }
    }

    private static void editarArma(){
        try{
            extras.println("");
            extras.println_bonito("Digite o ID da arma que gostaria de editar: ", 500, 500);
            int i = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual valor da arma "+handler.arma.get(i).getNome()+" voce gostaria de editar?", 500, 500);
            extras.println_bonito("Nome\nAtaque\nTipo\nPeso\nValor\nRaridade", 500, 500);
            switch(extras.inputS().toLowerCase()){
                case "nome":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo nome da arma " + handler.arma.get(i).getNome() + "?", 500, 500);
                    handler.arma.get(i).setNome(extras.inputS());
                break;
                case "ataque":
                    extras.println("");
                    extras.println_bonito("Qual sera o ataque da arma " + handler.arma.get(i).getNome() + "?", 500, 500);
                    handler.arma.get(i).setAtaque(extras.inputI());
                break;
                case "tipo":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo tipo da arma " + handler.arma.get(i).getNome() + "?", 500, 500);
                    extras.println_bonito("Curto\nLongo", 500, 500);
                    handler.arma.get(i).setTipo(extras.inputS());
                break;
                case "peso":
                    extras.println("");
                    extras.println_bonito("Qual sera o peso da arma " + handler.arma.get(i).getNome() + "?", 500, 500);
                    extras.println_bonito("Omega leve\nSuper leve\nLeve\nPesado\nSuper pesado", 500, 500);
                    handler.arma.get(i).setPeso(extras.inputS());
                break;
                case "valor":
                    extras.println("");
                    extras.println_bonito("Qual sera o valor $ da arma " + handler.arma.get(i).getNome() + "?", 500, 500);
                    handler.arma.get(i).setValor(extras.inputD());
                break;
                case "raridade":
                    extras.println("");
                    extras.println_bonito("Qual sera a raridade da arma " + handler.arma.get(i).getNome() + "?", 500, 500);
                    handler.arma.get(i).setRaridade(extras.inputI());
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida", 1000, 500);
                break;
            }
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar arma: "+e);
        }
    }
    
    public int getAtaque(){return this.ataque;}
    public void setAtaque(int n){this.ataque = n;}
    public void setTipo(String n){this.tipo = n;}

}
