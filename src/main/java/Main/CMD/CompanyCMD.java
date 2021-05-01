package Main.CMD;

import Companys.Company;
import Main.utils.NoctoraPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CompanyCMD implements Command {
    public boolean fire(CommandSender sender, String label, String[] args) {
        if(sender instanceof Player) {
            NoctoraPlayer NP = NoctoraPlayer.getNPlayer((Player) sender);
            switch (args[0].toLowerCase()) {
                case "create":
                    if(!args[1].isEmpty()) {
                        Company company = new Company(args[1],NP);
                        return true;
                    }

            }
        }
        return true;
    }

    public String getName() {
        return "commany";
    }

    @Override
    public List<String> TabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        ArrayList<String> lines = new ArrayList<String>();
        if(!args[0].isEmpty()) {
            lines.add("create");
        }
        return lines;
    }
}
