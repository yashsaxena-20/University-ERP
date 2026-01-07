package edu.univ.erp.domain;
public class Section
{
    private long sid;
    private long cid;
    private long iid;
    private String time;
    private String room;
    private int cap;
    public Section(long sid,long cid,long iid,String time,String room,int cap)
    {
        this.sid=sid;
        this.cid=cid;
        this.iid=iid;
        this.time=time;
        this.room=room;
        this.cap=cap;
    }
    public long getSid()
    {
        return sid;
    }
    public long getCid()
    {
        return cid;
    }
    public long getIid()
    {
        return iid;
    }
    public String getTime()
    {
        return time;
    }
    public String getRoom()
    {
        return room;
    }
    public int getCap()
    {
        return cap;
    }
}