package Main.CMD;

import Main.Noctora;
import Main.utils.ColorUtil;
import Main.utils.NoctoraPlayer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test implements Command {

    public boolean fire(CommandSender sender, String label, String[] args) {
        switch (args[0].toLowerCase().trim()) {
            case "chat":
                if(sender instanceof Player) {
                    AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(false, (Player) sender, ColorUtil.getRGB(NoctoraPlayer.getNPlayer(sender.getName()), StringUtils.join(args, " ", 1, args.length)), new HashSet<Player>(Bukkit.getOnlinePlayers()));
                    Noctora.getPlugin(Noctora.class).getServer().getPluginManager().callEvent(event);
                }
        }
        return true;
    }

    public String getName() {
        return null;
    }

    @Override
    public List<String> TabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("chat");
        return lines;
    }
}
