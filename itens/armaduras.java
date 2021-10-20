package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class armaduras extends itens{

    double evasion_bonus;
    double defesa;

    static int[] armor_drop;
    static int[] qual_armor_drop;

    public armaduras(String nome, double defesa, double evasion_bonus, String peso, double dinheiro, int raridade){
        this.nome = nome;
        this.defesa = defesa;
        this.evasion_bonus = evasion_bonus;
        this.peso = peso;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static int dropArmor(){
        return armor_drop[extras.rng_int(0, armor_drop.length)];
    }

    public static int dropArmorRaro(){
        return qual_armor_drop[extras.rng_int(0, qual_armor_drop.length)];
    }

    public static void setDropRateArmaduras(){
        int[] armor_drop = new int[]{}; 
        for(int i = 0; i < handler.armor.size(); i++){
            if(handler.armor.get(i).getRaridade()<=fases.fase_atual+1 && handler.armor.get(i).getRaridade()>=fases.fase_atual){
                armor_drop = extras.arrayintAdd(armor_drop, i);
                if(handler.armor.get(i).getRaridade()==fases.fase_atual){
                    armor_drop = extras.arrayintAdd(armor_drop, i);
                }
            }
        }
        armaduras.armor_drop = armor_drop;
        setDropRateArmadurasRaro();
    }

    static void setDropRateArmadurasRaro(){
        int[] armor_drop = new int[]{};
        for(int i = 0; i < handler.armor.size(); i++){
            if(handler.armor.get(i).getRaridade()<=fases.fase_atual+2 && handler.armor.get(i).getRaridade()>fases.fase_atual){
                armor_drop = extras.arrayintAdd(armor_drop, i);
                if(handler.armor.get(i).getRaridade()==fases.fase_atual+1){
                    armor_drop = extras.arrayintAdd(armor_drop, i);
                }
            }
        }
        armaduras.qual_armor_drop = armor_drop;
    }


    public void printStats(){
        extras.println("___________________________________________________________________________________");
        extras.println("|       Nome       |   Defesa   |   BonusEvasao   |      Peso      |   Raridade   |");
        extras.println("|__________________|____________|_________________|________________|______________|");
        extras.println("|"+ extras.verTamMax_table(this.nome, 18) + "|" + extras.verTamMax_table(String.format("%.00f", this.defesa), 12) + "|" + extras.verTamMax_table(this.evasion_bonus, 17) + "|" + extras.verTamMax_table(this.peso, 16) + "|" + extras.verTamMax_table(this.raridade, 14) + "|");
        extras.println("|__________________|____________|_________________|________________|______________|");
    }

    public static void listArmaduras(){
        extras.println("__________________________________________________________________________________________");
        extras.println("|  id  |       Nome       |   Defesa   |   BonusEvasao   |      Peso      |   Raridade   |");
        extras.println("|______|__________________|____________|_________________|________________|______________|");
        for(int i = 0; i < handler.armor.size(); i++){
            extras.println("|"+extras.verTamMax_table(i, 6)+"|"+ extras.verTamMax_table(handler.armor.get(i).getNome(), 18) + "|" + extras.verTamMax_table(String.format("%.00f", handler.armor.get(i).getDefesa()), 12) + "|" + extras.verTamMax_table(handler.armor.get(i).getEvasionB(), 17) + "|" + extras.verTamMax_table(handler.armor.get(i).getPeso(), 16) + "|"+ extras.verTamMax_table(handler.armor.get(i).getRaridade(), 14) + "|");
            extras.println("|______|__________________|____________|_________________|________________|______________|");
        }
    }

    
    public static void configArmaduras(){
        extras.println("");
        listArmaduras();
        extras.println("");
        extras.println_bonito("Adicionar uma nova armadura ou editar uma ja existente?", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarArmadura();
            break;
            case "editar":
                editarArmadura();
            break;
            default:
            break;
        }
    }

    private static void criarArmadura(){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome da armadura?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a defesa da armadura?", 500, 500);
            int defesa = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera o bonus de evasao da armadura?", 500, 500);
            extras.println_bonito("(Entre 0.0 e 2.0)", 500, 500);
            double eva_bonus = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera o peso da armadura?", 500, 500);
            extras.println_bonito("Omega leve\nSuper leve\nLeve\nPesado\nSuper pesado", 500, 500);
            String peso = extras.inputS().toLowerCase();
            extras.println("");
            extras.println_bonito("Qual sera o valor da armadura?", 500, 500);
            double valor = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera a raridade da armadura?", 500, 500);
            int raridade = extras.inputI();
            handler.armor.add(new armaduras(nome, defesa, eva_bonus, peso, valor, raridade));
            extras.println("");
            extras.println_bonito("Armadura criado com sucesso", 500, 1000);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar armadura: "+e);
            extras.delay(1000);
        }
    }

    private static void editarArmadura(){
        try{
            extras.println("");
            extras.println_bonito("Digite o ID da armadura que gostaria de editar: ", 500, 500);
            int i = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual valor da armadura "+handler.armor.get(i).getNome()+" voce gostaria de editar?", 500, 500);
            extras.println_bonito("Nome\nDefesa\nEbonus\nPeso\nValor\nRaridade", 500, 500);
            switch(extras.inputS().toLowerCase()){
                case "nome":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo nome da armadura " + handler.armor.get(i).getNome() + "?", 500, 500);
                    handler.armor.get(i).setNome(extras.inputS());
                break;
                case "defesa":
                    extras.println("");
                    extras.println_bonito("Qual sera a defesa da armadura " + handler.armor.get(i).getNome() + "?", 500, 500);
                    handler.armor.get(i).setDefesa(extras.inputD());
                break;
                case "ebonus":
                    extras.println("");
                    extras.println_bonito("Qual sera o bonus de evasao da armadura " + handler.armor.get(i).getNome() + "?", 500, 500);
                    extras.println_bonito("(Numero decimal)", 500, 500);
                    handler.armor.get(i).setEvasionB(extras.inputD());
                break;
                case "peso":
                    extras.println("");
                    extras.println_bonito("Qual sera o peso da armadura " + handler.armor.get(i).getNome() + "?", 500, 500);
                    extras.println_bonito("Omega leve\nSuper leve\nLeve\nPesado\nSuper pesado", 500, 500);
                    handler.armor.get(i).setPeso(extras.inputS());
                break;
                case "valor":
                    extras.println("");
                    extras.println_bonito("Qual sera o valor $ da armadura " + handler.armor.get(i).getNome() + "?", 500, 500);
                    handler.armor.get(i).setValor(extras.inputI());
                break;
                case "raridade":
                    extras.println("");
                    extras.println_bonito("Qual sera a raridade da armadura " + handler.armor.get(i).getNome() + "?", 500, 500);
                    handler.armor.get(i).setRaridade(extras.inputI());
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida", 1000, 500);
                break;
            }
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar armadura: "+e);
            extras.delay(1000);
        }
    }
    public void setDefesa(double n){this.defesa = n;}
    public void setEvasionB(double n){this.evasion_bonus = n;}
    public double getDefesa(){return this.defesa;}
    public double getEvasionB(){return this.evasion_bonus;}
}
