package me.offeex.ofx.client.altmanager.types;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.client.altmanager.Account;
import me.offeex.ofx.client.altmanager.AccountTypes;
import net.minecraft.client.util.Session;

public class CrackedAccount extends Account {
    public CrackedAccount(String name) {
        super(name, "", AccountTypes.Cracked);
    }

    @Override
    public String login() {
        Bloomware.IMC.setSession(new Session(login, "", "", "mojang"));
        return "Ok!";
    }
}
