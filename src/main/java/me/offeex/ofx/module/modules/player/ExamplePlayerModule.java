package me.offeex.ofx.module.modules.player;

import org.lwjgl.glfw.GLFW;

import me.offeex.ofx.module.Module;

public class ExamplePlayerModule extends Module {
	
	public ExamplePlayerModule() {
		super("example player module", "this is an example render module.", GLFW.GLFW_KEY_H, Category.PLAYER, false);
	}
	
}
