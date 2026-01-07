package edu.univ.erp.ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import edu.univ.erp.service.*;
import edu.univ.erp.domain.*;
import edu.univ.erp.data.*;
import edu.univ.erp.auth.UserSession;
public class StatsPanel extends JPanel
{
    JTable tab;
    DefaultTableModel mod;
    public StatsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Class Performance Stats"));
        mod=new DefaultTableModel(new String[]{"Section","Count","Avg Score","High Score"},0);
        tab=new JTable(mod);
        add(new JScrollPane(tab),BorderLayout.CENTER);
        calc();
    }
    void calc()
    {
        long uid=UserSession.get().getUser().getUid();
        CatalogService cs=new CatalogService();
        EnrollmentRepository er=new EnrollmentRepository();
        GradeRepository gr=new GradeRepository();
        List<Section> secs=cs.getSections();
        for(Section s:secs)
        {
            if(s.getIid()==uid)
            {
                List<Double> scores=new ArrayList<>();
                List<Enrollment> enrs=er.getBySec(s.getSid());
                for(Enrollment e:enrs)
                {
                    List<Grade> gs=gr.getByEnr(e.getEid());
                    for(Grade g:gs){scores.add(g.getScore());}
                }
                double sum=0;
                double max=0;
                if(scores.size()>0)
                {
                    for(Double d:scores)
                    {
                        sum=sum+d;
                        if(d>max){max=d;}
                    }
                    double avg=sum/scores.size();
                    mod.addRow(new Object[]{s.getSid(),enrs.size(),String.format("%.2f",avg),max});
                }
                else
                {
                    mod.addRow(new Object[]{s.getSid(),enrs.size(),"0.0","0.0"});
                }
            }
        }
    }
}