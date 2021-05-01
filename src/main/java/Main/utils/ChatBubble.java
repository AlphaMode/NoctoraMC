package Main.utils;

import Main.Noctora;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.util.logging.Level;

public class ChatBubble {

    private final Noctora plugin = Noctora.getPlugin(Noctora.class);

    //Private
    private NoctoraPlayer p;
    private TextComponent hover;

    private String All;
    private String Name;
    private String Bio;
    private String Role;
    private String Desc;

    private String Message;

    //Public

    //Methods
    public ChatBubble(NoctoraPlayer NP,String msg) {
        p = NP;
        Name = NP.getName();
        Bio = NP.getBio();
        Role = NP.getPrefix();
        Message = msg;
        Desc = "No desc defined for group yet sorry!";
        /*if(!plugin.getRoles().isSet("roles."+Name+"desc")) {
            Desc = plugin.getRoleInfo("roles."+Name+"desc");
        }*/
    }
    public void parse() {
        hover = new TextComponent(p.getPrefix()+" "+p.getName());
        Essentials ess = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
        User user = ess.getUser(p.getBukkitPlayer());
        if (user.getNickname() == null) {
            Name = plugin.getMessage("cb-name") + p.getBukkitPlayer().getName();
        } else {
            Name = plugin.getMessage("cb-name") + user.getNickname()+ " ("+p.getBukkitPlayer().getName()+")";
        }
        Bio  = plugin.getMessage("cb-bio") + p.getBio();
        Role = plugin.getMessage("cb-role") + Role+ChatColor.RESET+ChatColor.GRAY;
        Desc = plugin.getMessage("cb-desc") + plugin.getRoleInfo("roles."+ p.getGroup()+".desc").replace("\\n", "\n");
        if (Desc.isEmpty() || Desc.equalsIgnoreCase("")) {
            Desc = plugin.getMessage("cb-desc") + "No desc defined for group yet sorry!";
        }
        All = Name+"\n"+Role+"\n"+Desc+"\n"+Bio;
        hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(All).create()));
    }

    public void send() {
        for(NoctoraPlayer NPlayer : NoctoraPlayer.getOnlineNoctoraPlayers()) {
            if(Message.contains(NPlayer.getBukkitPlayer().getName())) {
                NPlayer.getBukkitPlayer().playSound(NPlayer.getBukkitPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                if (p.getBukkitPlayer().hasPermission("noctora.color")) {
                    Message = ColorUtil.getRGB(NPlayer, Message);
                    NPlayer.getBukkitPlayer().spigot().sendMessage(hover, new TextComponent(new TextComponent().fromLegacyText(ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', Message.replace(NPlayer.getBukkitPlayer().getName(), ChatColor.RED + "[" + ChatColor.DARK_RED + "!" + ChatColor.RED + "] " + ChatColor.GREEN + NPlayer.getBukkitPlayer().getName() + ChatColor.GRAY)))));
                } else {
                    NPlayer.getBukkitPlayer().spigot().sendMessage(hover, new TextComponent(new TextComponent().fromLegacyText(ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY + Message.replace(NPlayer.getBukkitPlayer().getName(), ChatColor.RED + "[" + ChatColor.DARK_RED + "!" + ChatColor.RED + "] " + ChatColor.GREEN + NPlayer.getBukkitPlayer().getName() + ChatColor.GRAY))));
                }
            }else if(Message.contains(NPlayer.getName())) {
                NPlayer.getBukkitPlayer().playSound(NPlayer.getBukkitPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                if (p.getBukkitPlayer().hasPermission("noctora.color")) {
                    Message = ColorUtil.getRGB(NPlayer, Message);
                    NPlayer.getBukkitPlayer().spigot().sendMessage(hover, new TextComponent(new TextComponent().fromLegacyText(ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', Message.replace(NPlayer.getName(), ChatColor.RED + "[" + ChatColor.DARK_RED + "!" + ChatColor.RED + "] " + ChatColor.GREEN + NPlayer.getName() + ChatColor.GRAY)))));
                } else {
                    NPlayer.getBukkitPlayer().spigot().sendMessage(hover, new TextComponent(new TextComponent().fromLegacyText(ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY + Message.replace(NPlayer.getName(), ChatColor.RED + "[" + ChatColor.DARK_RED + "!" + ChatColor.RED + "] " + ChatColor.GREEN + NPlayer.getName() + ChatColor.GRAY))));
                }
            }else {
                if (p.getBukkitPlayer().hasPermission("noctora.color")) {
                    Message = ColorUtil.getRGB(NPlayer, Message);
                    NPlayer.getBukkitPlayer().spigot().sendMessage(hover, new TextComponent(new TextComponent().fromLegacyText(ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', Message))));
                } else {
                    NPlayer.getBukkitPlayer().spigot().sendMessage(hover, new TextComponent(new TextComponent().fromLegacyText(ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY + Message)));
                }
            }

        }
        //DiscordSRV.getPlugin().processChatMessage(p.getBukkitPlayer(), Message, DiscordSRV.getPlugin().getChannels().size() == 1 ? null : "global", false);
        Bukkit.getLogger().log(Level.INFO,"[CHAT] "+new TextComponent(new TextComponent(p.getName()+ ChatColor.DARK_GRAY+" >> "+ChatColor.GRAY+Message)));
    }

}
