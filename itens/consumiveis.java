package itens;

import adicionais.extras;
import adicionais.handler;
import entidades.inimigos;
import fases.fases;

public class consumiveis extends itens{
    int acaoId;
    double valor;

    static int[] consu_drop;


    public consumiveis(String nome, String desc, double valor, int acaoId, double dinheiro, int raridade){
        this.nome = nome;
        this.desc = desc;
        this.valor = valor;
        this.acaoId = acaoId;
        this.valor = valor;
        this.dinheiro = dinheiro;
        this.raridade = raridade;
    }

    public void usar(){
        /*
        (estes sao no valordoitem*statusasermodificado)
            0 = se curar
            1 = aumentar forca 
            2 = aumentar destreza
            3 = aumentar defesa

        (estes dao dano igual ao numero do valor, defesa do inimigo nao muda)
            4 = causar dano verdadeiro no inimigo
        */
        switch(this.acaoId){
            case 0:
                extras.println("");
                extras.println_bonito("Voce se curou " + String.format("%.0f", handler.jogador.getVidamax()*this.valor) + " de vida!", 600, 500);
                handler.jogador.curar(handler.jogador.getVidamax()*this.valor);
                break;
            case 1:
                extras.println("");
                extras.println_bonito("Voce aumentou sua forca em " + String.format("%.02f", handler.jogador.getForca()*this.valor) + "!", 600, 500);
                handler.jogador.aumentarstat(handler.jogador.getForca()*this.valor, "forca");
                break;
            case 2:
                extras.println("");
                extras.println_bonito("Voce aumentou sua destreza em " + String.format("%.02f", handler.jogador.getDestreza()*this.valor) + "!", 600, 500);
                handler.jogador.aumentarstat(handler.jogador.getDestreza()*this.valor, "destreza");
                break;
            case 3:
                extras.println("");
                extras.println_bonito("Voce aumentou sua defesa em " + String.format("%.02f", handler.jogador.getDefesa()*this.valor) + "!", 600, 500);
                handler.jogador.aumentarstat(handler.jogador.getDefesa()*this.valor, "defesa");
                break;
            case 4:
                extras.println("");
                extras.println_bonito("Voce Jogou " + this.nome + " no " + inimigos.getInimigo(inimigos.indexmonstro, inimigos.tipomonstro).getNome() + "!", 600, 500);
                inimigos.getInimigo(inimigos.indexmonstro, inimigos.tipomonstro).levar_dano(this.valor, handler.jogador.getDestreza(), "leve", true);
                break;
        }
    }

    public static int dropConsu(){ 
        return consu_drop[extras.rng_int(0, consu_drop.length)];
    }

    public static void setDropRateconsu(){
        int[] consu_drop = new int[]{}; 
        for(int i = 0; i < handler.consu.size(); i++){
            if(handler.consu.get(i).getRaridade()<=fases.fase_atual+1 && handler.consu.get(i).getRaridade()>fases.fase_atual-3){
                consu_drop = extras.arrayintAdd(consu_drop, i);
                if(handler.consu.get(i).getRaridade()==fases.fase_atual){
                    consu_drop = extras.arrayintAdd(consu_drop, i);
                }
            }
        }
        consumiveis.consu_drop = consu_drop;
    }

    static void listarConsumiveis(){
        extras.println("_______________________________________________________________________________________________________________________________________");
        extras.println("|   Id   |           Nome           |                    Acao                    |   Valor   |   IdAcao   |   Valor$   |   Raridade   |");
        extras.println("|________|__________________________|____________________________________________|___________|____________|____________|______________|");
        for(int i = 0; i < handler.consu.size();i++){
            extras.println("|"+ extras.verTamMax_table(i, 8)
            + "|" + extras.verTamMax_table(handler.consu.get(i).getNome(), 26)
            + "|" + extras.verTamMax_table(handler.consu.get(i).getDesc(), 44) 
            + "|" + extras.verTamMax_table(handler.consu.get(i).getValor(), 11) 
            + "|" + extras.verTamMax_table(handler.consu.get(i).getIdAcao(), 12)
            + "|" + extras.verTamMax_table(handler.consu.get(i).getDinheiro(), 12)
            + "|" + extras.verTamMax_table(handler.consu.get(i).getRaridade(), 14)
            + "|");
            extras.println("|________|__________________________|____________________________________________|___________|____________|____________|______________|");
        }
    }

