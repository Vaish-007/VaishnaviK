//package ATM_Simulator_System;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;

    Login(){


        //Move the text to the center

        setFont(new Font("System", Font.BOLD, 22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("AUTOMATED TELLER MACHINE");
        int y = fm.stringWidth(" ");
        int z = getWidth() - x;
        int w = z/y;
        String pad ="";
        //for (int i=0; i!=w; i++) pad +=" ";   
        pad = String.format("%"+w+"s", pad);
        setTitle(pad+"AUTOMATED TELLER MACHINE");






        l1 = new JLabel("WELCOME TO ATM");
        l1.setFont(new Font("Osward", Font.BOLD, 38));

        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));

        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));

        tf1 = new JTextField(15);
        pf2 = new JPasswordField(15);

        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);

        setLayout(null); // we don't want to use an in-built layout, we create our own layout as we want.


        //setBounds() is a method of the java.awt.Component class.
        //It is used to set the position and size of a component.
        // setBounds(int x,int y,int width,int height)

        l1.setBounds(175,50,450,200); // set
        add(l1); // display

        l2.setBounds(125,150,375,200);
        add(l2);

        l3.setBounds(125,225,375,200);
        add(l3);

        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        tf1.setBounds(300,235,230,30);
        add(tf1);

        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300,310,230,30);
        add(pf2);

        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,400,100,30);
        add(b1);

        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,400,100,30);
        add(b2);

        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300,450,230,30);
        add(b3);

        // when the button is clicked some action is performed.
        // whichever class implements ActionListener, overrides the actionPerformed() method.

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        getContentPane().setBackground(Color.WHITE); // by default background has different color, set white color.

        setSize(750,750); // size of the frame
        setLocation(500,200);   // location of the frame on the screen.
        setVisible(true);           // by default is false. For the frame to be visible set true.



    }
    public void actionPerformed(ActionEvent ae){

        try{
            conn c1 = new conn();      // creating an object of conn class, gives connection to database.
            String a  = tf1.getText(); // getText() retrieves data from the fields.
            String b  = pf2.getText();
            String q  = "select * from login where cardno = '"+a+"' and pin = '"+b+"'";
            ResultSet rs = c1.s.executeQuery(q);

            // A ResultSet is a Java object that stores the result of executing an sql query.
            // It contains the rows that satisfy the conditions of the query.
            // executeQuery(q) is used when we want to retrieve data from database.


            // if action event is b1 ie sign in, then the following action will be performed.

            if(ae.getSource()==b1){
                if(rs.next()){            // next() is used to jump to next column in a table.
                    new Transactions().setVisible(true);       // displays transaction frame
                    setVisible(false);                         // closes the current frame.

                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Password");

                    // JOptionPane gives a pop with a message displayed in it.
                }
            }else if(ae.getSource()==b2){       // b2 is clear, we make the fields empty.
                tf1.setText("");
                pf2.setText("");
            }else if(ae.getSource()==b3){
                new Signup().setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error: "+e);
        }

    }


    public static void main(String[] args){
        new Login().setVisible(true);
    }


}
