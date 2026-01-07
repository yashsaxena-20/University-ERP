package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.service.AdminService;
public class ManageUsersPanel extends JPanel implements ActionListener
{
    JTextField u,p,r,pr,y,d;
    JComboBox<String> role;
    JButton btn;
    AdminService as;
    public ManageUsersPanel()
    {
        setLayout(new GridLayout(8,2,10,10));
        setBackground(Color.WHITE);
        as=new AdminService();
        add(new JLabel("User:"));u=new JTextField();add(u);
        add(new JLabel("Pass:"));p=new JTextField();add(p);
        add(new JLabel("Role:"));role=new JComboBox<>(new String[]{"Student","Instructor"});add(role);
        add(new JLabel("Roll:"));r=new JTextField();add(r);
        add(new JLabel("Prog:"));pr=new JTextField();add(pr);
        add(new JLabel("Year:"));y=new JTextField();add(y);
        add(new JLabel("Dept:"));d=new JTextField();add(d);
        add(new JLabel(""));
        btn=new JButton("Create");
        btn.addActionListener(this);
        add(btn);
    }
    public void actionPerformed(ActionEvent e)
    {
        String ro=(String)role.getSelectedItem();
        if(ro.equals("Student"))
        {
            JOptionPane.showMessageDialog(this,as.addStu(u.getText(),p.getText(),r.getText(),pr.getText(),Integer.parseInt(y.getText())));
        }
        else
        {
            JOptionPane.showMessageDialog(this,as.addInst(u.getText(),p.getText(),d.getText()));
        }
    }
}