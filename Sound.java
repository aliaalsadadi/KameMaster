package com.example;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {
    public static void gameSound() throws MalformedURLException {
        File file = new File("src/game.wav");
        URL url = file.toURI().toURL();
        System.out.println(url);
        AudioClip clip = Applet.newAudioClip(url);

        while (!Thread.currentThread().isInterrupted()) {
            clip.play();

            try {
                Thread.sleep(3 * 60 * 1000 + 5 * 1000); // Delay for 3 minutes and 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        gameSound();
    }

}