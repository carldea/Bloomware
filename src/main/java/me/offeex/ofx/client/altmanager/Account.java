package me.offeex.ofx.client.altmanager;

import me.offeex.ofx.client.altmanager.types.CrackedAccount;
import me.offeex.ofx.client.altmanager.types.MojangAccount;
import me.offeex.ofx.client.altmanager.types.TokenAccount;

public class Account {
    public String login, password;
    public AccountTypes type;

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

    public String login() {
        switch (this.type.toString()) {
            case "Cracked": {
                CrackedAccount account = new CrackedAccount(login);
                return account.login();
            }
            case "Mojang": {
                MojangAccount account = new MojangAccount(login, password);
                return account.login();
            }
            case "Token": {
                TokenAccount account = new TokenAccount(login, password);
                return account.login();
            }
        }
        return "l";
    }
}
