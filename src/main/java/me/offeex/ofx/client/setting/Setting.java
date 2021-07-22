package me.offeex.ofx.client.setting;

import me.offeex.ofx.client.module.Module;

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

}