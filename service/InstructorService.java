package edu.univ.erp.service;

import edu.univ.erp.data.*;
import edu.univ.erp.domain.Grade;
import java.util.*;

public class InstructorService
{
    private GradeRepository grepo;
    private MaintenanceService maint;
    public InstructorService()
    {
        grepo=new GradeRepository();
        maint=new MaintenanceService();
    }
    public String save(long eid,String comp,double sc)
    {
        if(maint.isOp())
        {
            return "Maintenance ON";
        }
        grepo.save(new Grade(0,eid,comp,sc));
        return "Saved";
    }
    public List<Grade> getGrades(long eid)
    {
        return grepo.getByEnr(eid);
    }
    public String computeFinalGrade(long eid)
    {
        if(maint.isOp())
        {
            return "Maintenance ON";
        }
        List<Grade> grades=grepo.getByEnr(eid);
        Map<String,Double> componentScores=new HashMap<>();
        for(Grade g:grades)
        {
            componentScores.put(g.getComp(),g.getScore());
        }
        double finalScore=0.0;
        finalScore+=componentScores.getOrDefault("Quiz",0.0)*0.15;
        finalScore+=componentScores.getOrDefault("Midterm",0.0)*0.25;
        finalScore+=componentScores.getOrDefault("End-Sem",0.0)*0.40;
        finalScore+=componentScores.getOrDefault("Assignment",0.0)*0.20;
        String finalGrade=convertScoreToGrade(finalScore);
        grepo.updateFinalGrade(eid,finalGrade);
        return "Final Grade Computed:"+finalGrade+" (Score: "+String.format("%.2f",finalScore)+")";
    }
    private String convertScoreToGrade(double finalScore)
    {
        if(finalScore>=90) return "A+(10)";
        if(finalScore>=80) return "A(9)";
        if(finalScore>=70) return "B+(8)";
        if(finalScore>=60) return "B(7)";
        if(finalScore>=50) return "C(6)";
        if(finalScore>=40) return "D(5)";
        return "F(0)";
    }
}