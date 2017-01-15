/*
 * probeer UI class te veranderen in een new jpanel van class BKE_Quick_Dirty
 * 
 * 
 */
package bke_quick_dirty;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BKE_Quick_Dirty extends JFrame {

    public static Font spelFont = new Font("Berlin Sans FB", Font.PLAIN, 100);
    public static Font textFont = new Font("Calibri", Font.PLAIN, 20);
    public static Font titelFont = new Font("Arial", Font.PLAIN, 60);

    
    public static JLabel scoreLabX, scoreLabO, Titel;
    private JButton reset, menuKnop, tegenSpeler;
    public static JButton[][] vakken;
    static JFrame frame;
      
    public static void main(String[] args) {        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                System.out.println("Test");
                new BKE_Quick_Dirty().createSpelGUI();
            }
        });
    }
    public void createMenuGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        Titel = new JLabel("Boter-kaas-en-eieren", SwingConstants.CENTER);
        Titel.setFont(titelFont);
        Titel.setPreferredSize(new Dimension(600,100));
        
        
        JPanel Menu = new JPanel();
      
        tegenSpeler = new JButton("Speler - Speler");
        tegenSpeler.setFont(textFont);
        tegenSpeler.setPreferredSize(new Dimension(500,100));
        tegenSpeler.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            createSpelGUI();
        }
    });
        
        Menu.add(Titel);
        Menu.add(tegenSpeler);    
        
        setContentPane(Menu);
        setSize(800,800);
        setTitle("Quick and Dirty");
        setVisible(true); 
    }
        
    public void createSpelGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel UI = new JPanel();
        UI.setLayout(new BorderLayout());
        
        scoreLabX = new JLabel("X : 0", SwingConstants.CENTER);
        scoreLabO = new JLabel("O : 0", SwingConstants.CENTER);
        
        scoreLabX.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        scoreLabO.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        
        scoreLabX.setFont(textFont);
        scoreLabO.setFont(textFont);
        
        reset = new JButton("Reset");
        reset.addActionListener(new ResetHandeler());
        
        menuKnop = new JButton("Menu");
        menuKnop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                createMenuGUI();
            }
        });
        
        UI.add(menuKnop);
        UI.add(scoreLabX);
        UI.add(scoreLabO);
        UI.add(reset);
        UI.setPreferredSize(new Dimension(10, 100));
        UI.setLayout(new GridLayout(1,4,10,0));

        JPanel spelBord = new JPanel();

        spelBord.setLayout(new BorderLayout(60,40));
        
        spelBord.add(UI, BorderLayout.PAGE_START);

        Spel spel = new Spel();
        
        spel.setBorder(BorderFactory.createEmptyBorder(10,50,50,50));
        
        spelBord.add(spel);
        
        setContentPane(spelBord);
        setSize(800,800);
        setTitle("Quick and Dirty");
        setVisible(true);      
    }

    class ResetHandeler implements ActionListener{        
    public void actionPerformed(ActionEvent e){
        for (int i =0; i<3; i++){
            for (int j=0; j<3;j++){
                vakken[i][j].setEnabled(true);
                vakken[i][j].setText("");
                Spel.aantal_geklikt = 0;
                Spel.aantal_zetten = 0;
                Spel.over = false;
                Spel.xWin = false;
                Spel.oWin = false;
                }
            }       
        }
    }
}

class Spel extends JPanel{
    final String X = "X", O = "O";
    public static boolean over = false, xWin = false, oWin = false;
    public static int scoreIntX, scoreIntO;
    public static int aantal_geklikt = 0, aantal_zetten = 0;
    public Spel(){

        setLayout(new GridLayout(3,3,5,5));

        BKE_Quick_Dirty.vakken = new JButton[3][3];
        for (int i=0; i<3; i++){
            for (int j = 0; j<3;j++){
                BKE_Quick_Dirty.vakken[i][j] = new JButton("");
                BKE_Quick_Dirty.vakken[i][j].addActionListener(new KnopHandeler());
                add(BKE_Quick_Dirty.vakken[i][j]);
            }
        }
    }
    

