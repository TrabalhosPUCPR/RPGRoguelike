package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class armas extends itens{

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

    public static int[] setDropRateArmas(){
        int[] arma_drop = new int[handler.arma.size()];
        for(int i = 0; i < handler.arma.size(); i++){
            if(handler.arma.get(i).getRaridade()<=fases.fase_atual+1 && handler.arma.get(i).getRaridade()>fases.fase_atual-1 && filtroArmaCurtoLongo(handler.arma.get(i).getTipo())){
                arma_drop[i] = i;
                if(handler.arma.get(i).getRaridade()==fases.fase_atual){
                    extras.aumentarTamArrayInt(arma_drop, 1);
                    arma_drop[i] = i;
                }
            }
        }
        return arma_drop;
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
        extras.println("_____________________________________________________________");
        extras.println("|       Nome       |   Ataque   |   Tipo   |      Peso      |");
        extras.println("|__________________|____________|__________|________________|");
        extras.println("|"+ extras.verTamMax_table(this.nome, 18) + "|" + extras.verTamMax_table(this.ataque, 12) + "|" + extras.verTamMax_table(this.tipo, 10) + "|" + extras.verTamMax_table(this.peso, 16) + "|");
        extras.println("|__________________|____________|__________|________________|");
    }

    public static void listArmas(){
        extras.println("____________________________________________________________________");
        extras.println("|  id  |       Nome       |   Ataque   |   Tipo   |      Peso      |");
        extras.println("|______|__________________|____________|__________|________________|");
        for(int i = 0; i < handler.arma.size(); i++){
            extras.println("|"+extras.verTamMax_table(i, 6)+"|"+ extras.verTamMax_table(handler.arma.get(i).getNome(), 18) + "|" + extras.verTamMax_table(handler.arma.get(i).getAtaque(), 12) + "|" + extras.verTamMax_table(handler.arma.get(i).getTipo(), 10) + "|" + extras.verTamMax_table(handler.arma.get(i).getPeso(), 16) + "|");
            extras.println("|______|__________________|____________|__________|________________|");
        }
    }

    public static void configArmas(){
        extras.println("");
        listArmas();
        extras.println("");
        extras.println_bonito("Adicionar uma nova arma ou editar uma ja existente?", 500, 500);
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
            double valor = extras.inputI();
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
                    handler.arma.get(i).setValor(extras.inputI());
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
