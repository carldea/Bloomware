package me.offeex.ofx.setting.settings;

import me.offeex.ofx.module.Module;
import me.offeex.ofx.setting.Setting;

public class KeybindSetting extends Setting {

	public int code;
	
	public KeybindSetting(int code) {
		this.setName("KeyBind");
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