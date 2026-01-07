package edu.univ.erp.service;

import edu.univ.erp.data.SettingsRepository;

public class MaintenanceService
{
    private SettingsRepository repo;

    public MaintenanceService()
    {
        repo=new SettingsRepository();
    }

    public boolean isOp()
    {
        String v=repo.getVal("maintenance_on");
        return "true".equals(v);
    }

    public void setMode(boolean on)
    {
        repo.setVal("maintenance_on",on?"true":"false");
    }
}