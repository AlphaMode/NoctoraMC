package Main.CMD;

import Main.Noctora;
import net.luckperms.api.model.group.Group;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class desc implements Command {
    private static Noctora plugin = Noctora.getPlugin(Noctora.class);
    public boolean fire(CommandSender sender, String label, String[] args) {
        Group pGroup = plugin.LP_api.getGroupManager().getGroup(args[0]);
        if(pGroup != null) {
            plugin.getRoles().set("roles."+pGroup.getName()+".desc", StringUtils.join(args, " ", 1,args.length));
            plugin.getRolesCfg().save();
            plugin.getRolesCfg().reload();
        }else{
            sender.sendMessage(plugin.getMessage("prefix")+"No group was found call: "+args[0]);
        }
        return true;
    }

    public String getName() {
        return "desc";
    }

    public List<String> TabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        ArrayList<String> lines = new ArrayList<String>();
            for(Group pGroup : plugin.LP_api.getGroupManager().getLoadedGroups()) {
                if(args[0].equalsIgnoreCase("")) {
                    lines.add(pGroup.getName());
                }else{
                    if(pGroup.getName().startsWith(args[0])) {
                        lines.add(pGroup.getName());
                    }
                }
            }
        return lines;
    }
}
