import adicionais.configuracao;
import adicionais.extras;
import adicionais.handler;
import adicionais.janela;
import ascii.ascii;

public class Main {
    public static void main(String[] args) throws Exception {
        new janela();
        handler.carregarJogo();
        menuInicial();
    }

        static void menuInicial(){
            while(true){
                ascii.printMonstroAsciipeloNome("logoepico", false);
                extras.console_clear();
                ascii.printAsciipeloNome("logo");
                //ascii.printMonstroAsciipeloNome("homem", true);
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
                    default:
                    break;
                }
            }
        }
}

