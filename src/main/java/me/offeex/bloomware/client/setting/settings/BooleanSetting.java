package me.offeex.bloomware.client.setting.settings;

import me.offeex.bloomware.client.setting.Setting;

public class BooleanSetting extends Setting {
	public boolean enabled;
	  
	public BooleanSetting(String name, boolean enabled) {
	    this.setName(name);
	    //this.setParentMod(parent);
	    this.enabled = enabled;
	}
	  
	public boolean isEnabled() {
	    return this.enabled;
	}
	  
	public void setEnabled(boolean enabled) {
	    this.enabled = enabled;
	}
	
	public void toggle() {
	    this.enabled = !this.enabled;
	}
}
