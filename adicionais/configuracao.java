package adicionais;

import entidades.classes;
import entidades.inimigos;
import itens.armaduras;
import itens.armas;
import itens.consumiveis;
import itens.itensDef;
import itens.itensMisc;
import itens.itensOfen;

public class configuracao {
    public static void configurar(){
        extras.println("");
        extras.println_bonito("Digite a opcao que gostaria de configurar", 500, 500);
        boolean res = false;
        while(res == false){
            extras.println("");
            extras.println("Classes");
            extras.println("Armas");
            extras.println("Armaduras");
            extras.println("Acessorios");
            extras.println("Consumiveis");
            extras.println("Monstros");
            extras.println("Voltar");
            extras.println("");
            switch(extras.inputS().toLowerCase()){
                case "classes":
                    classes.configClasses();
                break;
                case "armas":
                    armas.configArmas();
                break;
                case "armaduras":
                    armaduras.configArmaduras();
                break;
                case "acessorios":
                    extras.println("");
                    extras.println_bonito("Qual o tipo de acessorio que gostaria de configurar", 500, 500);
                    extras.println("");
                    extras.println("Ofensivo");
                    extras.println("Defensivo");
                    extras.println("Misc");
                    switch(extras.inputS().toLowerCase()){
                        case "ofensivo":
                            itensOfen.configItemOfen();
                            break;
                        case "defensivo":
                            itensDef.configItemDef();
                            break;
                        case "misc":
                            itensMisc.configItemMisc();
                            break;
                    }
                break;
                case "consumiveis":
                    consumiveis.configConsu();
                break;
                case "monstros":
                    extras.println("");
                    extras.println_bonito("Qual o tipo de monstro que gostaria de configurar", 500, 500);
                    extras.println("");
                    extras.println("Comum");
                    extras.println("Incomum");
                    extras.println("Boss");
                    switch(extras.inputS().toLowerCase()){
                        case "comum":
                            inimigos.configInimigos(0);
                            break;
                        case "incomum":
                            inimigos.configInimigos(1);
                            break;
                        case "boss":
                            inimigos.configInimigos(2);
                            break;
                    }
                break;
                case "voltar":
                    res = true;
                break;
                default:
                    extras.println("");
                    extras.println_bonito("Digite uma opcao valida...", 500, 500);
                break;
            }
        }
    }
}
