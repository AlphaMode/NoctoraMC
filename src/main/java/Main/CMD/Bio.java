package Main.CMD;

import Main.Noctora;
import Main.utils.NoctoraPlayer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Bio implements Command {
    public boolean fire(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            NoctoraPlayer NP = NoctoraPlayer.getNPlayer(sender.getName());
            NP.setBio(StringUtils.join(args, " ", 0,args.length));
            Noctora.getPlugin(Noctora.class).getPinfo().getData().set(NP.getBukkitPlayer().getUniqueId()+".bio", NP.getBio());
            Noctora.getPlugin(Noctora.class).getPinfo().save();
            
            return true;
        }
        return false;
    }
    public String getName() {
        return "bio";
    }

    @Override
    public List<String> TabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        return null;
    }
}
