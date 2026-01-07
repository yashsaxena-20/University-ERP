package edu.univ.erp.domain;

public class Grade
{
    private long gid;
    private long eid;
    private String comp;
    private double score;
    public Grade(long gid,long eid,String comp,double score)
    {
        this.gid=gid;
        this.eid=eid;
        this.comp=comp;
        this.score=score;
    }
    public long getGid()
    {
        return gid;
    }
    public long getEid()
    {
        return eid;
    }
    public String getComp()
    {
        return comp;
    }
    public double getScore()
    {
        return score;
    }
}