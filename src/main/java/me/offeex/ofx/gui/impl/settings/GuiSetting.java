package me.offeex.ofx.gui.impl.settings;

import me.offeex.ofx.setting.Setting;

public abstract class GuiSetting<T extends Setting> {
    protected T setting;
    public GuiSetting(T setting){
        this.setting = setting;
    }
    public void draw (){

    }
}
