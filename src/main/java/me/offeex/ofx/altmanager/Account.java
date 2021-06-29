package me.offeex.ofx.altmanager;

import com.mojang.authlib.yggdrasil.YggdrasilEnvironment;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import me.offeex.ofx.api.mixin.MixinSession;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;

public class Account {
    public String login, password;
    public AccountTypes type;
    MixinSession mixinSession;
    MinecraftClient mc;

    public Account(String login, String password, AccountTypes type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected void setSession(Session session) {
        mixinSession.setSession(session);
    }

    public void login() {
        YggdrasilMinecraftSessionService service = (YggdrasilMinecraftSessionService) mc.getSessionService();
        AccountHelper.setBaseUrl(service, YggdrasilEnvironment.PROD.getSessionHost() + "/session/minecraft/");
        AccountHelper.setJoinUrl(service, YggdrasilEnvironment.PROD.getSessionHost() + "/session/minecraft/join");
        AccountHelper.setCheckUrl(service, YggdrasilEnvironment.PROD.getSessionHost() + "/session/minecraft/hasJoined");
    }
}
