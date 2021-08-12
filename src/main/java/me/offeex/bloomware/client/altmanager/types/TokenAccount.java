package me.offeex.bloomware.client.altmanager.types;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.client.altmanager.Account;
import me.offeex.bloomware.client.altmanager.AccountTypes;
import net.minecraft.client.util.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TokenAccount extends Account {
    String username, token;

    public TokenAccount(String username, String token) {
        super(username, token, AccountTypes.Token);
    }

    @Override
    public String login() {
        String UUID_ = "";
        try {
            UUID_ = getUUID(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bloomware.IMC.setSession(new Session(username, UUID_, token, "mojang"));
        return "Success";
    }

    private String getUUID(String username) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line = "";
        while ((line = reader.readLine()) != null)
            sb.append(line);
        reader.close();

        JsonObject json = new JsonParser().parse(sb.toString()).getAsJsonObject();
        return json.get("id").getAsString();
    }
}
