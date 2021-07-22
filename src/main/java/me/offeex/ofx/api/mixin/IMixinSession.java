package me.offeex.ofx.api.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.net.Proxy;

@Mixin(MinecraftClient.class)
public interface IMixinSession {
    @Mutable
    @Accessor("session")
    void setSession(Session session);

    @Accessor("netProxy")
    Proxy getProxy();
}
