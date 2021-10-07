package entidades;

import adicionais.combate;
import adicionais.extras;
import adicionais.handler;
import fases.fases;
import itens.armas;

public class player extends entidade{

    String classe;
    int monstros_f_derrot = 0;
    int monstros_b_derrot = 0;
    int npcs_mortos = 0;
    int level = 1;
    static double xp_nes = 5;

    int forca_ini;
    double def_ini;
    int des_ini;

    public player(String nome, String classe, int arma_equip, int armor_equip, int vidamax, int forca, int defesa, int destreza){
        this.nome = nome;
        this.classe = classe;
        this.arma_equip = arma_equip;
        this.armor_equip = armor_equip;
        this.vidamax = vidamax;
        this.vida = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
    }

    public void luta_prep(){
        extras.println("");
        extras.println_bonito("Voce se prepara para lutar!", 800, 500);
        forca_ini = this.forca;
        def_ini = this.defesa;
        des_ini = this.destreza;
        this.defesa += handler.armor.get(this.armor_equip).getDefesa();
        this.destreza += handler.armor.get(this.armor_equip).getPesoDes() + handler.arma.get(this.arma_equip).getPesoDes();;
        buff_evasion = handler.armor.get(this.armor_equip).getEvasionB();
    }

    public void P_turno(int indexm, int Tmons){
        boolean act = false;
        combate.n_turno++;
        extras.print("");
        extras.println_bonito("É a sua vez!", 500, 300);
        extras.print("");
        while(act == false){
            extras.println_bonito("Voce está com " + String.format("%.02f", this.vida) + " de vida!", 800, 300);
            extras.print("");
            extras.println_bonito("Qual será sua proxima acao? ", 500, 200);
            extras.print("");
            extras.println_bonito("Atacar!", 200, 0);
            extras.println_bonito("Defender!", 200, 0);
            extras.println_bonito("Usar Item!", 200, 0);
            extras.println_bonito("Tentar fugir!", 200, 0);
            extras.println_bonito("Stats!", 200, 0);
            extras.print("");

            act = true;
            String res = extras.inputS().toLowerCase();
            switch(res){
                case "atacar":
                    P_atacar(indexm, Tmons);
                    break;
                case "defender":
                    P_defender();
                    break;
                case "usar item":
                    break;
                case "tentar fugir":
                    break;
                case "stats":
                    printStats();
                    act = false;
                    break;
                default:
                    extras.println_bonito("Digite uma acao valida", 800, 500);
                    act = false;

            }
        }
    }
    //inimigos.getInimigo(indexm, tipo)
    private void P_atacar(int indexm, int Tmons){
        double dano = handler.jogador.atacar();
        armas.texto_som(arma_equip);
        dano = inimigos.getInimigo(indexm, Tmons).levar_dano(dano, this.destreza);
        extras.println_bonito("O " + inimigos.getInimigo(indexm, Tmons).getNome() + " levou " + String.format("%.02f", dano) + " de dano!", 800, 500);
    }

    private void P_defender(){
        extras.println_bonito("Voce se defende!", 300, 300);
        defender();
        extras.println_bonito("Sua defesa aumentou para " + this.defesa + " ate o proximo turno!", 1000, 500);
    }

    public static void dor(){
        String sons_d[] = {"AIAIAI!", "AAAAAAAAH!", "OOOF!"};
        extras.println_bonito("" + sons_d[extras.rng_int(0, sons_d.length)], 100, 300);
    }

    public void morrer(int indexm, int Tmons){
        extras.print("");

        extras.println_bonito("Voce se sente fraco demais contra " + inimigos.getInimigo(indexm, Tmons).getNome() + ", sua visao comeca a ficar escura, e voce fecha totalmente seus olhos...", 2000, 500);

        extras.print("");
        extras.println_bonito("Voce esta morto...", 500, 200);
        extras.print("");
        extras.println_bonito("Precione enter para continuar...", 500, 200);
        extras.inputS();
        extras.print("");
        extras.println_bonito("Verifique seu progresso nesta ultima tentativa:", 600, 600);
        printStats();
        extras.println_bonito("Monstros Fracos derrotados: " + this.monstros_f_derrot, 400, 200);
        extras.print("");
        extras.println_bonito("Bosses derrotados: " + this.monstros_b_derrot, 400, 200);
        extras.print("");
        extras.println_bonito("Voce chegou no andar " + fases.andar_atual + " da fase " + fases.fase_atual, 400, 200);
        extras.print("");
        extras.println_bonito("Precione enter para criar um novo jogo...", 400, 200);
        extras.inputS();
        extras.console_clear();
        handler.NovoJogo();
    }

