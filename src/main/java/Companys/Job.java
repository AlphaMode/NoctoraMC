package Companys;

import Main.utils.NoctoraPlayer;
import org.bukkit.event.Listener;

public interface Job extends Listener {
    public Listener getEvents();
    public Company getCompany();
    public double getRatePay(NoctoraPlayer NP);
    public void setRatePay(NoctoraPlayer NP,double rate);
    public double getDefaultRatePay();
    public void setDefaultRatePay(double rate);
}