    class KnopHandeler implements ActionListener{
        public void actionPerformed(ActionEvent e){

            
            JButton geklikt = (JButton)e.getSource();
            geklikt.setFont(BKE_Quick_Dirty.spelFont);
            
            aantal_geklikt++;
            aantal_zetten++;
            
            if (aantal_geklikt % 2 != 0) geklikt.setText("X");
            if (aantal_geklikt % 2 == 0) geklikt.setText("O");
            
            geklikt.setEnabled(false);
            
            for(int i = 0;i<3;i++){
                for (int j=0;j<3;j++){
                    //vakken[verticaal][horizontaal]
                    if(BKE_Quick_Dirty.vakken[j][0].getText().equals(X) && BKE_Quick_Dirty.vakken[j][1].getText().equals(X) && BKE_Quick_Dirty.vakken[j][2].getText().equals(X)){
                        over = true;
                        xWin = true;
                    } 
                    if(BKE_Quick_Dirty.vakken[0][i].getText().equals(X) && BKE_Quick_Dirty.vakken[1][i].getText().equals(X) && BKE_Quick_Dirty.vakken[2][i].getText().equals(X)){
                        xWin = true;
                        over = true;
                    }
                    if(BKE_Quick_Dirty.vakken[2][0].getText().equals(X) && BKE_Quick_Dirty.vakken[1][1].getText().equals(X) && BKE_Quick_Dirty.vakken[0][2].getText().equals(X) ||
                       BKE_Quick_Dirty.vakken[0][0].getText().equals(X) && BKE_Quick_Dirty.vakken[1][1].getText().equals(X) && BKE_Quick_Dirty.vakken[2][2].getText().equals(X)){
                        xWin = true;
                        over = true;
                    }
                    
                    //Horizontale winconditie voor speler O
                    if (BKE_Quick_Dirty.vakken[j][0].getText().equals(O) && BKE_Quick_Dirty.vakken[j][1].getText().equals(O) && BKE_Quick_Dirty.vakken[j][2].getText().equals(O)){
                        oWin = true;
                        over = true;
                    } // Verticale winconditie voor speler O
                    if(BKE_Quick_Dirty.vakken[0][i].getText().equals(O) && BKE_Quick_Dirty.vakken[1][i].getText().equals(O) && BKE_Quick_Dirty.vakken[2][i].getText().equals(O)){
                        oWin = true;
                        over = true;
                    } //Diagonale winconditie voor speler O
                    if(BKE_Quick_Dirty.vakken[2][0].getText().equals(O) && BKE_Quick_Dirty.vakken[1][1].getText().equals(O) && BKE_Quick_Dirty.vakken[0][2].getText().equals(O) ||
                       BKE_Quick_Dirty.vakken[0][0].getText().equals(O) && BKE_Quick_Dirty.vakken[1][1].getText().equals(O) && BKE_Quick_Dirty.vakken[2][2].getText().equals(O)){
                        oWin = true;
                        over = true;
                    }
                    
                    //Gelijk spel conditie
                    if(aantal_zetten >= 9 && over == false){
                        //BKE_Quick_Dirty.gewonnen.setText("Gelijkspel");
                        over = true;
                    }//Schakelt alle vakken uit
                    if(over == true){
                        BKE_Quick_Dirty.vakken[i][j].setEnabled(false);
                        //System.out.println(BKE_Quick_Dirty.vakken[i][j]);
                    }
                }
            }
            if(xWin == true){
            scoreIntX = scoreIntX + 1;
            String scoreStrX = "X : " + String.valueOf(scoreIntX);
            BKE_Quick_Dirty.scoreLabX.setText(scoreStrX);
            }
            if(oWin == true){
            scoreIntO = scoreIntO + 1;
            String scoreStrO = "O : " + String.valueOf(scoreIntO);
            BKE_Quick_Dirty.scoreLabO.setText(scoreStrO);
            }
        }
    }
} 