package org.raul.plugins.simpleHardcoreMode.General.Managers;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;

import java.time.Duration;

public class BanManager {
    public void banPlayer(Player player, String reason, Duration duration) {
        if (GeneralManager.IS_NEWER_1_19) {
            // 1.20.1 Paper
            PlayerProfile profile = player.getPlayerProfile();
            BanList<PlayerProfile> bans = Bukkit.getBanList(BanList.Type.PROFILE);
            bans.addBan(profile, reason, duration, null);
        } else {
            // 1.12-1.18
            BanList<String> bans = Bukkit.getBanList(BanList.Type.NAME);
            bans.addBan(player.getName(), reason, duration != null ? duration : null, null);
        }
    }
}