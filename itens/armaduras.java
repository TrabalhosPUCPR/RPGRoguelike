package itens;

import adicionais.extras;
import adicionais.handler;

public class armaduras extends itens{
    double evasion_bonus;
    double defesa;

    public armaduras(String nome, double defesa, double evasion_bonus, String peso, double dinheiro){
        this.nome = nome;
        this.defesa = defesa;
        this.evasion_bonus = evasion_bonus;
        this.peso = peso;
        this.dinheiro = dinheiro;
    }

    public void printStats(){
        extras.println("____________________________________________________________________");
        extras.println("|       Nome       |   Defesa   |   BonusEvasao   |      Peso      |");
        extras.println("|__________________|____________|_________________|________________|");
        extras.println("|"+ extras.verTamMax_table(this.nome, 18) + "|" + extras.verTamMax_table(String.format("%.00f", this.defesa), 12) + "|" + extras.verTamMax_table(this.evasion_bonus, 17) + "|" + extras.verTamMax_table(this.peso, 16) + "|");
        extras.println("|__________________|____________|_________________|________________|");
    }

    public static void listArmaduras(){
        extras.println("___________________________________________________________________________");
        extras.println("|  id  |       Nome       |   Defesa   |   BonusEvasao   |      Peso      |");
        extras.println("|______|__________________|____________|_________________|________________|");
        for(int i = 0; i < handler.armor.size(); i++){
            extras.println("|"+extras.verTamMax_table(i, 6)+"|"+ extras.verTamMax_table(handler.armor.get(i).getNome(), 18) + "|" + extras.verTamMax_table(String.format("%.00f", handler.armor.get(i).getDefesa()), 12) + "|" + extras.verTamMax_table(handler.armor.get(i).getEvasionB(), 17) + "|" + extras.verTamMax_table(handler.armor.get(i).getPeso(), 16) + "|");
            extras.println("|______|__________________|____________|_________________|________________|");
        }
    }
    
    public double getDefesa(){return this.defesa;}
    public double getEvasionB(){return this.evasion_bonus;}
}
