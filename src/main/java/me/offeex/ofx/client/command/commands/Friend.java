package me.offeex.ofx.client.command.commands;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.command.Command;
import me.offeex.ofx.client.command.CommandManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Friend extends Command {
    public Friend() {
        super("friend", "allows you to add and remove friends", "");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args[0].equals("remove")) {
            try {
                Bloomware.friendManager.removeFriend(args[1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            CommandManager.addChatMessage(TextFormatting.DARK_RED + args[1] + " removed from your friend list");
        } else if (args[0].equals("add")) {
            try {
                Bloomware.friendManager.addFriend(args[1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            CommandManager.addChatMessage(TextFormatting.GREEN + args[1] + " added to your friend list");
        } else if (args[0].equals("list")) {
            ArrayList<String> friends = null;
            try {
                friends = Bloomware.friendManager.loadFriends();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (friends != null) {
                CommandManager.addChatMessage(TextFormatting.BLUE + "Current list of your friends: " + String.join(", ", friends));
            } else {
                CommandManager.addChatMessage(TextFormatting.DARK_RED + "You don't have any friends");
            }
        }
    }
}
