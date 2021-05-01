package Main.utils;

import Companys.Company;
import Main.Noctora;
import com.earth2me.essentials.Essentials;
import net.luckperms.api.model.user.User;
import net.minecraft.server.v1_16_R1.EntityPlayer;
import net.minecraft.server.v1_16_R1.Packet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class NoctoraPlayer {
    //All things static
    private static Noctora plugin = Noctora.getPlugin(Noctora.class);
    public static List<NoctoraPlayer> players = new ArrayList<NoctoraPlayer>();
    private NBoard Scoreboard;
    public static void addAllOnlinePlayers() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            NoctoraPlayer NPlayer = new NoctoraPlayer(p);
        }
        NoctoraPlayer.refreshTab();
    }
    public static NoctoraPlayer getNPlayer(String name) {
        for(NoctoraPlayer noctoraPlayer : players) {
            Bukkit.getLogger().log(Level.INFO,noctoraPlayer.getName());
            if (noctoraPlayer.getBukkitPlayer().getName().equals(name))
                return noctoraPlayer;
        }
        return null;
    }
    public static NoctoraPlayer getNPlayer(Player name) {
        for(NoctoraPlayer noctoraPlayer : players) {
            Bukkit.getLogger().log(Level.INFO,noctoraPlayer.getName());
            if (noctoraPlayer.getBukkitPlayer().getName().equals(name.getName()))
                return noctoraPlayer;
        }
        return null;
    }
    public static void refreshTab() {
        for(NoctoraPlayer NP : players) {
            Player BUPlayer = NP.getBukkitPlayer();
            User pUser = Noctora.LP_api.getUserManager().getUser(BUPlayer.getUniqueId());
            Essentials ess = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
            com.earth2me.essentials.User user = ess.getUser(NP.getBukkitPlayer());

            if (user.getNickname() == null) {
                //TABAPI.setValueTemporarily(NP.getBukkitPlayer().getUniqueId(), EnumProperty.CUSTOMTABNAME,plugin.getChat().getGroupPrefix(BUPlayer.getWorld(), pUser.getPrimaryGroup())+" "+NP.getName());
            } else {
                //TABAPI.setValueTemporarily(NP.getBukkitPlayer().getUniqueId(), EnumProperty.CUSTOMTABNAME,plugin.getChat().getGroupPrefix(BUPlayer.getWorld(), pUser.getPrimaryGroup())+" "+user.getNickname());
                //NametagEdit.getApi().setPrefix(BUPlayer, ChatColor.translateAlternateColorCodes('&', pUser.));
            }
        }
    }
    public static List<NoctoraPlayer> getOnlineNoctoraPlayers() {
        return players;
    }
    //Public
    //Private
    private String NName;
    private String Bio;
    private Player bPlayer;
    private Company Work;
    //Public Methods
    public NoctoraPlayer(Player p) {
        bPlayer = p;
        NName = p.getName();
        Bio = "";
        Scoreboard = new NBoard(this);
        players.add(this);
    }
    public NBoard getScoreboard() {
        return Scoreboard;
    }
    public String getGroup() {
        return Noctora.LP_api.getUserManager().getUser(bPlayer.getUniqueId()).getPrimaryGroup();
    }
    public String getName() {
        Essentials ess = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
        com.earth2me.essentials.User user = ess.getUser(bPlayer);
        if (ess.getUser(bPlayer).getNickname() == null) {
            return NName;
        } else {
            return ess.getUser(bPlayer).getNickname();
        }
    }

    public Company getWork() {
        return Work;
    }
    public void setCompany(Company p) {
        Work = p;
    }
    private int getProtocolVersionVia(){
        try {
            Object viaAPI = Class.forName("us.myles.ViaVersion.api.Via").getMethod("getAPI").invoke(null);
            int ver = (int) viaAPI.getClass().getMethod("getPlayerVersion", UUID.class).invoke(viaAPI, bPlayer.getUniqueId());
            return ver;
        } catch (Throwable e) {
             e.printStackTrace();
        }
        return 999;
    }
    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&',plugin.getChat().getGroupPrefix(bPlayer.getWorld(), getGroup()));
    }
    public String getBio() {
        return Bio;
    }
    public void sendMessage(String msg) {
        bPlayer.sendMessage(msg);
    }
    public void setBio(String bio) {
        Bio = bio;
    }
    public void sendPacket(Packet<?> packet) {
        getNMSPlayer().playerConnection.sendPacket(packet);
    }
    public Player getBukkitPlayer() {
        return bPlayer;
    }
    public EntityPlayer getNMSPlayer() {
        return ((CraftPlayer) bPlayer).getHandle();
    }
    public CraftPlayer getCraftPlayer() {
        return (CraftPlayer) bPlayer;
    }
}
