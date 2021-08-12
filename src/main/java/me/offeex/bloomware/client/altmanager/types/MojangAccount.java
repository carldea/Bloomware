package me.offeex.bloomware.client.altmanager.types;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.altmanager.Account;
import me.offeex.bloomware.client.altmanager.AccountTypes;
import net.minecraft.client.util.Session;

import java.net.Proxy;

public class MojangAccount extends Account {
    public MojangAccount(String username, String password) {
        super(username, password, AccountTypes.Mojang);
    }

    @Override
    public String login() {
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);

        auth.setUsername(login);
        auth.setPassword(password);

        try {
            auth.logIn();
            Bloomware.IMC.setSession(new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang"));
            return "Successful login!";
        } catch (AuthenticationUnavailableException e) {
            return "Cannot contact authentication server!";
        } catch (AuthenticationException e) {
            if (e.getMessage().contains("Invalid username or password.") || e.getMessage().toLowerCase().contains("account migrated")) {
                return "Wrong password!";
            } else {
                return "Cannot contact authentication server!";
            }
        } catch (NullPointerException e) {
            return "Wrong password!";
        }
    }
}
