package itens;

import adicionais.extras;
import adicionais.handler;

public class inventario {
    public static int itenOfensivo = 0;
    public static int itenDefensivo = 0;
    public static int  itenMisc = 0;
    private static int[] itenConsumivel = {1};
    public static Double dinheiro = 100.0;

    public static boolean usarItem(int index){
        if(index > itenConsumivel.length){
            extras.println("");
            extras.println_bonito("Digite um numero valido...", 400, 400);
            return false;
        }
        handler.consu.get(itenConsumivel[index-1]).usar();
        itenConsumivel = extras.removeIndex(itenConsumivel, index-1);
        return true;
    }

    public static boolean listarItensConsu(){
        if(itenConsumivel.length > 0){
            extras.println("_________________________________________________________________________");
            extras.println("|                          |                                            |");
            extras.println("|           Nome           |                    Acao                    |");
            for(int i = 0; i < itenConsumivel.length; i++){
                extras.println("|__________________________|____________________________________________|");
                extras.println("|                          |                                            |");
                extras.println("|"+ extras.verTamMax_table("["+(i+1)+"]" + handler.consu.get(itenConsumivel[i]).getNome(), 26) + "|" + extras.verTamMax_table(handler.consu.get(itenConsumivel[i]).getAcao(), 44) + "|");
                extras.println("|__________________________|____________________________________________|");
            }
            return true;
        }else{
            extras.println("");
            extras.println_bonito("Voce nao tem nenhum item para usar no seu inventario...", 400, 400);
            extras.println("");
            return false;
        }
    }

    public static void printAce(){
        extras.println("");
        extras.println("______________________________________________________________________________________");
        extras.println("|              |                        |                                            |");
        extras.println("|     Tipo     |          Nome          |                    Desc                    |");
        extras.println("|______________|________________________|____________________________________________|");
        extras.println("|              |                        |                                            |");
        extras.println("|"+ extras.verTamMax_table("Ofensivo", 14)+"|"+extras.verTamMax_table(handler.itemOfen.get(itenOfensivo).getNome(), 24) + "|" + extras.verTamMax_table(handler.itemOfen.get(itenOfensivo).getDesc(), 44)+ "|");
        extras.println("|______________|________________________|____________________________________________|");
        extras.println("|              |                        |                                            |");
        extras.println("|"+ extras.verTamMax_table("Defensivo", 14)+"|"+extras.verTamMax_table(handler.itemDef.get(itenDefensivo).getNome(), 24) + "|" + extras.verTamMax_table(handler.itemDef.get(itenDefensivo).getDesc(), 44)+ "|");
        extras.println("|______________|________________________|____________________________________________|");
        extras.println("|              |                        |                                            |");
        extras.println("|"+ extras.verTamMax_table("Misc", 14)+"|"+extras.verTamMax_table(handler.itemMisc.get(itenMisc).getNome(), 24) + "|" + extras.verTamMax_table(handler.itemMisc.get(itenMisc).getDesc(), 44)+ "|");
        extras.println("|______________|________________________|____________________________________________|");
    }

    public void receberItem(int id){
        extras.println("");
        extras.println_bonito("Voce recebeu um " + handler.consu.get(id).getNome()+"!", 700, 500);
        boolean jatem = false;
        for(int i = 0; i < itenConsumivel.length; i++){
            if(itenConsumivel[i] == id){
                extras.println_bonito("OPS!", 300, 400);
                extras.println("");
                extras.println_bonito("Voce ja tem um " + handler.consu.get(id).getNome()+" no seu inventario!", 700, 600);
                jatem = true;
                break;
            }
        }
        if(jatem == false){
            itenConsumivel[itenConsumivel.length] = id;
        }
    }

