package Main.CMD;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface Command {
    public boolean fire(CommandSender sender, String label, String[] args);
    public String getName();
    public List<String> TabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args);
}
