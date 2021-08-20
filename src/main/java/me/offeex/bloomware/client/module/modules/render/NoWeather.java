package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.module.Module;

public class NoWeather extends Module {
    public NoWeather() {
        super("NoWeather", "Disables the rain", Category.RENDER, true);
    }
    
    @Override
    public void onTick() {
        if (mc.world.isRaining()) {
            mc.world.setRainGradient(0);
        }
    }
}
