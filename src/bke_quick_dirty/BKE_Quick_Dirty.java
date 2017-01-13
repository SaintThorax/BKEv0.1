package bke_quick_dirty;
/**
 * bke_quick_dirty.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class BKE_Quick_Dirty extends JFrame {
    
    JPanel cards;
    static JFrame frame;
    private JButton menu;
      
    public static void main(String[] args) {        
        createGUI();
    }
    
    public static void createGUI(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setMaximumSize(new Dimension(200,200));
        //contentPane.setSize(400,400);
        contentPane.add(new Spel());
        
        BKE_Quick_Dirty bke = new BKE_Quick_Dirty();     
        
        frame.setSize(600,600);
        frame.setTitle("Quick and Dirty");
        frame.setVisible(true);      
    }
    
    public void naarMenu(){
        System.out.println("Freeze all motor functions");
        frame.setContentPane(new Menu());
        frame.revalidate();
    }
}

class speelScherm extends JPanel{
    

    
    public speelScherm(){  
 
    }
    
    

}

class Speler_Computer_Makkelijk extends JPanel{
    private JButton start;
    
    
    public Speler_Computer_Makkelijk(){
        start = new JButton("Oh Baby a Triple");
        start.addActionListener(new CODHandeler());
        
        add(start);
    }
    
    class CODHandeler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        }
    }
}

class Menu extends JPanel{
    private JButton start;
    
    
    public Menu(){
        start = new JButton("Oh Baby a Triple");
        start.addActionListener(new CODHandeler());
        
        add(start);
    }
    
    class CODHandeler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
        }
    }
}

class Spel extends JPanel{
    
    private JButton[][] vakken;
    private Font spelFont = new Font("Berlin Sans FB", Font.PLAIN, 100);
    private Font textFont = new Font("Arial", Font.PLAIN, 20);
    
    private boolean over = false;
    private JButton reset, menu;
    private int aantal_geklikt = 0, aantal_zetten = 0;
    private JTextField gewonnen;
    final String X = "X", O = "O";
    private JFrame BKE;
    
    public Spel(){
               
        setLayout(new GridLayout(4,3,5,5));
        
        gewonnen = new JTextField(10);
        gewonnen.setFont(textFont);
        gewonnen.setHorizontalAlignment(SwingConstants.CENTER);
        
        reset = new JButton("Reset");
        reset.addActionListener(new ResetHandeler());
        
        menu = new JButton("Menu");
        menu.addActionListener(new MenuHandeler());
        
        add(menu);
        add(gewonnen);
        add(reset);


        vakken = new JButton[3][3];
        for (int i=0; i<3; i++){
            for (int j = 0; j<3;j++){
                vakken[i][j] = new JButton("");
                vakken[i][j].addActionListener(new KnopHandeler());
                add(vakken[i][j]);
            }
        }
    }
    class KnopHandeler implements ActionListener{
        public void actionPerformed(ActionEvent e){

            
            JButton geklikt = (JButton)e.getSource();
            geklikt.setFont(spelFont);
            
            aantal_geklikt++;
            aantal_zetten++;
            
            if (aantal_geklikt % 2 != 0) geklikt.setText("X");
            if (aantal_geklikt % 2 == 0) geklikt.setText("O");
            
            geklikt.setEnabled(false);
            
            for(int i = 0;i<3;i++){
                for (int j=0;j<3;j++){
                    //vakken[verticaal][horizontaal]
                    if(vakken[j][0].getText().equals(X) && vakken[j][1].getText().equals(X) && vakken[j][2].getText().equals(X)){
                        gewonnen.setText("X wint!");
                        over = true;
                    } 
                    if(vakken[0][i].getText().equals(X) && vakken[1][i].getText().equals(X) && vakken[2][i].getText().equals(X)){
                        gewonnen.setText("X wint!");
                        over = true;
                    }
                    if(vakken[2][0].getText().equals(X) && vakken[1][1].getText().equals(X) && vakken[0][2].getText().equals(X) ||
                       vakken[0][0].getText().equals(X) && vakken[1][1].getText().equals(X) && vakken[2][2].getText().equals(X)){
                        gewonnen.setText("X wint!");
                        over = true;
                    }
                    
                    //Horizontale winconditie voor speler O
                    if (vakken[j][0].getText().equals(O) && vakken[j][1].getText().equals(O) && vakken[j][2].getText().equals(O)){
                        gewonnen.setText("O wint!");
                        over = true;
                    } // Verticale winconditie voor speler O
                    if(vakken[0][i].getText().equals(O) && vakken[1][i].getText().equals(O) && vakken[2][i].getText().equals(O)){
                        gewonnen.setText("O wint!");
                        over = true;
                    } //Diagonale winconditie voor speler O
                    if(vakken[2][0].getText().equals(O) && vakken[1][1].getText().equals(O) && vakken[0][2].getText().equals(O) ||
                       vakken[0][0].getText().equals(O) && vakken[1][1].getText().equals(O) && vakken[2][2].getText().equals(O)){
                        gewonnen.setText("O wint!");
                        over = true;
                    }
                    
                    //Gelijk spel conditie
                    if(aantal_zetten >= 9){
                        gewonnen.setText("Gelijkspel");
                        over = true;
                    }//Schakelt alle 
                    if(over == true){
                        vakken[i][j].setEnabled(false);
                    }
                }
            }
        }
    }
    
    class ResetHandeler implements ActionListener{        
        public void actionPerformed(ActionEvent e){
            for (int i =0; i<3; i++){
                for (int j=0; j<3;j++){
                    vakken[i][j].setEnabled(true);
                    vakken[i][j].setText("");
                    gewonnen.setText("");
                    aantal_zetten = 0;
                    aantal_geklikt = 0;
                    aantal_zetten = 0;
                    over = false;
                }
            }       
        }
    }
    class MenuHandeler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            BKE_Quick_Dirty  ehhh = new BKE_Quick_Dirty();
            ehhh.naarMenu();
        }
    }
}