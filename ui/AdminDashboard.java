package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame implements ActionListener
{
    JPanel side,main;
    CardLayout cards;
    JButton bHome,b1,b2,b3,bCP,out; // bCP is NEW
    public AdminDashboard()
    {
        setTitle("Admin Dashboard");
        setSize(1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Sidebar
        side=new JPanel(new GridLayout(10,1,0,5));
        side.setBackground(new Color(33,115,70));
        side.setPreferredSize(new Dimension(200,600));
        side.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel l=new JLabel("Admin",JLabel.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("SansSerif",Font.BOLD,18));
        side.add(l);
        bHome=mkBtn("Dashboard");
        b1=mkBtn("Users");
        b2=mkBtn("Courses");
        b3=mkBtn("Maintenance");
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
        // Panels
        main.add(new AdminHomePanel(this),"HOME");
        main.add(new ManageUsersPanel(),"CMD_USR");
        main.add(new ManageCoursesPanel(),"CMD_CRS");
        main.add(new MaintenancePanel(),"CMD_MNT");
        add(main,BorderLayout.CENTER);
    }
    JButton mkBtn(String t)
    {
        JButton b=new JButton(t);
        b.setBackground(new Color(33,115,70));
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
        // Navigation logic
        if(e.getSource()==bHome){cards.show(main,"HOME");}
        else if(e.getSource()==b1){cards.show(main,"CMD_USR");}
        else if(e.getSource()==b2){cards.show(main,"CMD_CRS");}
        else if(e.getSource()==b3){cards.show(main,"CMD_MNT");}
        else if(e.getSource()==bCP){new ChangePasswordDialog(this).setVisible(true);} // NEW LOGIC
        else if(e.getSource()==out){new LoginWindow().setVisible(true);dispose();}
        else
        {
            // Handle clicks from the 4 Green Squares
            if(cmd.equals("CMD_USR")||cmd.equals("CMD_CRS")||cmd.equals("CMD_MNT"))
            {
                cards.show(main,cmd);
            }
        }
    }
}