package me.offeex.ofx.api.friends;

import me.offeex.ofx.Bloomware;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.ArrayList;

public class FriendManager {
    private File dir, dataFile;

    public void addFriend(String nickname) throws FileNotFoundException {
        dir = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        dataFile = new File(dir, "friends.txt");
        ArrayList<String> t = loadFriends();
        t.add(nickname);
        PrintWriter pw = new PrintWriter(this.dataFile);
        for(String str : t) {
            pw.println(str);
        }
        pw.close();
    }

    public void removeFriend(String nickname) throws FileNotFoundException {
        dir = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        dataFile = new File(dir, "friends.txt");
        PrintWriter pw = new PrintWriter(this.dataFile);
        ArrayList<String> t = loadFriends();
        t.removeIf(str -> str.equals(nickname));
        for(String str : t) {
            pw.println(str);
        }
        pw.close();
    }

    public ArrayList<String> loadFriends() throws FileNotFoundException {
        ArrayList<String> friends = new ArrayList<>();
        dir = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        dataFile = new File(dir, "friends.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while (line != null) {
                String lineN = line;
                line = reader.readLine();
                friends.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public FriendManager() {
        dir = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        if (!dir.exists()) {
            dir.mkdir();
        }
        dataFile = new File(dir, "friends.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
