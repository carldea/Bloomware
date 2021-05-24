package me.offeex.ofx.module.modules.miscellaneous;

import com.mojang.authlib.GameProfile;
import me.offeex.ofx.Main;
import me.offeex.ofx.api.config.FakePlayerSaver;
import me.offeex.ofx.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.OtherClientPlayerEntity;
import org.lwjgl.glfw.GLFW;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class FakePlayer extends Module {
    public FakePlayer() {
        super("Fake Player", "Summons a fake player entity", GLFW.GLFW_KEY_UNKNOWN, Category.MISCELLANEOUS, false);
    }

    private File dir, dataFile;
    private String lineN;

    public String nickname = "TheRealDiOnFire", UUID_Data = "ab47040f-6bcb-4c00-9424-238be6eec731";

    @Override
    public void onEnable() {
        dir = new File(MinecraftClient.getInstance().runDirectory, Main.name);
        dataFile = new File(dir, "fakeplayer.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while(line != null) {
                lineN = line;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] args = lineN.split(":");
        nickname = args[1];
        if (mc.world != null && mc.player != null) {
            OtherClientPlayerEntity player = new OtherClientPlayerEntity(mc.world, new GameProfile(UUID.fromString(UUID_Data), nickname));
            player.copyPositionAndRotation(mc.player);
            player.setHeadYaw(mc.player.headYaw);
            mc.world.addEntity(-100, player);
        } else {
            disable();
        }
    }

    @Override
    public void onDisable() {
        if (mc.world != null) {
            mc.world.removeEntity(-100);
        }
    }
}
