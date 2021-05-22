package me.offeex.ofx.setting;

import me.offeex.ofx.module.Module;

/**
 *  @author HeroCode
 */

public abstract class Setting {

	private String name;
	private Module parent;


	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Module getParentMod() {
		return parent;
	}

	public void setParentMod(Module parent) {
		this.parent = parent;
	}

}