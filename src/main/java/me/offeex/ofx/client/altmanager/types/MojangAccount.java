package me.offeex.ofx.client.altmanager.types;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import me.offeex.ofx.client.altmanager.Account;
import me.offeex.ofx.client.altmanager.AccountTypes;
import me.offeex.ofx.mixins.IMixinSession;

public class MojangAccount extends Account {
    IMixinSession mc;
    YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(mc.getProxy(), "").createUserAuthentication(Agent.MINECRAFT);

    public MojangAccount(String username, String password) {
        super(username, password, AccountTypes.Mojang);
    }

    @Override
    public void login() {
        super.login();
        try {
            auth.setUsername(login);
            auth.setUsername(password);
            auth.logIn();
        } catch (AuthenticationException exception) {
            System.out.print("failed to login");
        }
    }
}
