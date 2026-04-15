package org.raul.plugins.simpleHardcoreMode.messages;

import org.raul.plugins.simpleHardcoreMode.General.COLORS;

public class EnglishMsg extends LanguageMsg {

    public EnglishMsg(int defaultLives) {
        super(defaultLives);
    }

    @Override
    public String noArgumentsSent() {
        return COLORS.RED + "Add some arguments to the command: " + COLORS.YELLOW + "'lives [get | set | add | defaultlives] <player> <number>'";
    }

    @Override
    public String wrongArguments() {
        return COLORS.RED + "Wrong command arguments";
    }

    @Override
    public String playerLives(String player, int lives) {
        return COLORS.YELLOW + player + COLORS.GREEN + " has " + COLORS.YELLOW + lives + COLORS.GREEN + " lives left.";
    }

    @Override
    public String setPlayerLives(String player, int lives) {
        return COLORS.GREEN + "Lives of " + COLORS.YELLOW + player + COLORS.GREEN + " set to " + COLORS.YELLOW + lives + COLORS.GREEN + ".";
    }

    @Override
    public String addPlayerLives(String player, int lives) {
        return COLORS.GREEN + "Added " + COLORS.YELLOW + lives + COLORS.GREEN + " lives to " + COLORS.YELLOW + player + COLORS.GREEN + ".";
    }

    @Override
    public String noPlayerSelected() {
        return COLORS.RED + "Specify a player.";
    }

    @Override
    public String defaultLives(String player) {
        return COLORS.GREEN + "Lives of " + COLORS.YELLOW + player + COLORS.GREEN + " reset to default (" + COLORS.YELLOW + this.defaultLives + COLORS.GREEN + ").";
    }

    @Override
    public String commandHelp(String action) {
        return COLORS.RED + "Use: /lives " + action + " <player> <number>";
    }

    @Override
    public String invalidNumber(String number) {
        return COLORS.RED + "'" + number + "' is not a valid number.";
    }

    @Override
    public String playerNotFound(String player) {
        return COLORS.RED + "Player '" + player + "' not found.";
    }

}
