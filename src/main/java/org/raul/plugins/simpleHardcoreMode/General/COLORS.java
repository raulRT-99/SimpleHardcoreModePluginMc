package org.raul.plugins.simpleHardcoreMode.General;

import org.bukkit.ChatColor;

public enum COLORS {
    RED(ChatColor.RED),
    GREEN(ChatColor.GREEN),
    YELLOW(ChatColor.YELLOW),
    WHITE(ChatColor.WHITE),
    GRAY(ChatColor.GRAY),
    RESET(ChatColor.RESET);

    private final ChatColor color;

    COLORS(ChatColor color) {
        this.color = color;
    }

    public String toString() {
        return color.toString();
    }

    public ChatColor getChatColor() {
        return color;
    }
}