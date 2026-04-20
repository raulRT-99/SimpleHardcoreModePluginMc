package org.raul.plugins.simpleHardcoreMode.messages;

import org.raul.plugins.simpleHardcoreMode.General.COLORS;

public abstract class LanguageMsg {
    protected final int defaultLives;

    LanguageMsg(int defaultLives) {
        this.defaultLives = defaultLives;
    }

    public abstract String noArgumentsSent();
    public abstract String wrongArguments();
    public abstract String playerLives(String player, int lives);
    public abstract String setPlayerLives(String player, int lives);
    public abstract String addPlayerLives(String player, int lives);
    public abstract String noPlayerSelected();
    public abstract String defaultLives(String player);
    public abstract String commandHelp(String action);
    public abstract String invalidNumber(String number);
    public abstract String playerNotFound(String player);
    public abstract String notEnoughPermissions();

    public String hoursAndXPAfterDeath(long XP, long totalTimeTicks, String lang) {
        String finalMsg = COLORS.WHITE + "Total XP: " + COLORS.GREEN + XP + "\n";
        double hours = totalTimeTicks/72000.0;
        if (lang.equalsIgnoreCase("es")) {
            finalMsg += COLORS.WHITE + "Tiempo total: " + COLORS.GREEN + String.format("%.2f", hours) + COLORS.WHITE + " horas.";
        } else {
            finalMsg += COLORS.WHITE + "Total time: " + COLORS.GREEN + String.format("%.2f", hours) + COLORS.WHITE + " hours.";
        }
        return finalMsg;
    }

}
