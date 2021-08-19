package me.offeex.bloomware.api.util;

import me.offeex.bloomware.Bloomware;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;

// Говнище класс
@Deprecated
public class APIChecker {

    public APIChecker() {
        File path = new File(MinecraftClient.getInstance().runDirectory, "/mods");
        if (path.isDirectory()) {
            if (!Arrays.stream(path.listFiles()).filter(p -> p.getName().startsWith("fabric-api")).findAny().isPresent()) {
                Bloomware.logger.info("Seems you have not installed Fabric-Api.");
                Bloomware.logger.info("Drag Fabric-Api into /mods folder and try again.");

                Runtime.getRuntime().exit(1);
            }
        }
    }
}
