package itens;

import adicionais.extras;
import adicionais.handler;

public class inventario {
    public static int itenOfensivo = 0;
    public static int itenDefensivo = 0;
    public static int  itenMisc = 0;
    private static int[] itenConsumivel = {};
    public static Double dinheiro = 0.0;

    public static void resetInventario(){
        itenOfensivo = 0;
        itenDefensivo = 0;
        itenMisc = 0;
        itenConsumivel = new int[]{};
        dinheiro = 0.0;
    }

    public static boolean chaveNoInventario(){
        if(handler.itemMisc.get(itenMisc).getNome().toLowerCase().equals("chave")){
            return true;
        }
        return false;
    }

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
                extras.println("|"+ extras.verTamMax_table("["+(i+1)+"]" + handler.consu.get(itenConsumivel[i]).getNome(), 26) + "|" + extras.verTamMax_table(handler.consu.get(itenConsumivel[i]).getDesc(), 44) + "|");
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

    public static boolean venderAce(){
        extras.print("");
        printAce();
        extras.print("");
        extras.println_bonito("Digite o tipo do acessorio que gostaria de vender: ", 500, 500);
        extras.print("");
        extras.println_bonito("Ofensivo", 200, 20);
        extras.println_bonito("Defensivo", 200, 20);
        extras.println_bonito("Misc", 200, 20);
        extras.println_bonito("Voltar", 200, 20);
        switch(extras.inputS().toLowerCase()){
            case "ofensivo":
                if(itenOfensivo == 0){
                    extras.print("");
                    extras.println_bonito("Voce nao pode vender o nada...", 500, 500);
                }else{
                    extras.print("");
                    extras.println_bonito("Voce gostaria de vender o seu " + handler.itemOfen.get(itenOfensivo).getNome() + " por $"+(handler.itemOfen.get(itenOfensivo).getDinheiro()/2), 500, 500);
                    if(extras.simNao()){
                        extras.print("");
                        extras.println_bonito("Voce vendeu o seu " + handler.itemOfen.get(itenOfensivo).getNome(), 500, 500);
                        ganharDinheiro(handler.itemOfen.get(itenOfensivo).getDinheiro()/2);
                        itenOfensivo = 0;
                        return true;
                    }
                }
            break;
            case "defensivo":
                if(itenDefensivo == 0){
                    extras.print("");
                    extras.println_bonito("Voce nao pode vender o nada...", 500, 500);
                }else{
                    extras.print("");
                    extras.println_bonito("Voce gostaria de vender o seu " + handler.itemDef.get(itenDefensivo).getNome() + " por $"+(handler.itemDef.get(itenDefensivo).getDinheiro()/2), 500, 500);
                    if(extras.simNao()){
                        extras.print("");
                        extras.println_bonito("Voce vendeu o seu " + handler.itemDef.get(itenDefensivo).getNome(), 500, 500);
                        ganharDinheiro(handler.itemDef.get(itenDefensivo).getDinheiro()/2);
                        itenDefensivo = 0;
                        return true;
                    }
                }
            break;
            case "misc":
                if(itenMisc == 0){
                    extras.print("");
                    extras.println_bonito("Voce nao pode vender o nada...", 500, 500);
                }else{
                    extras.print("");
                    extras.println_bonito("Voce gostaria de vender o seu " + handler.itemMisc.get(itenMisc).getNome() + " por $"+(handler.itemMisc.get(itenMisc).getDinheiro()/2), 500, 500);
                    if(extras.simNao()){
                        extras.print("");
                        extras.println_bonito("Voce vendeu o seu " + handler.itemMisc.get(itenMisc).getNome(), 500, 500);
                        ganharDinheiro(handler.itemMisc.get(itenMisc).getDinheiro()/2);
                        itenMisc = 0;
                        return true;
                    }
                }
            break;
            case "voltar":
            break;
            default:
                extras.print("");
                extras.println_bonito("Digite uma opcao valida...", 500, 500);
            break;
        }
        return false;

    }

