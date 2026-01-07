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
public class GradeEntryPanel extends JPanel implements ActionListener
{
    JComboBox<String> box;
    JTable tab;
    DefaultTableModel mod;
    JTextField t1,t2;
    JButton btn;
    JButton btnFinal;
    CatalogService cs;
    InstructorService is;
    EnrollmentRepository er;
    public GradeEntryPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        cs=new CatalogService();
        is=new InstructorService();
        er=new EnrollmentRepository();
        JPanel top=new JPanel();
        top.setBackground(Color.WHITE);
        top.add(new JLabel("Section:"));
        box=new JComboBox<>();
        long uid=UserSession.get().getUser().getUid();
        for(Section s:cs.getSections()){if(s.getIid()==uid){box.addItem(""+s.getSid());}}
        box.addActionListener(this);
        top.add(box);
        add(top,BorderLayout.NORTH);
        mod=new DefaultTableModel(new String[]{"EnrID","StuID"},0);
        tab=new JTable(mod);
        add(new JScrollPane(tab),BorderLayout.CENTER);
        JPanel bot=new JPanel();
        bot.setBackground(Color.WHITE);
        bot.add(new JLabel("Item:"));
        t1=new JTextField(8);bot.add(t1);
        bot.add(new JLabel("Score:"));
        t2=new JTextField(5);bot.add(t2);
        btn=new JButton("Save Score");
        btn.addActionListener(this);
        bot.add(btn);
        
        btnFinal=new JButton("Compute Final Grade"); // NEW BUTTON
        btnFinal.addActionListener(this);
        bot.add(btnFinal);

        add(bot,BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==box)
        {
            mod.setRowCount(0);
            String s=(String)box.getSelectedItem();
            if(s!=null){
                for(Enrollment en:er.getBySec(Long.parseLong(s))){mod.addRow(new Object[]{en.getEid(),en.getUid()});}
            }
        }
        else if(e.getSource()==btn)
        {
            int r=tab.getSelectedRow();
            if(r>=0)
            {
                long eid=(long)tab.getValueAt(r,0);
                is.save(eid,t1.getText(),Double.parseDouble(t2.getText()));
                JOptionPane.showMessageDialog(this,"Saved");
            }
        }
        else if(e.getSource()==btnFinal) // NEW LOGIC FOR FINAL GRADE
        {
            int r=tab.getSelectedRow();
            if(r>=0)
            {
                long eid=(long)tab.getValueAt(r,0);
                String result=is.computeFinalGrade(eid);
                JOptionPane.showMessageDialog(this,result);
            }
        }
    }
}