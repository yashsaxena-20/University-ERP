package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AdminHomePanel extends JPanel
{
    JButton b1,b2,b3,b4;
    public AdminHomePanel(ActionListener action)
    {
        setLayout(new GridLayout(2,2,20,20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        b1=mkTile("Add Users","CMD_USR");
        b1.addActionListener(action);
        add(b1);
        b2=mkTile("Manage Courses","CMD_CRS");
        b2.addActionListener(action);
        add(b2);
        b3=mkTile("Assign Courses","CMD_CRS");
        b3.addActionListener(action);
        add(b3);
        b4=mkTile("Maintenance Mode","CMD_MNT");
        b4.addActionListener(action);
        add(b4);
    }
    JButton mkTile(String t,String cmd)
    {
        JButton b=new JButton(t);
        b.setBackground(new Color(33,115,70));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif",Font.BOLD,18));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        // These two lines FORCE the color to show up
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setActionCommand(cmd);
        return b;
    }
}