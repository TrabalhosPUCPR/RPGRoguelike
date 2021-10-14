package itens;

public class itensDef extends itens{

    int defesa;
    int destreza;

    public itensDef(String nome, String desc, int defesa, int destreza, double dinheiro){
        this.nome = nome;
        this.desc = desc;
        this.defesa = defesa; 
        this.destreza = destreza;
        this.dinheiro = dinheiro;
    }

    
}
