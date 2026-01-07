package edu.univ.erp.domain;
public class AppSetting
{
    private String key;
    private String val;
    public AppSetting(String key,String val)
    {
        this.key=key;
        this.val=val;
    }
    public String getKey()
    {
        return key;
    }
    public String getVal()
    {
        return val;
    }
}