    public void fimLuta(int xp_rec, int tipo){
        extras.println("");
        extras.println_bonito("Sucesso!", 300, 300);
        switch(tipo){
            case 0:
                addmonstros_f_derrot(1);
                break;
            case 3:
                addnpcs_mortos(1);
                break;
            default:
                addmonstros_b_derrot(1);
                break;
        }
        this.forca = forca_ini;
        this.defesa = def_ini;
        this.destreza = des_ini;
        buff_forca = 1;
        buff_defesa = 1;
        buff_evasion = 1;
        receberXp(xp_rec);
    }

    public void receberXp(int xp_rec){
        extras.print("");
        if(xp_rec < 0){
            extras.println_bonito("Voce perdeu " + Math.abs(xp_rec) + " de experiencia...", 700, 600);
            exp += xp_rec;
        }else{
            extras.println_bonito("Voce recebeu " + xp_rec + " de experiencia!", 700, 600);
            for(int i = 0; i < xp_rec; i++){
                exp += 1;
                if (xp_nes <= exp){
                    level++;
                    extras.print("");
                    extras.println_bonito("Voce evoluiu de Nivel! Voce esta agora no nivel " + level, 700, 600);
                    exp = 0;
                    xp_nes = xp_nes + Math.sqrt(xp_nes)*2;
                    levelup();
                }
            }
        }
    }

    public void levelup(){
        switch(handler.jogador.getClasse().toLowerCase()){
            case "arqueiro":
                handler.jogador.setVidamax(this.vidamax + extras.rng_int(1, 3)); 
                handler.jogador.setForca(this.forca + extras.rng_int(1, 3)); 
                handler.jogador.setDefesa(this.defesa + extras.rng_int(1, 3));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(1, 5));
            break;
            case "guerreiro":
                handler.jogador.setVidamax(this.vidamax + extras.rng_int(1, 5)); 
                handler.jogador.setForca(this.forca + extras.rng_int(1, 5)); 
                handler.jogador.setDefesa(this.defesa + extras.rng_int(1, 4));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(1, 4));
                break;
            case "paladino":
                handler.jogador.setVidamax(this.vidamax + extras.rng_int(1, 7)); 
                handler.jogador.setForca(this.forca + extras.rng_int(1, 4)); 
                handler.jogador.setDefesa(this.defesa + extras.rng_int(1, 5));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(1, 3));
                break;
        }
        extras.print("");
        extras.println_bonito("Suas capacidades fisicas foram melhoradas!", 700, 200);
        extras.print("");
        extras.println_bonito("Sua vida maxima, forca, defesa, e destreza estao melhores!", 700, 400);
    }

    public static void printStats(){
        extras.println("_____________________________________________________________________________________________________");
        extras.println("|                |                  |           |           |           |            |              |");
        extras.println("|      Nome      |      Classe      |   Level   |  VidaMax  |   Forca   |   Defesa   |   Destreza   |");
        extras.println("|________________|__________________|___________|___________|___________|____________|______________|");
        extras.println("|                |                  |           |           |           |            |              |");
        extras.println("|"+ extras.verTamMax_table(handler.jogador.getNome(), 16) +"|"+ extras.verTamMax_table(handler.jogador.getClasse(), 18) +"|"+ extras.verTamMax_table(handler.jogador.getLevel(), 11) + "|" + extras.verTamMax_table(handler.jogador.getVidamax(), 11) +"|"+ extras.verTamMax_table(handler.jogador.getForcaIni(), 11) +"|"+ extras.verTamMax_table(handler.jogador.getDefesaIni(), 12) +"|"+ extras.verTamMax_table(handler.jogador.getDestrezaIni(), 14) +"|");
        extras.println("|________________|__________________|___________|___________|___________|____________|______________|");
        extras.println("");
        handler.armor.get(handler.jogador.getArmorEquip()).printStats();
        extras.println("");
        handler.arma.get(handler.jogador.getArmaEquip()).printStats();
        extras.println("");
        extras.println("Xp necessario para subir de nivel: " + String.format("%.02f", player.xp_nes - handler.jogador.getExp()));
        extras.println("");
        extras.println("Aperte enter para continuar...");
        extras.inputS();
        extras.println("");
    }

    //getters
    public String getClasse(){return classe;}
    public int getLevel(){return level;}
    public int getmonstros_f_derrot(){return monstros_f_derrot;}
    public int getmonstros_b_derrot(){return monstros_b_derrot;}
    public int getForcaIni(){return forca_ini;}
    public double getDefesaIni(){return def_ini;}
    public int getDestrezaIni(){return des_ini;}
    public int getArmorEquip(){return armor_equip;}

    //setters
    public void setClasse(String c){this.classe = c;}
    public void addmonstros_f_derrot(int n){this.monstros_f_derrot += n;}
    public void addmonstros_b_derrot(int n){this.monstros_b_derrot += n;}
    public void addnpcs_mortos(int n){this.npcs_mortos += n;}
    
}
