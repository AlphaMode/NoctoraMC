package Main;

import Main.utils.ChatBubble;
import Main.utils.ColorUtil;
import Main.utils.NoctoraPlayer;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventExecutor implements Listener {

    private Noctora main = Noctora.getPlugin(Noctora.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        NoctoraPlayer NPlayer = new NoctoraPlayer(e.getPlayer());
        NoctoraPlayer.refreshTab();
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        NoctoraPlayer.players.remove(NoctoraPlayer.getNPlayer(e.getPlayer().getName()));
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        NoctoraPlayer NP = NoctoraPlayer.getNPlayer(e.getPlayer().getName());
        ChatBubble chatBubble = new ChatBubble(NP, ColorUtil.getHex(e.getMessage()));
        chatBubble.parse();
        chatBubble.send();

        DiscordSRV.getPlugin().processChatMessage(e.getPlayer(), ColorUtil.stripColor(ColorUtil.getRGB(NP,ColorUtil.getHex(e.getMessage()))), DiscordSRV.getPlugin().getChannels().size() == 1 ? null : "global", false);
        //DynmapPlugin.plugin.postPlayerMessageToWeb(e.getPlayer(), e.getMessage());
        /*for(Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(role,new TextComponent(new TextComponent().fromLegacyText(" "+e.getPlayer().getDisplayName()+ChatColor.GRAY +" >> "+e.getMessage())));
        }*/
    }


}
