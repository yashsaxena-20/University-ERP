package edu.univ.erp.domain;
public class Student
{
    private long uid;
    private String roll;
    private String prog;
    private int year;
    public Student(long uid,String roll,String prog,int year)
    {
        this.uid=uid;
        this.roll=roll;
        this.prog=prog;
        this.year=year;
    }
    public long getUid()
    {
        return uid;
    }
    public String getRoll()
    {
        return roll;
    }
    public String getProg()
    {
        return prog;
    }
    public int getYear()
    {
        return year;
    }
}