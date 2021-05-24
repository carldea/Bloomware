package me.offeex.ofx.api.config;

import me.offeex.ofx.Main;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FakePlayerSaver {
    private File dir;
    private File dataFile;

    private String defaultNickname = "TheRealDiOnFire", UUID = "ab47040f-6bcb-4c00-9424-238be6eec731";

    public void fileManager() {
        dir = new File(MinecraftClient.getInstance().runDirectory, Main.name);
        if(!dir.exists()) {
            dir.mkdir();
        }
        dataFile = new File(dir, "fakeplayer.txt");
        if(!dataFile.exists()) {
            try {
                dataFile.createNewFile();
                PrintWriter pw = new PrintWriter(this.dataFile);
                pw.println("NICKNAME:" + defaultNickname);
                pw.close();
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    public void saver(String username) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(this.dataFile);
        pw.flush();
        if (username == null) {
            pw.println("NICKNAME:" + defaultNickname);
        } else {
            pw.println("NICKNAME:" + username);
        }

        pw.close();
    }
}