    public void receberItem(itensDef item, int id){
        extras.println("");
        extras.println_bonito("Voce recebeu um " + item.getNome() + ", " + item.getDesc(), 700, 500);
        if(itenDefensivo != 0){
            extras.println("");
            extras.println_bonito("Voce ja tem um " + handler.itemDef.get(itenDefensivo).getNome(), 700, 500);
            extras.println("");
            extras.println_bonito("Gostaria de trocar o seu " + handler.itemDef.get(itenDefensivo).getNome() + " por um " + item.getNome() + "?", 700, 500);
            if(extras.simNao()){
                extras.println("");
                extras.println_bonito("Voce jogou o " + handler.itemDef.get(itenDefensivo).getNome() + " fora", 700, 500);
                extras.println("");
                itenDefensivo = id;
                extras.println_bonito("E se equipou com " + handler.itemDef.get(itenDefensivo).getNome() + "!", 700, 500);
            }else{
                extras.println("");
                extras.println_bonito("Voce deixou o " + item.getNome() + " para tras, sera que foi para o melhor?", 700, 500);
            }
        }else{
            extras.println("");
            itenDefensivo = id;
            extras.println_bonito("Voce equipou o " + handler.itemDef.get(itenDefensivo).getNome() + "!", 700, 500);
        }
    }

    public void receberItem(itensOfen item, int id){
        extras.println("");
        extras.println_bonito("Voce recebeu um " + item.getNome() + ", " + item.getDesc(), 700, 500);
        if(itenOfensivo != 0){
            extras.println("");
            extras.println_bonito("Voce ja tem um " + handler.itemOfen.get(itenOfensivo).getNome(), 700, 500);
            extras.println("");
            extras.println_bonito("Gostaria de trocar o seu " + handler.itemOfen.get(itenOfensivo).getNome() + " por um " + item.getNome() + "?", 700, 500);
            if(extras.simNao()){
                extras.println("");
                extras.println_bonito("Voce jogou o " + handler.itemOfen.get(itenOfensivo).getNome() + " fora", 700, 500);
                extras.println("");
                itenOfensivo = id;
                extras.println_bonito("E se equipou com " + handler.itemOfen.get(itenOfensivo).getNome() + "!", 700, 500);
            }else{
                extras.println("");
                extras.println_bonito("Voce deixou o " + item.getNome() + " para tras, sera que foi para o melhor?", 700, 500);
            }
        }else{
            extras.println("");
            itenOfensivo = id;
            extras.println_bonito("Voce equipou o " + handler.itemOfen.get(itenOfensivo).getNome() + "!", 700, 500);
        }
    }

    public void receberItem(itensMisc item, int id){
        extras.println("");
        extras.println_bonito("Voce recebeu um " + item.getNome() + ", " + item.getDesc(), 700, 500);
        if(itenMisc != 0){
            extras.println("");
            extras.println_bonito("Voce ja tem um " + handler.itemMisc.get(itenMisc).getNome(), 700, 500);
            extras.println("");
            extras.println_bonito("Gostaria de trocar o seu " + handler.itemMisc.get(itenMisc).getNome() + " por um " + item.getNome() + "?", 700, 500);
            if(extras.simNao()){
                extras.println("");
                extras.println_bonito("Voce jogou o " + handler.itemMisc.get(itenMisc).getNome() + " fora", 700, 500);
                extras.println("");
                itenMisc = id;
                extras.println_bonito("E se equipou com " + handler.itemMisc.get(itenMisc).getNome() + "!", 700, 500);
            }else{
                extras.println("");
                extras.println_bonito("Voce deixou o " + item.getNome() + " para tras, sera que foi para o melhor?", 700, 500);
            }
        }else{
            extras.println("");
            itenMisc = id;
            extras.println_bonito("Voce equipou o " + handler.itemMisc.get(itenMisc).getNome() + "!", 700, 500);
        }
    }

    public static void ganharDinheiro(double n){dinheiro += n;}

    public static double getDefTotal(){return handler.itemDef.get(itenDefensivo).getDefesa() + handler.itemMisc.get(itenMisc).getDefesa();}
    public static double getDesTotal(){return handler.itemDef.get(itenDefensivo).getDestreza() + handler.itemMisc.get(itenMisc).getDestreza() + handler.itemOfen.get(itenOfensivo).getDestreza();}
    public static double getForcaTotal(){return handler.itemOfen.get(itenDefensivo).getForca() + handler.itemMisc.get(itenMisc).getForca();}
}