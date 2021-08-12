package me.offeex.bloomware.client.setting.settings;

import me.offeex.bloomware.client.setting.Setting;

public class KeybindSetting extends Setting {

	public int code;
	
	public KeybindSetting(int code) {
		this.setName("Bind");
		this.code = code;
	}

	public int getKeyCode() {
		return this.code;
	}
	
	public void setKeyCode(int code) {
		this.code = code;
	}

}