package org.raul.plugins.simpleHardcoreMode.events;

import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.raul.plugins.simpleHardcoreMode.General.Config;
import org.raul.plugins.simpleHardcoreMode.General.FormatMessage;
import java.util.HashMap;
import java.util.Map;

public class JoinServerEvent implements Listener {

    private final JavaPlugin plugin;
    private final NamespacedKey firstJoin;
    private final Config config;
    private final NamespacedKey lives;

    public JoinServerEvent(JavaPlugin plugin, Config config) {
        this.plugin = plugin;
        this.firstJoin = new NamespacedKey(plugin, "firstJoin");
        this.lives = new NamespacedKey(plugin, "lives");
        this.config = config;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player newPlayer = event.getPlayer();
        boolean showFirstJoinMsg;

        newPlayer.getStatistic(Statistic.TOTAL_WORLD_TIME);

        Map<String, String> args = new HashMap<>();
        args.put("%player%", newPlayer.getName());
        args.put("%defaultLives%", String.valueOf(config.getDefault_lives()));

        showFirstJoinMsg = Boolean.TRUE.equals(newPlayer.getPersistentDataContainer().get(firstJoin, PersistentDataType.BOOLEAN));
        if (!showFirstJoinMsg) {
            newPlayer.getPersistentDataContainer().set(firstJoin, PersistentDataType.BOOLEAN, true);
            newPlayer.getPersistentDataContainer().set(this.lives, PersistentDataType.INTEGER, config.getDefault_lives());
            args.put("%lives%", String.valueOf(config.getDefault_lives()));

            String welcomeMsg = FormatMessage.replaceArgs(config.getWelcome_message(), args);
            newPlayer.sendMessage(welcomeMsg);
            newPlayer.saveData();
        } else {
            int currentLives = newPlayer.getPersistentDataContainer().get(this.lives, PersistentDataType.INTEGER);
            args.put("%lives%", String.valueOf(currentLives));
        }

        String enterMsg = FormatMessage.replaceArgs(config.getEnter_message(), args);
        newPlayer.sendMessage(enterMsg);

    }
}