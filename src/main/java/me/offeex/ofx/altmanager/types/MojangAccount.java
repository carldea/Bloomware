package me.offeex.ofx.altmanager.types;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import me.offeex.ofx.altmanager.Account;
import me.offeex.ofx.altmanager.AccountTypes;
import me.offeex.ofx.api.mixin.MixinSession;

public class MojangAccount extends Account {
    MixinSession mc;
    YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(((MixinSession) mc).getProxy(), "").createUserAuthentication(Agent.MINECRAFT);

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
