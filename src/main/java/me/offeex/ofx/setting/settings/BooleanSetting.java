package me.offeex.ofx.setting.settings;

import me.offeex.ofx.Main;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.setting.Setting;

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
	    
//	    if(Main.saveLoad != null) {
//			Main.saveLoad.save();
//	    }
	}
	
	public void toggle() {
	    this.enabled = !this.enabled;
	    
//	    if(Main.saveLoad != null) {
//			Main.saveLoad.save();
//	    }
	}
}
