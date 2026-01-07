package edu.univ.erp.ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.service.*;
import edu.univ.erp.domain.*;
import edu.univ.erp.data.*;
import edu.univ.erp.auth.UserSession;
import java.util.List;
public class MyRegistrationsPanel extends JPanel implements ActionListener
{
    JTable t1,t2;
    DefaultTableModel m1,m2;
    JButton b1,b2;
    CatalogService cat;
    StudentService ss;
    EnrollmentRepository er;
    long uid;
    public MyRegistrationsPanel()
    {
        setLayout(new GridLayout(2,1,0,10));
        setBackground(Color.WHITE);
        cat=new CatalogService();
        ss=new StudentService();
        er=new edu.univ.erp.data.EnrollmentRepository();
        uid=UserSession.get().getUser().getUid();
        JPanel p1=new JPanel(new BorderLayout());
        p1.setBackground(Color.WHITE);
        p1.setBorder(BorderFactory.createTitledBorder("Available Sections"));
        m1=new DefaultTableModel(new String[]{"Enrollment Id","Code","Title","Time","Room","Cap"},0);
        t1=new JTable(m1);
        p1.add(new JScrollPane(t1),BorderLayout.CENTER);
        b1=new JButton("Register");
        b1.addActionListener(this);
        p1.add(b1,BorderLayout.SOUTH);
        JPanel p2=new JPanel(new BorderLayout());
        p2.setBackground(Color.WHITE);
        p2.setBorder(BorderFactory.createTitledBorder("My Classes"));
        m2=new DefaultTableModel(new String[]{"Cours","SecID","Status"},0);
        t2=new JTable(m2);
        p2.add(new JScrollPane(t2),BorderLayout.CENTER);
        b2=new JButton("Drop");
        b2.addActionListener(this);
        p2.add(b2,BorderLayout.SOUTH);
        add(p1);add(p2);
        load();
    }
    void load()
    {
        m1.setRowCount(0);
        CourseRepository cr=new CourseRepository(); 
        for(Section s:cat.getSections())
        {
            Course c=cr.getOne(s.getCid()); 
            m1.addRow(new Object[]{s.getSid(),c.getCode(),c.getTitle(),s.getTime(),s.getRoom(),s.getCap()});
        }
        m2.setRowCount(0);
        for(Enrollment e:er.getByStu(uid)){m2.addRow(new Object[]{e.getEid(),e.getSid(),e.getStat()});}
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            int r=t1.getSelectedRow();
            if(r>=0)
            {
                long sid=(long)t1.getValueAt(r,0);JOptionPane.showMessageDialog(this,ss.reg(uid,sid));
                load();
            }
        }
        else if(e.getSource()==b2)
        {
            int r=t2.getSelectedRow();
            if(r>=0)
            {
                long eid=(long)t2.getValueAt(r,0);JOptionPane.showMessageDialog(this,ss.drop(eid));
                load();
            }
        }
    }
}