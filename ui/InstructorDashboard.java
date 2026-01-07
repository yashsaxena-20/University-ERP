package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InstructorDashboard extends JFrame implements ActionListener
{
    JPanel side,main;
    CardLayout cards;
    JButton bHome,b1,b2,b3,bCP,out; // bCP is NEW
    public InstructorDashboard()
    {
        setTitle("Instructor Portal");
        setSize(1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Sidebar
        side=new JPanel(new GridLayout(10,1,0,5));
        side.setBackground(new Color(0,51,102)); // Dark Blue
        side.setPreferredSize(new Dimension(200,600));
        side.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel l=new JLabel("Instructor",JLabel.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("SansSerif",Font.BOLD,18));
        side.add(l);
        bHome=mkBtn("Dashboard");
        b1=mkBtn("My Sections");
        b2=mkBtn("Scores");
        b3=mkBtn("Stats");
        bCP=mkBtn("Change Password"); // NEW BUTTON
        out=mkBtn("Logout");
        side.add(bHome);side.add(b1);side.add(b2);side.add(b3);
        side.add(new JLabel());
        side.add(bCP); // ADDED
        side.add(out);
        add(side,BorderLayout.WEST);
        
        // Main Content
        cards=new CardLayout();
        main=new JPanel(cards);
        main.setBackground(Color.WHITE);
        main.add(new InstructorHomePanel(this),"HOME");
        main.add(new MySectionsPanel(),"CMD_SEC");
        main.add(new GradeEntryPanel(),"CMD_GRD");
        main.add(new StatsPanel(),"CMD_STS");
        add(main,BorderLayout.CENTER);
    }
    JButton mkBtn(String t)
    {
        JButton b=new JButton(t);
        b.setBackground(new Color(0,51,102)); // Dark Blue
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.addActionListener(this);
        return b;
    }
    public void actionPerformed(ActionEvent e)
    {
        String cmd=e.getActionCommand();
        if(e.getSource()==bHome){cards.show(main,"HOME");}
        else if(e.getSource()==b1){cards.show(main,"CMD_SEC");}
        else if(e.getSource()==b2){cards.show(main,"CMD_GRD");}
        else if(e.getSource()==b3){cards.show(main,"CMD_STS");}
        else if(e.getSource()==bCP){new ChangePasswordDialog(this).setVisible(true);} // NEW LOGIC
        else if(e.getSource()==out){new LoginWindow().setVisible(true);dispose();}
        else
        {
            if(cmd.equals("CMD_SEC")||cmd.equals("CMD_GRD")||cmd.equals("CMD_STS"))
            {
                cards.show(main,cmd);
            }
        }
    }
}