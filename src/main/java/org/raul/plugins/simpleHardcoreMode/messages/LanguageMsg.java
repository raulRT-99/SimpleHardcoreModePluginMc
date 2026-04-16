package org.raul.plugins.simpleHardcoreMode.messages;

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

}
