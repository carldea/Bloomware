package me.offeex.ofx.command.commands;

import me.offeex.ofx.Main;
import me.offeex.ofx.command.Command;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.modules.miscellaneous.FakePlayer;

import java.io.FileNotFoundException;

public class FakePlayerCommand extends Command {
    public FakePlayerCommand() {
        super("fakeplayer", "Allows you to configure the fake player module", "no", "fakeplayer");
    }

    String defaultN = "TheRealDiOnFire";

    @Override
    public void onCommand(String[] args, String command) {
        if (args.length == 1) {
            try {
                Main.fakePlayer.saver(args[0]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            CommandManager.addChatMessage("FakePlayer's nickname now is " + args[0] + "!");
        } else {
            try {
                Main.fakePlayer.saver(defaultN);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            CommandManager.addChatMessage("Nickname has been reset to default!");
        }
    }
}
