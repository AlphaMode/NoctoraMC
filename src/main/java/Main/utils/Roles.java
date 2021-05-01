package Main.utils;

import Main.Noctora;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Roles {
    private Noctora plugin;

    private YamlConfiguration RoleConfig;
    private File roleFile;

    public Roles(Noctora noctora) {
        plugin = noctora;
    }
    public void setup() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        try {
            roleFile = new File(plugin.getDataFolder(), "roles.yml");
            if (!roleFile.exists()) {
                roleFile.createNewFile();
            }
            RoleConfig = YamlConfiguration.loadConfiguration(roleFile);
            this.save();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            RoleConfig.save(roleFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reload() {
        try {
            if(!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdir();
            }
            roleFile = new File(plugin.getDataFolder(),"roles.yml");
            if(!roleFile.exists()) {
                roleFile.createNewFile();
            }
            RoleConfig = YamlConfiguration.loadConfiguration(roleFile);
            RoleConfig.save(roleFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getRoleConfig() {
        return RoleConfig;
    }
}
