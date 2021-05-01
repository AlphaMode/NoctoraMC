package Main;

import Companys.Fishing;
import Companys.JobsManager;
import Main.CMD.Commands;
import Main.utils.*;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Noctora extends JavaPlugin {
    private static Noctora pl;
    public static Noctora getPlugin() {
        return pl;
    }
    @Override
    public void onEnable() {
        setup();
        pl = this;
        EventExecutor eventExecutor = new EventExecutor();
        getServer().getPluginManager().registerEvents(eventExecutor, this);
    }
    public static LuckPerms LP_api;
    private Chat chat;
    private Economy eco;
    private JobsManager jobsManager;
    private PlayerInfo pinfo;
    private Roles roles;
    private Lang lang;
    private void setup() {
        new Commands();
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LP_api = provider.getProvider();
        }
        //TABAPI.enableUnlimitedNameTagModePermanently();
        roles = new Roles(this);
        roles.setup();
        lang = new Lang(this);
        lang.setup();
        jobsManager = new JobsManager();
        pinfo = new PlayerInfo(this);
        pinfo.setup();
        //Register default jobs
        jobsManager.registerJob(new Fishing());
        setupChat();
        setupEco();
        NoctoraPlayer.addAllOnlinePlayers();
        new Refresh(this).runTaskTimerAsynchronously(this, 5L,5L );
    }
    public JobsManager getJobManager() {
        return this.jobsManager;
    }
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    private boolean setupEco() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        eco = rsp.getProvider();
        return eco != null;
    }


    public Chat getChat() {
        return chat;
    }
    public String getRoleInfo(String path) {
        return ChatColor.translateAlternateColorCodes('&', roles.getRoleConfig().getString(path));
    }
    public Economy getEco() {
        
        return this.eco;
    }
    public PlayerInfo getPinfo() {
        return pinfo;
    }
    public Roles getRolesCfg() {
        return this.roles;
    }
    public YamlConfiguration getRoles() {
        return roles.getRoleConfig();
    }

    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&',lang.getLang().getString(path));
    }
}
