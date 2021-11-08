package adicionais;

import java.util.Random;
import java.util.Scanner;

public class extras { // essa classe serve para colocar uns codigos para fazer umas coisas meio q padrao pelo codigo todo, nao tem um lugar especifico pra colocar eles

    static Scanner input = new Scanner(System.in);

    public static String inputS(){
        String res = "";
        if(janela.Ativo){
            res = janela.JinputS();
        }else{
            System.out.print("R: ");
            res = input.nextLine();
        }
        return res;
    }

    public static int inputI(){
        int res = 0;
        if(janela.Ativo){
            res =  Integer.parseInt(janela.JinputS());
        }else{
            System.out.print("R: ");
            res = Integer.parseInt(input.nextLine());;
        }
        return res;
    }

    public static double inputD(){
        double res = 0;
        if(janela.Ativo){
            res = Double.parseDouble(janela.JinputS());
        }else{
            System.out.print("R: ");
            res = Double.parseDouble(input.nextLine());
        }
        return res;
    }

    public static void delay(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static Random random = new Random();
    public static int rng_int(int min, int max){
        int aleatorio = random.nextInt(max - min) + min;
        return aleatorio;
    }

    public static double rng_double(double min, double max){
        double aleatorio = random.nextDouble();
        aleatorio = min + (max - min) * aleatorio;
        return aleatorio;
    }

    public static boolean rng_bool(){
        boolean aleatorio = random.nextBoolean();
        return aleatorio;
    }

    public static void console_clear(){
        janela.clearJanela();
        for(int i = 0; i < 100; i++){ // eu queria que tivesse uma forma melhor de limpa o console :(
            System.out.println("");
        }
    }

    public static void println(Object ms){ //so pq e mais facil de digita
        String msg = ms.toString();
        janela.printJanela(msg);
        janela.printlnJanela("");
        System.out.print(msg);
        System.out.println("");
    }
    public static void print(Object ms){
        String msg = ms.toString();
        System.out.println(msg);
        janela.printlnJanela(msg);
    }
    public static void println_bonito(Object ms, int tempo, int fim){
        String msg = ms.toString();
        int n = 0;
        for(int i = 0; i < msg.length();i++){
            n++; // primeiro ele ve quantos digitos existem na mensagem pra dividi o tempo por isso
        }
        tempo = tempo/n;
        for(int i = 0; i < n;i++){
            janela.printJanela(msg.charAt(i));
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

    public static boolean simNao(){
        while(true){
            print("");
            println_bonito("Digite 'sim' ou 'nao'...", 400, 200);
            String res = inputS();
            switch(res.toLowerCase()){
                case "sim":
                    return true;
                case "nao":
                    return false;
                default:
                    print("");
                    println_bonito("Digite uma resposta valida", 500, 400);
            }
        }
    }

    public static void printIntArray(int[] arr){
        String texto = "{";
        for(int i = 0; i < arr.length; i++){
            texto+=arr[i];
            texto+=",";
        }
        texto+="}";
        print(texto);
    }

    public static int[] arrayintAdd(int [] arr, int n){ //eu podia usar um arraylist? podia, mas qual a graca?
        int[] Narray = new int[]{};
        if(arr.length < 1){
            Narray = new int[1];
            Narray[0] = n;
        }else{
            Narray = new int[arr.length+1];
            for (int i = 0; i < arr.length; i++){
                Narray[i] = arr[i];
            }
            Narray[Narray.length-1] = n;
        }
        return Narray;
    }

    public static int[] aumentarTamArrayInt(int[] array, int n){
        int[] Novo_array = new int[array.length+n];
        try{
            for(int i = 0;i<array.length;i++){
                Novo_array[i] = array[i];
            }
            return Novo_array;
        }catch(Exception e){
            println("Erro ao aumentar o tamanho do array: "+e);
        }
        return Novo_array;
    }

    public static int[] removeIndex(int[] array, int index){
        int[] a = {};
        for(int i = 0; i < array.length; i++){
            if(i != index){a[i] = array[i];}
        }
        return a;
    }

    public static String intArraytoString(int[] array){
        String texto = "";
        for(int i = 0; i < array.length;i++){
            texto += array[i];
            if(i < array.length-1){
                texto += ", ";
            }
        }
        return texto;
    }

    public static int[] stringtoIntArray(String texto){ // pse eu acho legal fazer coisa aleatoria com array
        int[] arr = new int[]{};
        String nX = "";
        for(int i = 0; i < texto.length();i++){
            if(Character.toString(texto.charAt(i)) != ","){
                nX += texto.charAt(i);
            }else{
                arr = arrayintAdd(arr, Integer.parseInt(nX));
                nX = "";
            }
        }
        return arr;
    }
}
