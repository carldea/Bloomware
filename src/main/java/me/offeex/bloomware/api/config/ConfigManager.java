package me.offeex.bloomware.api.config;

import com.google.gson.*;
import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.gui.impl.hud.element.Element;
import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.module.ModuleManager;
import me.offeex.bloomware.client.setting.Setting;
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
        if (Bloomware.settingManager.getSettings(module) != null && !Bloomware.settingManager.getSettings(module).isEmpty()) {
            {
                String json = gson.toJson(this.settingWriter(module));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(output)));
                writer.write(json);
                writer.close();
            }
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
            Bloomware.logger.info("Found bad config for " + module.getName() + "! Repairing...");
            fileRepairer(module);
        }
    }

    public JsonObject settingWriter(Module module) {
        JsonObject object = new JsonObject();
        for (Setting setting : Bloomware.settingManager.getSettings(module)) {
            if (setting.getType().equalsIgnoreCase("Double")) {
                object.add(setting.getName(), new JsonPrimitive(((Setting<Number>) setting).getValue()));
            } else if (setting.getType().equalsIgnoreCase("Boolean")) {
                object.add(setting.getName(), new JsonPrimitive(((Setting<Boolean>) setting).getValue()));
            } else if (setting.getType().equalsIgnoreCase("String")) {
                object.add(setting.getName(), new JsonPrimitive(((Setting<String>) setting).getValue()));
            }
        }
        if (module.getCategory().equals(Module.Category.HUD)) {
            object.add("x", new JsonPrimitive(module.x));
            object.add("y", new JsonPrimitive(module.y));
        }
        object.add("enabled", new JsonPrimitive(module.isEnabled()));
        object.add("bind", new JsonPrimitive(module.getKey()));
        return object;
    }

    private void valueLoader(Setting setting, JsonElement element) {
        if (setting.getType().equalsIgnoreCase("Boolean")) {
            ((Setting<Boolean>) setting).setValue(element.getAsBoolean());
        } else if (setting.getType().equalsIgnoreCase("Double")) {
            ((Setting<Number>) setting).setValue(element.getAsDouble());
        } else if (setting.getType().equalsIgnoreCase("String")) {
            ((Setting<String>) setting).setValue(element.getAsString());
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
                } catch (Exception ignored) {
                }
            }

            if (settingName.equals("bind")) {
                module.setKey(value.getAsInt());
            }

            if (settingName.equals("Drawn")) {
                module.shown.setValue(value.getAsBoolean());
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

            Bloomware.settingManager.getSettings(module).forEach(setting -> {
                if (settingName.equals(setting.getName())) {
                    valueLoader(setting, value);
                }
            });
        }
    }
}
