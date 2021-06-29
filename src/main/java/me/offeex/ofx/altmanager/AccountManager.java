package me.offeex.ofx.altmanager;

import com.google.gson.*;
import me.offeex.ofx.Main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    Path output;
    public List<Account> accounts = new ArrayList<Account>();
    JsonArray array = new JsonArray();

    public AccountManager() {
        if (!Files.exists(output = Paths.get(Main.name + "/accounts.json"))) {
            try {
                Files.createFile(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();

        try {
            accounts = gson.fromJson(output.toFile().toString(), accounts.getClass());
        } catch (JsonSyntaxException ignored) {}

        converter(accounts);
    }

    public void saveCracked(Account account) throws IOException {
        if (!Files.exists(output = Paths.get(Main.name + "/accounts.json"))) {
            Files.createFile(output);
        }
        accounts.add(account);
        writer(account);
    }

    public void savePremium(Account account) throws IOException {
        if (!Files.exists(output = Paths.get(Main.name + "/accounts.json"))) {
            Files.createFile(output);
        }
        accounts.add(account);
        writer(account);
    }

    public void saveToken(Account account) throws IOException {
        if (!Files.exists(output = Paths.get(Main.name + "/accounts.json"))) {
            Files.createFile(output);
        }
        accounts.add(account);
        writer(account);
    }

    public void writer(Account object) throws IOException {
        JsonObject jsonObject = writer(object.getLogin(), object.getPassword(), object.type);
        array.add(jsonObject);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(array);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(output)));
        writer.write(json);
        writer.close();
    }

    public void writer() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(array);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(output)));
        writer.write(json);
        writer.close();
    }

    public JsonObject writer(String login, String password, AccountTypes type) {
        JsonObject object = new JsonObject();
        object.add("type", new JsonPrimitive(type.name()));
        object.add("login", new JsonPrimitive(login));
        object.add("password", new JsonPrimitive(password));
        return object;
    }

    public void converter(List<Account> accounts) {
        for (Account account : accounts) {
            array.add(writer(account.getLogin(), account.getPassword(), account.type));
        }
    }

    public void deleteAccount(Account account) throws IOException {
        try {
            accounts.remove(account);
        } catch (NullPointerException ignored) {}

        try {
            array.remove(writer(account.getLogin(), account.getPassword(), account.type));
        } catch (NullPointerException ignored) {}

        writer();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
