package me.offeex.bloomware.client.altmanager;

import com.google.gson.*;
import me.offeex.bloomware.Bloomware;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AccountManager {
    Path output;
    public List<Account> accounts = new ArrayList<>();
    JsonArray array = new JsonArray();

    public AccountManager() {
        if (!Files.exists(output = Paths.get(Bloomware.name + "/accounts.json"))) {
            try {
                Files.createFile(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String s = "";

        try {
            Scanner in = new Scanner(new File(Bloomware.name + "/accounts.json"));
            while(in.hasNext())
                s += in.nextLine() + "\r\n";
            in.close();
        } catch (Exception ignored) {}

        // ПРОВЕРКА ЕСЛИ БЛЯ АККАУНТОВ НЕТ НИХУЯ

        try {
            array = (JsonArray) new JsonParser().parse(s);
            converter(array);
        } catch (Exception e) {
            Bloomware.logger.info("Found bad account config! Repairing...");
            try {
                fileRepair();
            } catch (Exception ignored) {}
        }
    }

    public void saveAccount(Account account) throws IOException {
        if (!Files.exists(output = Paths.get(Bloomware.name + "/accounts.json"))) {
            Files.createFile(output);
        }
        accounts.add(account);
        writer(account);
    }

    public void fileRepair() throws IOException {
        if (!Files.exists(output = Paths.get(Bloomware.name + "/accounts.json"))) {
            Files.createFile(output);
        } else {
            Files.delete(output);
            Files.createFile(output);
            writer();
        }
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

    public void converter() {
        for (int i = array.size() - 1; i >= 0; i--) {
            array.remove(i);
        }
        accounts.forEach(account -> { array.add(writer(account.getLogin(), account.getPassword(), account.type)); });
    }

    public void converter(JsonArray a) {
        for (JsonElement object : a) {
            String[] data = new String[3];
            int i = 0;
            for (Map.Entry entry : ((JsonObject) object).entrySet()) {
                data[i] = entry.getValue().toString();
                i++;
            }
            accounts.add(new Account(data[1].replace("\"", ""), data[2].replace("\"", ""), AccountTypes.valueOf(data[0].replace("\"", ""))));
        }
    }

    public void deleteAccount(Account account) throws IOException {
        try {
            accounts.remove(account);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        converter();
        writer();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}