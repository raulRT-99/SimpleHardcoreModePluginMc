package org.raul.plugins.simpleHardcoreMode.messages;

import org.bukkit.entity.Player;

public abstract class LanguageMsg {
    protected final int defaultLives;

    LanguageMsg(int defaultLives) {
        this.defaultLives = defaultLives;
    }
        /*
    COLORS.RED + "Add some arguments to the command: " + COLORS.YELLOW + "\'lives [get | set | add | defaultlives] \'"
    COLORS.RED + "Wrong command arguments"
    COLORS.GREEN + target.getName() + " tiene " + COLORS.YELLOW +   lives  + COLORS.GREEN + " vida(s)."
    COLORS.GREEN + "Vidas de " + target.getName() + COLORS.GREEN + " set a " + getLastParsedLives() + "."
    COLORS.GREEN + "Se agregaron " + getLastParsedLives() + " vida(s) a " + target.getName() + "."
    COLORS.GREEN + "Vidas de " + target.getName() + " reseteadas a por defecto (" + defaultLives + ")."
    COLORS.RED + "Usa: /lives " + action + " <jugador> <cantidad>"
    COLORS.RED + args[2] + "' no es un número válido."
    COLORS.RED+"Jugador '" + args[1] + "' no encontrado."
    COLORS.RED+"Especifica un jugador."
    */
    public abstract String noArgumentsSent();
    public abstract String wrongArguments();
    public abstract String playerLives(String player, int lives);
    public abstract String setPlayerLives(String player, int lives);
    public abstract String addPlayerLives(String player, int lives);
    public abstract String noPlayerSelected();
    public abstract String defaultLives(String player);
    public abstract String commandHelp(String action);
    public abstract String invalidNumber(int number);
    public abstract String playerNotFound(String player);
    public abstract String specifyPlayer();

}
