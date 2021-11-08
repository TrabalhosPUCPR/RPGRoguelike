package entidades;

import adicionais.extras;
import adicionais.janela;
import itens.inventario;

public class monstros_b extends inimigos{

    public monstros_b(String nome, String desc, int arma_equip, int vidamax, int forca, int defesa, int destreza, int exp, int[] Nfases, String nomeAscii){
        this.nome = nome;
        this.desc = desc;
        this.arma_equip = arma_equip;
        this.vidamax = vidamax;
        this.vida = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.exp = exp;
        this.Nfases = Nfases;
        this.nomeAscii = nomeAscii;
    }

    @Override
    public void morrer(){
        janela.clearJmonsAscii(true);
        extras.print("");
        extras.println_bonito("Viva! O " + this.nome + " foi derrotado!", 500, 1000);
        extras.print("");
        extras.println_bonito("Voce ganhou experiencia extra por esta luta!", 400, 400);
        dropar();
    }

    @Override
    public void dropar() {
        switch(this.nome.toLowerCase()){
            case "mimic":
                dropItemQualidade();
                inventario.ganharDinheiro(extras.rng_double(100, 150));
            break;
            case "slime de ferro":
                dropItemGenerico();
                inventario.ganharDinheiro(extras.rng_double(0, 30));
                break;
            case "nouveau riche":
                dropItemGenerico();
                inventario.ganharDinheiro(extras.rng_double(500, 1000));
            default:
                dropItemQualidade();
                inventario.ganharDinheiro(extras.rng_double(30, 60));
                break;
        }
    }
    
}

