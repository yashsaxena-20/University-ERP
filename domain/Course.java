package edu.univ.erp.domain;
public class Course
{
    private String code;
    private String title;
    private int credit;
    public Course(String code,String title,int credit)
    {
        this.code=code;
        this.title=title;
        this.credit=credit;
    }
    public String getCode()
    {
        return code;
    }
    public String getTitle()
    {
        return title;
    }
    public int getCredit()
    {
        return credit;
    }
}