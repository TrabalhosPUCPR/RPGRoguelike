package itens;

import adicionais.extras;
import adicionais.handler;

public class armas extends itens{

    int ataque;

    public armas(String nome, int ataque, String tipo, String peso, double dinheiro){
        this.nome = nome;
        this.ataque = ataque; 
        this.tipo = tipo;
        this.peso = peso;
        this.dinheiro = dinheiro;
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

    public void printStats(){
        extras.println("_____________________________________________________________");
        extras.println("|       Nome       |   Ataque   |   Tipo   |      Peso      |");
        extras.println("|__________________|____________|__________|________________|");
        extras.println("|"+ extras.verTamMax_table(this.nome, 18) + "|" + extras.verTamMax_table(this.ataque, 12) + "|" + extras.verTamMax_table(this.tipo, 10) + "|" + extras.verTamMax_table(this.peso, 16) + "|");
        extras.println("|__________________|____________|__________|________________|");
    }
    
    public int getAtaque(){return this.ataque;}
    public void setAtaque(int n){this.ataque = n;}
}
