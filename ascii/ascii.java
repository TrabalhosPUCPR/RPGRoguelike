package ascii;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import adicionais.extras;
import adicionais.handler;
import adicionais.janela;

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
            String linha = txt.nextLine();
            extras.println(linha);
        }
        this.txt = new Scanner(this.arquivo); // tenq resetar se nao o print ali em cima nao funciona dps da primeira vez
    }
    void printMonstroAscii() throws FileNotFoundException{

        while(this.txt.hasNextLine()){
            String linha = txt.nextLine();
            janela.printAsciiMonstro(linha);
        }
        this.txt = new Scanner(this.arquivo); // tenq resetar se nao o print ali em cima nao funciona dps da primeira vez
    }

    public static void printAsciipeloNome(String n){
        for(int i = 0; i < handler.asciiart.size(); i++){
            if(handler.asciiart.get(i).getNome().equals(n)){
                try {
                    handler.asciiart.get(i).printAscii();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void printMonstroAsciipeloNome(String n, boolean ln){
        if(ln){
            janela.clearJmonsAscii(true);
        }else{
            janela.clearJmonsAscii(false);
        }
        for(int i = 0; i < handler.asciiart.size(); i++){
            if(handler.asciiart.get(i).getNome().equals(n)){
                try {
                    handler.asciiart.get(i).printMonstroAscii();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public String getNome(){return this.nome;}
}
