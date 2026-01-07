package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StudentHomePanel extends JPanel
{
    JButton b1,b2,b3,b4;
    public StudentHomePanel(ActionListener action)
    {
        setLayout(new GridLayout(2,2,20,20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        b1=mkTile("Course Catalog","CMD_CAT");
        b1.addActionListener(action);
        add(b1);
        b2=mkTile("My Registrations","CMD_REG");
        b2.addActionListener(action);
        add(b2);
        b3=mkTile("My Grades","CMD_GRD");
        b3.addActionListener(action);
        add(b3);
        b4=mkTile("My TimeTable","CMD_TIME");
        b4.addActionListener(action);
        add(b4);
    }
    JButton mkTile(String t,String cmd)
    {
        JButton b=new JButton(t);
        b.setBackground(new Color(139,0,0)); // Dark Red
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif",Font.BOLD,18));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setActionCommand(cmd);
        return b;
    }
}