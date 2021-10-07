package itens;

public class itens {

    String nome;
    String peso;
    String tipo;

    public int getPesoDes(){
        int des = 0;
        switch(this.peso){
            case "omega leve":
                des = 5;
                break;
            case "super leve":
                des = 2;
                break;
            case "leve":
                des = 0;
                break;
            case "pesado":
                des = -2;
                break;
            case "super pesado":
                des = -5;
                break;
        }
        return des;
    }
    
    public String getNome(){return this.nome;}
    public String getTipo(){return this.tipo;}

}

