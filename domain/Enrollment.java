package edu.univ.erp.domain;

public class Enrollment
{
    private long eid;
    private long uid;
    private long sid;
    private String stat;
    public Enrollment(long eid,long uid,long sid,String stat)
    {
        this.eid=eid;
        this.uid=uid;
        this.sid=sid;
        this.stat=stat;
    }
    public long getEid()
    {
        return eid;
    }
    public long getUid()
    {
        return uid;
    }
    public long getSid()
    {
        return sid;
    }
    public String getStat()
    {
        return stat;
    }
}