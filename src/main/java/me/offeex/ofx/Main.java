package me.offeex.ofx;

import me.offeex.ofx.gui.api.RenderHelper;
import me.offeex.ofx.gui.impl.GuiScreen;
import me.offeex.ofx.gui.impl.GuiScreenManager;
import me.offeex.ofx.hud.HUD;
import me.offeex.ofx.hud.HudScreen;
import me.offeex.ofx.hud.HudScreenManager;
import me.offeex.ofx.hud.component.Component;
import me.offeex.ofx.hud.component.ComponentManager;
import me.offeex.ofx.hud.font.StringRenderer;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.offeex.ofx.api.config.SaveLoad;
import me.offeex.ofx.api.event.EventProcessor;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.module.ModuleManager;
import me.offeex.ofx.setting.SettingManager;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;

public class Main implements ClientModInitializer {

	public static final String name = "OFX";
	public static final String version = "0.1";
	public static final String FontMain = "JetBrainsMonoNL-Bold";

	public static final Logger LOGGER = LogManager.getLogger("ofx");
	public static EventBus EVENTBUS = new EventManager();

	public static HUD hud;
	public static GuiScreenManager gui;
	public static HudScreenManager hudd;
	public static RenderHelper rh;

	public static StringRenderer pFontRenderer;
	public static StringRenderer sFontRenderer;

	public static ModuleManager moduleManager;
	public static ComponentManager componentManager;
	public static SettingManager settingManager;
	public static SaveLoad saveLoad;
	public static EventProcessor eventProcessor;
	public static CommandManager commandManager;
	
	public Object syncronize = new Object();
	public void printLog(String text) {
		synchronized (syncronize) {
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
		
		// inits
		
		eventProcessor = new EventProcessor();
		printLog("event system initialized.");
		
		commandManager = new CommandManager();
		printLog("command system initialized.");

		moduleManager = new ModuleManager();
		printLog("module system initialized.");

		componentManager = new ComponentManager();
		
		settingManager = new SettingManager();
		printLog("setting system initialized.");

		gui = new GuiScreenManager(new GuiScreen());
		hudd = new HudScreenManager(new HudScreen());
		rh = new RenderHelper();

		hud = new HUD();
		printLog("HUD initialized.");
		
		saveLoad = new SaveLoad();
		printLog("config initialized.");

		//
		
		printLog(Main.name + " has finished initialization :)");
	}

}