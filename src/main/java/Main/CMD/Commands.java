package Main.CMD;

import Main.Noctora;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commands implements CommandExecutor, TabExecutor {

    public HashMap<String,Main.CMD.Command> cmds = new HashMap<String,Main.CMD.Command>();
    private Noctora plugn = Noctora.getPlugin(Noctora.class);
    public Commands() {
        cmds.put("bio",new Bio());
        cmds.put("desc", new desc());
        cmds.put("test",new Test());
        for(Map.Entry<String, Main.CMD.Command> cmd : cmds.entrySet()) {
            plugn.getCommand(cmd.getKey()).setExecutor(this);
            plugn.getCommand(cmd.getKey()).setTabCompleter(this);
        }
    }



    public boolean onCommand(CommandSender sender,Command command, String label, String[] args) {
        for(Map.Entry<String, Main.CMD.Command> cmd : cmds.entrySet()) {
            if(label.equalsIgnoreCase(cmd.getKey())) {
                cmd.getValue().fire(sender,label,args);
            }
        }
        return false;
    }
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        for(Map.Entry<String, Main.CMD.Command> cmd : cmds.entrySet()) {
            if(label.equalsIgnoreCase(cmd.getKey())) {
                return cmd.getValue().TabComplete(sender, command, label, args);
            }
        }
        return null;
    }
}
