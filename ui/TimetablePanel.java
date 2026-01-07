package edu.univ.erp.ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import edu.univ.erp.data.*;
import edu.univ.erp.domain.*;
import edu.univ.erp.auth.UserSession;

public class TimetablePanel extends JPanel
{
    JTable tab;
    DefaultTableModel mod;

    public TimetablePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Weekly Schedule"));

        String[] cols={"Time","Mon","Tue","Wed","Thu","Fri"};
        mod=new DefaultTableModel(cols,0);
        tab=new JTable(mod);
        tab.setRowHeight(60); // Made rows slightly taller to fit 3 lines
        tab.getTableHeader().setBackground(new Color(139,0,0));
        tab.getTableHeader().setForeground(Color.WHITE);
        
        add(new JScrollPane(tab),BorderLayout.CENTER);
        loadData();
    }

    void loadData()
    {
        mod.setRowCount(0);
        for(int i=9;i<=17;i++)
        {
            mod.addRow(new Object[]{i+":00","","","","",""});
        }

        long uid=UserSession.get().getUser().getUid();
        EnrollmentRepository er=new EnrollmentRepository();
        SectionRepository sr=new SectionRepository();
        CourseRepository cr=new CourseRepository(); // We need this now
        
        List<Enrollment> enrs=er.getByStu(uid);

        for(Enrollment e:enrs)
        {
            Section s=sr.getOne(e.getSid());
            if(s!=null)
            {
                // Fetch the Course Code using the new method
                String code=cr.getCode(s.getCid());
                placeInGrid(s,code);
            }
        }
    }

    void placeInGrid(Section s,String code)
    {
        String t=s.getTime(); 
        // New Format: Bold Course Code, then Section ID, then Room
        String txt="<html><b>"+code+"</b><br>Sec: "+s.getSid()+"<br>"+s.getRoom()+"</html>";
        
        int row=-1;
        if(t.contains("9")) row=0;
        else if(t.contains("10")) row=1;
        else if(t.contains("11")) row=2;
        else if(t.contains("12")) row=3;
        else if(t.contains("13")||t.contains("1 PM")) row=4;
        else if(t.contains("14")||t.contains("2 PM")) row=5;
        else if(t.contains("15")||t.contains("3 PM")) row=6;
        else if(t.contains("16")||t.contains("4 PM")) row=7;
        else if(t.contains("17")||t.contains("5 PM")) row=8;

        if(row!=-1)
        {
            if(t.contains("Mon")) mod.setValueAt(txt,row,1);
            if(t.contains("Tue")) mod.setValueAt(txt,row,2);
            if(t.contains("Wed")) mod.setValueAt(txt,row,3);
            if(t.contains("Thu")) mod.setValueAt(txt,row,4);
            if(t.contains("Fri")) mod.setValueAt(txt,row,5);
        }
    }
}