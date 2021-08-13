package me.offeex.bloomware.client.command.commands;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.friends.Friend;
import me.offeex.bloomware.client.command.Command;
import me.offeex.bloomware.client.command.CommandManager;
import net.minecraft.util.Formatting;

import java.io.FileNotFoundException;

public class FriendCommand extends Command {
    public FriendCommand() {
        super("friend", "allows you to add and remove friends", "");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if (args[0].equals("remove")) {
            boolean removed = false;
            try {
                removed = Bloomware.friendManager.removeFriend(new Friend(args[1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (removed) {
                CommandManager.addChatMessage(Formatting.DARK_RED + args[1] + " removed from your friend list");
            } else {
                CommandManager.addChatMessage(Formatting.DARK_RED + args[1] + " not found");
            }
        } else if (args[0].equals("add")) {
            boolean added = false;
            try {
                added = Bloomware.friendManager.addFriend(new Friend(args[1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (added) {
                CommandManager.addChatMessage(Formatting.GREEN + args[1] + " added to your friend list");
            } else {

            }
        }
    }
}
