package adicionais;

import java.util.Random;
import java.util.Scanner;

public class extras {

    static Scanner input = new Scanner(System.in);

    public extras(){}

    public static String inputS(){
        System.out.print("R: ");
        String res = input.nextLine();
        return res;
    }

    public static void delay(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int rng_int(int min, int max){
        max -= 1;
        int aleatorio = new Random().nextInt(max - min + 1) + min;
        return aleatorio;
    }

    public static double rng_double(double max){
        double aleatorio = new Random().nextDouble();
        aleatorio = aleatorio*max;
        return aleatorio;
    }

    public static void console_clear(){
        for(int i = 0; i < 100; i++){ // eu queria que tivesse uma forma melhor de limpa o console :(
            System.out.println("");
        }
    }

    public static void println(String msg){ //so pq e mais facil de digita
        System.out.print(msg);
        System.out.println("");
    }
    public static void print(String msg){
        System.out.println(msg);
    }
    public static void println_bonito(String msg, int tempo, int fim){
        int n = 0;
        for(int i = 0; i < msg.length();i++){
            n++; // primeiro ele ve quantos digitos existem na mensagem pra dividi o tempo por isso
        }
        tempo = tempo/n;
        for(int i = 0; i < n;i++){
            System.out.print(msg.charAt(i));
            delay(tempo);
        }
        delay(fim);
        print("");
    }

    public static String verTamMax_table(Object texto, int max){
        String tex = texto.toString();
        String tex2;
        boolean rev = false;
        while(tex.length() < max){
            if(rev == false){
                rev = true;
                tex = tex + " ";
            }else{
                rev = false;
                tex = " " + tex;
            }
        }
        while(tex.length() > max){
            tex2 = "";
            for(int i = 0; i < tex.length() - 1; i++){
                tex2 += tex.charAt(i); 
            }
            tex = tex2;
        }
        return tex;
    }

}
