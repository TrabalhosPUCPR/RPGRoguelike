package itens;

public class itensMisc extends itens{

    double buff_evasion;

    public itensMisc(String nome, String desc, int forca, int defesa, int destreza, double buff_evasion, double dinheiro){
        this.nome = nome;
        this.desc = desc;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.buff_evasion = buff_evasion;
        this.dinheiro = dinheiro;
    }

    public double getBuffEva(){return this.buff_evasion;}

}
