package org.raul.plugins.simpleHardcoreMode.General.Managers;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;

public class GeneralManager {

    public static final boolean IS_NEWER_1_14 = hasClass("org.bukkit.persistence.PersistentDataContainer");
    public static final boolean IS_NEWER_1_19 = hasClass("com.destroystokyo.paper.profile.PlayerProfile");

    private static boolean hasClass(String className) {
        try { Class.forName(className); return true; } catch (Exception e) { return false; }
    }

    //FORMAT MESSAGES WITH ARGUMENTS
    public static String replaceArgs(String msg, Map<String, String> args){
        for(String key : args.keySet()){
            msg = msg.replace(key, args.get(key));
        }
        return msg;
    }

    //PLAYAER DATA MANAGER
    public static void setFirstJoin(Player player, NamespacedKey key) {
        if (IS_NEWER_1_14) {
            player.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
            savePlayerData(player);
        }
    }

    public static boolean isFirstJoin(Player player, NamespacedKey key) {
        if (IS_NEWER_1_14) return false;
        Boolean hasJoined = player.getPersistentDataContainer().get(key, PersistentDataType.BOOLEAN);
        return hasJoined == null;
    }

    private static void savePlayerData(Player player) {
        if (IS_NEWER_1_19) {
            player.saveData();
        }
    }

}
