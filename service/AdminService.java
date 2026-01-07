package edu.univ.erp.service;

import edu.univ.erp.auth.*;
import edu.univ.erp.data.*;
import edu.univ.erp.domain.*;

public class AdminService
{
    private AuthRepository auth;
    private StudentRepository srepo;
    private InstructorRepository irepo;
    private CourseRepository crepo;
    private SectionRepository secrepo;

    public AdminService()
    {
        auth=new AuthRepository();
        srepo=new StudentRepository();
        irepo=new InstructorRepository();
        crepo=new CourseRepository();
        secrepo=new SectionRepository();
    }

    public String addStu(String u,String p,String r,String pg,int y)
    {
        long id=auth.addUser(u,PasswordUtil.hash(p),"Student");
        if(id>0)
        {
            srepo.add(new Student(id,r,pg,y));
            return "Added Student";
        }
        return "Fail";
    }

    public String addInst(String u,String p,String d)
    {
        long id=auth.addUser(u,PasswordUtil.hash(p),"Instructor");
        if(id>0)
        {
            irepo.add(new Instructor(id,d));
            return "Added Instructor";
        }
        return "Fail";
    }

    public void addCourse(Course c)
    {
        crepo.add(c);
    }

    public void addSection(Section s)
    {
        secrepo.add(s);
    }
}