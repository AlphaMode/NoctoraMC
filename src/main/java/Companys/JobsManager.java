package Companys;

import Main.Noctora;

import java.util.ArrayList;

public class JobsManager {
    private ArrayList<Job> jobs = new ArrayList<Job>();
    public ArrayList<Job> getJobs() {
        return jobs;
    }
    public void registerJob(Job j) {
        jobs.add(j);
        Noctora.getPlugin(Noctora.class).getServer().getPluginManager().registerEvents(j,Noctora.getPlugin(Noctora.class));
        //CitizensAPI.registerEvents(j);
    }
}
