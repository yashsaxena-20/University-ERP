package edu.univ.erp.domain;
public class User
{
    private long uid;
    private String role;
    private String hash;
    public User(long uid,String role)
    {
        this.uid=uid;
        this.role=role;
    }
    public long getUid()
    {
        return uid;
    }
    public String getRole()
    {
        return role;
    }
    public String getHash()
    {
        return hash;
    }
    public void setHash(String hash)
    {
        this.hash=hash;
    }
}