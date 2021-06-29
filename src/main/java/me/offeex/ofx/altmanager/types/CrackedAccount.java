package me.offeex.ofx.altmanager.types;

import me.offeex.ofx.altmanager.Account;
import me.offeex.ofx.altmanager.AccountTypes;
import net.minecraft.client.util.Session;

public class CrackedAccount extends Account {
    public CrackedAccount(String name) {
        super(name, "", AccountTypes.Cracked);
    }

    @Override
    public void login() {
        super.login();
        setSession(new Session(login, "", "", "mojang"));
    }
}
