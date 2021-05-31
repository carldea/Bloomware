package me.offeex.ofx.api.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerDataUtil {
    private final MinecraftClient mc = MinecraftClient.getInstance();


    public String getCoords() {
        assert mc.player != null;
        assert MinecraftClient.getInstance().player != null;
        return String.format("%.1f",MinecraftClient.getInstance().player.getX()) + ", " + String.format("%.1f",MinecraftClient.getInstance().player.getY()) + ", " + String.format("%.1f",MinecraftClient.getInstance().player.getZ());
    }

    public int getDimension() {
        return DimensionUtil.getDimension();
    }

    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public String getFps() {
        return mc.fpsDebugString.split(" ")[0];
    }

    public String getServer() {
        if (mc.getCurrentServerEntry() != null) {
            return mc.getCurrentServerEntry().address;
        } else if (mc.isInSingleplayer()) {
            return "Singleplayer";
        } else {
            return "null";
        }
    }

    public int getPing() {
        assert mc.getNetworkHandler() != null;
        assert mc.player != null;
        PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        assert playerListEntry != null;
        return playerListEntry.getLatency();
    }
}