    public static void configConsu(){
        extras.println("");
        listarConsumiveis();
        extras.println("");
        extras.println_bonito("Adicionar um novo consumivel ou editar um ja existente? (para apagar um consumivel, edite a raridade para 0)", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarConsu();
            break;
            case "editar":
                editarConsu();
            break;
        }
    }

    static void criarConsu(){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome do consumivel?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a descricao do consumivel?", 500, 500);
            String acao = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera o id da acao do consumivel?", 500, 500);
            extras.println_bonito("0 = curar\n1 = aumentar forca\n2 = aumentar destreza\n3 = aumentar defesa\n4 = dar dano verdadeiro", 500, 500);
            int acaoId = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera o valor da acao do consumivel?", 500, 500);
            extras.println_bonito("Itens que aumentam status multiplica este valor pelo status atual\nItens de dano verdadeiro dao dano igual a este valor independente da defesa do inimigo", 500, 500);
            double valor = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera o valor$ do consumivel?", 500, 500);
            double dinheiro = extras.inputD();
            extras.println("");
            extras.println_bonito("Qual sera a raridade do consumivel?", 500, 500);
            int raridade = extras.inputI();
            handler.consu.add(new consumiveis(nome, acao, valor, acaoId, dinheiro, raridade));
            extras.println("");
            extras.println_bonito("Consumivel criado com sucesso", 500, 1000);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar consumivel: "+e);
        }
    }
   static void editarConsu(){
        try{
            extras.println("");
            extras.println_bonito("Digite o ID do consumivel que gostaria de editar: ", 500, 500);
            int i = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual valor do consumivel "+handler.consu.get(i).getNome()+" voce gostaria de editar?", 500, 500);
            extras.println_bonito("Nome\nDesc\nValorAcao\nIdAcao\nValor\nRaridade", 500, 500);
            switch(extras.inputS().toLowerCase()){
                case "nome":
                    extras.println("");
                    extras.println_bonito("Qual sera o novo nome da consumivel " + handler.consu.get(i).getNome() + "?", 500, 500);
                    handler.consu.get(i).setNome(extras.inputS());
                break;
                case "desc":
                    extras.println("");
                    extras.println_bonito("Qual sera a descricao do consumivel " + handler.consu.get(i).getNome() + "?", 500, 500);
                    handler.consu.get(i).setDesc(extras.inputS());
                break;
                case "valoracao":
                    extras.println("");
                    extras.println_bonito("Qual sera o valor da acao do consumivel " + handler.consu.get(i).getNome() + "?", 500, 500);
                    extras.println_bonito("Itens que aumentam status multiplica este valor pelo status atual\nItens de dano verdadeiro dao dano igual a este valor independente da defesa do inimigo", 500, 500);
                    handler.consu.get(i).setValorAcao(extras.inputD());
                break;
                case "idacao":
                    extras.println("");
                    extras.println_bonito("Qual sera o Id da acao do consumivel " + handler.consu.get(i).getNome() + "?", 500, 500);
                    extras.println_bonito("0 = curar\n1 = aumentar forca\n2 = aumentar destreza\n3 = aumentar defesa\n4 = dar dano verdadeiro", 500, 500);
                    handler.consu.get(i).setIdAcao(extras.inputI());
                break;
                case "valor":
                    extras.println("");
                    extras.println_bonito("Qual sera o valor$ do consumivel " + handler.consu.get(i).getNome() + "?", 500, 500);
                    handler.consu.get(i).setValor(extras.inputI());
                break;
                case "raridade":
                    extras.println("");
                    extras.println_bonito("Qual sera a raridade do consumivel " + handler.consu.get(i).getNome() + " ?", 500, 500);
                    handler.consu.get(i).setRaridade(extras.inputI());
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida", 1000, 500);
                break;
            }
            extras.println("");
            extras.println_bonito(handler.consu.get(i).getNome() + " editado com sucesso!", 500, 700);
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar consumivel: "+e);
        }
    }
    
    public int getIdAcao() {return this.acaoId;}
    public double getValor() {return this.valor;}
    public void setIdAcao(int n) {this.acaoId = n;}
    public void setValorAcao(double valor) {this.valor = valor;}
}
