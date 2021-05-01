package Main;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;

public class Util {
    public static String parseC(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    public static boolean isStair(Block b) {
        if(b.getType().toString().contains("STAIRS")) {
            return true;
        }else{
            return false;
        }
    }
}
