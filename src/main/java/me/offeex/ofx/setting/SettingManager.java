package me.offeex.ofx.setting;

import java.util.ArrayList;

import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.ModuleManager;

public class SettingManager {
	
	private final ArrayList<Setting> settings;
	
	public SettingManager(){
		this.settings = new ArrayList<Setting>();
	}
	
	public void addSetting(Setting in){
		this.settings.add(in);
	}
	
	public ArrayList<Setting> getSettings() {
		return this.settings;
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod) {
		ArrayList<Setting> out = new ArrayList<Setting>();
		for(Setting s : getSettings()) {
			if(s.getParentMod().equals(mod)) {
				out.add(s);
			}
		}
		if(out.isEmpty()) {
			return null;
		}
		return out;
	}
	
	public Setting getSettingByName(Module mod, String name) {
		for (Module m : ModuleManager.modules) {
		for (Setting set : m.settings) {
			if (set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod) {
				return set;
			}
			}
		}
		System.err.println("[postman] Error Setting NOT found: '" + name +"'!");
		return null;
	}
}