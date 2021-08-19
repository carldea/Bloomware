/**
 * PROJECT FIXED AND CLEAN UPBY https://github.com/fuckyouthinkimboogieman
 */

package me.offeex.bloomware;

import com.google.common.eventbus.EventBus;
import me.offeex.bloomware.client.setting.SettingManager;
import me.offeex.bloomware.api.util.APIChecker;
import me.offeex.bloomware.client.altmanager.AccountManager;
import me.offeex.bloomware.api.config.ConfigManager;
import me.offeex.bloomware.api.friends.FriendManager;
import me.offeex.bloomware.client.gui.api.RenderHelper;
import me.offeex.bloomware.client.gui.impl.clickgui.ClickGUI;
import me.offeex.bloomware.client.gui.impl.clickgui.HudEditor;
import me.offeex.bloomware.client.gui.impl.hud.HUD;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.modules.hud.ModuleNotifier;
import me.offeex.bloomware.client.gui.api.font.StringRenderer;
import me.offeex.bloomware.client.module.modules.client.ChatNotifier;
import me.offeex.bloomware.mixins.IMixinSession;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.offeex.bloomware.client.command.CommandManager;
import me.offeex.bloomware.client.module.ModuleManager;

public class Bloomware implements ClientModInitializer {

    public static final String name = "Bloomware";
    public static final String version = "0.2.0";
    public static String FontMain = "Comfortaa";

    public static final Logger logger = LogManager.getLogger("bloomware");
    public static EventBus EVENTBUS = new EventBus();

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
    public static ClickGUI newGui;
    public static HudEditor hudEditor;
    public static SettingManager settingManager;

    public static ModuleNotifier moduleNotifier;

    public static APIChecker apiChecker;
    public final Object synchronize = new Object();

    public void printLog(String text) {
        synchronized (synchronize) {
            logger.info(text);
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

        apiChecker = new APIChecker();
        commandManager = new CommandManager();
        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        moduleNotifier = new ModuleNotifier();
        chatNotifier = new ChatNotifier();
        newGui = new ClickGUI();
        hudEditor = new HudEditor();
        accountManager = new AccountManager();
        configManager = new ConfigManager();
        rh = new RenderHelper();
        friendManager = new FriendManager();
        hud = new HUD();

        for (Module module : ModuleManager.getModules()) {
            try {
                configManager.loadConfig(module);
            } catch (Exception ignored) {
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
            ModuleManager.getModule("Freecam").disable();
        }));
    }
}