package me.offeex.ofx.api.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import me.offeex.ofx.Main;
import me.offeex.ofx.command.CommandManager;
import me.offeex.ofx.gui.impl.hud.component.Component;
import me.offeex.ofx.gui.impl.hud.component.ComponentManager;
import me.offeex.ofx.setting.Setting;
import me.offeex.ofx.setting.settings.BooleanSetting;
import me.offeex.ofx.setting.settings.ModeSetting;
import me.offeex.ofx.setting.settings.NumberSetting;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;
import net.minecraft.client.MinecraftClient;

/*
 * Written by @SrgantMooMoo on 11/30/20 with inspiration taken from @SebSb.
 */

public class SaveLoad {

	private File dir;
	private final File dataFile;
	   
	public SaveLoad() {
		dir = new File(MinecraftClient.getInstance().runDirectory, Main.name);
		if(!dir.exists()) {
			dir.mkdir();
		}
		dataFile = new File(dir, "config.txt");
		if(!dataFile.exists()) {
			try {
				dataFile.createNewFile();
			} catch (IOException e) {e.printStackTrace();}
		}
		
		this.load();
	}
	
	public void save() {
		ArrayList<String> toSave = new ArrayList<String>();
		
		for(Module mod : ModuleManager.modules) {
			if(!mod.getName().equals("tabGui"))
			toSave.add("MOD:" + mod.getName() + ":" + mod.isEnabled() + ":" + mod.getKey());
		}

		for(Component c : ComponentManager.getComponents()) {
			toSave.add("COMP:" + c.getName() + ":" + c.isEnabled());
		}
		
		for(Module mod : ModuleManager.modules) {
			for(Setting setting : mod.settings) {
				
				if(setting instanceof BooleanSetting) {
					BooleanSetting bool = (BooleanSetting) setting;
					toSave.add("SET:" + mod.getName() + ":" + setting.getName() + ":" + bool.isEnabled());
				}
				
				if(setting instanceof NumberSetting) {
					NumberSetting numb = (NumberSetting) setting;
					toSave.add("SET:" + mod.getName() + ":" + setting.getName() + ":" + numb.getValue());
				}
				
				if(setting instanceof ModeSetting) {
					ModeSetting mode = (ModeSetting) setting;
					toSave.add("SET:" + mod.getName() + ":" + setting.getName() + ":" + mode.getMode());
				}
			}
		}

		for(Component c : ComponentManager.getComponents()) {
			for(Setting setting : c.settings) {

				if(setting instanceof BooleanSetting) {
					BooleanSetting bool = (BooleanSetting) setting;
					toSave.add("SET:" + c.getName() + ":" + setting.getName() + ":" + bool.isEnabled());
				}

				if(setting instanceof NumberSetting) {
					NumberSetting numb = (NumberSetting) setting;
					toSave.add("SET:" + c.getName() + ":" + setting.getName() + ":" + numb.getValue());
				}

				if(setting instanceof ModeSetting) {
					ModeSetting mode = (ModeSetting) setting;
					toSave.add("SET:" + c.getName() + ":" + setting.getName() + ":" + mode.getMode());
				}
			}
			toSave.add("POS:" + c.getName() + c.x + ":" + c.y);
		}

		// command prefix
		toSave.add("COMMANDPREFIX:" + CommandManager.getPrefix());
		
		try {
			PrintWriter pw = new PrintWriter(this.dataFile);
			for(String str : toSave) {
				pw.println(str);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
			String line = reader.readLine();
			while(line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
			}
		
		for(String s : lines) {
			String[] args = s.split(":");
			if(s.toLowerCase().startsWith("mod:")) {
				Module m = Main.moduleManager.getModule(args[1]);
				if (m != null) {
					if (m.getName().equals("clickGuiModule") && m.getName().equals("hudEditor"))
						m.setEnabled(!Boolean.parseBoolean(args[2]));

					if (!m.getName().equals("clickGuiModule") && !m.getName().equals("hudEditor"))
						m.setEnabled(Boolean.parseBoolean(args[2]));
					m.setKey(Integer.parseInt(args[3]));
				}
			} else if(s.toLowerCase().startsWith("comp:")) {
				Component c = Main.componentManager.getComponent(args[1]);
				if (c != null) c.setEnabled(Boolean.parseBoolean(args[2]));

			} else if(s.toLowerCase().startsWith("set:")) {
				Module m = Main.moduleManager.getModule(args[1]);
				if(m != null) {
					Setting setting = Main.settingManager.getSettingByName(m,args[2]);
					if(setting != null) {
						if(setting instanceof BooleanSetting) {
							((BooleanSetting)setting).setEnabled(Boolean.parseBoolean(args[3]));
						}
						if(setting instanceof NumberSetting) {
							((NumberSetting)setting).setValue(Double.parseDouble(args[3]));
						}
						if(setting instanceof ModeSetting) {
							((ModeSetting)setting).setMode(args[3]);
						}
					}
				}
			} else if (s.toLowerCase().startsWith("pos:")) {
				Component c = Main.componentManager.getComponent(args[1]);
				if (c != null) {
					c.setX(Integer.parseInt(args[2]));
					c.setY(Integer.parseInt(args[3]));
				}
			} else if(s.toLowerCase().startsWith("commandprefix:")) {
				CommandManager.setCommandPrefix(args[1]);
			}
		}
	}
}
