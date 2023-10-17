package com.example;

import java.awt.*;

public class HUD {
    public static float HEALTH = 100;
    private float greenValue = 255;
    private int score = 0;
    private int level = 1;
    public void tick(){
        HEALTH = Game.clamp(HEALTH,0,100);
        greenValue = HEALTH*2;
        greenValue = Game.clamp(greenValue,0,255);
        score++;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int Level){
        this.level = Level;
    }

    public void render(Graphics graphics){
        graphics.setColor(Color.gray);
        graphics.fillRect(15,15,200,32);
        graphics.setColor(new Color(75,(int) greenValue,0));
        graphics.fillRect(15,15, (int) HEALTH*2,32);
        graphics.setColor(Color.white);
        graphics.drawRect(15,15,200,32);

        graphics.drawString("Score: "+ score,15,64);
        graphics.drawString("Level: "+ level,15,80);
    }
}
