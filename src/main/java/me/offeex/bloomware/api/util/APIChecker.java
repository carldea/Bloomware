package me.offeex.bloomware.api.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class APIChecker {

    public APIChecker() {
    }

    //    Check if fabric api installed
    File file = new File(System.getProperty("user.home") + "/AppData/Roaming/.minecraft/mods");

    public void check() throws FileNotFoundException {
        if (file.isDirectory()) {
            boolean found = false;
            for (File f : Objects.requireNonNull(file.listFiles())) {
                if (f.getName().startsWith("fabric-api")) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new FileNotFoundException();
            }
        }
    }
}
