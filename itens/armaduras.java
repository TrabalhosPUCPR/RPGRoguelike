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
        int[] armor_drop = new int[]{}; // eu comecei fazendo com int[] pq eu sou vagabundo, e percebi q tinha q fazer .add q o arraylist tem, mas ao inves de trocar pro arraylist eu fiz o meu prorio .add KKKKKKKKKKKKKKKKKK
        for(int i = 0; i < handler.armor.size(); i++){
            if(handler.armor.get(i).getRaridade()<=fases.fase_atual+1 && handler.armor.get(i).getRaridade()>=fases.fase_atual){
                armor_drop = extras.arrayintAdd(armor_drop, i);
                extras.println(handler.armor.get(i).getRaridade()+" i: "+i);
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
    
    public double getDefesa(){return this.defesa;}
    public double getEvasionB(){return this.evasion_bonus;}
}
