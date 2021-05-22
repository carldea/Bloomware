package me.offeex.ofx.module.modules.miscellaneous;

import org.lwjgl.glfw.GLFW;

import me.offeex.ofx.module.Module;

public class ExampleMiscellaneousModule extends Module {
	
	public ExampleMiscellaneousModule() {
		super("example miscellaneous module", "this is an example miscellaneous module.", GLFW.GLFW_KEY_J, Category.MISCELLANEOUS, false);
	}
	
}

