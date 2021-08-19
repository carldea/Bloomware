package me.offeex.bloomware.client.module.modules.client;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;

public class Colors extends Module {

    public final Setting<Number> textR = register("TextRed", 153, 1, 255, 1);
    public final Setting<Number> textG = register("TextGreen", 11, 1, 255, 1);
    public final Setting<Number> textB = register("TextBlue", 87, 1, 255, 1);

    public final Setting<Number> sliderR = register("SliderRed", 153, 1, 255, 1);
    public final Setting<Number> sliderG = register("SliderGreen", 11, 1, 255, 1);
    public final Setting<Number> sliderB = register("SliderBlue", 87, 1, 255, 1);

    public final Setting<Number> hudR = register("HudRed", 249, 1, 255, 1);
    public final Setting<Number> hudG = register("HudGreen", 47, 1, 255, 1);
    public final Setting<Number> hudB = register("HudBlue", 154, 1, 255, 1);

    private static Colors INSTANCE;

    public Colors() {
        super("Colors", "Configures colors", Category.CLIENT, true);
        INSTANCE = this;
    }

    public static Colors getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        this.disable();
    }
}
