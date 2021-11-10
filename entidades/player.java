package entidades;

import adicionais.extras;
import adicionais.handler;
import adicionais.janela;
import fases.fases;
import itens.armas;
import itens.inventario;

public class player extends entidade{

    String classe;
    int tipoArma;
    int monstros_f_derrot = 0;
    int monstros_b_derrot = 0;
    int npcs_mortos = 0;
    int level = 1;
    double xp_nes = 5;

    int forca_ini;
    double def_ini;
    int des_ini;
    public static boolean P_aliado = false;

    public player(String nome, String classe, int arma_equip, int armor_equip, int vidamax, int forca, int defesa, int destreza, int tipoArma){
        this.nome = nome;
        this.classe = classe;
        this.arma_equip = arma_equip;
        this.armor_equip = armor_equip;
        this.vidamax = vidamax;
        this.vida = vidamax;
        this.defesa = defesa; 
        this.forca = forca;
        this.destreza = destreza;
        this.tipoArma = tipoArma;
    }

    public void luta_prep(){
        extras.println("");
        extras.println_bonito("Voce se prepara para lutar!", 800, 500);
        forca_ini = this.forca;
        def_ini = this.defesa;
        des_ini = this.destreza;
        this.forca += inventario.getForcaTotal();
        this.defesa += handler.armor.get(this.armor_equip).getDefesa() + inventario.getDefTotal();
        this.destreza += handler.armor.get(this.armor_equip).getPesoDes() + handler.arma.get(this.arma_equip).getPesoDes() + inventario.getDesTotal();
        buff_evasion = handler.armor.get(this.armor_equip).getEvasionB()*inventario.getDodgeBonus();
        janela.setUpPlayerGUI();
    }

    public void P_turno(int indexm, int Tmons){
        boolean act = false;
        extras.print("");
        extras.println_bonito("É a sua vez!", 500, 300);
        extras.print("");
        while(act == false){
            extras.println_bonito("Voce está com " + String.format("%.00f", this.vida) + " de vida!", 800, 300);
            extras.print("");
            extras.println_bonito("Qual será sua proxima acao? ", 500, 200);
            extras.print("");
            extras.println_bonito("Atacar!", 200, 0);
            extras.println_bonito("Defender!", 200, 0);
            extras.println_bonito("Usar Item!", 200, 0);
            extras.println_bonito("Tentar fugir!", 200, 0);
            extras.println_bonito("Stats!", 200, 0);

            act = true;
            String res = extras.inputS().toLowerCase();
            switch(res){
                case "atacar":
                    Player_atacar(indexm, Tmons);
                    if(P_aliado){
                        Aliado_atacar(indexm, Tmons);
                    }
                    break;
                case "defender":
                    defender();
                    extras.println("");
                    extras.println_bonito("Voce se defende!", 400, 500);
                    break;
                case "usar item":
                    act = inventario.listarItensConsu();
                    if(act){
                        extras.println("");
                        extras.println_bonito("Digite o numero do item que deseja usar, ou 'voltar' para fazer outra coisa...", 800, 500);
                        switch(extras.inputS().toLowerCase()){
                            case "voltar":
                                act = false;
                                break;
                            default: //  TENQ VERIFICAR SE E UM NUMERO
                                try{
                                    act = inventario.usarItem(Integer.parseInt(res));
                                }catch(Exception e){
                                    extras.println("");
                                    extras.println_bonito("Digite um numero valido", 800, 500);
                                    act = false;
                                }
                                break;
                        }
                    }
                    break;
                case "tentar fugir":
                    tentarFugir(inimigos.getInimigo(indexm, Tmons).getDestreza(), Tmons);
                    break;
                case "stats":
                    printStats();
                    act = false;
                    break;
                case "reiniciar":
                    extras.println("");
                    extras.println_bonito("Voce tem certeza que gostaria de reiniciar o jogo?", 800, 500);
                    if(extras.simNao()){
                        handler.NovoJogo();

                    }else{
                        act = false;
                    }
                    break;
                default:
                    extras.print("");
                    extras.println_bonito("Digite uma acao valida", 800, 500);
                    act = false;

            }
        }
        inimigos.getInimigo(indexm, Tmons).resetBuff();
    }
    //inimigos.getInimigo(indexm, tipo)
    private void Player_atacar(int indexm, int Tmons){
        double dano = handler.jogador.atacar();
        armas.texto_som(arma_equip);
        dano = inimigos.getInimigo(indexm, Tmons).levar_dano(dano, this.destreza, handler.arma.get(this.arma_equip).getPeso(), false);
        extras.print("");
        extras.println_bonito("O " + inimigos.getInimigo(indexm, Tmons).getNome() + " levou " + String.format("%.00f", dano) + " de dano!", 800, 500);
    }

