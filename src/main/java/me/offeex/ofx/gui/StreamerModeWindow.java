package me.offeex.ofx.gui;

import me.offeex.ofx.Main;

import javax.swing.*;
import java.awt.*;

/*
public class StreamerModeWindow {
    public JFrame streamerWindow;
    public JTextArea titleText;
    Font coolFont;

    public StreamerModeWindow() {
        Thread t = new Thread(() -> {
            streamerWindow = new JFrame("BloomWare - Streamer Mode");
            titleText = new JTextArea(1, 1);
            coolFont = new Font("Verdana", Font.PLAIN, 20);
            titleText.setFont(coolFont);
            streamerWindow.setSize(400, 250);
            streamerWindow.setResizable(false);
        });
        t.start();
    }

    public void enableScreen() {
        Thread n = new Thread(() -> {
            Main.streamerWin.streamerWindow.setVisible(true);
        });
        n.start();
    }

    public void disableScreen() {
        Thread n = new Thread(() -> {
            Main.streamerWin.streamerWindow.setVisible(false);
        });
        n.start();
    }

    public void updateText(String text) {
        Main.streamerWin.titleText.setText(text);
    }
}


 */