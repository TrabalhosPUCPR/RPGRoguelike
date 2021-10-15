import adicionais.configuracao;
import adicionais.extras;
import adicionais.handler;

public class Main {
    public static void main(String[] args) throws Exception {
        handler.carregarJogo();
        while(true){
            menuInicial();
        }
    }
/*
   ___                   
  |_  |                  
    | | ___   ____  ___  
    | |/ _ \ / _` |/ _ \ 
/\__/ / (_) | (_| | (_) |
\____/ \___/ \__, |\___/ 
              __/ |      
             |___/   

https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20

mt legal esse site kk
*/


    static void menuInicial(){
        extras.console_clear();
        extras.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
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

