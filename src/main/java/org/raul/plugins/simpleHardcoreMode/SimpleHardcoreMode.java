package org.raul.plugins.simpleHardcoreMode;

import org.bukkit.plugin.java.JavaPlugin;
import org.raul.plugins.simpleHardcoreMode.commands.HelloCommand;
import org.raul.plugins.simpleHardcoreMode.commands.LifeSystemCommands;
import org.raul.plugins.simpleHardcoreMode.events.DeathEvent;

public final class SimpleHardcoreMode extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("hello").setExecutor(new HelloCommand());
        LifeSystemCommands lifesCmd = new LifeSystemCommands(this);
        getCommand("lives").setExecutor(lifesCmd);
        getCommand("lives").setTabCompleter(lifesCmd);

        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
