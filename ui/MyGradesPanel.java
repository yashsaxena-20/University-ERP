package edu.univ.erp.ui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.data.*;
import edu.univ.erp.domain.*;
import edu.univ.erp.auth.UserSession;
import java.util.List;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
public class MyGradesPanel extends JPanel implements ActionListener
{
    JTable tab;
    DefaultTableModel mod;
    JButton bTranscript;
    EnrollmentRepository er;
    GradeRepository gr;
    CourseRepository cr;
    SectionRepository sr;
    long uid;
    public MyGradesPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        mod=new DefaultTableModel(new String[]{"Course Name","Quiz(15%)","Midterm(25%)","End-Sem(40%)","Assignment(20%)","Final Score","Final Grade/GPA"},0);
        tab=new JTable(mod);
        er=new EnrollmentRepository();
        gr=new GradeRepository();
        cr=new CourseRepository();
        sr=new SectionRepository();
        uid=UserSession.get().getUser().getUid();
        loadData();
        add(new JScrollPane(tab),BorderLayout.CENTER);
        JPanel bottomPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bTranscript=new JButton("Download Transcript");
        bTranscript.addActionListener(this);
        bottomPanel.add(bTranscript);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    void loadData()
    {
        mod.setRowCount(0);
        List<Enrollment> enrollments=er.getByStu(uid);
        for(Enrollment e:enrollments)
        {
            Section s=sr.getOne(e.getSid());
            if(s==null){continue;}
            Course c=cr.getOne(s.getCid());
            String courseName=(c!=null)?c.getTitle():"Unknown Course";
            List<Grade> components=gr.getByEnr(e.getEid());
            HashMap<String,Double> scores=new HashMap<>();
            double finalScore=0.0;
            String finalGrade="N/A";
            for(Grade g:components)
            {
                scores.put(g.getComp(),g.getScore());
            }
            finalScore+=scores.getOrDefault("Quiz",0.0)*0.15;
            finalScore+=scores.getOrDefault("Midterm",0.0)*0.25;
            finalScore+=scores.getOrDefault("End-Sem",0.0)*0.40;
            finalScore+=scores.getOrDefault("Assignment",0.0)*0.20;
            if(finalScore>=90)
                finalGrade="A+ (10)";
            else if(finalScore>=80)
                finalGrade="A (9)";
            else if(finalScore>=70)
                finalGrade="B+ (8)";
            else if(finalScore>=60)
                finalGrade="B (7)";
            else if(finalScore>=50)
                finalGrade="C (6)";
            else if(finalScore>=40)
                finalGrade="D (5)";
            else
                finalGrade="F (-)";
            mod.addRow(new Object[]{courseName,scores.getOrDefault("Quiz",0.0),scores.getOrDefault("Midterm",0.0),scores.getOrDefault("End-Sem",0.0),scores.getOrDefault("Assignment",0.0),String.format("%.2f",finalScore),finalGrade});
        }
    }

    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource()==bTranscript)
        {
            String fileName="Transcript_"+UserSession.get().getUser().getUid()+".csv";
            try(FileWriter writer=new FileWriter(fileName))
            {
                for(int i=0;i<mod.getColumnCount();i++){
                    writer.append(mod.getColumnName(i));
                    if(i<mod.getColumnCount()-1)writer.append(",");
                }
                writer.append("\n");
                for(int r=0;r<mod.getRowCount();r++){
                    for(int c=0;c<mod.getColumnCount();c++){
                        writer.append(String.valueOf(mod.getValueAt(r,c)));
                        if(c<mod.getColumnCount()-1)writer.append(",");
                    }
                    writer.append("\n");
                }
                JOptionPane.showMessageDialog(this,"Transcript saved to file: "+fileName);
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(this,"Error saving file: "+e.getMessage());
            }
        }
    }
}