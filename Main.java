import adicionais.configuracao;
import adicionais.extras;
import adicionais.handler;
import ascii.ascii;

public class Main {
    public static void main(String[] args) throws Exception {
        handler.carregarJogo();
        while(true){
            menuInicial();
        }
    }

    static void menuInicial(){
        extras.console_clear();
        ascii.printAsciipeloNome("logo");
        extras.println("");
        extras.println_bonito("jogar", 200, 30);
        extras.println_bonito("config", 200, 30);
        switch(extras.inputS().toLowerCase()){
            case "jogar":
                handler.NovoJogo();
            break;
            case "config":
                configuracao.configurar();
            break;
        }
    }
}

