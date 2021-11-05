package adicionais;

import java.awt.Color;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.FlowLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class janela implements ActionListener{
    Font fonteInput = new Font("SansSerif", Font.BOLD, 18);
    Font fonteConsoleASCII = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    static JTextArea Jconsole = new JTextArea();
    static JTextArea JmonsAscii = new JTextArea();
    static JTextArea Jplayergui = new JTextArea();
    static JTextField inputUser = new JTextField(25);
    static boolean Ativo = true;
    static String inputText;
    static boolean enter = false;
    static DefaultCaret caret = (DefaultCaret)Jconsole.getCaret();
    
    public janela(){
        JFrame frame = new JFrame();
        JPanel Pesquerdo = new JPanel();
        JPanel Pdireito = new JPanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(null);

        // coisas do painel esquerdo
        // layout vou deixar o padrao msm (Flow)
        //Pesquerdo.setLayout(null);
        Pesquerdo.setLayout(new FlowLayout(FlowLayout.CENTER));
        Pesquerdo.setBounds(0, 0, 600, 762);
        Pesquerdo.setBackground(Color.darkGray);
        Pesquerdo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        // coisas da caixa de input
        JButton i = new JButton("->");
        i.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        i.setForeground(Color.white);
        i.setOpaque(true);
        i.setPreferredSize(new Dimension(26,40));
        i.setBackground(Color.gray);
        i.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        i.setFocusPainted(false);
        i.addActionListener(this);
        inputUser.setBackground(Color.gray);
        inputUser.setFont(fonteInput);
        inputUser.setPreferredSize(new Dimension(775, 40));
        inputUser.setColumns(29);
        inputUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        inputUser.addActionListener(this);

        // coisas da area de texto q e tipo o console
        Jconsole.setBackground(Color.DARK_GRAY);
        Jconsole.setForeground(Color.white);
        Jconsole.setFont(fonteConsoleASCII);
        Jconsole.setMargin(new Insets(0,25,0,15));
        Jconsole.setEditable(false);
        JScrollPane scroll = new JScrollPane(Jconsole, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(585, 670));
        scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 2), BorderFactory.createDashedBorder(Color.black, 2, 5, 5, true)));
        scroll.setBackground(Color.darkGray);
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        Pesquerdo.add(scroll);
        //Pesquerdo.add(Jconsole);
        Pesquerdo.add(inputUser);
        Pesquerdo.add(i);
        frame.add(Pesquerdo);

        // coisas do painel direito
        // esse painel vai ter duas coisas dentro: a caixa de texto que vai dar print no monstro e a area que mostra os status do jogador
        Pdireito.setLayout(new FlowLayout(FlowLayout.CENTER));
        Pdireito.setBounds(600, 0, 585, 762);
        Pdireito.setBackground(Color.darkGray);
        Pdireito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        JmonsAscii.setBackground(Color.black);
        JmonsAscii.setForeground(Color.white);
        JmonsAscii.setFont(fonteConsoleASCII);
        JmonsAscii.setEditable(false);
        JmonsAscii.setPreferredSize(new Dimension(555, 580));
        Jplayergui.setBackground(Color.darkGray);
        Jplayergui.setForeground(Color.white);
        Jplayergui.setPreferredSize(new Dimension(554, 154));
        Jplayergui.setFont(fonteConsoleASCII);
        
        Pdireito.add(JmonsAscii);
        Pdireito.add(Jplayergui);
        frame.add(Pdireito);

        frame.setVisible(true);
    }

    public static void clearJanela(){
        Jconsole.setText("");
        printJanela("\n\n\n");
    }
    public static void printJanela(Object m){
        String msg = m.toString();
        Jconsole.append(msg);
        Jconsole.setCaretPosition(Jconsole.getDocument().getLength());
    } 
    public static void printlnJanela(Object m){
        String msg = m.toString();
        Jconsole.append("\n" + msg);
        Jconsole.setCaretPosition(Jconsole.getDocument().getLength());
    } 
    public static String JinputS(){
        enter = false;
        while(enter == false){
            try {
                Thread.sleep(100); // eu nao sei pq, mas tenq ter um tempinho pro ngc pensa, isso daqui so espera o usuario apertar pra confirmar oq ele digitou
            }
            catch (InterruptedException e) {
            }
        }
        return inputText;
    }

    public static void setUpPlayerGUI(){ // chama essa mesma funcao pra atualizar essa tabela tb
        // 77 colunas 8 linhas
        Jplayergui.setText("_______________________________________________________________________________");
        Jplayergui.append("\n|"+extras.verTamMax_table(" Nome: " + handler.jogador.getNome(), 37) + "|" + "" + extras.verTamMax_table(" Classe: " + handler.jogador.getClasse(), 39)+"|");
        Jplayergui.append("\n|_____________________________________|_______________________________________|");
        Jplayergui.append("\n|"+extras.verTamMax_table(" Vida: " + String.format("%.0f", handler.jogador.getVida()), 18) + "|" + extras.verTamMax_table(" Forca: " + handler.jogador.getForca(), 18) + "|"+extras.verTamMax_table(" Defesa: " + String.format("%.0f",handler.jogador.getDefesa()), 19) + "|"+extras.verTamMax_table(" Destreza: " + handler.jogador.getDestreza(), 19) + "|");
        Jplayergui.append("\n|__________________|__________________|___________________|___________________|");
        Jplayergui.append("\n|"+extras.verTamMax_table(" Arma: " + handler.arma.get(handler.jogador.getArmaEquip()).getNome(), 37) + "|" + "" + extras.verTamMax_table(" Armadura: " + handler.armor.get(handler.jogador.getArmorEquip()).getNome(), 39)+"|");
        Jplayergui.append("\n|"+"__________________" + "_"+ "__________________" + "|"+"___________________" + "_"+ "___________________" + "|");
        Jplayergui.append("\n|"+extras.verTamMax_table(" Ataque: " + handler.arma.get(handler.jogador.getArmaEquip()).getAtaque(), 18) + "|" + extras.verTamMax_table(" Peso: " + handler.arma.get(handler.jogador.getArmaEquip()).getPeso(), 18) + "|"+extras.verTamMax_table(" Defesa: " + handler.armor.get(handler.jogador.getArmorEquip()).getDefesa(), 19) + "|"+extras.verTamMax_table(" Peso: " + handler.armor.get(handler.jogador.getArmorEquip()).getPeso(), 19) +"|");
        Jplayergui.append("\n|__________________|__________________|___________________|___________________|");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputText = inputUser.getText();
        inputUser.setText("");
        enter = true;
    }

}
