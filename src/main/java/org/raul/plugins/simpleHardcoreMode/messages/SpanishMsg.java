package org.raul.plugins.simpleHardcoreMode.messages;


public class SpanishMsg extends LanguageMsg {


    SpanishMsg(int defaultLives) {
        super(defaultLives);
    }

    @Override
    public String noArgumentsSent() {
        return "";
    }

    @Override
    public String wrongArguments() {
        return "";
    }

    @Override
    public String playerLives(String player, int lives) {
        return "";
    }

    @Override
    public String setPlayerLives(String player, int lives) {
        return "";
    }

    @Override
    public String addPlayerLives(String player, int lives) {
        return "";
    }

    @Override
    public String noPlayerSelected() {
        return "";
    }

    @Override
    public String defaultLives(String player) {
        return "";
    }

    @Override
    public String commandHelp(String action) {
        return "";
    }

    @Override
    public String invalidNumber(int number) {
        return "";
    }

    @Override
    public String playerNotFound(String player) {
        return "";
    }

    @Override
    public String specifyPlayer() {
        return "";
    }
}
