package me.offeex.ofx.client.command.commands;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.friends.Friend;
import me.offeex.ofx.client.command.Command;
import me.offeex.ofx.client.command.CommandManager;
import net.minecraft.util.Formatting;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
            try {
                Bloomware.friendManager.addFriend(new Friend(args[1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            CommandManager.addChatMessage(Formatting.GREEN + args[1] + " added to your friend list");
        }
    }
}
