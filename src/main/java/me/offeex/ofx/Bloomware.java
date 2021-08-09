/**
 * PROJECT FIXED AND CLEAN UPBY https://github.com/fuckyouthinkimboogieman
 */

package me.offeex.ofx;

import com.google.common.eventbus.EventBus;
import me.offeex.ofx.client.altmanager.AccountManager;
import me.offeex.ofx.api.config.ConfigManager;
import me.offeex.ofx.api.friends.FriendManager;
import me.offeex.ofx.client.gui.api.RenderHelper;
import me.offeex.ofx.client.gui.impl.newclick.ClickGUI;
import me.offeex.ofx.client.gui.impl.oldclick.GuiScreen;
import me.offeex.ofx.client.gui.impl.oldclick.HudScreen;
import me.offeex.ofx.client.gui.impl.hud.HUD;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.modules.hud.ModuleNotifier;
import me.offeex.ofx.client.gui.api.font.StringRenderer;
import me.offeex.ofx.client.module.modules.client.ChatNotifier;
import me.offeex.ofx.mixins.IMixinSession;
import me.zero.alpine.bus.EventManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.offeex.ofx.client.command.CommandManager;
import me.offeex.ofx.client.module.ModuleManager;

import java.io.IOException;

public class Bloomware implements ClientModInitializer {

    public static final String name = "Bloomware";
    public static final String version = "1.0-ALPHA";
    public static String FontMain = "Comfortaa";

    public static final Logger LOGGER = LogManager.getLogger("bloomware");
    public static EventBus EVENTBUS = new EventBus();
    public static me.zero.alpine.bus.EventBus EVENTBUS_ALPINE = new EventManager();

    public static RenderHelper rh;
    public static AccountManager accountManager;
    public static StringRenderer pFontRenderer;
    public static ChatNotifier chatNotifier;
    public static StringRenderer sFontRenderer;

    public static final IMixinSession IMC = (IMixinSession) MinecraftClient.getInstance();

    public static ModuleManager moduleManager;
    public static FriendManager friendManager;
    public static CommandManager commandManager;
    public static ConfigManager configManager;
    public static HUD hud;
    public static GuiScreen oldGui;
    public static ClickGUI newGui;
    public static HudScreen hudScreen;

    public static ModuleNotifier moduleNotifier;

    public final Object synchronize = new Object();

    public void printLog(String text) {
        synchronized (synchronize) {
            LOGGER.info(text);
        }
    }

    @Override
    public void onInitializeClient() {
        printLog("Bloomware started ratting you!");

        System.out.println(
                "__________.__                                                      \n" +
                        "\\______   \\  |   ____   ____   _______  _  _______ _______   ____  \n" +
                        " |    |  _/  |  /  _ \\ /  _ \\ /     \\ \\/ \\/ /\\__  \\\\_  __ \\_/ __ \\ \n" +
                        " |    |   \\  |_(  <_> |  <_> )  Y Y  \\     /  / __ \\|  | \\/\\  ___/ \n" +
                        " |______  /____/\\____/ \\____/|__|_|  /\\/\\_/  (____  /__|    \\___  >\n" +
                        "        \\/                         \\/             \\/            \\/ ");

        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        moduleNotifier = new ModuleNotifier();
        chatNotifier = new ChatNotifier();
        oldGui = new GuiScreen();
        newGui = new ClickGUI();
        accountManager = new AccountManager();
        hudScreen = new HudScreen();
        configManager = new ConfigManager();
        rh = new RenderHelper();
        friendManager = new FriendManager();
        hud = new HUD();

        for (Module module : ModuleManager.getModules()) {
            try {
                configManager.loadConfig(module);
            } catch (Exception ignored) {
                try {
                    configManager.fileRepairer(module);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        printLog(Bloomware.name + " finished ratting you!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Module module : ModuleManager.getModules()) {
                try {
                    configManager.saveConfig(module);
                } catch (Exception ignored) {
                }
            }
        }));
    }
}