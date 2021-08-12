package me.offeex.ofx.client.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {
	public String name, description;
	public String modifier;
	public List<String> aliases;
	
	public Command(String name, String description, String modifier, String... aliases) {
		this.name = name;
		this.description = description;
		this.aliases = Arrays.asList(aliases);
		this.modifier = modifier;
	}
	
	public abstract void onCommand(String[] args, String command);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
}
