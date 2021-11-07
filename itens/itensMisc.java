package itens;

import adicionais.extras;
import adicionais.handler;
import fases.fases;

public class itensMisc extends itens{

    double buff_evasion;

    static int[] itensMisc_drop;
    static int[] qual_itensMisc_drop;

    public itensMisc(String nome, String desc, int forca, int defesa, int destreza, double buff_evasion, double dinheiro, int raridade){
        this.nome = nome;
        this.desc = desc;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.buff_evasion = buff_evasion;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public static int dropItenMisc(){
        return itensMisc_drop[extras.rng_int(0, itensMisc_drop.length)];
    }

    public static int dropItenMiscRaro(){
        return qual_itensMisc_drop[extras.rng_int(0, qual_itensMisc_drop.length)];
    }

    public static void setDropRateItensMisc(){
        int[] itensMisc_drop = new int[]{}; 
        for(int i = 0; i < handler.itemMisc.size(); i++){
            if(handler.itemMisc.get(i).getRaridade()<=fases.fase_atual+1 && handler.itemMisc.get(i).getRaridade()>=fases.fase_atual){
                itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                if(handler.itemMisc.get(i).getRaridade()==fases.fase_atual){
                    itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                }
            }
        }
        itensMisc.itensMisc_drop = itensMisc_drop;
        setDropRateItensMiscRaro();
    }

    static void setDropRateItensMiscRaro(){
        int[] itensMisc_drop = new int[]{}; 
        for(int i = 0; i < handler.itemMisc.size(); i++){
            if(handler.itemMisc.get(i).getRaridade()<=fases.fase_atual+2 && handler.itemMisc.get(i).getRaridade()>fases.fase_atual){
                itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                if(handler.itemMisc.get(i).getRaridade()==fases.fase_atual+1){
                    itensMisc_drop = extras.arrayintAdd(itensMisc_drop, i);
                }
            }
        }
        itensMisc.qual_itensMisc_drop = itensMisc_drop;
    }

    static void listarItensMisc(){
        extras.println("______________________________________________________________________________________________________________________________________________________________________");
        extras.println("|   Id   |          Nome          |                    Desc                    |   Forca   |   Defesa   |   Destreza   |   BonusEvasao   |   Valor$   |   Raridade   |");
        extras.println("|________|________________________|____________________________________________|___________|____________|______________|_________________|____________|______________|");
        for(int i = 0; i < handler.itemMisc.size();i++){
            extras.println("|"+ extras.verTamMax_table(i, 8)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getNome(), 24)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getDesc(), 44)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getForca(), 11)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getDefesa(), 12)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getDestreza(), 14)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getBuffEva(), 17)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getDinheiro(), 12)
            +"|"+ extras.verTamMax_table(handler.itemMisc.get(i).getRaridade(), 14)
            +"|");
            extras.println("|________|________________________|____________________________________________|___________|____________|______________|_________________|____________|______________|");
        }
    }

    public static void configItemMisc(){
        extras.println("");
        listarItensMisc();
        extras.println("");
        extras.println_bonito("Adicionar um novo Acessorio Misc ou editar um ja existente? (para apagar um acessorio misc, edite a raridade para 0)", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarItenMisc();
            break;
            case "editar":
                editarItenMisc();
            break;
            default:
            break;
        }

    } 

    static void criarItenMisc(){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome do acessorio misc?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a descricao do acessorio misc?", 500, 500);
            String desc = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a forca do acessorio misc?", 500, 500);
            int forca = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a defesa do acessorio misc?", 500, 500);
            int defesa = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a destreza do acessorio misc?", 500, 500);
            int destreza = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera o bonus de evasao do acessorio misc?", 500, 500);
            double buff_eva = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera o valor do acessorio misc?", 500, 500);
            double valor = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera a raridade do acessorio misc?", 500, 500);
            int raridade = extras.inputI();
            handler.itemMisc.add(new itensMisc(nome, desc, forca, defesa, destreza, buff_eva, valor, raridade));
            extras.println("");
            extras.println_bonito("Acessorio misc criado com sucesso", 500, 1000);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar acessorio misc: "+e);
        }
    }
    static void editarItenMisc(){
        try{
            extras.println("");
            extras.println_bonito("Digite o ID do acessorio misc que gostaria de editar: ", 500, 500);
            int i = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual valor do acessorio misc "+handler.itemMisc.get(i).getNome()+" voce gostaria de editar?", 500, 500);
            extras.println_bonito("Nome\nDesc\nForca\nDefesa\nDestreza\nBEvasao\nValor\nRaridade", 500, 500);
            switch(extras.inputS().toLowerCase()){
                case "nome":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo nome da acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setNome(extras.inputS());
                break;
                case "desc":
                    extras.println("");
                    extras.println_bonito("Qual sera a descricao do acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setDesc(extras.inputS());
                break;
                case "forca":
                    extras.println("");
                    extras.println_bonito("Qual sera a forca do acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setForca(extras.inputI());
                break;
                case "defesa":
                    extras.println("");
                    extras.println_bonito("Qual sera a defesa do acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setDefesa(extras.inputI());
                break;
                case "destreza":
                    extras.println("");
                    extras.println_bonito("Qual sera a destreza do acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setDestreza(extras.inputI());
                break;
                case "bevasao":
                    extras.println("");
                    extras.println_bonito("Qual sera o bonus de evasao do acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setBuffEva(extras.inputD());
                break;
                case "valor":
                    extras.println("");
                    extras.println_bonito("Qual sera o valor $ do acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setValor(extras.inputD());
                break;
                case "raridade":
                    extras.println("");
                    extras.println_bonito("Qual sera a raridade do acessorio misc " + handler.itemMisc.get(i).getNome() + "?", 500, 500);
                    handler.itemMisc.get(i).setRaridade(extras.inputI());
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida", 1000, 500);
                break;
            }
            extras.println("");
            extras.println_bonito(handler.itemMisc.get(i).getNome() + " editado com sucesso!", 500, 700);
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar acessorio misc: "+e);
        }
    }

    public void setBuffEva(double n){this.buff_evasion = n;}
    public double getBuffEva(){return this.buff_evasion;}

}
