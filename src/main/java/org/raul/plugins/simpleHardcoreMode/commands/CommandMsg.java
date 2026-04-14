package org.raul.plugins.simpleHardcoreMode.commands;

import org.bukkit.entity.Player;

public class CommandMsg {

    private final String lang;
    private final Player target;
    private final int lives;
    private final int defaultLives;
    private final String[] args;

    CommandMsg(String lang, Player target, int lives, int defaultLives, String[] args) {
        this.lang = lang;
        this.target = target;
        this.lives = lives;
        this.defaultLives = defaultLives;
        this.args = args;
    }

    /*
    COLORS.RED + "Add some arguments to the command: " + COLORS.YELLOW + "\'lives [get | set | add | defaultlives] \'"
    COLORS.RED + "Wrong command arguments"
    COLORS.GREEN + target.getName() + " tiene " + COLORS.YELLOW +   lives  + COLORS.GREEN + " vida(s)."
    COLORS.GREEN + "Vidas de " + target.getName() + COLORS.GREEN + " set a " + getLastParsedLives() + "."
    COLORS.GREEN + "Se agregaron " + getLastParsedLives() + " vida(s) a " + target.getName() + "."
    COLORS.GREEN + "Vidas por defecto: " + COLORS.YELLOW + defaultLives
    COLORS.GREEN + "Vidas de " + target.getName() + " reseteadas a por defecto (" + defaultLives + ")."
    COLORS.RED + "Usa: /lives " + action + " <jugador> <cantidad>"
    COLORS.RED + args[2] + "' no es un número válido."
    COLORS.RED+"Jugador '" + args[1] + "' no encontrado."
    COLORS.RED+"Especifica un jugador."
    */

    public void

}
