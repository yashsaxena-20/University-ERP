package edu.univ.erp.ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import edu.univ.erp.service.CatalogService;
import edu.univ.erp.domain.*;
import java.util.List;
import edu.univ.erp.auth.*;
import edu.univ.erp.data.*;
public class CourseCatalogPanel extends JPanel
{
    public CourseCatalogPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        DefaultTableModel mod=new DefaultTableModel(new String[]{"Code","Title","Cred","Instructor","Time","Room","Capacity"},0);
        JTable tab=new JTable(mod);
        add(new JScrollPane(tab),BorderLayout.CENTER);
        CatalogService cat=new CatalogService();
        CourseRepository cr=new CourseRepository();
        AuthRepository ar=new AuthRepository();
        List<Course> l=cat.getCourses();
        List<Section> sections=cat.getSections();
        for(Section s:sections)
        {
            Course c=cr.getOne(s.getCid());
            String name=ar.getUsernameById(s.getIid());
            mod.addRow(new Object[]{c.getCode(),c.getTitle(),c.getCredit(),name,s.getTime(),s.getRoom(),s.getCap()});
        }
    }
}