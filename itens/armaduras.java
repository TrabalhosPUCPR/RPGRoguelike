package itens;

import adicionais.extras;

public class armaduras extends itens{
    double evasion_bonus;
    double defesa;

    public armaduras(String nome, double defesa, double evasion_bonus, String peso){
        this.nome = nome;
        this.defesa = defesa;
        this.evasion_bonus = evasion_bonus;
        this.peso = peso;
    }

    public void printStats(){
        extras.println("____________________________________________________________________");
        extras.println("|       Nome       |   Defesa   |   BonusEvasao   |      Peso      |");
        extras.println("|__________________|____________|_________________|________________|");
        extras.println("|"+ extras.verTamMax_table(this.nome, 18) + "|" + extras.verTamMax_table(String.format("%.00f", this.defesa), 12) + "|" + extras.verTamMax_table(this.evasion_bonus, 17) + "|" + extras.verTamMax_table(this.peso, 16) + "|");
        extras.println("|__________________|____________|_________________|________________|");
    }
    
    public double getDefesa(){return this.defesa;}
    public double getEvasionB(){return this.evasion_bonus;}
}
