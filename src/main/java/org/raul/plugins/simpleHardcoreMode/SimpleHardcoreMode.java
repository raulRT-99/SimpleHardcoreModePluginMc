package org.raul.plugins.simpleHardcoreMode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.raul.plugins.simpleHardcoreMode.General.Config;
import org.raul.plugins.simpleHardcoreMode.commands.LifeSystemCommands;
import org.raul.plugins.simpleHardcoreMode.events.DeathEvent;
import org.raul.plugins.simpleHardcoreMode.events.JoinServerEvent;
import org.raul.plugins.simpleHardcoreMode.messages.EnglishMsg;
import org.raul.plugins.simpleHardcoreMode.messages.LanguageMsg;
import org.raul.plugins.simpleHardcoreMode.messages.SpanishMsg;

public final class SimpleHardcoreMode extends JavaPlugin {

    private Config config = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info("SHM: Thanks for using my plugin");
        this.getLogger().info("SHM: Made by Latrell (raulRT99)");

        try {
            saveDefaultConfig();
            loadConfFile();
        } catch (Exception e) {
            this.getLogger().severe("Error on loading config file, please check you modify it correctly. Reset to default config --- " + e.getMessage());
        }

        LanguageMsg consoleMessage = selectLanguage(config.getLang());

        LifeSystemCommands lifesCmd = new LifeSystemCommands(this, consoleMessage, config);
        getCommand("lives").setExecutor(lifesCmd);
        getCommand("lives").setTabCompleter(lifesCmd);

        getServer().getPluginManager().registerEvents(new DeathEvent(this, config), this);
        getServer().getPluginManager().registerEvents(new JoinServerEvent(this, config), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfFile() {
        // Con defaults seguros
        config = new Config(
                getConfig().getString("lang", "eng"),
                getConfig().getString("welcome-message", "§aWelcome to the hardcore mode server. Your only have §6%defaultLives%§a lives"),
                getConfig().getString("kick-message", "§2You lost all of your lives"),
                getConfig().getString("ban-message", "§4You can not enter the server because you lost all of your lives"),
                getConfig().getString("broadcast-message", "§aThe player §e%player%§a has lost all of his lives"),
                getConfig().getString("enter-message", "§aHello again §e%player%§a, you have §e%lives%§a lives left"),
                getConfig().getString("lost-life-message", "§aYou lost a life, your new life's count is: §e%lives%"),
                getConfig().getInt("default-lives", 3),
                getConfig().getBoolean("permaban", true),
                getConfig().getInt("ban-time-minutes", -1),
                getConfig().getInt("ban-time-hours", -1),
                getConfig().getInt("ban-time-days", -1)
        );
    }

    private LanguageMsg selectLanguage(String lang) {
        return switch (lang) {
            case "es" -> new SpanishMsg(config.getDefault_lives());
            case "eng" -> new EnglishMsg(config.getDefault_lives());
            default -> new EnglishMsg(config.getDefault_lives());
        };
    }

}
