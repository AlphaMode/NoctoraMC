package Main.utils;

import Main.Noctora;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

enum defaults {
    PREFIX("prefix","&b[&3Noctora&b] "),
    CB_NAME("cb-name","&8Name&7: "),
    CB_ROLE("cb-role","&8Role&7: "),
    CB_BIO("cb-bio","&8Bio&7: "),
    CB_DESC("cb-desc","&8Description&7: ");

    private String path;
    private String def;
    private static FileConfiguration LANG;

    /**
     * Lang enum constructor.
     * @param path The string path.
     * @param start The default string.
     */
    defaults(String path, String start) {
        this.path = path;
        this.def = start;
    }

    /**
     * Set the {@code YamlConfiguration} to use.
     * @param config The config to set.
     */
    public static void setFile(FileConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

    /**
     * Get the default value of the path.
     * @return The default value of the path.
     */
    public String getDefault() {
        return this.def;
    }

    /**
     * Get the path to the string.
     * @return The path to the string.
     */
    public String getPath() {
        return this.path;
    }
}
public class Lang {
    private Noctora plugin;

    private YamlConfiguration LangConfig;
    private File langFile;

    public Lang(Noctora noctora) {
        plugin = noctora;
    }
    public void setup() {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        try {
            langFile = new File(plugin.getDataFolder(), "lang.yml");
            if (!langFile.exists()) {
                langFile.createNewFile();
            }
            LangConfig = YamlConfiguration.loadConfiguration(langFile);
            for(defaults item:defaults.values()) {
                if (LangConfig.getString(item.getPath()) == null) {
                    System.out.println(item.getPath()+" "+item.getDefault());
                    LangConfig.set(item.getPath(), item.getDefault());
                }
            }
            this.save();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            LangConfig.save(langFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reload() {
        try {
            if(!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdir();
            }
            langFile = new File(plugin.getDataFolder(),"lang.yml");
            if(!langFile.exists()) {
                langFile.createNewFile();
            }
            LangConfig = YamlConfiguration.loadConfiguration(langFile);
            LangConfig.save(langFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public YamlConfiguration getLang() {
        return LangConfig;
    }
}
