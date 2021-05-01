package Companys;

import Main.utils.NoctoraPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Company {

    private String name;
    private ArrayList<UUID> Founders = new ArrayList<UUID>();
    private List<Job> Jobs_GLOBAL = new ArrayList<Job>();

    private static ArrayList<Company> companies = new ArrayList<Company>();

    public static boolean ownsCompany(NoctoraPlayer NP) {
        for(Company c : Company.companies) {
            for(UUID id : c.getFounders()) {
                if(NP.getBukkitPlayer().getUniqueId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static Company getCompany(String o) {
        for(Company com : companies) {
            if(com.getName().equals(o))
                return com;
        }
        return null;
    }

    public Company(String name, NoctoraPlayer p) {
        this.name = name;
        Founders.add(p.getBukkitPlayer().getUniqueId());
    }

    public ArrayList<UUID> getFounders() {
        return Founders;
    }

    public String getName() {
        return this.name;
    }
}
