package edu.univ.erp.service;

import edu.univ.erp.data.*;
import edu.univ.erp.domain.*;
import java.util.List;

public class StudentService
{
    private EnrollmentRepository erepo;
    private SectionRepository srepo;
    private MaintenanceService maint;

    public StudentService()
    {
        erepo=new EnrollmentRepository();
        srepo=new SectionRepository();
        maint=new MaintenanceService();
    }

    public String reg(long stu,long sec)
    {
        if(maint.isOp())
        {
            return "Maintenance ON";
        }
        Section s=srepo.getOne(sec);
        if(s==null)
        {
            return "No Section";
        }
        List<Enrollment> list=erepo.getByStu(stu);
        for(Enrollment e:list)
        {
            if(e.getSid()==sec)
            {
                return "Already in";
            }
        }
        if(erepo.count(sec)>=s.getCap())
        {
            return "Full";
        }
        erepo.add(stu,sec);
        return "Success";
    }

    public String drop(long eid)
    {
        if(maint.isOp())
        {
            return "Maintenance ON";
        }
        erepo.del(eid);
        return "Dropped";
    }
}