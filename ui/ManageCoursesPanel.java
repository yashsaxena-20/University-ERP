package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.domain.*;
import edu.univ.erp.service.AdminService;
public class ManageCoursesPanel extends JPanel implements ActionListener
{
    JTextField c1,t1,cr1;
    JButton b1;
    JTextField cid,iid,tm,rm,cp;
    JButton b2;
    AdminService as;
    public ManageCoursesPanel()
    {
        setLayout(new GridLayout(2,1));
        setBackground(Color.WHITE);
        as=new AdminService();
        JPanel p1=new JPanel(new GridLayout(4,2));
        p1.setBackground(Color.WHITE);
        p1.setBorder(BorderFactory.createTitledBorder("Course"));
        p1.add(new JLabel("Code:"));c1=new JTextField();p1.add(c1);
        p1.add(new JLabel("Title:"));t1=new JTextField();p1.add(t1);
        p1.add(new JLabel("Cred:"));cr1=new JTextField();p1.add(cr1);
        p1.add(new JLabel(""));b1=new JButton("Add Course");b1.addActionListener(this);p1.add(b1);
        JPanel p2=new JPanel(new GridLayout(6,2));
        p2.setBackground(Color.WHITE);
        p2.setBorder(BorderFactory.createTitledBorder("Section"));
        p2.add(new JLabel("CrsID:"));cid=new JTextField();p2.add(cid);
        p2.add(new JLabel("InsID:"));iid=new JTextField();p2.add(iid);
        p2.add(new JLabel("Time:"));tm=new JTextField();p2.add(tm);
        p2.add(new JLabel("Room:"));rm=new JTextField();p2.add(rm);
        p2.add(new JLabel("Cap:"));cp=new JTextField();p2.add(cp);
        p2.add(new JLabel(""));b2=new JButton("Add Section");b2.addActionListener(this);p2.add(b2);
        add(p1);add(p2);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            as.addCourse(new Course(c1.getText(),t1.getText(),Integer.parseInt(cr1.getText())));
            JOptionPane.showMessageDialog(this,"Done");
        }
        else if(e.getSource()==b2)
        {
            as.addSection(new Section(0,Long.parseLong(cid.getText()),Long.parseLong(iid.getText()),tm.getText(),rm.getText(),Integer.parseInt(cp.getText())));
            JOptionPane.showMessageDialog(this,"Done");
        }
    }
}