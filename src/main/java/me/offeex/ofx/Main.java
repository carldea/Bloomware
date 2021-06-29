package me.offeex.ofx;

import me.offeex.ofx.altmanager.AccountManager;
import me.offeex.ofx.api.config.ConfigManager;
import me.offeex.ofx.api.config.FakePlayerSaver;
import me.offeex.ofx.api.friends.FriendManager;
import me.offeex.ofx.api.util.CapeUtil;
import me.offeex.ofx.gui.api.RenderHelper;
import me.offeex.ofx.gui.impl.clickgui.GuiScreen;
import me.offeex.ofx.gui.impl.clickgui.HudScreen;
import me.offeex.ofx.gui.impl.hud.HUD;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.modules.hud.ModuleNotifier;
import me.offeex.ofx.gui.api.font.StringRenderer;
import me.offeex.ofx.module.modules.client.ChatNotifier;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.offeex.ofx.api.event.EventProcessor;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.ModuleManager;
//import me.offeex.ofx.setting.SettingManager;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;

import java.io.IOException;

public class Main implements ClientModInitializer {

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
	public static CapeUtil capeUtil;
	//public static SettingManager settingManager;
//	public static SaveLoad saveLoad;
	public static EventProcessor eventProcessor;
	public static FriendManager friendManager;
	public static CommandManager commandManager;
	public static FakePlayerSaver fakePlayer;
	public static ConfigManager configManager;
	public static HUD hud;
	//	public static ScreensManager screens;
	public static GuiScreen guiscreen;
	public static HudScreen hudScreen;

	public static ModuleNotifier moduleNotifier;
	//public static StreamerModeWindow streamerWin;
	
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
		fakePlayer = new FakePlayerSaver();
		fakePlayer.fileManager();
		moduleNotifier = new ModuleNotifier();
		chatNotifier = new ChatNotifier();
		guiscreen = new GuiScreen();
		accountManager = new AccountManager();
		hudScreen = new HudScreen();
		configManager = new ConfigManager();
		rh = new RenderHelper();
		friendManager = new FriendManager();
		//streamerWin = new StreamerModeWindow();
		capeUtil = new CapeUtil();
		hud = new HUD();
		for (Module module : ModuleManager.getModules()) {
			try {
				configManager.loadConfig(module);
			} catch (IOException e) {
				try {
					configManager.saveConfig(module);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}
		printLog(Main.name + " has finished initialization :)");
	}
}