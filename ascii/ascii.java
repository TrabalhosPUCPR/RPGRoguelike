package ascii;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import adicionais.extras;
import adicionais.handler;

/*
https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20

mt legal esse site kk
*/

public class ascii {

    Scanner txt;
    String nome;
    private File arquivo;

    public ascii(String nome, File txt) throws FileNotFoundException{
        this.nome = nome;
        this.txt = new Scanner(txt);
        this.arquivo = txt;
    }

    void printAscii() throws FileNotFoundException{
        while(this.txt.hasNextLine()){
            extras.println(txt.nextLine());
        }
        this.txt = new Scanner(this.arquivo); // tenq resetar se nao o print ali em cima nao funciona dps da primeira vez
    }

    public static void printAsciipeloNome(String n){
        for(int i = 0; i < handler.ascii.size(); i++){
            if(handler.ascii.get(i).getNome().equals(n)){
                try {
                    handler.ascii.get(i).printAscii();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public String getNome(){return this.nome;}
}
