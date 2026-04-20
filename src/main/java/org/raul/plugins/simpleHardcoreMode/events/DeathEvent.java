package org.raul.plugins.simpleHardcoreMode.events;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.profile.PlayerProfile;
import org.raul.plugins.simpleHardcoreMode.General.Config;
import org.raul.plugins.simpleHardcoreMode.General.FormatMessage;
import org.raul.plugins.simpleHardcoreMode.messages.LanguageMsg;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DeathEvent implements Listener {

    private final JavaPlugin plugin;
    private final NamespacedKey livesKey;
    private final Config config;
    private final LanguageMsg messages;

    public DeathEvent(JavaPlugin plugin, Config config, LanguageMsg messages) {
        this.plugin = plugin;
        this.livesKey = new NamespacedKey(plugin, "lives");
        this.config = config;
        this.messages = messages;
    }

    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Map<String, String> args = new HashMap<>();
        args.put("%player%", player.getName());
        args.put("%defaultLives%", String.valueOf(config.getDefault_lives()));
        int newLives = discountLives(player);
        if (newLives <= 0) {
            args.put("%lives%", "0");
            args.put("%banTime%", (float) config.getBan_total_time() / 3600 + "hrs.");

            String kickMsg = FormatMessage.replaceArgs(config.getKick_message(), args);
            long totalXP = player.getPersistentDataContainer().getOrDefault(GainXPEvent.xp, PersistentDataType.LONG, 0L);
            kickMsg += "\n\n" + messages.hoursAndXPAfterDeath(totalXP, player.getStatistic(Statistic.PLAY_ONE_MINUTE), config.getLang());
            String finalKickMsg = kickMsg;
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.kickPlayer(finalKickMsg);
            }, 10L);

            String banMsg = FormatMessage.replaceArgs(config.getBan_message(), args);

            BanList<PlayerProfile> bans = Bukkit.getBanList(BanList.Type.PROFILE);
            bans.addBan(player.getPlayerProfile(), banMsg, banTime(), null);

            String broadcastMsg = FormatMessage.replaceArgs(config.getBroadcast_message(), args);
            Bukkit.broadcastMessage(broadcastMsg);
        } else {
            args.put("%lives%", player.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER).toString());
            String lostLifeMsg = FormatMessage.replaceArgs(config.getLost_life_message(), args);
            player.sendMessage(lostLifeMsg);
        }
    }


    private Duration banTime() {
        if (config.isPermaban()) return null;
        return Duration.ofSeconds(config.getBan_total_time());
    }

    private int discountLives(Player player) {
        int newLives;
        try {
            int currentLives = player.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
            if (currentLives <= 1) {
                newLives = 0;
            } else {
                newLives = currentLives - 1;
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
