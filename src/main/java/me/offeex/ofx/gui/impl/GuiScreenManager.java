package me.offeex.ofx.gui.impl;

public class GuiScreenManager {
    private static GuiScreenManager INSTANCE;
    private final GuiScreen guiScreen;

    public GuiScreenManager(GuiScreen guiScreen) {
        INSTANCE = this;
        this.guiScreen = guiScreen;
    }

    public GuiScreen getGuiScreen() {
        return guiScreen;
    }

    public static GuiScreenManager getInstance() {
        return INSTANCE;
    }
}
