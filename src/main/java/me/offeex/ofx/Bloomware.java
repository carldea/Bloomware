package me.offeex.ofx;

import me.offeex.ofx.client.altmanager.AccountManager;
import me.offeex.ofx.api.config.ConfigManager;
import me.offeex.ofx.api.friends.FriendManager;
import me.offeex.ofx.client.gui.api.RenderHelper;
import me.offeex.ofx.client.gui.impl.clickgui.GuiScreen;
import me.offeex.ofx.client.gui.impl.clickgui.HudScreen;
import me.offeex.ofx.client.gui.impl.hud.HUD;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.module.modules.hud.ModuleNotifier;
import me.offeex.ofx.client.gui.api.font.StringRenderer;
import me.offeex.ofx.client.module.modules.client.ChatNotifier;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.offeex.ofx.api.event.EventProcessor;
import me.offeex.ofx.client.command.CommandManager;
import me.offeex.ofx.client.module.ModuleManager;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;

// todo снести НАХУЙ БЛЯТЬ АЛЬПИН
public class Bloomware implements ClientModInitializer {

	public static final String name = "OFX";
	public static final String version = "1.0-ALPHA";
	public static String FontMain = "Comfortaa-Light";

	public static final Logger LOGGER = LogManager.getLogger("ofx");
	public static EventBus EVENTBUS = new EventManager();

	public static RenderHelper rh;
	public static AccountManager accountManager;
	public static StringRenderer pFontRenderer;
	public static ChatNotifier chatNotifier;
	public static StringRenderer sFontRenderer;

	public static ModuleManager moduleManager;
	public static EventProcessor eventProcessor;
	public static FriendManager friendManager;
	public static CommandManager commandManager;
	public static ConfigManager configManager;
	public static HUD hud;
	public static GuiScreen guiscreen;
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
		printLog("OFX Initialized!");
		
		printLog("\n" +
				".s5SSSs.  .s5SSSs. .s5 s.  \n" +
				"      SS.              SS. \n" +
				"sS    S%S sS       ssS SSS \n" +
				"SS    S%S SS       SSS SSS \n" +
				"SS    S%S SSSs.     SSSSS  \n" +
				"SS    S%S SS       SSS SSS \n" +
				"SS    `:; SS       SSS `:; \n" +
				"SS    ;,. SS       SSS ;,. \n" +
				"`:;;;;;:' :;       `:; ;:' ");

		eventProcessor = new EventProcessor();
		commandManager = new CommandManager();
		moduleManager = new ModuleManager();
		moduleNotifier = new ModuleNotifier();
		chatNotifier = new ChatNotifier();
		guiscreen = new GuiScreen();
		accountManager = new AccountManager();
		hudScreen = new HudScreen();
		configManager = new ConfigManager();
		rh = new RenderHelper();
		friendManager = new FriendManager();
		hud = new HUD();

		new Thread(() -> {
			for (Module module : ModuleManager.modules) {
				try {
					configManager.loadConfig(module);
				} catch (Exception ignored) {}
			}
		});

		printLog(Bloomware.name + " has finished initialization :)");

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			for (Module module : ModuleManager.modules) {
				try {
					configManager.saveConfig(module);
				} catch (Exception ignored) {}
			}
		}));
	}
}