package org.raul.plugins.simpleHardcoreMode.messages;


import org.raul.plugins.simpleHardcoreMode.General.COLORS;

public class SpanishMsg extends LanguageMsg {

    public SpanishMsg(int defaultLives) {
        super(defaultLives);
    }

    @Override
    public String noArgumentsSent() {
        return COLORS.RED + "Agrega parámetros al comando: " + COLORS.YELLOW + "'\\lives [get | set | add | defaultlives] <jugador> <cantidad>'";
    }

    @Override
    public String wrongArguments() {
        return COLORS.RED + "Parámetros erróneos";
    }

    @Override
    public String playerLives(String player, int lives) {
        return COLORS.YELLOW + player + COLORS.GREEN + " tiene " + COLORS.YELLOW + lives + COLORS.GREEN + " vidas restantes.";
    }

    @Override
    public String setPlayerLives(String player, int lives) {
        return COLORS.GREEN + "Vidas de " + COLORS.YELLOW + player + COLORS.GREEN + " asignadas a " + COLORS.YELLOW + lives + COLORS.GREEN + ".";
    }

    @Override
    public String addPlayerLives(String player, int lives) {
        return COLORS.GREEN + "Se agregaron " + COLORS.YELLOW + lives + COLORS.GREEN + " vidas a " + COLORS.YELLOW + player + COLORS.GREEN + ".";
    }

    @Override
    public String noPlayerSelected() {
        return COLORS.RED + "Especifica un jugador.";
    }

    @Override
    public String defaultLives(String player) {
        return COLORS.GREEN + "Vidas de " + COLORS.YELLOW + player + COLORS.GREEN + " reiniciadas a por defecto (" + COLORS.YELLOW + this.defaultLives + COLORS.GREEN + ").";
    }

    @Override
    public String commandHelp(String action) {
        return COLORS.RED + "Usa: /lives " + action + " <jugador> <cantidad>";
    }

    @Override
    public String invalidNumber(String number) {
        return COLORS.RED + "'" + number + "' no es un número válido.";
    }

    @Override
    public String playerNotFound(String player) {
        return COLORS.RED + "Jugador: '" + player + "' no encontrado.";
    }

}
