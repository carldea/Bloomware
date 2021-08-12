package me.offeex.bloomware.api.config;

import com.google.gson.*;
import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.gui.impl.hud.element.Element;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import me.offeex.bloomware.client.setting.settings.BooleanSetting;
import me.offeex.bloomware.client.setting.settings.KeybindSetting;
import me.offeex.bloomware.client.setting.settings.ModeSetting;
import me.offeex.bloomware.client.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class ConfigManager {
    Path output;

    public ConfigManager() {
        File directory = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public void saveConfig(Module module) throws IOException {
        if (!Files.exists(output = Paths.get(Bloomware.name + "/" + module.getName() + ".json"))) {
            Files.createFile(output);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (module.getSettings() != null) {
            String json = gson.toJson(this.settingWriter(module));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(output)));
            writer.write(json);
            writer.close();
        }
    }

    public void fileRepairer(Module module) throws IOException {
        if (!Files.exists(output = Paths.get(Bloomware.name + "/" + module.getName() + ".json"))) {
            Files.createFile(output);
        } else {
            Files.delete(Paths.get(Bloomware.name + "/" + module.getName() + ".json"));
            Files.createFile(output);
        }
        saveConfig(module);
        loadConfig(module);
    }

    public void loadConfig(Module module) throws IOException {
        Path settings = Paths.get(Bloomware.name + "/" + module.getName() + ".json");
        InputStream stream = Files.newInputStream(settings);
        try {
            Bloomware.configManager.loadSettingsFromFile(new JsonParser().parse((Reader) new InputStreamReader(stream)).getAsJsonObject(), module);
        } catch (Exception exception) {
            Bloomware.LOGGER.info("Found bad config for " + module.getName() + "! Repairing...");
            fileRepairer(module);
        }
    }

    public JsonObject settingWriter(Module module) {
        JsonObject object = new JsonObject();
        for (Setting setting : module.getSettings()) {
            if (setting instanceof NumberSetting) {
                object.add(setting.getName(), new JsonPrimitive(((NumberSetting) setting).getValue()));
            } else if (setting instanceof BooleanSetting) {
                object.add(setting.getName(), new JsonPrimitive(((BooleanSetting) setting).isEnabled()));
            } else if (setting instanceof ModeSetting) {
                object.add(setting.getName(), new JsonPrimitive(((ModeSetting) setting).getMode()));
            } else if (setting instanceof KeybindSetting) {
                object.add(setting.getName(), new JsonPrimitive(((KeybindSetting) setting).getKeyCode()));
            }
        }
        if (module.getCategory().equals(Module.Category.HUD)) {
            object.add("x", new JsonPrimitive(module.x));
            object.add("y", new JsonPrimitive(module.y));
        }
        object.add("enabled", new JsonPrimitive(module.isEnabled()));
        return object;
    }

    private void valueLoader(Setting setting, JsonElement element) {
        if (setting instanceof BooleanSetting) {
            ((BooleanSetting) setting).setEnabled(element.getAsBoolean());
        } else if (setting instanceof NumberSetting) {
            ((NumberSetting) setting).setValue(element.getAsDouble());
        } else if (setting instanceof KeybindSetting) {
            ((KeybindSetting) setting).setKeyCode(element.getAsInt());
        } else if (setting instanceof ModeSetting) {
            ((ModeSetting) setting).setMode(element.getAsString());
        }
    }

    private void loadSettingsFromFile(JsonObject data, Module module) {
        for (Map.Entry<String, JsonElement> entry : data.entrySet()) {
            String settingName = entry.getKey();
            JsonElement value = entry.getValue();

            if (settingName.equals("enabled")) {
                try {
                    if (value.getAsBoolean()) {
                        module.enable();
                    } else {
                        module.disable();
                    }
                } catch (Exception ignored) {}
            }

            if (module.getCategory().equals(Module.Category.HUD)) {
                Element element = Bloomware.hudEditor.getElementByModule(module);
                if (element != null) {
                    if (settingName.equals("x")) {
                        element.setX(value.getAsInt());
                    }
                    if (settingName.equals("y")) {
                        element.setY(value.getAsInt());
                    }
                }
            }

            module.getSettings().forEach(setting -> {
                if (settingName.equals(setting.getName())) {
                    valueLoader(setting, value);
                }
            });
        }
    }
}
