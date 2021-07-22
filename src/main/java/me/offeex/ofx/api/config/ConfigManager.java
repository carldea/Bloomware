package me.offeex.ofx.api.config;

import com.google.gson.*;
import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.module.Module;
import me.offeex.ofx.client.setting.Setting;
import me.offeex.ofx.client.setting.settings.BooleanSetting;
import me.offeex.ofx.client.setting.settings.KeybindSetting;
import me.offeex.ofx.client.setting.settings.ModeSetting;
import me.offeex.ofx.client.setting.settings.NumberSetting;
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
            String json = gson.toJson((JsonElement) this.settingWriter(module));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(output)));
            writer.write(json);
            writer.close();
        }
    }

    public void loadConfig(Module module) throws IOException {
        Path settings = Paths.get(Bloomware.name + "/" + module.getName() + ".json");
        InputStream stream = Files.newInputStream(settings);
        Bloomware.configManager.loadSettingsFromFile(new JsonParser().parse((Reader) new InputStreamReader(stream)).getAsJsonObject(), module);
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
        for (Map.Entry entry : data.entrySet()) {
            String settingName = (String) entry.getKey();
            JsonElement value = (JsonElement) entry.getValue();
//            if (settingName.equals("enabled")) {
//                module.setEnabled(value.getAsBoolean());
//                if (value.getAsBoolean()) {
//                    try {
//                        module.setEnabled(true);
//                        module.enable();
//                    } catch (Exception ignored) {}
//                } else {
//                    try {
//                        module.setEnabled(true);
//                        module.disable();
//                    } catch (Exception ignored) {}
//                }
//            }
            for (Setting setting : module.getSettings()) {
                if (!settingName.equals(setting.getName())) {
                    continue;
                } else {
                    valueLoader(setting, value);
                }
            }
        }
    }
}
