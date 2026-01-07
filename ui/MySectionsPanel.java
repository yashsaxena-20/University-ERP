package edu.univ.erp.ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import edu.univ.erp.service.CatalogService;
import edu.univ.erp.domain.Section;
import edu.univ.erp.auth.*;
import edu.univ.erp.data.*;
import edu.univ.erp.domain.*;
public class MySectionsPanel extends JPanel
{
    public MySectionsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        DefaultTableModel mod=new DefaultTableModel(new String[]{"ID","Course Title","Time","Room"},0);
        JTable tab=new JTable(mod);
        add(new JScrollPane(tab),BorderLayout.CENTER);
        long uid=UserSession.get().getUser().getUid();
        CatalogService cs=new CatalogService();
        CourseRepository cr=new CourseRepository();
        for(Section s:cs.getSections())
        {
            if(s.getIid()==uid)
            {
                Course c=cr.getOne(s.getCid());
                mod.addRow(new Object[]{s.getSid(),c.getTitle(),s.getTime(),s.getRoom()});
            }
        }
    }
}