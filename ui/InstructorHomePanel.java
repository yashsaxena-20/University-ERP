package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class InstructorHomePanel extends JPanel
{
    JButton b1,b2,b3;
    public InstructorHomePanel(ActionListener action)
    {
        setLayout(new GridLayout(2,2,20,20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        b1=mkTile("My Sections","CMD_SEC");
        b1.addActionListener(action);
        add(b1);
        b2=mkTile("Scores","CMD_GRD");
        b2.addActionListener(action);
        add(b2);
        b3=mkTile("Class Stats","CMD_STS");
        b3.addActionListener(action);
        add(b3);
    }
    JButton mkTile(String t,String cmd)
    {
        JButton b=new JButton(t);
        b.setBackground(new Color(0,51,102)); // Dark Blue
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