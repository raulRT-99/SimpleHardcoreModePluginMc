package org.raul.plugins.simpleHardcoreMode;

import org.bukkit.plugin.java.JavaPlugin;
import org.raul.plugins.simpleHardcoreMode.General.Config;
import org.raul.plugins.simpleHardcoreMode.commands.HelloCommand;
import org.raul.plugins.simpleHardcoreMode.commands.LifeSystemCommands;
import org.raul.plugins.simpleHardcoreMode.events.DeathEvent;
import org.raul.plugins.simpleHardcoreMode.messages.EnglishMsg;
import org.raul.plugins.simpleHardcoreMode.messages.LanguageMsg;
import org.raul.plugins.simpleHardcoreMode.messages.SpanishMsg;

public final class SimpleHardcoreMode extends JavaPlugin {

    private Config config = null;

    @Override
    public void onEnable() {
        // Plugin startup logic

        try {
            saveDefaultConfig();
            loadConfFile();
        } catch (Exception e) {
            this.getLogger().severe("Error on loading config file, please check you modify it correctly. Reset to default config --- " + e.getMessage());
        }

        LanguageMsg consoleMessage = null;
        if (getConfig().getString("lang").equals("es")) {
            consoleMessage = new SpanishMsg(config.getDefault_lives());
        } else {
            consoleMessage = new EnglishMsg(config.getDefault_lives());
        }



        getCommand("hello").setExecutor(new HelloCommand());
        LifeSystemCommands lifesCmd = new LifeSystemCommands(this, consoleMessage, config);
        getCommand("lives").setExecutor(lifesCmd);
        getCommand("lives").setTabCompleter(lifesCmd);

        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
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
                getConfig().getString("enter-message", "§aHello again §e%player%§a, you have §e%lives%§a left"),
                getConfig().getInt("default-lives", 3),
                getConfig().getBoolean("permaban", true),
                getConfig().getInt("ban-time-minutes", -1),
                getConfig().getInt("ban-time-hours", -1),
                getConfig().getInt("ban-time-days", -1)
        );
    }
}