    private void Aliado_atacar(int indexm, int Tmons){
        extras.print("");
        extras.println_bonito("O seu aliado vai atacar!", 500, 500);
        double dano = (handler.npcs.get(1).atacar())/5;
        armas.texto_som(this.arma_equip);
        dano = inimigos.getInimigo(indexm, Tmons).levar_dano(dano, this.destreza, handler.arma.get(this.arma_equip).getPeso(), false);
        extras.print("");
        extras.println_bonito("O " + inimigos.getInimigo(indexm, Tmons).getNome() + " levou " + String.format("%.00f", dano) + " de dano!", 800, 500);
    }

    public static void dor(){
        String sons_d[] = {"AIAIAI!", "AAAAAAAAH!", "OOOF!"};
        extras.print("");
        extras.println_bonito("" + sons_d[extras.rng_int(0, sons_d.length)], 100, 300);
    }

    public void aumentarstat(double c, String oq){
        switch(oq.toLowerCase()){
            case "forca":
                this.forca += c;
            break;
            case "destreza":
                this.destreza += c;
            break;
            case "defesa":
                this.defesa += c;
            break;
        }
        janela.setUpPlayerGUI();
    }

    public void printStatsFim(){
        printStats();
        extras.println_bonito("Monstros Fracos derrotados: " + this.monstros_f_derrot, 400, 200);
        extras.print("");
        extras.println_bonito("NPCs derrotados: " + this.npcs_mortos, 400, 200);
        extras.print("");
        extras.println_bonito("Bosses derrotados: " + this.monstros_b_derrot, 400, 200);
        extras.print("");
        extras.println_bonito("Voce chegou no andar " + fases.andar_atual + " da fase " + fases.fase_atual + ", na tentativa numero" + handler.Prun, 400, 200);
    }

    public void morrer(int indexm, int Tmons){
        extras.print("");
        extras.println_bonito("Voce se sente fraco demais contra " + inimigos.getInimigo(indexm, Tmons).getNome() + ",\nsua visao comeca a ficar escura, e voce fecha totalmente seus olhos...", 2000, 500);
        extras.print("");
        extras.println_bonito("Voce esta morto...", 500, 200);
        extras.print("");
        extras.println_bonito("Precione enter para continuar...", 500, 200);
        extras.inputS();
        extras.print("");
        extras.println_bonito("Verifique seu progresso nesta ultima tentativa:", 600, 600);
        printStatsFim();
        extras.inputS();
        resetNmonstrosderrot();
        resetBuff();
        P_aliado = false;
        extras.console_clear();
        handler.NovoJogo();
    }

    public void resetNmonstrosderrot(){
        this.monstros_b_derrot = 0;
        this.npcs_mortos = 0;
        this.monstros_f_derrot = 0;
    }

    public void fimLuta(double xp, int tipo){
        int xp_rec = (int) xp;
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
        janela.setUpPlayerGUI();
        receberXp(xp_rec);
    }