    public static void receberItem(int id){
        extras.println("");
        extras.println_bonito("Voce recebeu " + handler.consu.get(id).getNome()+"!", 700, 500);
        boolean jatem = false;
        if(itenConsumivel.length>0){
            for(int i = 0; i < itenConsumivel.length; i++){
                if(itenConsumivel[i] == id){
                    extras.println("");
                    extras.println_bonito("OPS!", 300, 400);
                    extras.println("");
                    extras.println_bonito("Voce ja tem " + handler.consu.get(id).getNome()+" no seu inventario!", 700, 600);
                    jatem = true;
                    break;
                }
            }
        }else if(jatem == false){
            itenConsumivel = new int[] {id};
        }
    }

    public static void receberItem(int tipo, int id){
        switch(tipo){
            case 0:
                receberItem(id);
            break;
            case 1:
                receberItem(handler.itemOfen.get(id), id);
            break;
            case 2:
                receberItem(handler.itemDef.get(id), id);
            break;
            case 3:
                receberItem(handler.itemMisc.get(id), id);
            break;
        }
    }

    public static void receberItem(itensDef item, int id){
        extras.println("");
        extras.println_bonito("Voce recebeu " + item.getNome() + ", " + item.getDesc(), 700, 500);
        if(itenDefensivo != 0){
            extras.println("");
            extras.println_bonito("Voce ja tem " + handler.itemDef.get(itenDefensivo).getNome(), 700, 500);
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
            extras.println_bonito("Gostaria de guarda-lo? ", 700, 500);
            if(extras.simNao()){
                itenDefensivo = id;
                extras.println("");
                extras.println_bonito("Voce equipou o " + handler.itemDef.get(itenDefensivo).getNome() + "!", 700, 500);
            }else{
                extras.println("");
                extras.println_bonito("Voce deixou o " + item.getNome() + " para tras, sera que foi para o melhor?", 700, 500);
            }
        }
    }

    public static void receberItem(itensOfen item, int id){
        extras.println("");
        extras.println_bonito("Voce recebeu " + item.getNome() + ", " + item.getDesc(), 700, 500);
        if(itenOfensivo != 0){
            extras.println("");
            extras.println_bonito("Voce ja tem " + handler.itemOfen.get(itenOfensivo).getNome(), 700, 500);
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
            extras.println_bonito("Gostaria de guarda-lo? ", 700, 500);
            if(extras.simNao()){
                itenOfensivo = id;
                extras.println("");
                extras.println_bonito("Voce equipou o " + handler.itemOfen.get(itenOfensivo).getNome() + "!", 700, 500);
            }else{
                extras.println("");
                extras.println_bonito("Voce deixou o " + item.getNome() + " para tras, sera que foi para o melhor?", 700, 500);
            }
        }
    }

    public static void receberItem(itensMisc item, int id){
        extras.println("");
        extras.println_bonito("Voce recebeu " + item.getNome() + ", " + item.getDesc(), 700, 500);
        if(itenMisc != 0){
            extras.println("");
            extras.println_bonito("Voce ja tem " + handler.itemMisc.get(itenMisc).getNome(), 700, 500);
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
            extras.println_bonito("Gostaria de guarda-lo? ", 700, 500);
            if(extras.simNao()){
                itenMisc = id;
                extras.println("");
                extras.println_bonito("Voce equipou o " + handler.itemMisc.get(itenMisc).getNome() + "!", 700, 500);
            }else{
                extras.println("");
                extras.println_bonito("Voce deixou o " + item.getNome() + " para tras, sera que foi para o melhor?", 700, 500);
            }
        }
    }

    public static void ganharDinheiro(double n){
        extras.println("");
        extras.println_bonito("Voce ganhou $" + String.format("%.0f", n) + "!", 600, 500);
        dinheiro += n;
    }

    public static void gastarDinheiro(double n){
        extras.println("");
        extras.println_bonito("Voce gastou $" + String.format("%.0f", n) + "!", 600, 500);
        dinheiro -= n;
    }

    public static double getDefTotal(){return handler.itemDef.get(itenDefensivo).getDefesa() + handler.itemMisc.get(itenMisc).getDefesa();}
    public static double getDesTotal(){return handler.itemDef.get(itenDefensivo).getDestreza() + handler.itemMisc.get(itenMisc).getDestreza() + handler.itemOfen.get(itenOfensivo).getDestreza();}
    public static double getForcaTotal(){return handler.itemOfen.get(itenOfensivo).getForca() + handler.itemMisc.get(itenMisc).getForca();}
    public static double getDodgeBonus(){return handler.itemMisc.get(itenMisc).getBuffEva();}
}