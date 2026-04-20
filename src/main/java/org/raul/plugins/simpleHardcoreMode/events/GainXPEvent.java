package org.raul.plugins.simpleHardcoreMode.events;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class GainXPEvent implements Listener {

    public static NamespacedKey xp;

    public GainXPEvent(JavaPlugin plugin) {
        xp = new NamespacedKey(plugin, "xp");
    }

    @EventHandler
    public void onGainXP(PlayerExpChangeEvent event) {
        long oldXP = event.getPlayer().getPersistentDataContainer().getOrDefault(xp, PersistentDataType.LONG, 0L);
        long newXP = event.getAmount();
        event.getPlayer().getPersistentDataContainer().set(xp, PersistentDataType.LONG, oldXP + newXP);
    }
}
