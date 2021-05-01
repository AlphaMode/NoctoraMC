package Main.utils;

import Main.Noctora;
import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Refresh extends BukkitRunnable {
    private Noctora plugin;
    public Refresh(Noctora pl) {
        plugin = pl;
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    private int i = 0;
    @Override
    public void run() {
        NoctoraPlayer.refreshTab();
        for(NoctoraPlayer NPlayer : NoctoraPlayer.getOnlineNoctoraPlayers()) {
            NPlayer.getScoreboard().Refresh();
            String bio = (String) Noctora.getPlugin(Noctora.class).getPinfo().getData().get(NPlayer.getBukkitPlayer().getUniqueId()+".bio");
            if(bio != null) {
                NPlayer.setBio(bio);
            }
            TABAPI.setValueTemporarily(NPlayer.getBukkitPlayer().getUniqueId(), EnumProperty.CUSTOMTAGNAME,NPlayer.getPrefix() +" "+ NPlayer.getName());
            TABAPI.setValueTemporarily(NPlayer.getBukkitPlayer().getUniqueId(),EnumProperty.CUSTOMTABNAME, NPlayer.getPrefix()+" " + NPlayer.getName());
            NPlayer.getBukkitPlayer().setPlayerListHeaderFooter(Noctora.getPlugin(Noctora.class).getMessage("prefix"), ChatColor.BLUE+"play.noctora.com");
        }
    }
}
