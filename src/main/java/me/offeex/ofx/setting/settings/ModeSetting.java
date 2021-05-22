package me.offeex.ofx.setting.settings;

import java.util.Arrays;
import java.util.List;

import me.offeex.ofx.Main;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.setting.Setting;

public class ModeSetting extends Setting {
	public int index;
	  
	public List<String> modes;
	  
	public ModeSetting(String name, Module parent, String defaultMode, String... modes) {
	    this.setName(name);
	    this.setParentMod(parent);
	    this.modes = Arrays.asList(modes);
	    this.index = this.modes.indexOf(defaultMode);
	}
	  
	public String getMode() {
	    return this.modes.get(this.index);
	}
	  
	public void setMode(String mode) {
		  this.index = this.modes.indexOf(mode);
		  
		   if(Main.saveLoad != null) {
				Main.saveLoad.save();
		   }
	}
	  
	public boolean is(String mode) {
	    return (this.index == this.modes.indexOf(mode));
	}
	  
	public void cycle() {
	    if (this.index < this.modes.size() - 1) {
	      this.index++;
	    } else {
	      this.index = 0;
	    }
	}
}