package me.offeex.ofx.module.modules.combat;

import org.lwjgl.glfw.GLFW;

import me.offeex.ofx.module.Module;

public class ExampleCombatModule extends Module {
	
	public ExampleCombatModule() {
		super("example combat module", "this is an example combat module.", GLFW.GLFW_KEY_K, Category.COMBAT, false);
	}
	
}
