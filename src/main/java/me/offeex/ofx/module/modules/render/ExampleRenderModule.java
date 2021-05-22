package me.offeex.ofx.module.modules.render;

import org.lwjgl.glfw.GLFW;

import me.offeex.ofx.module.Module;

public class ExampleRenderModule extends Module {
	
	public ExampleRenderModule() {
		super("example render module", "this is an example render module.", GLFW.GLFW_KEY_G, Category.RENDER, false);
	}
	
}
