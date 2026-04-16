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
import org.raul.plugins.simpleHardcoreMode.General.Config;
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
            sender.sendMessage(consoleMessage.noArgumentsSent());
            return true;
        }

        boolean permissions = sender.hasPermission("SimpleHardcoreMode.permission.lives.admin");
        if(!permissions && !args[0].equalsIgnoreCase("get")){
            sender.sendMessage(consoleMessage.notEnoughPermissions());
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
            case "defaultLives":
                handleDefault(sender, args);
                break;
            default:
                sender.sendMessage(consoleMessage.wrongArguments());
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

        sender.sendMessage(consoleMessage.playerLives(target.getName(), lives));
    }

    private void handleSet(CommandSender sender, String[] args) {
        Player target = getTarget(sender, args);
        if (target == null) return;

        if (!parseAndSetLives(sender, args, target, "set")) return;

        sender.sendMessage(consoleMessage.setPlayerLives(target.getName(), Integer.parseInt(args[2])));
    }

    private void handleAdd(CommandSender sender, String[] args) {
        Player target = getTarget(sender, args);
        if (target == null) return;

        if (!parseAndSetLives(sender, args, target, "add")) return;

        sender.sendMessage(consoleMessage.addPlayerLives(target.getName(), getLastParsedLives()));
    }

    private void handleDefault(CommandSender sender, String[] args) {
        Player target = getTarget(sender, args);
        if (target == null) {
            sender.sendMessage(consoleMessage.noPlayerSelected());
            return;
        }

        Integer lives = target.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
        if (lives != null) {
            target.getPersistentDataContainer().remove(livesKey);
        }
        sender.sendMessage(consoleMessage.defaultLives(target.getName()));
    }

    private int lastParsedLives = 0;

    private int getLastParsedLives() {
        return lastParsedLives;
    }

    private boolean parseAndSetLives(CommandSender sender, String[] args, Player target, String action) {
        if (args.length < 3) {
            sender.sendMessage(consoleMessage.commandHelp(action));
            return false;
        }
        int newLives = 0;
        try {
            int amount = Integer.parseInt(args[2]);
            Integer current = target.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
            if (current == null) current = 0;

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
            sender.sendMessage(consoleMessage.invalidNumber(args[2]));
            return false;
        }
    }

    private Player getTarget(CommandSender sender, String[] args) {
        Player target;
        if (args.length >= 2) {
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(consoleMessage.playerNotFound(args[1]));
                return null;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(consoleMessage.noPlayerSelected());
                return null;
            }
            target = (Player) sender;
        }
        return target;
    }
}
