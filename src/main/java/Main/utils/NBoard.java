package Main.utils;

import Main.Noctora;
import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import org.bukkit.ChatColor;

public class NBoard {
    private Noctora plugin = Noctora.getPlugin(Noctora.class);
    private BPlayerBoard bPlayerBoard;
    private NoctoraPlayer NPlayer;
    private int c = 1;
    public NBoard(NoctoraPlayer NP) {
        NPlayer = NP;
        bPlayerBoard = Netherboard.instance().createBoard(NPlayer.getBukkitPlayer(), plugin.getMessage("prefix").trim());
        bPlayerBoard.setAll(
                ChatColor.BOLD+"",
                ChatColor.translateAlternateColorCodes('&',"&9Rank&b: &r"+NPlayer.getPrefix()),
                ChatColor.translateAlternateColorCodes('&',"&9Balance&b: "+plugin.getEco().getBalance(NPlayer.getBukkitPlayer())),
                ChatColor.translateAlternateColorCodes('&', "&8 "),
                ChatColor.translateAlternateColorCodes('&', "&9play.noctora.com")
        );
    }
    public void Refresh() {
        bPlayerBoard.setAll(
                ChatColor.BOLD+"",
                ChatColor.translateAlternateColorCodes('&',"&9Rank&b: &r"+NPlayer.getPrefix()),
                ChatColor.translateAlternateColorCodes('&',"&9Balance&b: "+plugin.getEco().getBalance(NPlayer.getBukkitPlayer())),
                ChatColor.translateAlternateColorCodes('&', "&8 "),
                ChatColor.BLUE +"play.noctora.com"
        );
    }
}
