package edu.univ.erp.domain;
public class Instructor
{
    private long uid;
    private String dept;
    public Instructor(long uid,String dept)
    {
        this.uid=uid;
        this.dept=dept;
    }
    public long getUid()
    {
        return uid;
    }
    public String getDept()
    {
        return dept;
    }
}