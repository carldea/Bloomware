package me.offeex.ofx.client.module.modules.render;

import me.offeex.ofx.client.module.Module;

public class NoWeather extends Module {
    public NoWeather() {
        super("NoWeather", "Disables the rain", Category.RENDER, true);
    }
    
    @Override
    public void onTick() {
        assert mc.world != null;
        if (mc.world.isRaining()) {
            mc.world.setRainGradient(0);
        }
    }
}
