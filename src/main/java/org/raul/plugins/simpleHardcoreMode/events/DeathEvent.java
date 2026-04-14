package org.raul.plugins.simpleHardcoreMode.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.raul.plugins.simpleHardcoreMode.commands.COLORS;

import java.util.Date;

public class DeathEvent implements Listener {

    private final JavaPlugin plugin;
    private final NamespacedKey livesKey;

    public DeathEvent(JavaPlugin plugin) {
        this.plugin = plugin;
        this.livesKey = new NamespacedKey(plugin, "lives");
    }

    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent event) {
        Player player = event.getEntity();
        int newLives = discountLifes(player);
        if (newLives < 0) return;
        if (newLives == 0) {
            player.ban("You get out of lifes!!", (Date) null, null, true);
            Bukkit.broadcastMessage(COLORS.GREEN + "Player: " + COLORS.RED + player.getName() + " get out of Lives!!");
        } else {
            player.sendMessage(COLORS.GREEN + "You lost a lives, your new life's count is: " + COLORS.YELLOW + newLives);
        }
    }

    private int discountLifes(Player player) {
        int newLives;
        try {
            int currentLifes = player.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
            if (currentLifes == 1) {
                newLives = 0;
            } else {
                newLives = currentLifes - 1;
            }
            player.getPersistentDataContainer().set(livesKey, PersistentDataType.INTEGER, newLives);
        } catch (NullPointerException e) {
            plugin.getLogger().severe("An error has occurred!!, couldn't get current lives of player: " + player.getName());
            return -1;
        } catch (Exception e) {
            plugin.getLogger().severe("An error has occurred!!. Unexpected error!!.\n" + e.getMessage());
            return -2;
        }
        return newLives;
    }

}
