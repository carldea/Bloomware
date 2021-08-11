package me.offeex.ofx.client.module.modules.miscellaneous;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.offeex.ofx.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class RichPresence extends Module {
    public RichPresence() {
        super("RichPresence", "Shows your in-game activity in Discord rich presence", GLFW.GLFW_KEY_UNKNOWN, Category.MISCELLANEOUS, false);
    }

    public static final DiscordRichPresence rpc = new DiscordRichPresence();
    public static final DiscordRPC lib = DiscordRPC.INSTANCE;
    String applicationId = "845695870452367370";
    String steamId = "";

    @Override
    public void onEnable() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        lib.Discord_UpdatePresence(rpc);
        rpc.largeImageKey = "logo";
        rpc.state = "IGN: " + mc.getSession().getUsername();
        rpc.startTimestamp = System.currentTimeMillis() / 1000L;
        lib.Discord_UpdatePresence(rpc);
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                if (mc.getCurrentServerEntry() != null) {
                    rpc.details = "Playing on " + mc.getCurrentServerEntry().address;
                } else if (mc.isInSingleplayer()) {
                    rpc.details = "Playing on singleplayer";
                } else {
                    rpc.details = "On main menu";
                }
                lib.Discord_UpdatePresence(rpc);

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    lib.Discord_Shutdown();
                    break;
                }
            }
        }, "RPC-Callback-Handler");
        t.start();
    }

    @Override
    public void onDisable() {
        lib.Discord_ClearPresence();
        lib.Discord_Shutdown();
    }
}
