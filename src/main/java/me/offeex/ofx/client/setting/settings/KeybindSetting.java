package me.offeex.ofx.client.setting.settings;

import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.Setting;

public class KeybindSetting extends Setting {

	public int code;
	
	public KeybindSetting(int code) {
		this.setName("Bind");
		this.code = code;
	}
	
	public KeybindSetting(Module module) {

	}

	public int getKeyCode() {
		return this.code;
	}
	
	public void setKeyCode(int code) {
		this.code = code;
	}

}