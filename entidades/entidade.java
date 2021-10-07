package entidades;

import adicionais.extras;
import adicionais.handler;

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

    public double atacar(){
        double dano = ((this.forca + handler.arma.get(this.arma_equip).getAtaque()) * this.buff_forca);
        return dano;
    }

    public void defender(){
        this.defesa = this.defesa*1.5;
    }

    public double levar_dano(double d, int des_atacante){
        double chance;
        chance = (this.destreza+(0.5*this.destreza)*buff_evasion) - 0.7*des_atacante;

        if(extras.rng_double(100) < chance){
            d = 0;
            extras.println_bonito("O ataque errou!", 500, 500);
            extras.print("");
            return 0;
        }else{
            d -= this.defesa*this.buff_defesa;
            if (d < 1){
                d = 1;
            }
        }
        this.vida -= d;
        return d;
    }

    public void curar(double c){
        this.vida += c;
        if(this.vida > this.vidamax){
            this.vida = this.vidamax;
        }
    }

    //getters
    public String getNome(){return nome;}
    public int getVidamax(){return vidamax;}
    public double getVida(){return vida;}
    public int getForca(){return forca;}
    public double getDefesa(){return defesa;}
    public int getDestreza(){return destreza;}
    public int getArmaEquip(){return arma_equip;}

    //setters
    public void setNome(String n){this.nome = n;}
    public void setVidamax(int n){this.vidamax = n;}
    public void setVida(double n){this.vida = n;}
    public void setForca(int n){this.forca = n;}
    public void setDefesa(double n){this.defesa = n;}
    public void setDestreza(int n){this.destreza = n;}
    public void setArmaEquip(int n){this.arma_equip = n;}

}
