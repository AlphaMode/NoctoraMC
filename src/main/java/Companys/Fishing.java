package Companys;

import Main.Noctora;
import Main.utils.NoctoraPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import java.util.HashMap;

public class Fishing implements Job,Listener {
    public Fishing() {

    }
    public Listener getEvents() {
        return null;
    }

    public Company getCompany() {
        return null;
    }

    public double getRatePay(NoctoraPlayer NP) {
        return 0;
    }

    public void setRatePay(NoctoraPlayer NP, double rate) {

    }

    public double getDefaultRatePay() {
        return 0;
    }

    public void setDefaultRatePay(double rate) {

    }
    private HashMap<NoctoraPlayer,Integer> FISHYS = new HashMap<NoctoraPlayer,Integer>();
    //EVENTS
    @EventHandler
    public void onFish(PlayerFishEvent e) {
        NoctoraPlayer NPlayer = NoctoraPlayer.getNPlayer(e.getPlayer());
        if(e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY || e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if(FISHYS.containsKey(NoctoraPlayer.getNPlayer(e.getPlayer()))) {
                if(FISHYS.get(NPlayer) >= 64) {
                    FISHYS.replace(NPlayer, 0);
                    Noctora.getPlugin(Noctora.class).getEco().depositPlayer(NPlayer.getBukkitPlayer(), 100);
                    return;
                }
                FISHYS.replace(NoctoraPlayer.getNPlayer(e.getPlayer()), FISHYS.get(NoctoraPlayer.getNPlayer(e.getPlayer()))+ 1);
            }else{
                FISHYS.put(NoctoraPlayer.getNPlayer(e.getPlayer()), 1);
            }
        }
    }
    //@EventHandler
    //public void onNPCRightClick(NPCRightClickEvent e) {
     //   NoctoraPlayer NPlayer = NoctoraPlayer.getNPlayer(e.getClicker());
    //    NPlayer.sendMessage(e.getNPC().getFullName());
    //    NPlayer.sendMessage(e.getNPC().getName());
    //}
}