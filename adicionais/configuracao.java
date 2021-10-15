package adicionais;

import entidades.classes;
import itens.armas;

public class configuracao {
    public static void configurar(){
        extras.println("");
        extras.println_bonito("Digite a opcao que gostaria de configurar", 500, 500);
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
            break;
            case "acessorios":
            break;
            case "monstros":
            break;
            case "voltar":
            break;
            default:
                extras.println("");
                extras.println_bonito("Digite uma opcao valida...", 500, 500);
            break;
        }
    }
}