    @Override
    public void resetBuff(){
        if(this.defende){
            this.buff_defesa = 1;
            extras.println("");
            extras.println_bonito("Voce parou de defender!", 500, 500);
        }
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
                if (this.xp_nes <= exp){
                    level++;
                    extras.print("");
                    extras.println_bonito("Voce evoluiu de Nivel! Voce esta agora no nivel " + level, 700, 600);
                    exp = 0;
                    this.xp_nes = this.xp_nes + Math.sqrt(this.xp_nes)*2;
                    levelup();
                }
            }
        }
    }

    public boolean venderArma(){
        if(this.arma_equip == 0){
            extras.print("");
            extras.println_bonito("Voce nao pode vender o nada...", 500, 500);
        }else{
            extras.print("");
            extras.println_bonito("Voce gostaria de vender o seu " +handler.arma.get(this.arma_equip).getNome()+" por $"+(handler.arma.get(this.arma_equip).getDinheiro()/2)+"?", 700, 500);
            extras.print("");
            handler.arma.get(this.arma_equip).printStats();
            if(extras.simNao()){
                extras.print("");
                extras.println_bonito("Voce vendeu o seu "+handler.arma.get(this.arma_equip).getNome(), 500, 500);
                inventario.ganharDinheiro(handler.arma.get(this.arma_equip).getDinheiro()/2);
                this.arma_equip = 0;
                return true;
            }
        }
        return false;
    }

    public boolean venderArmadura(){
        if(this.armor_equip == 0){
            extras.print("");
            extras.println_bonito("Voce nao pode vender o nada...", 500, 500);
        }else{
            extras.print("");
            extras.println_bonito("Voce gostaria de vender o seu " +handler.armor.get(this.armor_equip).getNome()+" por $"+(handler.armor.get(this.armor_equip).getDinheiro()/2)+"?", 700, 500);
            extras.print("");
            handler.armor.get(this.armor_equip).printStats();
            if(extras.simNao()){
                extras.print("");
                extras.println_bonito("Voce vendeu o seu "+handler.armor.get(this.armor_equip).getNome(), 500, 500);
                inventario.ganharDinheiro(handler.armor.get(this.armor_equip).getDinheiro()/2);
                this.armor_equip = 0;
                return true;
            }
        }
        return false;
    }

    public void receberArmaArmor(int id, int tipo){
        extras.println("");
        switch(tipo){
            case 0:
                extras.println_bonito("Voce recebeu a arma " + handler.arma.get(id).getNome(), 900, 500);
                extras.println("");
                extras.println_bonito("Voce gostaria de trocar no lugar do seu " + handler.arma.get(this.arma_equip).getNome() + "?", 900, 500);
                if(extras.simNao()){
                    this.arma_equip = id;
                    extras.println("");
                    extras.println_bonito("Voce equipou a arma " + handler.arma.get(id).getNome(), 900, 500);
                }else{
                    extras.println("");
                    extras.println_bonito("Voce jogou a arma " + handler.arma.get(id).getNome() + " fora...", 900, 500);
                }
                break;
            case 1:
                extras.println_bonito("Voce recebeu a " + handler.armor.get(id).getNome(), 900, 500);
                extras.println("");
                extras.println_bonito("Voce gostaria de trocar no lugar do seu " + handler.armor.get(this.armor_equip).getNome() + "?", 900, 500);
                if(extras.simNao()){
                    this.armor_equip = id;
                    extras.println("");
                    extras.println_bonito("Voce equipou a " + handler.armor.get(id).getNome(), 900, 500);
                }else{
                    extras.println("");
                    extras.println_bonito("Voce jogou a " + handler.armor.get(id).getNome() + " fora...", 900, 500);
                }
                break;
        }
        janela.setUpPlayerGUI();
    }
    
    @Override
    public void tentarFugir(int des_atacante, int tipo){
        extras.println("");
        extras.println_bonito("Voce tenta fugir!", 400, 400);
        if(tipo == 0){
            if(fugir(des_atacante)){
                extras.println("");
                extras.println_bonito("Voce conseguiu fugir!", 400, 400);
                handler.jogador.fimLuta(3, tipo);
                handler.fase.get(fases.fase_atual-1).fimAndar();
            }else{
                extras.println("");
                extras.println_bonito("Voce falhou na tentativa de fugir!", 400, 400);
            }
        }else{
            extras.println("");
            extras.println_bonito("O que voce esta fazendo? Voce nao pode fugir desta luta!", 400, 400);
        }
    }

    public void levelup(){
        switch(handler.jogador.getClasse().toLowerCase()){
            case "arqueiro": 
                handler.jogador.setForca(this.forca + extras.rng_int(1, 3)); 
                handler.jogador.setDefesa(this.defesa + extras.rng_int(0, 2));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(1, 5));
            break;
            case "guerreiro":
                handler.jogador.setForca(this.forca + extras.rng_int(1, 4)); 
                handler.jogador.setDefesa(this.defesa + extras.rng_int(0, 2));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(1, 3));
                break;
            case "paladino":
                handler.jogador.setForca(this.forca + extras.rng_int(1, 3)); 
                handler.jogador.setDefesa(this.defesa + extras.rng_int(1, 5));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(1, 2));
                break;
            case "despojado":
                handler.jogador.setForca(this.forca + extras.rng_int(1, 3)); 
                handler.jogador.setDefesa(this.defesa + extras.rng_int(1, 5));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(1, 5));
                break;
            default:
                handler.jogador.setForca(this.forca + extras.rng_int(0, extras.rng_int(1, 5)));  // qnd vc fica na duvida oq dar de pontos pra uma classe criada dentro do jogo
                handler.jogador.setDefesa(this.defesa + extras.rng_int(0, extras.rng_int(1, 3)));
                handler.jogador.setDestreza(this.destreza + extras.rng_int(0, extras.rng_int(1, 4)));
                break;
        }
        janela.setUpPlayerGUI();
        extras.print("");
        extras.println_bonito("Suas capacidades fisicas foram melhoradas!", 700, 200);
        extras.print("");
        extras.println_bonito("Sua forca, defesa, e destreza estao melhores!", 700, 400);
    }

    public static void printStats(){
        extras.println("_____________________________________________________________________________________________________");
        extras.println("|                |                  |           |           |           |            |              |");
        extras.println("|      Nome      |      Classe      |   Level   |  VidaMax  |   Forca   |   Defesa   |   Destreza   |");
        extras.println("|________________|__________________|___________|___________|___________|____________|______________|");
        extras.println("|                |                  |           |           |           |            |              |");
        extras.println("|"+ extras.verTamMax_table(handler.jogador.getNome(), 16) +"|"+ extras.verTamMax_table(handler.jogador.getClasse(), 18) +"|"+ extras.verTamMax_table(handler.jogador.getLevel(), 11) + "|" + extras.verTamMax_table(handler.jogador.getVidamax(), 11) +"|"+ extras.verTamMax_table(handler.jogador.getForcaIni(), 11) +"|"+ extras.verTamMax_table(String.format("%.00f", handler.jogador.getDefesaIni()), 12) +"|"+ extras.verTamMax_table(handler.jogador.getDestrezaIni(), 14) +"|");
        extras.println("|________________|__________________|___________|___________|___________|____________|______________|");
        extras.println("");
        handler.armor.get(handler.jogador.getArmorEquip()).printStats();
        extras.println("");
        handler.arma.get(handler.jogador.getArmaEquip()).printStats();
        extras.println("");
        inventario.printAce();
        extras.println("");
        extras.println("Dinheiro: " + String.format("%.02f", inventario.dinheiro));
        extras.println("");
        extras.println("Xp necessario para subir de nivel: " + String.format("%.02f", handler.jogador.getExpNes() - handler.jogador.getExp()));
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
    public int gettipoArma(){return tipoArma;}
    public int getNpcsMortos(){return npcs_mortos;}
    public double getExpNes(){return xp_nes;}

    //setters
    public void setClasse(String c){this.classe = c;}
    public void setTipoArma(int n){this.tipoArma = n;}
    public void addmonstros_f_derrot(int n){this.monstros_f_derrot += n;}
    public void addmonstros_b_derrot(int n){this.monstros_b_derrot += n;}
    public void addnpcs_mortos(int n){this.npcs_mortos += n;}
    public void setArmorEquip(int n){this.armor_equip = n;}
    public void setNivel(int n){this.level = n;}
    
}
