package itens;

public class itensOfen extends itens{

    int forca;
    int destreza;

    public itensOfen(String nome, String desc, int forca, int destreza, double dinheiro){
        this.nome = nome;
        this.desc = desc;
        this.forca = forca; 
        this.destreza = destreza;
        this.dinheiro = dinheiro;
    }
}
