package me.offeex.ofx.hud;

public class HudScreenManager {
    private static HudScreenManager INSTANCE;
    private final HudScreen hudScreen;

    public HudScreenManager(HudScreen hudScreen) {
        INSTANCE = this;
        this.hudScreen = hudScreen;
    }

    public HudScreen getHudScreen() {
        return hudScreen;
    }

    public static HudScreenManager getInstance() {
        return INSTANCE;
    }
}
