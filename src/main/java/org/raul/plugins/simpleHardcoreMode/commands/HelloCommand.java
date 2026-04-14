package org.raul.plugins.simpleHardcoreMode.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class HelloCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            if(args[0].equals("world")){
                sender.sendMessage("Hello world");
            }else{
                sender.sendMessage("Hola mundo");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completer = new ArrayList<>();
        if (args.length == 1){
            String arg0 = args[0];

            String[] words = {"world","mundo"};
            for(String search : words){
                if(search.startsWith(arg0)){
                    completer.add(search);
                }
            }
        }

        return completer;
    }
}
