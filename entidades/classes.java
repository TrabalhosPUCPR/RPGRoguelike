package entidades;

import adicionais.extras;
import adicionais.handler;

public class classes extends player{

    public String classe_desc;

    public classes(String nome, String classe, int arma_equip, int armor_equip, int vidamax, int forca, int defesa, int destreza) {
        super(nome, classe, arma_equip, armor_equip, vidamax, forca, defesa, destreza);
        this.nome = nome;
        this.classe_desc = classe;
        this.arma_equip = arma_equip;
        this.armor_equip = armor_equip;
        this.vidamax = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
    }

    public static void printClasses(){
        extras.println("_____________________________________________________________________");
        extras.println("|                |          |           |            |              |");
        extras.println("|      Nome      |   Vida   |   Forca   |   Defesa   |   Destreza   |");
        extras.println("|________________|__________|___________|____________|______________|");
        extras.println("|                |          |           |            |              |");
        for(int i = 0;  i < handler.classe.size(); i++){
            extras.println("|"+ extras.verTamMax_table(handler.classe.get(i).getNome(), 16) +"|"+ extras.verTamMax_table(String.format("%.00f", handler.classe.get(i).getVida()), 10) +"|"+ extras.verTamMax_table(handler.classe.get(i).getForca(), 11) +"|"+ extras.verTamMax_table(String.format("%.00f", handler.classe.get(i).getDefesa()), 12) +"|"+ extras.verTamMax_table(handler.classe.get(i).getDestreza(), 14) +"|");
        }
        extras.println("|________________|__________|___________|____________|______________|");
        extras.println("");
    }

}
