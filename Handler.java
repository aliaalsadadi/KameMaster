package com.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    private Game game;
    public Handler(Game game){
        this.game = game;
    }
    ArrayList<GameObject> object = new ArrayList<>();
    public void tick(){
        for (int i = 0; i < object.size(); i++){
                GameObject tempObject = object.get(i);
                tempObject.tick();
        }
    }
    public void render(Graphics g){
            for (int i = 0; i < object.size(); i++){
                GameObject tempObject = object.get(i);
                tempObject.render(g);
            }
    }
    public void addObject(GameObject object){
        if (object != null) this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    public void clearEnemies(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.Player){
                object.clear();
                if(game.gameState != Game.STATE.End) addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(),ID.Player,this));
            }
        }
    }
}