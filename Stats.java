package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stats {
    private double strength;

    public double strength() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\HP\\IdeaProjects\\ArmOS\\src\\tftest\\python.exe", "C:\\Users\\HP\\IdeaProjects\\ArmOS\\src\\try.py");
            Process process = processBuilder.start();

            // Read the output of the Python script
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            int count_1 = 0;
            int count_2 = 0;
            boolean started_2 = false;
            while ((line = outputReader.readLine()) != null) {
                System.out.print(line);
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '1' && !started_2) {
                        count_1++;
                    } else if (line.charAt(i) == '2') {
                        count_2++;
                        started_2 = true;
                    }
                }
            }

            if (count_1 <= 0) {
                strength = 0.0;
            } else if (count_2 <= 0) {
                strength = 0.0;
            } else {
                strength = (double) count_1 / count_2;
            }

            int exitCode = process.waitFor();
            System.out.println("Python script executed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return strength;
    }
}