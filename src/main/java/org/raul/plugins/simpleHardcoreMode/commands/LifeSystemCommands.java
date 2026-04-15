package org.raul.plugins.simpleHardcoreMode.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.raul.plugins.simpleHardcoreMode.Config;
import org.raul.plugins.simpleHardcoreMode.messages.LanguageMsg;


import java.util.ArrayList;
import java.util.List;

public class LifeSystemCommands implements CommandExecutor, TabCompleter {

    private String[] commands = {"get", "set", "add", "defaultLives"};
    private final JavaPlugin plugin;
    private final NamespacedKey livesKey;
    private final int defaultLives = 3;
    private final LanguageMsg consoleMessage;
    private final Config config;

    public LifeSystemCommands(JavaPlugin plugin, LanguageMsg consoleMessage, Config config) {
        this.plugin = plugin;
        this.livesKey = new NamespacedKey(plugin, "lives");
        this.consoleMessage = consoleMessage;
        this.config = config;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(COLORS.RED + "Add some arguments to the command: " + COLORS.YELLOW + "\'lives [get | set | add | defaultlives] \'");
            return true;
        }

        switch (args[0]) {
            case "get":
                handleGet(sender, args);
                break;
            case "set":
                handleSet(sender, args);
                break;
            case "add":
                handleAdd(sender, args);
                break;
            case "defaultlives":
                handleDefault(sender, args);
                break;
            default:
                sender.sendMessage(COLORS.RED + "Wrong command arguments");
                return false;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completer = new ArrayList<>();

        if (args.length == 1) {
            String arg0 = args[0].toLowerCase();
            for (String search : commands) {
                if (search.startsWith(arg0)) {
                    completer.add(search);
                }
            }
        } else if (args.length == 2) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                    completer.add(p.getName());
                }
            }
        }
        return completer;
    }

    private void handleGet(CommandSender sender, String[] args) {
        Player target = getTarget(sender, args);
        if (target == null) return;

        Integer lives = target.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
        if (lives == null) lives = defaultLives;

        sender.sendMessage(COLORS.GREEN + target.getName() + " tiene " + COLORS.YELLOW +   lives  + COLORS.GREEN + " vida(s).");
    }

    private void handleSet(CommandSender sender, String[] args) {
        Player target = getTarget(sender, args);
        if (target == null) return;

        if (!parseAndSetLives(sender, args, target, "set")) return;

        sender.sendMessage(COLORS.GREEN + "Vidas de " + target.getName() + COLORS.GREEN + " set a " + getLastParsedLives() + ".");
    }

    private void handleAdd(CommandSender sender, String[] args) {
        Player target = getTarget(sender, args);
        if (target == null) return;

        if (!parseAndSetLives(sender, args, target, "add")) return;

        sender.sendMessage(COLORS.GREEN + "Se agregaron " + getLastParsedLives() + " vida(s) a " + target.getName() + ".");
    }

    private void handleDefault(CommandSender sender, String[] args) {
        Player target = getTarget(sender, args);
        if (target == null) {
            sender.sendMessage("Please select player");
            return;
        }

        Integer lives = target.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
        if (lives != null) {
            target.getPersistentDataContainer().remove(livesKey);
        }
        sender.sendMessage(COLORS.GREEN + "Vidas de " + target.getName() + " reseteadas a por defecto (" + defaultLives + ").");
    }

    private int lastParsedLives = 0;
    private int getLastParsedLives() {
        return lastParsedLives;
    }

    private boolean parseAndSetLives(CommandSender sender, String[] args, Player target, String action) {
        if (args.length < 3) {
            sender.sendMessage(COLORS.RED + "Usa: /lives " + action + " <jugador> <cantidad>");
            return false;
        }
        try {
            int amount = Integer.parseInt(args[2]);
            Integer current = target.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
            if (current == null) current = 0;

            int newLives;
            if ("add".equals(action)) {
                newLives = current + amount;
            } else {
                newLives = amount;
            }
            if (newLives < 0) newLives = 0;

            target.getPersistentDataContainer().set(livesKey, PersistentDataType.INTEGER, newLives);
            lastParsedLives = newLives;
            return true;
        } catch (NumberFormatException e) {
            sender.sendMessage(COLORS.RED + args[2] + "' no es un número válido.");
            return false;
        }
    }

    private Player getTarget(CommandSender sender, String[] args) {
        Player target;
        if (args.length >= 2) {
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(COLORS.RED+"Jugador '" + args[1] + "' no encontrado.");
                return null;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(COLORS.RED+"Especifica un jugador.");
                return null;
            }
            target = (Player) sender;
        }
        return target;
    }
}
