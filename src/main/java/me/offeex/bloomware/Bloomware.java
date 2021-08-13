/**
 * PROJECT FIXED AND CLEAN UPBY https://github.com/fuckyouthinkimboogieman
 */

package me.offeex.bloomware;

import com.google.common.eventbus.EventBus;
import me.offeex.bloomware.client.setting.SettingManager;
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

    public static final String prefix = "[Bloomware] ";
    public static final String name = "Bloomware";
    public static final String version = "0.11";
    public static String FontMain = "Comfortaa";

    public static final Logger LOGGER = LogManager.getLogger("bloomware");
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
    public static ClickGUI clickGUI;
    public static HudEditor hudEditor;
    public static SettingManager settingManager;

    public static ModuleNotifier moduleNotifier;

    public final Object synchronize = new Object();

    @Override
    public void onInitializeClient() {
        LOGGER.info(Bloomware.prefix + "Initializing...");

        commandManager = new CommandManager();
        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        clickGUI = new ClickGUI();
        hudEditor = new HudEditor();
        accountManager = new AccountManager();
        configManager = new ConfigManager();
        rh = new RenderHelper();
        friendManager = new FriendManager();
        hud = new HUD();

        for (Module module : ModuleManager.getModules()) {
            try {
                configManager.loadConfig(module);
            } catch (Exception e) {
                LOGGER.error(Bloomware.prefix + "Failed to load module: " + module.name);
                LOGGER.error(Bloomware.prefix + e.toString());
            }
        }

        LOGGER.info(Bloomware.prefix + "Initialization done!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Module module : ModuleManager.getModules()) {
                try {
                    configManager.saveConfig(module);
                } catch (Exception e) {
                    LOGGER.error(Bloomware.prefix + "Failed to save module: " + module.name);
                    LOGGER.error(Bloomware.prefix + e.toString());
                }
            }
        }));
    }
}