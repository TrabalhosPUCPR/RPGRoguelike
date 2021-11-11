package entidades;

import adicionais.extras;
import adicionais.handler;
import itens.armaduras;
import itens.armas;

public class classes extends player{

    public String classe_desc;

    public classes(String nome, String classe, int arma_equip, int armor_equip, int vidamax, int forca, int defesa, int destreza, int tipoArma) {
        super(nome, classe, arma_equip, armor_equip, vidamax, forca, defesa, destreza, tipoArma);
        this.nome = nome;
        this.classe_desc = classe;
        this.arma_equip = arma_equip;
        this.armor_equip = armor_equip;
        this.vidamax = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.tipoArma = tipoArma;
    }

    public static void printClasses(){
        extras.println("_____________________________________________________________________");
        extras.println("|                |          |           |            |              |");
        extras.println("|      Nome      |   Vida   |   Forca   |   Defesa   |   Destreza   |");
        extras.println("|________________|__________|___________|____________|______________|");
        extras.println("|                |          |           |            |              |");
        for(int i = 0;  i < handler.classe.size(); i++){
            if(!handler.classe.get(i).getNome().equals("Dev")){
                extras.println("|"+ extras.verTamMax_table(handler.classe.get(i).getNome(), 16) +"|"+ extras.verTamMax_table(String.format("%.00f", handler.classe.get(i).getVida()), 10) +"|"+ extras.verTamMax_table(handler.classe.get(i).getForca(), 11) +"|"+ extras.verTamMax_table(String.format("%.00f", handler.classe.get(i).getDefesa()), 12) +"|"+ extras.verTamMax_table(handler.classe.get(i).getDestreza(), 14) +"|");
            }
        }
        extras.println("|________________|__________|___________|____________|______________|");
        extras.println("");
    }

    public static void configClasses(){
        extras.println("");
        printClasses();
        extras.println("");
        extras.println_bonito("Adicionar uma nova classe ou editar uma ja existente?", 500, 500);
        switch(extras.inputS().toLowerCase()){
            case "adicionar":
                criarClasse();
            break;
            case "editar":
                editarClasse();
            break;
            default:
            break;
        }
    }

    private static void criarClasse(){
        try{
            extras.println("");
            extras.println_bonito("Qual sera o nome da classe?", 500, 500);
            String nome = extras.inputS();
            extras.println("");
            extras.println_bonito("Qual sera a descricao da classe?", 500, 500);
            String desc = extras.inputS();
            armas.listArmas();
            extras.println("");
            extras.println_bonito("Qual sera o ID da arma inicial da classe?", 500, 500);
            int arma_ini = extras.inputI();
            armaduras.listArmaduras();
            extras.println("");
            extras.println_bonito("Qual sera o ID da armadura inicial da classe?", 500, 500);
            int armor_ini = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a vida maxima(inicial) da classe?", 500, 500);
            int vidamax = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a forca da classe?", 500, 500);
            int forca = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a defesa da classe?", 500, 500);
            int defesa = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera a destreza da classe?", 500, 500);
            int destreza = extras.inputI();
            extras.println("");
            extras.println_bonito("Qual sera os tipos de arma que a classe podera usar?", 500, 200);
            extras.println_bonito("0 = Armas de curto alcance\n1 = Armas de longo alcance\n2 = Todos", 500, 500);
            int Tarma = extras.inputI();
            handler.classe.add(new classes(nome, desc, arma_ini, armor_ini, vidamax, forca, defesa, destreza, Tarma));
            extras.println_bonito("Classe criado com sucesso", 500, 200);
        }
        catch(Exception e){
            extras.println("");
            extras.println("Falha ao criar classe: "+e);
        }
    }

    private static void editarClasse(){
        try{
            extras.println("");
            extras.println_bonito("Digite o nome da classe que gostaria de editar: ", 500, 500);
            String res = extras.inputS();
            int classe_ok = 0;
            for(int i = 0; i < handler.classe.size();i++){
                if(handler.classe.get(i).getNome().toLowerCase().equals(res.toLowerCase())){
                    extras.println("");
                    extras.println_bonito("O que voce gostaria de editar do(a) " + handler.classe.get(i).getNome() + "?", 500, 500);
                    extras.println_bonito("Nome\nDescricao\nIDarma\nIDarmadura\nVidaMax\nForca\nDefesa\nDestreza\nTipoDeArma", 500, 500);
                    switch(extras.inputS().toLowerCase()){
                        case "nome":
                            extras.println("");
                            extras.println_bonito("Qual sera o novo nome da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setNome(extras.inputS());
                        break;
                        case "descricao":
                            extras.println("");
                            extras.println_bonito("Qual sera a nova descricao da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setDesc(extras.inputS());
                        break;
                        case "idarma":
                            armas.listArmas();
                            extras.println("");
                            extras.println_bonito("Qual sera a nova arma inicial da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setArmaEquip(extras.inputI());
                        break;
                        case "idarmadura":
                            armaduras.listArmaduras();
                            extras.println("");
                            extras.println_bonito("Qual sera a nova armadura inicial da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setArmorEquip(extras.inputI());
                        break;
                        case "vidamax":
                            extras.println("");
                            extras.println_bonito("Qual sera a vida maxima(inicial) da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setVidamax(extras.inputI());
                        break;
                        case "forca":
                            extras.println("");
                            extras.println_bonito("Qual sera a forca da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setForca(extras.inputI());
                        break;
                        case "defesa":
                            extras.println("");
                            extras.println_bonito("Qual sera a defesa da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setDefesa(extras.inputI());
                        break;
                        case "destreza":
                            extras.println("");
                            extras.println_bonito("Qual sera a destreza da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            handler.classe.get(i).setDestreza(extras.inputI());
                        break;
                        case "tipodearma":
                            extras.println("");
                            extras.println_bonito("Qual sera o tipo de arma equipavel da classe " + handler.classe.get(i).getNome() + "?", 500, 500);
                            extras.println_bonito("0 = Armas de curto alcance\n1 = Armas de longo alcance\n2 = Todos", 500, 500);
                            handler.classe.get(i).setTipoArma(extras.inputI());
                        break;
                        default:
                            extras.println("");
                            extras.println_bonito("Digite uma opcao valida", 1000, 500);
                        break;
                    }
                    classe_ok = 1;
                    break;
                }
            }
            if(classe_ok == 0){
                extras.println_bonito("Por favor digite uma classe válida...", 1000, 500);
            }
        }catch(Exception e){
            extras.println("");
            extras.println("Falha ao editar classe: "+e);
        }
    }

}
