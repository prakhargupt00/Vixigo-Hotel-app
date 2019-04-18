/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */

import javax.swing.* ; 
import java.awt.* ; 
import java.awt.event.* ; 
import static java.awt.Font.*;


public class welcome {
    
    welcome(){
        JFrame jfr = new JFrame("Vixigo") ; 
        jfr.setLayout(null) ; 
        jfr.setSize(900,900) ;
       // jfr.setLocationRelativeTo(null);
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
        jfr.setResizable(false);
        jfr.setContentPane(new JLabel(new ImageIcon("C:\\Users\\acer\\Desktop\\programme codes\\OOP mini project\\Sol-House-Ibiza-1-900x900.jpg")));
        
        
        JButton b1 = new JButton("Sign In") ; 
        JButton b2 = new JButton("Sign Up") ; 
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               new sign_in().setVisible(true) ;  
               jfr.dispose() ; 
            }
                    
        }) ; 
        
         b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                new  sign_up().setVisible(true) ; 
                jfr.dispose() ;
            }
                    
        }) ;
         
         b1.setBounds(800, 0, 100,50);
         b2.setBounds(700, 0, 100,50);
 
         b1.setForeground(Color.darkGray);
         b2.setForeground(Color.darkGray); 
         
         jfr.add(b1) ; 
         jfr.add(b2) ; 
         
         JLabel lab1 = new JLabel("   ixigo  ") ; 
        jfr.add(lab1) ; 
        lab1.setBounds(200,220,500,200) ; 
        lab1.setFont(new Font("Times New Roman" ,Font.BOLD,100));
        lab1.setForeground(Color.RED);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\acer\\Desktop\\programme codes\\OOP mini project\\creative-.jpg");  
        jfr.setIconImage(icon);
        
        JLabel head= new JLabel(new ImageIcon("C:\\Users\\acer\\Desktop\\programme codes\\OOP mini project\\creative-.jpg")) ; 
         head.setBounds(170, 225, 135, 146);
         jfr.add(head) ; 
        
        JLabel lab = new JLabel("Welcome to vixigo , one place  get your ideal hotels at ideal prices.. ..   ") ; 
        jfr.add(lab) ; 
        lab.setBounds(200,300,700,200) ; 
        lab.setFont(new Font("Times New Roman" ,Font.ITALIC,20));
        lab.setForeground(Color.BLACK);
        
        jfr.setVisible(true) ; 
    }
    


    public static void main(String args[]){
        
         SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                 new welcome() ;  
            }
         }) ; 
    }
}
