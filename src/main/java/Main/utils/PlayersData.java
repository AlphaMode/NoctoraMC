package Main.utils;

import Main.Noctora;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayersData {
    private Noctora plugin;

    private YamlConfiguration DataConfig;
    private File dataFile;

    public PlayersData(Noctora noctora) {
        plugin = noctora;
    }
    public void setup() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        try {
            dataFile = new File(plugin.getDataFolder(), "roles.yml");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            DataConfig = YamlConfiguration.loadConfiguration(dataFile);
            this.save();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            DataConfig.save(dataFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reload() {
        try {
            if(!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdir();
            }
            dataFile = new File(plugin.getDataFolder(),"roles.yml");
            if(!dataFile.exists()) {
                dataFile.createNewFile();
            }
            DataConfig = YamlConfiguration.loadConfiguration(dataFile);
            DataConfig.save(dataFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public YamlConfiguration getData() {
        return DataConfig;
    }
}
