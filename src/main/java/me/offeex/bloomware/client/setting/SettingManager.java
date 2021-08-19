package me.offeex.bloomware.client.setting;

import me.offeex.bloomware.client.module.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SettingManager {
    private final List<Setting<?>> settings;

    public SettingManager() {
        this.settings = new ArrayList<>();
    }

    public List<Setting<?>> getSettings() {
        return this.settings;
    }

    public void add(Setting<?> setting) {
        this.settings.add(setting);
    }

    public Setting<?> getSetting(String name, Module module) {
        return this.settings.stream().filter(s -> s.getModule().equals(module) && s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Setting<?>> getSettings(Module module) {
        return this.settings.stream().filter(s -> s.getModule().equals(module)).collect(Collectors.toList());
    }

    public Setting<?> getSetting(String name) {
        return settings.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}