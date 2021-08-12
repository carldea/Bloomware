package me.offeex.bloomware.client.altmanager.types;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.altmanager.Account;
import me.offeex.bloomware.client.altmanager.AccountTypes;
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
