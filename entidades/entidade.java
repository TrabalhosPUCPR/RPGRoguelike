package entidades;

import adicionais.extras;
import adicionais.handler;
import adicionais.janela;
import fases.fases;

public class entidade {
    
    String nome; 
    String desc; 
    int arma_equip;
    int armor_equip;

    double vida;
    int vidamax;

    double defesa;
    int forca;
    int destreza;

    int exp = 0;

    double buff_forca = 1; //modificacoes externas para a forca da pessoa, sem mudar nada e 1, ou seja, nao muda nada
    double buff_defesa = 1; //modificacoes externas para a defesa da pessoa, sem mudar nada e 1, ou seja, nao muda nada
    double buff_evasion = 1; //modificacoes externas para a chance de desviar da pessoa, sem mudar nada e 1, ou seja, nao muda nada

    boolean defende = false;

    boolean fugir(int des_atacante){
        double chance = (((this.destreza*2)*buff_evasion) - 0.3*des_atacante) * 1.5;
        if(extras.rng_double(0, 100) < chance){
            return true;
        }else{
            return false;
        }
    }

    public void tentarFugir(int des_atacante, int tipo){
        extras.println("");
        extras.println_bonito(this.nome + " tenta fugir!", 400, 400);
        if(fugir(des_atacante)){
            extras.println("");
            extras.println_bonito(this.nome + " conseguiu fugir!", 400, 400);
            handler.jogador.fimLuta(this.exp*0.3, tipo);
            handler.fase.get(fases.fase_atual-1).fimAndar();

        }else{
            extras.println("");
            extras.println_bonito(this.nome + " falhou na tentativa de fugir!", 400, 400);
        }
    }

    public double atacar(){
        double dano = ((this.forca + handler.arma.get(this.arma_equip).getAtaque()) * this.buff_forca)+(this.destreza*0.2);
        return extras.rng_double(dano-(dano*0.1), dano+(dano*0.1));
    }

    public void defender(){
        this.buff_defesa = 2;
        this.defende = true;
    }

    public void resetBuff(){
        if(this.defende){
            this.buff_defesa = 1;
            extras.println("");
            extras.println_bonito("O " + this.nome + " parou de defender!", 500, 500);
        }
    }

    public double levar_dano(double dano, int des_atacante, String pesoAtac, boolean ig_def){
        double danolevou = calc_dano(dano, des_atacante, ig_def);
        msg_dano(danolevou);
        this.vida -= danolevou;
        if(pesoAtac == "omega leve"){
            extras.println("");
            extras.println_bonito("O ataque acertou duas vezes!", 400, 600);
            dano = (calc_dano(dano, des_atacante, ig_def))/2;
            msg_dano(dano);
            this.vida -= dano;
            return danolevou + dano;
        }
        return danolevou;
    }

    public double calc_dano(double dano, int des_atacante, boolean ig_def){
        if(dodge(des_atacante)){
            extras.println("");
            extras.println_bonito("O ataque errou!", 500, 500);
            return 0;
        }else{
            if(ig_def != true){
                dano -= this.defesa*this.buff_defesa;
            }
            if (dano < 1){
                dano = 1;
            }
        }
        return dano;
    }
    /*
    ⠀⠀⠀⢠⣀⠀⢀⢢⠀⢀⣠⠀⠀⠀⠀
    ⠀⠤⣤⠤⠣⠑⠊⠈⠒⠡⠣⢤⡤⠄⠀
    ⣀⣤⣴⣛⡂⠀⠀⠀⠀⠀⣚⣣⣤⣄⡀
    ⠀⠀⣠⠮⠄⡂⢀⢄⠀⡂⠭⢦⡀⠀⠀
    ⠀⠀⠀⠀⠀⠗⠁⠀⠑⠇⠀⠀⠀⠀⠀
    */
    static void msg_dano(double dano){
        if(dano != 0){
            extras.delay(100);
            extras.print("");
            extras.println_bonito("    ⠀⠀⠀ ⢠⠀⢀⢢⠀⢀⣠⠀⠀⠀", 10, 0);
            extras.println_bonito("    ⠀⠤⣤⠤⠣⠑⠊⠈⠒⠡⠣⢤⡤⠄", 10, 0);
            extras.println_bonito("    ⣀⣤⣴⣛⡂"+extras.verTamMax_table(String.format("%.0f", dano), 5)+"⣚⣣⣤⣄⡀", 10, 0);
            extras.println_bonito("    ⠀⠀⣠⠮⠄⡂⢀⢄⠀⡂⠭⢦⡀⠀⠀", 10, 0);
            extras.println_bonito("    ⠀⠀⠀⠀⠀⠗⠁⠀⠑⠇⠀", 10, 500);
        }
    }

    public boolean dodge(int des_atacante){
        double chance;
        chance = (this.destreza+(0.5*this.destreza)*buff_evasion) - 0.7*des_atacante;
        if (extras.rng_double(0, 100) < chance && !handler.jogador.getClasse().equals("dev")){
            return true;
        }else{
            return false;
        }
    } 

    public void curar(double c){
        this.vida += c;
        if(this.vida > this.vidamax){
            this.vida = this.vidamax;
        }
        janela.setUpPlayerGUI();
    }

    public void morrer(){
        extras.print("");
        extras.println_bonito("O " + this.nome + " foi derrotado!", 300, 700);
        janela.clearJmonsAscii(true);
    }

    //getters
    public String getNome(){return nome;}
    public String getDesc(){return this.desc;}
    public int getVidamax(){return vidamax;}
    public double getVida(){return vida;}
    public int getForca(){return forca;}
    public double getDefesa(){return defesa;}
    public int getDestreza(){return destreza;}
    public int getArmaEquip(){return arma_equip;}
    public int getExp(){return this.exp;}

    //setters
    public void setNome(String n){this.nome = n;}
    public void setDesc(String n){this.desc = n;}
    public void setVidamax(int n){this.vidamax = n;}
    public void setVida(double n){this.vida = n;}
    public void setForca(int n){this.forca = n;}
    public void setDefesa(double n){this.defesa = n;}
    public void setDestreza(int n){this.destreza = n;}
    public void setArmaEquip(int n){this.arma_equip = n;}
    public void setExp(int n){this.exp = n;}

}
