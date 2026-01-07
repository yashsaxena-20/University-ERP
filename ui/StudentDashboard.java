package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentDashboard extends JFrame implements ActionListener
{
    JPanel side,main;
    CardLayout cards;
    JButton b1,b2,b3,b4,bCP,out; // bCP is NEW
    
    public StudentDashboard()
    {
        setTitle("Student Portal");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        side=new JPanel(new GridLayout(10,1,0,5));
        side.setBackground(new Color(33,115,70));
        side.setPreferredSize(new Dimension(200,600));
        side.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel l=new JLabel("Student",JLabel.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("SansSerif",Font.BOLD,18));
        side.add(l);
        
        b1=mkBtn("Catalog");
        b2=mkBtn("Registrations");
        b3=mkBtn("Grades");
        b4=mkBtn("TimeTable");
        bCP=mkBtn("Change Password"); // NEW BUTTON
        out=mkBtn("Logout");
        side.add(b1);side.add(b2);side.add(b3);side.add(b4); 
        side.add(new JLabel());
        side.add(bCP); // ADDED
        side.add(out);
        add(side,BorderLayout.WEST);
        cards=new CardLayout();
        main=new JPanel(cards);
        main.setBackground(Color.WHITE);
        main.add(new StudentHomePanel(this), "HOME"); 
        
        main.add(new CourseCatalogPanel(),"CAT");
        main.add(new MyRegistrationsPanel(),"REG");
        main.add(new MyGradesPanel(),"GRD");
        main.add(new TimetablePanel(),"TIME"); 
        add(main,BorderLayout.CENTER);
        cards.show(main, "HOME"); 
    }
    
    JButton mkBtn(String t)
    {
        JButton b=new JButton(t);
        b.setBackground(new Color(33,115,70));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.addActionListener(this);
        return b;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        String command = e.getActionCommand();
        
        if(source==b1 || "CMD_CAT".equals(command)){
            cards.show(main,"CAT");
        }
        else if(source==b2 || "CMD_REG".equals(command)){
            cards.show(main,"REG");
        }
        else if(source==b3 || "CMD_GRD".equals(command)){
            cards.show(main,"GRD");
        }
        else if(source==b4 || "CMD_TIME".equals(command)){ 
            cards.show(main,"TIME");
        }
        else if(source==bCP){ // NEW LOGIC
            new ChangePasswordDialog(this).setVisible(true);
        }
        else if(source==out){
            new LoginWindow().setVisible(true);
            dispose();
        }
    }
}