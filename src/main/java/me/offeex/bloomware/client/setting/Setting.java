package me.offeex.bloomware.client.setting;

import me.offeex.bloomware.client.module.Module;

public abstract class Setting {

	private String name;
	private Module parent;

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

}