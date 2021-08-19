package me.offeex.bloomware.api.friends;

import me.offeex.bloomware.Bloomware;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.ArrayList;

public class FriendManager {
    private final File dataFile;
    private ArrayList<Friend> friends;

    public FriendManager() {
        File dir = new File(MinecraftClient.getInstance().runDirectory, Bloomware.name);
        dataFile = new File(dir, "friends.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        friends = new ArrayList<>();
        loadFriends();
    }

    public boolean addFriend(Friend friend) throws FileNotFoundException {
        if (!convert().contains(friend.getNickname())) {
            friends.add(friend);
            writeFile();
            return true;
        }
        return false;
    }

    public boolean removeFriend(Friend friend) throws FileNotFoundException {
        if (convert().contains(friend.getNickname())) {
            ArrayList<Friend> temp = new ArrayList<>();
            for (Friend friend1 : friends) {
                if (!friend1.getNickname().equals(friend.getNickname())) {
                    temp.add(new Friend(friend1.getNickname()));
                }
            }
            friends.clear();
            friends = temp;
            writeFile();
            return true;
        }
        return false;
    }

    public void writeFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(this.dataFile);
        convert().forEach(pw::println);
        pw.close();
    }

    public ArrayList<String> convert() {
        ArrayList<String> friendsText = new ArrayList<>();
//        for (Friend friend : friends) {
//            friendsText.add(friend.getNickname());
//        }
        friends.forEach(friend -> friendsText.add(friend.getNickname()));
        return friendsText;
    }

    public void loadFriends() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();
            while (line != null) {
                friends.add(new Friend(reader.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
